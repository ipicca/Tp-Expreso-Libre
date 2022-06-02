package expreso_Libre;

import java.util.LinkedList;

public class Flete extends Transporte  { //Flete HEREDA de transporte.
	
	private double costoKm;
	private int cantAcompaniantes;
	private double costoPorAcompaniante;
	public LinkedList<Paquete> paquetesFlete;

	//Constructor de Flete.
	
	public Flete() {}
	public Flete(String matricula, double cargaMax, double capacidad,
			 double costoKm, int cantAcompaniantes, double costoPorAcompaniante) {
		super(matricula, cargaMax, capacidad);
		this.costoKm=costoKm;
		this.cantAcompaniantes = cantAcompaniantes;
		this.costoPorAcompaniante = costoPorAcompaniante;
		paquetesFlete= new LinkedList<Paquete>();
	}
	
	
	/*-----------toString de Fletes---------------*/
	@Override
	public String toString() {
		return "\n" + " * Fletes: " +"\n" + "	Tiene refrigeracion: " + tieneRefrigeracion() + "\n" + "	Su costo por kilometro es: " + costoKm +"\n" + "	Cantidad de acompañantes: " + cantAcompaniantes 
				+ "\n" + "	Su costo por acompañante es: " + costoPorAcompaniante + "\n";
		}
	
	
	/*----------- Metodos abstractos a implementar ---------------*/
	
	@Override
	public double consultarTarifa(double cantKm) { // SOBRECARGA O SOBREESCRITURA
		return (cantKm*costoKm) + (cantAcompaniantes * costoPorAcompaniante);
	}
	
	@Override
	public boolean tieneRefrigeracion() {		
		return false;
	}
	
	@Override
	 public void cargarPaqueteTransporte(Paquete paquete) {
		paquetesFlete.add(paquete);
		this.setCapacidad(paquete.getVol()); //se le resta el vol actual del trasporte - paquete
		this.setCargaMax(paquete.getPeso()); //se le resta el peso actual del transporte		
	}
	
	@Override
	public boolean tienePaquetes() {
		return paquetesFlete.size()>0;
	}

	@Override
	public void vaciarCarga() {
		paquetesFlete.clear();
	}
	
	@Override
	public boolean asignarDestinoTransporte(Viaje dest,Transporte transporte) {
		return dest.getKm() <100000000 && transporte instanceof Flete;
	}
	
	@Override
	public String tipoTransporte() {
		return "Flete";
	}
	
	@Override
	public void mostrarPaquetesCargados() {	
		if (paquetesFlete.size()==0) {
			System.out.println("---------------------------");
			System.out.println("Paquetes de FLETE");
			System.out.println("---------------------------");
			System.out.println("-NO TIENE PAQUETES CARGADOS-");
			System.out.println("---------------------------");	
		}		
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
	
	/*----------- Metodos ---------------*/
	
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
	
	//---------------------------------------------------------------- FIN CLASE FLETE ----------------------------------------------------------------//	
	
}


