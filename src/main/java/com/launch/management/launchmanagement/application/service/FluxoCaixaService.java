package com.launch.management.launchmanagement.application.service;

import com.launch.management.launchmanagement.domain.CashFlow;
import com.launch.management.launchmanagement.infrastructure.repository.CashFlowRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Slf4j
@Service
@RequiredArgsConstructor
public class FluxoCaixaService {

    private final CashFlowRepository cashFlowRepository;

    public Optional <List<CashFlow>> find(){
        LocalDate start = LocalDate.now();
        LocalDate end = LocalDate.now().plusDays(30);
        return cashFlowRepository.findByDataBetween(start, end);
    }
}
