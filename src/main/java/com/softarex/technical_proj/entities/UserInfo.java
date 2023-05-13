package com.softarex.technical_proj.entities;

import lombok.*;
import org.hibernate.Hibernate;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "usersinfo")
@Getter
@Setter
@RequiredArgsConstructor
public class UserInfo {

    @OneToOne
    @JoinColumn(name = "username")
    private User user;

    @Column(name = "name")
    private String name;

    @Column(name = "surname")
    private String surname;

    @Id
    @Column(name = "nickname")
    private String nickname;

    @Override
    public String toString() {
        return "UserInfo{" +
                "name='" + name + '\'' +
                ", surname='" + surname + '\'' +
                ", nickname='" + nickname + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        UserInfo userInfo = (UserInfo) o;
        return nickname != null && Objects.equals(nickname, userInfo.nickname);
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}
