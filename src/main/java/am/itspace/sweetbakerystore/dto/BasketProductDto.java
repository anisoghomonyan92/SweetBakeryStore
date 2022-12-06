package am.itspace.sweetbakerystore.dto;

import am.itspace.sweetbakerystore.entity.Product;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder

@NoArgsConstructor
public class BasketProductDto {
    private Product product;
    private int quantity;

    public BasketProductDto(Product product, int quantity) {
        this.product = product;
        this.quantity = quantity;
    }

}
