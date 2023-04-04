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
        <c:set var="checkCategoryId" value="${requestScope.checkCategoryId}"/>
        <c:set var="checkPrice" value="${requestScope.checkPrice}"/>
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
                                <input type="text" name="product_name" value="${requestScope.product_name}" class="form-control" placeholder="Tìm kiếm sản phẩm">
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
                                    <a href="<c:url value="/user_home"/>" class="nav-item nav-link ">Trang chủ</a>
                                    <a href="<c:url value="/user_products"/>" class="nav-item nav-link active">Toàn bộ sản phẩm</a>
                                    <a href="contact.html" class="nav-item nav-link">Liên Hệ</a>
                                </div>
                                <div class="navbar-nav py-0">
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
                                    <a href="" class="nav-item nav-link">
                                        <i class="fas fa-shopping-cart text-primary"></i>
                                        Giỏ Hàng
                                        <!-- <span class="badge text-secondary border border-secondary rounded-circle"
                                              style="padding-bottom: 2px;">0</span> -->
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
                        <form action="user_products" method="get" id="form">
                            <!-- ProductName Start -->
                            <div class="bg-light mb-30">
                                <div class="input-group">
                                    <input type="text" name="product_name" class="form-control" placeholder="Tìm kiếm sản phẩm" value="${requestScope.product_name}">
                                    <div class="input-group-append">
                                        <button id="search_product" type="submit" class="input-group-text bg-transparent text-primary">
                                            <i class="fa fa-search"></i>
                                        </button>
                                    </div>
                                </div>
                            </div>
                            <!-- ProductName Start -->

                            <!-- Categories Start -->
                            <h5 class="section-title position-relative text-uppercase mb-3"><span class="bg-secondary pr-3">Loại áo</span></h5>
                            <div class="bg-light p-4 mb-30">
                                <div class="custom-control custom-checkbox d-flex align-items-center justify-content-between mb-3">
                                    <input type="checkbox" name="list_category_id" value="0" onclick="checkForm2(this)" class="custom-control-input" id="category_0" ${checkCategoryId[0]?'checked':''}>
                                    <label class="custom-control-label" for="category_0">Tất cả</label>
                                    <span class="badge border font-weight-normal">${requestScope.allProducts.size()}</span>
                                </div>
                                <c:forEach begin="0" end="${categories.size() - 1}" var="i">
                                    <div class="custom-control custom-checkbox d-flex align-items-center justify-content-between mb-3">
                                        <input type="checkbox" name="list_category_id" value="${categories[i].id}" onclick="checkForm2(this)" class="custom-control-input" id="category_${categories[i].id}" ${(categories[i].id == requestScope.category_id)?'checked':''} ${checkCategoryId[i + 1]?'checked':''}>
                                        <label class="custom-control-label" for="category_${categories[i].id}">${categories[i].name}</label>
                                        <span class="badge border font-weight-normal">${categories[i].products.size()}</span>
                                    </div>
                                </c:forEach>
                            </div>
                            <!-- Categories End -->

                            <!-- Price Start -->
                            <h5 class="section-title position-relative text-uppercase mb-3"><span class="bg-secondary pr-3">Giá</span></h5>
                            <div class="bg-light p-4 mb-30">
                                <div class="custom-control custom-checkbox d-flex align-items-center justify-content-between mb-3">
                                    <input type="checkbox" name="price" value="0" onclick="checkForm3(this)" class="custom-control-input" ${checkPrice[0]?'checked':''} id="price-all">
                                    <label class="custom-control-label" for="price-all">Tất cả</label>
                                    <!--<span class="badge border font-weight-normal">1000</span>-->
                                </div>
                                <div class="custom-control custom-checkbox d-flex align-items-center justify-content-between mb-3">
                                    <input type="checkbox" name="price" value="1" onclick="checkForm3(this)"  class="custom-control-input" ${checkPrice[1]?'checked':''} id="price-1">
                                    <label class="custom-control-label" for="price-1">0 VNĐ - 199000 VNĐ</label>
                                    <!--<span class="badge border font-weight-normal">150</span>-->
                                </div>
                                <div class="custom-control custom-checkbox d-flex align-items-center justify-content-between mb-3">
                                    <input type="checkbox" name="price" value="2" onclick="checkForm3(this)"  class="custom-control-input" ${checkPrice[2]?'checked':''} id="price-2">
                                    <label class="custom-control-label" for="price-2">200000 VNĐ - 399000 VNĐ</label>
                                    <!--<span class="badge border font-weight-normal">295</span>-->
                                </div>
                                <div class="custom-control custom-checkbox d-flex align-items-center justify-content-between mb-3">
                                    <input type="checkbox" name="price" value="3" onclick="checkForm3(this)"  class="custom-control-input" ${checkPrice[3]?'checked':''} id="price-3">
                                    <label class="custom-control-label" for="price-3">400000 VNĐ - 599000 VNĐ</label>
                                    <!--<span class="badge border font-weight-normal">246</span>-->
                                </div>
                                <div class="custom-control custom-checkbox d-flex align-items-center justify-content-between mb-3">
                                    <input type="checkbox" name="price" value="4" onclick="checkForm3(this)"  class="custom-control-input" ${checkPrice[4]?'checked':''} id="price-4">
                                    <label class="custom-control-label" for="price-4">600000 VNĐ - 799000 VNĐ</label>
                                    <!--<span class="badge border font-weight-normal">145</span>-->
                                </div>
                                <div class="custom-control custom-checkbox d-flex align-items-center justify-content-between">
                                    <input type="checkbox" name="price" value="5" onclick="checkForm3(this)"  class="custom-control-input" ${checkPrice[5]?'checked':''} id="price-5">
                                    <label class="custom-control-label" for="price-5">800000 VNĐ - 999000 VNĐ</label>
                                    <!--<span class="badge border font-weight-normal">168</span>-->
                                </div>

                            </div>
                            <!-- Price End -->

                            <!-- Sort_by Start -->
                            <h5 class="section-title position-relative text-uppercase mb-3"><span class="bg-secondary pr-3">Sắp xếp</span></h5>
                            <div class="bg-light p-4 mb-30">
                                <div class="custom-control custom-checkbox d-flex align-items-center justify-content-between mb-3">
                                    <input type="checkbox" ${(requestScope.sort_by == 'asc')?'checked':''} class="custom-control-input" id="asc" name="sort_by" value="asc">
                                    <label class="custom-control-label" for="asc">Tăng dần</label>
                                </div>
                                <div class="custom-control custom-checkbox d-flex align-items-center justify-content-between mb-3">
                                    <input type="checkbox" ${(requestScope.sort_by == 'desc')?'checked':''} class="custom-control-input" id="desc" name="sort_by" value="desc">
                                    <label class="custom-control-label" for="desc">Giảm dần</label>
                                </div>
                            </div>
                            <!-- Sort_by End -->

                        </form>


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

        <script type="text/javascript">
            var form = document.getElementById("form")
            var asc = document.getElementById("asc");
            var desc = document.getElementById("desc");
            asc.onclick = function () {
                if (desc.checked == true) {
                    desc.checked = false;
                    asc.checked = true;
                }
                form.submit()
            }
            desc.onclick = function () {
                if (asc.checked == true) {
                    asc.checked = false;
                    desc.checked = true;
                }
                form.submit()
            }

            // var search_product = document.getElementById("search_product")
            // search_product.addEventListener("click", (e) => {
            //     e.preventDefault()
            //     for (let form of forms) {
            //         form.submit()
            //     }
            // })

            function checkForm2(input) {
                var list_category_id = document.getElementsByName("list_category_id");
                if (input.id == "category_0" && list_category_id[0].checked == true) {
                    for (var i = 1; i < list_category_id.length; i++) {
                        list_category_id[i].checked = false;
                    }
                } else {
                    for (var i = 1; i < list_category_id.length; i++) {
                        if (list_category_id[i].checked == true) {
                            list_category_id[0].checked = false;
                            break;
                        }
                    }
                }
                form.submit();
            }

            function checkForm3(input) {
                var price = document.getElementsByName("price");
                if (input.id == "price-all" && price[0].checked == true) {
                    for (var i = 1; i < price.length; i++) {
                        price[i].checked = false;
                    }
                } else {
                    for (var i = 1; i < price.length; i++) {
                        if (price[i].checked == true) {
                            price[0].checked = false;
                        }
                    }
                }
                form.submit();
            }
        </script>

    </body>

</html>