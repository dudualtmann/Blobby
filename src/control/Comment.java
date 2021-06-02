package control;

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

import model.MComment;

@Entity
@Table(name = "comments")
@DynamicUpdate(value = true)
@SelectBeforeUpdate(value = true)
@DynamicInsert(value = true)
public class Comment {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	@ManyToOne
	@JoinColumn(name = "id_post")
	private Post post;
	
	@ManyToOne
	@JoinColumn(name = "id_user")
	private User user;
	
	@Column(nullable=false, columnDefinition = "TEXT")
	private String content;
	
	@Transient
	public String errorMsg;
	
	public Comment() {
		
	}
	
	public Comment(Post post, User user, String content) {
		this.post = post;
		this.user = user;
		this.content = content;
	}
	
	public boolean fazerComment() {
		
		MComment mc = new MComment();
		if(!mc.fazerComment(this)) {
			errorMsg = mc.errorMsg;
			return false;
		}
		
		return true;
	}
	
	public List<Comment> getComments(int id_post) {

		MComment mc = new MComment();
		List<Comment> getComments = mc.getComments(id_post);
		if (getComments == null) {
			errorMsg = mc.errorMsg;
			return null;
		}
		return getComments;

	}

	
	
	// Getters and Setters
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Post getPost() {
		return post;
	}

	public void setPost(Post post) {
		this.post = post;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}
	
	

}
