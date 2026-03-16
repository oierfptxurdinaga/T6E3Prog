package DB;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConexionDB {

    // 1. Datos de configuración de la base de datos
    // CAMBIA "nombre_de_tu_bd" por el nombre real de tu base de datos
    private static final String URL = "jdbc:mysql://localhost:3306/fnfs";
    private static final String USER = "root";      // Usuario por defecto en XAMPP/WAMP suele ser 'root'
    private static final String PASSWORD = "";      // En local suele estar vacía, si tienes una ponla aquí

    // Objeto que guardará la conexión física
    private Connection conexion;


    public Connection conectar() {
    
        try {
            if (conexion == null || conexion.isClosed()) {
				
            	conexion = DriverManager.getConnection(URL, USER, PASSWORD);
            	//System.out.println("Conexión exitosa a la base de datos");
            }

        } catch (SQLException e) {
            System.out.println("Error: No se pudo conectar a la base de datos.");
            e.printStackTrace();
        }
        return conexion;
    }

    
    public void desconectar() {
        try {
            if (conexion != null && !conexion.isClosed()) {
            	conexion.close();
                //System.out.println("Conexión cerrada correctamente.");
            }
        } catch (SQLException e) {
            System.out.println("Error al cerrar la conexión.");
            e.printStackTrace();
        }
    }

}
