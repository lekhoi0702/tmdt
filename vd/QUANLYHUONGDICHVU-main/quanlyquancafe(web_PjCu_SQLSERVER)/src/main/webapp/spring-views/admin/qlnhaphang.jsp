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
				LÍ NHẬP HÀNG</div>

			<h4>${message}</h4>
			<div>
				<a href="/CNPM/admin-home/formNhapHang.htm"> <!-- 	<button type="button" class="btn btn-warning">Thêm</button> -->
					<button style="width: 184px; height: 33px; margin-bottom: 5px;"
						type="button" class="btn btn-primary">Thêm Đơn Nhập Hàng</button>
				</a>

			</div>
			<jsp:useBean id="pagedListHolder" scope="request"
				type="org.springframework.beans.support.PagedListHolder" />
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
						<th scope="row">Tên NL</th>
						<th scope="row">Ngày Nhập</th>
						<th scope="row">Số Lượng</th>
						<th scope="row">Giá DV</th>
						<th scope="row">Loại Hàng</th>
						<th scope="row">Nhà Cung Cấp</th>
						<th scope="row">Dịch Vụ</th>

						<th scope="row">NV Tạo</th>
						<th></th>
						<th></th>
					</tr>
				</thead>
				<tbody>
					<c:forEach var="nh" items="${pagedListHolder.pageList}">
						<tr>
							<th scope="row">${nh.id}</th>

							<td>${nh.tenNL}</td>
							<td>${nh.ngayNhap}</td>
							<td>${nh.soLuong}</td>
							<td>${nh.giaMoiDV}</td>

							<td>${nh.loai}</td>
							<td>${nh.nhaCungCap}</td>
							<td>${nh.dv}</td>

							<td>${nh.cpnv.maNV}</td>
							<td><a
								href="/CNPM/admin-home/formNhapHang.htm?linkEdit&id=${nh.id}">
									<button type="button" class="btn btn-secondary">SỬA</button>
							</a></td>

							<td>
								<div style="width: 75px;" class="row">


									<!-- <a href="">XÓA</a> -->
									<!-- Button trigger modal -->
									<button  type="button"
										class="btn btn-warning" data-toggle="modal"
										data-target="#n-${nh.id}"><i class="ti-trash"></i></button>

									<!-- Modal -->
									<div class="modal fade" id="n-${nh.id}" tabindex="-1"
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
												<div class="modal-body">Bạn có chắc muốn xóa!!!</div>
												<div class="modal-footer">
													<button type="button" class="btn btn-secondary"
														data-dismiss="modal">Thoát</button>
													<a
														href="/CNPM/admin-home/admin-nhaphang.htm?linkDelete&id=${nh.id}"<%--
																		href="/CNPM/admin-home/index.htm?linkDelete&id=${nv.maNV}"
																		--%>>
														<button name="btnXOA" id="#exampleModal2" type="button"
															class="btn btn-warning">Xóa</button>
													</a>

												</div>
											</div>
										</div>
									</div>
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