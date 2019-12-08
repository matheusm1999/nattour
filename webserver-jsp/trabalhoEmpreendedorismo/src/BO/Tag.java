package BO;

public class Tag {
	private String nome;
	
	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	private int id;
	
	public Tag(){
		
	}
	
	public Tag(String nome){
		this.nome = nome;
	}
	
	public Tag(int id){
		this.id = id;
	}
	
	public Tag(int id, String nome){
		this.nome = nome;
		this.id = id;
	}

	@Override
	public String toString(){
		return "Tag: " + this.nome +
				"\nID:" + this.id;
	}
}
