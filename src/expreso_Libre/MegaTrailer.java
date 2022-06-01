package expreso_Libre;

import java.util.LinkedList;

public class MegaTrailer extends Transporte { //MegaTrailer HEREDA de transporte.
	private boolean tieneRefrigeracion;
	private double costoKm;
	private double segCarga;
	private double costoFijo;
	private double costoComida;
	public LinkedList<Paquete> paquetesMegaTrailer;

	//Constructor de Flete.
	
	public MegaTrailer() {}
	public MegaTrailer(String matricula, double cargaMax, double capacidad, boolean tieneRefrigeracion, double costoKm,
			double segCarga, double costoFijo, double costoComida) {
		
		super(matricula, cargaMax, capacidad);
		this.costoKm=costoKm;
		this.segCarga = segCarga;
		this.costoFijo = costoFijo;
		this.costoComida = costoComida;
		paquetesMegaTrailer= new LinkedList<Paquete>();
	}

	/*-----------toString de MegaTrailer---------------*/
	@Override
	public String toString() {
		return "\n" +" * MegaTrailer: " + "\n" + "	Tiene refrigeracion: " + tieneRefrigeracion + "\n" + "	Su costo por kilometro es: " + costoKm + "\n" + 
	"	Tiene seguro de carga="+ segCarga +  "\n" + "	Su costo fijo es de: " + costoFijo +  "\n" + "	Su costo de comida es:" + costoComida +  "\n";
	}

	
	/*----------- Metodos abstractos a implementar ---------------*/
	
	@Override
	public  double consultarTarifa (double cantKm) { // SOBRECARGA O SOBREESCRITURA
		return cantKm*costoKm+segCarga + costoComida +costoFijo;
	}
	
	@Override
	public boolean tieneRefrigeracion() {
		return tieneRefrigeracion;
	}
	
	@Override
	 public void cargarPaqueteTransporte(Paquete paquete) {
		 paquetesMegaTrailer.add(paquete);
		this.setCapacidad(paquete.getVol()); //se le resta el vol actual del trasporte - paquete
		this.setCargaMax(paquete.getPeso()); //se le resta el peso actual del transporte
	}
	
	@Override
	public boolean tienePaquetes() {
		return paquetesMegaTrailer.size()>0;
	}

	@Override
	public boolean asignarDestinoTransporte(Viaje dest,Transporte transporte) {
		return dest.getKm()>500 && transporte instanceof MegaTrailer;
	}
	
	@Override
	public void vaciarCarga() {
		paquetesMegaTrailer.clear();
	}
	
	@Override
	public String tipoTransporte() {
		return "Mega Trailer";
	}
	
	@Override
	public void mostrarPaquetesCargados() {	
		if (paquetesMegaTrailer.size()==0) {
			System.out.println("---------------------------");
			System.out.println("Paquetes de MEGA TRAILER");
			System.out.println("---------------------------");
			System.out.println("-NO TIENE PAQUETES CARGADOS-");
			System.out.println("---------------------------");			
		}	
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
	
	/*----------- Metodos ---------------*/

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
	
	//---------------------------------------------------------------- FIN CLASE MEGATRAILER ----------------------------------------------------------------//	
	
}

