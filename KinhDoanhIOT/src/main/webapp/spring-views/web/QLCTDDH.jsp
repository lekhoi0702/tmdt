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
			<h4>Chi tiết đơn hàng</h4>
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
							<c:forEach var="ctddh" items="${chiTiet}">
								<tr>
									<td>${ctddh.ddh }</td>
									<td>${ctddh.sanPham }</td>
									<td>${ctddh.tenSP }</td>
									<td>${ctddh.soLuong }</td>
									<td id="tongtien">${ctddh.tongTien}đồng</td>
								</tr>
							</c:forEach>

						</tbody>
					</table>
				</div>
			</div>



		</form:form>

	</div>

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
<script>
const tongTiens = document.querySelectorAll("#tongtien");
tongTiens.forEach(tongTien => {
  const formattedTongTien = Number(tongTien.textContent.replace(/\D/g, '')).toLocaleString('vi-VN', {style: 'currency', currency: 'VND'});
  tongTien.textContent = formattedTongTien;
});
</script>
