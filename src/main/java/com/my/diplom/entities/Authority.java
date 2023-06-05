package com.my.diplom.entities;

import lombok.*;
import org.hibernate.Hibernate;
import org.springframework.security.core.GrantedAuthority;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "authorities")
@Getter
@Setter
@RequiredArgsConstructor
public class Authority implements GrantedAuthority {
    @Id
    @Column(name = "authority")
    private String authority;

    @ManyToOne
    @JoinColumn(name = "username")
    private User user;


    @Override
    public String toString() {
        return "Authority{" +
                "authority='" + authority + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        Authority authority1 = (Authority) o;
        return authority != null && Objects.equals(authority, authority1.authority);
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}
