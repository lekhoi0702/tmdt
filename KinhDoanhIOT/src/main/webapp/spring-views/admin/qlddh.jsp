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
				LÝ ĐƠN ĐẶT HÀNG</div>
			<%-- 	<jsp:useBean id="pagedListHolder" scope="request"
				type="org.springframework.beans.support.PagedListHolder" /> --%>
			<c:url value="admin-ddh.htm" var="pagedLink">
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
						<th scope="col">Khách hàng</th>
						<th scope="col">Ngày Thực Hiện</th>
						<th scope="col">Tình Trạng</th>

					</tr>
				</thead>
				<tbody>
					<c:forEach var="ddh" items="${list}">
						<tr>
							<th scope="row">${ddh.id}</th>
							<td>${ddh.nvThucHien}</td>
							<td>${ddh.khThucHien}</td>
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
                                    <a style="font-size: 10px;" class="btn btn-primary" href="admin-ddh/${ddh.id}.htm?linkView">
                                        XEM
                                    </a>
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