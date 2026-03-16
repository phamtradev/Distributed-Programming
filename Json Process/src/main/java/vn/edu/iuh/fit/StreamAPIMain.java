package vn.edu.iuh.fit;

import vn.edu.iuh.fit.model.ClassInfo;
import vn.edu.iuh.fit.utils.StreamAPI.JsonUtils;

import java.util.List;

public class StreamAPIMain {
    public static void main(String[] args) {
        List<ClassInfo> res = JsonUtils.fromJson("Json Process/json/classes.json");
        res.forEach(System.out::println);

        JsonUtils.toJson(res, "Json Process/json/classes3.json");
    }
}
