package expreso_Libre;

public abstract class Transporte {		//CLASE ABSTRACTA
	protected String matricula;
	protected double cargaMax;
	protected double capacidad;
	
	protected Transporte() {}

	public Transporte(String matricula, double cargaMax, double capacidad) {
		this.matricula = matricula;
		this.cargaMax = cargaMax;
		this.capacidad = capacidad;
	
		
		
	}
	
	//public abstract double getCargaMax();


	//public abstract void setCargaMax(double kg);

	//public abstract double getCapacidad();
	
	//public abstract void setCapacidad(double vol);
	
	public void setCapacidad(double capacidad) {
		this.capacidad = capacidad;
	}

	public double getCargaMax() {
		return cargaMax;
	}

	public double getCapacidad() {
		return capacidad;
	}

	public void setCargaMax(double cargaMax) {
		this.cargaMax = cargaMax;
	}

	public abstract double consultarTarifa();		//TODOS LAS SUBCLASES DEBEN IMPLEMENTAR CONSULTAR TARIFA
	
	public abstract boolean tieneRefrigeracion();
	
	public String getMatricula() {
        return matricula;
    }

    public abstract boolean tienePaquetes();
    
	public abstract void cargarPaqueteTransporte(Paquete paquete);
	
	public abstract boolean estaDisponibleParaUnViaje ();
	
	public abstract void cambiarEstaDisponible();
	
	//public abstract boolean tieneEspacioCarga();
	public boolean tieneEspacioCarga() {
		return cargaMax > 0 && capacidad >0;
	}
	public abstract double obtenerPesoCompletoPaquetes();
	
	public abstract double obtenerVolCompletoPaquetes();

	public abstract void mostrarPaquetesCargados();
	
	public abstract boolean estaCargado();




}
	