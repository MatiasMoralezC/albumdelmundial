package albumdelmundiall.figurita;

public class FiguritaTop10 extends FiguritaTradicional {
	private int contT;
	private String mundial;
	private String balon;

	public FiguritaTop10(String nombrePais, int numJugador, int valor, String paisSede, String balon) {
		super("Top10", numJugador, nombrePais, valor);
		this.mundial = paisSede;
		this.balon = balon;
		this.generarCodigo((numJugador + balon.length())/3 + contT);
		contT++;
	}

	@Override
	public boolean equals(Object obj) {
		
		if (obj == null || !(obj instanceof FiguritaTop10)) return false;
		
		FiguritaTop10 fig = (FiguritaTop10) obj;

		return super.equals((FiguritaTradicional) obj) 
				&& mundial.equals(fig.obtenerPais()) 
				&& balon.equals(fig.queBalon()
		);
	}
	
	public String queBalon() {
		return this.balon;
	}
}
