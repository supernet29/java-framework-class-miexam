package kr.ac.jejunu;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created by super on 2017-04-21.
 */
public class JdbcContext {

    public void setDataSource(DataSource dataSource) {
        this.dataSource = dataSource;
    }
    private DataSource dataSource;

    public Product selectQuery(PrepareStatementStrategy prepareStatementStrategy) throws SQLException {
        ResultSet resultSet = null;
        PreparedStatement preparedStatement = null;
        Connection connection = null;
        Product product = null;
        try {
            connection = dataSource.getConnection();
            preparedStatement = prepareStatementStrategy.getPrepareStatement(connection);
            resultSet = preparedStatement.executeQuery();

            if(resultSet.next()) {
                product = new Product();
                product.setId(resultSet.getLong("id"));
                product.setTitle(resultSet.getString("title"));
                product.setPrice(resultSet.getInt("price"));
            }
        } finally {
            if(resultSet != null)
                try {
                    resultSet.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            if(preparedStatement != null)
                try {
                    preparedStatement.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            if(connection != null)
                try {
                    connection.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
        }
        return product;
    }


    public void updateQuery(PrepareStatementStrategy prepareStatementStrategy) throws SQLException {
        PreparedStatement preparedStatement = null;
        Connection connection = null;
        try {
            connection = dataSource.getConnection();
            preparedStatement = prepareStatementStrategy.getPrepareStatement(connection);
            preparedStatement.executeUpdate();
        } finally {
            if(preparedStatement != null)
                try {
                    preparedStatement.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            if(connection != null)
                try {
                    connection.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
        }
    }
}
