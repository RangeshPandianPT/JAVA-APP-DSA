import java.sql.*;
public class AgriHarv{




    public static void main(String[] args) throws SQLException {
//        Class.forName("com.mysql.cj.jdbc.Driver");

        String url= "jdbc:mysql://localhost:3306/jdbcforAgriHarv";
        String username = "root";
        String password = "Rangesh@07";

        Connection con = DriverManager.getConnection(url,username,password);
        Statement st = con.createStatement();

        String sql = "Select * from student where rno = 2";

        System.out.println("Connection Established");
    }


}