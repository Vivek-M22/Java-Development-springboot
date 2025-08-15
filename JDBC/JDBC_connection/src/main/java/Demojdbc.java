
//1. import packages
import java.sql.*;


public class Demojdbc {
    public static void main(String[] args) throws SQLException {

        //2. load driver and register driver
        // (optional because it is already avaliable in after java6 and jdbc4)
        try {
            //Class.forName("com.mysql.cj.jdbc.Driver");
            Class.forName("org.postgresql.Driver");

        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }


        //3. create connection
        String url = "jdbc:postgresql://localhost:5432/mydatabaseP"; // your DB name
        String user = "postgres"; // your username
        String password = "147258"; // your password
        Connection conn;
        try {
            conn = DriverManager.getConnection(url, user, password);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        System.out.println("Connected successfully!");

        //4. create statement and 5.execute statement
        /*CRUD OPERATION*/
        //Statement stmt = conn.createStatement();
        //String query = "INSERT into users (name, email)  values ( 'vivek', 'vivek@ex.com')";
        //String query = "UPDATE users SET name='max brown' where id = 3";
        //String query = "DELETE FROM users where id >= 3";

        //boolean rs = stmt.execute(query);
        //System.out.println(rs);
        //stmt.execute(query);

        //5. process and result
        /* for particular rows or the set of rows

        String query = "SELECT * FROM users ";
        ResultSet rs = stmt.executeQuery(query);

        //return true/false for the query execution
        //System.out.println(rs.next());

        rs.next();
        String val = rs.getString("name");
        System.out.println(val);

        //while shifts the pointer with help of rs.next()
        while(rs.next()){
            System.out.print(rs.getInt("id") + "-");
            System.out.print(rs.getString("name") + "-");
            System.out.println(rs.getString("email"));
        }
         */


        int id = 3;
        String name = "max charlie";
        String email = "maxcharlie@example.com";
        String sql = "INSERT INTO users (id, name, email) VALUES (?, ?, ?)";


        PreparedStatement pr = conn.prepareStatement(sql);
        pr.setInt(1, id);
        pr.setString(2, name);
        pr.setString(3, email);
        pr.execute();

        conn.close();
        System.out.println("closed the connection!");
    }
}


/*
    1. import packages
    2. load driver and register driver
    3. create connection
    4. create statement
    5. execute statement
    6. process and result then close
 */