<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@include file="/common/taglib.jsp"%>
<!DOCTYPE html>
<html lang="en">

<head>
<jsp:include page="/common/admin/head.jsp" />
</head>
<body>

	<jsp:include page="/common/admin/header.jsp" />
		<jsp:include page="/common/admin/menubar.jsp" />

	<!-- CONTEND -->
	<div class="row main">

            <div class="container">
            <div class="header-content d-flex justify-content-center">QUẢN LÝ LOẠI SẢN PHẨM</div>
               <h4>${message}</h4>
			<div>
				<a href="/dichvu/admin-home/formLoaiSP.htm"> <!-- 	<button type="button" class="btn btn-warning">Thêm</button> -->
					<button style="width: 184px; height: 33px; margin-bottom: 5px;"
						type="button" class="btn btn-primary">Thêm loại sản phẩm</button>
				</a>

			</div>
			<%-- <jsp:useBean id="pagedListHolder" scope="request"
				type="org.springframework.beans.support.PagedListHolder" /> --%>
			<c:url value="admin-qlthucdon.htm" var="pagedLink">
				<c:param name="p" value="~" />
			</c:url>
			<form class="input-group" style="margin: 20px 0" method="post">
				<div>
					<input id="search-input" type="search" name="searchInput"
						class="form-control" placeholder="Tìm kiếm" />
				</div>
				<button id="search-button" type="submit" class="btn btn-primary"
					name="btnsearch">
					<i class="fas fa-search"></i>
				</button>
			</form>
                <table class="table table-striped shadow-box bg-white">
                    <thead>
                        <tr>
                            <th scope="row">ID</th>
                            <th scope="row">Tên Loại</th>
             
                            <th></th>
                        </tr>
                    </thead>
                    <tbody>
                    <c:forEach  var="th" items="${list}">
                       
                     <tr>
                            <th scope="row">${th.id}</th>
                            <td>${th.tenLoai}</td>
        
                            <td><a href="/dichvu/admin-home/formLoaiSP.htm?linkEdit&id=${th.id}">
											<button type="button"
												class="btn btn-primary" data-toggle="modal" 
												data-whatever="@mdo">SỬA</button>
										</a></td>
                            <%-- <td>
                                <a
														href="/dichvu/admin-home/admin-qlthucdon.htm?linkDelete&id=${th.id}"
																		href="/CNPM/admin-home/index.htm?linkDelete&id=${nv.maNV}"
																		>
														<button name="btnXOA1" type="button"
															class="btn btn-warning">Xóa</button>
													</a>
                            </td> --%>

                        </tr>
                        </c:forEach>
                        
                    </tbody>
                </table>
               <tg:paging pagedLink="${pagedLink}"
				pagedListHolder="${pagedListHolder}"></tg:paging>              
            </div>
        </div>
	<jsp:include page="/common/admin/footer.jsp" />



</body>

</html>