package expreso_Libre;

import java.util.ArrayList;
import java.util.LinkedList;

public class Flete extends Transporte  { //HERENCIA DE TRANSPORTE 
	private double costoKm;
	private int cantAcompaniantes;
	private double costoPorAcompaniante;
	public LinkedList<Paquete> paquetesFlete;
	private boolean disponible;
	
	
	public Flete() {}
	public Flete(String matricula, double cargaMax, double capacidad,
			 double costoKm, int cantAcompaniantes, double costoPorAcompaniante) {
		
		super(matricula, cargaMax, capacidad);
		
		this.costoKm = costoKm;
		this.cantAcompaniantes = cantAcompaniantes;
		this.costoPorAcompaniante = costoPorAcompaniante;
		paquetesFlete= new LinkedList <Paquete>();
		
	}
	
	

	@Override
	public double consultarTarifa() { // SOBRECARGA O SOBREESCRITURA
		return cantAcompaniantes * costoPorAcompaniante;
	}

	
	@Override
	public boolean tieneRefrigeracion() {
		
		return false;
	}
	
	@Override
	 public void cargarPaqueteTransporte(Paquete paquete) {
		paquetesFlete.add(paquete);
	}
	
	@Override
	public boolean tienePaquetes() {
		return paquetesFlete.size()>0;
	}
	/*
	@Override
	public  double getCargaMax() {
		return this.cargaMax;
	}

	@Override
	public void setCargaMax(double kg) {
		this.cargaMax-=kg;
	}
	
	@Override
	public  double getCapacidad() {
		return this.capacidad;
	}
	
	@Override
	public  void setCapacidad(double vol) {
		this.capacidad-=vol;
	}
	*/
	@Override
	public boolean estaDisponibleParaUnViaje () {
		return disponible;
	}
	
	public void cambiarEstaDisponible() {
		if (disponible) {
			disponible=false;
		}
		else {
			disponible=true;
		}
		
	}
	
	@Override
	public boolean estaCargado() {
		return 	paquetesFlete.size()>0;
	}
	/*
	public boolean tieneEspacioCarga() {
		return cargaMax> 0 && capacidad > 0 ;
	}*/
	
	public double obtenerPesoCompletoPaquetes() {
		double pesoTot=0;
		
		for (Paquete paquete:paquetesFlete) {
			 pesoTot+=paquete.getPeso();
		}
		
		 return pesoTot;
	}
	
	public double obtenerVolCompletoPaquetes() {
		
	double volTot=0;
		
		for (Paquete paquete:paquetesFlete) {
			volTot+=paquete.getVol();
		}
		
		 return volTot;
	
	}
	
	@Override
	public void mostrarPaquetesCargados() {
		
		for (Paquete paq:paquetesFlete) {
			System.out.println("---------------------------");
			System.out.println("Paquetes de FLETE");
			System.out.println("---------------------------");
			System.out.println("destino:"+paq.getDestino());
			System.out.println("peso:"+paq.getPeso());
			System.out.println("volumen:"+paq.getVol());
			System.out.println("Refri:"+paq.necesitaRefrigeracion());
			System.out.println("---------------------------");
		}
	}

	

}
