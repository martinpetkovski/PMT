package wto.models;

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
	@Column
	private int idcomment;
	@Column
	private int iduser;
	@Column
	private int idimage;
	@Column
	private String content;
	@Column
	private int points;
	@Column(name="create_time")
	private String createTime;
	
	public WtoComment() {}
	public WtoComment(int idcomment, int iduser, int idimage, String content,
			int points, String createTime) {
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
	public String getCreateTime() {
		return createTime;
	}
	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}
	
}
