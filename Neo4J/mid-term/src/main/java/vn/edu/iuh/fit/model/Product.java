package vn.edu.iuh.fit.model;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Builder
public class Product {

    private String productId;
    private String productName;
    private String unit;
    private double unitPrice;
    private int unitsInStock;
    private Supplier supplier;

}
