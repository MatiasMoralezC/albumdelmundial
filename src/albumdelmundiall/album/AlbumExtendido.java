package albumdelmundiall.album;

import java.util.HashMap;
import java.util.Map;

import albumdelmundiall.figurita.FiguritaTop10;

public class AlbumExtendido extends AlbumTradicional {
	private Map<Integer, FiguritaTop10> figuritasTop10;
	//private String tipo = "Extendido";
	private String premio = "Pelota y viaje";	

	public AlbumExtendido(String[] clasificados, int lugares) {
		super(clasificados, lugares);
		this.figuritasTop10 = new HashMap<>();
	}

	public boolean sePegoFiguritaTop10(FiguritaTop10 figurita) {

		return figuritasTop10.containsValue(figurita);
	}

	public void pegarFiguritaTop10(FiguritaTop10 figurita) {
		
		if (!figuritasTop10.containsValue(figurita))
			figuritasTop10.put( figurita.getCodigo(),figurita);

	}

	public boolean seCompletoAlbum() {
		return super.seCompletoAlbum();
	}
	
	@Override
	public boolean contieneTop10() {
		return true;
	}
}
