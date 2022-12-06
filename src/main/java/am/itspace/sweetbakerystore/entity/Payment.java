package am.itspace.sweetbakerystore.entity;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Range;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
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

    @Range(max = 16, min = 16, message = "Length must be 16.")
    @Pattern(regexp = "[0-9]", message = "Card number's must contain only digits.")
    @NotNull(message = "Number of card can't be empty.")
    private String cardNumber;

    @Range(max = 3, min = 3, message = "Length must be 3.")
    @Pattern(regexp = "[0-9]", message = "CVC code must contain only digits.")
    @NotNull(message = "CVC  can't be empty.")
    private Integer cvcCode;

    @NotNull(message = "Expiration Date can't be empty.")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date expirationDate;

    @Enumerated(value = EnumType.STRING)
    private Status status;

    @ManyToOne
    private User user;

    @Enumerated(value = EnumType.STRING)
    private CardType cardType;

    private double totalAmount;
}
