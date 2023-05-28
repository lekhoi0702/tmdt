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
							style="margin: 20px; text-align: center; text-transform: uppercaguse">
							<strong>Báo cáo <br> Tổng hợp nhập xuất
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
									<th scope="col">Ngày</th>
									<th scope="col">Nhập</th>
									<th scope="col">Tỉ lệ nhập</th>
									<th scope="col">Xuất</th>
									<th scope="col">Tỉ lệ xuất</th>
								</tr>
							</thead>
							<tbody class="reports body">
								<c:forEach items="${thnxs }" var="nv">
									<tr>
										<th scope="row">${nv.ngay}</th>
										<td>${nv.nhap}</td>
										<td>${nv.tiLeNhap}</td>
										<td>${nv.xuat}</td>
										<td>${nv.tiLeXuat}</td>
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
	<!-- End #main -->

	<jsp:include page="/template/cn1/congty/footer.jsp" />
</body>

</html>