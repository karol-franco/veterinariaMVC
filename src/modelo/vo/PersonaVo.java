package modelo.vo;

public class PersonaVo {

	private String documento;
	private String nombre;
	private String telefono;

	
	public PersonaVo() {
		
	}
	
	public PersonaVo( String documento, String nombre, String telefono) {
		this.documento= documento;
		this.nombre = nombre;
		this.telefono= telefono;
	}

	/**
	 * @return the docuemento
	 */
	public String getDocumento() {
		return documento;
	}

	/**
	 * @param docuemento the docuemento to set
	 */
	public void setDocuemento(String documento) {
		this.documento = documento;
	}

	/**
	 * @return the nombre
	 */
	public String getNombre() {
		return nombre;
	}

	/**
	 * @param nombre the nombre to set
	 */
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	/**
	 * @return the telefono
	 */
	public String getTelefono() {
		return telefono;
	}

	/**
	 * @param telefono the telefono to set
	 */
	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}

}
