package albumdelmundiall.album;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import albumdelmundiall.figurita.FiguritaTradicional;

public class Seleccion {
	private String nombre;
	private int lugares;
	private List<FiguritaTradicional> figuritas;
	

	public Seleccion(String nombre, int lugares) {
		this.nombre = nombre;
		this.lugares = lugares;
		this.figuritas = new ArrayList<>();
	}
	
	@Override 
	public String toString() {
		StringBuilder sb = new StringBuilder();
		Iterator<FiguritaTradicional> it = figuritas.iterator();
		
		sb.append("Figuritas obtenidas: ");
		sb.append("\n");
		
		while (it.hasNext()) {
			sb.append(it.next());
			sb.append("\n");
		}
		return sb.toString();
	}

	public boolean sePegoFigPais(FiguritaTradicional figurita) {

		if (figurita == null) {
			return true;
		}
		if (figuritas.size() == 0) {
			return false;
		}

		return figuritas.contains(figurita);
	}

	public boolean pegarFigPais(FiguritaTradicional figurita) { //boolean
		if (figurita == null) {
			throw new RuntimeException("figurita null");
		}
		if (!sePegoFigPais(figurita)) {
			figuritas.add(figurita);
			return true;
		}
		return false;
	}
 
	public boolean estaCompletoPais() {
		return figuritas.size() == lugares;
	}
	
	public String obtenerNombreSeleccion() {
		return nombre;
	}
}
