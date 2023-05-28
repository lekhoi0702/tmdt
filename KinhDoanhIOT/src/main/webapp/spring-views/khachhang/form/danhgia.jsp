<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@include file="/common/taglib.jsp"%>
<head>
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
<style>
.review-form {
	padding: 20px;
	border: 1px solid #ccc;
	background: #f5f5f5;
}

.form-group {
	margin-bottom: 10px;
}

label {
	display: block;
	font-weight: bold;
}

input, select, textarea {
	padding: 5px;
	font-size: 16px;
	border: 1px solid #ccc;
	border-radius: 4px;
	width: 100%;
}

textarea {
	resize: vertical;
	min-height: 100px;
}

button[type="submit"] {
	padding: 10px 20px;
	background: #008080;
	color: #fff;
	border: none;
	border-radius: 4px;
	font-size: 16px;
	cursor: pointer;
}
</style>
</head>

<body>

	<div style="margin-top: 2%; border-style: groove;"
		class="container card">
		<div class="card-header"></div>
		<form:form modelAttribute="ct" class="review-form" action = "KHdanhGia.htm" method="post">
			<h2>Đánh giá sản phẩm</h2>

			<div class="form-group">
				<label for="rating">Đánh giá:</label> <form:select id="rating"
					path="diem" required="true">
					<option value="" disabled selected>Chọn đánh giá</option>
					<option value="5">Tuyệt vời</option>
					<option value="4">Rất tốt</option>
					<option value="3">Bình thường</option>
					<option value="2">Không tốt</option>
					<option value="1">Tệ</option>
				</form:select>
			</div>
			<div class="form-group">
				<label for="comment">Nhận xét:</label>
				<form:textarea id="comment" path="danhGia" rows="5" required="true"></form:textarea>
			</div>
			<form:input path="id" type="hidden"/>
			<button type="submit">Gửi đánh giá</button>
		</form:form>


	</div>

	<script src="<c:url value='/template/admin/validation.js'/>"></script>
	<script>
		document.addEventListener('DOMContentLoaded', function() {
			// Mong muốn của chúng ta
			Validator({
				form : '#form-1',
				formGroupSelector : '.form-group',
				errorSelector : '.form-message',
				rules : [
						Validator.isRequired('#fullname',
								'Vui lòng nhập tên đầy đủ của bạn'),
						Validator.isRequired('#diachi',
								'Vui lòng địa chỉ của bạn'),
						Validator.minLength('#cmnd', 9),
						Validator.minLength('#sdt', 10),
						Validator.isDate('#ngaysinh'),

				],
			/*   onSubmit: function (data) {
			      // Call API
			      console.log(data);
			    }  */

			});

		});
	</script>


</body>
</html>