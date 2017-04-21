package kr.ac.jejunu;

import java.sql.*;

public class ProductDao {
    private JdbcContext jdbcContext;

    public void setJdbcContext(JdbcContext jdbcContext) {
        this.jdbcContext = jdbcContext;
    }

    public Product get(Long id) throws ClassNotFoundException, SQLException {
        String sql = "select * from product where id = ?";
        Object[] params = new Object[]{id};
        PrepareStatementStrategy prepareStatementStrategy = jdbcContext.makePrepareStatement(sql, params);
        return jdbcContext.selectQuery(prepareStatementStrategy);
    }

    public void add(Product product) throws ClassNotFoundException, SQLException {
        String sql = "insert into product(id, title, price) values (?, ?, ?)";
        Object[] params = new Object[]{product.getId(), product.getTitle(), product.getPrice()};
        PrepareStatementStrategy prepareStatementStrategy = jdbcContext.makePrepareStatement(sql, params);
        jdbcContext.updateQuery(prepareStatementStrategy);
    }

    public void update(Product product) throws SQLException {
        String sql = "update product set title=?, price=? where id =?";
        Object[] params = new Object[]{product.getTitle(), product.getPrice(), product.getId()};
        PrepareStatementStrategy prepareStatementStrategy = jdbcContext.makePrepareStatement(sql, params);
        jdbcContext.updateQuery(prepareStatementStrategy);

    }

    public void delete(Long id) throws SQLException {
        String sql = "delete from product where id = ?";
        Object[] params = new Object[]{id};
        PrepareStatementStrategy prepareStatementStrategy = jdbcContext.makePrepareStatement(sql, params);
        jdbcContext.updateQuery(prepareStatementStrategy);
    }
}
