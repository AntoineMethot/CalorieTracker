package CalorieTracker.service.user;

import CalorieTracker.entity.User;

import java.util.List;

public interface UserService {
    List<User> findAll();
    User findByUsername(String username);
    void save(User user); // make sure to encode password!
    void deleteByUsername(String username);
}
