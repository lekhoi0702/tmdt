<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@include file="/common/taglib.jsp"%>
<!DOCTYPE html>
<html lang="en">

<head>
<jsp:include page="/common/admin/head.jsp" />
</head>

<body>
	<jsp:include page="/common/admin/header.jsp" />
	<jsp:include page="/common/admin/menubar.jsp" />

	<!-- CONTEND -->
	<div class="container-fluid main">
		<div class="container">
			<div class="header-content d-flex justify-content-center">QUẢN
				LÝ NHẬP HÀNG</div>

			<h4>${message}</h4>
			<div>
				<a href="/dichvu/admin-home/formNhapHang.htm"> <!-- 	<button type="button" class="btn btn-warning">Thêm</button> -->
					<button style="width: 184px; height: 33px; margin-bottom: 5px;"
						type="button" class="btn btn-primary">Thêm Đơn Nhập Hàng</button>
				</a>

			</div>
			<%-- <jsp:useBean id="pagedListHolder" scope="request"
				type="org.springframework.beans.support.PagedListHolder" /> --%>
			<c:url value="admin-nhaphang.htm" var="pagedLink">
				<c:param name="p" value="~" />
			</c:url>
			<form class="input-group" style="margin: 20px 0" method="post">
				<div>
					<input id="search-input" type="search" name="searchInput"
						class="form-control" placeholder="Tìm kiếm" />
				</div>
				<button id="search-button" type="submit" class="btn btn-primary"
					name="btnsearch">
					<i class="fas fa-search"></i>
				</button>
			</form>
			<!-- ENDTHEEM -->
			<table style="width: 100%;"
				class="table table-striped shadow-box bg-white">
				<thead>
					<tr>

						<th scope="row">Mã Đơn</th>
						<th scope="row">Ngày Nhập</th>

						<th scope="row">Nhà Cung Cấp</th>
						<th scope="row">Tình Trạng</th>

						
						<th></th>
						<th></th>
					</tr>
				</thead>
				<tbody>
					<c:forEach var="nh" items="${list}">
						<tr>
							<th scope="row">${nh.id}</th>

							<td>${nh.ngayThucHien}</td>

							<td>${nh.ncc}</td>
							<td>${nh.tinhTrang}</td>

							
							<td><a
								href="/dichvu/admin-home/formNhapHang.htm?linkEdit&id=${nh.id}">
									<button type="button" class="btn btn-secondary">SỬA</button>
							</a></td>

							<td><a
								href="/dichvu/admin-home/admin-nhaphang.htm?linkDelete&id=${nh.id}"<%--
																		href="/CNPM/admin-home/index.htm?linkDelete&id=${nv.maNV}"
																		--%>>
									<button name="btnXOA" id="#exampleModal2" type="button"
										class="btn btn-warning">Xóa</button>
							</a></td>
							<td>
								<div class="">
									<a style="font-size: 10px;" class="btn btn-primary"
										href="admin-nhaphang/${nh.id}.htm?linkView"> XEM </a>
								</div>
							</td>
						</tr>
					</c:forEach>
				</tbody>
			</table>
			<tg:paging pagedLink="${pagedLink}"
				pagedListHolder="${pagedListHolder}"></tg:paging>
		</div>
	</div>


	<jsp:include page="/common/admin/footer.jsp" />


</body>

</html>