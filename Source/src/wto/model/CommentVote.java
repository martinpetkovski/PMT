package wto.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.DynamicUpdate;

@DynamicUpdate
@Table(name="comment_vote")
public class CommentVote {
	@Id
	@GeneratedValue
	@Column(name="idvote")
	private int idvote;
	@Column(name="iduser")
	private int iduser;
	@Column(name="idcomment")
	private int idcomment;
	@Column(name="votetype")
	private boolean votetype;
    @Temporal(TemporalType.TIMESTAMP)
	@Column(name="create_time")
	private Date createTime;
	
	public CommentVote() {}
	public CommentVote(int idvote, int iduser, int idcomment,
			boolean votetype, Date createTime) {
		super();
		this.idvote = idvote;
		this.iduser = iduser;
		this.idcomment = idcomment;
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
	public int getIdcomment() {
		return idcomment;
	}
	public void setIdcomment(int idcomment) {
		this.idcomment = idcomment;
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
