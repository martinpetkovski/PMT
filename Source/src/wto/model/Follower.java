package wto.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="follower")
public class Follower {
	@Id
	@GeneratedValue
	@Column
	private int idfollower;
	@Column
	private int followerid;
	@Column
	private int foloweeid;
}
