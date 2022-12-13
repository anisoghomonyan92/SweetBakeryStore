package am.itspace.sweetbakerystore.dto;


import am.itspace.sweetbakerystore.entity.CardType;
import am.itspace.sweetbakerystore.entity.Status;
import lombok.Builder;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.util.Date;

@Data
public class CheckoutDto {

    @Size(max = 16, min = 16, message = "Length must be 16.")
    @Pattern(regexp = "^\\d+$", message = "Card number's must contain only digits.")
    @NotBlank(message = "Number of card can't be empty.")
    private String cardNumber;

    @Size(max = 3, min = 3, message = "Length must be 3.")
    @Pattern(regexp = "^\\d+$", message = "CVC code must contain only digits.")
    @NotBlank(message = "CVC  can't be empty.")
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
