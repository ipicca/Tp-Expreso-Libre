package expreso_Libre;

public class Flete extends Transporte  { //HERENCIA DE TRANSPORTE 
	private double costoKm;
	private int cantAcompaniantes;
	private double costoPorAcompaniante;
	
	
	public Flete() {}
	public Flete(String matricula, double cargaMax, double capacidad,
			 double costoKm, int cantAcompaniantes, double costoPorAcompaniante) {
		
		super(matricula, cargaMax, capacidad);
		
		this.costoKm = costoKm;
		this.cantAcompaniantes = cantAcompaniantes;
		this.costoPorAcompaniante = costoPorAcompaniante;
		
	}
	

	@Override
	public double consultarTarifa() { // SOBRECARGA O SOBREESCRITURA
		return cantAcompaniantes * costoPorAcompaniante;
	}
	protected boolean estaCargado() {
		return capacidad == this.capacidad;
	}

	
	
	
	

}
