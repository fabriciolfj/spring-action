package sia.tacocloudm.config;

import org.apache.activemq.artemis.jms.client.ActiveMQQueue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jms.support.converter.MappingJackson2MessageConverter;
import sia.tacocloudm.domain.TacoOrder;

import javax.jms.Destination;
import java.util.HashMap;
import java.util.Map;

@Configuration
public class BeansMessaging {

    @Bean
    public Destination orderQueue() {
        return new ActiveMQQueue("tacocloud.order.queue");
    }

    @Bean
    public MappingJackson2MessageConverter messageConverter() {
        final var converter = new MappingJackson2MessageConverter();
        converter.setTypeIdPropertyName("_typeId");

        Map<String, Class<?>> typeIdMappings = new HashMap<>();
        typeIdMappings.put("order", TacoOrder.class); //dando mais flexibilidade, ou seja, posso mapear para uma outra classe com nome order
        converter.setTypeIdMappings(typeIdMappings);
        return converter;
    }
}
