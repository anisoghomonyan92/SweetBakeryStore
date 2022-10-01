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
@Table(name = "payment")
public class Payment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String cardNumber;
    private int cvcCode;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date expirationDate;
    @Enumerated(value = EnumType.STRING)
    private Status status;
    @ManyToOne
    private User user;
    @Enumerated(value = EnumType.STRING)
    private CardType cardType;
}
