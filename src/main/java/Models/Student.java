
package Models;

import Utils.DATABASE;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

public class Student {
    private int regNo;
    private String fname;
    private String lname;
    private Date dateOfBirth;
    private String gender;
    private String address;
    private String email;
    private int mobileNo;
    private int homePhone;
    private String parentName;
    private String parentNic;
    private int parentMobile;
    
    Connection con;
    PreparedStatement pst;
    ResultSet rs;
    Student student;

    public Student() {
    }

    
    //This construnctor for Inserting Data
    public Student(String fname, String lname, Date dateOfBirth, String gender, String address, String email, int mobileNo, int homePhone, String parentName, String parentNic, int parentMobile) {
        this.fname = fname;
        this.lname = lname;
        this.dateOfBirth = dateOfBirth;
        this.gender = gender;
        this.address = address;
        this.email = email;
        this.mobileNo = mobileNo;
        this.homePhone = homePhone;
        this.parentName = parentName;
        this.parentNic = parentNic;
        this.parentMobile = parentMobile;
    }

    public Student(int regNo, String fname, String lname, Date dateOfBirth, String gender, String address, String email, int mobileNo, int homePhone, String parentName, String parentNic, int parentMobile) {
        this.regNo = regNo;
        this.fname = fname;
        this.lname = lname;
        this.dateOfBirth = dateOfBirth;
        this.gender = gender;
        this.address = address;
        this.email = email;
        this.mobileNo = mobileNo;
        this.homePhone = homePhone;
        this.parentName = parentName;
        this.parentNic = parentNic;
        this.parentMobile = parentMobile;
    }

    public int getRegNo() {
        return regNo;
    }

    public void setRegNo(int regNo) {
        this.regNo = regNo;
    }

    public String getFname() {
        return fname;
    }

    public void setFname(String fname) {
        this.fname = fname;
    }

    public String getLname() {
        return lname;
    }

    public void setLname(String lname) {
        this.lname = lname;
    }

    public Date getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(Date dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }
    
    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getMobileNo() {
        return mobileNo;
    }

    public void setMobileNo(int mobileNo) {
        this.mobileNo = mobileNo;
    }

    public int getHomePhone() {
        return homePhone;
    }

    public void setHomePhone(int homePhone) {
        this.homePhone = homePhone;
    }

    public String getParentName() {
        return parentName;
    }

    public void setParentName(String parentName) {
        this.parentName = parentName;
    }

    public String getParentNic() {
        return parentNic;
    }

    public void setParentNic(String parentNic) {
        this.parentNic = parentNic;
    }

    public int getParentMobile() {
        return parentMobile;
    }

    public void setParentMobile(int parentMobile) {
        this.parentMobile = parentMobile;
    }
    
    
    //This method convert java.util.Date to java.sql.Date
    public java.sql.Date utilToSql(Date date){
        return new java.sql.Date(date.getTime());
    }
    
    public void insertStudent(Student student){
        String query = "INSERT INTO `registration`(`firstName`, `lastName`, `dateOfBirth`, `gender`, `address`, `email`, `mobilePhone`, `homePhone`, `parentName`, `nic`, `contactNo`) VALUES (?,?,?,?,?,?,?,?,?,?,?)";
        
        try {
            con = DATABASE.getConnection();
            pst = con.prepareStatement(query);
            
            pst.setString(1, student.getFname());
            pst.setString(2, student.getLname());
            pst.setDate(3, utilToSql(student.getDateOfBirth()));
            pst.setString(4, student.getGender());
            pst.setString(5, student.getAddress());
            pst.setString(6, student.getEmail());
            pst.setInt(7, student.getMobileNo());
            pst.setInt(8, student.getHomePhone());
            pst.setString(9, student.getParentName());
            pst.setString(10, student.getParentNic());
            pst.setInt(11, student.getParentMobile());
            
            
            if(pst.executeUpdate() != 0)
            {
                JOptionPane.showMessageDialog(null, "Student Registered" , "Registration", 1);
            } else 
            {
                JOptionPane.showMessageDialog(null, "Student Not Registered" , "Ragistration", 3);
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(Student.class.getName()).log(Level.SEVERE, null, ex);
        }
      
    }
    
    
    
    public Student getStudent(int id){
        String query = "SELECT * FROM `registration` WHERE `regNo` = ?";
        
        try {
            con = DATABASE.getConnection();
            pst = con.prepareStatement(query);
            pst.setInt(1, id);
            rs = pst.executeQuery();
            
            if(rs.next()){
                student = new Student(rs.getString("firstName"),
                                       rs.getString("lastName"),
                                        rs.getDate("dateOfBirth"),
                                         rs.getString("gender"),
                                        rs.getString("address"),
                                        rs.getString("email"),
                                        rs.getInt("mobilePhone"),
                                        rs.getInt("homePhone"),
                                        rs.getString("parentName"),
                                         rs.getString("nic"),
                                         rs.getInt("contactNo"));
            }
        } catch (SQLException ex) {
            Logger.getLogger(Student.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return student;
    }
    
    
    public void updateStudent(Student student){
        String query = "UPDATE `registration` SET `firstName`=?,`lastName`=?,`dateOfBirth`=?,`gender`=?,`address`=?,`email`=?,`mobilePhone`=?,`homePhone`=?,`parentName`=?,`nic`=?,`contactNo`=? WHERE `regNo` = ?";
        
        try {
            con = DATABASE.getConnection();
            pst = con.prepareStatement(query);
            
            pst.setString(1, student.getFname());
            pst.setString(2, student.getLname());
            pst.setDate(3, utilToSql(student.getDateOfBirth()));
            pst.setString(4, student.getGender());
            pst.setString(5, student.getAddress());
            pst.setString(6, student.getEmail());
            pst.setInt(7, student.getMobileNo());
            pst.setInt(8, student.getHomePhone());
            pst.setString(9, student.getParentName());
            pst.setString(10, student.getFname());
            pst.setInt(11, student.getParentMobile());
            pst.setInt(12, student.getRegNo());
            
            
            if(pst.executeUpdate() != 0)
            {
                JOptionPane.showMessageDialog(null, "Student Updated" , "Registration", 1);
            } else 
            {
                JOptionPane.showMessageDialog(null, "Student Not Update" , "Registration", 3);
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(Student.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    
    public void deleteStudent(int regNo){
        String query = "DELETE FROM `registration` WHERE `regNo`=?";
        
        try {
            con = DATABASE.getConnection();
            pst = con.prepareStatement(query);
            
            pst.setInt(1, regNo);
            
            
            if(pst.executeUpdate() != 0)
            {
                JOptionPane.showMessageDialog(null, "Student Deleted" , "Delete Student", 1);
            } else 
            {
                JOptionPane.showMessageDialog(null, "Student Not Deleted" , "Delete Student", 3);
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(Student.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    
}
