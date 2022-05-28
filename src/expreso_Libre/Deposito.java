package expreso_Libre;

import java.util.ArrayList;
import java.util.LinkedList;

public class Deposito {

	private boolean conRefrigeracion;
	private double capacidadDeposito;
	public LinkedList<Paquete> Lpaquetes;	
	
	public Deposito(boolean conRefrigeracion,double capacidadDeposito) {
		
		this.conRefrigeracion = conRefrigeracion;
		this.capacidadDeposito = capacidadDeposito;
		this.Lpaquetes= new LinkedList <Paquete>();
	}
	
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
		if (this.capacidadDeposito < paquete.getVol()) { //Si la capacidad actual del depo no nos permite incorporarlo
			throw new RuntimeException("No se pudo agregar por falta de capacidad");
        }
		Lpaquetes.add(paquete);	
		//setCapacidadDeposito(paquete.getVol()); //Si nos permite, actualizamos la capacidad del depo
						//Y lo agregamos
    }

	public void retirarPaqueteDep(Paquete p) {

			this.Lpaquetes.remove(p);
    }
	
	public void mostrarPaquetesDelDeposito() {
		System.out.println("cantidad de paquetes cargados"+Lpaquetes.size());
		for (Paquete p:Lpaquetes) {
			System.out.println();
			System.out.println();
			System.out.println("Deposito con:"+getRefrigferacion());
			System.out.println("  "+p.getDestino());
			System.out.println("  "+p.getPeso());
			System.out.println("  "+p.getVol());
			System.out.println("  "+p.necesitaRefrigeracion());
			System.out.println();
			System.out.println();
			
		}
	}
	
}