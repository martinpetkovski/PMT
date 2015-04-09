package wto.models;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.DynamicUpdate;

@DynamicUpdate
@Table(name="image")
public class WtoImage {
	@Id
	@GeneratedValue
	@Column(name="idimage")
	private int idimage;
	@Column(name="iduser")
	private int iduser;
	@Column(name="title")
	private String title;
	@Column(name="content")
	private String content;
	@Column(name="points")
	private int points;
    @Temporal(TemporalType.TIMESTAMP)
	@Column(name="create_time")
	private Date createTime;
	
	public WtoImage(){}
	public WtoImage(int idimage, int iduser, String title, String content,
			int points, Date createTime) {
		super();
		this.idimage = idimage;
		this.iduser = iduser;
		this.title = title;
		this.content = content;
		this.points = points;
		this.createTime = createTime;
	}
	public int getIdimage() {
		return idimage;
	}
	public void setIdimage(int idimage) {
		this.idimage = idimage;
	}
	public int getIduser() {
		return iduser;
	}
	public void setIduser(int iduser) {
		this.iduser = iduser;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
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
