<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>

<%@include file="/common/taglib.jsp"%>
<!DOCTYPE html>
<html lang="en">


<head>
<meta charset="UTF-8" />
<meta http-equiv="X-UA-Compatible" content="IE=edge" />
<meta name="viewport" content="width=device-width, initial-scale=1.0" />
<base href="${pageContext.servletContext.contextPath}/">
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
<title>Thêm tài khoản</title>
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
	<div style="margin-top: 5%;" class="container card">
		<div class="card-header">
			<h4>Nhập Thông Tài Khoản</h4>
		</div>
		<form:form id="form-1" class="card-body"
			action="admin-home/formTaiKhoan.htm" modelAttribute="tk"
			method="post">
			<div class="form-row">
				<div class="form-group col-md-6">
					<label>UserName</label> <br>
					<form:input  maxlength="20" minlength="3" readonly="${fixmanv}"
						pattern="^[a-zA-Z].{3,20}$" required="true" id="username"
						path="userName" type="text" />
					<span class="form-message"></span>
				</div>
			</div>
			<div class="form-group"> 
				<label>Mã Nhân Viên</label><br> <input id="manv" ${doc}
					value="${maNV}" name="manv" min="1" pattern="^[0-9]{1,10}$"
					required="true" type="number" class="form-control" /> <span
					class="form-message"></span>
			</div>
			<div class="form-row">
		
				

				<%-- <div class="form-group col-md-4">
					<label for="password" class="form-label">Mật khẩu</label>
					<form:input id="password" path="passwd" type="password"
						placeholder="Nhập mật khẩu" class="form-control" />
					<span class="form-message"></span>
				</div>

				<div class="form-group col-md-4">
					<label for="password_confirmation" class="form-label">Nhập
						lại mật khẩu</label> <input id="password_confirmation"
						name="password_confirmation" placeholder="Nhập lại mật khẩu"
						type="password" class="form-control"> <span
						class="form-message"></span>
				</div> --%>

			</div>
			<div class="form-row">
				<div class="form-group col-md-4">
					<div class="select-menu">

						<h5>
							<select name="chucvu">
								<c:forEach items="${chucvus}" var="cv">

									<option value="${cv.id}"
										<c:if test="${cv.id == idCV}" >selected</c:if>>
										${cv.tenChucVu}</option>

								</c:forEach>
							</select>
						</h5>
					</div>
				</div>
				<div class="form-group col-md-4">
					<label>Email</label> <br>
					<form:input id="email" path="email" type="email" />
					<span class="form-message"></span>
				</div>
			</div>
			<div class="card-footer">
				<button class="btn btn-primary" type="submit"
					name="${btnupdate ? 'btnupdate' : 'Insert'}">${btnupdate ? 'Update' : 'Insert'}</button>
				<a style="font-size: 16px; padding: 10px;" class="btn btn-secondary"
					href="admin-home/admin-taikhoan.htm"> QUAY LẠI </a>
			</div>
		</form:form>
	</div>
	<script src="<c:url value='/template/admin/validation.js'/>"></script>
	<script>
		document
				.addEventListener(
						'DOMContentLoaded',
						function() {
							// Mong muốn của chúng ta
							Validator({
								form : '#form-1',
								formGroupSelector : '.form-group',
								errorSelector : '.form-message',
								rules : [
										/* Validator.isRequired('#username',
												'Vui lòng nhập tên đăng nhập'), */
										Validator.isRequired('#manv',
												'Vui lòng nhập mã nhân viên'),
										Validator.isRequired('#email',
												'Vui lòng địa chỉ email'),
										Validator.minLength('#cmnd', 9),
										Validator.minLength('#sdt', 10),
										/* Validator.minLength('#password', 6),
										Validator
												.isRequired('#password_confirmation'),
										Validator
												.isConfirmed(
														'#password_confirmation', */
														function() {
															return document
																	.querySelector('#form-1 #password').value;
														},
														'Mật khẩu nhập lại không chính xác') ],

							});

						});
	</script>
</body>
</html>