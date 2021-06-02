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
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.annotations.SelectBeforeUpdate;

import javax.persistence.ForeignKey;

import model.MAutor;

@Entity
@Table(name = "autores")
@DynamicUpdate(value = true)
@SelectBeforeUpdate(value = true)
@DynamicInsert(value = true)
public class Autor {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	@Column(nullable = false)
	private String nome;
	@Column(nullable = false, columnDefinition = "TEXT")
	private String biografia;
	@Column(nullable = true)
	private File foto;
	@Transient
	public String errorMsg;
	@OneToMany(cascade = CascadeType.ALL)
	@JoinColumn(name = "autor_id",nullable = true, foreignKey = @ForeignKey(name = "fk_Autor_Livro"))
	private List<Book> listaLivros = new ArrayList<Book>();

	public Autor() {

	}

	public Autor(String nome, String biografia, File foto) {
		this.nome = nome;
		this.biografia = biografia;
		this.foto = foto;

	}

	public boolean cadastrarAutor() {

		MAutor ma = new MAutor();

		if (!ma.cadastrar(this)) {
			errorMsg = ma.errorMsg;
			return false;
		}

		return true;
	}

	public boolean cadastrarLivro(Book b) {
		MAutor ma = new MAutor();

		listaLivros.add(b);

		if (!ma.cadastrarLivro(this)) {
			errorMsg = ma.errorMsg;
			return false;
		}

		return true;
	}

	public boolean findAutor(String id_autor) {
		MAutor ma = new MAutor();
		
		if (id_autor != null) {
			
			id = Integer.parseInt(id_autor);
			Autor a = new Autor();
			a = ma.findAutor(this);
			
			
			if (a!=null) {
				id = a.id;
				nome = a.nome;
				biografia = a.biografia;
				foto = a.foto;
				return true;
				
			}
		}
		
		errorMsg = ma.errorMsg;
		return false;
	}

	public List<Autor> searchAutores(String arg) {
		// Título do Livro contem 'arg' -- usar SELECT * FROM books WHERE nome LIKE
		// '%arg%'

		MAutor ma = new MAutor();
		List<Autor> searchAutores = ma.searchAutores(arg);
		if (searchAutores == null) {
			errorMsg = ma.errorMsg;
			return null;
		}
		return searchAutores;

	}

	public List<Book> livrosAutor(int id) {

		MAutor ma = new MAutor();
		List<Book> list = ma.livrosAutor(id);
		if (list == null) {
			errorMsg = ma.errorMsg;
			return null;
		}
		return list;

	}

	// --------- Gets e Sets -------------------------------------------
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getBiografia() {
		return biografia;
	}

	public void setBiografia(String biografia) {
		this.biografia = biografia;
	}

	public File getFoto() {
		return foto;
	}

	public void setFoto(File foto) {
		this.foto = foto;
	}

	public List<Book> getListaLivros() {
		return listaLivros;
	}

	public void setListaLivros(List<Book> listaLivros) {
		this.listaLivros = listaLivros;
	}

}
