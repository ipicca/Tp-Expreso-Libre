package expreso_Libre;

public class CamionTrailer extends Transporte  { //HERENCIA DE TRANSPORTE 
	private boolean tieneRefrigeracion;
	private double segCarga;
	private double costoKm;
	
	public CamionTrailer() {}

	public CamionTrailer(String matricula, double cargaMax, double capacidad, double costoKm,
			boolean tieneRefrigeracion, double segCarga) {
		super(matricula, cargaMax, capacidad);
		this.tieneRefrigeracion = tieneRefrigeracion;
		this.segCarga = segCarga;
		this.costoKm = costoKm;
	
	}
	@Override
	public double consultarTarifa() { // SOBRECARGA O SOBREESCRITURA
		return segCarga;
	}
	protected boolean estaCargado() {
		return capacidad == this.capacidad;
	}
	
	
}


	