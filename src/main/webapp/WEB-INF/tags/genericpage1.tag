<%-- 
    Document   : generictag
    agungarahman21@gmail.com
--%>

<%@tag description="Overall Page template" pageEncoding="UTF-8"%>
<%@attribute name="custom_css" fragment="true" %>
<%@attribute name="custom_js" fragment="true" %>
<%@attribute name="menu" fragment="true" %>
<%@attribute name="breadcrumb" fragment="true" %>
<%@attribute name="pagetitle" fragment="true" %>
<%@attribute name="bodycontent" fragment="true" %>
<%@attribute name="outsidecontent" fragment="true" %>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix = "fmt" uri = "http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html lang="en-us">
    <head>

        <script>
            <jsp:useBean id="now" class="java.util.Date"/>
            window.URL = "${pageContext.request.getAttribute("javax.servlet.forward.request_uri")}";
            window.APP_PATH = "${pageContext.request.contextPath}";
            window.USR = "${pageContext.session.getAttribute("userid")}";
            window.NOW = "<fmt:setLocale value="en_US" scope="session"/><fmt:formatDate value="${now}" pattern="MMMM dd, YYYY HH:mm:ss" timeZone="Asia/Jakarta" />";
            window.TIMEZONE = "<fmt:formatDate value="${now}" pattern="z" timeZone="Asia/Jakarta" />";
        </script>
        <meta charset="utf-8">
        <meta name="_csrf" content="${_csrf.token}">
        <!-- default header name is X-CSRF-TOKEN -->
        <meta name="_csrf_header" content="${_csrf.headerName}">
        <!--<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">-->

        <title> <jsp:invoke fragment="pagetitle"/> </title>
        <meta name="description" content="">
        <meta name="author" content="">

        <meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=no">


        <!-- Basic Styles -->
        <link rel="stylesheet" type="text/css" media="screen" href="${pageContext.request.contextPath}/resources/assets/css/bootstrap.min.css">
        <link rel="stylesheet" type="text/css" media="screen" href="${pageContext.request.contextPath}/resources/assets/css/font-awesome2.min.css">
        <link rel="stylesheet" type="text/css" media="screen" href="${pageContext.request.contextPath}/resources/assets/css/font-awesome.min.css">
        <!--<link rel="stylesheet" type="text/css" media="screen" href="${pageContext.request.contextPath}/resources/assets/css/bootstrap-datepicker3.min.css">-->

        <!-- SmartAdmin Styles : Caution! DO NOT change the order -->
        <link rel="stylesheet" type="text/css" media="screen" href="${pageContext.request.contextPath}/resources/assets/css/smartadmin-production-plugins.min.css">
        <link rel="stylesheet" type="text/css" media="screen" href="${pageContext.request.contextPath}/resources/assets/css/smartadmin-production.min.css">
        <link rel="stylesheet" type="text/css" media="screen" href="${pageContext.request.contextPath}/resources/assets/css/smartadmin-skins.min.css">
        <link rel="stylesheet" type="text/css" media="screen" href="${pageContext.request.contextPath}/resources/custom/css/hcms.css">
        <link rel="stylesheet" type="text/css" media="screen" href="${pageContext.request.contextPath}/resources/custom/css/core.css">
        <link rel="stylesheet" type="text/css" media="screen" href="${pageContext.request.contextPath}/resources/custom/css/icon.css">
        <link rel="stylesheet" type="text/css" media="screen" href="${pageContext.request.contextPath}/resources/custom/css/font/font.css">
        <link rel="stylesheet" type="text/css" media="screen" href="${pageContext.request.contextPath}/resources/custom/css/loader.css">
        <link rel="stylesheet" type="text/css" media="screen" href="${pageContext.request.contextPath}/resources/custom/css/color.css">
        <link rel="stylesheet" type="text/css" media="screen" href="${pageContext.request.contextPath}/resources/custom/css/pe-icon-7-stroke/css/pe-icon-7-stroke.css">
        <link rel="stylesheet" type="text/css" media="screen" href="${pageContext.request.contextPath}/resources/custom/css/bootstrap_calendar.css">
        <link rel="stylesheet" type="text/css" media="screen" href="${pageContext.request.contextPath}/resources/custom/css/bootstrap-datepicker3.css">
        <jsp:invoke fragment="custom_css"/>
        <!-- SmartAdmin RTL Support  -->
        <link rel="stylesheet" type="text/css" media="screen" href="${pageContext.request.contextPath}/resources/assets/css/smartadmin-rtl.min.css">

        <!-- We recommend you use "your_style.css" to override SmartAdmin
             specific styles this will also ensure you retrain your customization with each SmartAdmin update.
        <link rel="stylesheet" type="text/css" media="screen" href="${pageContext.request.contextPath}/resources/assets/css/your_style.css"> -->

        <!-- Demo purpose only: goes with demo.js, you can delete this css when designing your own WebApp -->
        <!--<link rel="stylesheet" type="text/css" media="screen" href="${pageContext.request.contextPath}/resources/assets/css/demo.min.css">-->

        <!-- FAVICONS -->
        <link rel="shortcut icon" href="${pageContext.request.contextPath}/resources/assets/img/favicon/favicon.ico" type="image/x-icon">
        <link rel="icon" href="${pageContext.request.contextPath}/resources/assets/img/favicon/favicon.ico" type="image/x-icon">

        <!-- GOOGLE FONT -->
        <!--<link rel="stylesheet" href="http://fonts.googleapis.com/css?family=Open+Sans:400italic,700italic,300,400,700">-->
        <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/assets/css/google.font.css">

        <!-- Specifying a Webpage Icon for Web Clip
                 Ref: https://developer.apple.com/library/ios/documentation/AppleApplications/Reference/SafariWebContent/ConfiguringWebApplications/ConfiguringWebApplications.html -->
        <link rel="apple-touch-icon" href="${pageContext.request.contextPath}/resources/assets/img/splash/sptouch-icon-iphone.png">
        <link rel="apple-touch-icon" sizes="76x76" href="${pageContext.request.contextPath}/resources/assets/img/splash/touch-icon-ipad.png">
        <link rel="apple-touch-icon" sizes="120x120" href="${pageContext.request.contextPath}/resources/assets/img/splash/touch-icon-iphone-retina.png">
        <link rel="apple-touch-icon" sizes="152x152" href="${pageContext.request.contextPath}/resources/assets/img/splash/touch-icon-ipad-retina.png">

        <!-- iOS web-app metas : hides Safari UI Components and Changes Status Bar Appearance -->
        <meta name="apple-mobile-web-app-capable" content="yes">
        <meta name="apple-mobile-web-app-status-bar-style" content="black">

        <!-- Startup image for web apps -->
        <link rel="apple-touch-startup-image" href="${pageContext.request.contextPath}/resources/assets/img/splash/ipad-landscape.png" media="screen and (min-device-width: 481px) and (max-device-width: 1024px) and (orientation:landscape)">
        <link rel="apple-touch-startup-image" href="${pageContext.request.contextPath}/resources/assets/img/splash/ipad-portrait.png" media="screen and (min-device-width: 481px) and (max-device-width: 1024px) and (orientation:portrait)">
        <link rel="apple-touch-startup-image" href="${pageContext.request.contextPath}/resources/assets/img/splash/iphone.png" media="screen and (max-device-width: 320px)">

        <!-- Custom design afif -->
        <link rel="stylesheet" type="text/css" media="screen" href="${pageContext.request.contextPath}/resources/custom/css/hcms.css">
        <link rel="stylesheet" type="text/css" media="screen" href="${pageContext.request.contextPath}/resources/custom/css/responsive.css">
        <style>
            @font-face{
                font-family: logoText;
                src: url(${pageContext.request.contextPath}/resources/assets/fonts/Return-of-the-Grid.otf);
            }
            .textLogo {
                font-family: 'logoText';
                float: left;
                font-size: 18px;
                margin: 17px 0 0 13px;
                font-style: italic;
                letter-spacing: 1px;
            }
            #header>:first-child, aside {
                width: 400px;
            }
        </style>
    </head>

    <!--

    TABLE OF CONTENTS.

    Use search to find needed section.

    ===================================================================

    |  01. #CSS Links                |  all CSS links and file paths  |
    |  02. #FAVICONS                 |  Favicon links and file paths  |
    |  03. #GOOGLE FONT              |  Google font link              |
    |  04. #APP SCREEN / ICONS       |  app icons, screen backdrops   |
    |  05. #BODY                     |  body tag                      |
    |  06. #HEADER                   |  header tag                    |
    |  07. #PROJECTS                 |  project lists                 |
    |  08. #TOGGLE LAYOUT BUTTONS    |  layout buttons and actions    |
    |  09. #MOBILE                   |  mobile view dropdown          |
    |  10. #SEARCH                   |  search field                  |
    |  11. #NAVIGATION               |  left panel & navigation       |
    |  12. #RIGHT PANEL              |  right panel userlist          |
    |  13. #MAIN PANEL               |  main panel                    |
    |  14. #MAIN CONTENT             |  content holder                |
    |  15. #PAGE FOOTER              |  page footer                   |
    |  16. #SHORTCUT AREA            |  dropdown shortcuts area       |
    |  17. #PLUGINS                  |  all scripts and plugins       |

    ===================================================================

    -->

    <!-- #BODY -->
    <!-- Possible Classes

            * 'smart-style-{SKIN#}'
            * 'smart-rtl'         - Switch theme mode to RTL
            * 'menu-on-top'       - Switch to top navigation (no DOM change required)
            * 'no-menu'			  - Hides the menu completely
            * 'hidden-menu'       - Hides the main menu but still accessable by hovering over left edge
            * 'fixed-header'      - Fixes the header
            * 'fixed-navigation'  - Fixes the main menu
            * 'fixed-ribbon'      - Fixes breadcrumb
            * 'fixed-page-footer' - Fixes footer
            * 'container'         - boxed layout mode (non-responsive: will not work with fixed-navigation & fixed-ribbon)
    -->
    <body class="smart-style-3 minified fixed-navigation fixed-header" style="background: transparent !important;">

        <!-- HEADER -->
        <header id="header">
            <div id="logo-group">

                <!--PLACE YOUR LOGO HERE--> 
                <div>                     
                    <img src="${pageContext.request.contextPath}/resources/custom/img/LogoPU.png" alt="Kementrian PU" style="height: 40px;margin-right: -20px;padding:10px; float: left;">  
                    <h2 class="textLogo text-blue-material" id="logo-module-name"></h2>
                </div>
               
            </div>

            <!-- projects dropdown -->
            <!-- end projects dropdown -->

            <!-- pulled right: nav area -->
            <div class="pull-right">

       
                <span class="pull-right" id="logo" style="margin-right:-50px;">
                   <img src="${pageContext.request.contextPath}/resources/custom/img/" alt="Logo perusahaan" style="height: 30px;margin-right: -50px;">  
                </span>

                <div id="logout" class="btn-header transparent pull-right">
                    <span> <a href="${pageContext.request.contextPath}/logout" title="Logout" style="cursor: pointer !important;"><i class="fa fa-sign-out"></i></a> </span>
                </div>

                <div id="fullscreen" class="btn-header transparent pull-right">
                    <span> <a href="javascript:void(0);" data-action="launchFullscreen" title="Full Screen" style="cursor: pointer !important;"><i class="fa fa-arrows-alt"></i></a> </span>
                </div>

                <div class="btn-header transparent pull-right" style="">
                    <div id="Notifikasi" class="btn-header transparent pull-right dropdown-toggle" data-toggle="dropdown" aria-expanded="false">
                        <span> <a href="javascript:void(0);" style="cursor: pointer !important;"><i class="fa fa-bell"></i></a> </span>
                        <span class="badge red-darken-2 pull-right inbox-badge margin-right-13 badge-notif" style="display:none">0</span>
                    </div>
                    <ul class="dropdown-menu" style="margin-right:20px; background:white !important; width:300px; height:auto;">
                        <div class="dropdown-content-header">
                            <span style="margin-top:5px;">Notifikasi</span>
                        </div>
                        <div id="notification-list"  class="notif-drop scrollable-menu ">
                        </div>
                        <div id="clear-all-notifications" class="dropdown-content-footer">
                            <a href="#"> <center style="margin-top:5px;">Bersihkan Notifikasi</center></a>
                        </div>
                    </ul>

                </div>

                <div class="project-context hidden-xs">
                    <div class="project-selector dropdown-toggle" data-toggle="dropdown">
                        <span class="" id="running-day"><fmt:setLocale value="id_ID" scope="session"/><fmt:formatDate value="${now}" pattern="EEEE, dd MMMM yyyy" timeZone="Asia/Jakarta"/></span>
                        <span class="" id="running-time"><fmt:formatDate value="${now}" pattern="HH:mm:ss z" timeZone="Asia/Jakarta"/></span>
                    </div>
                </div>
                <div class="project-context hidden-xs">
                  

                </div>

            </div>
            <!-- end pulled right: nav area -->

        </header>
        <!-- END HEADER -->

        <!-- Left panel : Navigation area -->
        <!-- Note: This width of the aside area can be adjusted through LESS variables -->
        <aside id="left-panel">

            <!-- User info -->
            <div class="login-info">
                <span>
                    <a href="javascript:void(0);" data-action="minifyMenu">
                        <!--<img onerror="this.src='${pageContext.request.contextPath}/resources/assets/img/avatars/male.png'" src="${pageContext.request.contextPath}/peo/selfService/getProfilePicture/${pageContext.session.getAttribute("empid")}" alt="me"/>-->
                        <c:if test="${not empty pageContext.session.getAttribute('personid')}">
                            <img style ="border-radius: 50%;" onerror="this.src='${pageContext.request.contextPath}/resources/assets/img/avatars/male.png'" src="${pageContext.request.contextPath}/peo/selfService/getProfilePicture/${pageContext.session.getAttribute("empid")}" alt="me"/>
                        </c:if>
                        <span>
                            <b>${pageContext.session.getAttribute("name")}</b>
                        </span>
                    </a>
                </span>
            </div>
            <!-- end user info -->

            <%--<jsp:invoke fragment="menu"/>--%>
            <!-- NAVIGATION : This navigation is also responsive-->
            <nav>
                <!--PRINT MENU SIDE BAR-->
                ${pageContext.session.getAttribute("menu")}
                <ul class="menulist">
                    <li id="#" style="display: block;">
                        <a href="${pageContext.request.contextPath}/app/congiguration" data-original-title="" title="">
                            <i class="fa fa-lg fa-fw font-Self-Sevice"></i> 
                            <span class="menu-item-parent">Konfigurasi Akun</span>
                            <b class="collapse-sign">
                                <em class="fa fa-minus-square-o"></em>
                            </b>
                        </a>
                    </li>
                    <li id="#" style="display: block;">
                        <a href="${pageContext.request.contextPath}" data-original-title="" title="">
                            <i class="fa fa-lg fa-fw font-Configuring1"></i> 
                            <span class="menu-item-parent">Konfigurasi Parameter</span>
                            <b class="collapse-sign">
                                <em class="fa fa-minus-square-o"></em>
                            </b>
                        </a>
                        <ul style="display: block;">
                            <li id="apphomeDashboard">
                                <a href="/app/homeDashboard" data-original-title="" title="">
                                    <i class=""></i> 
                                    <span class="menu-item-parent">Input Provinsi</span>
                                </a>
                            </li>
                            <li id="ecmdashboard">\
                                <a href="/ecm/dashboard" data-original-title="" title="">
                                    <i class="f"></i>
                                    <span class="menu-item-parent">Input Kabupaten</span>
                                </a>
                            </li>
                            <li id="jopdistinct_job_profile">
                                <a href="/jop/distinct_job_profile" data-original-title="" title="">
                                    <i class=""></i> 
                                    <span class="menu-item-parent">Input Kecamatan</span>
                                </a>
                            </li>
                            <li id="jopdistinct_job_profile">
                                <a href="/jop/distinct_job_profile" data-original-title="" title="">
                                    <i class=""></i> 
                                    <span class="menu-item-parent">Input Desa</span>
                                </a>
                            </li>
                        </ul>
                    </li>
                    <li id="#" style="display: block;">
                        <a href="#" data-original-title="" title="">
                            <i class="fa fa-lg fa-fw font-Application-Management"></i> 
                            <span class="menu-item-parent">Buat Program Bantuan</span>
                            <b class="collapse-sign">
                                <em class="fa fa-minus-square-o"></em>
                            </b>
                        </a>
                    </li>
                    <li id="#" style="display: block;">
                        <a href="#" data-original-title="" title="">
                            <i class="fa fa-lg fa-fw fa fa-building"></i> 
                            <span class="menu-item-parent">Input Realisasi</span>
                            <b class="collapse-sign">
                                <em class="fa fa-minus-square-o"></em>
                            </b>
                        </a>
                    </li>
                </ul>
            </nav>


            <span class="minifyme" data-action="minifyMenu"> 
                <i class="fa fa-angle-left hit"></i> 
            </span>

        </aside>
        <!-- END NAVIGATION -->

        <!-- MAIN PANEL -->
        <div id="main" role="main">

            <!-- RIBBON -->
            <div id="ribbon">

                <span class="ribbon-button-alignment"> 
                    <span id="refresh" class="btn btn-ribbon" data-action="resetWidgets" data-title="refresh"  rel="tooltip" data-placement="bottom" data-original-title="<i class='text-warning fa fa-warning'></i> Peringatan! Tombol ini akan merubah kepengaturan awal." data-html="true">
                        <i class="fa fa-refresh"></i>
                    </span> 
                </span>

                <!-- breadcrumb -->
                <ol class="breadcrumb">
                    <jsp:invoke fragment="breadcrumb"/>
                </ol>
               

            </div>
            <!-- END RIBBON -->

            <!-- MAIN CONTENT -->
            <div id="content">

                <%--<%@include file="/WEB-INF/view/portal/module_content.jsp" %>--%>
                <jsp:invoke fragment="bodycontent"/>
                <div id="modalNews"></div>

            </div>
            <!-- END MAIN CONTENT -->
            <jsp:invoke fragment="outsidecontent"/>
        </div>
        <!-- END MAIN PANEL -->

        <!-- PAGE FOOTER -->
        <div class="page-footer " style="">
            <div class="row" style="margin-top:-10px;">
                <div class="col-xs-12 col-sm-12 pull-right">
                    <center><span class="txt-color-white" >Integrated Human Capital System <br/> 
                            <!--Term of Service | Privacy Police | Send Feedback | Hak Cipta © 2016 PT. Bank Negara Indonesia, Tbk.-->
                        </span></center>
                </div>
            </div>
        </div>
        <!-- END PAGE FOOTER -->

        <!-- SHORTCUT AREA : With large tiles (activated via clicking user name tag)
        Note: These tiles are completely responsive,
        you can add as many as you like
        -->
        <div id="shortcut">
            <ul>
                <li>
                    <a href="inbox.html" class="jarvismetro-tile big-cubes bg-color-blue"> <span class="iconbox"> <i class="fa fa-envelope fa-4x"></i> <span>Mail <span class="label pull-right bg-color-darken">14</span></span> </span> </a>
                </li>
                <li>
                    <a href="calendar.html" class="jarvismetro-tile big-cubes bg-color-orangeDark"> <span class="iconbox"> <i class="fa fa-calendar fa-4x"></i> <span>Calendar</span> </span> </a>
                </li>
                <li>
                    <a href="gmap-xml.html" class="jarvismetro-tile big-cubes bg-color-purple"> <span class="iconbox"> <i class="fa fa-map-marker fa-4x"></i> <span>Maps</span> </span> </a>
                </li>
                <li>
                    <a href="invoice.html" class="jarvismetro-tile big-cubes bg-color-blueDark"> <span class="iconbox"> <i class="fa fa-book fa-4x"></i> <span>Invoice <span class="label pull-right bg-color-darken">99</span></span> </span> </a>
                </li>
                <li>
                    <a href="gallery.html" class="jarvismetro-tile big-cubes bg-color-greenLight"> <span class="iconbox"> <i class="fa fa-picture-o fa-4x"></i> <span>Gallery </span> </span> </a>
                </li>
                <li>
                    <a href="profile.html" class="jarvismetro-tile big-cubes selected bg-color-pinkDark"> <span class="iconbox"> <i class="fa fa-user fa-4x"></i> <span>My Profile </span> </span> </a>
                </li>
            </ul>
        </div>
        <!-- END SHORTCUT AREA -->

        <!--================================================== -->

        <!-- PACE LOADER - turn this on if you want ajax loading to show (caution: uses lots of memory on iDevices)-->
        <script data-pace-options='{ "restartOnRequestAfter": false }' src="${pageContext.request.contextPath}/resources/assets/js/plugin/pace/pace.min.js"></script>

        <!-- Link to Google CDN's jQuery + jQueryUI; fall back to local -->
        <!--<script src="http://ajax.googleapis.com/ajax/libs/jquery/2.1.1/jquery.min.js"></script>-->
        <script>
            if (!window.jQuery) {
                document.write('<script src="${pageContext.request.contextPath}/resources/assets/js/libs/jquery-2.1.1.min.js"><\/script>');
            }
        </script>

        <!--<script src="http://ajax.googleapis.com/ajax/libs/jqueryui/1.10.3/jquery-ui.min.js"></script>-->
        <script>
            if (!window.jQuery.ui) {
                document.write('<script src="${pageContext.request.contextPath}/resources/assets/js/libs/jquery-ui-1.10.3.min.js"><\/script>');
            }
        </script>

        <!-- IMPORTANT: APP CONFIG -->
        <script src="${pageContext.request.contextPath}/resources/assets/js/app.config.js"></script>

        <!-- JS TOUCH : include this plugin for mobile drag / drop touch events-->
        <script src="${pageContext.request.contextPath}/resources/assets/js/plugin/jquery-touch/jquery.ui.touch-punch.min.js"></script>

        <!-- BOOTSTRAP JS -->
        <script src="${pageContext.request.contextPath}/resources/assets/js/bootstrap/bootstrap.min.js"></script>

        <!--date picker custom-->
        <!--<script src="${pageContext.request.contextPath}/resources/assets/js/bootstrap-datepicker/bootstrap-datepicker.min.js"></script>-->

        <!-- CUSTOM NOTIFICATION -->
        <script src="${pageContext.request.contextPath}/resources/assets/js/notification/SmartNotification.min.js"></script>

        <!-- JARVIS WIDGETS -->
        <script src="${pageContext.request.contextPath}/resources/assets/js/smartwidgets/jarvis.widget.min.js"></script>

        <!-- EASY PIE CHARTS -->
        <script src="${pageContext.request.contextPath}/resources/assets/js/plugin/easy-pie-chart/jquery.easy-pie-chart.min.js"></script>

        <!-- SPARKLINES -->
        <script src="${pageContext.request.contextPath}/resources/assets/js/plugin/sparkline/jquery.sparkline.min.js"></script>

        <!-- JQUERY VALIDATE -->
        <script src="${pageContext.request.contextPath}/resources/assets/js/plugin/jquery-validate/jquery.validate.min.js"></script>

        <!-- JQUERY MASKED INPUT -->
        <script src="${pageContext.request.contextPath}/resources/assets/js/plugin/masked-input/jquery.maskedinput.min.js"></script>

        <!-- JQUERY SELECT2 INPUT -->
        <script src="${pageContext.request.contextPath}/resources/assets/js/plugin/select2/select2.min.js"></script>

        <!-- JQUERY UI + Bootstrap Slider -->
        <script src="${pageContext.request.contextPath}/resources/assets/js/plugin/bootstrap-slider/bootstrap-slider.min.js"></script>

<!--<script src="${pageContext.request.contextPath}/resources/assets/js/bootstrap/bootstrap.min.js"></script>-->

        <!-- browser msie issue fix -->
        <script src="${pageContext.request.contextPath}/resources/assets/js/plugin/msie-fix/jquery.mb.browser.min.js"></script>

        <!-- FastClick: For mobile devices -->
        <script src="${pageContext.request.contextPath}/resources/assets/js/plugin/fastclick/fastclick.min.js"></script>

        <!--[if IE 8]>

        <h1>Your browser is out of date, please update your browser by going to www.microsoft.com/download</h1>

        <![endif]-->

        <!-- Demo purpose only -->
        <!--<script src="${pageContext.request.contextPath}/resources/assets/js/demo.min.js"></script>-->

        <!-- MAIN APP JS FILE -->
        <script src="${pageContext.request.contextPath}/resources/assets/js/app.min.js"></script>


        <!-- ENHANCEMENT PLUGINS : NOT A REQUIREMENT -->
        <!-- Voice command : plugin -->
        <script src="${pageContext.request.contextPath}/resources/assets/js/speech/voicecommand.min.js"></script>

        <!-- SmartChat UI : plugin -->
        <script src="${pageContext.request.contextPath}/resources/assets/js/smart-chat-ui/smart.chat.ui.min.js"></script>
        <script src="${pageContext.request.contextPath}/resources/assets/js/smart-chat-ui/smart.chat.manager.min.js"></script>


        <!-- CUSTOM JS IHCS -->
        <script src="${pageContext.request.contextPath}/resources/custom/js/portal/common.js"></script>
        <script src="${pageContext.request.contextPath}/resources/custom/js/portal/push_notifications_polling.js"></script>
        <script src="${pageContext.request.contextPath}/resources/custom/js/portal/moment.js"></script>
        <script src="${pageContext.request.contextPath}/resources/custom/module/avm/approvalModel.js"></script>
        <script src="${pageContext.request.contextPath}/resources/custom/js/portal/logo-modules.js"></script>
        <script src="${pageContext.request.contextPath}/resources/custom/js/portal/running-time.js"></script>

        <!-- DATA TABLE PLUGIN(S) -->
        <script src="${pageContext.request.contextPath}/resources/assets/js/plugin/datatables/jquery.dataTables.min.js"></script>
        <script src="${pageContext.request.contextPath}/resources/assets/js/plugin/datatables/dataTables.colVis.min.js"></script>
        <script src="${pageContext.request.contextPath}/resources/assets/js/plugin/datatables/dataTables.tableTools.min.js"></script>
        <script src="${pageContext.request.contextPath}/resources/assets/js/plugin/datatables/dataTables.bootstrap.min.js"></script>
        <script src="${pageContext.request.contextPath}/resources/assets/js/plugin/datatable-responsive/datatables.responsive.min.js"></script>
        <script src="${pageContext.request.contextPath}/resources/custom/js/calendar/bootstrap_calendar.js"></script>
        <script type="text/javascript" src="${pageContext.request.contextPath}/resources/custom/js/jquery.jscroll.js"></script>

        <jsp:invoke fragment="custom_js"/>
       
        <div style="display: none;" class="loading">
            <div id="loader"></div>
        </div>
    </body>
</html>
