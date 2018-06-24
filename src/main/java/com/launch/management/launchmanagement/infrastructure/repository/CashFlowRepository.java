package com.launch.management.launchmanagement.infrastructure.repository;

import com.launch.management.launchmanagement.domain.CashFlow;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface CashFlowRepository extends MongoRepository<CashFlow, String> {

    Optional <List<CashFlow>> findByDataBetween(LocalDate start,LocalDate end);

    Optional<CashFlow> findByCpfCnpjAndData(String cpfCnpj, LocalDate data);


}



