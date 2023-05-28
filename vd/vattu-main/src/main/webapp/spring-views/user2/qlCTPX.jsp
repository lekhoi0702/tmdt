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
<jsp:include page="/template/cn2/user/head.jsp" />

</head>
<body>

	<!-- ======= Header ======= -->
	<jsp:include page="/template/cn2/user/header.jsp" />

	<!-- End Header -->

	<!-- ======= Sidebar ======= -->
	<jsp:include page="/template/cn2/user/sidebar.jsp" />

	<!-- End Sidebar-->


	<main id="main" class="main">

	<div class="pagetitle">
		<h1>Kho</h1>
		<nav>
			<ol class="breadcrumb">
				<li class="breadcrumb-item"><a href="Kho.html">Kho</a></li>
				<li class="breadcrumb-item"><a href="Donhang.html">Đơn hàng</a></li>
				<li class="breadcrumb-item"><a href="Phieuxuat.html">Phiếu
						Xuất</a></li>
				<li class="breadcrumb-item active">Chi tiết phiếu xuất</li>
			</ol>
		</nav>
	</div>
	<!-- End Page Title -->

	<section class="section">
		<div class="row">
			<div class="col-lg-12">
				<div class="card">
					<div class="card-body">

						<!-- Bordered Table -->
						<table class="table table-bordered">
							<thead>
								<tr>
									<th scope="col">Mã PX</th>
									<th scope="col">Tên VT</th>
									<th scope="col">Số Lượng</th>
									<th scope="col">Đơn giá</th>
									<th scope="col">Thao tác</th>
								</tr>
							</thead>
							<tbody>


								<c:forEach items="${ddhs }" var="k">
									<tr>

										<th scope="row">${k.phieuXuat.maPX}</th>
										<td>${k.vatTu.tenVT}</td>
										<td>${k.soLuong}</td>
										<td>${k.donGia}VNĐ</td>

										<td><a href="quanlychitietphieuxuat/cn2/user/xoa.htm?id=${k.phieuXuat.maPX}&vt=${k.vatTu.maVT}" class="fa fa-times-circle"></a>
									</tr>
								</c:forEach>

								
							</tbody>
						</table>
						<a href="quanlychitietphieuxuat/cn2/user/add.htm" class="fa fa-plus"></a>
					</div>
				</div>

			</div>
		</div>
	</section>

	</main>
	<!-- End #main -->

	<jsp:include page="/template/cn2/user/footer.jsp" />


</body>

</html>