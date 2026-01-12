package vn.edu.iuh.fit.util;

import jakarta.json.Json;
import jakarta.json.JsonArray;
import jakarta.json.JsonObject;
import jakarta.json.JsonReader;
import vn.edu.iuh.fit.model.Address;
import vn.edu.iuh.fit.model.ClassInfo;
import vn.edu.iuh.fit.model.Student;

import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;

public class JsonUtils {
    public static List<ClassInfo> fromJson(String fileName) {
        List<ClassInfo> res = new ArrayList<>();

        try (JsonReader jsonReader = Json.createReader(new FileReader(fileName))) {
            JsonArray classInfoJsonArray = jsonReader.readArray();

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
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        return res;
    }
}
