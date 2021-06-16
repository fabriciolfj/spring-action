package sia.tacocloudm.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

import java.time.LocalDateTime;
import java.util.*;

@Data
@Table("TACO_ORDER")
public class Order {

    @Id
    private Long id;

    private String deliveryName;
    private String deliveryStreet;
    private String deliveryCity;
    private String deliveryState;
    private String deliveryZip;
    private String ccNumber;
    private String ccExpiration;
    private String ccCVV;
    @Column("PLACED_AT")
    @JsonIgnore
    private LocalDateTime placedAt = LocalDateTime.now();

    @Transient
    private Set<Long> tacoIds = new LinkedHashSet<>();

    public void add(final Long idTaco) {
        this.tacoIds.add(idTaco);
    }
}
