package control;

import java.io.File;
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

import model.MBook;
import model.MBookListaPessoal;

@Entity
@Table(name = "books")
@DynamicUpdate(value = true)
@SelectBeforeUpdate(value = true)
@DynamicInsert(value = true)
public class Book {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	@Column(nullable = false)
	private String nome;
	@Column(nullable = true, columnDefinition = "TEXT")
	private String sinopse;
	@Column(nullable = false)
	private int pages;
	@Column(nullable = false)
	private String genero1;
	@Column(nullable = true)
	private String editora;
	@Column(nullable = true)
	private double rating;
	@Column(nullable = false)
	private String ano;
	@Column(nullable = true)
	private File capa;
	@Column(nullable = false, length = 1)
	private String status; // A - Adicionado || R - Requisitado
	@Transient
	public String errorMsg;
	@ManyToOne
	@JoinColumn(nullable = true)
	private Autor autor;

	@OneToMany(cascade = CascadeType.ALL)
	@JoinColumn(name = "id_book")
	public List<Review> listaReviews;

//	@ManyToMany(cascade = CascadeType.ALL)
//	@JoinTable(name = "livro_listapessoal", joinColumns = @JoinColumn (name ="id_livro"), inverseJoinColumns = 
//	@JoinColumn(name = "id_listaPessoal"))
//	private List<ListaPessoal> listaListaPessoal = new ArrayList<ListaPessoal>();

	@OneToMany(cascade = CascadeType.ALL)
	@JoinColumn(name = "id_book")
	public List<BookListaPessoal> bookListaPessoal;

	public Book() {

	}

	public Book(String nome, String sinopse, String pages, String genero1, String editora, String rating, String ano,
			File capa, Autor autor) {

		this.nome = nome;
		this.sinopse = sinopse;
		if (pages != null)
			this.pages = Integer.parseInt(pages);
		this.genero1 = genero1;
		this.editora = editora;
		if (rating != null)
			this.rating = Double.parseDouble(rating);
		this.ano = ano;
		this.capa = capa;
		this.autor = autor;

	}
	
	public Book(int id, String nome, String sinopse, String pages, String genero1, String editora, String rating, String ano,
			File capa, Autor autor) {
		
        this.id = id;
		this.nome = nome;
		this.sinopse = sinopse;
		if (pages != null)
			this.pages = Integer.parseInt(pages);
		this.genero1 = genero1;
		this.editora = editora;
		if (rating != null)
			this.rating = Double.parseDouble(rating);
		this.ano = ano;
		this.capa = capa;
		this.autor = autor;

	}

	public boolean localizarLivro(String ids) {
		System.out.println("Método - Localizar Livro - Linha: 112 // ID: "+ ids);
		int id = Integer.parseInt(ids);
		MBook mb = new MBook();
		Book b = mb.localizarLivro(id);
		if (b.nome == null) {
			errorMsg = b.errorMsg;
			return false;
		}
		this.nome = b.nome;
		this.editora = b.editora;
		this.pages = b.pages;
		this.autor = b.autor;
		this.id = b.id;
		this.autor = b.autor;
		this.ano = b.ano;
		this.capa = b.capa;
		this.sinopse = b.sinopse;
		this.genero1 = b.genero1;
		this.rating = b.rating;
		return true;
	}

	public boolean cadastrarLivro() {

		if (!verifyName()  || !verifyYear()) {
			return false;
		}

		this.status = "A";

		MBook mb = new MBook();

		if (!mb.cadastrar(this)) {
			errorMsg = mb.errorMsg;
			return false;
		}

		return true;

	}

	public boolean updateRating() {
		
		this.status = "A";

		    MBook mb = new MBook();
			if (!mb.updateRating(rating+"",id+"")) {
				errorMsg = mb.errorMsg;
				return false;
			}
		 

		return true;

	}

	public boolean cadastrarRequisicao() {
		
		// Este método foi feito visando um estágio mais avançado do site, onde já terão muitos livros e será necessário um controle maior.

		if (!verifyName() || !verifyYear()) {
			return false;
		}

		this.status = "R";

		MBook mb = new MBook();

		if (!mb.cadastrar(this)) {
			errorMsg = mb.errorMsg;
			return false;
		}

		return true;
	}

	public List<Book> searchLivros(String arg) {
		// Título do Livro contem 'arg' -- usar SELECT * FROM books WHERE nome LIKE
		// '%arg%'

		MBook mb = new MBook();
		List<Book> searchLivros = mb.searchLivros(arg);
		if (searchLivros == null) {
			errorMsg = mb.errorMsg;
			return null;
		}
		return searchLivros;

	}

	public List<Book> livrosAutor(int id) {

		MBook mb = new MBook();
		List<Book> list = mb.livrosAutor(id);
		if (list == null) {
			errorMsg = mb.errorMsg;
			return null;
		}
		return list;

	}
	
	public List<Book> getTopBooks() {

		MBook mb = new MBook();

		List<Book> rating = mb.getTopBooks();
		

		if (rating == null || rating.isEmpty()) {
			errorMsg = mb.errorMsg;
			return null;
		}


		return rating;

	}
	
	public List<Book> ultimosAdicionados() {

		MBook mb = new MBook();

		List<Book> uAdicionados = mb.ultimosAdicionados();
		

		if (uAdicionados == null || uAdicionados.isEmpty()) {
			errorMsg = mb.errorMsg;
			return null;
		}


		return uAdicionados;

	}

	public boolean verifyName() {
		if (nome == null || nome.isEmpty()) {
			errorMsg = "O campo 'Nome do Livro' não pode ser vazio.";
			return false;
		}
		return true;
	}


	public boolean verifyYear() {
		if (ano == null || ano.isEmpty()) {
			errorMsg = "O campo 'Ano' não pode ser vazio.";
			return false;
		}
		return true;
	}

	// -------------- Gets e Sets ---------------------------------------------//
	// ------------------------------------------------------------------------//
	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getAno() {
		return ano;
	}

	public void setAno(String ano) {
		this.ano = ano;
	}

	public File getCapa() {
		return capa;
	}

	public void setCapa(File capa) {
		this.capa = capa;
	}

	public String getEditora() {
		return editora;
	}

	public void setEditora(String editora) {
		this.editora = editora;
	}

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

	public String getSinopse() {
		return sinopse;
	}

	public void setSinopse(String sinopse) {
		this.sinopse = sinopse;
	}

//	public String getAutor() {
//		return autor;
//	}
//
//	public void setAutor(String autor) {
//		this.autor = autor;
//	}

	public int getPages() {
		return pages;
	}

	public void setPages(int pages) {
		this.pages = pages;
	}

	public String getGenero1() {
		return genero1;
	}

	public void setGenero1(String genero1) {
		this.genero1 = genero1;
	}

	public double getRating() {
		return rating;
	}

	public void setRating(double rating) {
		this.rating = rating;
	}

	public Autor getAutor() {
		return autor;
	}

	public void setAutor(Autor autor) {
		this.autor = autor;
	}

//	public int getId_autor() {
//		return id_autor;
//	}
//
//	public void setId_autor(int id_autor) {
//		this.id_autor = id_autor;
//	}

}
