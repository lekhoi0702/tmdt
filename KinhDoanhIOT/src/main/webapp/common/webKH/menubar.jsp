<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@include file="/common/taglib.jsp"%>


 <%-- <div class="sidebar">
	<div class="logo_content">
		<div class="logo">
			<div class="logo_name">Danh Mục Loại Sản Phẩm</div>
		</div>

	</div>
	<ul class="nav_list">
		<c:forEach var="loai" items="${LoaiSPs}">

			<li><a href="khachhangsearch-loai.htm?id=${loai.id}"> <i
					class="fas fa-table"></i> <span class="links_name">${loai.tenLoai}</span>
			</a></li>
		</c:forEach>
	</ul>



</div>  --%>
<!-- Hero Section Begin -->
<section class="hero hero-normal">
	<div class="container">
		<div class="row">
			<div class="col-lg-3">
				<div class="hero__categories">
					<div class="hero__categories__all">
						<i class="fa fa-bars"></i> <span>Tất cả danh mục</span>
					</div>
					<ul>
						<c:forEach var="loai" items="${LoaiSPs}">

							<li><a href="khachhangsearch-loai.htm?id=${loai.id}"> ${loai.tenLoai}
							</a></li>
						</c:forEach>
					</ul>
				</div>
			</div>
			
		</div>
	</div>
</section>
<!-- Hero Section End -->

