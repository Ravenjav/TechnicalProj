package Belarus.Softarex.TechnicalProj.repository;

import Belarus.Softarex.TechnicalProj.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, String> {
}
