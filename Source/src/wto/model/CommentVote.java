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
    
    @ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name="iduser", referencedColumnName="iduser", insertable = false, updatable = false)
    private User user;
    @ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name="idcomment", referencedColumnName="idcomment", insertable = false, updatable = false)
    private Comment comment;
	
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
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	public Comment getComment() {
		return comment;
	}
	public void setComment(Comment comment) {
		this.comment = comment;
	}
	
	
	
}
