package postDAO;

import java.util.List;

public interface PostDAO {
	int create(Post post);

	int update(Post post);

	int delete(int id);

	List<Post> read();

	Post read(int id);

	List<Post> read(String title);
}
