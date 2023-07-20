package practice.day24workshopv2.repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.stereotype.Repository;

import practice.day24workshopv2.model.Details;

@Repository
public class OrderDetailsRepository {
    
    private static final String INSERT_DETAILS_SQL = "insert into order_details (product, unit_price, discount, quantity, order_id) values (?,?,?,?,?)";

    @Autowired
    private JdbcTemplate jdbcTemplate;

    public Integer insertDetails(Details details){
        GeneratedKeyHolder generatedKey = new GeneratedKeyHolder();
        PreparedStatementCreator psc = new PreparedStatementCreator() {

            @Override
            public PreparedStatement createPreparedStatement(Connection con) throws SQLException {
                PreparedStatement ps = con.prepareStatement(INSERT_DETAILS_SQL, new String[]{"id"});
                ps.setString(1, details.getProduct());
                ps.setFloat(2, details.getUnitPrice());
                ps.setFloat(3,details.getDiscount());
                ps.setInt(4, details.getQuantity());
                ps.setInt(5, details.getOrderId());
                return ps;
            }
            
        };
        
        jdbcTemplate.update(psc, generatedKey);
        return generatedKey.getKey().intValue();

    }
}
