package BO;

public class Oferta {
	private float valor;
	private String description;
	private int idOferta;
	private int idGUser;
	private int idRequest;

	
	public Oferta(float valor,String description,int idRequest){
		this.valor = valor;
		this.description = description;
		this.idRequest = idRequest;
	}
	
	public Oferta(int idOferta, int idGUser,float valor, String description,int idRequest){
		this.valor = valor;
		this.description = description;
		this.idRequest = idRequest;
		this.idOferta = idOferta;
		this.idGUser = idGUser;
	}
	
	public float getValor() {
		return valor;
	}
	public void setValor(float valor) {
		this.valor = valor;
	}
	
	public String getDescription() {
		return description;
	}
	
	public void setDescription(String description) {
		this.description = description;
	}
	
	public int getIdOferta() {
		return idOferta;
	}
	
	public void setIdOferta(int idOferta) {
		this.idOferta = idOferta;
	}
	
	public int getIdGUser() {
		return idGUser;
	}
	
	public void setIdGUser(int idGUser) {
		this.idGUser = idGUser;
	}
	
	public int getIdRequest() {
		return idRequest;
	}
	
	public void setIdRequest(int idRequest) {
		this.idRequest = idRequest;
	}
		
	@Override
	public String toString(){
		return "idOferta: " + this.idOferta+
				"\nValor: " + this.valor+
				"\nidGUser: " + this.idGUser+ 
				"\nidRequest: " + this.idRequest;
	}
	
}
