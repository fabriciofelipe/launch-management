package com.launch.management.launchmanagement.application.service;

import com.launch.management.launchmanagement.application.stream.LaunchPagamentoEventPublish;
import com.launch.management.launchmanagement.application.stream.LaunchRecebimentoEventPublish;
import com.launch.management.launchmanagement.domain.CashFlow;
import com.launch.management.launchmanagement.domain.Launch;
import com.launch.management.launchmanagement.infrastructure.error.ApiBadRequestException;
import com.launch.management.launchmanagement.infrastructure.error.ApiNotAccetableException;
import com.launch.management.launchmanagement.infrastructure.repository.CashFlowRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Optional;

@Slf4j
@Service
@RequiredArgsConstructor
public class LaunchService {

    private static final String RECEBIMENTO = "recebimento";
    private static final String PAGAMENTO = "pagamento";
    private final LaunchPagamentoEventPublish pagamentoEventPublish;
    private final LaunchRecebimentoEventPublish recebimentoEventPublish;
    private final CashFlowRepository cashFlowRepository;

    public Optional<Launch> create(Launch launch){
        Optional<Launch> launchTransient = Optional.ofNullable(launch);

        validaSaldo(launchTransient);

        validaDataLanacamento(launchTransient);

        launchTransient.filter(l -> l.getTipoLancamento().equalsIgnoreCase(RECEBIMENTO))
                .map(recebimentoEventPublish::send);

        launchTransient.filter(l -> l.getTipoLancamento().equalsIgnoreCase(PAGAMENTO))
                .map(pagamentoEventPublish::send);


        return launchTransient;
    }

    private void validaDataLanacamento(Optional<Launch> launch){
        boolean dataInavalida = launch.filter(l -> l.getDataLacamento().isBefore(LocalDate.now())).isPresent();
        if (dataInavalida){
            throw ApiBadRequestException
                    .builder()
                    .code(ApiBadRequestException.VALIDATION_ERROR)
                    .message("Não é permitido fazer lançamentos no passado")
                    .build();
        }
    }

    private void validaSaldo(Optional<Launch> launch){
        Optional<CashFlow> cashFlow = cashFlowRepository.findByCpfCnpjAndData(launch.get().getCpfCnpjDestino(), launch.get().getDataLacamento());

        boolean pagamento = launch.filter(l ->
                l.getTipoLancamento().equalsIgnoreCase(PAGAMENTO)).isPresent();

        boolean saldoinSuficiente = cashFlow.filter(saldo ->
                saldo.getTotal().add(launch.get().getValorLancamento().negate()).compareTo(BigDecimal.valueOf(-20000)) == -1).isPresent();

        if (saldoinSuficiente && pagamento){
            throw ApiNotAccetableException
                    .builder()
                    .code(ApiBadRequestException.VALIDATION_ERROR)
                    .message("Sua conta atingiu o limite maximo de R$ 20.000,00 negativos.")
                    .build();
        }
    }
}
