package sia.tacocloudm.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.cassandra.core.mapping.UserDefinedType;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@UserDefinedType("taco")
public class TacosUDT {

    private String name;
    private List<IngredientUDT> ingredients;
}
