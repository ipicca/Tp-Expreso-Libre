package expreso_Libre;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;

public class Empresa {

	private String cuit;
	private String nombre;
	private double capacidadMaxDepositos;
	
	/*-----------Estructuras de DATOS---------------*/
	//Lista de los Transportes de la empresa.
    private HashMap<String,Transporte> LTransportes; //Clave:matricula, Valor: tipoTransporte.
    private LinkedList<Viaje> LDestinos; // Destinos cargados por la empresa
    private ArrayList<Deposito> depositos;
    private HashMap<String,String> LViajesAsignados; // Clave:matricula, Valor: Destino.

    
    /*------------------------------------------------*/
    
    // Constructor de la empresa.
	public Empresa(String cuit, String nombre, double capacidadMaxDepositos) {
		
		this.cuit = cuit;
		this.nombre = nombre;
		this.capacidadMaxDepositos = capacidadMaxDepositos;
		this.LTransportes= new HashMap<String,Transporte>();
		this.LDestinos= new LinkedList <Viaje>();
		this.LViajesAsignados=new HashMap<String,String>();
		
		this.depositos = new ArrayList<Deposito>();
		this.depositos.add(new Deposito (true,capacidadMaxDepositos));
		this.depositos.add(new Deposito (false,capacidadMaxDepositos));
	
	}
		
	 @Override
	  public String toString() {
	    return "Empresa: "+nombre+"| cuit:"+cuit;
	  }
	
	
	// Incorpora un nuevo destino y su distancia en km.
	// Es requisito previo, para poder asignar un destino a un transporte.
	// Si ya existe el destino se debe generar una excepción.
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
	

	
	// Los siguientes métodos agregan los tres tipos de transportes a la 
	// empresa, cada uno con sus atributos correspondientes. 
	// La matrícula funciona como identificador del transporte.
	public void agregarTrailer(String matricula, double cargaMax, double capacidad, boolean tieneRefrigeracion, double costoKm, double segCarga){
		
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
	
	// Se asigna un destino a un transporte dada su matrícula (el destino 
	// debe haber sido agregado previamente, con el método agregarDestino). 
	// Si el destino no está registrado, se debe generar una excepción.
	public void asignarDestino(String matricula, String destino)  {
			boolean hayDestino=false;
			Transporte transporte=this.LTransportes.get(matricula);
			
			for (Viaje dest:LDestinos) {
				// chequear su tienen paquetes, si no, no lo puede agregar
			
				if (dest.getDestino().equals(destino)) {
					
					if (asignarDesitnoAMegaTrailer(transporte,dest)) {
						LViajesAsignados.put(matricula, destino);
						hayDestino=true;
					}
					else if (asignarDestinoACamionTrailer( transporte, dest)) {
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
	
	public boolean incorporarPaquete(String destino, double peso, double volumen,boolean necesitaRefrigeracion) {

		
		Paquete paquete = new Paquete (destino, peso, volumen, necesitaRefrigeracion);
		
		boolean sePudoIncorporar=false;
	
		for (Deposito dep : this.depositos) {
	
			//System.out.println(":"+"D refri " + dep.getRefrigferacion() + " P refri "+ paquete.necesitaRefrigeracion());
		
            if ((dep.getRefrigferacion() == paquete.necesitaRefrigeracion()) && paquete.getVol()<dep.getCapacidadDeposito()) {
            	dep.agregarPaquetesAlDeposito(paquete);// se agrega a la lista de paquetes en la clase deposito
            	dep.setCapacidadDeposito(paquete.getVol());// si ingresa un paquete al deposito se le resta su capcidad
            	
            	sePudoIncorporar=true;
            	
            
            }
        	 
		}  
            
            
		return sePudoIncorporar;
	}
	
	
	// Dado un ID de un transporte se pide cargarlo con toda la mercadería 
	// posible, de acuerdo al destino del transporte. No se debe permitir 
	// la carga si está en viaje o si no tiene asignado un destino. 
	// Utilizar el depósito acorde para cargarlo. 
	// Devuelve un double con el volumen de los paquetes subidos 
	// al transporte.
	
	public double cargarTransporte(String matricula) {
		
		double volumenSubidos=0;
		
		Transporte transporte= LTransportes.get(matricula);
		String destinoAsignado= LViajesAsignados.get(matricula);//obtengo el destino de LViajesAsignados que 
																//se la asigno al transporte
		

		for (Deposito dep:depositos) {
				int i=0;
				while(i<dep.Lpaquetes.size()) {// recorro los paquetes dentro del deposito
					
					String dest=dep.Lpaquetes.get(i).getDestino();
					double peso=dep.Lpaquetes.get(i).getPeso();
					double vol=dep.Lpaquetes.get(i).getVol();
					boolean refri=dep.Lpaquetes.get(i).necesitaRefrigeracion();
					
					Paquete paquete = new Paquete(dest,peso,vol,refri);
					
					if (actoParaCarga(transporte, paquete, destinoAsignado)) {
						
								transporte.cargarPaqueteTransporte(paquete);
								volumenSubidos+=paquete.getVol();
								actualizarDatosCargaPaquete(dep, transporte,paquete);
							
						}
					i++;
				}
			}
						
		
		
		return volumenSubidos;
	}
	
	
	
	
	// Inicia el viaje del transporte identificado por la 
	// matrícula pasada por parámetro. 
	// En caso de no tener mercadería cargada o de ya estar en viaje 
	// se genera una excepción.
	public void iniciarViaje(String matricula) {
		
	}
	
	
	// Finaliza el viaje del transporte identificado por la 
	// matrícula pasada por parámetro.
	// El transporte vacía su carga y blanquea su destino, para poder 
	// ser vuelto a utilizar en otro viaje. 
	// Genera excepción si no está actualmente en viaje.
	public void finalizarViaje(String matricula) {
		
	}
	
	
	// Obtiene el costo de viaje del transporte identificado por la 
	// matrícula pasada por parámetro. 
	// Genera Excepción si el transporte no está en viaje.
	public double obtenerCostoViaje(String matricula) {
		return 0;
	}
	
	// Busca si hay algún transporte igual en tipo, destino y carga. 
	// En caso de que no se encuentre ninguno, se debe devolver null. 
	//String obtenerTransporteIgual(String matricula);
	public String obtenerTransporteIgual(String matricula) {
		return null;
	}
	
	//Metedos AUXILIARES

	
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
