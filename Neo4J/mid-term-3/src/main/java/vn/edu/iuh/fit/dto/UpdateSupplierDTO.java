package vn.edu.iuh.fit.dto;

import lombok.*;
import vn.edu.iuh.fit.model.Product;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Builder
public class UpdateSupplierDTO {

    private String supplierID;
    private String companyName;
    private String contactName;
    private String country;

}
