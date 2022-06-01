package expreso_Libre;

public class Paquete {

	private String destino;
	private double volumen;
	private double peso;
	private boolean necesitaRefrigeracion;
	
	//Constructor de Paquetes.
	
	public Paquete(String destino, double peso, double vol, boolean necesitaRefrigeracion) {
		this.destino = destino;
		this.peso = peso;
		this.volumen = vol;
		this.necesitaRefrigeracion = necesitaRefrigeracion;
	}
	
	
	/*----------- Metodos ---------------*/
	
	public String getDestino() {
        return this.destino;
    	}
	
	public double getVol() {
		return this.volumen;
	    }
	
	 public double getPeso() {
		 return this.peso;
	    }

	 public boolean necesitaRefrigeracion() {
		 return this.necesitaRefrigeracion;
	    }
	 

	
	//---------------------------------------------------------------- FIN CLASE PAQUETES ----------------------------------------------------------------//	
	
}