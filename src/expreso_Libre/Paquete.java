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
	 
	 public boolean paqueteConElMismoDestino(String Nuevodestino) {
		 return destino.equals(Nuevodestino);
	 }
	 
	 public boolean equals2 (Paquete p) {
		 return this.destino.equals(p.getDestino()) && this.peso==p.getPeso() 
					&& this.volumen==p.getVol() && this.necesitaRefrigeracion==p.necesitaRefrigeracion(); 
	 }

	void mostrarPaquete() {
		System.out.println("destino="+getDestino());
		System.out.println("peso="+getPeso());
		System.out.println("vol="+getVol());
		System.out.println("refri="+necesitaRefrigeracion());
	}

	
	@Override
	public boolean equals(Object obj) {
		
		if (obj == null)
			return false;
		if (!this.getClass().getName().equals(obj.getClass().getName()))
			return false;
		
		Paquete otro = (Paquete) obj;
	
		return this.destino.equals(otro.getDestino()) && this.peso==otro.getPeso() 
		&& this.volumen==otro.getVol() && this.necesitaRefrigeracion==otro.necesitaRefrigeracion(); 
	}
	
	//---------------------------------------------------------------- FIN CLASE PAQUETES ----------------------------------------------------------------//	
	
}