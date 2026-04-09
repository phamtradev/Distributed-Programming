package vn.edu.iuh.fit.dto;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Builder
public class ProductsBySupplierDTO {

    private String companyName;
    private int page;
    private int size;

}
