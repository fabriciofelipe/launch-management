package com.launch.management.launchmanagement.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Document(collection = "cashflow")
public class CashFlow {

    @Id
    private String id;
    private String cpfCnpj;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy")
    private LocalDate data;
    private List<Entrada> entradas;
    private List<Saida> saidas;
    private List<Encargo> encargos;
    private BigDecimal total;
    private String posicaoDia;



}
