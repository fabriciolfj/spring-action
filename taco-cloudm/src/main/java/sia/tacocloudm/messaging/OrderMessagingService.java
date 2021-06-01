package sia.tacocloudm.messaging;

import sia.tacocloudm.domain.TacoOrder;

public interface OrderMessagingService {

    void sendOrder(final TacoOrder order);
}
