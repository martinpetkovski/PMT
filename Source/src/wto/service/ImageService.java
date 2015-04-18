package wto.service;

import java.util.List;
import java.util.Set;

import wto.model.Image;

public interface ImageService {
	public Image getImageById(Integer Id);
	public List<Image> getImagesByUserId(Integer userId);
	public List<Image> getAllImages(String order);
	public List<Image> getImagesByQuery(String query, String order);
	public List<Image> getImagesByTag(String query, String order);
	public Set<Image> getImagesByAll(String query, String order);
	public Integer getRandomImage();
}
