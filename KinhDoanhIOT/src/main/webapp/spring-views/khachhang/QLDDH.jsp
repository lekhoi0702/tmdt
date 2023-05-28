<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@include file="/common/taglib.jsp"%>
<head>
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
<jsp:include page="/common/webKH/head.jsp" />
</head>

<body>
	<jsp:include page="/common/webKH/header.jsp" />

	<!-- CONTENT -->
	<div class="container-fluid main">
		<div class="container">
			<div class="content">
				<div class="header-content d-flex justify-content-center">DANH
					SÁCH ĐƠN ĐẶT HÀNG</div>
				<!-- Shoping Cart Section Begin -->

				<div class="container">
					<div class="row">
						<div class="col-lg-12">
							<div class="shoping__cart__table">

								<table>
									<thead>
										<tr>
											<th>Mã đơn</th>
											<th>Ngày đặt hàng</th>
											<th>Tình trạng</th>
										</tr>
									</thead>
									<tbody>

										<c:forEach var="ddh" items="${list}">




											<tr>
												<td>${ddh.id }</td>
												<td>${ddh.ngayThucHien}</td>
												<td><c:choose>
														<c:when test="${ddh.tinhTrang == 0}">
												Chờ xác nhận
												</c:when>
														<c:when test="${ddh.tinhTrang == 1}">
												Đã xác nhận đơn hàng
												</c:when>
														<c:when test="${ddh.tinhTrang == 2}">
												Đang chuẩn bi hàng
												</c:when>
														<c:when test="${ddh.tinhTrang == 3}">
												Đang giao hàng
												</c:when>

														<c:when test="${ddh.tinhTrang == 4}">
												Giao hàng thành công
												</c:when>
														<c:when test="${ddh.tinhTrang == -1}">
												Đã hủy
												</c:when>
														<c:when test="${ddh.tinhTrang == -2}">
												Đổi trả
												</c:when>
													</c:choose></td>

												<td>
													<div class="">
														<a class="site-btn"
															href="KHdonhang.htm?linkView&id=${ddh.id}"> XEM </a>
													</div>
												</td>
												<c:if test="${ddh.tinhTrang <3 && ddh.tinhTrang >=0}">
													<td><a style="font-size: 10px;"
														class="btn btn-primary"
														href="KHdonhang.htm?linkHuy&id=${ddh.id}"> Hủy ĐH</a></td>
												</c:if>
											</tr>




										</c:forEach>


									</tbody>
								</table>

							</div>
						</div>
					</div>

				</div>
			</div>

			<!-- Shoping Cart Section End -->

		</div>

	</div>







	<jsp:include page="/common/webKH/footer.jsp" />
	<script
		src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
	<script
		src="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>

	<script type="text/javascript">
		$('.set-bg').each(function() {
			var bg = $(this).data('setbg');
			$(this).css('background-image', 'url(' + bg + ')');
		});
	</script>

</body>

</html>


