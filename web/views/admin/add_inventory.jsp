<%-- 
    Document   : admin_home
    Created on : Mar 12, 2023, 12:58:22 AM
    Author     : Bach
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="UTF-8">
        <title>AdminLTE 2 | Dashboard</title>
        <meta content='width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no' name='viewport'>
        <!-- Bootstrap 3.3.2 -->
        <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.1.2/css/bootstrap.min.css">
        <!-- <link href="<c:url value='/template/admin/bootstrap/css/bootstrap.min.css' />" rel="stylesheet" type="text/css" /> -->
        <!-- FontAwesome 4.3.0 -->
        <link href="https://maxcdn.bootstrapcdn.com/font-awesome/4.3.0/css/font-awesome.min.css" rel="stylesheet"
              type="text/css" />
        <!-- Ionicons 2.0.0 -->
        <link href="http://code.ionicframework.com/ionicons/2.0.0/css/ionicons.min.css" rel="stylesheet"
              type="text/css" />
        <!-- Theme style -->
        <link href="<c:url value='/template/admin/dist/css/AdminLTE.min.css' />" rel="stylesheet" type="text/css" />
        <!-- AdminLTE Skins. Choose a skin from the css/skins 
         folder instead of downloading all of them to reduce the load. -->
        <link href="<c:url value='/template/admin/dist/css/skins/_all-skins.min.css'/>" rel="stylesheet" type="text/css" />
        <!-- iCheck -->
        <link href="<c:url value='/template/admin/plugins/iCheck/flat/blue.css' />" rel="stylesheet" type="text/css" />
        <!-- Morris chart -->
        <link href="<c:url value='/template/admin/plugins/morris/morris.css' />" rel="stylesheet" type="text/css" />
        <!-- jvectormap -->
        <link href="<c:url value='/template/admin/plugins/jvectormap/jquery-jvectormap-1.2.2.css' />" rel="stylesheet"
              type="text/css" />
        <!-- Date Picker -->
        <link href="<c:url value='/template/admin/plugins/datepicker/datepicker3.css'/>" rel="stylesheet" type="text/css" />
        <!-- Daterange picker -->
        <link href="<c:url value='/template/admin/plugins/daterangepicker/daterangepicker-bs3.css'/>" rel="stylesheet"
              type="text/css" />
        <!-- bootstrap wysihtml5 - text editor -->
        <link href="<c:url value='/template/admin/plugins/bootstrap-wysihtml5/bootstrap3-wysihtml5.min.css'/>" rel="stylesheet"
              type="text/css" />

        <link rel="stylesheet" href="<c:url value='/template/admin/css/styles.css'/>">

    </head>
    <body class='skin-yellow'>

        <div class="wrapper">
            <!-- Header -->
            <%@include file="header.jsp" %>

            <!-- End Header -->

            <!--Sidebar-->
            <%@include file="sidebar.jsp" %>
            <!--End Sidebar-->


            <!--Content-->
            <div class="content-wrapper" style="min-height: 836px;">
                <section class="content-header">
                    <h1>
                        Thêm sản phẩm trong kho
                    </h1>
                    <ol class="breadcrumb">
                        <li><a href="<c:url value='/admin_home'/>"><i class="fa fa-dashboard"></i> Trang chủ</a></li>
                        <li><a href="<c:url value='/admin-list-inventory'/>">Quản lý sản phẩm trong kho</a></li>
                        <li class="active">Thêm sản phẩm trong kho</li>
                    </ol>
                </section>

                <section class="content">
                    <div class="row justify-content-center">
                        <div class="col-xl-6">
                            <form action="<c:url value='/admin-add-inventory'/>" id="form" method="post">
                                <div class="form-group row">
                                    <div class="col-sm-12">
                                        <a class="btn btn-primary" href="/ttcs/admin-choose-product">Chọn sản phẩm</a>
                                    </div>
                                    <label for="product_id" class="col-sm-4 col-form-label">Sản phẩm:</label>
                                    <div class="col-sm-8">
                                        <c:set var="product" value="${requestScope.product}"/>
                                        <p>
                                            ${(product.name != null)?product.name:'Chưa chọn sản phẩm'}
                                        </p>
                                        <input type="hidden" id="product_id" name="product_id" value="${product.id}" readonly>
                                        <img src="<c:url value='/assets/images/${product.galeries[0].thumbnail}'/>" alt="" class="thumbnail" id="imgInput">
                                    </div>
                                </div>
                                <div class="form-group row">
                                    <label for="size_id" class="col-sm-4 col-form-label">Kích thước:</label>
                                    <div class="col-sm-8">
                                        <select name="size_id" id="size_id">
                                            <c:forEach var="size" items="${requestScope.sizes}">
                                                <option ${(requestScope.size_id == size.id)?'selected':''} value="${size.id}">${size.name}</option>
                                            </c:forEach>
                                        </select>

                                    </div>
                                </div>
                                <div class="form-group row">
                                    <label for="quantity" class="col-sm-4 col-form-label">Số lượng: </label>
                                    <div class="col-sm-8">
                                        <input type="text" name="quantity" id="quantity">
                                    </div>
                                </div>
                                <button type="submit" class="btn btn-primary" id="gui">Thêm sản phẩm trong kho</button>
                            </form>
                        </div>
                    </div>
                </section>
            </div>
            <!--End Content-->

            <!--Footer-->
            <%@include file="footer.jsp" %>
            <!--End Footer-->
        </div>

        <!-- jQuery 2.1.3 -->
        <script src="<c:url value='/template/admin/plugins/jQuery/jQuery-2.1.3.min.js' />"></script>
        <!-- jQuery UI 1.11.2 -->
        <script src="http://code.jquery.com/ui/1.11.2/jquery-ui.min.js" type="text/javascript"></script>
        <!-- Resolve conflict in jQuery UI tooltip with Bootstrap tooltip -->
        <script>
            $.widget.bridge('uibutton', $.ui.button);
        </script>
        <!-- Bootstrap 3.3.2 JS -->
        <script src="<c:url value='/template/admin/bootstrap/js/bootstrap.min.js'/>" type="text/javascript"></script>
        <!-- Morris.js charts -->
        <script src="http://cdnjs.cloudflare.com/ajax/libs/raphael/2.1.0/raphael-min.js"></script>
        <script src="<c:url value='/template/admin/plugins/morris/morris.min.js'/>" type="text/javascript"></script>
        <!-- Sparkline -->
        <script src="<c:url value='/template/admin/plugins/sparkline/jquery.sparkline.min.js'/>" type="text/javascript"></script>
        <!-- jvectormap -->
        <script src="<c:url value='/template/admin/plugins/jvectormap/jquery-jvectormap-1.2.2.min.js'/>"
        type="text/javascript"></script>
        <script src="<c:url value='/template/admin/plugins/jvectormap/jquery-jvectormap-world-mill-en.js'/>"
        type="text/javascript"></script>
        <!-- jQuery Knob Chart -->
        <script src="<c:url value='/template/admin/plugins/knob/jquery.knob.js'/>" type="text/javascript"></script>
        <!-- daterangepicker -->
        <script src="<c:url value='/template/admin/plugins/daterangepicker/daterangepicker.js'/>" type="text/javascript"></script>
        <!-- datepicker -->
        <script src="<c:url value='/template/admin/plugins/datepicker/bootstrap-datepicker.js'/>" type="text/javascript"></script>
        <!-- Bootstrap WYSIHTML5 -->
        <script src="<c:url value='/template/admin/plugins/bootstrap-wysihtml5/bootstrap3-wysihtml5.all.min.js'/>"
        type="text/javascript"></script>
        <!-- iCheck -->
        <script src="<c:url value='/template/admin/plugins/iCheck/icheck.min.js' />" type="text/javascript"></script>
        <!-- Slimscroll -->
        <script src="<c:url value='/template/admin/plugins/slimScroll/jquery.slimscroll.min.js' />" type="text/javascript"></script>
        <!-- FastClick -->
        <script src=""<c:url value='/template/admin/plugins/fastclick/fastclick.min.js'/>"></script>
        <!-- AdminLTE App -->
        <script src="<c:url value='/template/admin/dist/js/app.min.js' />" type="text/javascript"></script>

        <!-- AdminLTE dashboard demo (This is only for demo purposes) -->
        <script src="<c:url value='/template/admin/dist/js/pages/dashboard.js' />" type="text/javascript"></script>

        <!-- AdminLTE for demo purposes -->
        <script src="<c:url value='/template/admin/dist/js/demo.js'/>" type="text/javascript"></script>

        <!-- My js file -->
        <script>


            var error = "${requestScope.error}";
            if (error !== "") {
                alert(error);
            }


            var fileInput = document.getElementById("product_id");
            var imgInput = document.getElementById("imgInput");
            fileInput.onchange = evt => {
                const [file] = fileInput.files;
                if (file) {
                    fileInput.setAttribute("value", file.name);
                    imgInput.src = URL.createObjectURL(file);
                }
            }



            var btnSubmit = document.getElementById("gui");
            btnSubmit.addEventListener("click", (e) => {
                e.preventDefault();
                var quantity = document.getElementById("quantity");
                if (isNaN(Number.parseInt(quantity.value))) {
                    alert("Vui lòng nhập đúng định dạng số lượng")
                    quantity.value = "";
                } else {
                    document.getElementById("form").submit();
                }
            })

        </script>

    </body>
</html>
