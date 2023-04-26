<%-- Document : admin_home Created on : Mar 12, 2023, 12:58:22 AM Author : Bach --%>

<%@page contentType="text/html" pageEncoding="UTF-8" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix = "fmt" uri = "http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>

    <head>
        <meta charset="UTF-8">
        <title>AdminLTE 2 | Dashboard</title>
        <meta content='width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no' name='viewport'>
        <!-- Bootstrap 3.3.2 -->
        <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.1.2/css/bootstrap.min.css">
        <!-- <link href="<c:url value='/template/admin/bootstrap/css/bootstrap.min.css' />" rel="stylesheet"
            type="text/css" /> -->
        <!-- FontAwesome 4.3.0 -->
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.3.0/css/all.min.css" integrity="sha512-SzlrxWUlpfuzQ+pcUCosxcglQRNAq/DZjVsC0lE40xsADsfeQoEypE+enwcOiGjk/bSuGGKHEyjSoQ1zVisanQ==" crossorigin="anonymous" referrerpolicy="no-referrer" />
        <!-- <link href="https://maxcdn.bootstrapcdn.com/font-awesome/4.3.0/css/font-awesome.min.css"
              rel="stylesheet" type="text/css" /> -->
        <!-- Ionicons 2.0.0 -->
        <link href="http://code.ionicframework.com/ionicons/2.0.0/css/ionicons.min.css" rel="stylesheet"
              type="text/css" />
        <!-- Theme style -->
        <link href="<c:url value='/template/admin/dist/css/AdminLTE.min.css' />" rel="stylesheet"
              type="text/css" />
        <!-- AdminLTE Skins. Choose a skin from the css/skins 
 folder instead of downloading all of them to reduce the load. -->
        <link href="<c:url value='/template/admin/dist/css/skins/_all-skins.min.css'/>" rel="stylesheet"
              type="text/css" />
        <!-- iCheck -->
        <link href="<c:url value='/template/admin/plugins/iCheck/flat/blue.css' />" rel="stylesheet"
              type="text/css" />
        <!-- Morris chart -->
        <link href="<c:url value='/template/admin/plugins/morris/morris.css' />" rel="stylesheet"
              type="text/css" />
        <!-- jvectormap -->
        <link href="<c:url value='/template/admin/plugins/jvectormap/jquery-jvectormap-1.2.2.css' />"
              rel="stylesheet" type="text/css" />
        <!-- Date Picker -->
        <link href="<c:url value='/template/admin/plugins/datepicker/datepicker3.css'/>" rel="stylesheet"
              type="text/css" />
        <!-- Daterange picker -->
        <link href="<c:url value='/template/admin/plugins/daterangepicker/daterangepicker-bs3.css'/>"
              rel="stylesheet" type="text/css" />
        <!-- bootstrap wysihtml5 - text editor -->
        <link href="<c:url value='/template/admin/plugins/bootstrap-wysihtml5/bootstrap3-wysihtml5.min.css'/>"
              rel="stylesheet" type="text/css" />

        <!-- My css -->
        <link rel="stylesheet" href="<c:url value='/template/admin/css/styles.css'/>">

        <!-- Panigation -->
    </head>

    <body class="skin-yellow">

        <div class="wrapper">
            <!-- Header -->
            <%@include file="header.jsp" %>
            <!-- End Header -->

            <!--Sidebar-->
            <%@include file="sidebar.jsp" %>
            <!--End Sidebar-->

            <!--Content-->
            <div class="content-wrapper" style="min-height: 836px;">
                <!-- Content Header (Page header) -->
                <section class="content-header">
                    <h1>
                        Cập nhật đơn hàng
                    </h1>
                    <ol class="breadcrumb">
                        <li><a href="<c:url value='/admin_home'/>"><i class="fa fa-dashboard"></i> Trang chủ</a></li>
                        <li class="active"><a href="<c:url value='/admin-list-order'/>">Quản lý đơn hàng</a></li>
                        <li class="active">Cập nhật đơn hàng</li>
                    </ol>
                </section>

                <!-- Main content -->
                <section class="content">
                    <div class="row justify-content-center justify-content-xl-center">
                        <div class="col-xl-12 ">
                            <!-- table -->
                            <c:set var="order" value="${requestScope.order}"/>
                            <table class="table table-bordered">
                                <thead>
                                    <tr>
                                        <th scope="col">Tên Sản Phẩm</th>
                                        <th scope="col">Kích Thước</th>
                                        <th scope="col">Số Lượng</th>
                                        <th scope="col">Giá</th>
                                        <th scope="col">Thành Tiền</th>
                                    </tr>
                                </thead>
                                <tbody>
                                    <c:forEach var="orderDetail" items="${order.orderDetails}">
                                        <tr class="data-href">
                                            <th>${orderDetail.inventory.product.name}</th>
                                            <td>${orderDetail.inventory.size.name}</td>
                                            <td>${orderDetail.quantity}</td>
                                            <td><fmt:formatNumber type = "number" maxFractionDigits = "3" value = "${orderDetail.price}" /> VNĐ</td>
                                            <td><fmt:formatNumber type = "number" maxFractionDigits = "3" value = "${(orderDetail.quantity*orderDetail.price)}" /> VNĐ</td>
                                        </tr>
                                    </c:forEach>
                                    <tr class="data-href">
                                        <th></th>
                                        <td></td>
                                        <td></td>
                                        <td>Tổng Tiền: </td>
                                        <td><fmt:formatNumber type = "number" maxFractionDigits = "3" value = "${order.totalMoney}" /> VNĐ</td>
                                    </tr>
                                </tbody>
                            </table>
                            <!-- end table -->
                        </div><!-- /.col -->
                        <div class="col-12 row justify-content-center">
                            <form action="admin-update-order-status" method="post">
                                <div class="form-group">
                                    <label for="status">Trạng Thái Đơn Hàng: </label>
                                    <select class="status-select" id="status" name="status_id">
                                        <c:forEach var="status" items="${requestScope.statuses}">
                                            <option value="${status.id}" ${(status.id == order.status.id)?'selected':''}>${status.name}</option>
                                        </c:forEach>
                                    </select>
                                </div>
                                <div class="form-group d-flex justify-content-center">
                                    <input type="hidden" name="order_id" value="${order.id}">
                                    <button class="btn btn-primary">Cập Nhật</button>
                                </div>
                            </form>
                        </div>
                    </div><!-- /.row -->


                </section><!-- /.content -->
            </div>
            <!--End Content-->

            <!--Footer-->
            <%@include file="footer.jsp" %>
            <!--End Footer-->
        </div>

        <!-- jQuery 2.1.3 -->
        <!-- <script src="<c:url value='/template/admin/plugins/jQuery/jQuery-2.1.3.min.js' />"></script> -->
        <script src="https://code.jquery.com/jquery-3.3.1.min.js"></script>

        <!-- jQuery UI 1.11.2 -->
        <script src="http://code.jquery.com/ui/1.11.2/jquery-ui.min.js" type="text/javascript"></script>
        <!-- Resolve conflict in jQuery UI tooltip with Bootstrap tooltip -->
        <script>
            $.widget.bridge('uibutton', $.ui.button);
        </script>
        <!-- Bootstrap 3.3.2 JS -->
        <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.1.2/js/bootstrap.min.js"></script>

                <!-- <script src="<c:url value='/template/admin/bootstrap/js/bootstrap.min.js'/>"
                    type="text/javascript"></script> -->
        <!-- Morris.js charts -->
        <script src="http://cdnjs.cloudflare.com/ajax/libs/raphael/2.1.0/raphael-min.js"></script>
        <script src="<c:url value='/template/admin/plugins/morris/morris.min.js'/>"
        type="text/javascript"></script>
        <!-- Sparkline -->
        <script src="<c:url value='/template/admin/plugins/sparkline/jquery.sparkline.min.js'/>"
        type="text/javascript"></script>
        <!-- jvectormap -->
        <script src="<c:url value='/template/admin/plugins/jvectormap/jquery-jvectormap-1.2.2.min.js'/>"
        type="text/javascript"></script>
        <script src="<c:url value='/template/admin/plugins/jvectormap/jquery-jvectormap-world-mill-en.js'/>"
        type="text/javascript"></script>
        <!-- jQuery Knob Chart -->
        <script src="<c:url value='/template/admin/plugins/knob/jquery.knob.js'/>"
        type="text/javascript"></script>
        <!-- daterangepicker -->
        <script src="<c:url value='/template/admin/plugins/daterangepicker/daterangepicker.js'/>"
        type="text/javascript"></script>
        <!-- datepicker -->
        <script src="<c:url value='/template/admin/plugins/datepicker/bootstrap-datepicker.js'/>"
        type="text/javascript"></script>
        <!-- Bootstrap WYSIHTML5 -->
        <script
            src="<c:url value='/template/admin/plugins/bootstrap-wysihtml5/bootstrap3-wysihtml5.all.min.js'/>"
        type="text/javascript"></script>
        <!-- iCheck -->
        <script src="<c:url value='/template/admin/plugins/iCheck/icheck.min.js' />"
        type="text/javascript"></script>
        <!-- Slimscroll -->
        <script src="<c:url value='/template/admin/plugins/slimScroll/jquery.slimscroll.min.js' />"
        type="text/javascript"></script>
        <!-- FastClick -->
        <script src="<c:url value='/template/admin/plugins/fastclick/fastclick.min.js' />"></script>
        <!-- AdminLTE App -->
        <script src="<c:url value='/template/admin/dist/js/app.min.js' />" type="text/javascript"></script>

        <!-- AdminLTE dashboard demo (This is only for demo purposes) -->
        <script src="<c:url value='/template/admin/dist/js/pages/dashboard.js' />"
        type="text/javascript"></script>

        <!-- AdminLTE for demo purposes -->
        <script src="<c:url value='/template/admin/dist/js/demo.js'/>" type="text/javascript"></script>

        <script type="text/javascript">
            var success = "${requestScope.success}";
            var error = "${requestScope.error}";
            if (success !== "") {
                alert(success);
            } else if (error !== "") {
                alert(error);
            }
        </script>
    </body>

</html>