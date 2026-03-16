package vn.edu.iuh.fit.model.classes;

import lombok.*;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class ClassInfo {

    private String name;
    private String teacher;
    private int room;
    private String startTime;
    private String endTime;

    private List<Student> students;

}
