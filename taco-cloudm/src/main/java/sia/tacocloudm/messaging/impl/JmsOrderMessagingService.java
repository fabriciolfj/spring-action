package sia.tacocloudm.messaging.impl;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Service;
import sia.tacocloudm.domain.TacoOrder;
import sia.tacocloudm.messaging.OrderMessagingService;

import javax.jms.Destination;

@Slf4j
@Service
public class JmsOrderMessagingService implements OrderMessagingService {

    @Autowired
    private JmsTemplate jms;
    @Autowired
    private Destination orderQueue;

    @Override
    public void sendOrder(TacoOrder order) {
        log.info("Send order: {}", order);
        jms.convertAndSend(orderQueue, order, post -> {
            post.setStringProperty("X_ORDER_SOURCE", "WEB"); //cabeçalho da mensagem
            return post;
        });
    }
}
