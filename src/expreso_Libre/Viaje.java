package expreso_Libre;

public class Viaje {
	
	private String destino;
	private int km ;
	
	//Constructor de viaje.
	
	public Viaje(){}
	public Viaje (String destino, int km) {
		this.destino=destino;
		this.km=km;
	}
	
	/*-----------toString de Viajes---------------*/
	@Override
	public String toString() {
		return "Destino: " + destino +" - "+ " Kilometraje: " + km;
	}
	
	/*----------- Metodos ---------------*/
	
	public String getDestino() {
		return destino;
	}
	
	public int getKm() {
		return km;
	}

	//---------------------------------------------------------------- FIN CLASE VIAJE ----------------------------------------------------------------//		
	
}
