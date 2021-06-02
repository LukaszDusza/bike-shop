package pl.akademia.api;

import org.springframework.stereotype.Service;
import pl.akademia.api.model.UserApp;
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

    public UserApp createUser(UserApp user) {
        if (!validateEmail(user)) throw new IllegalArgumentException("Invalid email address");
        user.setUserRole(UserRole.CLIENT_ROLE.getRoleName());
        user.setRegistrationDate(LocalDateTime.now());
        user.setActive(true);
        return userRepository.save(user);
    }

    public boolean validateEmail(UserApp user) {
        String regex = "^[\\w!#$%&'*+/=?`{|}~^-]+(?:\\.[\\w!#$%&'*+/=?`{|}~^-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,6}";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(user.getLogin());
        return matcher.matches();
    }

    public List<UserApp> getAllUsers() {
        return userRepository.findAll();
    }
}
