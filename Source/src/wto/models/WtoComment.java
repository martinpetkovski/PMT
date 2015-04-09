package wto.models;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.DynamicUpdate;

@DynamicUpdate
@Table(name="comment")
public class WtoComment {
	@Id
	@GeneratedValue
	@Column(name="idcomment")
	private int idcomment;
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
	
	public WtoComment() {}
	public WtoComment(int idcomment, int iduser, int idimage, String content,
			int points, Date createTime) {
		super();
		this.idcomment = idcomment;
		this.iduser = iduser;
		this.idimage = idimage;
		this.content = content;
		this.points = points;
		this.createTime = createTime;
	}
	public int getIdcomment() {
		return idcomment;
	}
	public void setIdcomment(int idcomment) {
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
		return createTime;
	}
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	
}
