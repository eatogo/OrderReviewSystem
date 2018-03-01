package _02_review.model.dao;

import java.sql.Timestamp;

import _02_review.model.REVIEWS;

public class ReviewsDaoMain {

	public static void main(String[] args) {
		ReviewsDao reviewsDao = new ReviewsDaoImpl();
//		Reviews review = new Reviews();
		reviewsDao.updateReviewCount(1);
//		review.setReview_user(1);
//		review.setReview_order(1);
//		review.setReview_food(1);
//		review.setReview_time(new Timestamp(System.currentTimeMillis()));
//		review.setReview_comment("非常好吃");
//		System.out.println("成功取得一筆評價資訊" + review.toString());
//		try {
//			rwdao.insertReview(review);
//		} catch (RuntimeException ex) {
//			System.out.println(ex.getMessage());
//		}
	}
}
