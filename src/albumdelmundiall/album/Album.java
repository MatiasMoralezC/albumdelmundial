package albumdelmundiall.album;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

import albumdelmundiall.figurita.FiguritaTradicional;


public abstract class Album {
	private int codigo;
	private Map<String, Seleccion> selecciones;
	private String[] clasificados;
	private int lugaresPorPais;

	
	public Album(String[] clasificados, int lugares) {
		this.codigo = getRandomCode();
		this.clasificados = clasificados;
		this.lugaresPorPais = lugares;
	}
	
	private Map<String, Seleccion> generarPaises() {
		Map<String, Seleccion> ret = new HashMap<>();
		for (String pais : clasificados) {
			Seleccion _pais = new Seleccion(pais, lugaresPorPais);
			ret.put(pais, _pais);
		}
		return ret;
	}


	public boolean seCompletoAlbum() {
		for (Map.Entry<String, Seleccion> seleccion : selecciones.entrySet()) {
			if(!seleccion.getValue().estaCompletoPais())
				return false;
		}
		return true;
	}
	
	public boolean sePegoFigurita(FiguritaTradicional figurita) { 
		Seleccion seleccion = selecciones.get(figurita.obtenerPais());
		return seleccion.sePegoFigPais(figurita);
	}

	public void pegarFigurita(FiguritaTradicional figu) {
		selecciones.get(figu.obtenerPais()).pegarFigPais(figu);
	}

	public boolean seCompletoSeleccion(String seleccion) {
		
		if (!selecciones.containsKey(seleccion)) {
			return false;
		}
		
		return selecciones.get(seleccion).estaCompletoPais();
	}

	public void obtenerSelecciones() {
		selecciones = generarPaises();
	}
	
	private Integer getRandomCode() {
		
        Random random = new Random();
        return random.nextInt();
	}
	
	public int obtenerCodigo() {
		return codigo;
	}

	public String toString() {
		StringBuilder sb = new StringBuilder();
		
		sb.append("--------------------------");
		sb.append("Selecciones: ");
		
		for(Seleccion pais : selecciones.values()) {
			sb.append(pais);
			sb.append(" - ");
		}
		
		return sb.toString();
	}

	public abstract boolean participaEnSorteo();

	public abstract String obtenerPremio();

	public abstract boolean contieneTop10();
}