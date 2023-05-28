<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@include file="/common/taglib.jsp"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>

<!DOCTYPE html>
<html lang="en">

<head>
<meta charset="UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>Highland Coffee</title>
<base href="${pageContext.servletContext.contextPath}/">
<link rel="stylesheet"
	href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.4/css/all.min.css">
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
<link
	href="https://fonts.googleapis.com/css?family=Open+Sans:300,300i,400,400i,600,600i,700,700i|Nunito:300,300i,400,400i,600,600i,700,700i|Poppins:300,300i,400,400i,500,500i,600,600i,700,700i"
	rel="stylesheet">

<link href="<c:url value='/template/web/styles.css'/>" rel="stylesheet"
	type="text/css">

</head>

<body>
	<jsp:include page="/common/web/header.jsp" />
	<jsp:include page="/common/web/menubar.jsp" />
	<!-- CONTENT -->
	<div class="container-fluid main">
		<div class="content">
			<div class="header-content d-flex justify-content-center">
				THANH TOÁN</div>
			<div class="row">
				<div class="col-lg-4">
					<div class="icon-header d-flex ">
						<div class="header-icon">
							<div
								class="icon-coffee d-flex flex-column align-items-center icon-coffee-small">
								<i class="fas fa-mug-hot"></i>
							</div>
							<span style="font-size: 12px;">Bàn trống</span>
						</div>
						<div class="header-icon">
							<div
								class="icon-coffee d-flex flex-column align-items-center icon-coffee-small"
								style="background-color: rgb(248, 106, 106);">
								<i class="fas fa-mug-hot"></i>
							</div>
							<span style="font-size: 12px;">Đã có khách</span>
						</div>
						<div class="header-icon">
							<div
								class="icon-coffee d-flex flex-column align-items-center icon-coffee-small"
								style="border: #2519ce ridge 3px;">
								<i class="fas fa-mug-hot"></i>
							</div>
							<span style="font-size: 12px;">Đang chọn</span>
						</div>
						<div class="header-icon">
							<div
								class="icon-coffee d-flex flex-column align-items-center icon-coffee-small"
								style="opacity: 0.5;">
								<i class="fas fa-mug-hot"></i>
							</div>
							<span style="font-size: 12px;">Đã đặt</span>
						</div>

					</div>
					<div style="width: 100%; font-weight: 600; font-size: 20px;">
						QUẢN LÝ BÀN</div>
					<!--   LOGO bàn -->
					<div class="table-icon shadow-box">

						<c:forEach var="b" items="${bans}">
							<c:choose>
								<c:when test="${ b.tinhTrang == 0}">
									<div class="icon-coffee d-flex flex-column align-items-center">
										<i class="fas fa-mug-hot"></i> <span
											style="font-size: 14px; color: black;">${b.id }</span>
									</div>
								</c:when>
								<c:when test="${ b.tinhTrang == 1}">
									<div class="icon-coffee d-flex flex-column align-items-center"
										style="background-color: rgb(248, 106, 106);">
										<i class="fas fa-mug-hot"></i> <span
											style="font-size: 14px; color: black;">${b.id }</span>
									</div>
								</c:when>
								<c:when test="${ b.tinhTrang == 2}">
									<div class="icon-coffee d-flex flex-column align-items-center"
										style="border: #2519ce ridge 3px;">
										<i class="fas fa-mug-hot"></i> <span
											style="font-size: 14px; color: black;">${b.id }</span>
									</div>
								</c:when>
								<c:when test="${ b.tinhTrang == 3}">
									<div class="icon-coffee d-flex flex-column align-items-center"
										style="opacity: 0.5;">
										<i class="fas fa-mug-hot"></i> <span
											style="font-size: 14px; color: black;">${b.id }</span>
									</div>
								</c:when>
							</c:choose>


						</c:forEach>


					</div>
				</div>
				<!-- end  LOGO bàn -->
				<div class="col-lg-8">
					<div class="original-info d-flex justify-content-center">
						<div class=" mg-0-40">Họ tên nhân viên: ${NHANVIEN.hoTen}</div>
						<!-- CAN TG -->
						<div class=" mg-0-40">
							Ngày: <span id="date-now"></span>
						</div>
						<div class=" mg-0-40">
							Thời gian: <span id="current-time"></span>

						</div>
						<!-- CAN TG -->
					</div>

					<table class="table table-striped shadow-box">
						<thead>
							<tr>
								<th>Loại</th>
								<th>Tên</th>
								<th>Số Lượng</th>

								<th>Giá</th>
								<th></th>
							</tr>
						</thead>
						<tbody>
							<!-- MENU MON AN -->
							<c:forEach items="${banHD.cthds }" var="cthd" varStatus="theCount">
								<tr>
									<td>${cthd.thucDon.loaiThucUong.tenLoai }</td>
									<td>${cthd.thucDon.ten }</td>
									<td><input id="inp-4" type="number"
										value="${cthd.soLuong }"
										style="width: 64px; text-align: center;" disabled></td>

									<td>${cthd.thucDon.gia }</td>
									<td class="d-flex justify-content-center align-items-center">


										<div class="">
											<!-- Button trigger modal -->
											<form action="thanh-toan.htm?id=${theCount.index}" method="post">
												<button style="font-size: 10px;"
													class="btn btn-primary" data-toggle="modal"
													data-target="#exampleModal2" name="xoa">XÓA</button>
											</form>
							
										</div>

									</td>
								</tr>
								<!-- end MENU MON AN -->
							</c:forEach>

						</tbody>
					</table>
					<div class="footer-pay">
						<div class="row">



							<div class="col-lg-6 d-flex flex-column shadow-box ">
								<div class="d-flex justify-content-between">
									<div>Tổng tiền:</div>
									<label id="tongTien">${tongtien} </label>
								</div>
								<div class="d-flex justify-content-between">
									<label for="">Tiền nhận của khách:</label> <input onkeyup="autoTienThua(this.value)" type="number"
										style="max-width: 100px; text-align: right;">
								</div>
								<div class="d-flex justify-content-between">
									<div>Tiền thừa:</div>
									<label id="tienThua">000000</label>
								</div>

							</div>
							<form action="thanh-toan.htm" method="post">
								<h5>
									${message }<br> <label>Bàn số: </label> <select name="Ban" id="ban">
										<c:forEach items="${banids}" var="B">

											<option value="${B}"
												<c:if test="${B == idBanHT }"> selected</c:if>>${B}</option>
										</c:forEach>
									</select>
									<button name="xem">Xem</button>
								</h5>
								<div class="col-lg-6 d-flex justify-content-around"
									style="margin-left: 50px">


									<button class="pay-print-icon shadow-box" name="thanhtoan">
										<i class="fas fa-cash-register"></i>
									</button>
									<button class="pay-print-icon shadow-box" name="print">
										<i class="fas fa-print"></i>
									</button>
								</div>
							</form>
						</div>
					</div>
				</div>
			</div>
		</div>

		<jsp:include page="/common/web/footer.jsp" />
		<script
			src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
		<script
			src="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
		<script src="<c:url value='/template/web/scipts.js'/>"></script>
</body>
</html>