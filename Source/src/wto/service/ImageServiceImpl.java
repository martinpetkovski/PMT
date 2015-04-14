package wto.service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import wto.model.Image;
import wto.repository.ImageRepositoryImpl;

public class ImageServiceImpl implements ImageService {

	ImageRepositoryImpl img = new ImageRepositoryImpl();
	
	@Override
	public Image getImageById(Integer Id) {
		return img.read(Id);
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
	public Image getRandomImage() {
		return img.randomImage();
	}

	@Override
	public List<Image> getImagesByTag(String query, String order) {
		return img.readByTag(query, order);
	}

	public Set<Image> getImagesByAll(String query, String order) {
		Set<Image> images = new HashSet<Image>();
		images.addAll(img.readByQuery(query, order));
		images.addAll(img.readByTag(query, order));
		images.addAll(img.readByUsername(query, order));
		return images;
	}
}
