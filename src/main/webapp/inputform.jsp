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

	
	<P>暫保留Form
	
	<Form action="OrderInsertServlet.do" method="POST">
		<label for="">訂單編號：</label><br> <label for="">下單用戶編號：</label> <input
			type="text" name="order_user" value="1"> <br> <label
			for="">訂單備註：</label> <input type="text" name="order_note"
			value="EX:麵加大"> <br> <label for="">下單時間：</label><br>
		<label for="">預約取餐日期：</label> <input type="date"
			name="order_reserve_date" value="2018-02-14">
		(測試用，暫不提供預約取餐日期) <br> <label for="">店家編號：</label> <input
			type="text" name="order_store" value="2"> <br> <label
			for="">接單人員編號：</label> <input type="text" name="order_confirm_user"
			value="1"> <br> <label for="">接單時間：</label><br> <label
			for="">取餐時段：</label> <select name="order_takeout_period">
			<option value="A">A</option>
			<option value="B">B</option>
			<option value="C">D</option>
		</select> <br> <label for="">交易確認：</label> <input type="text"
			name="order_status" value="ordered"> <br> <label for="">確認時間：</label><br>
		<label for="">餐點編號1：</label> <input type="text" name="order_food"
			value="1"> <label for="">餐點數量1：</label> <input type="text"
			name="order_quantity" value="1"><br> 
			
<!-- 		<label for="">餐點編號2：</label> -->
<!-- 		<input type="text" name="order_food" value="2">  -->
<!-- 		<label for="">餐點數量2：</label> -->
<!-- 		<input type="text" name="order_quantity" value="2"><br>  -->
		<label for=""><input type="submit" value="新增訂單"></label>
	</Form>
	<p>
	<Form action="OrderQueryServlet.do" method="POST">
		<label for="">輸入使用者編號：</label>
		<input type="text" name="user_id"></input><br>
		
		<label for=""><input type="submit" value="查詢訂單"></label>
	</Form>
	<P>
	<Form action="OrderQueryServlet.do" method="POST">
		<label for="">選擇查詢訂單編號：</label> <select name="order_id">
			<option value="1">1</option>
			<option value="2">2</option>
			<option value="3">3</option>
		</select><br> <label for=""><input type="submit" value="查詢訂單"></label>
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
