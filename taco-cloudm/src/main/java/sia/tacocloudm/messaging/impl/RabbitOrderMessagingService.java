package sia.tacocloudm.messaging.impl;

import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessageProperties;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import sia.tacocloudm.domain.TacoOrder;
import sia.tacocloudm.messaging.OrderMessagingService;

@Service
public class RabbitOrderMessagingService implements OrderMessagingService {

    @Autowired
    private RabbitTemplate rabbitTemplate;

    @Override
    public void sendOrder(TacoOrder order) {
        MessageConverter converter = rabbitTemplate.getMessageConverter();
        MessageProperties propos = new MessageProperties();
        propos.setHeader("X_ORDER_SOURCE", "WEB");
        Message message = converter.toMessage(order, propos);
        rabbitTemplate.send("tacocloud.order", message);
    }
}
