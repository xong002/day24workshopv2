package practice.day24workshopv2.repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Repository;

import practice.day24workshopv2.model.Order;

@Repository
public class OrderRepository {
    
    private static final String INSERT_ORDER_SQL = "insert into orders (order_date, customer_name, ship_address, notes, tax) values (?,?,?,?,?)";
    private static final String FIND_ORDER_SQL = "select * from orders where order_id = ?";

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

    public Order findOrderById(Integer id){
        // SqlRowSet result = jdbcTemplate.queryForRowSet(FIND_ORDER_SQL, id);
        // List<Order> orderList = new LinkedList<>();
        // while(result.next()){
        //     Order o = new Order();
        //     o.setOrderId(result.getInt("order_id"));
        //     o.setOrderDate(result.getDate("order_date"));
        //     o.setCustomerName(result.getString("customer_name"));
        //     o.setShipAddress(result.getString("ship_address"));
        //     o.setNotes(result.getString("notes"));
        //     o.setTax(result.getFloat("tax"));
        //     orderList.add(o);
        // }
        // return orderList.get(0);

        List<Order> result = jdbcTemplate.query(FIND_ORDER_SQL, BeanPropertyRowMapper.newInstance(Order.class), id);
        return result.get(0);
    }
    
}
