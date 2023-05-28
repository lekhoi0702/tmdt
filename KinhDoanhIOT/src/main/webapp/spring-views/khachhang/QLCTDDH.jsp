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
				<div class="header-content d-flex justify-content-center">CHI
					TIẾT ĐƠN HÀNG</div>
				<!-- Shoping Cart Section Begin -->

				<div class="container">
					<div class="row">
						<div class="col-lg-12">
							<div class="shoping__cart__table">

								<table>
									<thead>
										<tr>
											<th>Mã đơn</th>
											<th>Mã sản phẩm</th>
											<th>Tên sản phẩm</th>
											<th>Số lượng</th>
											<th>Thành tiền</th>
										</tr>
									</thead>
									<tbody>

										<c:forEach var="ctddh" items="${chiTiet}">
											<tr>
												<td>${ctddh.ddh }</td>
												<td>${ctddh.sanPham }</td>
												<td>${ctddh.tenSP }</td>
												<td>${ctddh.soLuong }</td>
												<td id="tongtien">${ctddh.tongTien}</td>
												<c:if test="${danhGia}"> 
												<td>
													<div class="">
														<a style="font-size: 10px;" class="btn btn-primary"
															href="KHdanhGia.htm?idct=${ctddh.id}"> Đánh giá </a>
													</div>
												</td>
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
<script>
const tongTiens = document.querySelectorAll("#tongtien");
tongTiens.forEach(tongTien => {
  const formattedTongTien = Number(tongTien.textContent.replace(/\D/g, '')).toLocaleString('vi-VN', {style: 'currency', currency: 'VND'});
  tongTien.textContent = formattedTongTien;
});
</script>


