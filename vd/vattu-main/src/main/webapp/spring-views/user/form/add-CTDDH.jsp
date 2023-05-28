<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@include file="/common/taglib.jsp"%>
<!DOCTYPE html>
<html>


<head>

<meta charset="utf-8">
<meta content="width=device-width, initial-scale=1.0" name="viewport">

<title>QLVT</title>
<!-- ======= Head ======= -->
<jsp:include page="/template/cn1/user/head.jsp" />

</head>
<body>

	<!-- ======= Header ======= -->
	<jsp:include page="/template/cn1/user/header.jsp" />

	<!-- End Header -->

	<!-- ======= Sidebar ======= -->
	<jsp:include page="/template/cn1/user/sidebar.jsp" />

	<!-- End Sidebar-->


	<main id="main" class="main">

	<div class="pagetitle">
		<h1>Đơn hàng</h1>
		<nav>
			<ol class="breadcrumb">
				<li class="breadcrumb-item"><a href=""quanlykho/cn1/user.htm"">Kho</a></li>
				<li class="breadcrumb-item"><a
					href="quanlydondathang/cn1/user.htm">Đơn hàng</a></li>
				<li class="breadcrumb-item"><a
					href="quanlychitietdondathang/cn1/user.htm">Chi tiết đơn đặt hàng</a></li>
				<li class="breadcrumb-item active">Thêm chi tiết đơn đặt hàng</li>
			</ol>
		</nav>
	</div>
	<section class="section">
		<div class="row">
			<div class="col-lg-10">

				<div class="card">
					<div class="card-body">
						<h5 class="card-title">Thông tin chi tiết đơn đặt hàng</h5>


						<!-- End Multi Columns Form -->
						<form:form action="quanlychitietdondathang/cn1/user/add.htm"
							method="post" modelAttribute="ct" class="row g-3">
							<div class="col-md-8">
								<label for="inputEmail5" class="form-label">Tên VT</label> <select
									class="form-control" id="inputEmail5" name="maVT">
									<c:forEach items="${vattus }" var="vt">
										<option value="${vt.maVT}" label="${vt.tenVT}"></option>
									</c:forEach>
								</select>
							</div>
							<div class="col-md-4">
								<label for="inputPassword5" class="form-label">Số lượng</label>
								<form:input type="text" class="form-control" id="inputEmail5"
									path="soLuong" />

							</div>

							<div class="col-md-5">
								<label for="inputState" class="form-label">Đơn giá</label> <br>
								<form:input path="donGia" />
								<br>
							</div>
							<div class="col-md-12">
								<div class="text-center" style="margin-top: 20px">
									<button type="submit" class="btn btn-primary">Tạo</button>
									<button type="reset" class="btn btn-secondary">Reset</button>
								</div>
							</div>
						</form:form>
					</div>
				</div>

			</div>
		</div>
	</section>

	</main>
	<!-- End #main -->

	<!-- End #main -->
	<jsp:include page="/template/cn1/user/footer.jsp" />


</body>

</html>