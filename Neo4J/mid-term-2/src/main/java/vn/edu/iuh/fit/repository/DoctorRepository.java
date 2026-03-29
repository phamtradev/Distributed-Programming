package vn.edu.iuh.fit.repository;

import org.neo4j.driver.Result;
import org.neo4j.driver.Session;
import org.neo4j.driver.types.Node;
import vn.edu.iuh.fit.db.ConnectDB;
import vn.edu.iuh.fit.model.Doctor;

import java.util.Map;
import java.util.Objects;

public class DoctorRepository {

    public Doctor findDoctorById(String doctorId) {
        String cypher =
                """
                        MATCH (d:Doctor)
                        WHERE d.doctor_id = $doctorId
                        RETURN d
                        """;
        Map<String, Object> params = Map.of(
                "doctorId", doctorId
        );

        try (Session session = ConnectDB.getSession()) {
            return session.executeRead(tx -> {
                Result result = tx.run(cypher, params);

                if (result.hasNext()) {
                    Node node = result.next().get("d").asNode();

                    return Doctor
                            .builder()
                            .speciality(node.get("speciality").asString())
                            .doctorId(node.get("doctor_id").asString())
                            .phone(node.get("phone").asString())
                            .name(node.get("name").asString())
                            .departmentId(node.get("dept_id").asString())
                            .build();
                } else {
                    return null;
                }
            });
        }
    }
}
