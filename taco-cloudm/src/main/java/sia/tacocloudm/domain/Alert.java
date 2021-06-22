package sia.tacocloudm.domain;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.Instant;

@Data
@AllArgsConstructor
public class Alert {

    private Level level;
    private String orderedBy;
    private Instant orderedAt;

    public static enum Level{
        YELLOW, ORANGE, RED, BLACK
    }
}
