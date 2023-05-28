<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@include file="/common/taglib.jsp"%>
<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>IOT SHOP</title>
	<base href="${pageContext.servletContext.contextPath}/">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.4/css/all.min.css">
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
    <link
        href="https://fonts.googleapis.com/css?family=Open+Sans:300,300i,400,400i,600,600i,700,700i|Nunito:300,300i,400,400i,600,600i,700,700i|Poppins:300,300i,400,400i,500,500i,600,600i,700,700i"
        rel="stylesheet">

    <link href="<c:url value='/template/web/styles.css'/>" rel="stylesheet" type="text/css"> 

</head>

<body>
    <jsp:include page="/common/admin/header.jsp" />
	<jsp:include page="/common/admin/menubar.jsp" />
	
	<div class="container-fluid main">
        <div class="container">
            <div class="content">
                <div class="header-content d-flex justify-content-center">
                    Chi tiết phiếu nhập
                </div>
                <div class="original-info d-flex justify-content-center">
                  
                    <div class=" mg-0-40">
                        Ngày:
                        <span id="date-now"></span>
                    </div>
                    <div class=" mg-0-40">
                        Thời gian:
                        <span id="current-time"></span>
                    </div>
                </div>
                <div class="" style="margin: 16px;">
                    <a style="font-size: 16px; padding: 10px;" class="btn btn-secondary" href="hoa-don.htm">
                        QUAY LẠI
                    </a>
                </div>
                
               
                
                <table class="table table-striped datatable shadow-box">

                    <thead>
                        <tr>
                            <th>Mã PN</th>
                            <th>Mã SP</th>
                            <th>Tên SP</th>
                            <th>Giá</th>
                            <th>Số Lượng</th>
                            <th>Thành Tiền</th>
                        </tr>
                    </thead>
                    <tbody>
                        <c:forEach var="cthd" items="${chiTiet}">
                        	<tr>
                           <td>${cthd.phieuNhap }</td>
                            <td>${cthd.sanPham }</td>
                            <td><%-- ${cthd.thucDon.ten } --%></td> 
                            <td>${cthd.donGia } đồng</td>
                            <td>${cthd.soLuong }</td>
                            <td>${cthd.soLuong * cthd.donGia} đồng</td> 
                        </tr>
                        </c:forEach>

                    </tbody>
                </table>
                <h3>Tổng Tiền:  ${tongTien} đồng</h3>
         
            </div>

        </div>
    </div>
	
	<jsp:include page="/common/web/footer.jsp" />
	<script
		src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
	<script
		src="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
	<script src="<c:url value='/template/web/scipts.js'/>"></script>

</body>
</html>