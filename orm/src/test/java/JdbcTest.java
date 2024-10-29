import org.junit.Test;

import java.sql.*;

public class JdbcTest {

    @Test
    public void jdbcTest() {
        Connection connection = null;
        PreparedStatement pstmt = null;
        try {
            Class.forName("com.mysql.jdbc.Driver");
            connection = DriverManager.getConnection("jdbc:mysql://server.panke.cc:33060/test", "root", "Jycoder@123");
            String sql = "select * from user";
            pstmt = connection.prepareStatement(sql);
            pstmt.execute();
            ResultSet rs = pstmt.getResultSet();
            while (rs.next()) {
                System.out.println(rs.getString(1));
            }
        } catch (Exception e) {
            System.err.println(e);
        } finally {
            try {
                if (connection != null) {
                    connection.close();
                }
                if (pstmt != null) {
                    pstmt.close();
                }
            } catch (Exception e) {
                System.err.println(e);
            }
        }
    }
}
