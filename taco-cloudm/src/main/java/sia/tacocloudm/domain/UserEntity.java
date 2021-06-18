package sia.tacocloudm.domain;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;


@Table("user")
@Data
public class UserEntity {

    @Id
    private Long id;
    private String name;
    private String password;

    public UserDetails toUserDetails() {
        return User.withUsername(this.name)
                .password(this.password)
                .roles("USER")
                .build();
    }
}
