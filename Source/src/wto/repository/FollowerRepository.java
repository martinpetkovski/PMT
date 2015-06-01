package wto.repository;

import wto.model.Follower;

public interface FollowerRepository {
	public void create(Follower follower);

	boolean isFollowing(int follower, int followee);
}
