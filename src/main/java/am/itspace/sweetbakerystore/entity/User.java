package am.itspace.sweetbakerystore.entity;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.util.Date;

@AllArgsConstructor
@Builder
@Data
@Entity
@NoArgsConstructor
@Table(name = "user")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String name;
    private String surname;
    private String email;
    private String password;
    private String phone;
    @ManyToOne
    private Address address;
    private String profilePic;
    @Enumerated(value = EnumType.STRING)
    private Role role;
    private boolean isActive;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date createAt;
    private String verifyToken;

}