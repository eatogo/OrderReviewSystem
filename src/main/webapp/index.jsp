<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<script type="text/javascript"></script>

<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Eatogo's Order and Review Testing</title>
</head>
<body>

	<h3>測試前須先手動於資料庫新增使用者表單資料(user)</h3>
	<h3>訂單功能測試</h3>
	<p>
	<Form action="OrderInsertServlet.do" method="POST">
		1.下單用戶編號：<input type="text" name="order_user" value="1"><br>
		2.訂單備註：<input type="text" name="order_note" value="EX:麵加大"><br>
		<!-- 	下單時間： -->
		3.預約取餐日期：<input type="date" name="order_reserve_date"
			value="2018-02-14"><br> (測試用，暫不提供預約取餐日期)<br>
		4.店家編號：<input type="text" name="order_store" value="2"><br>
		5.接單人員編號：<input type="text" name="order_confirm_user" value="1"><br>
		<!-- 	接單時間： -->
		6.取餐時段：<select name="order_takeout_period">
			<option>A
			<option>B
			<option>C
		</select><br> 7.交易確認：<input type="text" name="order_status"
			value="ordered"><br> (1,4,5,7 由系統取得)<br>
		<!-- 	確認時間： -->
		餐點編號：<input type="text" name="order_food" value="2"><br>
		餐點數量：<input type="text" name="order_quantity" value="5"><br>
		<input type="submit" value="新增訂單">
	</Form>
	<p>
	<Form action="OrderQueryServlet.do" method="POST">
		選擇查詢訂單編號(oder_id) <select name="order_id">
			<option>1
			<option>2
			<option>3
		</select><br> <input type="submit" value="查詢訂單">
	</Form>


	<hr>
	<h3>評價功能測試</h3>
	<p>
	<Form action="ReviewInsertServlet.do" method="POST">
		新增評價：<input type="text" name="action" value="insertReview"><br>
		評價用戶：<input type="text" name="review_user" value="1"><br>
		評價訂單：<input type="text" name="review_order" value="1"><br>
		推薦餐點：<input type="text" name="review_food" value="2"><br>
		<!-- 	評價時間：<input type="date" name="review_time"><br> -->
		文字評價：<input type="text" name="review_comment" value="非常好吃"><br>
		<input type="submit" value="新增評價">
	</Form>
	<p>
	<Form action="ReviewQueryServlet.do" method="POST">
		選擇查詢餐點編號(review_food) <select name="review_food">
			<option>1
			<option>2
			<option>3
		</select><br> <input type="submit" value="查詢評價">
	</Form>


</body>
</html>
