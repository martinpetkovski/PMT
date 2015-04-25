package wto.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name="image_vote")
public class ImageVote {
	@Id
	@GeneratedValue
	@Column(name="idvote")
	private Integer idvote;
	@Column(name="iduser")
	private int iduser;
	@Column(name="idimage")
	private int idimage;
	@Column(name="votetype")
	private boolean votetype;
    @Temporal(TemporalType.TIMESTAMP)
	@Column(name="create_time")
	private Date createTime;
    
    @ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name="iduser", referencedColumnName="iduser", insertable = false, updatable = false)
    private User user;
    @ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name="idimage", referencedColumnName="idimage", insertable = false, updatable = false)
    private Image image;
	
	public ImageVote() {}
	public ImageVote(Integer idvote, int iduser, int idimage, boolean votetype,
			Date createTime) {
		super();
		this.idvote = idvote;
		this.iduser = iduser;
		this.idimage = idimage;
		this.votetype = votetype;
		this.createTime = createTime;
	}
	public Integer getIdvote() {
		return idvote;
	}
	public void setIdvote(Integer idvote) {
		this.idvote = idvote;
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
	public boolean isVotetype() {
		return votetype;
	}
	public void setVotetype(boolean votetype) {
		this.votetype = votetype;
	}
	public Date getCreateTime() {
		return createTime;
	}
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	public Image getImage() {
		return image;
	}
	public void setImage(Image image) {
		this.image = image;
	}
	
	
}
