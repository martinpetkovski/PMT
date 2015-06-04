package wto.model;

import java.text.SimpleDateFormat;
import java.util.Comparator;
import java.util.Date;
import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OrderBy;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;

@Entity
@Table(name="image")
public class Image implements Comparable<Image> {
	@Id
	@GeneratedValue
	@Column(name="idimage")
	private Integer idimage;
	@Column(name="iduser")
	private int iduser;
	@Column(name="title")
	private String title;
	@Column(name="address")
	private String address;
	@Column(name="content")
	private String content;
	@Column(name="points")
	private int points;
    @Temporal(TemporalType.TIMESTAMP)
	@Column(name="create_time")
	private Date createTime;
    @Transient
    private int lastIndex;
    @Transient
    public static int imageCount;
    
    @ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name="iduser", referencedColumnName="iduser", insertable = false, updatable = false)
    private User user;
    
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idimage", fetch = FetchType.LAZY)
    @OrderBy("points desc")
    private List<Comment> comments;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idimage", fetch = FetchType.LAZY)
    private Set<Tag> tags;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idimage", fetch = FetchType.LAZY)
    private Set<ImageVote> votes;
    
	public Image(){}
	public Image(Integer idimage, int iduser, String title, String address, String content,
			int points, Date createTime) {
		super();
		this.idimage = idimage;
		this.iduser = iduser;
		this.title = title;
		this.address = address;
		this.content = content;
		this.points = points;
		this.createTime = createTime;
	}
	public Integer getIdimage() {
		return idimage;
	}
	public void setIdimage(Integer idimage) {
		this.idimage = idimage;
	}
	public int getIduser() {
		return iduser;
	}
	public void setIduser(int iduser) {
		this.iduser = iduser;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public int getPoints() {
		return points;
	}
	public void setPoints(int points) {
		this.points = points;
	}
	public Date getCreateTime() {
		return this.createTime;
	}
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	public String getCreateTimeAsString() {
		SimpleDateFormat dt = new SimpleDateFormat("dd.MM.yyyy 'at' HH:mm"); 
		return dt.format(this.createTime);
	}
	
	public List<Comment> getComments() {
		return comments;
	}
	public void setComments(List<Comment> comments) {
		this.comments = comments;
	}
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	public Set<Tag> getTags() {
		return tags;
	}
	public void setTags(Set<Tag> tags) {
		this.tags = tags;
	}
	public Set<ImageVote> getVotes() {
		return votes;
	}
	public void setVotes(Set<ImageVote> votes) {
		this.votes = votes;
	}
	public int getLastIndex() {
		return lastIndex;
	}
	public void setLastIndex(int lastIndex) {
		this.lastIndex = lastIndex;
	}
	
	@Override
	public int hashCode() {
        return this.getContent().hashCode();
    }

    @Override
    public boolean equals(final Object obj) {
    	 if (this == obj)
             return true;
         if (obj == null)
             return false;
         if (getClass() != obj.getClass())
             return false;
         final Image other = (Image) obj;
         if (content == null) {
             if (other.content != null)
                 return false;
         } else if (!content.equals(other.content))
             return false;
         return true;
    }
    
	public static Comparator<Image> getPointsComparator() {
        return new Comparator<Image>() {

			@Override
			public int compare(Image o1, Image o2) {
				if(o1.getPoints() < o2.getPoints())
					return 1;
				else if(o1.getPoints() > o2.getPoints())
					return -1;
				else
					return 0;
			}
        };
    }

    public static Comparator<Image> getTimeComparator() {
        return new Comparator<Image>() {

			@Override
			public int compare(Image o1, Image o2) {
				return o2.getCreateTime().compareTo(o1.getCreateTime());
			}
            
        };
    }
	@Override
	public int compareTo(Image o) {
		return this.getCreateTime().compareTo(o.getCreateTime());
	}
	
}
