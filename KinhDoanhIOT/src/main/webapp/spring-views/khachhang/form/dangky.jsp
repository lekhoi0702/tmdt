<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@include file="/common/taglib.jsp"%>
<head>
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
<jsp:include page="/common/webKH/head.jsp" />
</head>

<body>

	<div style="margin-top: 2%; border-style: groove;"
		class="container card">
		<div class="card-header">
			<h4>Nhập Thông Tin Đăng Ký</h4>
		</div>
		<form:form class="card-body" id="form-1" action="khachhang-dangky.htm"
			modelAttribute="user" method="post">
			
				<label>Username</label>

					<form:input id="userName" type="text" class="form-control"
						placeholder="Username" path="userName" aria-label="userName"
						maxlength="50" aria-describedby="basic-addon1" />
					<span class="form-message"></span> 
					
					<label>Password</label>
					<form:input id="fullname" type="password" class="form-control"
						placeholder="Password" path="passwd" aria-label="Username"
						maxlength="50" aria-describedby="basic-addon1" />
					<span class="form-message"></span> 
					
					
					<label>Email</label>

					<form:input id="userName" type="email" class="form-control"
						placeholder="Username" path="email" aria-label="userName"
						maxlength="50" aria-describedby="basic-addon1" required="true"/>
					<span class="form-message"></span> 
					
			<div class="form-row">
				<div class="form-group col-md-6">
					<label>Họ Tên</label>

					<div class="input-group">
						<div class="input-group-prepend">
							<span class="input-group-text" id="basic-addon1"> <i
								class="ti-user" aria-hidden="true"></i>
							</span>
						</div>
						<form:input id="fullname" type="text" class="form-control"
							placeholder="Nguyễn Văn A" path="hoTen" aria-label="Username"
							maxlength="50" aria-describedby="basic-addon1" />
						<span class="form-message"></span>

					</div>
				

			

				</div>

			</div>
			<div class="form-group">
				<label>Địa chỉ</label>
				<form:input id="diachi" path="diaChi" type="text"
					class="form-control" placeholder="97 Man Thiện, tp Thủ Đức" />
				<span class="form-message"></span>
			</div>

			<div class="form-row">


				<div class="form-group col-md-6">
					<div class="form-group">
						<label>Ngày Sinh</label> <br> <input type="date"
							id="ngaysinh" name="ngaysinh" value="${ngaysinh}" /> <span
							class="form-message"></span>
					</div>
				</div>




			</div>
			<div class="form-row row">
				<div class="form-group col-md-6">
					<label>Số Điện Thoại</label> <br>

					<form:input id="sdt" path="sdt" type="text" minlength="10"
						maxlength="10" pattern="^[0-9]{10,15}$" required="true" />
					<span class="form-message"></span>

				</div>
			</div>

			<div class="form-group row">
				<label class="col-sm-2 col-form-label">Giới Tính</label>
				<div class="col-sm-10">
					<div class="form-check form-check-inline">
						<form:radiobutton path="gioiTinh" value="1" />
						<label class="form-check-label">Nam</label>
					</div>
					<div class="form-check form-check-inline">
						<form:radiobutton path="gioiTinh" value="0" />
						<label class="form-check-label">Nữ</label>
					</div>
				</div>
			</div>



			<div class="card-footer">
				<button class="btn btn-primary" type="submit"
					name="${btnupdate ? 'btnupdate' : 'Insert'}">${btnupdate ? 'Cập Nhật' : 'Thêm'}</button>

				<a style="font-size: 16px; padding: 10px;" class="btn btn-secondary"
					href="khachhang-login.htm?action=login"> QUAY LẠI </a>
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