package wto.models;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.DynamicUpdate;

@DynamicUpdate
@Table(name="user")
public class WtoUser {
	@Id
	@GeneratedValue
	@Column
	private int iduser;
	@Column
	private String username;
	@Column
	private String password;
	@Column
	private int points;
	@Column(name="create_time")
	private String createTime;
	
	public WtoUser() {}
	public WtoUser(int iduser, String username, String password, int points,
			String createTime) {
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
	public String getCreateTime() {
		return createTime;
	}
	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}
	
	
	
}
