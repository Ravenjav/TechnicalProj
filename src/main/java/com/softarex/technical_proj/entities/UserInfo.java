package com.softarex.technical_proj.entities;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Objects;

@Entity
@Table(name = "usersinfo")
@Getter
@Setter
@RequiredArgsConstructor
public class UserInfo implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;
    @OneToOne(cascade = {CascadeType.ALL})
    @JoinColumn(name = "username")
    private User user;

    @Column(name = "name")
    private String name;

    @Column(name = "surname")
    private String surname;

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
        if (o == null || getClass() != o.getClass()) return false;
        UserInfo userInfo = (UserInfo) o;
        return name.equals(userInfo.name) && surname.equals(userInfo.surname) && nickname.equals(userInfo.nickname);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, surname, nickname);
    }
}
