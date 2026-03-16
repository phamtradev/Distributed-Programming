package vn.edu.iuh.fit.utils.people.StreamAPI;

import jakarta.json.Json;
import jakarta.json.stream.JsonParser;
import vn.edu.iuh.fit.model.people.Address;
import vn.edu.iuh.fit.model.people.Person;
import vn.edu.iuh.fit.model.people.PhoneNumber;

import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;

public class JsonUtils {

    public static List<Person> fromJson(String fileName) {

        List<Person> res = new ArrayList<>();

        try (JsonParser parser = Json.createParser(new FileReader(fileName))) {

            Person person = null;
            Address address = null;

            List<PhoneNumber> phoneNumbers = null;

            PhoneNumber phoneNumber = null;

            String key = "";

            while (parser.hasNext()) {
                JsonParser.Event event = parser.next();

                switch (event) {
                    case START_ARRAY -> {
                        if (key.equalsIgnoreCase("phoneNumbers")) {
                            phoneNumbers = new ArrayList<>();
                        }
                    }
                    case START_OBJECT -> {
                        if (person == null) {
                            person = new Person();
                        } else if (address == null && key.equalsIgnoreCase("address")) {
                            address = new Address();
                        } else if (phoneNumber == null) {
                            phoneNumber = new PhoneNumber();
                        }
                    }
                    case END_OBJECT -> {
                        if (address != null) {
                            person.setAddress(address);
                            address = null;
                        } else if (phoneNumber != null) {
                            phoneNumbers.add(phoneNumber);
                            phoneNumber = null;
                        } else if (person != null) {
                            person.setPhoneNumbers(phoneNumbers);
                            res.add(person);
                            phoneNumbers = null;
                            person = null;
                        }
                    }
                    case VALUE_NUMBER -> {
                        int value = parser.getInt();

                        switch (key) {
                            case "age" -> person.setAge(value);
                            case "postalCode" -> address.setPostalCode(value);
                        }
                    }
                    case VALUE_STRING -> {
                        String value = parser.getString();

                        switch (key) {
                            case "firstName" -> person.setFirstName(value);
                            case "lastName" -> person.setLastName(value);
                            case "streetAddress" -> address.setStreetAddress(value);
                            case "city" -> address.setCity(value);
                            case "state" -> address.setState(value);
                            case "type" -> phoneNumber.setType(value);
                            case "number" -> phoneNumber.setNumber(value);
                        }
                    }
                    case KEY_NAME -> {
                        key = parser.getString();
                    }
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return res;
    }
}
