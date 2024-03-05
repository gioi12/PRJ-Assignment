/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dal;
import java.sql.*;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
/**
 *
 * @author ACER
 */
public abstract class DBcontext<T>{
    protected Connection connection;
      public DBcontext() {
        try {
            String url = "jdbc:sqlserver://LAPTOP-C0FTF5UA\\SQLEXPRESS:1433;databaseName=AssignmentPRJ;encrypt=true;trustServerCertificate=true;";
            String user = "hyamyyou1";
            String password = "123";
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            connection = DriverManager.getConnection(url, user, password);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(DBcontext.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(DBcontext.class.getName()).log(Level.SEVERE, null, ex);
        }
      }
    public abstract ArrayList<T> list();
    public abstract void insert(T entity);
    public abstract void update(T entity);
    public abstract void delete(T entity);
    
      
}
