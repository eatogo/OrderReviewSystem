package _02_review.controller;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Date;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.google.gson.JsonObject;

import _02_review.model.REVIEWS;
import _02_review.model.dao.ReviewsDao;
import _02_review.model.dao.ReviewsDaoImpl;

@WebServlet("/ReviewInsertServlet.do")
public class ReviewInsertServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final String CONTENT_TYPE = "text/html; charset=UTF-8";
	// private List<Reviews> reviewsList;

	public ReviewInsertServlet() {
		super();
	}

	@Override
	public void init() throws ServletException {
		super.init();
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");

		// 接收App端送來之json格式新增評價請求資訊，未完成
//		BufferedReader br = request.getReader();
//		StringBuilder reviewAppJson = new StringBuilder();
//		String line = null;
//		while ((line = br.readLine()) != null) {
//			reviewAppJson.append(line);
//		}
//		System.out.println("App input: " + reviewAppJson);
		Gson gson = new Gson();
//		JsonObject reviewJsonObj = gson.fromJson(reviewAppJson.toString(), JsonObject.class); // 轉為json物件

		// 解析由Web端送來之新增評價請求資訊
		Integer review_user = Integer.parseInt(request.getParameter("review_user"));
		Integer review_order = Integer.parseInt(request.getParameter("review_order"));
		Integer review_food = Integer.parseInt(request.getParameter("review_food"));
		Date review_time = new Date(System.currentTimeMillis());
		String review_comment = request.getParameter("review_comment");
		REVIEWS insertReviewBean = new REVIEWS(null, review_user, review_order, review_food, review_time,
				review_comment);
		System.out.println("新增評價資訊: " + insertReviewBean);
		String insertReviewJsonStr = gson.toJson(insertReviewBean); // Object to JSON
		System.out.println("新增評價資訊JSON" + insertReviewJsonStr);

		ReviewsDao reviewsDao = new ReviewsDaoImpl();

		// 以下為整合App端新增與查詢請求準備，未完成
//		String action = reviewJsonObj.get("action").getAsString();
//		if (action.equals("getReviewsByFood")) {
//			List<Reviews> reviewsList = reviewsDao.getReviewsByFood(review_food);
//			writeText(response, gson.toJson(reviewsList));
//		} else if (action.equals("insertReview")) {
//			String reviewJson = reviewJsonObj.get(" ").getAsString();
//			Reviews review = gson.fromJson(reviewJson, Reviews.class);

//			int count = 0;
//			count = reviewsDao.insertReview(insertReviewBean);
			reviewsDao.insertReview(insertReviewBean);
			// System.out.println("count:" + count);
			writeText(response, insertReviewJsonStr);
			// Gson gson = new Gson();
			// writeText(response, gson.toJson(insertReviewObj));
			// writeText(response, new Gson().toJson(insertReviewObj)); // 簡化前兩行寫法
		}
//	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doPost(request, response);

	}

	// 測試response用，實際response待修改
	private void writeText(HttpServletResponse response, String outText) throws IOException {
		response.setContentType(CONTENT_TYPE);
		PrintWriter out = response.getWriter();
		out.print(outText);
		System.out.println("output: " + outText);
	}
}
