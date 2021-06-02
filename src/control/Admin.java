package control;

import javax.persistence.Entity;

import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.annotations.SelectBeforeUpdate;

import model.MAdmin;

@Entity(name = "admins")
@DynamicUpdate(value = true)
@SelectBeforeUpdate(value = true)
@DynamicInsert(value = true)
public class Admin extends Pessoa {

	
	public Admin() {
		this.status = "admin";
	}
	
	public Admin(String email, String username, String senha) {
		this.email = email;
		this.username = username;
		this.senha = senha;
		this.status = "admin";
	}

	public boolean cadastrar() {

		if(!verifyUsername() || !verifyEmail() || !verifyPassword()) {
			return false;
		}

			MAdmin ma = new MAdmin();
			
			User u = new User();
			
			if(u.findUser(username) || findAdm(username)) {
				errorMsg = "Esse Nome de Usuário Já Está Sendo Usado.";
				return false;
			}

			if (!ma.cadastrar(this)) {
				errorMsg = ma.errorMsg;
				return false;
			}
		 

		return true;
	}

	public boolean login(String username, String senha) {

		MAdmin ma = new MAdmin();

		Admin a = ma.login(username, senha);

		if(a == null) {
			errorMsg = ma.errorMsg;
			return false;
		}
		else {
			this.id = a.id;
			this.email = a.email;
			this.username = a.username;
			this.senha = a.senha;
		}

		return true;

	}

	public boolean findAdm(String username) {

		MAdmin ma = new MAdmin();
		this.username = username;
		Admin a = ma.findAdm(this);
		if (a==null) {
			errorMsg = ma.errorMsg;
			return false;
		}
		this.email = a.getEmail();
		this.id = a.getId();
		this.senha = a.getSenha();
		this.status = a.getStatus();
		this.username = a.getUsername();

		return true;
	}

}
