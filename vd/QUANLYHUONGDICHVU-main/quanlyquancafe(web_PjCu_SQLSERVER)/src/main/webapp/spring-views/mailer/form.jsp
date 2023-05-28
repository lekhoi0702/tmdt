<%@ page pageEncoding="utf-8"%>
<!DOCTYPE html>
<html>
<head>
<base href="${pageContext.servletContext.contextPath}/">
<meta charset="utf-8" />
<title>Send email</title>
<link rel="stylesheet"
	href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css"
	integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T"
	crossorigin="anonymous">
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css">
<style type="text/css">
.errors {
	color: red;
	font-style: italic;
}
</style>
<script src='https://www.google.com/recaptcha/api.js'></script>
</head>
<body>
	<h3 style="text-align: center;">${message}</h3>
	<div class="container col-7 offset-3 mt-5 ">
	<form action="mailer/form.htm" method="post">

		<br> <input value="nhonamstg@gmail.com" style="display: none;"
			name="from" placeholder="From">
		<div class="row mb-3">
			<label for="input1" class="col-md-4 col-lg-3 col-form-label">Nhập địa chỉ email của
				bạn</label>
			<div class="col-md-8 col-lg-9">
				<input name="to" type="email" class="form-control" required="required" value="${email}"
				id="input1" placeholder="name@example.com" style="display: inline-block; width: calc(100% - 85px)">
				<button type="submit" class="btn btn-primary" name="guima">Gửi mã</button>
				<!-- <a href="mailer/send.htm?guima" class="btn btn-primary">Gửi mã</a> -->
			</div>
			
		</div>
		
		<div class="row mb-3">
			<label class="col-md-4 col-lg-3 col-form-label">Mã Xác Nhận</label>
			<div class="col-md-8 col-lg-9">
				<input name="code" type="number"
					class="form-control" />
			</div>
		</div>
		
		<div class="row mb-3">
			<label for="newPassword"
				class="col-md-4 col-lg-3 col-form-label">Mật Khẩu
				Mới</label>
			<div class="col-md-8 col-lg-9">
				<input name="newpassword" type="password"
					class="form-control" />
			</div>
		</div>

		<div class="row mb-3">
			<label for="renewPassword"
				class="col-md-4 col-lg-3 col-form-label">Nhập Lại
				Mật Khẩu Mới</label>
			<div class="col-md-8 col-lg-9">
				<input name="renewpassword" type="password"
					class="form-control" />
			</div>
		</div>
		<div class="form-group">
			<div class="g-recaptcha"
				data-sitekey="6Ld1TpAfAAAAAOR_gKzy4BMOirgICntMGSp-XR5B"></div>
		</div>
		<div class="mb-1">
			<h6 class="mb-0 text-sm errors">${reCaptra}</h6>
		</div>

		<button type="submit" class="btn btn-primary">Xác Nhận</button>
		<a href="dang-nhap.htm?action=login" class="btn btn-secondary">Quay lại</a>
		
	</form>
	</div>
</body>
</html>