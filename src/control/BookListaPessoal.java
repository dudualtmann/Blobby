package control;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import javax.persistence.Table;
import javax.persistence.Transient;

import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.annotations.SelectBeforeUpdate;

import model.MBook;
import model.MBookListaPessoal;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
@Table(name = "book_listapessoal")
@DynamicUpdate(value = true)
@SelectBeforeUpdate(value = true)
@DynamicInsert(value = true)
public class BookListaPessoal {

	@Transient
	public String errorMsg;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	@ManyToOne
	@JoinColumn(name = "id_booklist")
	private ListaPessoal listaPessoal;

	@ManyToOne
	@JoinColumn(name = "id_book")
	private Book book;

	@Column(length = 2)
	private int rating;
	@Column(nullable = true)
	private String status;
	@Column(nullable = true)
	private int pagLidas;

	public BookListaPessoal() {

	}

	public BookListaPessoal(ListaPessoal lp, Book book, String rating, String status, String pagLidas) {
		this.listaPessoal = lp;
		this.book = book;
		if (rating != null)
			this.rating = Integer.parseInt(rating);
		this.status = status;
		if (pagLidas != null)
			this.pagLidas = Integer.parseInt(pagLidas);

	}

	public boolean addList() {

		MBookListaPessoal mb = new MBookListaPessoal();
		if (this.listaPessoal != null && this.book != null) {
			if (!mb.addList(this)) {
				errorMsg = mb.errorMsg;
				return false;
			}
		} else {
			errorMsg = "A Lista do Usuário ou o Livro não foi Localizado.";
			return false;
		}
		return true;

	}

	public boolean verifyList(ListaPessoal lp, Book b) {
		MBookListaPessoal mb = new MBookListaPessoal();
		BookListaPessoal bl = new BookListaPessoal();

		if (lp == null || b == null) {
			errorMsg = "null";
			return false;
		}

		bl = mb.verifyList(lp, b);

		if (bl != null) {
			id = bl.id;
			return true;
		}

		errorMsg = mb.errorMsg;
		return false;
	}

	public boolean updateList() {

		MBookListaPessoal mb = new MBookListaPessoal();
		if (this.listaPessoal != null && this.book != null) {
			if (!mb.updateList(this)) {
				errorMsg = mb.errorMsg;
				return false;
			}
		} else {
			errorMsg = "A Lista do Usuário ou o Livro não foi Localizado.";
			return false;
		}
		
		
		
		return true;

	}

	public double getRating(String id_livro) {

		MBookListaPessoal mb = new MBookListaPessoal();
		int id_book = 0;
		if (id_livro != null)
			id_book = Integer.parseInt(id_livro);
		else {
			errorMsg = "ID do livro inválido";
			return 0;
		}

		List<Double> rating = mb.getRating(id_book);
		double nota = 0;

		if (rating == null || rating.isEmpty()) {
			errorMsg = mb.errorMsg;
			return 0;
		} else {
			try {

				nota = rating.get(0).doubleValue();

			} catch (Exception e) {
				errorMsg = "Não foi Encontrada nenhuma nota para esse livro.";
				return 0;
			}
		}

		return nota;

	}

	public String countUsers(String id_livro) {

		MBookListaPessoal mb = new MBookListaPessoal();
		int id_book = 0;
		if (id_livro != null)
			id_book = Integer.parseInt(id_livro);

		int count = 0;

		List<Integer> list = mb.countUsers(id_book);

		if (list == null) {
			errorMsg = mb.errorMsg;
			return "0";
		} else {
			for (int i = 0; i < list.size(); i++) {
				count++;
			}
		}

		return count + "";

	}

	public List<BookListaPessoal> getMyList(String id_booklist){
		
		    List<BookListaPessoal> myList; 

			MBookListaPessoal mb = new MBookListaPessoal();

			myList = mb.getMyList(id_booklist);
			

			if (myList == null || myList.isEmpty()) {
				errorMsg = mb.errorMsg;
				return null;
			}


			return myList;

		}
		
	

	// Gets e Sets -----------------------------------------------------------
	public ListaPessoal getListaPessoal() {
		return listaPessoal;
	}

	public void setListaPessoal(ListaPessoal listaPessoal) {
		this.listaPessoal = listaPessoal;
	}

	public Book getBook() {
		return book;
	}

	public void setBook(Book book) {
		this.book = book;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

//	public List<ListaPessoal> getListaListaPessoal() {
//		return listaListaPessoal;
//	}
//	public void setListaListaPessoal(List<ListaPessoal> listaListaPessoal) {
//		this.listaListaPessoal = listaListaPessoal;
//	}
//	public List<Book> getListaLivros() {
//		return listaLivros;
//	}
//	public void setListaLivros(List<Book> listaLivros) {
//		this.listaLivros = listaLivros;
//	}
	public int getRating() {
		return rating;
	}

	public void setRating(int rating) {
		this.rating = rating;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public int getPagLidas() {
		return pagLidas;
	}

	public void setPagLidas(int pagLidas) {
		this.pagLidas = pagLidas;
	}

}
