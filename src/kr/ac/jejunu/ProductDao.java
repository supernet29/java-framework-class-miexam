package kr.ac.jejunu;

import java.sql.*;

public class ProductDao {
    public Product get(Long id) throws ClassNotFoundException, SQLException {
        Connection connection = getConnection();
        PreparedStatement preparedStatement = connection.prepareStatement("select * from product where id = ?");
        preparedStatement.setLong(1, id);
        ResultSet resultSet = preparedStatement.executeQuery();
        resultSet.next();
        Product product = new Product();
        product.setId(resultSet.getLong("id"));
        product.setTitle(resultSet.getString("title"));
        product.setPrice(resultSet.getInt("price"));
        resultSet.close();
        preparedStatement.close();
        connection.close();
        return product;
    }

    public void add(Product product) throws ClassNotFoundException, SQLException {
        Connection connection = getConnection();
        Long id = product.getId();
        String title = product.getTitle();
        Integer price = product.getPrice();

        PreparedStatement preparedStatement = connection.prepareStatement("insert into product(id, title, price) values (?, ?, ?)");
        preparedStatement.setLong(1,id);
        preparedStatement.setString(2,title);
        preparedStatement.setLong(3,price);
        preparedStatement.executeUpdate();

        preparedStatement.close();
        connection.close();
    }

    private Connection getConnection() throws ClassNotFoundException, SQLException {
        Class.forName("com.mysql.jdbc.Driver");
        return DriverManager.getConnection("jdbc:mysql://117.17.102.106/jeju", "root", "1234");
    }
}
