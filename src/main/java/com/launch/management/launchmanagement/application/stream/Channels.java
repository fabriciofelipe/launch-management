package com.launch.management.launchmanagement.application.stream;

import org.springframework.cloud.stream.annotation.Output;
import org.springframework.messaging.MessageChannel;

public interface Channels {

    String OUTPUT_PAGAMENTO = "outputPagamento";
    String UTPUT_RECEBIMENTO = "outputRecebimento";

    @Output(Channels.OUTPUT_PAGAMENTO)
    MessageChannel outPutPagamento();

    @Output(Channels.UTPUT_RECEBIMENTO)
    MessageChannel outPutRecebimento();
}
