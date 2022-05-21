package expreso_Libre;

public class Empresa {

	private String cuit;
	private String nombre;
	private double capacidadMaxDepositos;
	
	
	public Empresa(String cuit, String nombre, double capacidadMaxDepositos) {
		this.cuit = cuit;
		this.nombre = nombre;
		this.capacidadMaxDepositos = capacidadMaxDepositos;
	}

	public String getCuit() {
		return cuit;
	}

	public void setCuit(String cuit) {
		this.cuit = cuit;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public double getCapacidadMaxDepositos() {
		return capacidadMaxDepositos;
	}

	public void setCapacidadMaxDepositos(double capacidadMaxDepositos) {
		this.capacidadMaxDepositos = capacidadMaxDepositos;
	}
	
	void asignarDestino(String matricula, String destino) {
		
	}

	public void agregarDestino (String destino,int km) {
		
	}
	
	void agregarTransporte(String matricula, double cargaMax, double costoKm, double capacidad) {
		
	}
	
	void agregarTrailer(String matricula, double cargaMax, 
			double capacidad, boolean tieneRefrigeracion, double costoKm, double segCarga){}

	void agregarMegaTrailer(String matricula, double cargaMax, double capacidad,
			 boolean tieneRefrigeracion, double costoKm, double segCarga, double costoFijo, double costoComida){}

	void agregarFlete(String matricula, double cargaMax, double capacidad,
			 double costoKm, int cantAcompaniantes, double costoPorAcompaniante){}

	
	boolean incorporarPaquete(String destino, double peso, double volumen, 
			boolean necesitaRefrigeracion) {
		return false;
	}
	
	double cargarTransporte(String matricula) {
		return 0;
	}
	
	void iniciarViaje(String matricula) {}
	
	void finalizarViaje(String matricula) {}
	
	double obtenerCostoViaje(String matricula) {
		return 0;
	}
	
	
	String obtenerTransporteIgual(String matricula) {
		return null;
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
