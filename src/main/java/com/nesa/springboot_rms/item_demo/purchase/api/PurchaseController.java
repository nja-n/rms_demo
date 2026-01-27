package com.nesa.springboot_rms.item_demo.purchase.api;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.nesa.springboot_rms.item_demo.purchase.api.dtos.PurchaseRequest;
import com.nesa.springboot_rms.item_demo.purchase.application.PuchaseApplicaitonService;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/purchase")
@RequiredArgsConstructor
public class PurchaseController {

    private final PuchaseApplicaitonService puchaseApplicaitonService;

    @PostMapping("/yes")
    public String postPurchaseYes(@RequestBody PurchaseRequest entity) {
        // puchaseApplicaitonService.purchase(entity);
        return "success";
    }
    
}
