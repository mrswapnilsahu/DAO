package postDAO;

import java.util.List;
import java.util.Scanner;

public class PostCRUD {

	public static void main(String[] args) {
		int choice, flag;
		PostDAOImpl impl = new PostDAOImpl();
		Scanner sc = new Scanner(System.in);
		flag = -1;
		do {
			System.out.println("Enter Choice:");
			System.out.println("1.Insert\n2.Update\n3.Delete\n4.Show All\n5.Search By Id\n6.Search By Title\n");
			choice = sc.nextInt();
			sc.nextLine();
			switch (choice) {
			case 1:
				System.out.println("Enter Post Title and Body : \n");
				int res = impl.create(new Post(0, sc.nextLine(), sc.nextLine()));
				System.out.println("Rows Inserted : " + res);
				break;
			case 2:
				System.out.println("Enter ID, Post Title and Body to Update: \n");
				res = impl.update(new Post(sc.nextInt(), sc.nextLine() + sc.nextLine(), sc.nextLine()));
				System.out.println("Rows Updated : " + res);
				break;
			case 3:
				System.out.println("Enter ID of post you want to delete : \n");
				res = impl.delete(sc.nextInt());
				System.out.println("Rows Deleted : " + res);
				break;
			case 4:
				List<Post> list = impl.read();
				for (Post post : list) {
					System.out.println(post);
				}
				break;
			case 5:
				System.out.println("Enter ID of post you want to search : \n");
				Post post = impl.read(sc.nextInt());
				System.out.println(post);
				break;
			case 6:
				System.out.println("Enter Title of post you want to search : \n");
				list = impl.read(sc.nextLine());
				for (Post post2 : list) {
					System.out.println(post2);
				}
				break;
			default:
				System.err.println("Invalid Input Given");
				break;
			}
			System.out.println("RERUN : 1.YES 2.NO\n");
			flag = sc.nextInt();
			sc.nextLine();
		} while (flag == 1);
	}

}
