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
        <ul class="sidebar-menu">
            <li class="treeview">
                <a href="#">
                    <i class="fa fa-pie-chart"></i>
                    <span>Quản lý</span>
                    <i class="fa fa-angle-left pull-right"></i>
                </a>
                <ul class="treeview-menu" style="display: none;">
                    <li><a href="<c:url value='/list_category'/>"><i class="fa fa-circle-o"></i>Quản
                            lý loại sản phẩm</a></li>
                    <li><a href="<c:url value='/list_product'/>"><i class="fa fa-circle-o"></i>Quản lý sản phẩm</a></li>
                    <li><a href="<c:url value='/list_galery'/>"><i class="fa fa-circle-o"></i>Quản lý hình ảnh sản phẩm</a></li>
                </ul>
            </li>
            <li class="treeview">
                <a href="#">
                    <i class="fa fa-edit"></i> <span>Forms</span>
                    <i class="fa fa-angle-left pull-right"></i>
                </a>
                <ul class="treeview-menu">
                    <li><a href="../../template/admin/pages/forms/general.html"><i
                                class="fa fa-circle-o"></i> General Elements</a></li>
                    <li><a href="../../template/admin/pages/forms/advanced.html"><i
                                class="fa fa-circle-o"></i> Advanced Elements</a></li>
                    <li><a href="../../template/admin/pages/forms/editors.html"><i
                                class="fa fa-circle-o"></i> Editors</a></li>
                </ul>
            </li>
            <li class="treeview">
                <a href="#">
                    <i class="fa fa-table"></i> <span>Tables</span>
                    <i class="fa fa-angle-left pull-right"></i>
                </a>
                <ul class="treeview-menu" style="display: none;">
                    <li><a href="../../template/admin/pages/tables/simple.html"><i
                                class="fa fa-circle-o"></i> Simple tables</a></li>
                    <li class=""><a href="../../template/admin/pages/tables/data.html"><i
                                class="fa fa-circle-o"></i> Data tables</a></li>
                </ul>
            </li>
        </ul>
    </section>
    <!-- /.sidebar -->
</aside>
<!--End Sidebar-->
