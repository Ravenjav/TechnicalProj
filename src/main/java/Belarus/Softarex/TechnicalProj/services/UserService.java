package Belarus.Softarex.TechnicalProj.services;

import Belarus.Softarex.TechnicalProj.entities.User;
import Belarus.Softarex.TechnicalProj.entities.UserSecurity;
import Belarus.Softarex.TechnicalProj.exceptions.ServiceException;
import Belarus.Softarex.TechnicalProj.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;


@Service
public class UserService{

    @Autowired
    private UserRepository userRepository;

    User findUserById(String email){
        Optional<User> userDB = userRepository.findById(email);
        return userDB.orElse(null);
    }

    public void saveUser(User user) throws ServiceException {
        if (findUserById(user.getEmail()) != null){
            throw new ServiceException("User with this email is exist");
        }

        userRepository.save(user);
    }


}
