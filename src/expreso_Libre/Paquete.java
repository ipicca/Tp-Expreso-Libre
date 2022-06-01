package expreso_Libre;

public class Paquete {

	private String destino;
	private double volumen;
	private double peso;
	private boolean necesitaRefrigeracion;
	
	public Paquete(String destino, double peso, double vol, boolean necesitaRefrigeracion) {
		this.destino = destino;
		this.peso = peso;
		this.volumen = vol;
		this.necesitaRefrigeracion = necesitaRefrigeracion;
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
		 return this.necesitaRefrigeracion;
	    }
	 
	 public boolean paqueteConElMismoDestino(String Nuevodestino) {
		 return destino.equals(Nuevodestino);
	 }
	 
	 @Override
	 public boolean equals (Object obj){
	 if (obj ==null)
	   return false;
	 if(!this.getClass().getName().equals(obj.getClass().getName()))
	   return false;
	 Paquete otro= (Paquete) obj;
	 return this.destino.equals(otro.getDestino()) && this.peso==otro.getPeso()
	 && this.volumen==otro.getVol() && this.necesitaRefrigeracion==otro.necesitaRefrigeracion();
	 }
	
	
}
