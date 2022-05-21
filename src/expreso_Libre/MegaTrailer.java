package expreso_Libre;

public class MegaTrailer extends Transporte { //HERENCIA DE TRANSPORTE 

	private int seguroCarga;
	private float costoViaje;
	private float gastoComida;
	
	
	
	public float consultarTarifa () { // SOBRECARGA O SOBREESCRITURA
		return costoViaje + seguroCarga + gastoComida;
	}
	
	
	
}
