package kr.ac.jejunu;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 * Created by super on 2017-04-21.
 */
public class DeletePrepareStatementStrategy implements PrepareStatementStrategy{
    private final Long id;
    public DeletePrepareStatementStrategy(Long id) {
       this.id = id;
    }

    @Override
    public PreparedStatement getPrepareStatement(Connection connection) throws SQLException {
        PreparedStatement preparedStatement = connection.prepareStatement("delete from product where id = ?");
        preparedStatement.setLong(1,id);
        return preparedStatement;
    }
}
