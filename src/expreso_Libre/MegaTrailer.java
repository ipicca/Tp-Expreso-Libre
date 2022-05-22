package expreso_Libre;

public class MegaTrailer extends Transporte { //HERENCIA DE TRANSPORTE 
	private boolean tieneRefrigeracion;
	private double costoKm;
	private double segCarga;
	private double costoFijo;
	private double costoComida;
	
	public MegaTrailer() {}
	
	public MegaTrailer(String matricula, double cargaMax, double capacidad, boolean tieneRefrigeracion, double costoKm,
			double segCarga, double costoFijo, double costoComida) {
		
		super(matricula, cargaMax, capacidad);
		
		this.tieneRefrigeracion = tieneRefrigeracion;
		this.costoKm = costoKm;
		this.segCarga = segCarga;
		this.costoFijo = costoFijo;
		this.costoComida = costoComida;
	}


	@Override
	public  double consultarTarifa () { // SOBRECARGA O SOBREESCRITURA
		return segCarga + costoComida + segCarga;
	}


	
	
	
}
