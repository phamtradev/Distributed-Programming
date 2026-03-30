package vn.edu.iuh.fit.repository.impl;

import org.neo4j.driver.Result;
import org.neo4j.driver.Session;
import org.neo4j.driver.summary.ResultSummary;
import org.neo4j.driver.types.Node;
import vn.edu.iuh.fit.db.ConnectDB;
import vn.edu.iuh.fit.model.Doctor;
import vn.edu.iuh.fit.repository.DoctorRepository;

import java.util.Map;
import java.util.stream.Collectors;

public class DoctorRepositoryImpl implements DoctorRepository {

    @Override
    public Doctor findDoctorById(String doctorId) {
        String cypher = """
                MATCH (d:Doctor)
                WHERE d.doctor_id = $doctorId
                RETURN d
                """;
        Map<String, Object> params = Map.of("doctorId", doctorId);

        try (Session session = ConnectDB.getSession()) {
            return session.executeRead(tx -> {
                Result result = tx.run(cypher, params);
                if (result.hasNext()) {
                    Node node = result.next().get("d").asNode();
                    return Doctor.builder()
                            .speciality(node.get("speciality").asString())
                            .doctorId(node.get("doctor_id").asString())
                            .phone(node.get("phone").asString())
                            .name(node.get("name").asString())
                            .departmentId(node.get("dept_id").asString())
                            .build();
                }
                return null;
            });
        }
    }

    @Override
    public Map<String, Long> getNoOfDoctorBySpeciality(String departmentName) {
        String cypher = """
                MATCH (d:Doctor)-[:BELONG_TO]->(dp:Department)
                WHERE dp.name = $departmentName
                RETURN d.speciality as speciality, count(d) as totalDoctor
                """;
        Map<String, Object> params = Map.of("departmentName", departmentName);

        try (Session session = ConnectDB.getSession()) {
            return session.executeRead(tx -> {
                Result result = tx.run(cypher, params);
                return result.stream()
                        .collect(Collectors.toMap(
                                r -> r.get("speciality").asString(),
                                r -> r.get("totalDoctor").asLong()
                        ));
            });
        }
    }

    @Override
    public boolean addDoctor(Doctor doctor) {
        String cypher = """
                CREATE (d:Doctor)
                SET d.doctor_id = $doctorId,
                    d.name = $name,
                    d.phone = $phone,
                    d.dept_id = $departmentId,
                    d.speciality = $speciality
                """;

        Map<String, Object> params = Map.of(
                "doctorId", doctor.getDoctorId(),
                "name", doctor.getName(),
                "phone", doctor.getPhone(),
                "departmentId", doctor.getDepartmentId(),
                "speciality", doctor.getSpeciality()
        );

        try (Session session = ConnectDB.getSession()) {
            return session.executeWrite(tx -> {
                ResultSummary summary = tx.run(cypher, params).consume();
                return summary.counters().nodesCreated() > 0;
            });
        }
    }
}