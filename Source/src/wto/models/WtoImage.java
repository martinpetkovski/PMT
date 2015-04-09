package wto.models;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.DynamicUpdate;

@DynamicUpdate
@Table(name="image")
public class WtoImage {
	@Id
	@GeneratedValue
	@Column
	private int idimage;
	@Column
	private int iduser;
	@Column
	private String title;
	@Column
	private String content;
	@Column
	private int points;
	@Column(name="create_time")
	private String createTime;
	
	public WtoImage(){}
	public WtoImage(int idimage, int iduser, String title, String content,
			int points, String createTime) {
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
	public String getCreateTime() {
		return createTime;
	}
	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}
	
	
}
