package expreso_Libre;

public abstract class Transporte {		//CLASE ABSTRACTA

	private int numId;
	private double cargaMax;
	private double capacidadMax;
	private boolean eqRefri;
	private boolean enViaje;
	private float costoKm;
	
	abstract float consultarTarifa();		//TODOS LAS SUBCLASES DEBEN IMPLEMENTAR CONSULTAR TARIFA
	
}
