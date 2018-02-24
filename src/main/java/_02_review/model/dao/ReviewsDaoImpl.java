package _02_review.model.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

import _00_utility.Db;
import _02_review.model.Reviews;

public class ReviewsDaoImpl implements ReviewsDao {
	Connection conn;
	DataSource ds = null;

	 public ReviewsDaoImpl() {
	 try {
	 Context ctx = new InitialContext();
	 ds = (DataSource) ctx.lookup(Db.JNDI_DB_NAME);
	 } catch (Exception e) {
	 throw new RuntimeException(e.getMessage());
	 }
	 }

//	public ReviewsDaoImpl() {
//		super();
//		try {
//			Class.forName("com.mysql.jdbc.Driver");
//		} catch (ClassNotFoundException e) {
//			e.printStackTrace();
//		}
//	}

	@Override
	public int insertReview(Reviews rb) {
		int count = 0;
		String insertReviewSql = "INSERT INTO REVIEWS ("
				+ " review_id, review_user, review_order, review_food, review_time, " + " review_comment) "
				+ "VALUES(null, ?, ?, ?, ?, ?)";
		String reviewCountSql = "SELECT food_review_count FROM FOODS WHERE food_id = ? ";
		String updateReviewCountSql = "UPDATE FOODS SET food_review_count = ? + 1 WHERE food_id = ?";
		
		try (
				// Connection conn = DriverManager.getConnection(Db.URLALL);
				Connection conn = ds.getConnection();
				PreparedStatement ps = conn.prepareStatement(insertReviewSql);
				PreparedStatement ps1 = conn.prepareStatement(reviewCountSql);
				PreparedStatement ps2 = conn.prepareStatement(updateReviewCountSql);
				) {
			ps.setInt(1, rb.getReview_user());
			ps.setInt(2, rb.getReview_order());
			ps.setInt(3, rb.getReview_food());
			ps.setTimestamp(4, new Timestamp(rb.getReview_time().getTime()));
			ps.setString(5, rb.getReview_comment());
			count = ps.executeUpdate();
			System.out.println("成功新增一筆評價");
			ps1.setInt(1, rb.getReview_food());
			ResultSet rset = ps1.executeQuery();
			rset.next();
			long reviewCount = rset.getLong("food_review_count");
			ps2.setLong(1, reviewCount);
			ps2.setInt(2, rb.getReview_food());
			count = ps2.executeUpdate();
			System.out.println("推薦數+1後為: " + (reviewCount + 1));
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return count;
	}

	@Override
	public int updateReviewCount(int food_id) {
		int count = 0;
		String reviewCountSql = "SELECT food_review_count FROM FOODS WHERE food_id = ? ";
		String updateReviewCountSql = "UPDATE FOODS SET food_review_count = ? + 1 WHERE food_id = ?";
		try (
				 Connection conn = ds.getConnection();
//				Connection conn = DriverManager.getConnection(Db.URLALL);
				PreparedStatement ps1 = conn.prepareStatement(reviewCountSql);
				PreparedStatement ps2 = conn.prepareStatement(updateReviewCountSql);
			) {
			ps1.setInt(1, food_id);
			ResultSet rset = ps1.executeQuery();
			// while (rset.next()) {
			rset.next();
			long reviewCount = rset.getLong("food_review_count");
			System.out.println("long:" + reviewCount);
			ps2.setLong(1, reviewCount);
			ps2.setInt(2, food_id);
			count = ps2.executeUpdate();
			// }
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return count;
	}

	@Override
	public List<Reviews> getReviewsByFood(int review_food) {
		String getReviewByFoodSql = "SELECT review_id, review_user, review_time, review_comment "
				+ " FROM reviews WHERE review_food = ? ORDER BY review_time DESC ";
		// String getLast5ReviewByFoodSql = "SELECT review_id, review_user, review_time,
		// review_comment "
		// + " FROM reviews WHERE review_food = ? ORDER BY review_time DESC LIMIT 5 ";

		List<Reviews> reviewlist = new ArrayList<>();
		Reviews review = null;
		try (
				// Connection conn = DriverManager.getConnection(Db.URLALL);
				Connection conn = ds.getConnection();
				PreparedStatement ps = conn.prepareStatement(getReviewByFoodSql);) {
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

	@Override
	public int getReviewsNumber() {
		String getReviewsNumberSql = "SELECT count(*) FROM REVIEWS";
		/*
		 * / 1.取得推薦餐點或搜尋之餐點 2.才能個別計算數量
		 */
		return 0;
	}
}
