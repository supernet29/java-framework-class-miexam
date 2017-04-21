package kr.ac.jejunu;

import javax.sql.DataSource;
import java.sql.*;

public class ProductDao {
    public void setDataSource(DataSource dataSource) {
        this.dataSource = dataSource;
    }
    private DataSource dataSource;

    public Product get(Long id) throws ClassNotFoundException, SQLException {
        PrepareStatementStrategy prepareStatementStrategy = new GetPrepareStatementStrategy(id);
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

    public void add(Product product) throws ClassNotFoundException, SQLException {
        PrepareStatementStrategy prepareStatementStrategy = new AddPrepareStatementStrategy(product);
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


    public void update(Product product) throws SQLException {
        PrepareStatementStrategy prepareStatementStrategy = new UpdatePrepareStatementStrategy(product);
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

    public void delete(Long id) throws SQLException {
        PrepareStatementStrategy prepareStatementStrategy = new DeletePrepareStatementStrategy(id);
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
