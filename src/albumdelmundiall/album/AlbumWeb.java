package albumdelmundiall.album;

public class AlbumWeb extends Album {
    private Boolean utilizado;
    //private String tipo = "Web";
    private String premio = "Camiseta de la seleccion";

    public AlbumWeb(String[] paisesParticipantes, int lugaresPorPais) {
		super(paisesParticipantes, lugaresPorPais);
		this.utilizado = false;
	}

	public Boolean utilizoCodigo() {
    	return utilizado;
    }
    
	@Override
    public String obtenerPremio() {
    	return premio;
    }
    
    @Override
    public boolean participaEnSorteo() {
    	return false;
    }
    
    @Override
    public boolean contieneTop10() {
    	return false;
    }
}
