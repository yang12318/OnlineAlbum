import util.DatabaseUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class Test {
    public static void main(String[] args) {
        Connection connection = DatabaseUtil.getConnection();
        PreparedStatement pst = null;
        String sql = "insert into userInfo(username) values('abc')";
        try {
            pst = connection.prepareStatement(sql);
            //connection.commit();
            pst.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DatabaseUtil.close(null, pst, connection);
        }

    }
}
