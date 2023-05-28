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
		<h1>Kho</h1>
		<nav>
			<ol class="breadcrumb">
				<li class="breadcrumb-item"><a
					href="quanlykho/cn1/chinhanh.htm">Kho</a></li>
				<li class="breadcrumb-item active">Xóa Kho</li>
			</ol>
		</nav>
	</div>
	<!-- End Sidebar-->
	
	<section class="section">
		<div class="row">
			<div class="col-lg-5">

				<div class="card">
					<div class="card-body">
						<h5 class="card-title">Xóa vật tư</h5>
						${message} ${message} 
						
						<form action="quanlykho/cn1/chinhanh/xoa.htm?id=${id }" method="post"
							class="row g-3">
							<div  class="col-md-12" style="align-items=center;">
								<label for="inputEmail5" class="form-label">Bạn có đồng ý xoá kho ${id }</label>
							</div>
							
							<div class="col-md-12">
								<label for="inputEmail5" class="form-label">Xác Nhận( Nhập "YES" vào khung này)</label>
								<input type="text" class="form-control" name="xacNhan" />
							</div>
							
							<div class="text-center" style="margin-top:15px; margin-left:170px">
								<button type="submit" class="btn btn-primary">Xoá</button>
								
							</div>
						</form>
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