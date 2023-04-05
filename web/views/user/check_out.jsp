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
        <c:set var="cart" value="${requestScope.cart}"/>
        <c:set var="items" value="${requestScope.items}"/>
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
                                              style="padding-bottom: 2px;">${cart.totalItems}</span>
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

            <!-- Checkout Start -->
            <form action="user_check_out" id="form-check-out" method="post" class="container-fluid">
                <div class="row px-xl-5">
                    <div class="col-lg-4">
                        <h5 class="section-title position-relative text-uppercase mb-3"><span class="bg-secondary pr-3">Thông Tin Khách Hàng</span></h5>
                        <div class="bg-light p-30 mb-5">
                            <div class="row">
                                <div class="col-md-12 form-group">
                                    <label>Họ Và Tên</label>
                                    <input name="fullname" id="fullname" value="${user.fullname}" class="form-control" type="text" placeholder="" required>
                                </div>
                                <div class="col-md-12 form-group">
                                    <label>Số Điện Thoại</label>
                                    <input name="phone_number" id="phone_number" value="${user.phoneNumber}" class="form-control" type="text" placeholder="">
                                </div>
                                <div class="col-md-12 form-group">
                                    <label>E-mail</label>
                                    <input name="email" value="${user.email}" class="form-control" readonly type="text" placeholder="">
                                </div>
                                <div class="col-md-12 form-group">
                                    <label>Địa Chỉ Giao Hàng</label>
                                    <input name="address" id="address" class="form-control" type="text" placeholder="" required>
                                </div>
                                <div class="col-md-12 form-group">
                                    <label>Ghi Chú</label>
                                    <input name="note" class="form-control" type="text" placeholder="">
                                </div>

                            </div>
                        </div>
                    </div>
                    <div class="col-lg-8">
                        <h5 class="section-title position-relative text-uppercase mb-3"><span class="bg-secondary pr-3">Thông Tin Đơn Hàng</span></h5>
                        <div class="bg-light p-30 mb-5">
                            <div class="border-bottom">
                                <div class="d-flex justify-content-between">
                                    <div style="width: 60%">
                                        <h6 class="mb-3">Danh Sách Sản Phẩm</h6>
                                    </div>
                                    <div class="text-center" style="width: 20%">Số Lượng</div>
                                    <div class="text-center" style="width: 20%">Giá</div>
                                </div>
                                <c:forEach var="item" items="${cart.items}">
                                    <div class="d-flex justify-content-between">
                                        <div style="width: 60%">
                                            <p>${item.product.name}</p>
                                        </div>
                                        <div class="text-center" style="width: 20%"><p>${item.quantity}</p></div>
                                        <div class="text-center" style="width: 20%"><p>${item.price}</p></div>
                                    </div>
                                </c:forEach>
                            </div>
                            <div class="pt-2">
                                <div class="d-flex justify-content-between mt-2">
                                    <h5 style="width: 80%">Tổng Tiền</h5>
                                    <h5 class="text-center" style="width: 20%">${cart.totalMoney}</h5>
                                </div>
                            </div>
                        </div>
                        <div class="mb-5">
                            <h5 class="position-relative text-uppercase text-center mb-3">Vui Lòng Nhập Đúng Số Điện Thoại Để Chúng Tôi Xác Nhận Trước Khi Giao Hàng</h5>
                            <div class="bg-light p-30">
                                <button id="btn-check-out" type="submit" class="btn btn-block btn-primary font-weight-bold py-3">Đặt Hàng</button>
                            </div>
                        </div>
                    </div>
                </div>
            </form>
            <!-- Checkout End -->

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
            var phoneNumber = document.getElementById("phone_number");
            var address = document.getElementById("address");
            var fullname = document.getElementById("fullname");
            var btnCheckOut = document.getElementById("btn-check-out");
            const phoneNumberRegex = /(84|0[3|5|7|8|9])+([0-9]{8})\b/g;

            btnCheckOut.addEventListener("click", (evt) => {
                evt.preventDefault();
                const phoneNumberValue = phoneNumber.value.trim();
                const addressValue = address.value.trim();
                const fullnameValue = fullname.value.trim();
                const totalItems = Number.parseInt(${cart.totalItems});

                console.log(totalItems);

                if (totalItems <= 0) {
                    alert("Vui Lòng Chọn Sản Phẩm Trước Khi Đặt Hàng");
                } else if(fullnameValue === ""){
                    alert("Vui Lòng Nhập Họ Và Tên");
                    phoneNumber.focus();
                }else if(!phoneNumberValue.match(phoneNumberRegex)){
                    alert("Vui Lòng Nhập Đúng Số Điện Thoại");
                    phoneNumber.focus();
                } else if (addressValue === ""){
                    alert("Vui Lòng Nhập Địa Chỉ");
                    address.focus();
                } else {
                    document.getElementById("form-check-out").submit();

                }
            })
        </script>

    </body>

</html>