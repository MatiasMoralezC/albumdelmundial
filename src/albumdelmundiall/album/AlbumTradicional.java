package albumdelmundiall.album;

public class AlbumTradicional extends Album {

    private boolean sorteoPremio;
    //private String tipo = "Tradicional";
    private String premio = "Pelota"; 

    public AlbumTradicional(String[] clasificados, int lugares) {
		super(clasificados, lugares);
		this.sorteoPremio = false;
	}

	public void sortearPremio() {
    	if (this.sorteoPremio) {
    		throw new RuntimeException("Premio ya sorteado");
    	}
    	this.sorteoPremio = true;
    }
    
    public boolean seLeSorteoPremio() {
    	return this.sorteoPremio;
    }
    
    @Override
    public String obtenerPremio() {
		return premio;
	}
    
    @Override
    public boolean participaEnSorteo() {
    	return true;
    }
    
    @Override
    public boolean contieneTop10() {
    	return false;
    }
}
