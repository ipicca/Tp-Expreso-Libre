package expreso_Libre;

import java.util.ArrayList;
import java.util.LinkedList;

public class CamionTrailer extends Transporte  { //HERENCIA DE TRANSPORTE 
	private boolean tieneRefrigeracion;
	private double segCarga;
	private double costoKm;
	private boolean disponible;
	public LinkedList<Paquete> paquetesCamionTrailer;
	
	
	@Override
	public double consultarTarifa() { // SOBRECARGA O SOBREESCRITURA
		return segCarga;
	}
	public CamionTrailer() {}

	public CamionTrailer(String matricula, double cargaMax, double capacidad, boolean tieneRefrigeracion,double costoKm,
		double segCarga) {
		super(matricula, cargaMax, capacidad);
		this.tieneRefrigeracion = tieneRefrigeracion;
		this.segCarga = segCarga;
		this.costoKm = costoKm;
		paquetesCamionTrailer= new LinkedList <Paquete>();
		disponible=true;
	
	}
	
	@Override
	public boolean tieneRefrigeracion() {
		return tieneRefrigeracion;
	}
	
	@Override
	 public void cargarPaqueteTransporte(Paquete paquete) {
		paquetesCamionTrailer.add(paquete);
	}
	
	@Override
	public boolean tienePaquetes() {
		return paquetesCamionTrailer.size()>0;
	}
	
	@Override
	public boolean estaCargado() {
		return 	paquetesCamionTrailer.size()>0;
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
	
	@Override
	public void cambiarEstaDisponible() {
		if (disponible) {
			disponible=false;
		}
		else {
			disponible=true;
		}
		
	}
	/*
	@Override
	public boolean tieneEspacioCarga() {
		return cargaMax> 0 && capacidad > 0 ;
	}
	*/
	@Override
	public double obtenerPesoCompletoPaquetes() {
		double pesoTot=0;
		
		for (Paquete paquete:paquetesCamionTrailer) {
			 pesoTot+=paquete.getPeso();
		}
		
		 return pesoTot;
	}
	
	@Override
	public double obtenerVolCompletoPaquetes() {
		
	double volTot=0;
		
		for (Paquete paquete:paquetesCamionTrailer) {
			volTot+=paquete.getVol();
		}
		
		 return volTot;
	
	}
	@Override
	public void mostrarPaquetesCargados() {
	
		for (Paquete paq:paquetesCamionTrailer) {
			System.out.println("---------------------------");
			System.out.println("Paquetes de CAMION TRAILER");
			System.out.println("---------------------------");
			System.out.println("destino:"+paq.getDestino());
			System.out.println("peso:"+paq.getPeso());
			System.out.println("volumen:"+paq.getVol());
			System.out.println("Refri:"+paq.necesitaRefrigeracion());
			System.out.println("---------------------------");
		}
	}
	
	

}
