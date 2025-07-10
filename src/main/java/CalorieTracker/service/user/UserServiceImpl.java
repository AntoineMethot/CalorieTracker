package CalorieTracker.service.user;

import CalorieTracker.dao.UserRepository;
import CalorieTracker.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public UserServiceImpl(UserRepository userRepository, PasswordEncoder passwordEncoder, JdbcTemplate jdbcTemplate) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public List<User> findAll() {
        List<User> users = userRepository.findAll();

        // For each user, fetch their roles
        for (User user : users) {
            List<String> roles = jdbcTemplate.queryForList(
                    "SELECT authority FROM authorities WHERE username = ?",
                    String.class,
                    user.getUsername()
            );
            user.setRoles(roles);
        }

        return users;
    }

    @Override
    public User findByUsername(String username) {
        return userRepository.findByUsername(username)
                .orElseThrow(() -> new RuntimeException("User not found with username: " + username));
    }

    @Override
    public void save(User user) {
        // Encode password only if it's a new user or password is not already encoded
        if (!user.getPassword().startsWith("{")) {
            user.setPassword(passwordEncoder.encode(user.getPassword()));
        }

        userRepository.save(user);
    }

    @Override
    public void deleteByUsername(String username) {
        User user = findByUsername(username); // reuses existing method
        userRepository.delete(user);
    }
}
