<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@include file="/common/taglib.jsp"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<!DOCTYPE html>
<html>

<head>
<jsp:include page="/common/admin/head.jsp" />
</head>

<style>
.table thead th {
	vertical-align: baseline;
	border-bottom: 2px solid #dee2e6;
}

.table td, .table th {
	padding: 0.6rem;
	vertical-align: top;
	border-top: 1px solid #dee2e6;
}

.col-6 {
	-ms-flex: 0 0 50%;
	flex: 0 0 50%;
	max-width: 35%;
}
</style>

<body>

	<jsp:include page="/common/admin/header.jsp" />
	<jsp:include page="/common/admin/menubar.jsp" />

	<!-- CONTEND -->
	<div class="container-fluid main">
		<div style="padding-left: 20px;">
			<div class="header-content d-flex justify-content-center">QUẢN
				LÝ TÀI KHOẢN KHÁCH HÀNG</div>
			<h4>${message}</h4>

			<%-- <jsp:useBean id="pagedListHolder" scope="request"
				type="org.springframework.beans.support.PagedListHolder" /> --%>
			<c:url value="admin-taikhoanKH.htm" var="pagedLink">
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
			<table class="table table-striped shadow-box bg-white">
				<thead>
					<tr>
						<th scope="row">Tên Tài Khoản</th>
						<th scope="row">Email</th>
						<th scope="row">Trạng thái</th>
						<th scope="row">Mã KH</th>
						

						<th scope="row"></th>
					</tr>
				</thead>
				<tbody>
					<c:forEach var="tk" items="${list}">
						<tr>
							<td>${tk.userName}</td>
							<td>${tk.email}</td>
							<c:if test="${tk.status == 1}">
								<td>Đã kích hoạt</td>
							</c:if>
							<c:if test="${tk.status == 0}">
								<td>Chưa kích hoạt</td>
							</c:if>
							<td>${tk.ID}</td>







							<!-- end xóa -->
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