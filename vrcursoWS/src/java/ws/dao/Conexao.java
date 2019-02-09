package ws.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class Conexao {
    
    
    private static Connection con;
    
    public static Connection getConexao() {
        
        try {
            if (con == null || con.isClosed()) {
                String url = "jdbc:postgresql://localhost/vrcurso";
                String usuario = "postgres";
                String senha = "postgres";

                Class.forName("org.postgresql.Driver");

                con = DriverManager.getConnection(url, usuario, senha);
                
                System.out.println("sucesso");
            }
            
        } catch (Exception e) {
            System.out.println("deu merda");
            e.printStackTrace();
        }

        return con;
    }
    
    public static Statement getStatement() throws SQLException{
        return getConexao().createStatement();
    }
    
}
