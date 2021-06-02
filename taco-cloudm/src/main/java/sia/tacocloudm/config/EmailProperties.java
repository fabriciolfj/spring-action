package sia.tacocloudm.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

@Data
@ConfigurationProperties(prefix = "tacocloud.email")
public class EmailProperties {

    private String username;
    private String password;
    private String hots;
    private String mailBox;
    private long pollRate = 30000;

    public String getImapUrl() {
        return String.format("imaps://%s:%s@%s/%s", this.username, this.password, this.hots, this.mailBox);
    }
}
