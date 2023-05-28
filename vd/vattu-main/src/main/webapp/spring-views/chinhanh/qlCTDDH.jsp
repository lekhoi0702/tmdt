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
		<h1>Đơn hàng</h1>
		<nav>
			<ol class="breadcrumb">
				<li class="breadcrumb-item"><a href=""quanlykho/cn1/chinhanh.htm"">Kho</a></li>
				<li class="breadcrumb-item"><a href="quanlydondathang/cn1/chinhanh.htm">Đơn hàng</a></li>
				<li class="breadcrumb-item active">Chi tiết đơn đặt hàng</li>
			</ol>
		</nav>
	</div>
	<section class="section">
		<div class="row">
			<div class="col-lg-10">

				<div class="card">
					<div class="card-body">
						<h5 class="card-title">Chi tiết đơn đặt hàng</h5>

						<table class="table table-bordered">
							<thead>
								<tr class="table-secondary">
									<th scope="col">Mã số DDH</th>
									<th scope="col">tên VT</th>
									<th scope="col">Số lượng</th>
									<th scope="col">Đơn giá</th>
									<th scope="col">Thao tác</th>
								</tr>
							</thead>
							<tbody>




								<c:forEach items="${ddhs }" var="k">
									<tr>

										<th scope="row">${k.datHang.maSoDDH}</th>
										<td>${k.vatTu.tenVT}</td>
										<td>${k.soLuong}</td>

										<td>${k.donGia}</td>
										<td><a href="quanlychitietdondathang/cn1/chinhanh/xoa.htm?id=${k.datHang.maSoDDH}&vt=${k.vatTu.maVT}" class="fa fa-times-circle"></a></td>
									</tr>
								</c:forEach>

							</tbody>
						</table>

						<a href="quanlychitietdondathang/cn1/chinhanh/add.htm"
							class="fa fa-plus"></a>

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