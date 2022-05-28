package expreso_Libre;

import java.util.ArrayList;
import java.util.LinkedList;

public class MegaTrailer extends Transporte { //HERENCIA DE TRANSPORTE 
	private boolean tieneRefrigeracion;
	private double costoKm;
	private double segCarga;
	private double costoFijo;
	private double costoComida;
	public LinkedList<Paquete> paquetesMegaTrailer;
	private boolean disponible;
	
	public MegaTrailer() {}
	
	public MegaTrailer(String matricula, double cargaMax, double capacidad, boolean tieneRefrigeracion, double costoKm,
			double segCarga, double costoFijo, double costoComida) {
		
		super(matricula, cargaMax, capacidad);
		
		//this.tieneRefrigeracion = tieneRefrigeracion;
		this.costoKm = costoKm;
		this.segCarga = segCarga;
		this.costoFijo = costoFijo;
		this.costoComida = costoComida;
		paquetesMegaTrailer= new LinkedList<Paquete>();
	}


	@Override
	public  double consultarTarifa () { // SOBRECARGA O SOBREESCRITURA
		return segCarga + costoComida + segCarga;
	}
	
	@Override
	public boolean tieneRefrigeracion() {
		return tieneRefrigeracion;
	}
	
	@Override
	 public void cargarPaqueteTransporte(Paquete paquete) {
		paquetesMegaTrailer.add(paquete);
	}
	
	@Override
	public boolean tienePaquetes() {
		return paquetesMegaTrailer.size()>0;
	}
	
	@Override
	public boolean estaCargado() {
		return 	paquetesMegaTrailer.size()>0;
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
	/*
	public boolean tieneEspacioCarga() {
		return cargaMax> 0 && capacidad > 0 ;
	}
	*/
	public double obtenerPesoCompletoPaquetes() {
		double pesoTot=0;
		
		for (Paquete paquete:paquetesMegaTrailer) {
			 pesoTot+=paquete.getPeso();
		}
		
		 return pesoTot;
	}
	
	public double obtenerVolCompletoPaquetes() {
		
	double volTot=0;
		
		for (Paquete paquete:paquetesMegaTrailer) {
			volTot+=paquete.getVol();
		}
		
		 return volTot;
	
	}
	
	@Override
	public void mostrarPaquetesCargados() {

		for (Paquete paq:paquetesMegaTrailer) {
			System.out.println("---------------------------");
			System.out.println("Paquetes de MEGA TRAILER");
			System.out.println("---------------------------");
			System.out.println("destino:"+paq.getDestino());
			System.out.println("peso:"+paq.getPeso());
			System.out.println("volumen:"+paq.getVol());
			System.out.println("Refri:"+paq.necesitaRefrigeracion());
			System.out.println("---------------------------");
		}
	}


	
	
	
}
