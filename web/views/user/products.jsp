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
        <%@include file="header.jsp" %>
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
                        <!-- Price Start -->
                        <h5 class="section-title position-relative text-uppercase mb-3"><span class="bg-secondary pr-3">Filter by price</span></h5>
                        <div class="bg-light p-4 mb-30">
                            <form>
                                <div class="custom-control custom-checkbox d-flex align-items-center justify-content-between mb-3">
                                    <input type="checkbox" class="custom-control-input" checked id="price-all">
                                    <label class="custom-control-label" for="price-all">Mọi giá</label>
                                    <span class="badge border font-weight-normal">${fn:length(requestScope.products)}</span>
                                </div>
                                <div class="custom-control custom-checkbox d-flex align-items-center justify-content-between mb-3">
                                    <input type="checkbox" class="custom-control-input" id="price-1">
                                    <label class="custom-control-label" for="price-1">100.000 VNĐ - 199.000 VNĐ</label>
                                    <span class="badge border font-weight-normal">${fn:length(requestScope.productsByPrice1)}</span>
                                </div>
                                <div class="custom-control custom-checkbox d-flex align-items-center justify-content-between mb-3">
                                    <input type="checkbox" class="custom-control-input" id="price-2">
                                    <label class="custom-control-label" for="price-2">200.000 VNĐ - 399.000 VNĐ</label>
                                    <span class="badge border font-weight-normal">${fn:length(requestScope.productsByPrice2)}</span>
                                </div>
                                <div class="custom-control custom-checkbox d-flex align-items-center justify-content-between mb-3">
                                    <input type="checkbox" class="custom-control-input" id="price-3">
                                    <label class="custom-control-label" for="price-3">400.000 VNĐ - 599.000 VNĐ</label>
                                    <span class="badge border font-weight-normal">${fn:length(requestScope.productsByPrice3)}</span>
                                </div>
                                <div class="custom-control custom-checkbox d-flex align-items-center justify-content-between mb-3">
                                    <input type="checkbox" class="custom-control-input" id="price-4">
                                    <label class="custom-control-label" for="price-4">600.000 VNĐ - 799.000 VNĐ</label>
                                    <span class="badge border font-weight-normal">${fn:length(requestScope.productsByPrice4)}</span>
                                </div>
                                <div class="custom-control custom-checkbox d-flex align-items-center justify-content-between">
                                    <input type="checkbox" class="custom-control-input" id="price-5">
                                    <label class="custom-control-label" for="price-5">800.000 VNĐ - 999.000 VNĐ</label>
                                    <span class="badge border font-weight-normal">${fn:length(requestScope.productsByPrice5)}</span>
                                </div>
                            </form>
                        </div>
                                
                                
                        <!-- Price End -->

                    </div>
                    <!-- Shop Sidebar End -->


                    <!-- Shop Product Start -->
                    <div class="col-lg-9 col-md-8">
                        <div class="row pb-3">
                            <div class="col-12 pb-1">
                                <div class="d-flex align-items-center justify-content-between mb-4">
                                    <div class="ml-2">
                                        <div class="btn-group">
                                            <button type="button" class="btn btn-sm btn-light dropdown-toggle" data-toggle="dropdown">Sắp xếp theo giá</button>
                                            <div class="dropdown-menu dropdown-menu-right">
                                                <a class="dropdown-item" href="#">Tăng Dần</a>
                                                <a class="dropdown-item" href="#">Giảm Dần</a>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                            </div>
                            <c:forEach var="product" items="${products}">
                                <%@include file="product_item.jsp" %>
                            </c:forEach>

                            <div class="col-12">
                                <nav>
                                    <ul class="pagination justify-content-center">
                                        <li class="page-item disabled"><a class="page-link" href="#">Previous</span></a></li>
                                        <li class="page-item active"><a class="page-link" href="#">1</a></li>
                                        <li class="page-item"><a class="page-link" href="#">2</a></li>
                                        <li class="page-item"><a class="page-link" href="#">3</a></li>
                                        <li class="page-item"><a class="page-link" href="#">Next</a></li>
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