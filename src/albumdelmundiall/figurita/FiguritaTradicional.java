package albumdelmundiall.figurita;

public class FiguritaTradicional {
	private int cont;
	private Integer codigo;
	private String tipoFigurita;
	private int valor;
	private int numJugador;
	private String nombrePais;

	public FiguritaTradicional(String tipo, int numJugador, String nombrePais, int valor) {
		this.tipoFigurita = tipo;
		this.numJugador = numJugador;
		this.nombrePais = nombrePais;
		generarCodigo((numJugador + tipoFigurita.length())/3 + cont);
		this.valor = valor;
		cont++;
	}
	
	protected void generarCodigo(int c) {
		codigo = c;
	}

	public int getCodigo() {
		return codigo;
	}
	
	public int getNumJugador() {
		return numJugador;
	}

	public String getTipo() {
		return tipoFigurita;
	}

	public String getNombrePais() {
		return nombrePais;
	}

	@Override
	public boolean equals(Object obj) {
		if (obj == null) {
			return false;
		}
		if (!(obj instanceof FiguritaTradicional)) {
			return false;
		}

		FiguritaTradicional fig = (FiguritaTradicional) obj;

		return numJugador == fig.getNumJugador() && nombrePais.equals(fig.getNombrePais())
				&& tipoFigurita.equals(fig.getTipo());
	}
	
	public String obtenerPais() {
		return getNombrePais();
	}
	
	public int obtenerValor() {
		return valor;
	}

	public boolean mismoOMenorValor(FiguritaTradicional f2) {
		int val1 = this.obtenerValor();
		int val2 = f2.obtenerValor();

		if (getTipo().equals("top10")) {
			FiguritaTop10 figu1 = (FiguritaTop10) this;
			val1 *= figu1.queBalon().equals("oro") ? 1.20 : 1.10;
		}
		if (f2.getTipo().equals("top10")) {
			FiguritaTop10 figu2 = (FiguritaTop10) f2;
			val1 *= figu2.queBalon().equals("oro") ? 1.20 : 1.10;
		}
		return val1 >= val2;
	}
}
