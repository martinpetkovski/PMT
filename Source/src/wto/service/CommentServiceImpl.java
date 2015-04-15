package wto.service;

import java.util.List;

import javax.transaction.Transactional;

import wto.model.Comment;
import wto.repository.CommentRepositoryImpl;

public class CommentServiceImpl implements CommentService {

	CommentRepositoryImpl cri = new CommentRepositoryImpl();
	
	@Override
	@Transactional
	public List<Comment> getCommentByImage(Integer imageId) {
		return cri.readByImageId(imageId);
	}

	@Override
	@Transactional
	public List<Comment> getCommentByUser(Integer userId) {
		return cri.readByUserId(userId);
	}

}
