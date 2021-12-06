package Utils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class PasswordAuth {
    public static boolean valid = false;
    
    //I made this method static, because static methods can calling without creating instance
    public static boolean authenticate(String username, String password){
        Connection con;
        PreparedStatement pst;
        ResultSet rs;
        
        String query = "SELECT * FROM `logins` WHERE `username` = ? AND `password` = ?";
        
        try {
            con = DATABASE.getConnection();
            pst = con.prepareStatement(query);
            pst.setString(1, username);
            pst.setString(2, password);
            rs = pst.executeQuery();
            
            if(rs.next()){
                valid = true;
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(PasswordAuth.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return valid;
    }
    
}
