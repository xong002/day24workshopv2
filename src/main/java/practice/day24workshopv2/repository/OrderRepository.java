package practice.day24workshopv2.repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.stereotype.Repository;

import practice.day24workshopv2.model.Order;

@Repository
public class OrderRepository {
    
    private static final String INSERT_ORDER_SQL = "insert into orders (order_date, customer_name, ship_address, notes, tax) values (?,?,?,?,?)";

    @Autowired
    private JdbcTemplate jdbcTemplate;

    public Integer insertOrder(Order order){
        GeneratedKeyHolder generatedKey = new GeneratedKeyHolder();
        PreparedStatementCreator psc = new PreparedStatementCreator() {

            @Override
            public PreparedStatement createPreparedStatement(Connection con) throws SQLException {
                PreparedStatement ps = con.prepareStatement(INSERT_ORDER_SQL, new String[] {"order_id"});
                ps.setDate(1, order.getOrderDate());
                ps.setString(2, order.getCustomerName());
                ps.setString(3, order.getShipAddress());
                ps.setString(4, order.getNotes());
                ps.setFloat(5, order.getTax());
                return ps;
            }
            
        };

        jdbcTemplate.update(psc, generatedKey);
        return generatedKey.getKey().intValue();
    }

    
}
