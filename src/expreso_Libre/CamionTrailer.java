package expreso_Libre;

import java.util.LinkedList;

public class CamionTrailer extends Transporte  { //Camion trailer HEREDA de transporte.
	
	private boolean tieneRefrigeracion;
	private double segCarga;
	private double costoKm;
	public LinkedList<Paquete> paquetesCamionTrailer;
	
	/*-----------toString de CamionTrailer---------------*/
	@Override
	public String toString() {
		return "\n" + " * Camion Trailer: " + "\n" + "	Tiene refrigeracion: " + tieneRefrigeracion + "\n" + "	Su seguro de carga es: " + segCarga + 
				"\n" + "	Su costo por kilometro es: " + costoKm +"\n";
	}
	
	//Constructor del CamionTrailer.
	
	public CamionTrailer(String matricula, double cargaMax, double capacidad, boolean tieneRefrigeracion,double costoKm,
		double segCarga) {
		super(matricula, cargaMax, capacidad);
		this.costoKm=costoKm;
		this.tieneRefrigeracion = tieneRefrigeracion;
		this.segCarga = segCarga;
		paquetesCamionTrailer= new LinkedList <Paquete>();
	}
	
	
	/*----------- Metodos abstractos a implementar ---------------*/
	
	@Override
	public boolean tieneRefrigeracion() {
		return tieneRefrigeracion;
	}
	
	@Override
	 public void cargarPaqueteTransporte(Paquete paquete) {
		paquetesCamionTrailer.add(paquete);
		this.setCapacidad(paquete.getVol()); //se le resta el vol actual del trasporte - paquete
		this.setCargaMax(paquete.getPeso()); //se le resta el peso actual del transporte
	}
	
	@Override
	public boolean tienePaquetes() {
		return paquetesCamionTrailer.size()>0;
		
	}

	@Override
	public double consultarTarifa(double cantKm) { // SOBRECARGA O SOBREESCRITURA
		return cantKm*costoKm+segCarga;
	}

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
	public boolean asignarDestinoTransporte(Viaje dest,Transporte transporte) {
		return  dest.getKm()<500 && transporte instanceof CamionTrailer ;
	}
	
	@Override
	public void vaciarCarga() {
		paquetesCamionTrailer.clear();
	}
	
	@Override
	public String tipoTransporte() {
		return "Camion Trailer";
	}
	
	@Override
	public void mostrarPaquetesCargados() {	
		if (paquetesCamionTrailer.size()==0) {
			System.out.println("---------------------------");
			System.out.println("Paquetes de CAMION TRAILER");
			System.out.println("---------------------------");
			System.out.println("-NO TIENE PAQUETES CARGADOS-");
			System.out.println("---------------------------");	
		}
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
	
	//---------------------------------------------------------------- FIN CLASE CAMIONTRAILER ----------------------------------------------------------------//

}
