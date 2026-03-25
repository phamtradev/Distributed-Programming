package vn.edu.iuh.fit.model;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Builder
public class Supplier {

    private String supplierId;
    private String supplierName;
    private String companyName;
    private String contactName;
    private String country;

}
