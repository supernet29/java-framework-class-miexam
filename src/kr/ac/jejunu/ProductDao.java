package kr.ac.jejunu;

import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowCallbackHandler;

import java.sql.*;

public class ProductDao {
    private JdbcTemplate jdbcTemplate;

    public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public Product get(Long id) throws ClassNotFoundException, SQLException {
        String sql = "select * from product where id = ?";
        Object[] params = new Object[]{id};
        Product result = null;
        try {
            result = jdbcTemplate.query(sql, params, resultSet -> {
                Product product = null;
                if(resultSet.next()) {
                    product = new Product();
                    product.setId(resultSet.getLong("id"));
                    product.setPrice(resultSet.getInt("price"));
                    product.setTitle(resultSet.getString("title"));
                }
                return product;
            });
        } catch (DataAccessException e) {
            e.printStackTrace();
        }
        return result;
    }

    public void add(Product product) throws ClassNotFoundException, SQLException {
        String sql = "insert into product(id, title, price) values (?, ?, ?)";
        Object[] params = new Object[]{product.getId(), product.getTitle(), product.getPrice()};
        jdbcTemplate.update(sql, params);
    }

    public void update(Product product) throws SQLException {
        String sql = "update product set title=?, price=? where id =?";
        Object[] params = new Object[]{product.getTitle(), product.getPrice(), product.getId()};
        jdbcTemplate.update(sql, params);

    }

    public void delete(Long id) throws SQLException {
        String sql = "delete from product where id = ?";
        Object[] params = new Object[]{id};
        jdbcTemplate.update(sql, params);
    }
}
