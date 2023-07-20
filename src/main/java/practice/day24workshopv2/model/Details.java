package practice.day24workshopv2.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Details {
    private Integer id;
    private String product;
    private Float unitPrice;
    private Float discount;
    private Integer quantity;
    private Integer orderId;
}
