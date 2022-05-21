package expreso_Libre;


import java.util.ArrayList;

public class Deposito {
	ArrayList<Paquete> paquetes = new ArrayList<Paquete>();
	boolean conRefrigeracion;
	
	
	public Deposito(ArrayList<Paquete> paquetes, boolean conRefrigeracion) {

		this.paquetes = paquetes;
		this.conRefrigeracion = conRefrigeracion;
		
	}
	
	
	public void agregarPaquetesAlDeposito() {
		 
	}
	
}
