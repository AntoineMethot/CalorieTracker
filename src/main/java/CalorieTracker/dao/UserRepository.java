package CalorieTracker.dao;

import CalorieTracker.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    // This interface will automatically provide CRUD operations for User entity
    // No need to implement standard methods, JpaRepository provides them out of the box
    //User findByUsername(String username);
    Optional<User> findByUsername(String username);
}
