package kr.ac.jejunu;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 * Created by super on 2017-04-21.
 */
public class AddPrepareStatementStrategy implements PrepareStatementStrategy{

    private final Long id;
    private final String title;
    private final Integer price;

    public AddPrepareStatementStrategy(Product product){
        id = product.getId();
        title = product.getTitle();
        price = product.getPrice();
    }

    @Override
    public PreparedStatement getPrepareStatement(Connection connection) throws SQLException {
        PreparedStatement preparedStatement = connection.prepareStatement("insert into product(id, title, price) values (?, ?, ?)");
        preparedStatement.setLong(1, id);
        preparedStatement.setString(2, title);
        preparedStatement.setInt(3, price);
        return preparedStatement;
    }
}
