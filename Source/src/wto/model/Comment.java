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
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name="comment")
public class Comment {
	@Id
	@GeneratedValue
	@Column(name="idcomment")
	private Integer idcomment;
	@Column(name="iduser")
	private int iduser;
	@Column(name="idimage")
	private int idimage;
	@Column(name="content")
	private String content;
	@Column(name="points")
	private int points;
	@Column(name="create_time")
	private Date createTime;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name="idimage", referencedColumnName="idimage", insertable = false, updatable = false)
    private Image img;
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name="iduser", referencedColumnName="iduser", insertable = false, updatable = false)
    private User user;
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "idcomment", fetch = FetchType.LAZY)
    private Set<CommentVote> votes;
	
	public Comment() {}
	public Comment(Integer idcomment, int iduser, int idimage, String content,
			int points, Date createTime) {
		super();
		this.idcomment = idcomment;
		this.iduser = iduser;
		this.idimage = idimage;
		this.content = content;
		this.points = points;
		this.createTime = createTime;
	}
	public Integer getIdcomment() {
		return idcomment;
	}
	public void setIdcomment(Integer idcomment) {
		this.idcomment = idcomment;
	}
	public int getIduser() {
		return iduser;
	}
	public void setIduser(int iduser) {
		this.iduser = iduser;
	}
	public int getIdimage() {
		return idimage;
	}
	public void setIdimage(int idimage) {
		this.idimage = idimage;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public int getPoints() {
		return points;
	}
	public void setPoints(int points) {
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
	public Image getImg() {
		return img;
	}
	public void setImg(Image img) {
		this.img = img;
	}
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	public Set<CommentVote> getVotes() {
		return votes;
	}
	public void setVotes(Set<CommentVote> votes) {
		this.votes = votes;
	}
	
}
