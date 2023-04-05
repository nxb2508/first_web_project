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

            <!-- Cart Start -->
            <div class="container-fluid">
                <div class="row px-xl-5">
                    <div class="col-lg-8 table-responsive mb-5">
                        <table class="table table-light table-borderless table-hover mb-0">
                            <thead class="thead-dark">
                                <tr class="text-center">
                                    <th >Sản Phẩm</th>
                                    <th>Giá</th>
                                    <th>Số Lượng</th>
                                    <th>Thành Tiền</th>
                                    <th>Xóa</th>
                                </tr>
                            </thead>
                            <tbody class="align-middle">
                                <c:forEach var='item' items="${requestScope.items}">
                                    <tr>
                                        <td class="align-middle">
                                            <img class=" ml-2 mr-2" src="/ttcs/assets/images/${item.product.galeries[0].thumbnail}" alt="" style="width: 50px;">
                                            <span>${item.product.name}</span>
                                        </td>
                                        <td class="align-middle text-center">${item.product.price}</td>
                                        <td class="align-middle text-center">
                                            <div class="input-group quantity mx-auto" style="width: 100px;">
                                                <div class="input-group-btn">
                                                    <a href="user_check_item_quantity?product_id=${item.product.id}&number=${-1}" class="btn btn-sm btn-primary btn-minus" >
                                                        <i class="fa fa-minus"></i>
                                                    </a>
                                                </div>
                                                <input type="text" class="form-control form-control-sm bg-secondary border-0 text-center" value="${item.quantity}">
                                                <div class="input-group-btn">
                                                    <a href="user_check_item_quantity?product_id=${item.product.id}&number=${1}" class="btn btn-sm btn-primary btn-plus">
                                                        <i class="fa fa-plus"></i>
                                                    </a>
                                                </div>
                                            </div>
                                        </td>
                                        <td class="align-middle text-center">${(item.price * item.quantity)}</td>
                                        <td class="align-middle text-center"><a href="user_check_item_quantity?product_id=${item.product.id}&number=${0}" class="btn btn-sm btn-danger"><i class="fa fa-times"></i></a></td>
                                    </tr>
                                </c:forEach>
                            </tbody>
                        </table>
                    </div>
                    <div class="col-lg-4">
                        <h5 class="section-title position-relative text-uppercase mb-3"><span class="bg-secondary pr-3">Giỏ Hàng</span></h5>
                        <div class="bg-light p-30 mb-5">
                            <div class="pt-2">
                                <div class="d-flex justify-content-between mt-2 total-items">
                                    <h5>Tổng Số Sản Phẩm</h5>
                                    <h5>${requestScope.cart.totalItems}</h5>
                                </div>
                                <div class="d-flex justify-content-between mt-2">
                                    <h5>Tổng Tiền</h5>
                                    <h5>${requestScope.cart.totalMoney}</h5>
                                </div>
                                <button class="btn btn-block btn-primary font-weight-bold my-3 py-3">Thanh Toán</button>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
            <!-- Cart End -->

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

        <script>
            console.log("${user.fullname}");
            console.log("${user.email}");
            console.log("${user.password}");
            console.log("${user.phoneNumber}");
        </script>

    </body>

</html>