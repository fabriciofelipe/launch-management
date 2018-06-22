package com.launch.management.launchmanagement.application.stream;

import com.launch.management.launchmanagement.domain.Launch;
import lombok.RequiredArgsConstructor;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class launchPagamentoEventPublish {

    private final Channels channels;

    public void send(final Launch launch){
        final Message msg = MessageBuilder.withPayload(launch).build();
        channels.outPutPagamento().send(msg);
    }

}
