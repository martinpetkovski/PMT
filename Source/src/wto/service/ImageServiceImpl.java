package wto.service;

import java.util.Date;
import java.util.List;
import java.util.Set;
import java.util.SortedSet;
import java.util.TreeSet;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import wto.model.CommentVote;
import wto.model.Image;
import wto.model.ImageVote;
import wto.repository.CommentVoteRepository;
import wto.repository.ImageRepository;
import wto.repository.ImageVoteRepository;

@Service("ImageService")
public class ImageServiceImpl implements ImageService {

	@Autowired
	ImageRepository img;
	@Autowired
	ImageVoteRepository ivr;
	@Autowired
	CommentVoteRepository cvr;
	
	public ImageServiceImpl() {}
	
	public ImageServiceImpl(ImageRepository imageRepository) {
		this.img = imageRepository;
	}

	@Override
	public Image getImageById(Integer Id) {
		return img.read(Id);
	}
	
	@Override
	public Image getImageByAddress(String address) {
		return img.readByAddress(address);
	}

	@Override
	public List<Image> getImagesByUserId(Integer userId) {
		return img.readByUserId(userId);
	}

	@Override
	public List<Image> getAllImages(String order) {
		return img.readAll(order);
	}

	@Override
	public List<Image> getImagesByQuery(String query, String order) {
		return img.readByQuery(query, order);
	}

	@Override
	public String getRandomImage() {
		return img.randomImage();
	}

	@Override
	public List<Image> getImagesByTag(String query, String order) {
		return img.readByTag(query, order);
	}

	public Set<Image> getImagesByAll(String query, String order) {
		SortedSet<Image> images;
		
		if(order.equals("bypoints"))
			images = new TreeSet<Image>(Image.getPointsComparator());
		else if(order.equals("bynewest"))
			images = new TreeSet<Image>(Image.getTimeComparator());
		else 
			images = new TreeSet<Image>();
		
		images.addAll(img.readByQuery(query,""));
		images.addAll(img.readByTag(query,""));
		images.addAll(img.readByUsername(query,""));
		
		return images;
	}

	@Override
	public List<String> getNextPrevAddress(Date id, int points, String order) {
		return img.readNextPrev(id, points, order);
	}

	@Override
	public void voteImage(int iduser, int idimage, boolean voteType) {
		ivr.create(new ImageVote(null, iduser, idimage, voteType, null));
		
	}
	
	@Override
	public void voteComment(int iduser, int idcomment, boolean voteType) {
		cvr.create(new CommentVote(null, iduser, idcomment, voteType, null));
	}

	@Override
	public void saveImage(Integer idimage, int iduser, String title,
			String address, String content, int points, Date createTime) {
		img.create(new Image(idimage, iduser, title, address, content, points, createTime));
		
	}
	
}
