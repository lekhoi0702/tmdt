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

	<main id="mains" class="mains">
	<!-- End Page Title -->

	<section class="section">
      <div class="row">
        <div class="col-lg-12">
          <div class="card">
            <div class="card-body">
              <h4 style="margin: 20px 0;text-align: center; text-transform: uppercase;"><b>CỘNG HOÀ XÃ HỘI CHỦ NGHĨA VIỆT NAM</b></h4>
              <h5 style="margin: 20px 0;text-align: center;"><b>Độc Lập - Tự Do - Hạnh Phúc</b></h5>
              <br>
              <h6 class="text-end">............,ngày.....tháng.....năm 202......</h6>
              <br>
              <br>
              <h3 style="margin: 20px ;text-align: center; text-transform: uppercase"><strong>Báo cáo <br> Hoạt động nhân viên </strong> </h3>
              <h5 style="margin: 20px ; text-align: center"><b>Chi nhánh 2</b></h5>
              <br>
              <br>
              <br>
                <form class="row g-3">
                	
                  <div class="col-md-4">
                    <p><b>Mã nhân viên : ${nvs.maNV}</b></p>
                  </div>
                	<div class="col-md-8">
                    <p><b>Họ và tên nhân viên :${nvs.ho} ${nvs.ten}</b></p>
                  </div> 
                  <div class="col-md-6">
                    <p><b>Ngày sinh : ${nvs.ngaySinh}</b></p>
                  </div>
                  <div class="col-md-12">
                    <p><b>Địa chỉ : ${nvs.diaChi}</b></p>
                  </div>
                  <div class="col-md-4">
                    <p><b>Lương : ${nvs.luong}VND</b></p>
                  </div>
                  <div class="col-md-6">
                    <p><b>Ghi chú: </b></p>
                  </div>
                 
                  <hr  width="100%"  size="5px" align="center"  />
                  <h3 style="margin: 20px ;text-align: center; text-transform: uppercase"><strong>Bảng kê chứng từ</strong> </h3>
                  <hr  width="100%"  size="7px" align="center" style="color:rgb(0, 26, 255)" />
                  <c:forEach items="${hdxs}" var="nv">
                  <h5 class="card-title">${nv.thangNam}</h5>
                  <!-- Bordered Table -->
                  <table class="table border-bottom-0 ">
                    <thead class="reports head">
                      <tr>
                        <th scope="col">Ngày</th>
                        <th scope="col">Số phiếu</th>
                        <th scope="col">Tên vật tư</th>
                        <th scope="col">Tên kho</th>
                        <th scope="col">Số lượng</th>
                        <th scope="col">Đơn giá</th>
                        <th scope="col">Thành tiền</th>
                      </tr>
                    </thead>
                    <tbody class="reports body">
                    <c:forEach items="${hdxs}" var="nv">
                      <tr>
                      
                        <th scope="row">${nv.ngay}</th>
                        <td>${nv.maPhieu}</td>
                        <td>${nv.tenVT}</td>
                        <td>${nv.tenKho}</td>
                        <td>${nv.soLuong}</td>
                        <td>${nv.donGia}VNĐ</td>
                        <td>${nv.triGia}VNĐ</td>
                        
                      </tr>
                      </c:forEach>
                      
                  
                    </tbody>
                  </table>
                 </c:forEach>

                </form><!-- End Multi Columns Form -->
              <br>
              <br>
              <p class="text-end" style="margin-right:100px"><b> Người báo cáo</b></p>
              <br>
              <br>
              <br>
              <br>

              </div>
            </div>

          </div>
        </div>
      </section>
	</main>

</body>

</html>