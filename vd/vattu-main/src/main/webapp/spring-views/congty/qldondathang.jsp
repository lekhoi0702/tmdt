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
<jsp:include page="/template/cn1/congty/head.jsp" />

</head>
<body>

	<!-- ======= Header ======= -->
	<jsp:include page="/template/cn1/congty/header.jsp" />

	<!-- End Header -->

	<!-- ======= Sidebar ======= -->
	<jsp:include page="/template/cn1/congty/sidebar.jsp" />

	<!-- End Sidebar-->


	<main id="main" class="main">

	<div class="pagetitle">
		<h1>Đơn Đặt Hàng</h1>
		<nav>
			<ol class="breadcrumb">
				<li class="breadcrumb-item"><a
					href="quanlykho/cn1/congty.htm">Kho</a></li>
				<li class="breadcrumb-item active">Đơn đặt hàng</li>
			</ol>
		</nav>
	</div>
	<!-- End Page Title -->
${message}
	<section class="section">
		<div class="row">
			<div class="col-lg-12">
				<div class="card">
					<div class="card-body">

						<!-- Bordered Table -->
						<table class="table table-bordered">
							<thead>
								<tr>
									<th scope="col">Mã số DDH</th>
									<th scope="col">Ngày nhập</th>
									<th scope="col">Nhà cung cấp</th>
									<th scope="col">Mã NV</th>
									<th scope="col">Mã Kho</th>
									<th scope="col">Xem</th>
								
								</tr>
							</thead>
							<tbody>
								<c:forEach items="${ddhs }" var="k">
									<tr>

										<th scope="row">${k.maSoDDH}</th>
										<td>${k.ngay}</td>
										<td>${k.nhaCC}</td>
										<td>${k.nhanVien.maNV}</td>
										<td>${k.kho.maKho}</td>
										<td><a
											href="quanlychitietdondathang/cn1/congty.htm?idddh=${k.maSoDDH}"
											class="fa fa-eye"><br>Chi Tiết DDH<br> </a> <a
											href="quanlyphieunhap/cn1/congty.htm?idDDH=${k.maSoDDH}&&idkho=${k.kho.maKho}"
											class="fa fa-indent"> <br>Xem phiếu nhập<br></a>
									
									</tr>
								</c:forEach>
							</tbody>
						</table>
					
					</div>
				</div>

			</div>
		</div>
	</section>

	</main>
	<!-- End #main -->
	<jsp:include page="/template/cn1/congty/footer.jsp" />


</body>

</html>