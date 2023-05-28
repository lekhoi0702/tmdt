<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@include file="/common/taglib.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>


<link rel="stylesheet"
	href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css"
	integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T"
	crossorigin="anonymous">
<link rel="shortcut icon" type="image/png"
	href="https://www.logo.wine/a/logo/Costa_Coffee/Costa_Coffee-Logo.wine.svg" />

<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css">

<script
	src="//cdnjs.cloudflare.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
<link href="<c:url value='/template/login/style.css' />"
	rel="stylesheet" type="text/css" media="all" />

<style type="text/css">
.errors {
	color: red;
	font-style: italic;
}
</style>

<script src='https://www.google.com/recaptcha/api.js'></script>

<meta charset="utf-8">
<meta name="viewport"
	content="width=device-width, initial-scale=1, shrink-to-fit=no">
<title>Đăng nhập</title>
</head>
<body>

	<main class="container"> <header class="row text-center">

	</header> <section class="row">
	<div class="col-6 offset-3 mt-5 ">
		<form action="" method="post">
			<div class="card">
				<div class="card-header">
					<img style="height: 50px; width: 50px;"
						src="<c:url value='/common/images/logo_highland.png'/>"
						alt="highland"> Đăng nhập vào HIGHLAND

				</div>
				<c:if test="${not empty message}">
					<div class="alert alert-${alert}">${message}</div>
				</c:if>
				<div class="card-body">
					<div class="form-group">
						<label for="username">Tên Đăng Nhập</label> <input type="text"
							class="form-control" name="userName" id="userName"
							aria-describedby="helpId" placeholder="Tên Đăng Nhập">
						<!-- <small
							id="usernameHid" class="form-text text-muted">Username is
							invalid</small> -->
					</div>
					<div class="form-group">
						<label for="password">Mật Khẩu</label> <input type="password"
							class="form-control" name="passwd" id="passwd"
							aria-describedby="helpId" placeholder="Mật Khẩu">
						<!-- <small
							id="passwordHid" class="form-text text-muted">Password is
							invalid</small> -->
					</div>

					<div class="form-group">
						<div class="g-recaptcha"
							data-sitekey="6Ld1TpAfAAAAAOR_gKzy4BMOirgICntMGSp-XR5B"></div>
					</div>
					<label class="mb-1">
						<h6 class="mb-0 text-sm errors">${reCaptra}</h6>
					</label> <input type="hidden" value="login" name="action" />

					
				</div>
				<div class="card-footer text-muted">
					<button type="submit" class="btn btn-primary">Đăng Nhập</button>

					<button class="btn btn-secondary" type="reset">Làm Mới</button>
					<a href="mailer/form.htm" class="btn btn-primary">Quên mật khẩu?</a>

				</div>
			</div>
		</form>
	</div>
	</section> <footer class="row"></footer> </main>
	<!-- Optional JavaScript -->
	<!-- jQuery first, then Popper.js, then Bootstrap JS -->
	<script src="https://code.jquery.com/jquery-3.3.1.slim.min.js"
		integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo"
		crossorigin="anonymous"></script>
	<script
		src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js"
		integrity="sha384-UO2eT0CpHqdSJQ6hJty5KVphtPhzWj9WO1clHTMGa3JDZwrnQq4sF86dIHNDz0W1"
		crossorigin="anonymous"></script>
	<script
		src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js"
		integrity="sha384-JjSmVgyd0p3pXB1rRibZUAYoIIy6OrQ6VrjIEaFf/nJGzIxFDsf4x0xIM+B07jRM"
		crossorigin="anonymous"></script>
</body>
</html>