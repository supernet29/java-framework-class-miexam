package kr.ac.jejunu;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 * Created by super on 2017-04-21.
 */
public class UpdatePrepareStatementStrategy implements PrepareStatementStrategy{
    private Long id;
    private String title;
    private Integer price;

    public UpdatePrepareStatementStrategy(Product product) {
        id = product.getId();
        title = product.getTitle();
        price = product.getPrice();
    }

    @Override
    public PreparedStatement getPrepareStatement(Connection connection) throws SQLException {
        PreparedStatement preparedStatement = connection.prepareStatement("update product set title=?, price=? where id =?");
        preparedStatement.setString(1, title);
        preparedStatement.setInt(2, price);
        preparedStatement.setLong(3, id);
        return preparedStatement;
    }
}
