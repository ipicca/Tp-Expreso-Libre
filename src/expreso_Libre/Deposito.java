package expreso_Libre;

import java.util.Iterator;
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
	
	
	
	@Override
	public String toString() {
		return "Deposito [conRefrigeracion=" + conRefrigeracion + ", capacidadDeposito=" + capacidadDeposito
				+ ", Lpaquetes=" + Lpaquetes + "]";
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
		setCapacidadDeposito(paquete.getVol()); 
					
    }
	
	public void retirarPaqueteDep(Paquete p) {
		
		Iterator<Paquete> it = Lpaquetes.iterator();
		while (it.hasNext()) {
			 Paquete  paq = (Paquete)it.next();
			  if (paq.equals(p)) {
			    it.remove();
			  }
		}
	}
	
	public boolean chequearPaquete(Paquete paquete) {
		return this.getRefrigferacion() == paquete.necesitaRefrigeracion() && paquete.getVol()<this.getCapacidadDeposito();
	}
	
	

	
	public boolean actoParaCarga(Transporte transporte){		
			
			boolean refri=transporte.tieneRefrigeracion()==getRefrigferacion();
			boolean tieneEspacioCarga=transporte.tieneEspacioCarga();
			return  refri && tieneEspacioCarga;
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
	/*
	@Override
	public String toString(){

		StringBuilder st = new StringBuilder();
		for (Paquete p:Lpaquetes) {
		st.append(p.getDestino());
		st.append("\n");
		st.append(p.getPeso());
		st.append("\n");
		st.append(p.getVol());
		st.append("\n");
		st.append(p.necesitaRefrigeracion());
		st.append("\n");
		
		}
		return st.toString();
	}
	*/
	
}
