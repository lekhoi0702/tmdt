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
				LÍ TÀI KHOẢN</div>
			<h4>${message}</h4>
			<div>
				<a href="/CNPM/admin-home/formTaiKhoan.htm"> <!-- 	<button type="button" class="btn btn-warning">Thêm</button> -->
					<button style="width: 144px; height: 33px; margin-bottom: 5px;"
						type="button" class="btn btn-primary">Thêm Tài Khoản</button>
				</a>

			</div>
			<jsp:useBean id="pagedListHolder" scope="request"
				type="org.springframework.beans.support.PagedListHolder" />
			<c:url value="admin-taikhoan.htm" var="pagedLink">
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
						<th scope="row">Chức Vụ</th>
						<th scope="row">Email</th>

						<th scope="row"></th>
					</tr>
				</thead>
				<tbody>
					<c:forEach var="tk" items="${pagedListHolder.pageList}">
						<tr>
							<td>${tk.userName}</td>
							<td>${tk.chucVu.tenChucVu}</td>
							<td>${tk.email}</td>


							<%-- <td><a
								href="/CNPM/admin-home/formTaiKhoan.htm?linkEdit&userName=${tk.userName}">
									<button type="button" class="btn btn-primary">SỬA</button>
							</a></td> --%>
							
							<!--  -->
							
								<td>
								<!--  --> <c:choose>
									<c:when test="${ tk.chucVu.id == 1}">
										<button name="btnxoa1" type="button" class="btn btn-primary"
											data-toggle="modal" data-target="#nadmin">
											Đặt lại mật khẩu
										</button>

										<!-- Modal -->
										<div class="modal fade" id="nadmin" tabindex="-1"
											role="dialog" aria-labelledby="exampleModalLabel"
											aria-hidden="true">
											<div class="modal-dialog" role="document">
												<div class="modal-content">
													<div class="modal-header">
														<h5 class="modal-title" id="exampleModalLabel">!!!</h5>
														<button type="button" class="close" data-dismiss="modal"
															aria-label="Close">
															<span aria-hidden="true">&times;</span>
														</button>
													</div>
													<div class="modal-body">Không thể sửa tài khoản của
														admin</div>
													<div class="modal-footer">
														<button type="button" class="btn btn-secondary"
															data-dismiss="modal">OK</button>
														<!-- nut xoa -->



													</div>
												</div>
											</div>
										</div>

									</c:when>
									<c:when test="${ tk.chucVu.id != 1}">

										<button name="btnxoa" type="button" class="btn btn-primary"
										data-toggle="modal" data-target="#n1-${tk.userName}">
										Đặt lại mật khẩu
									</button>

								</div> <!-- Modal -->
								<div class="modal fade" id="n1-${tk.userName}" tabindex="-1"
									role="dialog" aria-labelledby="exampleModalLabel"
									aria-hidden="true">
									<div class="modal-dialog" role="document">
										<div class="modal-content">
											<div class="modal-header">
												<h5 class="modal-title" id="exampleModalLabel">!!!</h5>
												<button type="button" class="close" data-dismiss="modal"
													aria-label="Close">
													<span aria-hidden="true">&times;</span>
												</button>
											</div>
											<div class="modal-body">Bạn có chắc muốn đặt lại mật khẩu</div>
											<div class="modal-footer">
												<button type="button" class="btn btn-secondary"
													data-dismiss="modal">Thoát</button>
												<!-- nut xoa -->
												<a
													href="/CNPM/admin-home/admin-taikhoan.htm?linkReset&userName=${tk.userName}"<%--
																		href="/CNPM/admin-home/index.htm?linkDelete&id=${nv.maNV}"
																		--%>>
													<button name="btnXOA" id="#exampleModal2" type="button"
														class="btn btn-warning">Lưu</button>
												</a>


											</div>
										</div>
									</div>
								</div>
									</c:when>
								</c:choose>
							</td>
							<!--  -->

							<td>
								<!--  --> <c:choose>
									<c:when test="${ tk.chucVu.id == 1}">
										<button name="btnxoa1" type="button" class="btn btn-primary"
											data-toggle="modal" data-target="#nadmin">
											SỬA
										</button>

										<!-- Modal -->
										<div class="modal fade" id="nadmin" tabindex="-1"
											role="dialog" aria-labelledby="exampleModalLabel"
											aria-hidden="true">
											<div class="modal-dialog" role="document">
												<div class="modal-content">
													<div class="modal-header">
														<h5 class="modal-title" id="exampleModalLabel">!!!</h5>
														<button type="button" class="close" data-dismiss="modal"
															aria-label="Close">
															<span aria-hidden="true">&times;</span>
														</button>
													</div>
													<div class="modal-body">Không thể sửa tài khoản của
														admin</div>
													<div class="modal-footer">
														<button type="button" class="btn btn-secondary"
															data-dismiss="modal">OK</button>
														<!-- nut xoa -->



													</div>
												</div>
											</div>
										</div>

									</c:when>
									<c:when test="${ tk.chucVu.id != 1}">

										<a
											href="/CNPM/admin-home/formTaiKhoan.htm?linkEdit&userName=${tk.userName}">
											<button type="button" class="btn btn-primary">SỬA</button>
										</a>
									</c:when>
								</c:choose>
							</td>
							<!--  -->


							<!-- xóa -->
							<td>


								<div class="row">


									<button name="btnxoa" type="button" class="btn btn-warning"
										data-toggle="modal" data-target="#n-${tk.userName}">
										<i class="ti-trash"></i>
									</button>

								</div> <!-- Modal -->
								<div class="modal fade" id="n-${tk.userName}" tabindex="-1"
									role="dialog" aria-labelledby="exampleModalLabel"
									aria-hidden="true">
									<div class="modal-dialog" role="document">
										<div class="modal-content">
											<div class="modal-header">
												<h5 class="modal-title" id="exampleModalLabel">!!!</h5>
												<button type="button" class="close" data-dismiss="modal"
													aria-label="Close">
													<span aria-hidden="true">&times;</span>
												</button>
											</div>
											<div class="modal-body">Bạn có chắc muốn xóa</div>
											<div class="modal-footer">
												<button type="button" class="btn btn-secondary"
													data-dismiss="modal">Thoát</button>
												<!-- nut xoa -->
												<a
													href="/CNPM/admin-home/admin-taikhoan.htm?linkDelete&userName=${tk.userName}"<%--
																		href="/CNPM/admin-home/index.htm?linkDelete&id=${nv.maNV}"
																		--%>>
													<button name="btnXOA" id="#exampleModal2" type="button"
														class="btn btn-warning">Xóa</button>
												</a>


											</div>
										</div>
									</div>
								</div>

							</td>

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