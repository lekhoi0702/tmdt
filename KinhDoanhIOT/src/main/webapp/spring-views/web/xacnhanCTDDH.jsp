<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>

<%@include file="/common/taglib.jsp"%>

<!DOCTYPE html>
<html lang="en">


<head>
<meta charset="UTF-8" />
<meta http-equiv="X-UA-Compatible" content="IE=edge" />
<meta name="viewport" content="width=device-width, initial-scale=1.0" />
<base href="${pageContext.servletContext.contextPath}/">
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
<link rel="stylesheet"
	href=<c:url value="/common/vendor/themify-icons/themify-icons.css"/>>
<title>Document</title>
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
	<div style="margin-top: 2%; border-style: groove;"
		class="container card">
		<div class="card-header">
			<h4>Xác nhận đơn hàng</h4>
		</div>
		<form:form class="card-body" id="form-1"
			action="xacnhanddh/${idddh}.htm" modelAttribute="pn" method="post">
			<div class="form-row">
				<div class="form-group col-md-6">
					<table class="table table-striped datatable shadow-box">

						<thead>
							<tr>
								<th>Mã ĐĐH</th>
								<th>Mã SP</th>

								<th>Tên SP</th>
								<th>Số Lượng</th>

								<th>Thành Tiền</th>

							</tr>
						</thead>
						<tbody>
							<c:forEach var="cthd" items="${chiTiet}">
								<tr>
									<td>${cthd.ddh }</td>
									<td>${cthd.sanPham }</td>
									<td>
										<%-- ${cthd.soLuong } --%>
									</td>
									<td>${cthd.soLuong }</td>
									<td id="tongtien">${cthd.tongTien}đồng</td>
								</tr>
							</c:forEach>

						</tbody>
					</table>
				</div>
			</div>
			<div class="card-footer">
				<button class="btn btn-primary" type="submit">Xác nhận đơn
					hàng</button>
				<a style="font-size: 16px; padding: 10px;" class="btn btn-secondary"
					href="ddh.htm"> QUAY LẠI </a>
			</div>


		</form:form>

	</div>
	<script>
const tongTiens = document.querySelectorAll("#tongtien");
tongTiens.forEach(tongTien => {
  const formattedTongTien = Number(tongTien.textContent.replace(/\D/g, '')).toLocaleString('vi-VN', {style: 'currency', currency: 'VND'});
  tongTien.textContent = formattedTongTien;
});
</script>

	<script src="<c:url value='/template/admin/validation.js'/>"></script>
	<script>
		document.addEventListener('DOMContentLoaded', function() {
			// Mong muốn của chúng ta
			Validator({
				form : '#form-1',
				formGroupSelector : '.form-group',
				errorSelector : '.form-message',
				rules : [
						Validator.isRequired('#fullname',
								'Vui lòng nhập tên đầy đủ của bạn'),
						Validator.isRequired('#diachi',
								'Vui lòng địa chỉ của bạn'),
						Validator.minLength('#cmnd', 9),
						Validator.minLength('#sdt', 10),
						Validator.isDate('#ngaysinh'),

				],
			/*   onSubmit: function (data) {
			      // Call API
			      console.log(data);
			    }  */

			});

		});
	</script>


</body>
</html>
