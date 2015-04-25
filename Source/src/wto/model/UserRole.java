package wto.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="user_roles")
public class UserRole implements Serializable  {
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue
	@Column(name="idrole")
	private int idrole;
	@Column(name="iduser")
	private int iduser;
	@Column(name="ROLE")
	private String role;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name="iduser", referencedColumnName="iduser", insertable = false, updatable = false)
	private User user;

	public int getIdrole() {
		return idrole;
	}

	public void setIdrole(int idrole) {
		this.idrole = idrole;
	}

	public int getIduser() {
		return iduser;
	}

	public void setIduser(int iduser) {
		this.iduser = iduser;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}
	
	
}
