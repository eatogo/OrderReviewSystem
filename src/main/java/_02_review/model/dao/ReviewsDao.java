package _02_review.model.dao;

import java.util.List;

import _02_review.model.REVIEWS;

public interface ReviewsDao {
	
	int insertReview(REVIEWS rwb);
	
	List<REVIEWS> getReviewsByFood(int review_food);
	
	int updateReviewCount(int food_id);
	
	int getReviewsNumber(); //計算推薦數

}
