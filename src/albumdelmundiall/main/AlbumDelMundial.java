package albumdelmundiall.main;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

import albumdelmundiall.album.Album;
import albumdelmundiall.figurita.FiguritaTop10;
import albumdelmundiall.figurita.FiguritaTradicional;
import albumdelmundiall.participante.Participante;

public class AlbumDelMundial implements IAlbumDelMundial {

	private Random random;
	private Map<Integer, Participante> participantes;
	private Fabrica fabrica;

	public AlbumDelMundial() {
		fabrica = new Fabrica();
		participantes = new HashMap<>();
		random = new Random();
	}

	@Override
	public int registrarParticipante(int dni, String nombre, String tipoAlbum) {

		if (participantes.containsKey(dni)) {
			throw new RuntimeException("Participante ya se encuentra registrado");

		} else if (!tipoAlbum.equals("Web") && !tipoAlbum.equals("Tradicional") && !tipoAlbum.equals("Extendido")) {
			throw new RuntimeException("Ha ingresado un tipo de álbum incorrecto");
		}
		if (dni < 0) {
			throw new RuntimeException("Ha ingresado un tipo de dni erróneo");
		}
		if (nombre == null)
			throw new RuntimeException("Ha ingresado un nombre inválido");

		Album album;

		if (tipoAlbum.equals("Web")) {
			album = fabrica.crearAlbumWeb();
		} else if (tipoAlbum.equals("Tradicional")) {
			album = fabrica.crearAlbumTradicional();
		} else {
			album = fabrica.crearAlbumExtendido();
		}

		album.obtenerSelecciones(); 

		Participante participante = new Participante(dni, album, nombre);
		participante.ingresarTipoAlbum(tipoAlbum);

		participantes.put(participante.obtenerDNI(), participante);

		return album.obtenerCodigo();
	}

	@Override
	public void comprarFiguritas(int dni) {
		Participante participante = buscarParticipante(dni);

		participante.cargarSobreDeFiguritas(fabrica.generarSobre(4));
	}

	@Override
	public void comprarFiguritasTop10(int dni) {
		Participante participante = buscarParticipante(dni);
		if (!participante.puedeComprarTop10()) {
			throw new RuntimeException("El participante no cuenta con un Álbum Extendido");
		}
		
		participante.cargarSobreDeFiguritas(fabrica.generarSobreTop10(4));
	}


	@Override
	public void comprarFiguritasConCodigoPromocional(int dni) {

		Participante participante = buscarParticipante(dni);

		participante.utilizoCodigo();
		participante.cargarSobreDeFiguritas(fabrica.generarSobre(4));

	}

	public List<String> pegarFiguritas(int dni) {
			
		List<String> fFinales = new ArrayList<>();
		participantes.get(dni).pegarFiguritas().forEach((codigo, figu) -> {
			fFinales.add(figu.toString());
		});

		return fFinales;
	}

	@Override
	public boolean llenoAlbum(int dni) {
		Participante participante = buscarParticipante(dni);

		return participante.seCompletoAlbum();
	}

	@Override
	public String aplicarSorteoInstantaneo(int dni) {
		Participante participante = buscarParticipante(dni);
		
		int numSorteo = random.nextInt(3);
		participante.usarSorteo();
		return fabrica.sortearPremio(numSorteo);		
	}

	@Override
	public int buscarFiguritaRepetida(int dni) {
		Participante p = buscarParticipante(dni);

		return p.darCodigoFiguRepetida();
	}

	@Override
	public boolean intercambiar(int dni, int codFigurita) { //
		Participante p = buscarParticipante(dni);
		//valor de la figurita a recibir es menor o igual a mi figu.

		for (Participante part : participantes.values()) {
			if (!p.equals(part)) {
				int otroC = buscarFiguritaRepetida(part.obtenerDNI());
				if (p.intercambiar(p.obtenerFiguritaTradicional(codFigurita), part.obtenerFiguritaTradicional(otroC))) {
					return true;
				}
			}
		}
		return false;
	}

	private void existeParticipante(int dni) { //
		if (!participantes.containsKey(dni)) {
			throw new RuntimeException("No se encuentra registrado");
		}
	}
	
	private Participante buscarParticipante(int dni) {
		existeParticipante(dni);
		return participantes.get(dni);
	}

	@Override
	public boolean intercambiarUnaFiguritaRepetida(int dni) {
		buscarParticipante(dni);

		int codFig = buscarFiguritaRepetida(dni);
		return intercambiar(dni, codFig);
	}

	@Override
	public String darNombre(int dni) {
		Participante participante = buscarParticipante(dni);
		return participante.obtenerNombre();
	}

	@Override
	public String darPremio(int dni) {
		Participante p = buscarParticipante(dni);
		if (!p.seCompletoAlbum()) {
			throw new RuntimeException("Todavia no completo el album");
		}

		return p.obtenerPremio();
	}

	@Override
	public String listadoDeGanadores() { //
		StringBuilder sb = new StringBuilder();
		for (Participante p : participantes.values()) {
			if (p.seCompletoAlbum()) {
				sb.append("- (");
				sb.append(p.obtenerDNI());
				sb.append(") ");
				sb.append(p.obtenerNombre());
				sb.append(": ");
				sb.append(p.obtenerPremio());
				sb.append("\n");
			}
		}
		return sb.toString();
	}

	@Override
	public List<String> participantesQueCompletaronElPais(String nombrePais) {
		List<String> completaronPais = new ArrayList<>();
		
		for (Participante p : participantes.values()) {
			if (p.seCompletoSeleccion(nombrePais)) {
				completaronPais.add(p.toString());
			}
		}
		return completaronPais;
	}
	
	@Override 
	public String toString() {
		
		StringBuilder sb = new StringBuilder();
		
		int count = 0;
		sb.append("Participantes totales ");
		sb.append(participantes.size());
		sb.append("\n----------------------");
		sb.append("\nGanadores totales: ");
		for (Participante  p : participantes.values()) {
			if (p.seCompletoAlbum()) {
				count++;
			}
		}
		sb.append(count);
		
		return sb.toString();
	}
}
