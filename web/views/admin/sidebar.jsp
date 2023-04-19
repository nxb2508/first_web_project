<%-- 
    Document   : header
    Created on : Mar 18, 2023, 10:47:18 PM
    Author     : Bach
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!--Sidebar-->
<aside class="main-sidebar">
    <!-- sidebar: style can be found in sidebar.less -->
    <section class="sidebar" style="height: auto;">
        <!-- Sidebar user panel -->
        <!-- search form -->

        <!-- /.search form -->
        <!-- sidebar menu: : style can be found in sidebar.less -->
        <div class="sidebar-menu">
            <a href="<c:url value='/list_category'/>">Quản
            lý loại sản phẩm</a>
        </div>
        <div class="sidebar-menu">
            <a href="<c:url value='/list_product'/>">Quản lý sản phẩm</a>
        </div>
        <div class="sidebar-menu">
            <a href="<c:url value='/list_galery'/>">Quản lý hình ảnh sản phẩm</a>
        </div>
        <div class="sidebar-menu">
            <a href="<c:url value='/admin-list-size'/>">Quản lý kích thước sản phẩm</a>
        </div>
        <div class="sidebar-menu">
            <a href="<c:url value='/admin-list-inventory'/>">Quản lý sản phẩm trong kho</a>
        </div>
    </section>
    <!-- /.sidebar -->
</aside>
<!--End Sidebar-->
