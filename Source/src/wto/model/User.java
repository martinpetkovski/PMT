package wto.model;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;


@Entity
@Table(name="user")
public class User {
	@Id
	@GeneratedValue
	@Column(name="iduser")
	private Integer iduser;
	@Column(name="username")
	private String username;
	@Column(name="email")
	private String email;
	@Column(name="password")
	private String password;
	@Column(name="points")
	private Integer points;
    @Temporal(TemporalType.TIMESTAMP)
	@Column(name="create_time")
	private Date createTime;
    
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "iduser", fetch = FetchType.LAZY)
    private Set<Image> images;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "iduser", fetch = FetchType.LAZY)
    private Set<Comment> comments;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "iduser", fetch = FetchType.LAZY)
    private Set<CommentVote> commentVotes;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "iduser", fetch = FetchType.LAZY)
    private Set<ImageVote> imageVotes;
	
	public User() {}
	public User(Integer iduser, String username, String email, String password, Integer points,
			Date createTime) {
		super();
		this.iduser = iduser;
		this.username = username;
		this.email = email;
		this.password = password;
		this.points = points;
		this.createTime = createTime;
	}
	public Integer getIduser() {
		return iduser;
	}
	public void setIduser(Integer iduser) {
		this.iduser = iduser;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public Integer getPoints() {
		return points;
	}
	public void setPoints(Integer points) {
		this.points = points;
	}
	public Date getCreateTime() {
		return this.createTime;
	}
	public String getCreateTimeAsString() {
		SimpleDateFormat dt = new SimpleDateFormat("dd.MM.yyyy 'at' HH:mm"); 
		return dt.format(this.createTime);
	}
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	public Set<Image> getImages() {
		return images;
	}
	public void setImages(Set<Image> images) {
		this.images = images;
	}
	public Set<Comment> getComments() {
		return comments;
	}
	public void setComments(Set<Comment> comments) {
		this.comments = comments;
	}
	public Set<CommentVote> getCommentVotes() {
		return commentVotes;
	}
	public void setCommentVotes(Set<CommentVote> commentVotes) {
		this.commentVotes = commentVotes;
	}
	public Set<ImageVote> getImageVotes() {
		return imageVotes;
	}
	public void setImageVotes(Set<ImageVote> imageVotes) {
		this.imageVotes = imageVotes;
	}
	
}
