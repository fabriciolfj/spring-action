package sia.tacocloudm.messaging;

import sia.tacocloudm.domain.Order;

public interface OrderMessagingService {

    void sendOrder(final Order order);
}
