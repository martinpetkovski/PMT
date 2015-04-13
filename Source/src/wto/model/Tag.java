package wto.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="tag")
public class Tag {
	@Id
	@GeneratedValue
	@Column(name="idtag")
	private int idtag;
	@Column(name="idimage")
	private int idimage;
	@Column(name="content")
	private String content;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name="idimage", referencedColumnName="idimage", insertable = false, updatable = false)
    private Image image;
	
	public Tag(){}
	public Tag(int idtag, int idimage, String content) {
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
	public Image getImage() {
		return image;
	}
	public void setImage(Image image) {
		this.image = image;
	}
	
}
