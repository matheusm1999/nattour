package BO;

public class tourEmProgresso {
	private User usuario;
	private User guia;
	private Requisicao requisicao;
	private Oferta oferta;
	private int idTourProgresso;
	private int isTerminado;
	
	public tourEmProgresso(User usuario, User guia, Requisicao requisicao, Oferta oferta) {
		this.usuario = usuario;
		this.guia = guia;
		this.requisicao = requisicao;
		this.oferta = oferta;
		//this.idTourProgresso = idTourProgresso;
	}
	public User getUsuario() {
		return usuario;
	}
	public void setUsuario(User usuario) {
		this.usuario = usuario;
	}
	public User getGuia() {
		return guia;
	}
	public void setGuia(User guia) {
		this.guia = guia;
	}
	public Requisicao getRequisicao() {
		return requisicao;
	}
	public void setRequisicao(Requisicao requisicao) {
		this.requisicao = requisicao;
	}
	public Oferta getOferta() {
		return oferta;
	}
	public void setOferta(Oferta oferta) {
		this.oferta = oferta;
	}
	public int getIdTourProgresso() {
		return idTourProgresso;
	}
	public void setIdTourProgresso(int idTourProgresso) {
		this.idTourProgresso = idTourProgresso;
	}
	public int getIsTerminado() {
		return isTerminado;
	}
	public void setIsTerminado(int isTerminado) {
		this.isTerminado = isTerminado;
	}
	
	
	
}
