package postDAO;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class PostDAOImpl implements PostDAO {
	private static Connection con;
	static {
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:XE", "system", "1997");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	public int create(Post post) {
		int res = -1;
		try {
			PreparedStatement ps = con.prepareStatement("insert into posts values(inInc.NEXTVAL,?,?)");
			ps.setString(1, post.getTitle());
			ps.setString(2, post.getBody());
			res = ps.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return res;
	}

	@Override
	public int update(Post post) {
		int res = -1;
		try {
			PreparedStatement ps = con.prepareStatement("update posts set title = ?, body = ? where id = ?");
			ps.setInt(3, post.getId());
			ps.setString(1, post.getTitle());
			ps.setString(2, post.getBody());
			res = ps.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return res;
	}

	@Override
	public int delete(int id) {
		int res = -1;
		try {
			PreparedStatement ps = con.prepareStatement("delete from posts where id = ?");
			ps.setInt(1, id);
			res = ps.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return res;
	}

	@Override
	public List<Post> read() {
		ArrayList<Post> list = new ArrayList<>();
		try {
			PreparedStatement ps = con.prepareStatement("select * from posts");
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				list.add(new Post(rs.getInt(1), rs.getString(2), rs.getString(3)));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return list;
	}

	@Override
	public Post read(int id) {
		Post post = new Post();
		try {
			PreparedStatement ps = con.prepareStatement("select * from posts where id = ?");
			ps.setInt(1, id);
			ResultSet rs = ps.executeQuery();
			if (rs.next()) {
				post = new Post(rs.getInt(1), rs.getString(2), rs.getString(3));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return post;
	}

	@Override
	public List<Post> read(String title) {
		ArrayList<Post> list = new ArrayList<>();
		try {
			PreparedStatement ps = con.prepareStatement("select * from posts where title like ?");
			ps.setString(1, "%" + title + "%");
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				list.add(new Post(rs.getInt(1), rs.getString(2), rs.getString(3)));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return list;
	}

}
