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
			<h4>Nhập Mã Xác Nhận</h4>
		</div>
		<form class="card-body" id="form-1" action="khachhang-xacnhan.htm"
			method="post">

			<label>Mã Xác Nhận</label>

			<input id="userName" type="text" 
				placeholder="Mã đươc gửi qua email cho bạn" name="maXacNhan" 
				maxlength="50"  />
		
		
				<button class="btn btn-primary" type="submit"> Gửi </button>
		</form>

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
						Validator.isRequired('#userName',
								'Vui lòng nhập '),
						

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