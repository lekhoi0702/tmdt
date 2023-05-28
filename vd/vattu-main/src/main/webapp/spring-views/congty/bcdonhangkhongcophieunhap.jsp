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

	<main id="mains" class="mains">
	<!-- End Page Title -->

	<section class="section">
		<div class="row">
			<div class="col-lg-12">
				<div class="card">
					<div class="card-body">
						<h4
							style="margin: 20px 0; text-align: center; text-transform: uppercase;">
							<b>CỘNG HOÀ XÃ HỘI CHỦ NGHĨA VIỆT NAM</b>
						</h4>
						<h5 style="margin: 20px 0; text-align: center;">
							<b>Độc Lập - Tự Do - Hạnh Phúc</b>
						</h5>
						<br>
						<h6 style="margin-left:890px;">............,ngày.....tháng.....năm
							202......</h6>
						<br> <br>
						<h3
							style="margin: 20px; text-align: center; text-transform: uppercase">
							<strong>Báo cáo <br> Đơn hàng không có phiếu nhập
							</strong>
						</h3>
						<h5 style="margin: 20px; text-align: center">
							<b>Chi nhánh 1</b>
						</h5>
						<br> <br>
						<p>
							<b>Họ và Tên : ${USERMODEL.hoTen}</b>
						</p>
						<p>
							<b>Chi Nhánh : ${USERMODEL.maChiNhanh}</b>
						</p>
						<br>
						<table class="table table-bordered border-bottom-0 border-dark">
							<thead class="reports head">
								<tr>
									<th scope="col">Mã số DDH</th>
									<th scope="col">Ngày nhập</th>
									<th scope="col">Nhà cung cấp</th>
									<th scope="col">Họ tên nhân viên</th>
									<th scope="col">Tên vật tư</th>
									<th scope="col">Số lượng đặt</th>
									<th scope="col">Đơn giá</th>
								</tr>
							</thead>
							<tbody class="reports body">
								<c:forEach items="${ddhs }" var="nv">
									<tr>

										<th scope="row">${nv.maSoDDH}</th>
										<td>${nv.ngay}</td>
										<td>${nv.nhaCC}</td>

										<td>${nv.hoTen}</td>
										<td>${nv.tenVT}</td>
										<td>${nv.soLuong}</td>
										<td>${nv.donGia}</td>
									</tr>
								</c:forEach>

							</tbody>
						</table>
						<br> <br>
						<p style="margin-left:950px;">
							<b> Người báo cáo</b>
						</p>
						<br> <br> <br> <br>

					</div>
				</div>

			</div>
		</div>
	</section>

	</main>

</body>

</html>