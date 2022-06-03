package expreso_Libre;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;

public class Empresa {
	
	private String cuit;
	private String nombre;
	private double capacidadMaxDepositos;
	
	/*-----------Estructuras de datos---------------*/
	
    private HashMap<String,Transporte> LTransportes; 	//Lista de transportes --> Clave:matricula, Valor: tipoTransporte.
    private LinkedList<Viaje> LDestinos; 				//Destinos cargados por la empresa.
    private ArrayList<Deposito> depositos;				//Depositos de la empresa.
    private HashMap<String,String> LViajesAsignados; 	//Lista de viajes asignados --> Clave:matricula, Valor: destino.
    
    /*------------------------------------------------*/
    
    //Constructor de la empresa.
    
	public Empresa(String cuit, String nombre, double capacidadMaxDepositos) {
		this.cuit = cuit;
		this.nombre = nombre;
		this.capacidadMaxDepositos = capacidadMaxDepositos;
		this.LTransportes= new HashMap<String,Transporte>();
		this.LDestinos= new LinkedList <Viaje>();
		this.LViajesAsignados=new HashMap<String,String>();	
		this.depositos = new ArrayList <Deposito>();
		this.depositos.add(new Deposito (true,capacidadMaxDepositos));	//Deposito apto para refrigeracion.
		this.depositos.add(new Deposito (false,capacidadMaxDepositos));	//Deposito no apto para refrigeracion.
	}
	
	/*-----------toString de empresa---------------*/
	@Override
	public String toString() {
		return "\n" + "Detalles de la Empresa." + "\n" + " - Su numero de CUIT es: " + cuit + "\n" + " - Su razón social: " + nombre + 
				"\n" +" - Capacidad máxima de sus depositos: " + capacidadMaxDepositos+"\n" + 
				" - La flota de la empresa se compone por: " +"\n" +
				LTransportes.toString().replace("[", "").replace("]", "").replace(",", "").replace("{", "").replace("}", "").replace("=", "--> Detalles del dominio: ") + "\n" + 
				" - La lista de destinos: " + LDestinos.toString().replace("]", "").replace("[", "").replace(",", " ||") + "\n" + 
				" - Sus depositos son: " + depositos.toString().replace("[", "").replace("]", "").replace(",", "") + "\n" +
				 " - Los viajes asignados al momento son: " + LViajesAsignados.toString().replace("{", "").replace("}", "").replace("=", "-->");
	}
	

	//------------------------------------------------------------------METODOS A IMPLEMENTAR------------------------------------------------------------------//
	
	 //------------------------------------------------------------------ Agregar destinos ------------------------------------------------------------------//
	
	// Incorpora un nuevo destino y su distancia en km.
	// Es requisito previo, para poder asignar un destino a un transporte.
	// Si ya existe el destino se debe generar una excepción.
	
	public void agregarDestino (String destino,int km) {
		
		Viaje nuevoDestino= new Viaje (destino,km);	
		
		if (LDestinos.isEmpty())
			LDestinos.add(nuevoDestino);
		else {
			for (Viaje dest:LDestinos) {
				if ((dest.getDestino().equals(nuevoDestino.getDestino()))) {
					throw new RuntimeException ("El destino: "+nuevoDestino.getDestino()+"|"
							+ nuevoDestino.getKm()+"km, ya existe");
					}
				}
			LDestinos.add(nuevoDestino);
		}
	}

	//------------------------------------------------------------------Agregar transportes ------------------------------------------------------------------//	
	
	// Los siguientes métodos agregan los tres tipos de transportes a la empresa, cada uno con sus atributos correspondientes. 
	// La matrícula funciona como identificador del transporte.
	
	public void agregarTrailer(String matricula, double cargaMax, 
			double capacidad, boolean tieneRefrigeracion, double costoKm, double segCarga){
		
		Transporte trailer=new CamionTrailer(matricula,cargaMax, capacidad,tieneRefrigeracion,costoKm, segCarga);
		
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
	
	//------------------------------------------------------------------Asignacion de destinos ------------------------------------------------------------------//
	
	// Se asigna un destino a un transporte dada su matrícula (el destino debe haber sido agregado previamente, con el método agregarDestino). 
	// Si el destino no está registrado, se debe generar una excepción.
	
	public void asignarDestino(String matricula, String destino)  {
		
			boolean hayDestino=false;
			
			Transporte transporte=this.LTransportes.get(matricula);
			
			if (transporte.tienePaquetes())
					throw new RuntimeException("el transporte "+transporte.tipoTransporte() +" ya tiene paquetes cargados"); 
			for (Viaje dest:LDestinos) {
				if (dest.getDestino().equals(destino)) {
				 if(transporte.asignarDestinoTransporte(dest, transporte)) {
						LViajesAsignados.put(matricula, destino);
						hayDestino=true; 
					}
				}
			}
			if (!hayDestino)
				throw new RuntimeException("El destino ingresado "+destino+ " no existe"); 
		}
	
	//------------------------------------------------------------------ Agregar paquetes ------------------------------------------------------------------//
	
	// Se incorpora un paquete a algún depósito de la empresa.
	// Devuelve verdadero si se pudo incorporar, es decir, 
	// Si el depósito acorde al paquete tiene suficiente espacio disponible.
	
	public boolean incorporarPaquete(String destino, double peso, double volumen,boolean necesitaRefrigeracion) {
		
		Paquete paquete = new Paquete (destino, peso, volumen, necesitaRefrigeracion);
		
		boolean sePudoIncorporar=false;
		
		for (Deposito dep : this.depositos) {
            if (dep.chequearPaquete(paquete)) {    
            	dep.agregarPaquetesAlDeposito(paquete);// Se agrega a la lista de paquetes en la clase deposito
            	sePudoIncorporar=true;   	
            }        	 
		}                      
		return sePudoIncorporar;
	}
	
	//------------------------------------------------------------------ Cargar un transporte ------------------------------------------------------------------//
	
	// Dado un ID de un transporte se pide cargarlo con toda la mercadería posible, de acuerdo al destino del transporte. 
	//No se debe permitir la carga si está en viaje o si no tiene asignado un destino. 
	// Utilizar el depósito acorde para cargarlo. 
	// Devuelve un double con el volumen de los paquetes subidos al transporte.
	
	public double cargarTransporte(String matricula) {
		
		Transporte transporte= LTransportes.get(matricula);
		
		String destinoAsignado= LViajesAsignados.get(matricula);//obtengo el destino de LViajesAsignados que 
		
		double volumenSubidos=0;
		
		if (transporte.estado()==false) {// estado disponible=false, entonces tiene un viaje iniciado
			throw new RuntimeException("No se puede realizar la carga:"+transporte.tipoTransporte()+" ya inicio un viaje"); 
			}
		if (destinoAsignado==null) {
			throw new RuntimeException("El transporte:"+transporte.tipoTransporte()+" no tiene ninguna destino asignado"); 
			}		
		
		for (Deposito dep: depositos) {
				Iterator<Paquete> it = dep.Lpaquetes.iterator();
				if (dep.actoParaCarga(transporte)){// chequea si tiene refri y tiene espacio el transporte
					while(it.hasNext() ) {
						Paquete p = it.next(); 
						if (p.getDestino().equals(destinoAsignado)) {// paquete tiene el mismo destino que el que se 							
						transporte.cargarPaqueteTransporte(p);// le asigno al transporte lo carga
						volumenSubidos += p.getVol();
						dep.setCapacidadDeposito(-p.getVol());//Vuelvo a sumar el vol del paquete al deposito
						it.remove(); // una vez que lo encuentra, lo elimina del deposito
						}		
					}
				}
			}
		return volumenSubidos;
	}
	
	//------------------------------------------------------------------ Iniciar un viaje ------------------------------------------------------------------//	
	
	// Inicia el viaje del transporte identificado por la matrícula pasada por parámetro. 
	// En caso de no tener mercadería cargada o de ya estar en viaje se genera una excepción.
	
	public void iniciarViaje(String matricula) {
		
		Transporte transporte= LTransportes.get(matricula);
		
		String destinoAsignado= LViajesAsignados.get(matricula);
		
		if (transporte.estado()==false && transporte.enViaje()==false) 
			throw new RuntimeException("El transporte:"+transporte.tipoTransporte()+" ya inicio un viaje"); 		
		if(!(transporte.tienePaquetes()))
			throw new RuntimeException("El transporte:"+transporte.tipoTransporte()+" no puede iniciar el viaje, porque no tiene paquetes cargados"); 		
		if (destinoAsignado==null) {
			throw new RuntimeException("El transporte:"+transporte.tipoTransporte()+" no tiene ninguna destino asignado"); 
		}

		transporte.cambiarViaje();	// estado = true; -->en viaje.
		
		transporte.cambiarEstado();	// disponibilidad = false

	}
	
	//------------------------------------------------------------------ Finaliza un viaje ------------------------------------------------------------------//	
	
	// Finaliza el viaje del transporte identificado por la matrícula pasada por parámetro.
	// El transporte vacía su carga y blanquea su destino, para poder ser vuelto a utilizar en otro viaje. 
	// Genera excepción si no está actualmente en viaje.
	
	public void finalizarViaje(String matricula) {
		
		Transporte transporte= LTransportes.get(matricula);	
		
		if(!transporte.enViaje()) // si no esta en viaje  
			throw new RuntimeException("5 El transporte actualmente no se encuentra en viaje"); 
				
		transporte.actualizarDatosDelTransporte();// reincopora su carga y vacia sus paquetes
		
		LViajesAsignados.remove(matricula);// se blanquea el destino de la lista de LviajesAsignados				
	}
	
	//------------------------------------------------------------------ Obtener costo de viaje ------------------------------------------------------------------//
	
	// Obtiene el costo de viaje del transporte identificado por la matrícula pasada por parametro. 
	// Genera excepción si el transporte no está en viaje.
	
	public double obtenerCostoViaje(String matricula) {
		
		Transporte transporte= LTransportes.get(matricula);
		
		String destinoAsignado= LViajesAsignados.get(matricula);
		
		double cantKm=0;
		
		if(transporte.enViaje()==false) // si no esta disponible y esta en viaje
			throw new RuntimeException("2 El transporte actualmente no se encuentra en viaje"+transporte.tipoTransporte()); 		

		for (Viaje dest:LDestinos) {
			if (dest.getDestino().equals(destinoAsignado))
				cantKm=dest.getKm();
		}
		return transporte.consultarTarifa(cantKm); //obtengo los km del destino asingado al transporte
	}
	
	//---------------------------------------------------------------- Obtener transporte igual ----------------------------------------------------------------//	
	
	// Busca si hay algún transporte igual en tipo, destino y carga. 
	// En caso de que no se encuentre ninguno, se debe devolver null. 
	
public String obtenerTransporteIgual(String matricula) {
		
		String matriculaIgual = null;
		Transporte trans2=LTransportes.get(matricula);
		String destAsigT2= LViajesAsignados.get(matricula);

		
		for(String elem : LTransportes.keySet()) { 								//Iteramos la lista de transporte con las keys --> matriculas.
			
			Transporte trans1=LTransportes.get(elem);
			String destAsigT1= LViajesAsignados.get(elem);
			boolean ambosConMismoDest= destAsigT1.equals(destAsigT2);
			
			if(trans1.equals(trans2) && ambosConMismoDest ){					//Y comparamos con el .get de la matricula que nos pasan por parametro.
				matriculaIgual = LTransportes.get(elem).getMatricula();		  	//Si entra al if es porque hay un igual, entonces cargamos la matricula
				return matriculaIgual;											//del objetoTransporte en la variable a retornar.
			}										  							//Retornamos entonces la matricula.
			
		}
		
		System.out.println("No existe un transporte igual a " + matricula); 	//Si no existe un igual, se informa y se pasa la matricula.
		return null;															//Retornamos null ya que nunca se sobreescribe.
	}
	

	//---------------------------------------------------------------- FIN CLASE EMPRESA ----------------------------------------------------------------//
	
	
	/*
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
	
	public void mostrarContenidoDelosDepositos() {
		for (Deposito dep: depositos) {
			dep.mostrarPaquetesDelDeposito();
		}
	}
	
	public void mostrarCargaTransporte(String matricula) {
		Transporte transporte= LTransportes.get(matricula);
		transporte.mostrarPaquetesCargados();
	}
	
	void verDatosTrans(String matricula) {
		Transporte trans= LTransportes.get(matricula);
		trans.datosTransporte();
	}
	*/
	

}
