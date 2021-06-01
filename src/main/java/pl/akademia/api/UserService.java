package pl.akademia.api;

import org.springframework.stereotype.Service;
import pl.akademia.api.model.User;
import pl.akademia.api.model.UserRole;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class UserService {

    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public User createUser(User user){
        user.setUserRole(UserRole.USER_ROLE.getRoleName());
        user.setRegistrationDate(LocalDateTime.now());
        user.setActive(true);
        return userRepository.save(user);
    }

    public List<User> getAllUsers(){
        return userRepository.findAll();
    }
}
