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
@Table(name="user")
public class WtoUser {
	@Id
	@GeneratedValue
	@Column(name="iduser")
	private int iduser;
	@Column(name="username")
	private String username;
	@Column(name="password")
	private String password;
	@Column(name="points")
	private int points;
    @Temporal(TemporalType.TIMESTAMP)
	@Column(name="create_time")
	private Date createTime;
	
	public WtoUser() {}
	public WtoUser(int iduser, String username, String password, int points,
			Date createTime) {
		super();
		this.iduser = iduser;
		this.username = username;
		this.password = password;
		this.points = points;
		this.createTime = createTime;
	}
	public int getIduser() {
		return iduser;
	}
	public void setIduser(int iduser) {
		this.iduser = iduser;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
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
