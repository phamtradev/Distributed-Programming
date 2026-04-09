package vn.edu.iuh.fit.repository.impl;

import org.neo4j.driver.Result;
import org.neo4j.driver.Session;
import org.neo4j.driver.summary.ResultSummary;
import org.neo4j.driver.types.Node;
import vn.edu.iuh.fit.db.ConnectDB;
import vn.edu.iuh.fit.model.Product;
import vn.edu.iuh.fit.model.Supplier;
import vn.edu.iuh.fit.repository.SupplierRepository;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class SupplierRepositoryImpl implements SupplierRepository {

    @Override
    public boolean createRangeIndexOnCompanyName() {
        String cypher = """
                CREATE RANGE INDEX supplier_company_name_idx IF NOT EXISTS
                FOR (s:Supplier)
                ON (s.companyName)
                """;
        try (Session session = ConnectDB.getSession()) {
            return session.executeWrite(tx -> {
                tx.run(cypher).consume();
                return true;
            });
        }
    }

    @Override
    public List<Product> listProductsBySupplier(String companyName, int page, int size) {
        String cypher = """
                MATCH (s:Supplier) - [r:SUPPLIES] -> (p:Product)
                WHERE s.company_name = $companyName
                RETURN p, s
                ORDER BY p.product_name ASC
                SKIP $skip
                LIMIT $limit
                """;

        int skip = (page - 1) * size;
        Map<String, Object> params = Map.of(
                "companyName", companyName,
                "skip", skip,
                "limit", size
        );

        try (Session session = ConnectDB.getSession()) {
            return session.executeRead(tx -> {
                Result result = tx.run(cypher, params);
                return result.stream()
                        .map(r -> {
                            Node pNode = r.get("p").asNode();
                            Node sNode = r.get("s").asNode();

                            Supplier supplier = Supplier
                                    .builder()
                                    .supplierID(sNode.get("supplier_id").asString())
                                    .companyName(sNode.get("company_name").asString())
                                    .contactName(sNode.get("contact_name").asString())
                                    .country(sNode.get("country").asString())
                                    .build();

                            return Product
                                    .builder()
                                    .productID(pNode.get("product_id").asString())
                                    .productName(pNode.get("product_name").asString())
                                    .unit(pNode.get("unit").asString())
                                    .unitPrice(pNode.get("unit_price").isNull() ? 0.0 : pNode.get("unit_price").asDouble())
                                    .unitsInStock(pNode.get("unit_in_stock").isNull() ? 0 : pNode.get("unit_in_stock").asInt())
                                    .supplier(supplier)
                                    .build();
                        }).toList();
            });
        }

    }

    @Override
    public boolean updateSupplier(Supplier supplier) {
        String cypher = """
                MATCH (s:Supplier)
                WHERE s.supplier_id = $supplierID
                SET s.company_name = $companyName,
                s.contact_name = $contactName,
                s.country = $country
                """;
        Map<String, Object> params = Map.of(
                "supplierID", supplier.getSupplierID(),
                "companyName", supplier.getCompanyName(),
                "contactName", supplier.getContactName(),
                "country", supplier.getCountry()
        );

        try (Session session = ConnectDB.getSession()) {
            return session.executeWrite(tx -> {
                ResultSummary resultSummary = tx.run(cypher, params).consume();
                return resultSummary.counters().propertiesSet() > 0;
            });
        }
    }


}
