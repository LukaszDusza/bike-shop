package pl.akademia.api;

import org.springframework.stereotype.Service;
import pl.akademia.api.model.User;
import pl.akademia.api.model.UserRole;

import java.time.LocalDateTime;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Service
public class UserService {

    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public User createUser(User user) {
        if (!validateEmail(user)) throw new IllegalArgumentException("Invalid email address");
        user.setUserRole(UserRole.USER_ROLE.getRoleName());
        user.setRegistrationDate(LocalDateTime.now());
        user.setActive(true);
        return userRepository.save(user);
    }

    public boolean validateEmail(User user) {
        String regex = "^[\\w!#$%&'*+/=?`{|}~^-]+(?:\\.[\\w!#$%&'*+/=?`{|}~^-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,6}";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(user.getLogin());
        return matcher.matches();
    }

    public List<User> getAllUsers() {
        return userRepository.findAll();
    }
}
