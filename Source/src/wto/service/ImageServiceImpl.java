package wto.service;

import java.util.List;

import wto.model.Comment;
import wto.model.Image;
import wto.model.Tag;
import wto.repository.ImageRepositoryImpl;

public class ImageServiceImpl implements ImageService {

	ImageRepositoryImpl img = new ImageRepositoryImpl();
	CommentServiceImpl cmt = new CommentServiceImpl();
	
	@Override
	public Image getImageById(Integer Id) {
		return img.read(Id);
	}

	@Override
	public List<Image> getImagesByUserId(Integer userId) {
		return img.readByUserId(userId);
	}

	@Override
	public List<Image> getAllImages() {
		return img.readAll();
	}

	@Override
	public List<Image> getImagesByQuery(String query) {
		return img.readByQuery(query);
	}

	@Override
	public List<Comment> getImageComments(Integer imageId) {
		return cmt.getCommentByImage(imageId);
	}

	@Override
	public List<Tag> getImageTags() {
		return null;
	}

}
