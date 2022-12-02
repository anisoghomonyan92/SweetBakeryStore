package am.itspace.sweetbakerystore.dto;

import am.itspace.sweetbakerystore.entity.Product;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class BasketProductDto {
    private Product product;
    private long amount;
    private int quantity;

    public BasketProductDto(Product product, int quantity) {
        this.product = product;
        this.quantity = quantity;
        this.amount = (long) (this.quantity * this.product.getPrice());
    }

}
