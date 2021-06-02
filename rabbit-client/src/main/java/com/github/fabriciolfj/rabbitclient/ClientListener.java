package com.github.fabriciolfj.rabbitclient;

import com.github.fabriciolfj.rabbitclient.dto.TacoDTO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class ClientListener {

    @RabbitListener(queues = "tacocloud.order")
    public void test(TacoDTO value) {
        log.info("Value: {}", value);
    }
}
