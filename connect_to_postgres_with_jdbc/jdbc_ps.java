import java.sql.*;

public class jdbc_ps{
    public static void main(String[] args) throws Exception {
        try {
            System.out.println("DBTEST01 START");
            new jdbc_ps().dbAccess();
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        finally {
            System.out.println("DBTEST01 END");
        }
    }

    private void dbAccess() throws Exception {
        Connection conn = null;
        PreparedStatement stmt = null;
        PreparedStatement stmt2 = null;
        PreparedStatement stmt3 = null;
        Statement stmt4 = null;
        ResultSet rs = null;
        ResultSet rs2 = null;
        try {
            Class.forName("org.postgresql.Driver");
            conn = DriverManager.getConnection("jdbc:postgresql://localhost/postgres");
            stmt = conn.prepareStatement("SELECT * FROM test_table;");
            stmt2 = conn.prepareStatement("SELECT * FROM test_table;");
            stmt3 = conn.prepareStatement("select * from pg_prepared_statements;");
            stmt4 = conn.createStatement();

            for (int i=1;i<6;i++){
                rs = stmt.executeQuery();
            }

            rs = stmt3.executeQuery();

            while (rs.next()) {
                System.out.println(rs.getString("name"));
                System.out.println(rs.getString("statement"));
            }

            rs2 = stmt4.executeQuery("EXECUTE \"S_1\"");
			while (rs2.next()) {
	            System.out.println(rs2.getString("hoge"));
			}

        }catch(SQLException e){
                while(e != null) {
                  System.err.println(e.getMessage());
                  System.err.println(e.getSQLState());
                  System.err.println(e.getErrorCode());

                  e = e.getNextException();
                }
        }

        finally {
            if (rs != null) rs.close();
            if (rs2 != null) rs.close();
            if (stmt != null) stmt.close();
            if (stmt2 != null) stmt2.close();
            if (stmt3 != null) stmt3.close();
            if (stmt4 != null) stmt3.close();
            if (conn != null) conn.close();
        }
    }
}

