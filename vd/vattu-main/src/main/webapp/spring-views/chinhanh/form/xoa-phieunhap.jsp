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


	<main id="main" class="main">
	<div class="pagetitle">
      <h1>Đơn hàng</h1>
		<nav>
			<ol class="breadcrumb">
				<li class="breadcrumb-item"><a href="quanlykho/cn1/chinhanh.htm">Kho</a></li>
				<li class="breadcrumb-item"><a href="quanlyphieunhap/cn1/chinhanh.htm">Phiếu nhập</a></li>
				<li class="breadcrumb-item active">Xóa phiếu nhập</li>
			</ol>
		</nav>
    </div><!-- End Page Title -->
	<!-- End Sidebar-->
<section class="section">
		<div class="row">
			<div class="col-lg-10">

				<div class="card">
					<div class="card-body">
						


						${message}
						
						
						<!-- Multi Columns Form -->
						<form action="quanlyphieunhap/cn1/chinhanh/xoa.htm?id=${id }" method="post"
							class="row g-3">
							
							<div class="col-md-12 text-center" >
								Bạn có đồng ý xoá PN ${id }
								<br>
								<label for="inputEmail5" class="form-label">Xác Nhận( Nhập "YES" vào khung này)</label>
								<input type="text" class="form-control" name="xacNhan" />
							</div>
							<div class="col-md-12">
								<div class="text-center">
								<button type="submit" class="btn btn-primary">Xoá</button>
							</div>
							</div>
						</form>
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