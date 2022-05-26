package expreso_Libre;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;


public class Empresa {

	private String cuit;
	private String nombre;
	private double capacidadMaxDepositos;
	
	/*-----------Estructuras de DATOS---------------*/
    private HashMap<String,Transporte> listasTransportes; 	//Lista de los Transportes de la empresa. Clave:matricula, Valor: tipoTransporte.
    private ArrayList<Paquete> paquetes;
    private LinkedList<Viaje> Ldestinos;
    private ArrayList<Deposito> depositos;
    private HashMap<String,String> LViajesAsignados; 		// Lista de viajes asignados a un transporte. Clave:matricula, Valor: Destino.
	private HashMap<String,Boolean> LTransportesEnViaje; 	// Lista de transportes que se encuentran en viaje. Clave:matricula, Valor: En viaje o no.
    /*------------------------------------------------*/
    
    // Constructor de la empresa.
	public Empresa(String cuit, String nombre, double capacidadMaxDepositos) {
		
		this.cuit = cuit;
		this.nombre = nombre;
		this.capacidadMaxDepositos = capacidadMaxDepositos;
		this.listasTransportes= new HashMap<String,Transporte>();
		this.paquetes= new ArrayList <Paquete>();
		this.Ldestinos= new LinkedList <Viaje>();	
		this.LViajesAsignados=new HashMap<String,String>();
		this.LTransportesEnViaje=new HashMap<String,Boolean>();
		this.depositos = new ArrayList<Deposito>();
		this.depositos.add(new Deposito (true,capacidadMaxDepositos));
		this.depositos.add(new Deposito (false,capacidadMaxDepositos));
		
	}
	
	// Incorpora un nuevo destino y su distancia en km.
	// Es requisito previo, para poder asignar un destino a un transporte.
	// Si ya existe el destino se debe generar una excepción.
	public void agregarDestino (String destino,int km) {
		Viaje nuevoDestino= new Viaje (destino,km);
		
		if (Ldestinos.isEmpty())//si la lista esta vacia
			Ldestinos.add(nuevoDestino);
		else {
			for (Viaje dest:Ldestinos) {
				if (dest.getDestino().equals(nuevoDestino.getDestino())
						&& dest.getKm()==nuevoDestino.getKm()) {
					
					throw new RuntimeException ("El destino: "+nuevoDestino.getDestino()+"|"
							+ nuevoDestino.getKm()+"km, ya existe");
					}
				}
			Ldestinos.add(nuevoDestino);
		}
		
	}
	
	
	// Los siguientes métodos agregan los tres tipos de transportes a la 
	// empresa, cada uno con sus atributos correspondientes. 
	// La matrícula funciona como identificador del transporte.
	public void agregarTrailer(String matricula, double cargaMax, 
			double capacidad, boolean tieneRefrigeracion, double costoKm, double segCarga){
		
		Transporte trailer=new CamionTrailer(matricula,cargaMax, capacidad, costoKm,
				tieneRefrigeracion, segCarga);
		
		listasTransportes.put(matricula, trailer);
		
	}
	
	public void agregarMegaTrailer(String matricula, double cargaMax, double capacidad,
			 boolean tieneRefrigeracion, double costoKm, double segCarga, double costoFijo, double costoComida){
	
		Transporte megatrailer= new MegaTrailer(matricula, cargaMax, capacidad, tieneRefrigeracion, costoKm,
				segCarga, costoFijo, costoComida);
		
		listasTransportes.put(matricula, megatrailer);
		
	}
	
	
	public void agregarFlete(String matricula, double cargaMax, double capacidad,
			 double costoKm, int cantAcompaniantes, double costoPorAcompaniante){
		
		Transporte flete= new Flete(matricula, cargaMax, capacidad,costoKm,cantAcompaniantes, 
				costoPorAcompaniante);
		
		listasTransportes.put(matricula, flete);
	}
	
	// Se asigna un destino a un transporte dada su matrícula (el destino 
	// debe haber sido agregado previamente, con el método agregarDestino). 
	// Si el destino no está registrado, se debe generar una excepción.
	public void asignarDestino(String matricula, String destino) {
		boolean hayDestino=false;
		Transporte transporte=this.listasTransportes.get(matricula);
		
		for (Viaje dest:Ldestinos) {
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
	
	
	// Se incorpora un paquete a algún depósito de la empresa.
	// Devuelve verdadero si se pudo incorporar, es decir, 
	// si el depósito acorde al paquete tiene suficiente espacio disponible.
	
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
	
	// Dado un ID de un transporte se pide cargarlo con toda la mercadería 
	// posible, de acuerdo al destino del transporte. 
	//No se debe permitir la carga si está en viaje o si no tiene asignado un destino. 
	// Utilizar el depósito acorde para cargarlo. 
	// Devuelve un double con el volumen de los paquetes subidos 
	// al transporte.
	public double cargarTransporte(String matricula) {
		return 0;
	}
	
	
	
	// Inicia el viaje del transporte identificado por la 
	// matrícula pasada por parámetro. 
	// En caso de no tener mercadería cargada o de ya estar en viaje 
	// se genera una excepción.
	public void iniciarViaje(String matricula) {	
		if (LTransportesEnViaje.containsKey(matricula)) {
			throw new RuntimeException("El transporte esta en viaje");
		}
		else if (listasTransportes.get(matricula).estaCargado()==false) { 
			throw new RuntimeException("El transporte no tiene mercaderia cargada");
		}
		else {
			LTransportesEnViaje.put(matricula, true);
		}
	}
	
	
	// Finaliza el viaje del transporte identificado por la 
	// matrícula pasada por parámetro.
	// El transporte vacía su carga y blanquea su destino, para poder 
	// ser vuelto a utilizar en otro viaje. 
	// Genera excepción si no está actualmente en viaje.
	public void finalizarViaje(String matricula) {
			LTransportesEnViaje.remove(matricula); //Finaliza el viaje

			
	}
	
	
	// Obtiene el costo de viaje del transporte identificado por la 
	// matrícula pasada por parámetro. 
	// Genera Excepción si el transporte no está en viaje.
	public double obtenerCostoViaje(String matricula) {
		return 0;
	}
	
	// Obtiene el costo de viaje del transporte identificado por la 
	// matrícula pasada por parámetro. 
	// Genera Excepción si el transporte no está en viaje.	
	public String obtenerTransporteIgual(String matricula) {
		return null;
	}

	
	
	
	
	
}
