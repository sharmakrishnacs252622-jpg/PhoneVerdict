package com.phoneverdict.controller;

import com.phoneverdict.model.Phone;
import com.phoneverdict.service.PhoneService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Collections;
import java.util.List;

@Controller
public class SearchController {

    private final PhoneService phoneService;

    @Autowired
    public SearchController(PhoneService phoneService) {
        this.phoneService = phoneService;
    }

    @GetMapping("/search")
    public String search(@RequestParam(name = "q", required = false) String query, Model model) {
        List<Phone> phones;
        if (query == null || query.trim().isEmpty()) {
            phones = Collections.emptyList();
        } else {
            phones = phoneService.searchPhones(query.trim());
        }

        model.addAttribute("phones", phones);
        model.addAttribute("query", query != null ? query.trim() : "");
        return "search-results";
    }
}
