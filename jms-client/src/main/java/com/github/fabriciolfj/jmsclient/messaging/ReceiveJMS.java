package com.github.fabriciolfj.jmsclient.messaging;

import com.github.fabriciolfj.jmsclient.dto.TacoDTO;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

@Component
public class ReceiveJMS {

    @JmsListener(destination = "tacocloud.order.queue")
    public void receive(final TacoDTO dto) {
        System.out.println(dto);
    }
}
