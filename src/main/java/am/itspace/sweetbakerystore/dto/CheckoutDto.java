package am.itspace.sweetbakerystore.dto;


import am.itspace.sweetbakerystore.entity.CardType;
import am.itspace.sweetbakerystore.entity.Status;
import lombok.Data;
import org.hibernate.validator.constraints.Range;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import java.util.Date;

@Data
public class CheckoutDto {

    @Range(max = 16, min = 16, message = "Length must be 16.")
    @Pattern(regexp = "[0-9]", message = "Card number's must contain only digits.")
    @NotNull(message = "Number of card can't be empty.")
    private String cardNumber;

    @Range(max = 3, min = 3, message = "Length must be 3.")
    @NotNull(message = "CVC  can't be empty.")
    private Integer cvcCode;

    @NotNull(message = "Expiration Date can't be empty.")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date expirationDate;

    @Enumerated(value = EnumType.STRING)
    private Status status;

    @Enumerated(value = EnumType.STRING)
    private CardType cardType;

    private boolean isGift;

    private String wishNotes;

    private Integer productId;

    private Integer quantity;

}
