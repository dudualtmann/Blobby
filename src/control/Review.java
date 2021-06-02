package control;

import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.annotations.SelectBeforeUpdate;

import model.MReview;

@Entity
@Table(name = "reviews")
@DynamicUpdate(value = true)
@SelectBeforeUpdate(value = true)
@DynamicInsert(value = true)
public class Review {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	@ManyToOne
	@JoinColumn(name = "id_user")
	private User user;
	@ManyToOne
	@JoinColumn(name = "id_book")
	private Book book;
	@Column(nullable = false, columnDefinition = "TEXT")
	private String texto;
	@Column(nullable = true, columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
    private Date data;
	@Transient
	public String errorMsg;

	public Review() {

	}

	public Review(User u, Book b, String resenha) {
		this.book = b;
		this.user = u;
		this.texto = resenha;
	}

	public boolean cadastrar() {

		MReview mr = new MReview();
		if (!mr.cadastrar(this)) {
			errorMsg = mr.errorMsg;
			return false;
		}

		return true;

	}

	public List<Review> getReviews(String id_livro) {
		
		if (id_livro != null) {
			int id_book = Integer.parseInt(id_livro);
			
			MReview mr = new MReview();
			List<Review> list = mr.getReviews(id_book);

			if (list != null) {
				
				return list;
				
			} else {
				errorMsg = mr.errorMsg;
			}

		}

		return null;

	}

	// ------------ Gets e Sets ----------------------------------------------------

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
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

	public String getTexto() {
		return texto;
	}

	public void setTexto(String texto) {
		this.texto = texto;
	}

	public Date getData() {
		return data;
	}

	public void setData(Date data) {
		this.data = data;
	}

}
