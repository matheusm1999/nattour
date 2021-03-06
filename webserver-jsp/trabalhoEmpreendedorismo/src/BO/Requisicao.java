package BO;

import java.util.ArrayList;

public class Requisicao {
	private String title;
	private String description;
	private String startsAt;
	private String endsAt;
	private float minPrice;
	private float maxPrice;
	private int idTuser;
	private int idCity;
	private int idRequest;
	private String complement;
	private Cidade cidade;
	private ArrayList<Tag> tags;
	private User user;
	private String horarioInicio;
	private int isPago;
	
	private String horarioFim;

	public Requisicao(){
		
	}
	
	//Deixa apenas as requisi��es iguais
	//Sa�da: Requisi��es que n�o tem o id repetido
	public ArrayList<Requisicao> comparaIDReequisicoes(ArrayList<Requisicao> requisicoes){
		ArrayList<Requisicao> novasRequisicoes = new ArrayList<>();
		novasRequisicoes.add(requisicoes.get(0));
		int igual = 0; //0 se for igual
		
		for(int i = 1;i < requisicoes.size();i++){
			//Pego o id de uma requisi��o
			int idRequisicao = requisicoes.get(i).getIdRequest();
			
			//Comparo esse id com todos os outros na lista de novasRequisicoes
			for(int j = 0; j < novasRequisicoes.size(); j++){
				if(idRequisicao == novasRequisicoes.get(j).getIdRequest())
					igual++;
			}
			if(igual == 0) //se n�o tiverem ids iguais...
				novasRequisicoes.add(requisicoes.get(i));
		igual = 0;
		}
		return novasRequisicoes;
	}
	
	public Requisicao(String title, String description, String startsAt, String endsAt, float minPrice, float maxPrice,int idTuser, int idCity, String complement,Cidade cidade) {
		this.title = title;
		this.description = description;
		this.startsAt = startsAt;
		this.endsAt = endsAt;
		this.minPrice = minPrice;
		this.maxPrice = maxPrice;
		this.idTuser = idTuser;
		this.idCity = idCity;
		this.complement = complement;
		this.cidade = cidade;
	}

	public Requisicao(int idRequest,String title, String description, String startsAt, String endsAt,String complement,Cidade cidade,int idTUser) {
		this.idRequest = idRequest;
		this.title = title;
		this.description = description;
		this.startsAt = startsAt;
		this.endsAt = endsAt;
		this.complement = complement;
		this.cidade = cidade;
		
		this.idTuser = idTUser;
		this.minPrice = 0;
		this.maxPrice = 0;
	}

	public Requisicao(String title, String description, String startsAt, String endsAt,String complement,Cidade cidade, int idTUser,ArrayList<Tag> tags) {
		this.title = title;
		this.description = description;
		this.startsAt = startsAt;
		this.endsAt = endsAt;
		this.complement = complement;
		this.cidade = cidade;
		this.tags = tags;
		
		this.idTuser = idTUser;
		this.minPrice = 0;
		this.maxPrice = 0;
		
	}


	public String getHorarioInicio() {
		return horarioInicio;
	}

	public void setHorarioInicio(String horarioInicio) {
		this.horarioInicio = horarioInicio;
	}

	public String getHorarioFim() {
		return horarioFim;
	}

	public void setHorarioFim(String horarioFim) {
		this.horarioFim = horarioFim;
	}

	
	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}
	
	public ArrayList<Tag> getTags() {
		return tags;
	}

	public void setTags(ArrayList<Tag> tags) {
		this.tags = tags;
	}

	public Cidade getCidade() {
		return cidade;
	}
	
	public void setCidade(Cidade cidade) {
		this.cidade = cidade;
	}


	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getStartsAt() {
		return startsAt;
	}

	public void setStartsAt(String startsAt) {
		this.startsAt = startsAt;
	}

	public String getEndsAt() {
		return endsAt;
	}

	public void setEndsAt(String endsAt) {
		this.endsAt = endsAt;
	}

	public float getMinPrice() {
		return minPrice;
	}

	public void setMinPrice(float minPrice) {
		this.minPrice = minPrice;
	}

	public float getMaxPrice() {
		return maxPrice;
	}

	public void setMaxPrice(float maxPrice) {
		this.maxPrice = maxPrice;
	}

	public int getIdTuser() {
		return idTuser;
	}

	public void setIdTuser(int idTuser) {
		this.idTuser = idTuser;
	}

	public int getIdCity() {
		return idCity;
	}

	public void setIdCity(int idCity) {
		this.idCity = idCity;
	}

	public String getComplement() {
		return complement;
	}

	public void setComplement(String complement) {
		this.complement = complement;
	}
	
	public int getIdRequest() {
		return idRequest;
	}

	public void setIdRequest(int idRequest) {
		this.idRequest = idRequest;
	}
	
	@Override
	public String toString() {
		return "Title: " + this.title +
				"\nDescription: " + this.description +
				"\nStartsAt: " + this.startsAt + 
				"\nEndsAt: " + this.endsAt + 
				"\nminPrice: " + this.minPrice + 
				"\nmaxprice: " + this.maxPrice + 
				"\nidTuser: " + this.idTuser +
				"\nidCity" + this.idCity + 
				"\nComplement" + this.complement;
	}

	public int getIsPago() {
		return isPago;
	}

	public void setIsPago(int isPago) {
		this.isPago = isPago;
	}

}
