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
				LÍ BÀN</div>
			<h4>${message}</h4>
			<div>
				<a href="/dichvu/admin-home/formBan.htm"> <!-- 	<button type="button" class="btn btn-warning">Thêm</button> -->
					<button style="width: 184px; height: 33px; margin-bottom: 5px;"
						type="button" class="btn btn-primary">Thêm Bàn</button>
				</a>

			</div>
			<%-- <jsp:useBean id="pagedListHolder" scope="request"
				type="org.springframework.beans.support.PagedListHolder" /> --%>
			<c:url value="admin-qlban.htm" var="pagedLink">
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
			<table class="table table-striped shadow-box bg-white">
				<thead>
					<tr>
						<th scope="row">ID</th>
						<th scope="row">Số Ghế</th>
						<th scope="row">Loại Bàn</th>
						<th scope="row">Tình Trạng</th>
						<th></th>
					</tr>
				</thead>
				<tbody>
					<c:forEach var="b" items="${list}">
						<tr>
							<th scope="row">${b.id}</th>
							<td>${b.soGhe}</td>
							<td>${b.tenLoai}</td>
							<td>${b.tinhTrang}</td>
							<td><a href="/dichvu/admin-home/formBan.htm?linkEdit&id=${b.id}">
											<button  type="button"
												class="btn btn-primary" data-toggle="modal" 
												data-whatever="@mdo">SỬA</button>
										</a></td>
							<td>
								<a
														href="/dichvu/admin-home/admin-qlban.htm?linkDelete&id=${b.id}"<%--
																		href="/CNPM/admin-home/index.htm?linkDelete&id=${nv.maNV}"
																		--%>>
														<button name="btnXOA" type="button"
															class="btn btn-warning">Xóa</button>
													</a>
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