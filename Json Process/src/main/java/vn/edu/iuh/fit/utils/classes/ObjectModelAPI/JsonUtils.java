package vn.edu.iuh.fit.utils.classes.ObjectModelAPI;


import jakarta.json.*;
import jakarta.json.stream.JsonGenerator;
import vn.edu.iuh.fit.model.classes.Address;
import vn.edu.iuh.fit.model.classes.ClassInfo;
import vn.edu.iuh.fit.model.classes.Student;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.Writer;
import java.util.*;

public class JsonUtils {

    //read json
    public static List<ClassInfo> fromJson(String fileName) {

        List<ClassInfo> res = new ArrayList<>();

        try (JsonReader reader = Json.createReader(new FileReader(fileName))) {
            JsonArray classInfoJsonArray = reader.readArray();

            classInfoJsonArray.forEach(classInfoValue -> {
                JsonObject classInfoJsonObject = classInfoValue.asJsonObject();

                String name = classInfoJsonObject.getString("name");
                String teacher = classInfoJsonObject.getString("teacher");
                int room = classInfoJsonObject.getInt("room");
                String startTime = classInfoJsonObject.getString("start_time");
                String endTime = classInfoJsonObject.getString("end_time");

                List<Student> students = new ArrayList<>();

                JsonArray studentJsonArray = classInfoJsonObject.getJsonArray("students");

                studentJsonArray.forEach(studentValue -> {
                    JsonObject studentJsonObject = studentValue.asJsonObject();

                    String studentName = studentJsonObject.getString("name");
                    int age = studentJsonObject.getInt("age");
                    double gpa = studentJsonObject.getJsonNumber("gpa").doubleValue();

                    JsonObject addressJsonObject = studentJsonObject.getJsonObject("address");

                    Address address = new Address(
                        addressJsonObject.getString("street"),
                        addressJsonObject.getString("city"),
                        addressJsonObject.getString("state"),
                        addressJsonObject.getString("zip")
                    );

                    Student student = new Student(
                      studentName, age, gpa, address
                    );

                    students.add(student);

                });

                ClassInfo classInfo = new ClassInfo(
                  name, teacher, room, startTime, endTime, students
                );

                res.add(classInfo);

            });
        } catch (Exception e) {
            e.printStackTrace();
        }

        return res;
    }

    //write json
    public static void toJson(List<ClassInfo> classInfos, String fileName) {

        Map<String, Object> config = new HashMap<>();
        config.put(JsonGenerator.PRETTY_PRINTING, true);
        JsonWriterFactory jsonWriterFactory = Json.createWriterFactory(config);

        try (JsonWriter writer = jsonWriterFactory.createWriter(new FileWriter(fileName))) {
            JsonArrayBuilder classInfoJsonArray = Json.createArrayBuilder();

            classInfos.forEach(x -> {
                JsonObjectBuilder classInfoJsonObject = Json
                        .createObjectBuilder()
                        .add("name", x.getName())
                        .add("teacher", x.getTeacher())
                        .add("room", x.getRoom())
                        .add("start_time", x.getStartTime())
                        .add("end_time", x.getEndTime());

                JsonArrayBuilder studentJsonArray = Json.createArrayBuilder();

                x.getStudents().forEach(y -> {
                    JsonObjectBuilder studentJsonObject = Json
                            .createObjectBuilder()
                            .add("name", y.getName())
                            .add("age", y.getAge())
                            .add("gpa", y.getGpa());

                    JsonObjectBuilder addressJsonObject = Json
                            .createObjectBuilder()
                            .add("street", y.getAddress().getStreet())
                            .add("city", y.getAddress().getCity())
                            .add("state", y.getAddress().getState())
                            .add("zip", y.getAddress().getZip());

                    studentJsonObject.add("address", addressJsonObject);

                    studentJsonArray.add(studentJsonObject);
                });

                classInfoJsonObject.add("students", studentJsonArray);

                classInfoJsonArray.add(classInfoJsonObject);
            });

            writer.writeArray(classInfoJsonArray.build());

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    //doc file co dieu kien
    public static List<Student> listStudentByClassName(String className, String fileName) {

        List<Student> res = new ArrayList<>();

        try (JsonReader reader = Json.createReader(new FileReader(fileName))) {

            JsonArray classInfoJsonArray = reader.readArray();
            classInfoJsonArray.forEach(clasInfoValue -> {
                JsonObject classInfoJsonObject = clasInfoValue.asJsonObject();

                String name = classInfoJsonObject.getString("name");

                if (name.equalsIgnoreCase(className)) {
                    JsonArray studentJsonArray = classInfoJsonObject.getJsonArray("students");

                    studentJsonArray.forEach(studentValue -> {

                        JsonObject studentJsonObject = studentValue.asJsonObject();
                        String studentName = studentJsonObject.getString("name");
                        int age = studentJsonObject.getInt("age");
                        double gpa = studentJsonObject.getJsonNumber("gpa").doubleValue();

                        JsonObject addressJsonObject = studentJsonObject.getJsonObject("address");
                        Address address = new Address(
                          addressJsonObject.getString("street"),
                          addressJsonObject.getString("city"),
                          addressJsonObject.getString("state"),
                          addressJsonObject.getString("zip")
                        );

                        Student student = new Student(
                          name, age, gpa, address
                        );
                        res.add(student);
                    });
                }
            });
        } catch (Exception e) {
            e.printStackTrace();
        }
        return res;
    }

    //viet file co dieu kien
    public static void writeStudentByClassNameToJson(List<Student> students, String fileName) {


        Map<String, Object> config = new HashMap<>();
        config.put(JsonGenerator.PRETTY_PRINTING, true);
        JsonWriterFactory jsonWriterFactory = Json.createWriterFactory(config);

        try (JsonWriter writer = jsonWriterFactory.createWriter(new FileWriter(fileName))) {

            JsonArrayBuilder studentJsonArray = Json.createArrayBuilder();

            students.forEach(student -> {
                JsonObjectBuilder studentJsonObject = Json
                        .createObjectBuilder()
                        .add("name", student.getName())
                        .add("age", student.getAge())
                        .add("gpa", student.getGpa());

                Address address = student.getAddress();
                JsonObjectBuilder addressJsonObject = Json
                        .createObjectBuilder()
                        .add("street", address.getStreet())
                        .add("city", address.getCity())
                        .add("state", address.getCity())
                        .add("state", address.getState())
                        .add("zip", address.getZip());
                studentJsonObject.add("address", addressJsonObject);
                studentJsonArray.add(studentJsonObject);
            });

            writer.write(studentJsonArray.build());

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
