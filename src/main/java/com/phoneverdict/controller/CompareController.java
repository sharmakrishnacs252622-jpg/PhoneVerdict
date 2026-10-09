package com.phoneverdict.controller;

import com.phoneverdict.dto.ComparisonResult;
import com.phoneverdict.model.Phone;
import com.phoneverdict.service.PhoneService;
import com.phoneverdict.service.VerdictService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Controller
@RequestMapping("/compare")
public class CompareController {

    private final PhoneService phoneService;
    private final VerdictService verdictService;

    @Autowired
    public CompareController(PhoneService phoneService, VerdictService verdictService) {
        this.phoneService = phoneService;
        this.verdictService = verdictService;
    }

    @GetMapping
    public String compareSelection(Model model) {
        model.addAttribute("allPhones", phoneService.getAllPhones());
        return "compare";
    }

    @GetMapping("/result")
    public String compareResult(@RequestParam(name = "ids", required = false) String idsParam,
                                Model model,
                                RedirectAttributes redirectAttributes) {

        if (idsParam == null || idsParam.trim().isEmpty()) {
            redirectAttributes.addFlashAttribute("errorMessage", "Please select at least 2 smartphones to compare.");
            return "redirect:/compare";
        }

        List<Phone> selectedPhones = new ArrayList<>();
        String[] ids = idsParam.split(",");

        for (String idStr : ids) {
            try {
                Long id = Long.parseLong(idStr.trim());
                selectedPhones.add(phoneService.getPhoneById(id));
            } catch (Exception ignored) {
            }
        }

        if (selectedPhones.size() < 2) {
            redirectAttributes.addFlashAttribute("errorMessage", "Please select at least 2 valid smartphones to compare.");
            return "redirect:/compare";
        }

        ComparisonResult comparison = verdictService.comparePhones(selectedPhones);
        model.addAttribute("phones", selectedPhones);
        model.addAttribute("comparison", comparison);

        return "compare-result";
    }
}
