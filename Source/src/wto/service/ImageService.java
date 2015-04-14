package wto.service;

import java.util.List;

import wto.model.Image;

public interface ImageService {
	public Image getImageById(Integer Id);
	public List<Image> getImagesByUserId(Integer userId);
	public List<Image> getAllImages();
	public List<Image> getImagesByQuery(String query);
	public Image getRandomImage();
}
