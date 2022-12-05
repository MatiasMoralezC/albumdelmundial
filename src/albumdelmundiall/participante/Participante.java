package albumdelmundiall.participante;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import albumdelmundiall.figurita.FiguritaTop10;
import albumdelmundiall.figurita.FiguritaTradicional;
import albumdelmundiall.album.Album;
import albumdelmundiall.album.AlbumExtendido;
import albumdelmundiall.album.AlbumTradicional;
import albumdelmundiall.album.AlbumWeb;

public class Participante {
	private int dni;
	private String nombre;
	private Album album;
	private String tipoAlbum;
	private HashMap<Integer, FiguritaTradicional> figuritasTradicionales;
	
	public Participante(int dni, Album album, String nombre) {
		this.dni = dni;
		this.nombre = nombre;
		this.album = album;
		this.figuritasTradicionales = new HashMap<>();
	}

	public void cargarSobreDeFiguritas(List<FiguritaTradicional> sobre) {

		for (FiguritaTradicional f : sobre) {
			figuritasTradicionales.put(f.getCodigo(), f);
		}
	}

	public HashMap<Integer, FiguritaTradicional> pegarFiguritas() { //iterator
		HashMap<Integer, FiguritaTradicional> pegadas = new HashMap<>();
		
		Iterator<FiguritaTradicional> it = figuritasTradicionales.values().iterator();
		
		while (it.hasNext()) {
			FiguritaTradicional f = it.next();
			album.pegarFigurita(f);
			pegadas.put(f.getCodigo(), f);
		}

		return pegadas;
	
	}
	
	public int darCodigoFiguRepetida() {
		if (figuritasTradicionales.isEmpty()) {
			return -1;
		}
		return figuritasTradicionales.keySet().iterator().next();

	}
	
	public boolean tieneEsaFigurita(int cod) {
		if (cod == -1) {
			throw new RuntimeException("codigo invalido");
		}
		if (figuritasTradicionales.size() == 0) {
			return false;
		}

		if (figuritasTradicionales.get(cod) != null) {
			return true;
		}
		return false;
	}

	public boolean utilizoCodigo() {
		return ( (AlbumWeb) album).utilizoCodigo(); 
	}
	
	public FiguritaTradicional obtenerFiguritaTradicional(Integer codFiguritaTradicional) {
		return figuritasTradicionales.get(codFiguritaTradicional);
	}

	public boolean intercambiar(FiguritaTradicional f1, FiguritaTradicional f2) {
		if (f1 == null || f2 == null) return false;
		//1 es la que recibe intercambiar, 2 es la que albumdelmundial obtiene de buscar repetidas en participantes
		if (!tieneEsaFigurita(f1.getCodigo())) throw new RuntimeException("No posee esa figurita");
		if (tieneEsaFigurita(f2.getCodigo())) throw new RuntimeException("Ya posee esa figurita");
		
		int cod1 = f1.getCodigo();
		int cod2 = f2.getCodigo();
		
		if (cod1 < 1 || cod2 == -1) throw new RuntimeException("Figurita Invalida");
		
		if (f1.mismoOMenorValor(f2)) {
			figuritasTradicionales.remove(cod1);
			figuritasTradicionales.put(cod2, f2);
			return true;
		}
		return false;
	}
	
	public void usarSorteo() {
		if (!puedeParticiparEnSorteo()) {
			throw new RuntimeException ("No es posible sortear porque no es un album tradicional o extendido");
		}
		AlbumTradicional trad = (AlbumTradicional) album;
		
		if (trad.seLeSorteoPremio()) {
			throw new RuntimeException("Ya se le sorteo un premio");
		}

		trad.sortearPremio();
		
	}
	
	public boolean seUtilizoSorteo() {
		if (tipoAlbum.toLowerCase().contains("tradicional")) 
			return ((AlbumTradicional) album).seLeSorteoPremio();
		return false;
	}

	public boolean seCompletoAlbum() {
		return album.seCompletoAlbum();
	}

	public String obtenerPremio() {
		return album.obtenerPremio();
	}

	public boolean seCompletoSeleccion(String seleccion) {
		return album.seCompletoSeleccion(seleccion);
	}

	public String obtenerNombre() {
		return nombre;
	}
	
	public int obtenerDNI() {
		return dni;
	}

	public boolean tieneFiguritas() {
		return !figuritasTradicionales.isEmpty();
	}
	
	public void ingresarTipoAlbum(String a) {
		tipoAlbum = a;
	}
	
	public String tipoAlbum() {
		return tipoAlbum;
	}

	public boolean puedeParticiparEnSorteo() {
		return album.participaEnSorteo();
	}
	
	public boolean puedeComprarTop10() {
		return album.contieneTop10();
	}

	@Override
	public String toString() {
		return "(" + dni + ") " + nombre + ": " + tipoAlbum;
	}	
}
