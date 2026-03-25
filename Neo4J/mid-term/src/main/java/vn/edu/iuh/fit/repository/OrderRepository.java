package vn.edu.iuh.fit.repository;

import org.neo4j.driver.Record;
import org.neo4j.driver.Result;
import org.neo4j.driver.Session;
import vn.edu.iuh.fit.db.ConnectDB;

import java.util.Map;

public class OrderRepository {

    public double calculateTotalOrder(String orderID) {
        String cypher = """
            MATCH (o:Order {order_id: $orderID})-[r:ORDERS]->(:Product)
            RETURN sum(r.quantity * r.unit_price * (1 - r.discount)) AS total
            """;

        try (Session session = ConnectDB.getSession()) {
            return session.executeRead(tx -> {
                Result result = tx.run(cypher, Map.of("orderID", orderID));
                if (!result.hasNext()) {
                    return 0.0;
                }

                Record record = result.next();
                return record.get("total").isNull() ? 0.0 : record.get("total").asDouble();
            });
        } catch (Exception e) {
            e.printStackTrace();
            return 0.0;
        }
    }
}