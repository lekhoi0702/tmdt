<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@include file="/common/taglib.jsp"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<base href="${pageContext.servletContext.contextPath}/">
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
</head>
<style>
label {
	font-weight: bold;
}

body {
	background-color: #d7d7d7;
}

.form-message {
	color: red;
}
</style>
<body>



	<main id="main" class="main">


	<div class="row">
		<div class="col-lg-10">

			<div class="card">
				<div class="card-body">
					<h5 class="card-title">Thông tin chi tiết phiếu nhập</h5>


					<!-- End Multi Columns Form -->
					<form:form action="admin-home/ctpn.htm"
						method="post" modelAttribute="ct" class="row g-3">
						<div class="col-md-8">
							<label for="inputEmail5" class="form-label">Tên SP</label> 
							<form:select path="sanPham" 
								class="form-control" id="inputEmail5" name="id">
								<c:forEach items="${sanphams }" var="vt">
									<form:option value="${vt.ID}" label="${vt.ten}"/>
								</c:forEach>
							</form:select>
							
						</div>

								
						<div class="col-md-4">
							<label for="inputPassword5" class="form-label">Số lượng</label>
							<form:input type="number" class="form-control" id="inputEmail5"
								path="soLuong" />

						</div>

						<div class="col-md-5">
							<label for="inputState" class="form-label">Đơn giá
							</label> <br>
							<form:input path="donGia" />
							<br>
						</div>

						<div class="col-md-5">
							<label for="inputState" class="form-label">Mã PN</label> <br>
							<form:input path="phieuNhap"  readonly="true" /> <br>
						</div>
						<div class="col-md-12">
							<div class="text-center" style="margin-top: 20px">
								<button type="submit" name="addctnh2" class="btn btn-primary">Tạo</button>
							</div>
							
						</div>
					</form:form>



				</div>
			</div>

		</div>
	</div>
	<section class="section">
		<div class="row">
			<div class="col-lg-10">

				<div class="card">
					<div class="card-body">
						<h5 class="card-title">Chi tiết phiếu nhập</h5>

						<table class="table table-bordered">
							<thead>
								<tr class="table-secondary">
									<th scope="col">mã VT</th>
									<th scope="col">Số lượng</th>
									<th scope="col">Đơn giá</th>

								</tr>
							</thead>
							<tbody>




								<c:forEach items="${ctpns }" var="k">
								
									<tr>

										<td>${k.sanPham}</td>
										<td>${k.soLuong}</td>

										<td>${k.donGia}</td>

									</tr>
								</c:forEach>

							</tbody>
						</table>



					</div>
				</div>

			</div>
		</div>

	</section>

	</main>
<a style="font-size: 16px; padding: 10px;" class="btn btn-secondary"
					href="admin-home/admin-nhaphang.htm"> QUAY LẠI </a>

</body>

</html>