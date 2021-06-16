package sia.tacocloudm.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Table("Taco")
@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class Taco {

    @Id
    private Long id;
    @JsonIgnore
    @Column("created_at")
    private LocalDateTime createdAt = LocalDateTime.now();
    private String name;
    @Transient
    private Set<String> ingredientIds = new HashSet<>();
}
