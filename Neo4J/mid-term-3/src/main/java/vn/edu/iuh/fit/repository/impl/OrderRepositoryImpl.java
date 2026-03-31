package vn.edu.iuh.fit.repository.impl;

import org.neo4j.driver.Result;
import org.neo4j.driver.Session;
import vn.edu.iuh.fit.db.ConnectDB;
import vn.edu.iuh.fit.repository.OrderRepository;

import java.util.Map;
import java.util.Objects;

public class OrderRepositoryImpl implements OrderRepository {
    @Override
    public double calculateTotalOrder(String orderID) {
        String cypher = """
                MATCH (o:Order) - [r:ORDERS] -> (p:Product)
                WHERE o.order_id = $orderID
                RETURN coalesce(sum(r.quantity * r.unit_price * (1 - r.discount)), 0) as totalAmount
                """;

        Map<String, Object> params = Map.of(
                "orderID", orderID
        );

        try (Session session = ConnectDB.getSession()) {
            return session.executeRead(tx -> {
                Result result = tx.run(cypher, params);
                return result.single().get("totalAmount").asDouble();
            });
        }
    }
}
