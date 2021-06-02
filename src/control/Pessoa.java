package control;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.MappedSuperclass;
import javax.persistence.Transient;

@MappedSuperclass
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
public class Pessoa {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	protected int id;
	@Column(nullable = false, unique = true, length = 20)
	protected String username;
	@Transient // Faz com que o atributo abaixo não entre no Banco de Dados
	public String errorMsg;
	@Column(nullable = false, length = 30)
	protected String senha;
	@Column(nullable = false, unique = true)
	protected String email;
	@Transient
	protected String status;

	public boolean verifyUsername() {

		if (username != null && !username.isEmpty()) {

			if (username.matches("[a-zA-Z0-9]*")) {

				return true;

			} else {

				errorMsg = "O campo 'username' deve conter apenas letras e números";
				return false;

			}

		} else {
			errorMsg = "O Campo 'username' não pode ser vazio";
			return false;

		}

	}

	public boolean verifyEmail() {

		if (email != null && !email.isEmpty()) {

			if ((!email.contains("@")) || email.contains(" ") || email.contains("ç")) {
				errorMsg = "E-Mail inválido";
				return false;
			}

		} else {
			errorMsg = "O campo 'email' não pode ser vazio";
			return false;
		}

		return true;

	}

	public boolean verifyPassword() {

		if (senha != null && !senha.isEmpty()) {

			if (senha.length() > 30 || senha.length() < 7) {

				errorMsg = "A senha tem que ter entre 7 e 30 caracteres";
				return false;

			}

		} else {

			errorMsg = "O campo 'senha' não pode ser vazio";
			return false;

		}

		return true;

	}

	

	// --------------- Gets e Sets ------------------------------------------//
	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

}
