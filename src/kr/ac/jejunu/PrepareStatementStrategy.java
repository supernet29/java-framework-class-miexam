package kr.ac.jejunu;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 * Created by super on 2017-04-21.
 */
public interface PrepareStatementStrategy {
    PreparedStatement getPrepareStatement(Connection connection) throws SQLException;
}
