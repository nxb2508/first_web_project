<%@page contentType="text/html" pageEncoding="UTF-8"%>

<!-- Header -->

<c:set var="user" value="${sessionScope.user}"/>

<header class="main-header">
    <a href="<c:url value='/admin_home'/>" class="logo"><b>Trang Quản Lý</b></a>
    <!-- Header Navbar: style can be found in header.less -->
    <nav class="navbar navbar-static-top" role="navigation">
        <!-- Sidebar toggle button-->
        <a href="#" class="sidebar-toggle" data-toggle="offcanvas" role="button">
            <span class="sr-only">Toggle navigation</span>
            <span class="icon-bar"></span>
            <span class="icon-bar"></span>
            <span class="icon-bar"></span>
        </a>
        <!--        <div class="navbar-custom-menu">
                    <ul class="nav navbar-nav">
                         User Account: style can be found in dropdown.less 
                        <li class="user user-menu">
                            <a href="#" class=""
                               aria-expanded="false">
                                <span class="hidden-xs">${user.email}</span>
                            </a>
                        </li>
                    </ul>
                </div>-->
        <div class="navbar-nav py-0 admin-acc">
            <a href="user_home" class="nav-item nav-link">
                <i class="fa-solid fa-lock"></i>
                Trang người dùng
            </a>
            <a href="user_profile" class="nav-item nav-link">
                <i class="fa-solid fa-user"></i>
                ${user.email}
            </a>
            <a href="user_sign_out" class="nav-item nav-link">
                <i class="fa-solid fa-right-from-bracket"></i>
                Ðăng Xuất
            </a>
        </div>
    </nav>
</header>
<!-- End Header -->
