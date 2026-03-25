package vn.edu.iuh.fit.repository;

import org.neo4j.driver.Record;
import org.neo4j.driver.Session;
import org.neo4j.driver.types.Node;
import vn.edu.iuh.fit.db.ConnectDB;
import vn.edu.iuh.fit.model.Product;
import vn.edu.iuh.fit.model.Supplier;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class ProductRepository {

    public boolean createSupplierCompanyNameRangeIndex() {
        String cypher = """
                        CREATE RANGE INDEX supplier_company_name_range_index IF NOT EXISTS
                        FOR (s:Supplier)
                        ON (s.companyName)
                        """;
        try (Session session = ConnectDB.getSession()) {
            return session.executeWrite(tx -> {
                tx.run(cypher);
                return true;
            });

        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public List<Product> listProductsBySupplier(String companyName, int page, int size) {
        String cypher = """
            MATCH (s:Supplier)-[:SUPPLIES]->(p:Product)
            WHERE toLower(s.company_name) = toLower($companyName)
            RETURN p, s
            ORDER BY p.product_name
            SKIP $skip
            LIMIT $limit
            """;

        int skip = (page - 1) * size;

        try (Session session = ConnectDB.getSession()) {
            List<Record> records = session.executeRead(tx ->
                    tx.run(cypher, Map.of(
                            "companyName", companyName,
                            "skip", skip,
                            "limit", size
                    )).list()
            );

            List<Product> list = new ArrayList<>();
            for (Record r : records) {
                Node pNode = r.get("p").asNode();
                Node sNode = r.get("s").asNode();

                Supplier supplier = Supplier.builder()
                        .supplierId(sNode.get("supplier_id").asString())
                        .companyName(sNode.get("company_name").asString())
                        .contactName(sNode.get("contact_name").asString())
                        .country(sNode.get("country").asString())
                        .build();

                Product product = Product.builder()
                        .productId(pNode.get("product_id").asString())
                        .productName(pNode.get("product_name").asString())
                        .unit(pNode.get("unit").asString())
                        .unitPrice(pNode.get("unit_price").asDouble())
                        .unitsInStock(pNode.get("units_in_stock").asInt())
                        .supplier(supplier)
                        .build();

                list.add(product);
            }
            return list;
        } catch (Exception e) {
            e.printStackTrace();
            return new ArrayList<>();
        }
    }
}
