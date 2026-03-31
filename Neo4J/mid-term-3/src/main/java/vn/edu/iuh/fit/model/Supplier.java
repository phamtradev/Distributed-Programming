package vn.edu.iuh.fit.model;

import lombok.*;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Builder
public class Supplier {

    private String supplierID;
    private String companyName;
    private String contactName;
    private String country;

    private List<Product> products;
}
