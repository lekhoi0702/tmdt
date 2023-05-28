<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Sản Phẩm</title>
<base href="${pageContext.servletContext.contextPath}/">
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">

</head>

<style>
label {
	font-weight: bold;
}

body {
	background-color: #d7d7d7;
}

.form-message {
	color: red;
}
</style>
<body>
	<div style="margin-top: 7%;" class="container container card">
		<div class="card-header">
			<h4>Nhập Thông Tin SP</h4>
		</div>
		<form:form id="form-1" class="card-body"
			action="admin-home/formSanPham.htm" modelAttribute="td" method="post">
			<div class="form-row">
				<div class="form-group col-md-6">
					<label>ID</label> <br>
					<form:input minlenth="1" readonly="true" id="id" path="ID"
						type="text" placeholder=" " />
					<span class="form-message"></span>
				</div>

			</div>
			<label>Tên Loại </label>
			<br>
			<div class="form-row">
				<div class="select-menu">

					<h5>
						<select name="loai">
							<c:forEach items="${loaisps}" var="loai">

								<option value="${loai.id}"
									<c:if test="${loai.id == idloaiTU}" >selected</c:if>>
									${loai.tenLoai}</option>
							</c:forEach>
						</select>
					</h5>


				</div>
			</div>

			<div class="form-row">
				<div class="form-group col-md-6">
					<label>Tên Sản Phẩm</label> <br> <form:input id="tenTU"
						required="true" minlength="1" value="${ten}" path="ten"
						type="text" /> <span class="form-message"></span>
				</div>
				
				<div class="form-group col-md-6">
					<label>Mô tả Sản Phẩm</label> <br> <form:input id="tenTU"
						required="true" minlength="1"  path="moTa"
						type="textarea" /> <span class="form-message"></span>
				</div>
				
				<div class="col-md-6">

					<label>Giá Thành</label> <br> <form:input id="giathanh" min="1"
						max="1000000" value="${gia}" path="gia" type="number"
						required="true" /> <span class="form-message"></span>
				</div>
				<div class="col-md-6">

					<label>ĐVT</label> <br> <form:input id="dvt" value="${dvt}"
						path="dvt" type="text"  /> <span
						class="form-message"></span>
				</div>
				<div class="col-md-6">

					<form:input id="dvt" value="${dvt}"
						path="slTon" type="hidden"  /> <span
						class="form-message"></span>
				</div>
				<div class="col-md-6">

					<label>ICON</label> <br> <form:input id="dvt" value="${icon}"
						path="icon" type="file"  /> <span
						class="form-message"></span>
				</div>
			</div>

			<div class="card-footer">
				<button class="btn btn-primary" type="submit"
					name="${btnupdate ? 'btnupdate' : 'Insert'}">${btnupdate ? 'Update' : 'Insert'}</button>
				<a style="font-size: 16px; padding: 10px;" class="btn btn-secondary"
					href="admin-home/admin-qlthucdon.htm"> QUAY LẠI </a>
			</div>
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
						Validator.isRequired('#giathanh',
								'Vui lòng nhập vào giá thành'),
						Validator.isRequired('#tenTU',
								'Vui lòng nhập '),

				/*  Validator.isEmail('#email'),
				   Validator.minLength('#password', 6),
				   Validator.isRequired('#password_confirmation'),
				   Validator.isConfirmed('#password_confirmation',  function () {
				     return document.querySelector('#form-1 #password').value;
				   }, 'Mật khẩu nhập lại không chính xác') */
				],

			});

		});
	</script>

</body>
</html>