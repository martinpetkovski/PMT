package wto.service;

import java.util.List;

import wto.model.Comment;
import wto.model.Image;
import wto.model.Tag;

public interface ImageService {
	public Image getImageById(Integer Id);
	public List<Image> getImagesByUserId(Integer userId);
	public List<Image> getAllImages();
	public List<Image> getImagesByQuery(String query);
	public List<Comment> getImageComments(Integer imageId);
	public List<Tag> getImageTags();
}
