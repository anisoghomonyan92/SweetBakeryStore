package am.itspace.sweetbakerystore.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class BasketDto {

    private List<BasketProductDto> basketProductDtos = new ArrayList<>();
    private long total;

    public void setSingleProduct(BasketProductDto product) {
        this.basketProductDtos.add(product);
    }

    public void calculateTotal() {
        total = basketProductDtos.stream().mapToLong(b -> b.getAmount() * b.getQuantity()).sum();
    }

    public void removeSingleProduct(int id) {
        this.basketProductDtos.removeIf(b -> b.getProduct().getId() == id);
    }
}