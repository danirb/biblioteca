package biblioteca;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class LibroBD {

	private static Connection conn = null;

	
	
	/**
	 * Accede a la base de datos para obtener un ArrayList de Alumnos
	 * 
	 * @return	Un ArrayList de alumnos
	 * @throws SQLException
	 * @throws ClassNotFoundException
	 */
	public static ArrayList<Libro> getAlumnos() throws SQLException, ClassNotFoundException {
		Libro libro;
		ArrayList<Libro> listado = new ArrayList<Libro>();
        Statement stmt;
        ResultSet rs;
        
        Class.forName("org.sqlite.JDBC");
		conn = DriverManager.getConnection("jdbc:sqlite:db/libros.db");
		stmt = conn.createStatement();
        rs = stmt.executeQuery("SELECT * FROM alumno");
        
        while ( rs.next() ) {
            String id_libro = rs.getString("id_libro");
            String titulo = rs.getString("titulo");
            int autor = rs.getInt("autor");
            String direccion = rs.getString("direccion");
            String email = rs.getString("email");
            String codigoPostal = rs.getString("codigo postal");
            String poblacion = rs.getString("poblacion");
            String localidad = rs.getString("localidad");
            String telefono = rs.getString("telefono");
            libro = new Libro(dni, nombre, apellidos);
            libro.setDireccion(direccion);
            libro.setCodigoPostal(codigoPostal);
            libro.setEmail(email);
            libro.setPoblacion(poblacion);
            libro.setLocalidad(localidad);
            libro.setTelefono(telefono);
            listado.add(libro);
        }
    	conn.close();
        return listado;
	
	}
}
