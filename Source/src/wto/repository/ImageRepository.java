package wto.repository;

import java.util.List;

import wto.model.Image;

public interface ImageRepository {
	public Integer create(Image entity);
	public Image read(Integer primaryKey);
	public void update(Image entity);
	public void delete(Integer primaryKey);
	public List<Image> readByUserId(int userId);
	public List<Image> readAll(String order);
	public List<Image> readByQuery(String query, String order);
	public List<Image> readByTag(String query, String order);
	public Integer randomImage();
	List<Image> readByUsername(String username, String order);
}
