package conexion;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConexionBD {

    private static ConexionBD instancia;
    private Connection conexion;

    private final String url = "jdbc:mysql://localhost:3306/veterinariaABC?useSSL=false&serverTimezone=UTC";
    private final String usuario = "root";
    private final String password = "1234";

    public ConexionBD() {
        try {
            conexion = DriverManager.getConnection(url, usuario, password);
        } catch (SQLException e) {
            System.out.println("Error en la conexión: " + e.getMessage());
        }
    }

    public static ConexionBD getInstancia() {
        if (instancia == null) {
            instancia = new ConexionBD();
        }
        return instancia;
    }

    public Connection getConexion() {
        return conexion;
    }

	public void desconectar() {
		// TODO Auto-generated method stub
		
	}
}
