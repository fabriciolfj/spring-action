package sia.tacocloudm.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Table("Taco_Ingredients")
public class TacoIngredient {

    @Column("taco")
    private Long idTaco;
    @Column("ingredient")
    private String idIngredient;
}
