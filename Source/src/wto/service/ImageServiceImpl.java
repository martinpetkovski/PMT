package wto.service;

import java.util.List;
import java.util.Set;
import java.util.SortedSet;
import java.util.TreeSet;

import javax.transaction.Transactional;

import wto.model.Image;
import wto.repository.ImageRepositoryImpl;

public class ImageServiceImpl implements ImageService {

	ImageRepositoryImpl img = new ImageRepositoryImpl();
	
	@Override
	@Transactional
	public Image getImageById(Integer Id) {
		return img.read(Id);
	}

	@Override
	@Transactional
	public List<Image> getImagesByUserId(Integer userId) {
		return img.readByUserId(userId);
	}

	@Override
	@Transactional
	public List<Image> getAllImages(String order) {
		return img.readAll(order);
	}

	@Override
	@Transactional
	public List<Image> getImagesByQuery(String query, String order) {
		return img.readByQuery(query, order);
	}

	@Override
	@Transactional
	public Image getRandomImage() {
		return img.randomImage();
	}

	@Override
	@Transactional
	public List<Image> getImagesByTag(String query, String order) {
		return img.readByTag(query, order);
	}

	@Transactional
	public Set<Image> getImagesByAll(String query, String order) {
		SortedSet<Image> images;
		
		if(order.equals("bypoints"))
			images = new TreeSet<Image>(Image.getPointsComparator());
		else if(order.equals("bynewest"))
			images = new TreeSet<Image>(Image.getTimeComparator());
		else // За рандом не работи
			images = new TreeSet<Image>();
		
		images.addAll(img.readByQuery(query,""));
		images.addAll(img.readByTag(query,""));
		images.addAll(img.readByUsername(query,""));
		
		return images;
	}
}
