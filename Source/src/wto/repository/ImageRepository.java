package wto.repository;

import java.util.Date;
import java.util.List;

import wto.model.Image;

public interface ImageRepository {
	public Integer create(Image entity);
	public Image read(Integer primaryKey);
	public void update(Image entity);
	public void delete(Integer primaryKey);
	public Image readByAddress(String address);
	public List<String> readNextPrev(Date id, int points, String order);
	public List<Image> readByUserId(int userId);
	public List<Image> readAll(String order);
	public List<Image> readByQuery(String query, String order);
	public List<Image> readByTag(String query, String order);
	public String randomImage();
	List<Image> readByUsername(String username, String order);
}
