package _02_review.model.dao;

import java.util.List;

import _02_review.model.Reviews;

public interface ReviewsDao {
	
	int insertReview(Reviews rwb);
	
	List<Reviews> getReviewsByFood(int review_food);
	
	int updateReviewCount(int food_id);
	
	int getReviewsNumber(); //計算推薦數

}
