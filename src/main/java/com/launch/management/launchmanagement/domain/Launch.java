package com.launch.management.launchmanagement.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Document(collection = "launchs")
public class Launch {


    @ApiModelProperty(value = "Tipos de lançamento (pagamento e recebimento)")
    @NotNull
    private String tipoLancamento;
    @ApiModelProperty(value = "Qualquer descrição sobre o pagamento")
    private String descricao;
    @ApiModelProperty(value = "Conta Destino para o Lançamento")
    @NotNull
    private String contaDestino;
    @ApiModelProperty(value = "Banco destino para Lançamento")
    @NotNull
    private String bancoDestino;
    @ApiModelProperty(value = "Tipo Conta CC ou CP")
    @NotNull
    private String tipoConta;
    @NotNull
    @ApiModelProperty(value = "Cpf ou Cnpj para o Lançamento")
    private String cpfCnpjDestino;
    @ApiModelProperty(value = "Valor em reais do lançamento no formato (R$ 0.000,00)")
    @NotNull
    private BigDecimal valorLancamento;
    @ApiModelProperty(value = "Valor em reais dos encargos do lançamento no formato (R$ 0.000,00)")
    @NotNull
    private BigDecimal encargos;
    @ApiModelProperty(value = "Data em que a lançamento o ocorreu no formato (dd-mm-aaaa)")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy")
    @NotNull
    private LocalDate dataLacamento;
}
