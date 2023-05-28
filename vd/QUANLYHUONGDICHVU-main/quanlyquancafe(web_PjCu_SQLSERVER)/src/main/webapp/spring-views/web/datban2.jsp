<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
	<%@include file="/common/taglib.jsp" %>
		<!DOCTYPE html>
		<html lang="en">

		<head>
			<meta charset="UTF-8">
			<meta http-equiv="X-UA-Compatible" content="IE=edge">
			<meta name="viewport" content="width=device-width, initial-scale=1.0">
			<title>Highland Coffee</title>
			<base href="${pageContext.servletContext.contextPath}/">

			<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.4/css/all.min.css">
			<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
			<link
				href="https://fonts.googleapis.com/css?family=Open+Sans:300,300i,400,400i,600,600i,700,700i|Nunito:300,300i,400,400i,600,600i,700,700i|Poppins:300,300i,400,400i,500,500i,600,600i,700,700i"
				rel="stylesheet">

			<link href="<c:url value='/template/web/styles.css'/>" rel="stylesheet" type="text/css">

		</head>

		<body>
			<jsp:include page="/common/web/header.jsp" />
			<jsp:include page="/common/web/menubar.jsp" />

			<div class="container-fluid main">
				<div class="container">
					<div class="content">
						${message }
						<div class="header-content d-flex justify-content-center">ĐẶT
							BÀN</div>
						<div class="original-info d-flex justify-content-center">
							<div class=" mg-0-40">Họ tên nhân viên: ${NHANVIEN.hoTen}</div>
							<div class=" mg-0-40">
								Ngày: <span id="date-now"></span>
							</div>
							<div class=" mg-0-40">
								Thời gian: <span id="current-time"></span>
							</div>
						</div>
						<div class="d-flex justify-content-around">
							<div class="">
								<a style="font-size: 16px; padding: 10px;" class="btn btn-secondary"
									href="trang-chu.htm"> QUAY LẠI </a>
							</div>
							<div class="">
								<button style="padding: 10px; margin-bottom: 5px;" type="button" class="btn btn-primary"
									data-toggle="modal" data-target="#exampleModal">
									<span>ĐẶT BÀN</span>
								</button>

								<div class="modal fade" id="exampleModal" tabindex="-1" role="dialog"
									aria-labelledby="exampleModalLabel" aria-hidden="true">
									<div class="modal-dialog" role="document">
										<form action="dat-ban/${id}.htm?btndatban" method="post">
											<div class="modal-content">
												<div class="modal-header">
													<h5 class="modal-title" id="exampleModalLabel">ĐẶT BÀN</h5>
													<button type="button" class="close" data-dismiss="modal"
														aria-label="Close">
														<span aria-hidden="true">&times;</span>
													</button>
												</div>
												<!-- 	-------------------------------------form dat ----------------------------------------------->
												<form:form modelAttribute="datban" class="modal-body">
													<div style="margin-top: 40px; margin-left: 20px;">
														<div class="mg-10">
															<label for="name-customer">Tên khách hàng:</label> <input
																type="text" id="name-customer" name="hoTen">
														</div>
														<form:errors path="hoTen" />
														<div class="mg-10">
															<label for="number" style="margin-right: 18px;">Số
																điện thoại:</label> <input type="text" id="number"
																name="sdt">
														</div>
														<form:errors path="sdt" />

														<div class="mg-10">
															<label for="set-date" style="margin-right: 33px;">TG
																dự kiến:</label> <input type="datetime-local"
																id="set-date" name="tg">
														</div>
														<form:errors path="ngayDat" />
														<div class="mg-10">
															<label for="deposit" style="margin-right: 51px;">Tiền
																cọc:</label> <input type="number" id="deposit"
																name="tienCoc">
														</div>
														<form:errors path="tienCoc" />

													</div>
												</form:form>
												<!-- 	------------------------------------- end form dat ----------------------------------------------->

												<div class="modal-footer">
													<button type="button" class="btn btn-secondary"
														data-dismiss="modal">Thoát</button>
													<button type="submit" class="btn btn-primary" name="btndatban">ĐẶT
														BÀN</button>
												</div>
											</div>

										</form>
									</div>
								</div>
							</div>
						</div>


						<jsp:useBean id="pagedListHolder" scope="request"
							type="org.springframework.beans.support.PagedListHolder" />
						<c:url value="dat-ban/${id}.htm?linkView" var="pagedLink">
							<c:param name="p" value="~" />
						</c:url>
						<form class="input-group" style="margin: 20px 0" method="post">
							<div>
								<input id="search-input" type="search" name="searchInput" class="form-control"
									placeholder="Tìm kiếm" />
							</div>
							<button id="search-button" type="submit" class="btn btn-primary" name="btnsearch">
								<i class="fas fa-search"></i>
							</button>
						</form>
						<table class="table table-striped datatable bg-white shadow-box">

							<thead>
								<tr>
									<th>Tên khách hàng</th>
									<th>SĐT</th>
									<th>Tiền cọc</th>
									<th>Ngày đặt</th>
									<th>Thời gian dự kiến</th>
									<th>Bàn</th>
									<th>Trạng Thái</th>
								</tr>
							</thead>
							<tbody>

								<c:forEach var="d" items="${pagedListHolder.pageList}">
									<tr>
										<td>${d.hoTen}</td>
										<td>${d.sdt}</td>
										<td>${d.tienCoc} đồng</td>
										<td>${d.ngayDat}</td>
										<td>${d.tgDuKien}</td>
										<td>${d.ban.id}</td>
										<td>${d.trangThai}</td>
									</tr>
								</c:forEach>

							</tbody>

						</table>
						<tg:paging pagedLink="${pagedLink}" pagedListHolder="${pagedListHolder}"></tg:paging>
					</div>

				</div>
			</div>
			<jsp:include page="/common/web/footer.jsp" />
			<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
			<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
			<script src="<c:url value='/template/web/scipts.js'/>"></script>
		</body>

		</html>