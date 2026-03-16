package vn.edu.iuh.fit.main.classes;

import vn.edu.iuh.fit.model.classes.ClassInfo;
import vn.edu.iuh.fit.utils.classes.StreamAPI.JsonUtils;

import java.util.List;

public class StreamAPIMain {
    public static void main(String[] args) {
        List<ClassInfo> res = JsonUtils.fromJson("Json Process/json/classes/classes.json");
        res.forEach(System.out::println);

        JsonUtils.toJson(res, "Json Process/json/classes/classes3.json");
    }
}
