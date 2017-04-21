package kr.ac.jejunu;

import javax.sql.DataSource;
import java.sql.*;

public class ProductDao {
    public void setDataSource(DataSource dataSource) {
        this.dataSource = dataSource;
    }
    private DataSource dataSource;

    public Product get(Long id) throws ClassNotFoundException, SQLException {
        ResultSet resultSet = null;
        PreparedStatement preparedStatement = null;
        Connection connection = null;
        Product product = null;
        try {
            connection = dataSource.getConnection();
            preparedStatement = connection.prepareStatement("select * from product where id = ?");
            preparedStatement.setLong(1, id);
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
        PreparedStatement preparedStatement = null;
        Connection connection = null;
        try {
            connection = dataSource.getConnection();
            Long id = product.getId();
            String title = product.getTitle();
            Integer price = product.getPrice();

            preparedStatement = connection.prepareStatement("insert into product(id, title, price) values (?, ?, ?)");
            preparedStatement.setLong(1, id);
            preparedStatement.setString(2, title);
            preparedStatement.setInt(3, price);
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
        PreparedStatement preparedStatement = null;
        Connection connection = null;
        try {
            connection = dataSource.getConnection();
            preparedStatement = connection.prepareStatement("delete from product where id = ?");
            preparedStatement.setLong(1,id);
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
        PreparedStatement preparedStatement = null;
        Connection connection = null;
        try {
            connection = dataSource.getConnection();
            Long id = product.getId();
            String title = product.getTitle();
            Integer price = product.getPrice();

            preparedStatement = connection.prepareStatement("update product set title=?, price=? where id =?");
            preparedStatement.setString(1, title);
            preparedStatement.setInt(2, price);
            preparedStatement.setLong(3, id);
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
