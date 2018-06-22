package com.launch.management.launchmanagement.application.service;

import com.launch.management.launchmanagement.application.stream.LaunchPagamentoEventPublish;
import com.launch.management.launchmanagement.application.stream.LaunchRecebimentoEventPublish;
import com.launch.management.launchmanagement.domain.Launch;
import com.launch.management.launchmanagement.infrastructure.error.ApiBadRequestException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

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

    public Optional<Launch> create(Launch launch){
        Optional<Launch> launchTransient = Optional.ofNullable(launch);

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
}
