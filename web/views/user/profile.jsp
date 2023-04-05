<%-- 
    Document   : home
    Created on : Mar 25, 2023, 11:19:15 PM
    Author     : Bach
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="model.UserModel" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>

<!DOCTYPE html>
<html lang="en">

    <head>
        <meta charset="utf-8">
        <title>MultiShop - Online Shop Website Template</title>
        <meta content="width=device-width, initial-scale=1.0" name="viewport">
        <meta content="Free HTML Templates" name="keywords">
        <meta content="Free HTML Templates" name="description">

        <!-- Favicon -->
        <link href="img/favicon.ico" rel="icon">

        <!-- Google Web Fonts -->
        <link rel="preconnect" href="https://fonts.gstatic.com">
        <link href="https://fonts.googleapis.com/css2?family=Roboto:wght@400;500;700&display=swap" rel="stylesheet">  

        <!-- Font Awesome -->
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.3.0/css/all.min.css"
              integrity="sha512-SzlrxWUlpfuzQ+pcUCosxcglQRNAq/DZjVsC0lE40xsADsfeQoEypE+enwcOiGjk/bSuGGKHEyjSoQ1zVisanQ=="
              crossorigin="anonymous" referrerpolicy="no-referrer" />

        <!-- Libraries Stylesheet -->
        <link href="<c:url value="/template/user/lib/animate/animate.min.css"/>" rel="stylesheet">
        <link href="<c:url value="/template/user/lib/owlcarousel/assets/owl.carousel.min.css"/>" rel="stylesheet">

        <!-- Customized Bootstrap Stylesheet -->
        <link href="<c:url value="/template/user/css/style.css"/>" rel="stylesheet">
        <link href="<c:url value="/template/user/css/my-style.css"/>" rel="stylesheet">

        <link rel="stylesheet" href="/ttcs/template/user/update_profile/css/styles.css">
    </head>

    <body>

        <!-- Variable Start -->
        <c:set var="categories" value="${requestScope.categories}"/>
        <c:set var="products" value="${requestScope.products}"/>
        <c:set var="user" value="${sessionScope.user}"/>
        <!-- Variable End -->


        <!-- Header Start -->
        <header class="header">
            <!-- Topbar Start -->
            <div class="container-fluid">
                <div class="row align-items-center bg-light py-3 px-xl-5 d-none d-lg-flex">
                    <div class="col-lg-4">
                        <a href="<c:url value="/user_home"/>" class="text-decoration-none">
                            <span class="h1 text-uppercase text-primary bg-dark px-2">B</span>
                            <span class="h1 text-uppercase text-dark bg-primary px-2 ml-n1">Shop</span>
                        </a>
                    </div>
                    <div class="col-lg-4 col-6 text-left">
                        <form action="user_products" method="get" id="form1">
                            <div class="input-group">
                                <input type="text" name="product_name" class="form-control" placeholder="Tìm kiếm sản phẩm">
                                <div class="input-group-append">
                                    <button id="search_product" class="input-group-text bg-transparent text-primary">
                                        <i class="fa fa-search"></i>
                                    </button>
                                </div>
                            </div>
                        </form>
                    </div>
                </div>
            </div>
            <!-- Topbar End -->


            <!-- Navbar Start -->
            <div class="container-fluid bg-dark mb-30">
                <div class="row px-xl-5">
                    <div class="col-lg-3 d-none d-lg-block">
                        <a class="btn d-flex align-items-center justify-content-between bg-primary w-100" data-toggle="collapse"
                           href="#navbar-vertical" style="height: 65px; padding: 0 30px;">
                            <h6 class="text-dark m-0"><i class="fa fa-bars mr-2"></i>Áo Nam</h6>
                            <i class="fa fa-angle-down text-dark"></i>
                        </a>
                        <nav class="collapse position-absolute navbar navbar-vertical navbar-light align-items-start p-0 bg-light"
                             id="navbar-vertical" style="width: calc(100% - 30px); z-index: 999;">
                            <div class="navbar-nav w-100">
                                <c:forEach var="category" items="${categories}">
                                    <a href="user_products?list_category_id=${category.id}" class="nav-item nav-link">${category.name}</a>
                                </c:forEach>
                            </div>
                        </nav>
                    </div>
                    <div class="col-lg-9">
                        <nav class="navbar navbar-expand-lg bg-dark navbar-dark py-3 py-lg-0 px-0">
                            <a href="" class="text-decoration-none d-block d-lg-none">
                                <span class="h1 text-uppercase text-dark bg-light px-2">B</span>
                                <span class="h1 text-uppercase text-light bg-primary px-2 ml-n1">Shop</span>
                            </a>
                            <button type="button" class="navbar-toggler" data-toggle="collapse" data-target="#navbarCollapse">
                                <span class="navbar-toggler-icon"></span>
                            </button>
                            <div class="collapse navbar-collapse justify-content-between" id="navbarCollapse">
                                <div class="navbar-nav mr-auto py-0">
                                    <a href="<c:url value="/user_home"/>" class="nav-item nav-link active">Trang chủ</a>
                                    <a href="<c:url value="/user_products"/>" class="nav-item nav-link ">Toàn bộ sản phẩm</a>
                                    <a href="contact.html" class="nav-item nav-link">Liên Hệ</a>
                                </div>
                                <div class="navbar-nav py-0">
                                    <c:if test="${user != null && user.role.id == 1}">
                                        <a href="admin_home" class="nav-item nav-link">
                                            <i class="fa-solid fa-lock text-primary"></i>
                                            Trang quản lý
                                        </a>
                                    </c:if>
                                    <c:if test="${user != null}">
                                        <a href="user_profile" class="nav-item nav-link">
                                            <i class="fa-solid fa-user text-primary"></i>
                                            ${user.email}
                                        </a>
                                    </c:if>
                                    <c:if test="${user == null}">
                                        <a href="user_sign_in" class="nav-item nav-link">
                                            <i class="fa-solid fa-right-to-bracket text-primary"></i>
                                            Ðăng Nhập
                                        </a>
                                    </c:if>
                                    <a href="user_cart" class="nav-item nav-link">
                                        <i class="fas fa-shopping-cart text-primary"></i>
                                        Giỏ Hàng
                                        <span class="badge text-secondary border border-secondary rounded-circle"
                                              style="padding-bottom: 2px;">${requestScope.cart.totalItems}</span>
                                    </a>
                                </div>
                            </div>
                        </nav>
                    </div>
                </div>
            </div>
            <!-- Navbar End -->
        </header>
        <!-- Header End -->

        <!-- Content Start -->
        <section class="content">
            <div class="container">
                <div class="row flex-column">
                    <h1 class="text-center">Thông Tin Tài Khoản</h1>
                    <form action="" method="get" id="form" class="form">
                        <div class="form-group">
                            <label for="full-name">Họ Và Tên</label>
                            <input type="text" readonly class="form-control" id="full-name" name="full-name" value="${user.fullname}">
                            <span class="success-icon">
                                <i class="fa-solid fa-check"></i>
                            </span>
                            <span class="error-icon">
                                <i class="fa-solid fa-xmark"></i>
                            </span>
                            <small id="email-existed">Error</small>
                        </div>
                        <div class="form-group">
                            <label for="phone-number">Số Điện Thoại</label>
                            <input type="text" readonly class="form-control" id="phone-number" maxlength="11"
                                   name="phone_number" value="${user.phoneNumber}">
                            <span class="success-icon">
                                <i class="fa-solid fa-check"></i>
                            </span>
                            <span class="error-icon">
                                <i class="fa-solid fa-xmark"></i>
                            </span>
                            <small>Error</small>
                        </div>
                        <div class="form-group">
                            <label for="email">Số Điện Thoại</label>
                            <input type="text" readonly class="form-control" id="email" maxlength="11"
                                   name="email" value="${user.email}">
                            <span class="success-icon">
                                <i class="fa-solid fa-check"></i>
                            </span>
                            <span class="error-icon">
                                <i class="fa-solid fa-xmark"></i>
                            </span>
                            <small>Error</small>
                        </div>
                        <div class="form-group row justify-content-between">
                            <a href="user_change_password" class="btn btn-primary form-control col-3">Thay Đổi Mật Khẩu</a>
                            <a href="user_update_profile" class="btn btn-primary form-control col-3">Cập Nhật Thông Tin</a>
                            <a href="user_sign_out" class="btn sign-out form-control col-2"><i class="fa-solid fa-right-from-bracket text-primary"></i>Đăng Xuất</a>
                        </div>
                    </form>
                </div>

            </div>
        </section>
        <!-- Content End -->

        <!-- Footer Start -->
        <%@include file="footer.jsp" %>
        <!-- Footer End -->

        <!-- Back to Top -->
        <a href="#" class="btn btn-primary back-to-top"><i class="fa fa-angle-double-up"></i></a>


        <!-- JavaScript Libraries -->
        <script src="https://code.jquery.com/jquery-3.4.1.min.js"></script>
        <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/js/bootstrap.bundle.min.js"></script>
        <script src="<c:url value="/template/user/lib/easing/easing.min.js"/>"></script>
        <script src="<c:url value="/template/user/lib/owlcarousel/owl.carousel.min.js"/>"></script>

        <!-- Contact Javascript File -->
        <script src="<c:url value="/template/user/mail/jqBootstrapValidation.min.js"/>"></script>
        <script src="<c:url value="/template/user/mail/contact.js"/>"></script>

        <!-- Template Javascript -->
        <script src="<c:url value="/template/user/js/main.js"/>"></script>

        <script type="text/javascript">
            console.log("${user.id}")
            console.log("${user.fullname}");
            console.log("${user.email}");
            console.log("${user.password}");
            console.log("${user.phoneNumber}");
            
            var success = "${requestScope.success}";
            var error = "${requestScope.error}";
            var changePasswordSuccess = "${requestScope.changePasswordSuccess}";
            var changePasswordError = "${requestScope.changePasswordError}";
            var wrongOldPassword = "${requestScope.wrongOldPassword}";
            if (success !== ""){
                alert(success);
            } else if (error !== ""){
                alert(error);
            } else if (changePasswordError !== ""){
                alert(changePasswordError);
            } else if (changePasswordSuccess !== ""){
                alert(changePasswordSuccess);
            } else if (wrongOldPassword !== ""){
                alert(wrongOldPassword);
            }
        </script>

    </body>

</html>