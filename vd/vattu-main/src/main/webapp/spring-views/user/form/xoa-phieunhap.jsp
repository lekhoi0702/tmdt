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
<jsp:include page="/template/cn1/user/head.jsp" />

</head>
<body>

	<!-- ======= Header ======= -->
	<jsp:include page="/template/cn1/user/header.jsp" />

	<!-- End Header -->

	<!-- ======= Sidebar ======= -->
	<jsp:include page="/template/cn1/user/sidebar.jsp" />


	<main id="main" class="main">
	<!-- End Sidebar-->
<section class="section">
		<div class="row">
			<div class="col-lg-10">

				<div class="card">
					<div class="card-body">
						


						${message}
						
						Bạn có đồng ý xoá PN ${id }
						<!-- Multi Columns Form -->
						<form action="quanlyphieunhap/cn1/user/xoa.htm?id=${id }" method="post"
							class="row g-3">
							
							<div class="col-md-5">
								<label for="inputEmail5" class="form-label">Xác Nhận( Nhập "YES" vào khung này)</label>
								<input type="text" class="form-control" name="xacNhan" />
							</div>
							<button type="submit" > Xoá </button>
						</form>
						<!-- End Multi Columns Form -->

					</div>
				</div>

			</div>
		</div>
	</section>

	</main>
	<!-- End #main -->
	<jsp:include page="/template/cn1/user/footer.jsp" />


</body>

</html>