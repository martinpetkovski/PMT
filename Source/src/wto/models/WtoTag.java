package wto.models;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.DynamicUpdate;

@DynamicUpdate
@Table(name="tag")
public class WtoTag {
	@Id
	@GeneratedValue
	@Column
	private int idtag;
	@Column
	private int idimage;
	@Column
	private String content;
	
	public WtoTag(){}
	public WtoTag(int idtag, int idimage, String content) {
		super();
		this.idtag = idtag;
		this.idimage = idimage;
		this.content = content;
	}
	public int getIdtag() {
		return idtag;
	}
	public void setIdtag(int idtag) {
		this.idtag = idtag;
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
	
	
}
