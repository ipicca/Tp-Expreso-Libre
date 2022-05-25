package expreso_Libre;
import java.util.ArrayList;
import java.util.List;

public class Deposito {
	
	private boolean conRefrigeracion;
	private double capacidadDeposito;
	private ArrayList<Paquete> Lpaquetes;	
	
	public Deposito(boolean conRefrigeracion,double capacidadDeposito) {
		this.Lpaquetes= new ArrayList <Paquete>();
		this.conRefrigeracion = conRefrigeracion;
		this.capacidadDeposito = capacidadDeposito;		
	}
	
	public boolean getRefrigferacion() {
		return this.conRefrigeracion;
	}
	
	public double getCapacidadDeposito() {
		return this.capacidadDeposito;
	}
	
	public double setCapacidadDeposito(double vol) {
		return this.capacidadDeposito-=vol;
	}
	
	public void agregarPaquetesAlDeposito(Paquete paquete) {
		if (this.capacidadDeposito < paquete.getVol()) { //Si la capacidad actual del depo no nos permite incorporarlo
			throw new RuntimeException("No se pudo agregar por falta de capacidad");
        }
		setCapacidadDeposito(paquete.getVol()); //Si nos permite, actualizamos la capacidad del depo
		Lpaquetes.add(paquete);					//Y lo agregamos
    }


}
