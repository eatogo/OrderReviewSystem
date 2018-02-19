package _02_review.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

import _02_review.model.Reviews;
import _02_review.model.dao.ReviewsDao;
import _02_review.model.dao.ReviewsDaoImpl;

@WebServlet("/ReviewQueryServlet.do")
public class ReviewQueryServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final String CONTENT_TYPE = "text/html; charset=UTF-8";

	public ReviewQueryServlet() {
		super();
	}
	
	@Override
	public void init() throws ServletException {
		super.init();
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");

		Gson gson = new Gson();
		
		/*/
		 * 尚未處理App端送來之查詢評價請求，，查無評價之回應
		 * 測試解析Web端送來之查詢評價請求資訊 
		 */
		 
		Integer review_food = Integer.parseInt(request.getParameter("review_food"));
		ReviewsDao reviewsDao = new ReviewsDaoImpl();
		List<Reviews> reviewsListBean = reviewsDao.getReviewsByFood(review_food);
		System.out.println("查詢評價資訊: " + reviewsListBean.toString());
		String reviewsListJson = gson.toJson(reviewsListBean); // Object to JSON
		System.out.println("查詢評價資訊JSON: " + reviewsListJson);
		writeText(response, reviewsListJson);

	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doPost(request, response);
	}

	private void writeText(HttpServletResponse response, String outText) throws IOException {
		response.setContentType(CONTENT_TYPE);
		PrintWriter out = response.getWriter();
		out.print(outText);
		System.out.println("output: " + outText);
	}

}
