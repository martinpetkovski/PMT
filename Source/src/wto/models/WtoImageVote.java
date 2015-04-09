package wto.models;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.DynamicUpdate;

@DynamicUpdate
@Table(name="image_vote")
public class WtoImageVote {
	@Id
	@GeneratedValue
	@Column
	private int idvote;
	@Column
	private int iduser;
	@Column
	private int idimage;
	@Column
	private boolean votetype;
	@Column(name="create_time")
	private String createTime;
	
	public WtoImageVote() {}
	public WtoImageVote(int idvote, int iduser, int idimage, boolean votetype,
			String createTime) {
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
	public String getCreateTime() {
		return createTime;
	}
	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}
	
	
}
