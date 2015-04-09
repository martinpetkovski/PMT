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
@Table(name="image_vote")
public class WtoImageVote {
	@Id
	@GeneratedValue
	@Column(name="idvote")
	private int idvote;
	@Column(name="iduser")
	private int iduser;
	@Column(name="idimage")
	private int idimage;
	@Column(name="votetype")
	private boolean votetype;
    @Temporal(TemporalType.TIMESTAMP)
	@Column(name="create_time")
	private Date createTime;
	
	public WtoImageVote() {}
	public WtoImageVote(int idvote, int iduser, int idimage, boolean votetype,
			Date createTime) {
		super();
		this.idvote = idvote;
		this.iduser = iduser;
		this.idimage = idimage;
		this.votetype = votetype;
		this.createTime = createTime;
	}
	public int getIdvote() {
		return idvote;
	}
	public void setIdvote(int idvote) {
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
	
	
}
