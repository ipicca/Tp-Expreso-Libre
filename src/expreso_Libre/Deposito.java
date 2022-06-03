package expreso_Libre;

import java.util.LinkedList;

public class Deposito {
	
	private boolean conRefrigeracion;
	private double capacidadDeposito;
	public LinkedList<Paquete> Lpaquetes;	
	
	//Constructor de los depositos.
	public Deposito(boolean conRefrigeracion,double capacidadDeposito) {	
		this.conRefrigeracion = conRefrigeracion;
		this.capacidadDeposito = capacidadDeposito;
		this.Lpaquetes= new LinkedList <Paquete>();
	}
	
	/*-----------toString de depositos---------------*/
	@Override
	public String toString() {
		return  "\n"+ "		Deposito con capacidad de refrigeracion: " + conRefrigeracion + " 	||" + " Capacidad máxima del mismo: " + capacidadDeposito;
	}

	/*----------- Metodos ---------------*/
	
	public boolean getRefrigferacion() {
		return this.conRefrigeracion;
	}
	
	public double getCapacidadDeposito() {
		return this.capacidadDeposito;
	}
	
	public double setCapacidadDeposito(double vol) {
		return this.capacidadDeposito=this.capacidadDeposito-vol;
	}
	
	public void agregarPaquetesAlDeposito(Paquete paquete) {
		if (this.capacidadDeposito < paquete.getVol()) { 								//Si la capacidad actual del depo no nos permite incorporarlo
			throw new RuntimeException("No se pudo agregar por falta de capacidad");
        }
		Lpaquetes.add(paquete);	
		setCapacidadDeposito(paquete.getVol()); 			
    }
	
	public boolean chequearPaquete(Paquete paquete) {
		return this.getRefrigferacion() == paquete.necesitaRefrigeracion() && paquete.getVol()<this.getCapacidadDeposito();
	}
	
	public boolean actoParaCarga(Transporte transporte){			
			boolean refri=transporte.tieneRefrigeracion()==getRefrigferacion();
			boolean tieneEspacioCarga=transporte.tieneEspacioCarga();
			return  refri && tieneEspacioCarga;
		}

}	
	//---------------------------------------------------------------- FIN CLASE DEPOSITOS ----------------------------------------------------------------//	

