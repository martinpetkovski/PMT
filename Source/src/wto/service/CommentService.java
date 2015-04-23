package wto.service;

import java.util.List;

import org.springframework.stereotype.Service;

import wto.model.Comment;

@Service
public interface CommentService {
	public List<Comment> getCommentByImage(Integer imageId);
	public List<Comment> getCommentByUser(Integer userId);
	public void saveComment(Comment theComment);
}

