package sia.tacocloudm.messaging.impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;
import sia.tacocloudm.domain.TacoOrder;
import sia.tacocloudm.messaging.OrderMessagingService;

@Slf4j
@Service
public class KafkaOrderMessagingService implements OrderMessagingService {

    @Autowired
    private KafkaTemplate<String, String> kafkaTemplate;
    @Autowired
    private ObjectMapper objectMapper;

    @Override
    public void sendOrder(TacoOrder order) {
        try {
            kafkaTemplate.sendDefault(objectMapper.writeValueAsString(order));
        } catch (Exception e) {
            log.error(e.getMessage());
        }
    }
}
