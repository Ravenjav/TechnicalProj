package Belarus.Softarex.TechnicalProj.entities;

import lombok.Data;

import javax.persistence.*;

@Entity
@Table(name = "usersinfo")
@Data
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

}
