package Utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class DATABASE {
    
    public static Connection getConnection(){
        Connection connection = null;
        
        try {
            
            Class.forName("com.mysql.cj.jdbc.Driver");
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/student","root","");
            
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(DATABASE.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(DATABASE.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        
        return connection;
    }
    
}


