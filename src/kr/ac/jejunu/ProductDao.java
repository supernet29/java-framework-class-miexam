package kr.ac.jejunu;

import javax.sql.DataSource;
import java.sql.*;

public class ProductDao {
    private JdbcContext jdbcContext;

    public void setJdbcContext(JdbcContext jdbcContext) {
        this.jdbcContext = jdbcContext;
    }

    public Product get(Long id) throws ClassNotFoundException, SQLException {
        PrepareStatementStrategy prepareStatementStrategy = connection -> {
            PreparedStatement preparedStatement = connection.prepareStatement("select * from product where id = ?");
            preparedStatement.setLong(1, id);
            return preparedStatement;
        };
        return jdbcContext.selectQuery(prepareStatementStrategy);
    }

    public void add(Product product) throws ClassNotFoundException, SQLException {
        PrepareStatementStrategy prepareStatementStrategy = connection -> {
            long id = product.getId();
            String title = product.getTitle();
            int price = product.getPrice();
            PreparedStatement preparedStatement = connection.prepareStatement("insert into product(id, title, price) values (?, ?, ?)");
            preparedStatement.setLong(1, id);
            preparedStatement.setString(2, title);
            preparedStatement.setInt(3, price);
            return preparedStatement;
        };
        jdbcContext.updateQuery(prepareStatementStrategy);
    }


    public void update(Product product) throws SQLException {
        PrepareStatementStrategy prepareStatementStrategy = connection -> {
            long id = product.getId();
            String title = product.getTitle();
            int price = product.getPrice();
            PreparedStatement preparedStatement = connection.prepareStatement("update product set title=?, price=? where id =?");
            preparedStatement.setString(1, title);
            preparedStatement.setInt(2, price);
            preparedStatement.setLong(3, id);
            return preparedStatement;
        };
        jdbcContext.updateQuery(prepareStatementStrategy);

    }

    public void delete(Long id) throws SQLException {
        PrepareStatementStrategy prepareStatementStrategy = connection -> {
            PreparedStatement preparedStatement = connection.prepareStatement("delete from product where id = ?");
            preparedStatement.setLong(1,id);
            return preparedStatement;
        };
        jdbcContext.updateQuery(prepareStatementStrategy);
    }
}
