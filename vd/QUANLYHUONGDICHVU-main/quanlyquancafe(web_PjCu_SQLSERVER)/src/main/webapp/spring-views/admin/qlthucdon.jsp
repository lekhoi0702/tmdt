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
            <div class="header-content d-flex justify-content-center">QUẢN LÍ THỰC ĐƠN</div>
               <h4>${message}</h4>
			<div>
				<a href="/CNPM/admin-home/formThucDon.htm"> <!-- 	<button type="button" class="btn btn-warning">Thêm</button> -->
					<button style="width: 184px; height: 33px; margin-bottom: 5px;"
						type="button" class="btn btn-primary">Thêm Thực Đơn</button>
				</a>

			</div>
			<jsp:useBean id="pagedListHolder" scope="request"
				type="org.springframework.beans.support.PagedListHolder" />
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
                            <th scope="row">Loại Thức Uống</th>
                            <th scope="row">Tên Thức Uống</th>
                            <th scope="row">Giá Thành</th>
                            <th></th>
                        </tr>
                    </thead>
                    <tbody>
                    <c:forEach  var="th" items="${pagedListHolder.pageList}">
                       
                     <tr>
                            <th scope="row">${th.id}</th>
                            <td>${th.loaiThucUong.tenLoai}</td>
                            <td>${th.ten}</td>
                            <td>${th.gia}</td>
                            <td><a href="/CNPM/admin-home/formThucDon.htm?linkEdit&id=${th.id}">
											<button type="button"
												class="btn btn-primary" data-toggle="modal" 
												data-whatever="@mdo">SỬA</button>
										</a></td>
                            <td>
                                <div  class="row">
                                  
                                        <!-- <a href="">XÓA</a> -->
                                        <!-- Button trigger modal -->
                                        <button  type="button" class="btn btn-warning"
                                            data-toggle="modal" data-target="#-n${th.id}">
                                          <i class="ti-trash"></i>
                                        </button>

                                        <!-- Modal -->
                                        <div class="modal fade" id="-n${th.id}" tabindex="-1" role="dialog"
                                            aria-labelledby="exampleModalLabel" aria-hidden="true">
                                            <div class="modal-dialog" role="document">
                                                <div class="modal-content">
                                                    <div class="modal-header">
                                                        <h5 class="modal-title" id="exampleModalLabel">!!!</h5>
                                                        <button type="button" class="close" data-dismiss="modal"
                                                            aria-label="Close">
                                                            <span aria-hidden="true">&times;</span>
                                                        </button>
                                                    </div>
                                                    <div class="modal-body">
                                                        Bạn có chắc muốn xóa
                                                    </div>
                                                    <div class="modal-footer">
                                                        <button type="button" class="btn btn-secondary"
                                                            data-dismiss="modal">Thoát</button>
                                                        	<a
														href="/CNPM/admin-home/admin-qlthucdon.htm?linkDelete&id=${th.id}"<%--
																		href="/CNPM/admin-home/index.htm?linkDelete&id=${nv.maNV}"
																		--%>>
														<button name="btnXOA1" type="button"
															class="btn btn-warning">Xóa</button>
													</a>
                                                    </div>
                                                </div>
                                            </div>
                                        </div>
                                    </div>
                            </td>

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