<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@include file="/common/taglib.jsp"%>

<!-- Footer Section Begin -->
<footer class="footer spad">
	<div class="container">
		<div class="row">
			<div class="col-lg-3 col-md-6 col-sm-6">
				<div class="footer__about">
					<div class="footer__about__logo">
						<a href="./index.html"><img src="img/logoPtit.png" alt=""></a>
					</div>
					<ul>
						<li>Địa chỉ: 69/69 Man Thiện</li>
						<li>Số điện thoại: 666.666</li>
						<li>Email: thuongmaidientu@ptit.com</li>
					</ul>
				</div>
			</div>
			<div class="col-lg-4 col-md-6 col-sm-6 offset-lg-1"></div>
		</div>
		<div class="row">
			<div class="col-lg-12">
				<div class="footer__copyright"></div>



			</div>
		</div>
	</div>

	<div class="logo_content">
		<div class="logo">
			<div class="logo_name">Danh Mục Nhãn</div>
		</div>

	</div>
	<ul class="nav_list">
		<c:forEach var="nhan" items="${Nhans}">

			<li><a href="khachhangtimkiem.htm?searchnhan=${nhan.tenNhan}">
					<i class="fas fa-table"></i> <span class="links_name">${nhan.tenNhan}</span>
			</a></li>
		</c:forEach>
	</ul>
</footer>
<!-- Footer Section End -->
<!-- Js Plugins -->
"
<!-- <c:url value='/template/khachhang/css/bootstrap.min.css'/>" -->
<script
	src="<c:url value='/template/khachhang/js/jquery-3.3.1.min.js'/>"></script>
<script src="<c:url value='/template/khachhang/"js/bootstrap.min.js'/>"></script>
<script
	src="<c:url value='/template/khachhang/js/jquery.nice-select.min.js'/>"></script>
<script src="<c:url value='/template/khachhang/"js/jquery-ui.min.js'/>"></script>
<script src="<c:url value='/template/khachhang/js/jquery.slicknav.js'/>"></script>
<script src="<c:url value='/template/khachhang/js/mixitup.min.js'/>"></script>
<script
	src="<c:url value='/template/khachhang/js/owl.carousel.min.js'/>"></script>
<script src="<c:url value='/template/khachhang/js/main.js'/>"></script>

