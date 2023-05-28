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
	background-color:#d7d7d7;
}
.form-message{
	color:red;
}
</style>
<body>
	<div style="margin-top: 10%; width:500px; hight:1000px" class="container card">
		<div class="card-header">
			<h4>Nhập Thông Tin Bàn</h4>
		</div>
		<form:form id ="form-1" class ="card-body" action="admin-home/formBan.htm" modelAttribute="b"
			method="post">
			<div class="form-group">
				
					<label>Số Ghế</label> <input id="soghe" value="${soGhe1}" name="soGhe"
					min="1" max="20" required="true" maxlength="3" type="number" placeholder="2,4,10...." />
						<span class="form-message"></span>
				
			</div>

			<div class="form-group">
				<label>ID Bàn</label>
				<form:input readonly="true" path="id" />
			</div>
			<div class="form-group">

				<label>Loại Bàn</label> <select name="loaiBan">
					<c:forEach items="${loaibans}" var="loai">

						<option value="${loai.id}"
							<c:if test="${loai.id == idLoai}" >selected</c:if>>
							${loai.tenLoai}</option>
					</c:forEach>
				</select>

			</div>
			<div class="card-footer">
				<button class="btn btn-primary" type="submit"
					name="${btnupdate ? 'btnupdate' : 'Insert'}">${btnupdate ? 'Update' : 'Insert'}</button>
				<a style="font-size: 16px; padding: 10px;" class="btn btn-secondary"
					href="admin-home/admin-qlban.htm"> QUAY LẠI </a>

			</div>
		</form:form>
	</div>
	<script src="<c:url value='/template/web/scipts.js'/>"></script>
	<script src="<c:url value='/template/admin/validation.js'/>"></script>
	 <script>
  
        document.addEventListener('DOMContentLoaded', function () {
          // Mong muốn của chúng ta
          Validator({
            form: '#form-1',
            formGroupSelector: '.form-group',
            errorSelector: '.form-message',
            rules: [
              Validator.isRequired('#soghe', 'Vui lòng nhập Số ghế'),
        
           /*  Validator.isEmail('#email'),
              Validator.minLength('#password', 6),
              Validator.isRequired('#password_confirmation'),
              Validator.isConfirmed('#password_confirmation',  function () {
                return document.querySelector('#form-1 #password').value;
              }, 'Mật khẩu nhập lại không chính xác') */
            ],
           
          });
  
        }); 
  
      </script>
</body>
</html>