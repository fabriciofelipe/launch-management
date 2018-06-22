package com.launch.management.launchmanagement.application.stream;

import com.launch.management.launchmanagement.domain.Launch;
import lombok.RequiredArgsConstructor;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class LaunchRecebimentoEventPublish {

    private final Channels channels;

    public Optional<Launch> send(final Launch launch){
        final Message msg = MessageBuilder.withPayload(launch).build();
        channels.outPutRecebimento().send(msg);
        return Optional.ofNullable(launch);
    }

}
