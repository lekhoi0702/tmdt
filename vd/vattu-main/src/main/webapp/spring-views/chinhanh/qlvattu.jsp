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
		<h1>Vật tư</h1>
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
									<th scope="col">Mã vật tư</th>
									<th scope="col">Tên vật tư</th>
									<th scope="col">Đơn vị tính</th>
									<th scope="col">Số lượng tồn</th>
									<th scope="col">Thao tác</th>
								</tr>
							</thead>
							<tbody>
								<!-- 		<tr>
									<th scope="row">M01</th>
									<td>Máy giặt</td>
									<td>Cái</td>
									<td>40</td>
									<td><a href="edit-vattu.html"
										class="fa fa-pencil-square-o"></a> <a href="#"
										class="fa fa-times-circle"></a></td>

								</tr> -->
								<c:forEach items="${vts }" var="nv">
									<tr>

										<th scope="row">${nv.maVT}</th>
										<td>${nv.tenVT}</td>
										<td>${nv.donViTinh}</td>
										<td>${nv.soLuongTon}</td>

										<td><a href="quanlyvattu/cn1/chinhanh/edit.htm?id=${nv.maVT}"
											class="fa fa-pencil-square-o"></a> <a href="quanlyvattu/cn1/chinhanh/xoa.htm?id=${nv.maVT}"
											class="fa fa-times-circle"></a></td>
									</tr>
								</c:forEach>
							</tbody>
						</table>
						<!-- End Table with stripped rows -->
						<a href="quanlyvattu/cn1/chinhanh/add.htm" class="fa fa-plus"></a>
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