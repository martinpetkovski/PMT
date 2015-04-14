package wto.repository;

import java.util.List;

import wto.model.Image;

public interface ImageRepository {
	public Integer create(Image entity);
	public Image read(Integer primaryKey);
	public void update(Image entity);
	public void delete(Integer primaryKey);
	public List<Image> readByUserId(int userId);
	public List<Image> readAll();
	public List<Image> readByQuery(String query);
	public Image randomImage();
}
