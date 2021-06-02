package control;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.ForeignKey;

import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.annotations.SelectBeforeUpdate;

import model.MUser;

@Entity(name = "users")
@DynamicUpdate(value = true)
@SelectBeforeUpdate(value = true)
@DynamicInsert(value = true)
public class User extends Pessoa {
	
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "id_listaPessoal", nullable = true, foreignKey = @ForeignKey(name =	"fk_Lista_User"))
	private ListaPessoal listaPessoal;
	
	@ManyToMany (mappedBy="listaUsers")
	private List<Grupo> listaGrupos = new ArrayList<>();
	
	@OneToMany(cascade = CascadeType.ALL)
	@JoinColumn(name="id_user")
	public List<Post> listaPosts;
	
	@OneToMany(cascade = CascadeType.ALL)
	@JoinColumn(name="id_user")
	public List<Comment> listaComments;
	
	@OneToMany(cascade = CascadeType.ALL)
	@JoinColumn(name="id_owner")
	public List<Grupo> listaOwners;
	
	@OneToMany(cascade = CascadeType.ALL)
	@JoinColumn(name="id_user")
	public List<Review> listaReviews;


	public User() {

		this.status = "user";

	}

	public User(String email, String username, String senha) {

		this.email = email;
		this.username = username;
		this.senha = senha;
		this.status = "user";
		
		System.out.println(email+"/"+username+"/"+senha);

	}

	public boolean cadastrar() {
		// Tratar variáveis individualmente através de métodos
		
		if(!verifyUsername() || !verifyEmail() || !verifyPassword()) {
			return false;
		}

			MUser mu = new MUser();
			Admin a = new Admin();
			
			if(findUser(username) || a.findAdm(username)) {
				errorMsg = "Esse Nome de Usuário Já Está Sendo Usado.";
				return false;
			}

			if (!mu.cadastrar(this)) {
				errorMsg = mu.errorMsg;
				return false;
			}
		
		return true;
	}

	public boolean login(String username, String senha) {

		MUser mu = new MUser();

		User u = mu.login(username, senha);
		
		if(u == null) {
			errorMsg = mu.errorMsg;
			return false;
		}
		else {
			this.id = u.id;
			this.email = u.email;
			this.username = u.username;
			this.senha = u.senha;
		}
		System.out.println(this.id);
		
			return true;
		

	}
	
	public boolean updateUser() {
		
		MUser mu = new MUser();

		if (!mu.update(this)) {
			errorMsg = mu.errorMsg;
			return false;
		}
	
	return true;
		
	}

	public boolean findUser(String username) {

		MUser mu = new MUser();
		this.username = username;
		User u = mu.findUser(this);
		if (u==null) {
			errorMsg = mu.errorMsg;
			return false;
		}
		this.email = u.getEmail();
		this.id = u.getId();
		this.senha = u.getSenha();
		this.status = u.getStatus();
		this.username = u.getUsername();
		this.listaPessoal = u.getListaPessoal();

		return true;
	}

	public List<User> searchUsers(String arg) {
		// User contem 'arg' -- usar SELECT * FROM users WHERE nome LIKE '%arg%'

		MUser mu = new MUser();
		List<User> searchUsers = mu.searchUsers(arg);
		if (searchUsers == null) {
			errorMsg = mu.errorMsg;
			return null;
		}
		return searchUsers;

	}
	
	public boolean delUser() {
		
		MUser mu = new MUser();
		if(!mu.delUser(this)) {
			errorMsg = mu.errorMsg;
			return false;
		}
		
		return true;
			
		
		
	}

	

	// ------------------------ Gets e Sets
	// ------------------------------------------------//
	
	public ListaPessoal getListaPessoal() {
		return listaPessoal;
	}

	public void setListaPessoal(ListaPessoal listaPessoal) {
		this.listaPessoal = listaPessoal;
	}

}
