package vn.edu.iuh.fit.model.people;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class Person {

    private String firstName;
    private String lastName;
    private int age;

    private Address address;
    private List<PhoneNumber> phoneNumbers;
}
