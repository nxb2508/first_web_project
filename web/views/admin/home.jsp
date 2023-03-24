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
        <!--<link href="<c:url value='/template/admin/bootstrap/css/bootstrap.min.css' />" rel="stylesheet" type="text/css" />-->
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
                <!--                 Content Header (Page header) 
                                <section class="content-header">
                                    <h1>
                                        Data Tables
                                        <small>advanced tables</small>
                                    </h1>
                                    <ol class="breadcrumb">
                                        <li><a href="#"><i class="fa fa-dashboard"></i> Home</a></li>
                                        <li><a href="#">Tables</a></li>
                                        <li class="active">Data tables</li>
                                    </ol>
                                </section>
                
                                 Main content 
                                <section class="content">
                                    <div class="row">
                                        <div class="col-xs-12">
                                             /.box 
                
                                            <div class="box">
                                                <div class="box-header">
                                                    <h3 class="box-title">Data Table With Full Features</h3>
                                                </div> /.box-header 
                                                <div class="box-body">
                                                    <div id="example1_wrapper" class="dataTables_wrapper form-inline" role="grid"><div class="row"><div class="col-xs-6"><div id="example1_length" class="dataTables_length"><label><select size="1" name="example1_length" aria-controls="example1"><option value="10" selected="selected">10</option><option value="25">25</option><option value="50">50</option><option value="100">100</option></select> records per page</label></div></div><div class="col-xs-6"><div class="dataTables_filter" id="example1_filter"><label>Search: <input type="text" aria-controls="example1"></label></div></div></div><table id="example1" class="table table-bordered table-striped dataTable" aria-describedby="example1_info">
                                                            <thead>
                                                                <tr role="row"><th class="sorting_asc" role="columnheader" tabindex="0" aria-controls="example1" rowspan="1" colspan="1" aria-sort="ascending" aria-label="Rendering engine: activate to sort column descending" style="width: 165px;">Rendering engine</th><th class="sorting" role="columnheader" tabindex="0" aria-controls="example1" rowspan="1" colspan="1" aria-label="Browser: activate to sort column ascending" style="width: 238px;">Browser</th><th class="sorting" role="columnheader" tabindex="0" aria-controls="example1" rowspan="1" colspan="1" aria-label="Platform(s): activate to sort column ascending" style="width: 213px;">Platform(s)</th><th class="sorting" role="columnheader" tabindex="0" aria-controls="example1" rowspan="1" colspan="1" aria-label="Engine version: activate to sort column ascending" style="width: 139px;">Engine version</th><th class="sorting" role="columnheader" tabindex="0" aria-controls="example1" rowspan="1" colspan="1" aria-label="CSS grade: activate to sort column ascending" style="width: 96px;">CSS grade</th></tr>
                                                            </thead>
                
                                                            <tfoot>
                                                                <tr><th rowspan="1" colspan="1">Rendering engine</th><th rowspan="1" colspan="1">Browser</th><th rowspan="1" colspan="1">Platform(s)</th><th rowspan="1" colspan="1">Engine version</th><th rowspan="1" colspan="1">CSS grade</th></tr>
                                                            </tfoot>
                                                            <tbody role="alert" aria-live="polite" aria-relevant="all"><tr class="odd">
                                                                    <td class="  sorting_1">Gecko</td>
                                                                    <td class=" ">Firefox 1.0</td>
                                                                    <td class=" ">Win 98+ / OSX.2+</td>
                                                                    <td class=" ">1.7</td>
                                                                    <td class=" ">A</td>
                                                                </tr><tr class="even">
                                                                    <td class="  sorting_1">Gecko</td>
                                                                    <td class=" ">Firefox 1.5</td>
                                                                    <td class=" ">Win 98+ / OSX.2+</td>
                                                                    <td class=" ">1.8</td>
                                                                    <td class=" ">A</td>
                                                                </tr><tr class="odd">
                                                                    <td class="  sorting_1">Gecko</td>
                                                                    <td class=" ">Firefox 2.0</td>
                                                                    <td class=" ">Win 98+ / OSX.2+</td>
                                                                    <td class=" ">1.8</td>
                                                                    <td class=" ">A</td>
                                                                </tr><tr class="even">
                                                                    <td class="  sorting_1">Gecko</td>
                                                                    <td class=" ">Firefox 3.0</td>
                                                                    <td class=" ">Win 2k+ / OSX.3+</td>
                                                                    <td class=" ">1.9</td>
                                                                    <td class=" ">A</td>
                                                                </tr><tr class="odd">
                                                                    <td class="  sorting_1">Gecko</td>
                                                                    <td class=" ">Camino 1.0</td>
                                                                    <td class=" ">OSX.2+</td>
                                                                    <td class=" ">1.8</td>
                                                                    <td class=" ">A</td>
                                                                </tr><tr class="even">
                                                                    <td class="  sorting_1">Gecko</td>
                                                                    <td class=" ">Camino 1.5</td>
                                                                    <td class=" ">OSX.3+</td>
                                                                    <td class=" ">1.8</td>
                                                                    <td class=" ">A</td>
                                                                </tr><tr class="odd">
                                                                    <td class="  sorting_1">Gecko</td>
                                                                    <td class=" ">Netscape 7.2</td>
                                                                    <td class=" ">Win 95+ / Mac OS 8.6-9.2</td>
                                                                    <td class=" ">1.7</td>
                                                                    <td class=" ">A</td>
                                                                </tr><tr class="even">
                                                                    <td class="  sorting_1">Gecko</td>
                                                                    <td class=" ">Netscape Browser 8</td>
                                                                    <td class=" ">Win 98SE+</td>
                                                                    <td class=" ">1.7</td>
                                                                    <td class=" ">A</td>
                                                                </tr><tr class="odd">
                                                                    <td class="  sorting_1">Gecko</td>
                                                                    <td class=" ">Netscape Navigator 9</td>
                                                                    <td class=" ">Win 98+ / OSX.2+</td>
                                                                    <td class=" ">1.8</td>
                                                                    <td class=" ">A</td>
                                                                </tr><tr class="even">
                                                                    <td class="  sorting_1">Gecko</td>
                                                                    <td class=" ">Mozilla 1.0</td>
                                                                    <td class=" ">Win 95+ / OSX.1+</td>
                                                                    <td class=" ">1</td>
                                                                    <td class=" ">A</td>
                                                                </tr></tbody></table><div class="row"><div class="col-xs-6"><div class="dataTables_info" id="example1_info">Showing 1 to 10 of 57 entries</div></div><div class="col-xs-6"><div class="dataTables_paginate paging_bootstrap"><ul class="pagination"><li class="prev disabled"><a href="#">← Previous</a></li><li class="active"><a href="#">1</a></li><li><a href="#">2</a></li><li><a href="#">3</a></li><li><a href="#">4</a></li><li><a href="#">5</a></li><li class="next"><a href="#">Next → </a></li></ul></div></div></div></div>
                                                </div> /.box-body 
                                            </div> /.box 
                                        </div> /.col 
                                    </div> /.row 
                                </section> /.content -->
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
    </body>
</html>
