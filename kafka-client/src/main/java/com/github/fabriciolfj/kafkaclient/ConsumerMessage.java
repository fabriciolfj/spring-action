package com.github.fabriciolfj.kafkaclient;

import com.github.fabriciolfj.kafkaclient.domain.Taco;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.Message;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class ConsumerMessage {

    @KafkaListener(topics = "tacocloud.orders.topic", groupId = "consumer")
    public void receive(final String taco, final Message<String> headers) {
        log.info("Headers: {}", headers.getHeaders().get(KafkaHeaders.RECEIVED_PARTITION_ID));
        log.info("Received: {}", taco);
    }

}
