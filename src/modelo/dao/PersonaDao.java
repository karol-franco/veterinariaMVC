package modelo.dao;

import java.sql.Connection;

import conexion.ConexionBD;
import modelo.vo.PersonaVo;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JOptionPane;

public class PersonaDao {
	private Connection conexion;
	private static PersonaDao instancia;
	
	public PersonaDao(Connection conexion) {
		this.conexion=conexion;
		
	}
	public PersonaDao() {
		conexion = ConexionBD.getInstancia().getConexion();
	}
	
	
	

	public String RegistrarPersona(PersonaVo miPersonaVo) {
	    String resultado = "";
	    try {
	        String consulta = "INSERT INTO persona(documento, nombre, telefono) VALUES(?,?,?)";
	        PreparedStatement preStatement = conexion.prepareStatement(consulta);
	        preStatement.setString(1, miPersonaVo.getDocumento());
	        preStatement.setString(2, miPersonaVo.getNombre());
	        preStatement.setString(3, miPersonaVo.getTelefono());
	        preStatement.execute();
	        resultado = "OK";
	    } catch (Exception e) {
	        System.err.println("NO SE PUDO REGISTRAR: " + e.getMessage());
	        resultado = "NO SE PUDO REGISTRAR EL DATO";
	    }
	    return resultado;
	}


	public static PersonaDao getInstancia() {
	    if (instancia == null) {
	        instancia = new PersonaDao(new ConexionBD().getConexion());
	    }
	    return instancia;
	}


	public String ActualizarPersona(PersonaVo personaVo) {
	    String resultado = "";
	    try {
	        String consulta = "UPDATE persona SET nombre = ?, telefono = ? WHERE documento = ?";
	        PreparedStatement stmt = conexion.prepareStatement(consulta);
	        stmt.setString(1, personaVo.getNombre());
	        stmt.setString(2, personaVo.getTelefono());
	        stmt.setString(3, personaVo.getDocumento());

	        int filasAfectadas = stmt.executeUpdate();
	        if (filasAfectadas > 0) {
	            resultado = "Actualización exitosa.";
	            System.out.println("Se ha actualizado");
	        } else {
	            resultado = "No se encontró la persona con ese documento.";
	        }
	    } catch (Exception e) {
	        resultado = "Error al actualizar: " + e.getMessage();
	    }
	    return resultado;
	}


	public boolean eliminarPersona(PersonaVo personaVo) {
		try {
			Connection conectar = ConexionBD.getInstancia().getConexion();
			String sql = "DELETE FROM persona WHERE documento = ?";
			java.sql.PreparedStatement ps = conectar.prepareStatement(sql);

		      ps.setString(1, personaVo.getDocumento());
		      
		    int fila = ps.executeUpdate();
		    JOptionPane.showMessageDialog(null, "Persona Elimada con exito");
		    ps.close();
		    conectar.close();
		    return fila > 0;
		
		}catch(Exception e) {
		     System.out.println("Error al eliminar persona: " + e.getMessage());
		     return false;
		}
	}
	public List<PersonaVo> listaPersonas() {
		 List<PersonaVo> lista = new ArrayList<>();
		    try {
		        String consulta = "SELECT * FROM persona";
		        PreparedStatement stmt = conexion.prepareStatement(consulta);
		        ResultSet rs = stmt.executeQuery();

		        while (rs.next()) {
		            PersonaVo p = new PersonaVo();
		            p.setDocuemento(rs.getString("documento"));
		            p.setNombre(rs.getString("nombre"));
		            p.setTelefono(rs.getString("telefono"));
		            lista.add(p);
		        }
		    } catch (Exception e) {
		        System.err.println("Error al listar personas: " + e.getMessage());
		    }
		    return lista;
	}
	public String obtenerNombrePorDocumento(String documento) {
	    String nombre = "";
	    try {
	        Connection conn = ConexionBD.getInstancia().getConexion();
	        String sql = "SELECT nombre FROM persona WHERE documento = ?";
	        PreparedStatement ps = conn.prepareStatement(sql);
	        ps.setString(1, documento);
	        ResultSet rs = ps.executeQuery();
	        if (rs.next()) {
	            nombre = rs.getString("nombre");
	        }
	        rs.close();
	        ps.close();
	    } catch (Exception e) {
	        System.err.println("Error al obtener nombre del dueño: " + e.getMessage());
	    }
	    return nombre;
	}
	public PersonaVo ConsultarPersona(String documento) {
		PersonaVo persona = null;
	    try {
	        Connection conn = ConexionBD.getInstancia().getConexion();
	        String sql = "SELECT * FROM persona WHERE documento = ?";
	        PreparedStatement ps = conn.prepareStatement(sql);
	        ps.setString(1, documento);
	        ResultSet rs = ps.executeQuery();

	        if (rs.next()) {
	            persona = new PersonaVo();
	            persona.setDocuemento(rs.getString("documento"));
	            persona.setNombre(rs.getString("nombre"));
	            persona.setTelefono(rs.getString("Telefono"));
	        }

	        rs.close();
	        ps.close();
	    } catch (Exception e) {
	        System.err.println("Error al consultar persona: " + e.getMessage());
	    }

	    return persona;
	
	}

}
