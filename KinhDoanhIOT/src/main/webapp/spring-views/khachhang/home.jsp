<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@include file="/common/taglib.jsp"%>
<head>

<jsp:include page="/common/webKH/head.jsp" />

</head>

<body>
	<jsp:include page="/common/webKH/header.jsp" />
	<jsp:include page="/common/webKH/searchbar.jsp" />

	<%-- 	<jsp:include page="/common/webKH/menubar.jsp" />

 --%>
	<!-- CONTENT -->
	<!-- <div class="container-fluid main"> -->
	<div class="container">
		<div class="content">
			<!-- Product Section Begin -->
			<section class="product spad">

				<div class="col-lg-12 col-md-7">


					<div class="filter__item">
						<div class="row">
							<div class="col-lg-4 col-md-5">
								<!-- <div class="filter__sort">
										<span>Sắp xếp</span> <select>
											<option value="0">Mặc định</option>
										</select>
									</div> -->
							</div>
							<div class="col-lg-4 col-md-4">
								<div class="filter__found">
									<h6>
										<span> ${SanPhams.size()} </span> Sản phẩm
									</h6>
								</div>
							</div>

						</div>
					</div>
					<div class="row">


						<c:forEach var="th" items="${SanPhams}">

							<div class="col-lg-3 col-md-6 col-sm-6">
								<div class="product__item" style="">
									<div class="product__item__pic set-bg"
										data-setbg="<c:url value='/common/images/products/${th.icon}'/>">
										<ul class="product__item__pic__hover">

											<li><a href="KH-giohangthem.htm?id=${th.ID}&sl=1"><i
													class="fa fa-shopping-cart"></i></a></li>
										</ul>
									</div>
									<div id="giasp" class="product__item__text">
										<h6>
											<a href="khachhang-ctsp.htm?id=${th.ID} ">${th.ten}</a>
										</h6>
										<h5>${th.gia}</h5>
										<c:if test="${th.slTon<=0}">
											<h6>Sản phẩm đã hết hàng</h6>
										</c:if>
									</div>
								</div>
							</div>

						</c:forEach>











					</div>
				</div>
				<div class="product__pagination">
					<a href="#">1</a> <a href="#">2</a> <a href="#">3</a> <a href="#"><i
						class="fa fa-long-arrow-right"></i></a>
				</div>
			</section>
			<!-- Product Section End -->

		</div>

	</div>
	<!-- </div> -->







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
  const priceElements = document.querySelectorAll('#giasp h5'); // Lấy tất cả các thẻ HTML có id là "giasp" và thẻ h5
  priceElements.forEach((priceElement) => { // Lặp qua danh sách các thẻ HTML
    const priceValue = parseInt(priceElement.innerText); // Lấy giá trị từ thẻ HTML và chuyển đổi thành số nguyên
    const priceFormatted = priceValue.toLocaleString('vi-VN', { style: 'currency', currency: 'VND' }); // Định dạng giá trị tiền tệ
    priceElement.innerText = priceFormatted; // Gán kết quả định dạng vào thẻ HTML
  });
</script>