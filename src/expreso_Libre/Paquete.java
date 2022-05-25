package expreso_Libre;

public class Paquete {

	private String destino;
	private double volumen;
	private double peso;
	private boolean refrigeracion;
	
	public Paquete(String destino, double volumen, double peso, boolean necesitaRefrigeracion) {
		this.destino = destino;
		this.volumen = volumen;
		this.peso = peso;
		this.refrigeracion = necesitaRefrigeracion;
	}
	
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
		 return this.refrigeracion;
	    }

	 
}
	

