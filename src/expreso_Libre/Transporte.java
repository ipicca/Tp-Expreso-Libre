package expreso_Libre;

public abstract class Transporte {		//CLASE ABSTRACTA

	protected String matricula;
	protected double cargaMax;
	protected double capacidad;
	protected String destino;
	
	protected Transporte() {}

	public Transporte(String matricula, double cargaMax, double capacidad) {
		this.matricula = matricula;
		this.cargaMax = cargaMax;
		this.capacidad = capacidad;
		
	}
	
	protected abstract double consultarTarifa();		//TODOS LAS SUBCLASES DEBEN IMPLEMENTAR CONSULTAR TARIFA

	protected abstract boolean estaCargado();



}
	