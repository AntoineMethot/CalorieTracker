package CalorieTracker.service.user;

import CalorieTracker.dao.UserRepository;
import CalorieTracker.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public UserServiceImpl(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public List<User> findAll() {
        // Logic to find all users
        return userRepository.findAll(); // Replace with actual implementation
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
