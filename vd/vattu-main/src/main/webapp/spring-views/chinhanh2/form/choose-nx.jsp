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
<jsp:include page="/template/cn2/chinhanh/head.jsp" />

</head>
<body>

	<!-- ======= Header ======= -->
	<jsp:include page="/template/cn2/chinhanh/header.jsp" />

	<!-- End Header -->

	<!-- ======= Sidebar ======= -->
	<jsp:include page="/template/cn2/chinhanh/sidebar.jsp" />

	<!-- End Sidebar-->


	<main id="main" class="main">

	
	<section class="section">
		<div class="row">
			<div class="col-lg-10">

				<div class="card">
					<div class="card-body">
						<h5 class="card-title">Tổng hợp nhập xuất</h5>
						${message}
						<form action="tonghopnhapxuat/cn2/chinhanh/nhap.htm"
							method="post" modelAttribute="nv" class="row g-3">
					
							<div class="col-md-6">
								<label for="set-date" style="margin-left: 13px; margin-top: 15px">Từ ngày :</label> 
								<div class="col-md-12">
									<input type="datetime-local" id="set-date" name="tungay" style="margin-left:10px" >
								</div>
							</div>
							<div class="col-md-6">
								<label for="set-date"
									style="margin-left: 13px; margin-top: 15px">Đến ngày :</label>
								<div class="col-md-12">
									<input type="datetime-local" id="set-date" name="denngay"
										style="margin-left: 10px">
								</div>
							</div>
							
							

							<div class="col-md-12">
								<div class="text-center" style="margin-top: 20px">
									<button type="submit" class="btn btn-primary">Next</button>
									<button type="reset" class="btn btn-secondary">Reset</button>
								</div>
							</div>
						</form>
						<!-- End Multi Columns Form -->

					</div>
				</div>

			</div>
		</div>
	</section>

	</main>
	<!-- End #main -->
	<jsp:include page="/template/cn2/chinhanh/footer.jsp" />


</body>

</html>