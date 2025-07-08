package modelo.vo;

public class MascotaVo {
	private String nombre;
	private String sexo;
	private String raza;
	private String documentoDueno;
	
	public MascotaVo() {
		
	}
	public MascotaVo( String nombre, String sexo, String raza, String documentoDueno) {
		this.nombre= nombre;
		this.sexo=sexo;
		this.raza=raza;
		this.documentoDueno=documentoDueno;
		
		
	}
	
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
	 * @return the tipo
	 */
	public String getSexo() {
		return sexo;
	}
	/**
	 * @param tipo the tipo to set
	 */
	public void setSexo(String sexo) {
		this.sexo = sexo;
	}
	/**
	 * @return the raza
	 */
	public String getRaza() {
		return raza;
	}
	/**
	 * @param raza the raza to set
	 */
	public void setRaza(String raza) {
		this.raza = raza;
	}
	/**
	 * @return the documentoDueno
	 */
	public String getDocumentoDueno() {
		return documentoDueno;
	}
	/**
	 * @param documentoDueno the documentoDueno to set
	 */
	public void setDocumentoDueno(String documentoDueno) {
		this.documentoDueno = documentoDueno;
	}
	

}
