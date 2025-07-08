package modelo.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JOptionPane;

import conexion.ConexionBD;

import modelo.vo.MascotaVo;
import modelo.vo.PersonaVo;

public class MascotaDao {

	private static final boolean ConsultarMascota = false;
	private MascotaVo MascotaVo;
	private static MascotaDao instancia;
	private static Connection conexion;
	
	public MascotaDao(Connection conexion) {
		this.conexion=conexion;
	}
	public String registrarMascota(MascotaVo mascota) {
        String resultado = "";

        try {
            String consultaDueño = "SELECT nombre FROM persona WHERE documento = ?";
            PreparedStatement psDueño = conexion.prepareStatement(consultaDueño);
            psDueño.setString(1, mascota.getDocumentoDueno());
            ResultSet rsDueño = psDueño.executeQuery();

            if (!rsDueño.next()) {
                resultado = "ERROR_DUEÑO";
                JOptionPane.showMessageDialog(null, "No existe dueño");
            } else {
                String insertar = "INSERT INTO mascota(nombre, raza, sexo, documento_dueno) VALUES (?, ?, ?, ?)";
                PreparedStatement ps = conexion.prepareStatement(insertar);
                ps.setString(1, mascota.getNombre());
                ps.setString(2, mascota.getRaza());
                ps.setString(3, mascota.getSexo());
                ps.setString(4, mascota.getDocumentoDueno());
                ps.executeUpdate();
                

                resultado = rsDueño.getString("nombre"); 
                JOptionPane.showMessageDialog(null, "mascota registrada");
            }
        } catch (Exception e) {
            System.err.println("Error al registrar mascota: " + e.getMessage());
            resultado = "ERROR_BD";
        }

        return resultado;
    }
	public static MascotaDao getInstacia() {
		if (instancia == null) {
	        instancia = new MascotaDao(conexion);
	    }
	    return instancia;
	}
	public static void inicializarConexion() {
		if (conexion == null) {
	        ConexionBD conexionBD = new ConexionBD();
	        conexion = conexionBD.getConexion();
	    }
	}
	public String ActualizarMascota(MascotaVo mascota) {
	    String resultado = "";

	    try {
	 
	        String sqlDueño = "SELECT nombre FROM persona WHERE documento = ?";
	        PreparedStatement psDueño = conexion.prepareStatement(sqlDueño);
	        psDueño.setString(1, mascota.getDocumentoDueno());
	        ResultSet rsDueño = psDueño.executeQuery();

	        if (!rsDueño.next()) {
	            return "DUEÑO_NO_EXISTE";
	        }

	        String sqlExiste = "SELECT * FROM mascota WHERE nombre = ? AND documento_dueno = ?";
	        PreparedStatement psExiste = conexion.prepareStatement(sqlExiste);
	        psExiste.setString(1, mascota.getNombre());
	        psExiste.setString(2, mascota.getDocumentoDueno());
	        ResultSet rsMascota = psExiste.executeQuery();

	        if (!rsMascota.next()) {
	        	 JOptionPane.showMessageDialog(null, "No se encontro la mascota");
	            return "MASCOTA_NO_EXISTE";
	          
	        }

	        String sqlActualizar = "UPDATE mascota SET raza = ?, sexo = ? WHERE nombre = ? AND documento_dueno = ?";
	        PreparedStatement psActualizar = conexion.prepareStatement(sqlActualizar);
	        psActualizar.setString(1, mascota.getRaza());
	        psActualizar.setString(2, mascota.getSexo());
	        psActualizar.setString(3, mascota.getNombre());
	        psActualizar.setString(4, mascota.getDocumentoDueno());

	        int filas = psActualizar.executeUpdate();
	        if (filas > 0) {
	            resultado = "OK";
	            JOptionPane.showMessageDialog(null, "Se actualizo correctamente");
	        } else {
	            resultado = "ERROR_ACTUALIZACION";
	            JOptionPane.showMessageDialog(null, "Erros en la actualizacion");
	        }

	    } catch (Exception e) {
	        resultado = "ERROR_BD";
	        System.err.println("Error al actualizar mascota: " + e.getMessage());
	    }

	    return resultado;
	}
		public boolean EliminarMascota(MascotaVo miMascotaVo) {
		    try {
		        Connection conectar = ConexionBD.getInstancia().getConexion();
		        String sql = "DELETE FROM mascota WHERE documento_dueno = ?";
		        PreparedStatement ps = conectar.prepareStatement(sql);
	
		        ps.setString(1, miMascotaVo.getDocumentoDueno());
	
		        int fila = ps.executeUpdate();
		        ps.close();
		        conectar.close();
	
		        JOptionPane.showMessageDialog(null, "Mascota eliminada con éxito");
		        return fila > 0;
		    } catch (Exception e) {
		        System.out.println("Error al eliminar mascota: " + e.getMessage());
		    }
			return false;
		}
		public List<MascotaVo> ConsultarMascota(String documento) {
		    List<MascotaVo> ConsultarMascota = new ArrayList<>();
		    try {
		        Connection conn = ConexionBD.getInstancia().getConexion();
		        String sql = "SELECT * FROM mascota WHERE documento_dueno = ?";
		        PreparedStatement ps = conn.prepareStatement(sql);
		        ps.setString(1, documento);
		        ResultSet rs = ps.executeQuery();

		        while (rs.next()) {
		            MascotaVo mascota = new MascotaVo();
		            mascota.setNombre(rs.getString("nombre"));
		            mascota.setRaza(rs.getString("raza"));
		            mascota.setSexo(rs.getString("sexo"));
		            mascota.setDocumentoDueno(documento);
		            ConsultarMascota.add(mascota);		 
	
		        }
		        
		        
		        
		    } catch (Exception e) {
		        System.err.println("Error al consultar mascotas: " + e.getMessage());
		    }
		    
		    return ConsultarMascota;
		}
		public List<MascotaVo> listaMascotas() {
			List<MascotaVo> lista = new ArrayList<>();
		    try {
		        String consulta = "SELECT * FROM mascota";
		        PreparedStatement stmt = conexion.prepareStatement(consulta);
		        ResultSet rs = stmt.executeQuery();

		        while (rs.next()) {
		            MascotaVo m = new MascotaVo();
		            m.setNombre(rs.getString("Nombre"));
		            m.setRaza(rs.getString("raza"));
		            m.setSexo(rs.getString("sexo"));
		            
		            lista.add(m);
		        }
		    } catch (Exception e) {
		        System.err.println("Error al listar mascotas: " + e.getMessage());
		    }
		    return lista;
		}
	}