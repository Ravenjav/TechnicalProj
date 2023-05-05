package Belarus.Softarex.TechnicalProj.services;

import Belarus.Softarex.TechnicalProj.entities.User;
import Belarus.Softarex.TechnicalProj.entities.UserSecurity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class SecurityService implements UserDetailsService {

    @Autowired
    UserService userService;

    @Override
    public UserSecurity loadUserByUsername(String email) throws UsernameNotFoundException {
        System.out.println("Start----------------------------------------------------------");
        User user = userService.findUserById(email);
        System.out.println(user.toString());
        if (user == null){
            throw new UsernameNotFoundException("User not found");
        }
        System.out.println("---------------------------------------- ");
        return new UserSecurity(user.getUsername(), user.getPassword(), user.getAuthorities(), user.getUserInfo());
    }
}
