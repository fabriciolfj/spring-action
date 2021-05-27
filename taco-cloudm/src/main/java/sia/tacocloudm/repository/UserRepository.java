package sia.tacocloudm.repository;

import org.springframework.data.repository.CrudRepository;
import sia.tacocloudm.domain.User;

public interface UserRepository extends CrudRepository<User, Long> {

    User findByUsername(final String username);
}
