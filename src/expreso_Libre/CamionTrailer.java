package expreso_Libre;

public class CamionTrailer extends Transporte  { //HERENCIA DE TRANSPORTE 

	private int seguroCarga;
	
	public float consultarTarifa() { // SOBRECARGA O SOBREESCRITURA
		return seguroCarga;
	}

}
