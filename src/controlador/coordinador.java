package controlador;

import java.util.List;

import modelo.dao.MascotaDao;
import modelo.dao.PersonaDao;
import modelo.vo.MascotaVo;
import modelo.vo.PersonaVo;
import vista.VentanaMascotas;
import vista.VentanaPersonas;

public class coordinador {

	private VentanaPersonas miVentanaPersonas;
	private VentanaMascotas miVentanaMascotas;
	private PersonaDao miPersonaDao;
	private MascotaDao miMascotaDao;

	public coordinador() {
		MascotaDao.inicializarConexion();
		miMascotaDao = MascotaDao.getInstacia();
		
		miPersonaDao = PersonaDao.getInstancia();
	}
	public void mostrarVentanaPersonas() {
		miVentanaPersonas.setVisible(true);
	}


	public void setVentanaPersonas(VentanaPersonas miVentanaPersonas) {
		this.miVentanaPersonas = miVentanaPersonas;
		
	}

	public void mostrarVentanaMascotas() {
		miVentanaMascotas.setVisible(true);
	}
	public void setVentanaMascotas(VentanaMascotas miVentanaMascotas) {
		this.miVentanaMascotas = miVentanaMascotas;
	}


	public void registrarPersona(PersonaVo personaVo) {
		 miPersonaDao.RegistrarPersona(personaVo);
	}
	public void ActualizarPersona(PersonaVo personaVo) {
		miPersonaDao.ActualizarPersona(personaVo);
	}
	public void eliminarPersona(PersonaVo personaVo) {
		miPersonaDao.eliminarPersona(personaVo);
	}
	public List<PersonaVo> listaPersonas() {
		
		return miPersonaDao.listaPersonas();
	}
	public PersonaVo consultarPersonaPorDocumento(String documento) {
		
		return  miPersonaDao.ConsultarPersona(documento);
	}
	
	
	
	public void  registrarMascota(MascotaVo miMascotaVo) {
		miMascotaDao.registrarMascota(miMascotaVo);
		
	}
	public void ActualizarMascota(MascotaVo miMascotaVo) {
		miMascotaDao.ActualizarMascota(miMascotaVo);
	}
	public void EliminarMascota(MascotaVo miMascotaVo) {
		miMascotaDao.EliminarMascota(miMascotaVo);
	}
	public List<MascotaVo> ConsultarMascota(String documento) {
		return miMascotaDao.ConsultarMascota(documento);
	}
	public List<MascotaVo> listaMascotas() {
		
		
		return miMascotaDao.listaMascotas();
	}
	public String obtenerNombreDueno(String documento) {
	    return miPersonaDao.obtenerNombrePorDocumento(documento);
	}
	
	

}
