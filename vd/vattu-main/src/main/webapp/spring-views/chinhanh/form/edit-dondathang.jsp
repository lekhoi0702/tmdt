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
<jsp:include page="/template/cn1/chinhanh/head.jsp" />

</head>
<body>

	<!-- ======= Header ======= -->
	<jsp:include page="/template/cn1/chinhanh/header.jsp" />

	<!-- End Header -->

	<!-- ======= Sidebar ======= -->
	<jsp:include page="/template/cn1/chinhanh/sidebar.jsp" />

	<!-- End Sidebar-->


	<main id="main" class="main">

	<div class="pagetitle">
		<h1>Đơn đặt hàng</h1>
		<nav>
			<ol class="breadcrumb">
				<li class="breadcrumb-item"><a
					href="quanlykho/cn1/chinhanh.htm">Kho</a></li>
				<li class="breadcrumb-item"><a
					href="quanlydondathang/cn1/chinhanh.htm">Đơn đặt hàng</a></li>
				<li class="breadcrumb-item active">Sửa đơn đặt hàng</li>
			</ol>
		</nav>
	</div>
	<section class="section">
		<div class="row">
			<div class="col-lg-10">

				<div class="card">
					<div class="card-body">
						<h5 class="card-title">Thông tin Đơn hàng</h5>
						${message}
						<!-- Multi Columns Form -->
						<form:form action="quanlydondathang/cn1/chinhanh/edit.htm"
							method="post" modelAttribute="dh" class="row g-3">

							<div class="col-md-6">
								<label for="inputEmail5" class="form-label">Mã số DDH</label>
								<form:input type="text" class="form-control" id="inputEmail5"
									path="maSoDDH" readonly="true"/>
							</div>
							
							<div class="col-md-6">
								<label for="inputPassword5" class="form-label">Mã Kho</label> <input
									type="text" class="form-control" id="inputPassword5"
									value="${idkho} " readonly />
							</div>
							<div class="col-md-12">
								<label for="inputPassword5" class="form-label">Nhà cung
									cấp</label>
								<form:input type="text" class="form-control" id="inputPassword5"
									path="nhaCC" />
							</div>






							<div class="col-md-12" >
								<div class="text-center" style="margin-top:20px">
									<button type="submit" class="btn btn-primary">Sửa</button>
									<button type="reset" class="btn btn-secondary">Reset</button>
								</div>
							</div>
						</form:form>
						<!-- End Multi Columns Form -->

					</div>
				</div>

			</div>
		</div>
	</section>

	</main>
	<!-- End #main -->
	<jsp:include page="/template/cn1/chinhanh/footer.jsp" />


</body>

</html>