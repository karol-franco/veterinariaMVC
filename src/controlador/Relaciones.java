package controlador;

import conexion.ConexionBD;
import vista.VentanaMascotas;
import vista.VentanaPersonas;
import vista.VentanaPrincipal;

public class Relaciones {
	private static final ConexionBD ConexionBD = null;
	ConexionBD miConexion= ConexionBD;


	
	public void iniciar() {
		
		VentanaPrincipal miVentanaPrincipal= new VentanaPrincipal();
		VentanaPersonas miVentanaPersonas= new VentanaPersonas();
		VentanaMascotas miVentanaMascotas = new VentanaMascotas(); 
		coordinador miCoordinador = new coordinador();
		
		
		miVentanaPrincipal.setCoordinador(miCoordinador);
		miVentanaPersonas.setCoordinador(miCoordinador);
		miVentanaMascotas.setCoordinador(miCoordinador);
		
		miCoordinador.setVentanaPersonas(miVentanaPersonas);
		miCoordinador.setVentanaMascotas(miVentanaMascotas);
		
		miVentanaPrincipal.setVisible(true);
	
	}
}
