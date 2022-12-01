package am.itspace.sweetbakerystore.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BasketRequest {
    private Integer product_id;
    private int quantity;
}
