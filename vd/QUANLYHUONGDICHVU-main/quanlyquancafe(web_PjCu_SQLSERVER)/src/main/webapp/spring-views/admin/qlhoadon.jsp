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
				LÍ HÓA ĐƠN</div>
			<jsp:useBean id="pagedListHolder" scope="request"
				type="org.springframework.beans.support.PagedListHolder" />
			<c:url value="admin-hoadon.htm" var="pagedLink">
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
						<th scope="col">ID</th>

						<th scope="col">Nhân Viên Thực Hiện</th>
						<th scope="col">Bàn</th>
						<th scope="col">Ngày Thực Hiện</th>
					</tr>
				</thead>
				<tbody>
					<c:forEach var="hd" items="${pagedListHolder.pageList}">
						<tr>
							<th scope="row">${hd.id}</th>
							<td>${hd.hdnv.hoTen}</td>
							<td>${hd.ban.id}</td>
							<td>${hd.ngayThucHien}</td>
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