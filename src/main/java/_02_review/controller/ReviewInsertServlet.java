package _02_review.controller;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Date;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.google.gson.JsonObject;

import _01_order.model.dao.OrdersUpdateDao;
import _01_order.model.dao.OrdersUpdateDaoImpl;
import _02_review.model.REVIEWS;
import _02_review.model.dao.ReviewsDao;
import _02_review.model.dao.ReviewsDaoImpl;

@WebServlet("/ReviewInsertServlet.do")
public class ReviewInsertServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final String CONTENT_TYPE = "text/html; charset=UTF-8";
	JsonObject reviewAppJsonObj = null;

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		Gson gson = new Gson();

		// 以下解析由Web端送來之新增評價請求資訊(測試用)，實際應解析由App端送來之JSON格式字串
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

		// 以下接收App端送來新增評價請求資訊，json格式字串，待整合未完成
		// BufferedReader br = request.getReader();
		// StringBuilder reviewAppJson = new StringBuilder();
		// String line = null;
		// while ((line = br.readLine()) != null) {
		// reviewAppJson.append(line);
		// }
		// reviewAppJsonObj = gson.fromJson(gson.toJson(reviewAppJson),
		// JsonObject.class);
		// String action = reviewAppJsonObj.get("action").getAsString();
		// if (action.equals("insertReview")) {
		reviewsDao.insertReview(insertReviewBean);
		OrdersUpdateDao ordersUpdate = new OrdersUpdateDaoImpl();
		ordersUpdate.updateOrderStatus("unconfirmed_store", review_order); // 評價後更新orderStatu狀態
		writeText(response, insertReviewJsonStr);
		// }
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doPost(request, response);

	}

	// 測試response用，實際response內容尚未準備
	private void writeText(HttpServletResponse response, String outText) throws IOException {
		response.setContentType(CONTENT_TYPE);
		PrintWriter out = response.getWriter();
		out.print(outText);
		System.out.println("output: " + outText);
	}
}
