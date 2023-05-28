<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@include file="/common/taglib.jsp"%>
<!DOCTYPE html>
<html>


<head>

<meta charset="utf-8">
<meta content="width=device-width, initial-scale=1.0" name="viewport">

<title>QLVT</title>
<!-- ======= Head ======= -->
<jsp:include page="/template/cn2/user/head.jsp" />

</head>
<body>

	<!-- ======= Header ======= -->
	<jsp:include page="/template/cn2/user/header.jsp" />

	<!-- End Header -->

	<!-- ======= Sidebar ======= -->
	<jsp:include page="/template/cn2/user/sidebar.jsp" />

	<!-- End Sidebar-->


	<main id="main" class="main">

	<div class="pagetitle">
		<h1>Vật tư</h1>
		<nav>
			<ol class="breadcrumb">
				<li class="breadcrumb-item"><a
					href="quanlyvattu/cn2/user.htm">Vật tư</a></li>
				<li class="breadcrumb-item active">Thêm Vật tư</li>
			</ol>
		</nav>
	</div>
	<section class="section">
		<div class="row">
			<div class="col-lg-10">

				<div class="card">
					<div class="card-body">
						<h5 class="card-title">Thông tin Vật tư</h5>


						<!-- End Multi Columns Form -->
						<form:form action="quanlychitietphieuxuat/cn2/user/add.htm"
							method="post" modelAttribute="ct" class="row g-3">

							<label for="inputEmail5" class="form-label">tên VT</label>
							<select class="form-control" id="inputEmail5" name="maVT">
								<c:forEach items="${vattus }" var="vt">

									<option value="${vt.maVT}" label="${vt.tenVT}"></option>

								</c:forEach>
							</select>
							<div class="col-md-5">
								<div class="col-md-6">
									<label for="inputPassword5" class="form-label">Số lượng</label>
									<form:input type="text" class="form-control" id="inputEmail5"
										path="soLuong" />
								</div>

							</div>

							<div class="col-md-4">
								<label for="inputState" class="form-label">Đơn giá</label> <br>
								<form:input path="donGia" />
								<br>
							</div>




			


							<div class="text-center">
								<button type="submit" class="btn btn-primary">Tạo</button>
								<button type="reset" class="btn btn-secondary">Reset</button>
							</div>
						</form:form>
					</div>
				</div>

			</div>
		</div>
	</section>

	</main>
	<!-- End #main -->

	<!-- End #main -->
	<jsp:include page="/template/cn2/user/footer.jsp" />


</body>

</html>