package wto.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="follower")
public class Follower implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 630006238042797096L;
	@Id
	@GeneratedValue
	@Column
	private Integer idfollower;
	@Column
	private int followerid;
	@Column
	private int followeeid;
	
	
	
	public Follower(Integer idfollower, int followerid, int foloweeid) {
		super();
		this.idfollower = idfollower;
		this.followerid = followerid;
		this.followeeid = foloweeid;
	}
	
	
	
	public Follower() {
		
	}



	public int getIdfollower() {
		return idfollower;
	}
	public void setIdfollower(int idfollower) {
		this.idfollower = idfollower;
	}
	public int getFollowerid() {
		return followerid;
	}
	public void setFollowerid(int followerid) {
		this.followerid = followerid;
	}
	public int getFolloweeid() {
		return followeeid;
	}
	public void setFolloweeid(int foloweeid) {
		this.followeeid = foloweeid;
	}
	
	
}