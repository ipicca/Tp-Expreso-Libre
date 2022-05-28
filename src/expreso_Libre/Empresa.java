package expreso_Libre;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;


public class Empresa {

	private String cuit;
	private String nombre;
	private double capacidadMaxDepositos;
	
	/*-----------Estructuras de DATOS---------------*/
    private HashMap<String,Transporte> LTransportes; 	//Lista de los Transportes de la empresa. Clave:matricula, Valor: tipoTransporte.
    private ArrayList<Paquete> paquetes;
    private LinkedList<Viaje> LDestinos;
    private ArrayList<Deposito> depositos;
    private HashMap<String,String> LViajesAsignados; 		// Lista de viajes asignados a un transporte. Clave:matricula, Valor: Destino.
	private HashMap<String,Boolean> LTransportesEnViaje; 	// Lista de transportes que se encuentran en viaje. Clave:matricula, Valor: En viaje o no.
    /*------------------------------------------------*/
    
    // Constructor de la empresa.
	public Empresa(String cuit, String nombre, double capacidadMaxDepositos) {
		
		this.cuit = cuit;
		this.nombre = nombre;
		this.capacidadMaxDepositos = capacidadMaxDepositos;
		this.LTransportes= new HashMap<String,Transporte>();
		this.paquetes= new ArrayList <Paquete>();
		this.LDestinos= new LinkedList <Viaje>();	
		this.LViajesAsignados=new HashMap<String,String>();
		this.LTransportesEnViaje=new HashMap<String,Boolean>();
		this.depositos = new ArrayList<Deposito>();
		this.depositos.add(new Deposito (true,capacidadMaxDepositos));
		this.depositos.add(new Deposito (false,capacidadMaxDepositos));
		
	}
	
	// Incorpora un nuevo destino y su distancia en km.
	// Es requisito previo, para poder asignar un destino a un transporte.
	// Si ya existe el destino se debe generar una excepci�n.
	public void agregarDestino (String destino,int km) {
		Viaje nuevoDestino= new Viaje (destino,km);
		
		if (LDestinos.isEmpty())//si la lista esta vacia
			LDestinos.add(nuevoDestino);
		else {
			for (Viaje dest:LDestinos) {
				if (dest.getDestino().equals(nuevoDestino.getDestino())
						&& dest.getKm()==nuevoDestino.getKm()) {
					
					throw new RuntimeException ("El destino: "+nuevoDestino.getDestino()+"|"
							+ nuevoDestino.getKm()+"km, ya existe");
					}
				}
			LDestinos.add(nuevoDestino);
		}
		
	}
	
	
	// Los siguientes m�todos agregan los tres tipos de transportes a la 
	// empresa, cada uno con sus atributos correspondientes. 
	// La matr�cula funciona como identificador del transporte.
	public void agregarTrailer(String matricula, double cargaMax, 
			double capacidad, boolean tieneRefrigeracion, double costoKm, double segCarga){
		
		Transporte trailer=new CamionTrailer(matricula,cargaMax, capacidad, tieneRefrigeracion,costoKm,segCarga);
		
		LTransportes.put(matricula, trailer);
		
	}
	
	public void agregarMegaTrailer(String matricula, double cargaMax, double capacidad,
			 boolean tieneRefrigeracion, double costoKm, double segCarga, double costoFijo, double costoComida){
	
		Transporte megatrailer= new MegaTrailer(matricula, cargaMax, capacidad, tieneRefrigeracion, costoKm,
				segCarga, costoFijo, costoComida);
		
		LTransportes.put(matricula, megatrailer);
		
	}
	
	
	public void agregarFlete(String matricula, double cargaMax, double capacidad,
			 double costoKm, int cantAcompaniantes, double costoPorAcompaniante){
		
		Transporte flete= new Flete(matricula, cargaMax, capacidad,costoKm,cantAcompaniantes, 
				costoPorAcompaniante);
		
		LTransportes.put(matricula, flete);
	}
	
	// Se asigna un destino a un transporte dada su matr�cula (el destino 
	// debe haber sido agregado previamente, con el m�todo agregarDestino). 
	// Si el destino no est� registrado, se debe generar una excepci�n.
	public void asignarDestino(String matricula, String destino) {
		boolean hayDestino=false;
		Transporte transporte=this.LTransportes.get(matricula);
		
		for (Viaje dest:LDestinos) {
			// chequear su tienen paquetes, si no, no lo puede agregar
		
			if (dest.getDestino().equals(destino)) {
				
				if (dest.getKm()>500 && transporte instanceof MegaTrailer) {
					LViajesAsignados.put(matricula, destino);
					hayDestino=true;
				}
				else if (dest.getKm()<500 && transporte instanceof CamionTrailer) {
					LViajesAsignados.put(matricula, destino);
					hayDestino=true;
				}
				else {
					  throw new RuntimeException("El transporte no sirve para este destino"); 
				}
			}
		}
		if (!hayDestino)
			throw new RuntimeException("El destino ingresado "+destino+ " no existe"); 
		}
	
	
	// Se incorpora un paquete a alg�n dep�sito de la empresa.
	// Devuelve verdadero si se pudo incorporar, es decir, 
	// si el dep�sito acorde al paquete tiene suficiente espacio disponible.
	
	public boolean incorporarPaquete(String destino, double peso, double volumen, 
			boolean necesitaRefrigeracion) {
		
		Paquete paquete = new Paquete (destino, peso, volumen, necesitaRefrigeracion);
		
		for (Deposito dep : this.depositos) {
			//System.out.println("D refri " + dep.getRefrigferacion() + " P refri "+ paquete.necesitaRefrigeracion()  );
            if (dep.getRefrigferacion() == paquete.necesitaRefrigeracion()) {
                    dep.agregarPaquetesAlDeposito(paquete); 
                }
            }
		return true;
	}
	
	// Dado un ID de un transporte se pide cargarlo con toda la mercader�a 
	// posible, de acuerdo al destino del transporte. 
	//No se debe permitir la carga si est� en viaje o si no tiene asignado un destino. 
	// Utilizar el dep�sito acorde para cargarlo. 
	// Devuelve un double con el volumen de los paquetes subidos 
	// al transporte.
	public double cargarTransporte(String matricula) {
		return 0;
	}
	
	
	
	// Inicia el viaje del transporte identificado por la 
	// matr�cula pasada por par�metro. 
	// En caso de no tener mercader�a cargada o de ya estar en viaje 
	// se genera una excepci�n.
	public void iniciarViaje(String matricula) {	
		if (LTransportesEnViaje.containsKey(matricula)) {
			throw new RuntimeException("El transporte esta en viaje");
		}
		else if (LTransportes.get(matricula).estaCargado()==false) { 
			throw new RuntimeException("El transporte no tiene mercaderia cargada");
		}
		else {
			LTransportesEnViaje.put(matricula, true);
		}
	}
	
	
	// Finaliza el viaje del transporte identificado por la 
	// matr�cula pasada por par�metro.
	// El transporte vac�a su carga y blanquea su destino, para poder 
	// ser vuelto a utilizar en otro viaje. 
	// Genera excepci�n si no est� actualmente en viaje.
	public void finalizarViaje(String matricula) {
			LTransportesEnViaje.remove(matricula); //Finaliza el viaje
			//TODO	
			
	}
	
	
	// Obtiene el costo de viaje del transporte identificado por la 
	// matr�cula pasada por par�metro. 
	// Genera Excepci�n si el transporte no est� en viaje.
	public double obtenerCostoViaje(String matricula) {
		return 0;
	}
	
	// Obtiene el costo de viaje del transporte identificado por la 
	// matr�cula pasada por par�metro. 
	// Genera Excepci�n si el transporte no est� en viaje.	
	public String obtenerTransporteIgual(String matricula) {
		return null;
	}
	
	

	//Metedos AUXILIARES
	private boolean estaRefrigerado(Transporte transporte, Paquete paquete){
		
		boolean resultado=(transporte.tieneRefrigeracion()== paquete.necesitaRefrigeracion());
	
		return  resultado;
	}
	
	private boolean actoParaCarga(Transporte transporte, Paquete paquete, String destinoAsignado){
		
		boolean refri=(transporte.tieneRefrigeracion()== paquete.necesitaRefrigeracion());
		boolean mismoDestino=paquete.getDestino().equals(destinoAsignado);
		boolean tieneEspacioCarga=transporte.tieneEspacioCarga();
		
		return  refri && mismoDestino && tieneEspacioCarga;
	}
	
	private void actualizarDatosCargaPaquete(Deposito dep, Transporte transporte,Paquete paquete) {

		dep.setCapacidadDeposito(-(paquete.getVol()));//Vuelvo a sumar el vol del paquete al deposito
		transporte.setCapacidad(paquete.getVol()); //se le resta el vol actual del trasporte - paquete
		transporte.setCargaMax(paquete.getPeso()); //sele resta el peso actual del transporte
		dep.retirarPaqueteDep(paquete);// lo borro del Lista de paquetes en el Deposito
	
	}
	
	public void mostrarViajesAsignadosTrasnporte() {
		System.out.println();
		System.out.println();
		System.out.println("Destinos asignados a los transportes");
	
		
		for (String key: LViajesAsignados.keySet()){  
			System.out.println(key+ " = " + LViajesAsignados.get(key));
		} 
	}
	
	public void mostrasDestinos() {
		for (Viaje dest:LDestinos) {
			System.out.println(dest.getDestino());
		}
	}
	
	public void mostrarDepositosCargados() {

		for(Deposito dep:depositos) {
			if (dep.getRefrigferacion()) {
				System.out.println("vol de dep con regri: "+dep.getCapacidadDeposito()+" "+dep.getRefrigferacion());
			}
			else {
				System.out.println("vol de dep SIN regri: "+dep.getCapacidadDeposito()+" "+dep.getRefrigferacion());
			}
		}
	}
	
	private boolean asignarDesitnoAMegaTrailer(Transporte transporte, Viaje dest) {
		return dest.getKm()>500 && transporte instanceof MegaTrailer;
	}
	
	private boolean asignarDestinoACamionTrailer(Transporte transporte, Viaje dest) {
		return dest.getKm()<500 && transporte instanceof CamionTrailer;
	}

	public void mostrarContenidoDelosDepositos() {
		for (Deposito dep: depositos) {
			dep.mostrarPaquetesDelDeposito();
		}
	}
	
	public void mostrarCargaTransporte(String matricula) {
		
		Transporte transporte= LTransportes.get(matricula);
		transporte.mostrarPaquetesCargados();
		
	}


	
	
	
	
	
}
