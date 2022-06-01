package expreso_Libre;

public abstract class Transporte {		//CLASE ABSTRACTA


	protected String matricula;
	protected double cargaMax;
	protected double capacidad;
	protected boolean disponibilidad;
	protected boolean enViaje;
	
	protected Transporte() {}

	public Transporte(String matricula, double cargaMax, double capacidad) {
		this.matricula = matricula;
		this.cargaMax = cargaMax;
		this.capacidad = capacidad;
		enViaje = false;
		disponibilidad = true;
	
	}
	@Override
	public String toString() {
		return "Transporte [matricula=" + matricula + ", cargaMax=" + cargaMax + ", capacidad=" + capacidad
				+ ", enViaje=" + disponibilidad + "]";
	}
	
	public void setCapacidad(double capacidad) {
		this.capacidad -= capacidad;
	}

	public double getCargaMax() {
		return cargaMax;
	}

	public double getCapacidad() {
		return capacidad;
	}

	public void setCargaMax(double cargaMax) {
		this.cargaMax-= cargaMax;
	}
	
	public String getMatricula() {
        return matricula;
    }
	
	public  boolean estado () {
		return disponibilidad;
	}
	
	public void cambiarViaje() {
		if (!enViaje) {
			enViaje=true;
		}
		else {
			enViaje=false;
		}
	}
	
	public boolean enViaje() { 
		return enViaje;
	}
	
	public void cambiarEstado() {
		if (disponibilidad) {
			disponibilidad=false;
		}
		else {
			disponibilidad=true;
		}
	}

	public boolean tieneEspacioCarga() {
		return cargaMax > 0 && capacidad >0;
	}
	
	public void cambiarEstado2(boolean tipo) {
		disponibilidad=tipo;
	}
	

	public abstract double consultarTarifa(double cantKm);
	
	public abstract boolean tieneRefrigeracion();
	
	
    public abstract boolean tienePaquetes();
    
	public abstract void cargarPaqueteTransporte(Paquete paquete);
	
	
	public abstract double obtenerPesoCompletoPaquetes();
	
	public abstract double obtenerVolCompletoPaquetes();

	public abstract void mostrarPaquetesCargados();
	
	public abstract void vaciarCarga();
	
	public abstract boolean asignarDestinoTransporte(Viaje dest,Transporte transporte);
	
	public abstract String tipoTransporte();

	
	
	public void actualizarDatosDelTransporte() {
		setCapacidad(-(obtenerVolCompletoPaquetes())); // se vuelve a reincoprar el vol
		setCargaMax(-(obtenerPesoCompletoPaquetes())); // se vuelve a reincoprar el peso
		vaciarCarga();//se blanquea toda la lista de paquetes del transporte
		cambiarEstado(); // disponibilidad = true;
		cambiarViaje();	// enViaje = false;
	
	}
	
	@Override
	public boolean equals(Object obj) {
		if (obj == null)
			return false;
		if (obj instanceof Transporte == false)
			return false;
		Transporte otro = (Transporte) obj;
		return this.obtenerVolCompletoPaquetes() == otro.obtenerVolCompletoPaquetes() 
				&& this.obtenerPesoCompletoPaquetes() == otro.obtenerPesoCompletoPaquetes()
				&& this.tipoTransporte().equals(otro.tipoTransporte());
	}
	
	public void datosTransporte() {
		System.out.println("capacidad= lts "+this.getCapacidad());
		System.out.println("carga Max= kg "+this.getCargaMax());
		System.out.println("Disp="+this.estado());
	}

	
	
}
	