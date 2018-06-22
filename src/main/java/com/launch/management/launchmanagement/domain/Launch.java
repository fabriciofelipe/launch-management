package com.launch.management.launchmanagement.domain;

import io.swagger.annotations.ApiModelProperty;
import lombok.*;
import org.hibernate.validator.constraints.br.CNPJ;
import org.hibernate.validator.constraints.br.CPF;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.validation.annotation.Validated;

import java.math.BigDecimal;
import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Document(collection = "launchs")
public class Launch {


    @ApiModelProperty(value = "Tipos de lançamento (pagamento e recebimento)")
    @NonNull
    private String tipoLancamento;
    @ApiModelProperty(value = "Qualquer descrição sobre o pagamento")
    private String descricao;
    @ApiModelProperty(value = "Conta Destino para o Lançamento")
    @NonNull
    private String contaDestino;
    @ApiModelProperty(value = "Banco destino para Lançamento")
    @NonNull
    private String bancoDestino;
    @ApiModelProperty(value = "Tipo Conta CC ou CP")
    @NonNull
    private String tipoConta;
    @NonNull
    @ApiModelProperty(value = "Cpf ou Cnpj para o Lançamento")
    private String cpfCnpjDestino;
    @ApiModelProperty(value = "Valor em reais do lançamento no formato (R$ 0.000,00)")
    @NonNull
    private BigDecimal valorLancamento;
    @ApiModelProperty(value = "Valor em reais dos encargos do lançamento no formato (R$ 0.000,00)")
    @NonNull
    private BigDecimal encargos;
    @ApiModelProperty(value = "Data em que a lançamento o ocorreu no formato (dd-mm-aaaa)")
    private LocalDate dataLacamento;
}
