package BO;

public class User {
	private String email;
	private String name;
	private String hash;
	private int userID;
	private int idRating;
	private int rating;
	private Cidade cidade;
	private String idFacebook;
	private String fotoFacebook;
	private int ddi;
	private int ddd;
	private int numeroTelefone;
	private int isGuide;
	private int isTourist;
	
	public User(){
		
	}
	
	public int getIsGuide() {
		return isGuide;
	}

	public void setIsGuide(int isGuide) {
		this.isGuide = isGuide;
	}

	public int getIsTourist() {
		return isTourist;
	}

	public void setIsTourist(int isTourist) {
		this.isTourist = isTourist;
	}

	public int getDdi() {
		return ddi;
	}

	public void setDdi(int ddi) {
		this.ddi = ddi;
	}

	public int getDdd() {
		return ddd;
	}

	public void setDdd(int ddd) {
		this.ddd = ddd;
	}

	public int getNumeroTelefone() {
		return numeroTelefone;
	}

	public void setNumeroTelefone(int numeroTelefone) {
		this.numeroTelefone = numeroTelefone;
	}

	
	public User(String email, String name, String hash, int userID) {
		this.email = email;
		this.name = name;
		this.hash = hash;
		this.userID = userID;
	}
	
	public User(String email, String name, String hash, Cidade cidade) {
		this.email = email;
		this.name = name;
		this.hash = hash;
		this.cidade = cidade;
	}
	
	public User(int idUsuario, String idFacebook, String fotoFacebook,Cidade cidade, String name){
		this.idFacebook  = idFacebook;
		this.userID = idUsuario;
		this.fotoFacebook = fotoFacebook;
		this.cidade = cidade;
		this.name = name;
	}
	
	public User(int idUsuario, String idFacebook, String fotoFacebook,Cidade cidade, String name, String email){
		this.idFacebook  = idFacebook;
		this.userID = idUsuario;
		this.fotoFacebook = fotoFacebook;
		this.cidade = cidade;
		this.name = name;
		this.email = email;
	}

	public User(String idFacebook, String fotoFacebook,String name,String emailFacebook){
		this.idFacebook = idFacebook;
		this.fotoFacebook = fotoFacebook;
		this.email = emailFacebook;
		this.name = name;
	}
	
	public int getIdRating() {
		return idRating;
	}

	public void setIdRating(int idRating) {
		this.idRating = idRating;
	}

	public int getRating() {
		return rating;
	}

	public void setRating(int rating) {
		this.rating = rating;
	}

	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getHash() {
		return hash;
	}
	public void setHash(String hash) {
		this.hash = hash;
	}
	public int getUserID() {
		return userID;
	}
	public void setUserID(int userID) {
		this.userID = userID;
	}

	public Cidade getCidade() {
		return cidade;
	}

	public void setCidade(Cidade cidade) {
		this.cidade = cidade;
	}

	public String getIdFacebook() {
		return idFacebook;
	}

	public void setIdFacebook(String idFacebook) {
		this.idFacebook = idFacebook;
	}

	public String getFotoFacebook() {
		return fotoFacebook;
	}

	public void setFotoFacebook(String fotoFacebook) {
		this.fotoFacebook = fotoFacebook;
	}
	
	@Override
	public String toString(){
		return "name: " + this.name +
				"\nemail: " + this.email +
				"\nid: " + this.userID;
	}
		
}