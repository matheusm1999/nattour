package BO;

public class Pais {
	private String nomePais;
	private String siglaPais;
	private int idPais;
	
	public Pais(){
		
	}

		
	public Pais(String nomePais, String siglaPais, int idPais) {
		this.nomePais = nomePais;
		this.siglaPais = siglaPais;
		this.idPais = idPais;
	}

	public String getNomePais() {
		return nomePais;
	}

	public void setNomePais(String nomePais) {
		this.nomePais = nomePais;
	}

	public String getSiglaPais() {
		return siglaPais;
	}

	public void setSiglaPais(String siglaPais) {
		this.siglaPais = siglaPais;
	}

	public int getIdPais() {
		return idPais;
	}

	public void setIdPais(int idPais) {
		this.idPais = idPais;
	}
	
	

}
