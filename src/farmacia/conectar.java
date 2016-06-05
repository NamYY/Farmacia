/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package farmacia;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
/**
 *
 * @author NaMYY
 */
public class conectar {
    public Connection conect(){
        Connection cnn;
        try{
            Class.forName("com.mysql.jdbc.Driver");
            String coneccion = "jdbc:mysql://localhost:3306/farmacia";
            String usuario = "root";
            String cont = "JuviaKawaii";
            cnn = DriverManager.getConnection(coneccion, usuario, cont);
            return cnn;
        }catch( ClassNotFoundException | SQLException ex )
        {
            return null;
        }
    }
}
