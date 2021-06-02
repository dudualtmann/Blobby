package control;

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

import model.MListaPessoal;

	@Entity
	@Table(name = "booklists")
public class ListaPessoal {
	@Transient
	public String errorMsg;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	@Column(nullable = false)
	private int id_user;
//	@Column(nullable = false)
//	private int id_book;
//	@Column(length = 2)
//	private int rating;
//	@Column(nullable = true)
//	private String status;
//	@Column(nullable = true)
//	private int pagLidas;
	
//	@ManyToMany(mappedBy = "listaListaPessoal")
//	private List<Book> listaLivros = new ArrayList<Book>();
	
	@OneToMany(cascade = CascadeType.ALL)
	@JoinColumn(name="id_booklist")
	public List<BookListaPessoal> bookListaPessoal;

	public ListaPessoal() {

	}

	public ListaPessoal(String id_user) {
		if (id_user != null)
			this.id_user = Integer.parseInt(id_user);
//		if (id_book != null)
//			this.id_book = Integer.parseInt(id_book);
//		if (rating != null)
//			this.rating = Integer.parseInt(rating);
//		if (pagLidas != null)
//			this.pagLidas = Integer.parseInt(pagLidas);
//		this.status = status;
	}

	public boolean addList() {

		MListaPessoal mlp = new MListaPessoal();
		if (!mlp.createList(this)) {
			errorMsg = mlp.errorMsg;
			return false;
		}

		return true;

	}
	
	

	// ------------------ Gets e Sets
	// -----------------------------------------------------------//

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getId_user() {
		return id_user;
	}

	public void setId_user(int id_user) {
		this.id_user = id_user;
	}

//	public int getId_book() {
//		return id_book;
//	}
//
//	public void setId_book(int id_book) {
//		this.id_book = id_book;
//	}

//	public int getRating() {
//		return rating;
//	}
//
//	public void setRating(int rating) {
//		this.rating = rating;
//	}
//
//	public String getStatus() {
//		return status;
//	}
//
//	public void setStatus(String status) {
//		this.status = status;
//	}
//
//	public int getPagLidas() {
//		return pagLidas;
//	}
//
//	public void setPagLidas(int pagLidas) {
//		this.pagLidas = pagLidas;
//	}
	

}
