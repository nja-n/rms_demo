package com.nesa.springboot_rms.itemManagement.infrastructure;


import com.nesa.springboot_rms.itemManagement.domain.service.ChickenCuttingDomainService;
import com.nesa.springboot_rms.itemManagement.domain.strategy.BoneInCuttingStrategy;
import com.nesa.springboot_rms.itemManagement.domain.strategy.BreastCuttingStrategy;
import com.nesa.springboot_rms.itemManagement.domain.strategy.ChickenCuttingStrategy;
import com.nesa.springboot_rms.itemManagement.domain.strategy.WingCuttingStrategy;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
public class DomainServiceConfig {

    @Bean
    public ChickenCuttingStrategy breastStrategy() {
        return new BreastCuttingStrategy();
    }

    @Bean
    public ChickenCuttingStrategy wingStrategy() {
        return new WingCuttingStrategy();
    }

    @Bean
    public ChickenCuttingStrategy boneInStrategy() {
        return new BoneInCuttingStrategy();
    }

    @Bean
    public ChickenCuttingDomainService chickenCuttingDomainService
            (List<ChickenCuttingStrategy> strategies) {
        return new ChickenCuttingDomainService(strategies);
    }
}
