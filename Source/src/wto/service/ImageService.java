package wto.service;

import java.util.Date;
import java.util.List;
import java.util.Set;

import org.springframework.stereotype.Service;

import wto.model.Image;

@Service
public interface ImageService {
	public Image getImageById(Integer Id);
	public Image getImageByAddress(String address);
	public List<String> getNextPrevAddress(Date id, int points, String order);
	public List<Image> getImagesByUserId(Integer userId);
	public List<Image> getAllImages(String order, int page);
	public List<Image> getImagesByQuery(String query, String order, int page);
	public List<Image> getImagesByTag(String query, String order, int page);
	public Set<Image> getImagesByAll(String query, String order, int page);
	public String getRandomImage();
	public int numberOfImages();
	public void voteImage(int iduser, int idimage, boolean voteType);
	void voteComment(int iduser, int idcomment, boolean voteType);
	void saveImage(Integer idimage, int iduser, String title, String address, String content, int points, Date createTime, String tags);
}
