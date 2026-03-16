package vn.edu.iuh.fit.main.people;

import vn.edu.iuh.fit.model.people.Person;
import vn.edu.iuh.fit.utils.people.StreamAPI.JsonUtils;

import java.util.List;

public class StreamAPIMain {
    public static void main(String[] args) {
        List<Person> persons = JsonUtils.fromJson("Json Process/json/people/people.json");
        persons.forEach(System.out::println);

    }
}
