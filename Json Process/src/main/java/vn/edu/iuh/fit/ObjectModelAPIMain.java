package vn.edu.iuh.fit;

import vn.edu.iuh.fit.model.ClassInfo;
import vn.edu.iuh.fit.utils.ObjectModelAPI.JsonUtils;

import java.util.List;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class ObjectModelAPIMain {
    public static void main(String[] args) {
        List<ClassInfo> res = JsonUtils.fromJson("Json Process/json/classes.json");
        res.forEach(System.out::println);

        JsonUtils.toJson(res, "Json Process/json/classes2.json");
    }
}