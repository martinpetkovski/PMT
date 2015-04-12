package wto.service;

import java.util.List;

import wto.model.Comment;

public interface CommentService {
	public List<Comment> getCommentByImage(Integer imageId);
	public List<Comment> getCommentByUser(Integer userId);
}
