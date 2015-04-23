package wto.repository;

import java.util.List;

import wto.model.Comment;

public interface CommentRepository {
	public void create(Comment entity);
	public Comment read(Integer primaryKey);
	public void update(Comment entity);
	public void delete(Integer primaryKey);
	public List<Comment> readByImageId(Integer imageId);
	public List<Comment> readByUserId(Integer userId);
}
