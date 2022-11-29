
public class Mensaje {

	private String fechaHora;
	
	private String emisor;
	
	private String receptor;
	
	private String mensaje;
	
	public Mensaje(String fechaHora, String emisor, String receptor, String mensaje) {
		
		this.fechaHora = fechaHora;
		this.emisor = emisor;
		this.receptor = receptor;
		this.mensaje = mensaje;
		
	}

	public String getFechaHora() {
		return fechaHora;
	}

	public void setFechaHora(String fechaHora) {
		this.fechaHora = fechaHora;
	}

	public String getEmisor() {
		return emisor;
	}

	public void setEmisor(String emisor) {
		this.emisor = emisor;
	}

	public String getReceptor() {
		return receptor;
	}

	public void setReceptor(String receptor) {
		this.receptor = receptor;
	}

	public String getMensaje() {
		return mensaje;
	}

	public void setMensaje(String mensaje) {
		this.mensaje = mensaje;
	}

	@Override
	public String toString() {
		return "Mensaje [fechaHora=" + fechaHora + ", emisor=" + emisor + ", receptor=" + receptor + ", mensaje="
				+ mensaje + "]";
	}
	
	
	
}
