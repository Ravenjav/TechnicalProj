package Belarus.Softarex.TechnicalProj.entities;

import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;

import java.util.Collection;

@Getter
@Setter
@EqualsAndHashCode
@ToString
public class UserSecurity extends User {

    private UserInfo userInfo;

    public UserSecurity(String username, String password, Collection<? extends GrantedAuthority> authorities) {
        super(username, password, authorities);
    }

    public UserSecurity(String username, String password, boolean enabled, boolean accountNonExpired, boolean credentialsNonExpired, boolean accountNonLocked, Collection<? extends GrantedAuthority> authorities) {
        super(username, password, enabled, accountNonExpired, credentialsNonExpired, accountNonLocked, authorities);
    }

    public UserSecurity(String username, String password, Collection<? extends GrantedAuthority> authorities, UserInfo userInfo) {
        super(username, password, authorities);
        this.userInfo = userInfo;
    }
}
