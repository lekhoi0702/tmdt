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
		<h1>Kho</h1>
		<nav>
			<ol class="breadcrumb">
				<li class="breadcrumb-item"><a
					href="quanlykho/cn1/chinhanh.htm">Kho</a></li>
				<li class="breadcrumb-item active">Thêm Kho</li>
			</ol>
		</nav>
	</div>
	<section class="section">
		<div class="row">
			<div class="col-lg-10">

				<div class="card">
					<div class="card-body">
						<h5 class="card-title">Thông tin kho</h5>
						${message}
						<!-- Multi Columns Form -->
						<form:form action="quanlykho/cn1/chinhanh/add.htm"
							method="post" modelAttribute="k" class="row g-3">
					
							<div class="col-md-5">
								<label for="inputEmail5" class="form-label">Mã Kho</label>
								<form:input type="text" class="form-control" id="inputEmail5" path="maKho" />
							</div>
							<div class="col-md-7">
								<label for="inputPassword5" class="form-label">Tên Kho</label>
								<form:input type="text" class="form-control" id="inputPassword5"
									path="tenKho" />
							</div>
							
							 <br>
							<div class="col-md-12">
								<label for="inputAddress5" class="form-label">Đia Chỉ</label>
								<form:input type="text" class="form-control" id="inputAddres5s"
									placeholder="TPHCM" path="diaChi" />
							</div>

							<div class="col-md-12" >
								<div class="text-center" style="margin-top:20px">
									<button type="submit" class="btn btn-primary">Tạo</button>
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