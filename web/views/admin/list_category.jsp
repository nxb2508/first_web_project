<%-- Document : admin_home Created on : Mar 12, 2023, 12:58:22 AM Author : Bach --%>

<%@page contentType="text/html" pageEncoding="UTF-8" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
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
                        Quản lý loại sản phẩm
                    </h1>
                    <ol class="breadcrumb">
                        <li><a href="<c:url value='/admin_home'/>"><i class="fa fa-dashboard"></i> Trang chủ</a></li>
                        <li class="active">Quản lý loại sản phẩm</li>
                    </ol>
                </section>

                <!-- Main content -->
                <section class="content">
                    <div class="row justify-content-center justify-content-xl-center">
                        <div class="col-xl-6 d-flex content-top justify-content-between">
                            <a class="btn btn-primary" href="<c:url value='/add_category'/>" role="button">Thêm loại sản phẩm</a>
                            <form action="<c:url value='/search_category'/>" method="get" class="sidebar-form col-xl-4">
                                <div class="input-group">
                                    <input type="text" name="category_name" class="form-control" placeholder="Tìm kiếm loại sản phẩm" value="${requestScope.category_name}">
                                    <span class="input-group-btn">
                                        <button type="submit" id="search-btn" class="btn btn-flat"><i class="fa fa-search"></i></button>
                                    </span>
                                </div>
                            </form>
                        </div>
                        <div class="col-xl-12 ">
                            <!-- table -->
                            <c:set var="categories" value="${requestScope.categories}" />
                            <table class="table table-bordered">
                                <thead>
                                    <tr>
                                        <th scope="col">ID</th>
                                        <th scope="col">Tên</th>
                                        <th scope="col">Sửa</th>
                                        <th scope="col">Xóa</th>
                                    </tr>
                                </thead>
                                <tbody>
                                    <c:forEach items="${categories}" var="category">
                                        <tr>
                                            <th scope="row">${category.id}</th>
                                            <td>${category.name}</td>
                                            <td><a href="<c:url value='/update_category?id=${category.id}&name=${category.name}'/>"><i class="fa-regular fa-pen-to-square"></i></a></td>
                                            <td><a href="#" onclick="deleteCategory(${category.id})"><i class="fa-solid fa-trash"></i></a></td>
                                        </tr>
                                    </c:forEach>
                                </tbody>
                            </table>

                            <!-- end table -->
                        </div><!-- /.col -->
                        <div class="col-xl-12 d-flex justify-content-center">
                            <ul class="pagination">
                                <c:set var="page" value="${requestScope.page}"/>
                                <c:forEach begin="${1}" end="${requestScope.totalPages}" var="i">
                                    <li class="page-item ${(i==page)?"active":""}"><a class="page-link" href="list_category?page=${i}">${i}</a></li>
                                </c:forEach>
                            </ul>
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
        <script src="" <c:url value='/template/admin/plugins/fastclick/fastclick.min.js' />"></script>
        <!-- AdminLTE App -->
        <script src="<c:url value='/template/admin/dist/js/app.min.js' />" type="text/javascript"></script>

        <!-- AdminLTE dashboard demo (This is only for demo purposes) -->
        <script src="<c:url value='/template/admin/dist/js/pages/dashboard.js' />"
        type="text/javascript"></script>

        <!-- AdminLTE for demo purposes -->
        <script src="<c:url value='/template/admin/dist/js/demo.js'/>" type="text/javascript"></script>

        <script type="text/javascript">
            function deleteCategory(id) {
                if (confirm('Bạn sẽ đồng thời xóa các sản phẩm thuộc loại sản phẩm này!!!')) {
                    window.location = 'delete_category?id=' + id;
                }
            }
            var updated = "${requestScope.updated}";
            var added = "${requestScope.added}";
            var deleted = "${requestScope.deleted}";
            if(updated !== "") {
                alert(updated);
            } else if (added !== "") {
                alert(added);
            } else if (deleted) {
                alert(deleted);
            }
        </script>
    </body>

</html>