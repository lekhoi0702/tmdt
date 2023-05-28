<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@include file="/common/taglib.jsp"%>
<!DOCTYPE html>
<html>

<head>
<jsp:include page="/common/admin/head.jsp" />
<link rel="stylesheet"
	href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.8.0/font/bootstrap-icons.css">
<link rel="stylesheet" type="text/css"
	href=<c:url value="/template/web/simple-datatables/style.css"/>>
</head>
<body>

	<jsp:include page="/common/admin/header.jsp" />
	<jsp:include page="/common/admin/menubar.jsp" />

	<div class="container-fluid main">
		<div class="content">
			<div class="header-content d-flex justify-content-center">
				THỐNG KÊ</div>
			<section class="section dashboard">

				<!-- Left side columns -->
				<div class="row">

					<div class="col-12">
						<form class="d-flex justify-content-center align-items-center"
							method="post">
							<span class="form-check-inline">Tìm Kiếm:</span>
							<div class="form-check form-check-inline">
								<input class="form-check-input" type="radio" name="totime"
									id="today" onclick="disabledTime(this.id)" value="day" checked
									<c:if test="${timeradio == 'day'}">checked</c:if>> <label
									class="form-check-label" for="today"> Theo Ngày </label>
							</div>
							<div class="form-check form-check-inline">
								<input class="form-check-input" type="radio" name="totime"
									id="tomonth" onclick="disabledTime(this.id)" value="month"
									<c:if test="${timeradio == 'month'}">checked</c:if>> <label
									class="form-check-label" for="tomonth"> Theo Tháng </label>
							</div>
							<div class="form-check form-check-inline    ">
								<input class="form-check-input" type="radio" name="totime"
									id="toyear" onclick="disabledTime(this.id)" value="year"
									<c:if test="${timeradio == 'year'}">checked</c:if>> <label
									class="form-check-label" for="toyear"> Theo Năm </label>
							</div>
							<span> <select name="day" class="mg-0-40" id="sel-today"
								<c:if test="${null == day}">disabled</c:if>>
									<c:forEach begin="01" end="31" step="1" var="b">
										<option value="${b}" <c:if test="${b == day }">selected</c:if>>${b}</option>
									</c:forEach>
							</select>
							</span> <span> <select name="month" onchange="checkTotalDay()"
								class="mg-0-40" id="sel-tomonth"
								<c:if test="${null == month}">disabled="disabled"</c:if>>
									<c:forEach begin="01" end="12" step="1" var="a">
										<option value="${a}"
											<c:if test="${a == month}">selected</c:if>>Tháng
											${a}</option>
									</c:forEach>
							</select>
							</span> <span> <select name="year" onchange="checkTotalDay()"
								class="mg-0-40" id="sel-toyear">
									<c:forEach begin="2019" end="2040" step="1" var="c">
										<option value="${c}" <c:if test="${c == year}">selected</c:if>>${c}</option>
									</c:forEach>
							</select>
							</span>
							<button type="submit" class="btn btn-primary btn-sm"
								name="btn-search">
								<i class="fas fa-search"></i>
							</button>
						</form>
					</div>

					<div class="col-md-6 col-lg-3">
						<div class="card info-card sales-card">
							<div class="card-body">
								<div class="card-title text-center">Số Đơn</div>

								<div class="d-flex align-items-center justify-content-center">
									<div
										class="card-icon rounded-circle d-flex align-items-center justify-content-center"
										style="color: #e2b816; background-color: #f9f6cd;">
										<i class="bi bi-receipt"></i>
									</div>
									<h6>${soHoaDon} đơn</h6>
								</div>
							</div>

						</div>
					</div>
					<!-- Sales Card -->
					<div class="col-md-6 col-lg-3">
						<div class="card info-card sales-card">
							<div class="card-body">
								<div class="card-title text-center">Lợi Nhuận</div>

								<div class="d-flex align-items-center justify-content-center">
									<div
										class="card-icon rounded-circle d-flex align-items-center justify-content-center">
										<i class="bi bi-currency-dollar"></i>
									</div>
									<h6>${loiNhuan} đồng</h6>
								</div>
							</div>

						</div>
					</div>
					<!-- End Sales Card -->

					<!-- Revenue Card -->
					<div class="col-md-6 col-lg-3">
						<div class="card info-card revenue-card">
							<div class="card-body">
								<div class="card-title text-center">Doanh thu</div>

								<div class="d-flex align-items-center justify-content-center">
									<div
										class="card-icon rounded-circle d-flex align-items-center justify-content-center">
										<i class="bi bi-currency-dollar"></i>
									</div>
									<h6>${doanhThu} đồng</h6>
								</div>
							</div>

						</div>
					</div>
					<!-- End Revenue Card -->

					<!-- Customers Card -->
					<div class="col-md-6 col-lg-3">

						<div class="card info-card customers-card">

							<div class="card-body">
								<div class="card-title text-center">Chi Phí</div>

								<div class="d-flex align-items-center justify-content-center">
									<div
										class="card-icon rounded-circle d-flex align-items-center justify-content-center">
										<i class="bi bi-currency-dollar"></i>
									</div>
									<h6>${chiPhi} đồng</h6>
								</div>

							</div>
						</div>

					</div>
					<!-- End Customers Card -->

					<!-- Reports -->
					<div class="col-12" <c:if test="${timeradio =='day'}">hidden</c:if>>
						<div class="card">
							<div class="card-body">
								<h5 class="card-title">Thống kê</h5>

								<!-- Line Chart -->
								<div id="reportsChart"></div>
							</div>

							<script>
                                        document.addEventListener("DOMContentLoaded", () => {
                                            new ApexCharts(document.querySelector("#reportsChart"), {
                                                series: [
                                               	{
                                                       name: 'Số Hóa Đơn',
                                                       data: ${soDonBD},   
                                                }, {
                                                    name: 'Lợi Nhuận',
                                                    data: ${loiNhuanBD},
                                                }, {
                                                    name: 'Doanh Thu',
                                                    data: ${doanhThuBD},
                                                }, {
                                                    name: 'Chi Phí',
                                                    data: ${chiPhiBD},
                                                }],
                                                chart: {
                                                    height: 350,
                                                    type: 'area',
                                                    toolbar: {
                                                        show: false
                                                    },
                                                },
                                                markers: {
                                                    size: 4
                                                },
                                                colors: ['#e2b816','#4154f1', '#2eca6a', '#ff771d'],
                                                fill: {
                                                    type: "gradient",
                                                    gradient: {
                                                        shadeIntensity: 1,
                                                        opacityFrom: 0.3,
                                                        opacityTo: 0.4,
                                                        stops: [0, 90, 100]
                                                    }
                                                },
                                                dataLabels: {
                                                    enabled: false
                                                },
                                                stroke: {
                                                    curve: 'smooth',
                                                    width: 2
                                                },
                                                xaxis: {
                                                    type: 'category',
													categories: ${cotX}
 												}
                                                /* tooltip: {
                                                    x: {
                                                        format: 'dd/MM/yy'
                                                    },
                                                } */
                                            }).render();
                                        });
                                    </script>
							<!-- End Line Chart -->

						</div>
				</div>
				<!-- End Reports -->


					<!-- Recent Sales -->
					<div class="col-12">
						<div class="card recent-sales overflow-auto">
							<div class="card-body">
								<h5 class="card-title">
									Doanh Thu <span>| Hóa đơn</span>
								</h5>
	
								<table class="table table-borderless datatable">
									<thead>
										<tr>
											<th scope="col">ID</th>
											<th scope="col">Nhân Viên Thực Hiện</th>
											<th scope="col">Bàn</th>
											<th scope="col">Thời Gian</th>
											<th scope="col">Tổng</th>
										</tr>
									</thead>
									<tbody>
										<c:forEach var="hd" items="${hoaDon}">
											<tr>
												<th scope="row">${hd.id}</th>
												<td>${hd.hdnv.hoTen}</td>
												<td>${hd.ban.id }</td>
												<td>${hd.ngayThucHien }</td>
												<td>${tongHD.get(hoaDon.indexOf(hd))} đồng</td>
											</tr>
										</c:forEach>
									</tbody>
								</table>
	
							</div>
	
						</div>
					</div>
					<!-- End Recent Sales -->
	
					<!-- Top Selling -->
					<div class="col-12">
						<div class="card top-selling overflow-auto">
							<div class="card-body pb-0">
								<h5 class="card-title">Chi Phí</h5>
								<table class="table table-borderless datatable">
									<thead>
										<tr>
											<th scope="row">Mã Đơn</th>
											<th scope="col">Tên NL</th>
											<th scope="col">Ngày Nhập</th>
											<th scope="col">Số Lượng</th>
											<th scope="col">Giá DV</th>
											<th scope="col">Loại Hàng</th>
											<th scope="col">Nhà Cung Cấp</th>
											<th scope="col">Dịch Vụ</th>
											<th scope="col">Ghi Chú</th>
											<th scope="col">NV Tạo</th>
										</tr>
									</thead>
									<tbody>
										<c:forEach var="bcp" items="${bangChiPhi}">
											<tr>
												<th scope="row">${bcp.id}</th>
												<td>${bcp.tenNL}</td>
												<td>${bcp.ngayNhap}</td>
												<td>${bcp.soLuong}</td>
												<td>${bcp.giaMoiDV}</td>
												<td>${bcp.loai}</td>
												<td>${bcp.nhaCungCap}</td>
												<td>${bcp.dv}</td>
												<td>${bcp.ghiChu}</td>
												<td>${bcp.cpnv.maNV}</td>
											</tr>
										</c:forEach>
									</tbody>
								</table>
							</div>
	
						</div>
					</div>
					<!-- End Top Selling -->
				</div>

		</section>
	</div>

	</div>

	<jsp:include page="/common/admin/footer.jsp" />

	<script
		src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.1/dist/js/bootstrap.bundle.min.js"></script>
	<script src="https://cdn.jsdelivr.net/npm/apexcharts"></script>
	<script src="<c:url value='/template/web/simple-datatables/simple-datatables.js'/>"></script>
	<script src="<c:url value='/template/web/simple-datatables/tinymce.min.js'/>"></script>
	<script>
        (function () {
            /**
   * Easy selector helper function
   */
            const select = (el, all = false) => {
                el = el.trim()
                if (all) {
                    return [...document.querySelectorAll(el)]
                } else {
                    return document.querySelector(el)
                }
            }

            /**
             * Easy event listener function
             */
            const on = (type, el, listener, all = false) => {
                if (all) {
                    select(el, all).forEach(e => e.addEventListener(type, listener))
                } else {
                    select(el, all).addEventListener(type, listener)
                }
            }

            /**
             * Easy on scroll event listener 
             */
            const onscroll = (el, listener) => {
                el.addEventListener('scroll', listener)
            }
            const datatables = select('.datatable', true)
            datatables.forEach(datatable => {
                new simpleDatatables.DataTable(datatable);
            })
        })();
    </script>
	<script src="<c:url value='/template/web/scipts.js'/>"></script>
</body>
</html>
