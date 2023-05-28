<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@include file="/common/taglib.jsp"%>
<!-- HEADER -->
<header class="header">
	<div class="header__top">
		<div class="container">
			<div class="row">
				<div class="col-lg-6">
					<div class="header__top__left"></div>
				</div>
				<div class="col-lg-6">
					<div class="header__top__right">
						<div class="header__top__right__social">
							<a href="#"><i class="fa fa-facebook"></i></a> <a href="#"><i
								class="fa fa-twitter"></i></a> <a href="#"><i
								class="fa fa-linkedin"></i></a> <a href="#"><i
								class="fa fa-pinterest-p"></i></a>
						</div>
						<c:if test="${USERKHMODEL == null }">
							<div class="header__top__right__auth">
								<a href="/dichvu/khachhang-login.htm?action=login"><i
									class="fa fa-user"></i> Đăng nhập</a>
							</div>
						</c:if>
						<c:if test="${USERKHMODEL != null && USERKHMODEL.userName != null}">
							<div class="header__top__right__auth">
								<a href="#"><i
									class="fa fa-user"></i>Xin Chào ${USERKHMODEL.userName} </a>
									<a href="KHuser.htm">Thông tin tài khoản</a>
							</div>
						</c:if>
					</div>
				</div>
			</div>
		</div>
	</div>
	<div class="container">
		<div class="row">
			<div class="col-lg-3">
				<div class="header__logo">
					<a href="khachhanghome.htm"><img
						src="<c:url value='/common/images/logo.webp'/>" alt=""></a>
				</div>
			</div>
			<div class="col-lg-6">
				<nav class="header__menu">
					<ul>
					
						<li><a href="KHdonhang.htm">Đơn hàng của tôi</a></li>
						<li class="active"><a href="khachhanghome.htm">Sản phẩm</a></li>
						<c:if test="${USERKHMODEL != null && USERKHMODEL.userName != null}">
							<li><a href="/dichvu/khachhang-login.htm?action=logout">Đăng xuất</a></li>
						</c:if>
						
						
					</ul>


				</nav>
			</div>
			<div class="col-lg-3">
				<div class="header__cart">
					<ul>
						
						<li><a href="KH-giohang.htm"><i
								class="fa fa-shopping-bag"></i> <span id="myData"> ${slGioHang} </span></a></li>
					</ul>
				</div>
			</div>
		</div>
		<div class="humberger__open">
			<i class="fa fa-bars"></i>
		</div>
	</div>
</header>
<!-- END_HEADER -->
<!-- Breadcrumb Section Begin -->
<section class="breadcrumb-section set-bg"
	data-setbg="<c:url value='/template/khachhang/img/bread.jpg'/>">
	<div class="container">
		<div class="row">
			<div class="col-lg-12 text-center">
				<div class="breadcrumb__text">
					<h2>SHOP BÁN THIẾT BỊ IOT</h2>

				</div>
				
			</div>
		</div>
	</div>
	<input type="hidden" id="makh" value="${USERKHMODEL.maKH}">
</section>

<!-- Breadcrumb Section End -->
