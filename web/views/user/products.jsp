<%-- 
    Document   : home
    Created on : Mar 25, 2023, 11:19:15 PM
    Author     : Bach
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
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
                                    <a href="" class="nav-item nav-link">${category.name}</a>
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
                                    <a href="<c:url value="/user_home"/>" class="nav-item nav-link ">Trang chủ</a>
                                    <a href="<c:url value="/user_products"/>" class="nav-item nav-link active">Toàn bộ sản phẩm</a>
                                    <a href="contact.html" class="nav-item nav-link">Liên Hệ</a>
                                </div>
                                <div class="navbar-nav ml-auto py-0 d-none d-lg-block">
                                    <a href="" class="btn px-0">
                                        <i class="fa-solid fa-user text-primary"></i>
                                        <span class="badge text-secondary border border-secondary rounded-circle"
                                              style="padding-bottom: 2px;">0</span>
                                    </a>
                                    <a href="" class="btn px-0 ml-3">
                                        <i class="fas fa-shopping-cart text-primary"></i>
                                        <span class="badge text-secondary border border-secondary rounded-circle"
                                              style="padding-bottom: 2px;">0</span>
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

            <!-- Breadcrumb Start -->
            <div class="container-fluid">
                <div class="row px-xl-5">
                    <div class="col-12">
                        <nav class="breadcrumb bg-light mb-30">
                            <a class="breadcrumb-item text-dark" href="<c:url value='/user_home'/>">Trang chủ</a>
                            <span class="breadcrumb-item active">Toàn bộ sản phẩm</span>
                        </nav>
                    </div>
                </div>
            </div>
            <!-- Breadcrumb End -->


            <!-- Shop Start -->
            <div class="container-fluid">
                <div class="row px-xl-5">
                    <!-- Shop Sidebar Start -->
                    <div class="col-lg-3 col-md-4">
                        <form action="user_search_product" method="get">
                            <div class="bg-light mb-30">
                                <div class="input-group">
                                    <input type="text" name="product_description" class="form-control" placeholder="Tìm kiếm sản phẩm">
                                    <div class="input-group-append">
                                        <button class="search">
                                            <span class="input-group-text bg-transparent text-primary search-icon">
                                                <i class="fa fa-search"></i>
                                            </span>
                                        </button>
                                    </div>
                                </div>
                            </div>


                            <h5 class="section-title position-relative text-uppercase mb-3"><span class="bg-secondary pr-3">Loại Áo</span></h5>
                            <div class="bg-light mb-30">
                                <div class="input-group">
                                    <select class="form-control" name="category_id">
                                        <option value="all_categories">Tất Cả</option>
                                        <c:forEach var="category" items="${categories}">
                                            <option value="${category.id}">${category.name}</option>
                                        </c:forEach>
                                    </select>
                                </div>
                            </div>

                            <h5 class="section-title position-relative text-uppercase mb-3"><span class="bg-secondary pr-3">Lọc Theo Giá</span></h5>
                            <div class="bg-light p-4 mb-30">
                                <div class="custom-control custom-checkbox d-flex align-items-center justify-content-between mb-3">
                                    <input type="checkbox" class="custom-control-input" checked id="price-all" readonly value="price-all" name="price">
                                    <label class="custom-control-label" for="price-all">Mọi giá</label>
                                    <span class="badge border font-weight-normal">${fn:length(requestScope.products)}</span>
                                </div>
                                <div class="custom-control custom-checkbox d-flex align-items-center justify-content-between mb-3">
                                    <input type="checkbox" class="custom-control-input" id="price-1" value="price-100-199" name="price">
                                    <label class="custom-control-label" for="price-1">100.000 VNĐ - 199.000 VNĐ</label>
                                    <span class="badge border font-weight-normal">${fn:length(requestScope.productsByPrice1)}</span>
                                </div>
                                <div class="custom-control custom-checkbox d-flex align-items-center justify-content-between mb-3">
                                    <input type="checkbox" class="custom-control-input" id="price-2" value="price-200-399" name="price">
                                    <label class="custom-control-label" for="price-2">200.000 VNĐ - 399.000 VNĐ</label>
                                    <span class="badge border font-weight-normal">${fn:length(requestScope.productsByPrice2)}</span>
                                </div>
                                <div class="custom-control custom-checkbox d-flex align-items-center justify-content-between mb-3">
                                    <input type="checkbox" class="custom-control-input" id="price-3" value="price-400-599" name="price">
                                    <label class="custom-control-label" for="price-3">400.000 VNĐ - 599.000 VNĐ</label>
                                    <span class="badge border font-weight-normal">${fn:length(requestScope.productsByPrice3)}</span>
                                </div>
                                <div class="custom-control custom-checkbox d-flex align-items-center justify-content-between mb-3">
                                    <input type="checkbox" class="custom-control-input" id="price-4" value="price-600-799" name="price">
                                    <label class="custom-control-label" for="price-4">600.000 VNĐ - 799.000 VNĐ</label>
                                    <span class="badge border font-weight-normal">${fn:length(requestScope.productsByPrice4)}</span>
                                </div>
                                <div class="custom-control custom-checkbox d-flex align-items-center justify-content-between">
                                    <input type="checkbox" class="custom-control-input" id="price-5" value="price-800-999" name="price">
                                    <label class="custom-control-label" for="price-5">800.000 VNĐ - 999.000 VNĐ</label>
                                    <span class="badge border font-weight-normal">${fn:length(requestScope.productsByPrice5)}</span>
                                </div>
                            </div>
                        </form>

                        <h5 class="section-title position-relative text-uppercase mb-3"><span class="bg-secondary pr-3">Sắp xếp theo giá</span></h5>
                        <div class="bg-light p-4 mb-30">
                            <div class="custom-control custom-radio d-flex align-items-center justify-content-between mb-3">
                                <input type="radio" class="custom-control-input" checked id="sort-1" readonly value="none" name="sort_by">
                                <label class="custom-control-label" for="sort-1">Không</label>
                            </div>
                            <div class="custom-control custom-radio d-flex align-items-center justify-content-between mb-3">
                                <input type="radio" class="custom-control-input" id="sort-2" value="asc" name="sort_by">
                                <label class="custom-control-label" for="sort-2">Tăng Dần</label>
                            </div>
                            <div class="custom-control custom-radio d-flex align-items-center justify-content-between mb-3">
                                <input type="radio" class="custom-control-input" id="sort-3" value="desc" name="sort_by">
                                <label class="custom-control-label" for="sort-3">Giảm Dần</label>
                            </div>
                        </div>

                    </div>
                    <!-- Shop Sidebar End -->


                    <!-- Shop Product Start -->
                    <div class="col-lg-9 col-md-8">
                        <div class="row pb-3">
                            <c:forEach var="product" items="${products}">
                                <%@include file="product_item.jsp" %>
                            </c:forEach>

                            <div class="col-12">
                                <nav>
                                    <ul class="pagination justify-content-center">
                                        <c:set var="page" value="${requestScope.page}"/>
                                        <c:forEach begin="${1}" end="${requestScope.totalPages}" var="i">
                                        <li class="page-item ${(i==page)?"active":""}"><a class="page-link" href="user_products?page=${i}">${i}</a></li>
                                        </c:forEach>
                                    </ul>
                                </nav>
                            </div>
                        </div>
                    </div>
                    <!-- Shop Product End -->
                </div>
            </div>
            <!-- Shop End -->
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
    </body>

</html>