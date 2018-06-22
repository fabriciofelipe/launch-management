package com.launch.management.launchmanagement.application.service;

import com.launch.management.launchmanagement.application.stream.LaunchPagamentoEventPublish;
import com.launch.management.launchmanagement.application.stream.LaunchRecebimentoEventPublish;
import com.launch.management.launchmanagement.domain.Launch;
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
    private final LaunchPagamentoEventPublish pagamentoEventPublish;
    private final LaunchRecebimentoEventPublish recebimentoEventPublish;

    public Optional<Launch> create(Launch launch){
        launch.setDataLacamento(LocalDate.now());
        Optional<Launch> launchTransient =  Optional.ofNullable(launch);

            launchTransient.filter(l -> l.getTipoLancamento().equals(RECEBIMENTO))
                    .map(recebimentoEventPublish::send)
                    .orElse(pagamentoEventPublish.send(launch));


        return launchTransient;
    }
}
