package vn.edu.iuh.fit.repository;

import org.neo4j.driver.Record;
import org.neo4j.driver.Session;
import vn.edu.iuh.fit.db.ConnectDB;
import vn.edu.iuh.fit.model.Supplier;

import java.util.Map;

public class SupplierRepository {

    public boolean updateSupplier(Supplier supplier) {
        String cypher = """
            MATCH (s:Supplier {supplier_id: $supplierId})
            SET s.company_name = $companyName,
                s.contact_name = $contactName,
                s.country = $country
            RETURN count(s) > 0 AS updated
            """;

        try (Session session = ConnectDB.getSession()) {
            return session.executeWrite(tx -> {
                Record record = tx.run(cypher, Map.of(
                        "supplierId", supplier.getSupplierId(),
                        "companyName", supplier.getCompanyName(),
                        "contactName", supplier.getContactName(),
                        "country", supplier.getCountry()
                )).single();

                return record.get("updated").asBoolean();
            });
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
}
