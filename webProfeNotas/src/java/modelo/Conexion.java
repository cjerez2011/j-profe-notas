
package modelo;

import java.sql.*;
import com.mysql.jdbc.jdbc2.optional.MysqlDataSource;


public class Conexion {
    public Connection conexion;
    public Statement sentencia;
    public ResultSet tablaResultado;

    public Conexion(String server, String bd, String user, String pass) 
            throws SQLException{
        MysqlDataSource mysql= new MysqlDataSource();
        
        mysql.setServerName(server);
        mysql.setDatabaseName(bd);
        mysql.setUser(user);
        mysql.setPassword(pass);
        
        conexion = mysql.getConnection();
        
                 
        
    }
    
    
}
