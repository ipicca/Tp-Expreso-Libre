package expreso_Libre;

public class Flete extends Transporte  { //HERENCIA DE TRANSPORTE 
	
	private int cantidadAcompaņantes;
	private float costoFijo;
	
	public float consultarTarifa() { // SOBRECARGA O SOBREESCRITURA
		return costoFijo * cantidadAcompaņantes;
	}

}
