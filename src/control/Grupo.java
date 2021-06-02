package control;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.persistence.JoinColumn;

import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.annotations.SelectBeforeUpdate;

import model.MGrupo;

@Entity
@Table(name = "grupos")
@DynamicUpdate(value = true)
@SelectBeforeUpdate(value = true)
@DynamicInsert(value = true)
public class Grupo {
	@Transient
	public String errorMsg;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	@ManyToOne
	@JoinColumn(name = "id_owner")
	private User owner;

	@Column(nullable = false)
	private String name;

	@Column(nullable = false, columnDefinition = "TEXT")
	private String description;

	@Column(nullable = false, columnDefinition = "TEXT")
	private String rules;

	@Column(nullable = false)
	private String year;

	@Column(nullable = false)
	private String category;

	@Column(nullable = true)
	private File fotoGrupo;

	@ManyToMany(cascade = CascadeType.ALL)
	@JoinTable(name = "grupo_user", joinColumns = @JoinColumn(name = "id_grupo"), inverseJoinColumns = @JoinColumn(name = "id_user"))
	private List<User> listaUsers = new ArrayList<User>();

	@OneToMany(cascade = CascadeType.ALL)
	@JoinColumn(name = "id_grupo")
	private List<Post> listaPosts = new ArrayList<Post>();

	public Grupo() {

	}

	public Grupo(User owner, String name, String desc, String rules, String year, String category, File fotoGrupo) {

		this.owner = owner;
		this.name = name;
		this.description = desc;
		this.rules = rules;
		this.year = year;
		this.category = category;
		this.fotoGrupo = fotoGrupo;

	}

	public boolean cadastrar() {

		MGrupo mg = new MGrupo();

		if (!mg.cadastrar(this)) {
			errorMsg = mg.errorMsg;
			return false;
		}

		if (!addUser(this.owner)) {
			return false;
		}

		return true;

	}

	public boolean addUser(User u) {

		MGrupo mg = new MGrupo();

		if (!mg.addUser(this, u)) {
			errorMsg = mg.errorMsg;
			return false;
		}

		return true;

	}

	public boolean localizarGrupo(String id_grupo) {
		if (id_grupo == null || id_grupo.isEmpty()) {
			errorMsg = "Não foi Possível carregar as informações do Grupo.";
			return false;
		}
		int grupo_id = Integer.parseInt(id_grupo);

		MGrupo mg = new MGrupo();

		Grupo g = mg.localizarGrupo(grupo_id);
		if (g == null) {
			errorMsg = mg.errorMsg;
			return false;
		}

		this.category = g.category;
		this.description = g.description;
		this.fotoGrupo = g.fotoGrupo;
		this.id = g.id;
		this.listaPosts = g.listaPosts;
		this.listaUsers = g.listaUsers;
		this.name = g.name;
		this.owner = g.owner;
		this.rules = g.rules;
		this.year = g.year;

		return true;

	}

	public List<Grupo> searchGrupos(String arg) {
		// Título do Livro contem 'arg' -- usar SELECT * FROM books WHERE nome LIKE
		// '%arg%'

		MGrupo mg = new MGrupo();
		List<Grupo> searchGrupos = mg.searchGrupos(arg);
		if (searchGrupos == null || searchGrupos.size() < 1) {
			errorMsg = mg.errorMsg;
			return null;
		}

		return searchGrupos;

	}

	public boolean hasJoined(User u) {

		MGrupo mg = new MGrupo();
		if (!mg.hasJoined(this, u)) {
			errorMsg = mg.errorMsg;
			return false;
		}

		return true;

	}

	public String countUsers(String id_grupo) {

		MGrupo mg = new MGrupo();
		int id_group = 0;
		if (id_grupo != null)
			id_group = Integer.parseInt(id_grupo);

		int count = 0;

		List<Long> list = mg.countUsers(id_group);

		if (list == null) {
			errorMsg = mg.errorMsg;
			return "0";
		} else {
			for (int i = 0; i < list.size(); i++) {
				count++;
			}
		}

		return count + "";

	}

	public boolean leaveGroup(User u) {

		MGrupo mg = new MGrupo();
		if (!mg.leaveGroup(this, u)) {
			errorMsg = mg.errorMsg;
			return false;
		}

		return true;

	}
	
	public List<Grupo> ultimosAdicionados() {

		MGrupo mg = new MGrupo();

		List<Grupo> uAdicionados = mg.ultimosAdicionados();
		

		if (uAdicionados == null || uAdicionados.isEmpty()) {
			errorMsg = mg.errorMsg;
			return null;
		}


		return uAdicionados;

	}
	
	public List<Long> getMyList(int id_user){
		
	    List<Long> myList; 

		MGrupo mg = new MGrupo();

		myList = mg.getMyList(id_user);
		

		if (myList == null || myList.isEmpty()) {
			errorMsg = mg.errorMsg;
			return null;
		}


		return myList;

	}
	
	public boolean delGroup() {
		
		MGrupo mg = new MGrupo();
		if(!mg.delGroup(this)) {
			errorMsg = mg.errorMsg;
			return false;
		}
		
		return true;
	}

	// Getters and Setters

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public List<User> getListaUsers() {
		return listaUsers;
	}

	public void setListaUsers(List<User> listaUsers) {
		this.listaUsers = listaUsers;
	}

	public User getOwner() {
		return owner;
	}

	public void setOwner(User owner) {
		this.owner = owner;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getRules() {
		return rules;
	}

	public void setRules(String rules) {
		this.rules = rules;
	}

	public String getYear() {
		return year;
	}

	public void setYear(String year) {
		this.year = year;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public List<Post> getListaPosts() {
		return listaPosts;
	}

	public void setListaPosts(List<Post> listaPosts) {
		this.listaPosts = listaPosts;
	}

	public File getFotoGrupo() {
		return fotoGrupo;
	}

	public void setFotoGrupo(File fotoGrupo) {
		this.fotoGrupo = fotoGrupo;
	}

}
