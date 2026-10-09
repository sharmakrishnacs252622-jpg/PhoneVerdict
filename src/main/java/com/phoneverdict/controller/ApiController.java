package com.phoneverdict.controller;

import com.phoneverdict.model.Phone;
import com.phoneverdict.service.PhoneService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
@CrossOrigin(origins = "*")
public class ApiController {

    private final PhoneService phoneService;

    @Autowired
    public ApiController(PhoneService phoneService) {
        this.phoneService = phoneService;
    }

    @GetMapping("/phones")
    public ResponseEntity<List<Phone>> getAllPhones() {
        return ResponseEntity.ok(phoneService.getAllPhones());
    }

    @GetMapping("/phones/search")
    public ResponseEntity<List<Phone>> searchPhones(@RequestParam(name = "q", defaultValue = "") String query) {
        return ResponseEntity.ok(phoneService.searchPhones(query));
    }

    @GetMapping("/phones/{id}")
    public ResponseEntity<Phone> getPhoneById(@PathVariable Long id) {
        try {
            return ResponseEntity.ok(phoneService.getPhoneById(id));
        } catch (IllegalArgumentException e) {
            return ResponseEntity.notFound().build();
        }
    }
}
