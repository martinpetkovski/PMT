package wto.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import wto.model.Comment;
import wto.repository.CommentRepository;

@Service("CommentService")
public class CommentServiceImpl implements CommentService {

	@Autowired
	CommentRepository cri;
	
	public CommentServiceImpl (){}
	
	public CommentServiceImpl(CommentRepository commentRepository) {
		this.cri = commentRepository;
	}

	@Override
	public List<Comment> getCommentByImage(Integer imageId) {
		return cri.readByImageId(imageId);
	}

	@Override
	public List<Comment> getCommentByUser(Integer userId) {
		return cri.readByUserId(userId);
	}

	@Override
	public void saveComment(Comment theComment) {
		cri.create(theComment);
	}

}
