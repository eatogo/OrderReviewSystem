package _02_review.model.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import _00_utility.Db;
import _02_review.model.Reviews;

public class ReviewsDaoImpl implements ReviewsDao {

	public ReviewsDaoImpl() {
		super();
		try {
			Class.forName("com.mysql.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}

	@Override
	public int insertReview(Reviews rb) {
		int count = 0;
		String insertReviewSql = "INSERT INTO REVIEWS (" + " review_id, review_user, review_order, review_food, review_time, "
				+ " review_comment) " + "VALUES(null, ?, ?, ?, ?, ?)";
		try (Connection conn = DriverManager.getConnection(Db.URLALL);
				// Connection conn = DataSource.getConnection(); 待改用DataSource
				PreparedStatement ps = conn.prepareStatement(insertReviewSql);) {
			ps.setInt(1, rb.getReview_user());
			ps.setInt(2, rb.getReview_order());
			ps.setInt(3, rb.getReview_food());
			ps.setTimestamp(4, new Timestamp(rb.getReview_time().getTime()));
			ps.setString(5, rb.getReview_comment());
			count = ps.executeUpdate();
			System.out.println("成功新增一筆評價");
		} catch (SQLException ex) {
			String msg = ex.getMessage();
			if (msg.startsWith("Duplicate entry"))
				throw new RuntimeException("鍵值重複: " + msg);
			else {
				System.out.println(ex);
			}
		}
		return count;
	}

	@Override
	public List<Reviews> getReviewsByFood(int review_food) {
		String getReviewByFoodSql = "SELECT review_id, review_user, review_time, review_comment "
				+ " FROM reviews WHERE review_food = ? ORDER BY review_time DESC ";
//		String getLast5ReviewByFoodSql = "SELECT review_id, review_user, review_time, review_comment "
//				+ " FROM reviews WHERE review_food = ? ORDER BY review_time DESC LIMIT 5 ";
		
		List<Reviews> reviewlist = new ArrayList<>();
		Reviews review = null;
		try (
			Connection conn = DriverManager.getConnection(Db.URLALL);
			PreparedStatement ps = conn.prepareStatement(getReviewByFoodSql);
			) {
			ps.setInt(1, review_food);
			ResultSet rset = ps.executeQuery();
			while (rset.next()) {
				Integer review_id = rset.getInt("review_id");
				Integer review_user = rset.getInt("review_user");
				Timestamp review_time = rset.getTimestamp("review_time");
				String review_comment = rset.getString("review_comment");
				review = new Reviews(review_id, review_user, review_time, review_comment);
				reviewlist.add(review);
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return reviewlist;
	}
}
