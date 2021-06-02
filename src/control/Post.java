package control;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.annotations.SelectBeforeUpdate;

import model.MPost;

@Entity
@Table(name = "posts")
@DynamicUpdate(value = true)
@SelectBeforeUpdate(value = true)
@DynamicInsert(value = true)
public class Post {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	@ManyToOne
	@JoinColumn(name = "id_grupo")
	private Grupo grupo;
	
	@ManyToOne
	@JoinColumn(name = "id_user")
	private User user;
	
	@OneToMany(cascade = CascadeType.ALL)
	@JoinColumn(name="id_post")
	private List<Comment> listaComments;
	
	@Column(nullable=false)
	private String title;
	
	@Column(nullable=false, columnDefinition = "TEXT")
	private String content;
	
	@Transient
	public String errorMsg;
	
	public Post() {
		
	}
	
	public Post(Grupo grupo, User user, String title, String content) {
		this.content = content;
		this.grupo = grupo;
		this.title = title;
		this.user = user;
	}
	
	public boolean fazerPost() {
		
		MPost mp = new MPost();
		if(!mp.fazerPost(this)) {
			errorMsg = mp.errorMsg;
			return false;
		}
		
		return true;
		
	}
	
	public List<Post> getPosts(int id_grupo) {

		MPost mp = new MPost();
		List<Post> getPosts = mp.getPosts(id_grupo);
		if (getPosts == null) {
			errorMsg = mp.errorMsg;
			return null;
		}
		return getPosts;

	}
	
	public boolean localizarPost(String ids) {
		System.out.println("Método - Localizar Post // ID: "+ ids);
		int id = 0;
		if(ids!=null && !ids.isEmpty())
	     id = Integer.parseInt(ids);
		MPost mp = new MPost();
		Post p = mp.localizarPost(id);
		if (p.title == null) {
			errorMsg = mp.errorMsg;
			return false;
		}
		
		this.content = p.content;
		this.grupo = p.grupo;
		this.title = p.title;
		this.user = p.user;
		this.id = p.id;
		this.listaComments = p.listaComments;
		
		return true;
	}

	
	
	// Getters and Setters
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Grupo getGrupo() {
		return grupo;
	}

	public void setGrupo(Grupo grupo) {
		this.grupo = grupo;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public List<Comment> getListaComments() {
		return listaComments;
	}

	public void setListaComments(List<Comment> listaComments) {
		this.listaComments = listaComments;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}
	
	
	

}
