package Servicos;


import java.sql.Connection;
import java.sql.SQLException;

import BD.Conexao;
import BO.Conta;
import BO.User;
import DAO.enderecoDAO;
import DAO.geralDAO;
import DAO.userDAO;
import facebook4j.Facebook;
import facebook4j.FacebookException;
import facebook4j.FacebookFactory;
import facebook4j.auth.AccessToken;

public class loginServicos {
	public loginServicos(){
		
	}

	
	public void registrarConta(User user){
		Connection connection = Conexao.getConnection();
		userDAO ud = new userDAO(connection); 
		geralDAO gd = new geralDAO(connection);
		enderecoDAO ed = new enderecoDAO(connection);
		
		int chave;
		try {
			int idRating = gd.insertQualquer("INSERT INTO rating (total,number,average) VALUES (0,0,5)");
			user.setIdRating(idRating);
			//Recuperando a cidade no banco de dados
			System.out.println("Cidade: " + user.getCidade().getNome());
			int idCidade = ed.selectRetornaChave("SELECT id FROM city WHERE name = '" + user.getCidade().getNome() + "'");
			user.getCidade().setIdCidade(idCidade);
						
			//Recuperando o id e nome da uf no banco de dados, associado ao id da cidade
			int idUf = ed.selectRetornaChave("SELECT idState FROM city WHERE name = '" + user.getCidade().getNome() + "'");
			user.getCidade().getUf().setIdUf(idUf);
			System.out.println("idUff: " + idUf);
			String nomeUf = ed.selectRetornaString("SELECT name FROM state where id = " + idUf);
			user.getCidade().getUf().setNomeUf(nomeUf);
			//System.out.println("NullPointer??");
			
			//Recuperando o id e nome do pais no banco de dados, associado ao id da uf (que est� associado ao id da cidade)
			int idPais = ed.selectRetornaChave("SELECT id FROM country WHERE id = " + idUf);
			user.getCidade().getUf().getPais().setIdPais(idPais); //adicona o id no pais
			//System.out.println("Null Pointer??");
			String nomePais = ed.selectRetornaString("SELECT name FROM country WHERE id = " + idPais);
			user.getCidade().getUf().getPais().setNomePais(nomePais);
			
			ud.insertUser(user);
			
			
		} catch (SQLException e) {
			System.out.println("Erro ao realizar a inser��o do usu�rio");
			e.printStackTrace();
		}
		
	}

	//Loga pelo facebook, entretanto j� tenho que saber o token do meu usu�rio
	public void loginFacebook(){
		Facebook f = new FacebookFactory().getInstance();
		String appId = "457529064901252";
		String appSecret = "";
		String permissions = "";
		String token = "EAAInj5f2z4QBAAITg0rZCZB2H2BKapPxwWVJhpsF6yZAzCZAnD9UKVO1yGdf4fdiMBSKg8jnFsLYxatZAEfZBzlXob8bQ0lCkQgQygZCQB5OyghSJwpfISbdTe6tgsZCjEfHj3vUxV8XeH4tgOXE800jMlQoTjZC5aio2ZBJTNxWZBVlckCZCfF2odGNPRr6Opt5c4nM00ZCrPjG89UGnJBEePEM0JkZBSLIQTL0HdggthrxXQaQZDZD";
	
		f.setOAuthAppId(appId, appSecret);
		f.setOAuthPermissions(permissions);
		f.setOAuthAccessToken(new AccessToken(token));
		
		try {
			String nome = f.getName();
			String id = f.getId();
			
			System.out.println("Nome da pessoa: " + nome );
			System.out.println("Id da pessoa: " + id);
		} catch (IllegalStateException e) {
			System.out.println("Erro");
			e.printStackTrace();
		} catch (FacebookException e) {
			System.out.println("Erro com o Facebook");
			e.printStackTrace();
		}
		
	}
	
	//Verifica se o usu�rio j� est� registrado no banco de dados
	//Entrada: Id do usu�rio que v�m ap�s o login no Facebook
	//Sa�da: Usu�rio com o respectivo id do facebook associado (pode ser null tamb�m)
	//Pr� condi��o: O id dever� vir ap�s o usu�rio ter entrado pelo Facebook
	//P�s condi��o: Nenhuma
	public User verificaUsuarioExiste(User user){
		Connection connection = Conexao.getConnection();
		userDAO ud = new userDAO(connection);
		String idFacebook = user.getIdFacebook();
		try {
			user = ud.selectUserID(idFacebook); //pode ser null ou n�o
			//connection.commit(); //Talvez seja melhor tirar pq dessa forma, n�o garanto a integridade completa da minha transa��o...
			//Apenas dessa parte do c�digo. Se outro m�todo usar essa fun��o, melhor tirar esse commit, e deixar apenas no m�todo
			//Que chamou esse m�todo.
		} catch (SQLException e) {
			System.out.println("Erro ao verificar se o usu�rio existe");
			e.printStackTrace();
		}
		return user;
	}
	
	//Verifico se o id do usu�rio j� se encontra no banco de dados. Caso exista, esse usu�rio j� poss�i uma...
	//...conta no banco de dados. Caso n�o exista, insiro esse usu�rio no banco de dados.
	//Entrada: Usu�rio a ser inserido na entidade User
	//Sa�da: Usu�rio que foi inserido ou recuperado do bd
	//Pr� condi��o: Usu�rio precisa ter feito um login por meio do Facebook
	//P�s condi��o: Usu�rio inserido ou recuperado do banco de dados
	public User userLogarFacebook(User user){
		Connection connection = Conexao.getConnection();
		User userTemp;
		
		userDAO ud = new userDAO(connection);
		userTemp = verificaUsuarioExiste(user);
		
		if(userTemp == null){ //se n�o existir usu�rio...
			System.out.println("Usu�rio n�o registrado!");
			try {
				user = ud.insertUser(user); //o usu�rio retorna, mas com ID preenchido.
			} catch (SQLException e) {
				e.printStackTrace();
			} 
			
		}
		else
			user = userTemp;
		return user; //retorno o usu�rio com o id preenchido.
	}
}
