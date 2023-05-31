package com.softarex.technical_proj.entities;

import lombok.Data;
import lombok.ToString;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.util.Collection;
import java.util.List;

@Entity
@Table(name = "users")
@Data
public class User implements UserDetails{

    @Id
    @Column(name = "username")
    private String  email;

    @Column(name = "password")
    private String password;

    @Column(name = "enabled")
    private Boolean enabled;

    @OneToMany(fetch = FetchType.EAGER, mappedBy = "user")
    @ToString.Exclude
    private List<Authority> authorityList;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "sender")
    @ToString.Exclude
    private List<Question> questions_sender;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "responsible")
    @ToString.Exclude
    private List<Question> questions_responsible;

    @OneToOne(mappedBy = "user", cascade = {CascadeType.ALL})
    private UserInfo userInfo;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return getAuthorityList();
    }

    @Override
    public String getUsername() {
        return email;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
