package kr.ac.jejunu;

import javax.sql.DataSource;
import java.sql.*;

public class ProductDao {
    private JdbcContext jdbcContext;

    public void setJdbcContext(JdbcContext jdbcContext) {
        this.jdbcContext = jdbcContext;
    }

    public Product get(Long id) throws ClassNotFoundException, SQLException {
        PrepareStatementStrategy prepareStatementStrategy = new GetPrepareStatementStrategy(id);
        return jdbcContext.selectQuery(prepareStatementStrategy);
    }

    public void add(Product product) throws ClassNotFoundException, SQLException {
        PrepareStatementStrategy prepareStatementStrategy = new AddPrepareStatementStrategy(product);
        jdbcContext.updateQuery(prepareStatementStrategy);
    }


    public void update(Product product) throws SQLException {
        PrepareStatementStrategy prepareStatementStrategy = new UpdatePrepareStatementStrategy(product);
        jdbcContext.updateQuery(prepareStatementStrategy);

    }

    public void delete(Long id) throws SQLException {
        PrepareStatementStrategy prepareStatementStrategy = new DeletePrepareStatementStrategy(id);
        jdbcContext.updateQuery(prepareStatementStrategy);
    }
}
