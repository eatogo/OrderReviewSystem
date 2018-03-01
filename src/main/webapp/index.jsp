<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<script type="text/javascript"></script>
<style>
label {
	display: inline-block;
	width: 150px;
	text-align: right;
}
</style>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Eatogo's Order and Review Testing</title>
</head>
<body>

	<h3>訂單功能測試</h3>
	<p>測試前需先執行_00_init下之CreateUserDate.java新增使用者(user)假資料</p>
	<p>
	<Form action="OrderInsertServlet.do" method="POST">
	測試經由 Servlet init方法送出一筆訂單<br> 
	<input type="submit" value="確認新增">
	</Form>
			
	<Form action="OrderInsertServletApp.do" method="POST">
	測試經由 Servlet init方法送出一筆訂單(模擬App)for實際狀況<br>
	(暫無法使用)<br> 
	<input type="submit" value="確認新增">
	</Form>
	
	
	<hr>
		<label for=""><input type="submit" value="新增訂單"></label>
	</Form>
	<p>
	<Form action="OrderQueryServlet.do" method="POST">
		<label for="">輸入使用者編號：</label>
		<input type="text" name="user_id"></input><br>
		
		<label for=""><input type="submit" value="查詢訂單"></label>
	</Form>
	<P>


	<hr>
	<h3>評價功能測試</h3>
	<p>
	<Form action="ReviewInsertServlet.do" method="POST">
		新增評價：<input type="text" name="action" value="insertReview"><br>
		評價用戶：<input type="text" name="review_user" value="1"><br>
		評價訂單：<input type="text" name="review_order" value="1"><br>
		推薦餐點：<input type="text" name="review_food" value="2"><br>
		<!-- 	評價時間：<input type="date" name="review_time"><br> -->
		文字評價：<input type="text" name="review_comment" placeholder="非常好吃"><br>
		<input type="submit" value="新增評價">
	</Form>
	<p>
	<Form action="ReviewQueryServlet.do" method="POST">
		選擇查詢餐點編號(review_food) <select name="review_food">
			<option value="1">1</option>
			<option value="2">2</option>
			<option value="3">3</option>
		</select><br> <input type="submit" value="查詢評價">
	</Form>


</body>
</html>
