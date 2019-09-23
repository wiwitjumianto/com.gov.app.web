<!DOCTYPE html>
<html lang="en-us" id="extr-page" style="background:rgba(244,246,249,1) !important;">
    <head>
        <script>
            window.APP_PATH = "${pageContext.request.contextPath}";
        </script>
        <!--[if lt IE 9 ]>
      <script>
      window.location = APP_PATH+"/unsupportedBrowser";
        
      </script>
      <![endif]-->
        <meta charset="utf-8">
        <title>Program Demo</title>
        <meta name="description" content="">
        <meta name="author" content="">
        <meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=no">

        <!-- #CSS Links -->
        <!-- Basic Styles -->
        <link rel="stylesheet" type="text/css" media="screen" href="${pageContext.request.contextPath}/resources/assets/css/bootstrap.min.css">
        <link rel="stylesheet" type="text/css" media="screen" href="${pageContext.request.contextPath}/resources/assets/css/font-awesome.min.css">
        <link rel="stylesheet" type="text/css" media="screen" href="${pageContext.request.contextPath}/resources/custom/css/core.css">

        <!-- SmartAdmin Styles : Caution! DO NOT change the order -->
        <link rel="stylesheet" type="text/css" media="screen" href="${pageContext.request.contextPath}/resources/assets/css/smartadmin-production-plugins.min.css">
        <link rel="stylesheet" type="text/css" media="screen" href="${pageContext.request.contextPath}/resources/assets/css/smartadmin-production.min.css">
        <link rel="stylesheet" type="text/css" media="screen" href="${pageContext.request.contextPath}/resources/assets/css/smartadmin-skins.min.css">

        <!-- SmartAdmin RTL Support -->
        <link rel="stylesheet" type="text/css" media="screen" href="${pageContext.request.contextPath}/resources/assets/css/smartadmin-rtl.min.css"> 


        <!-- We recommend you use "your_style.css" to override SmartAdmin
             specific styles this will also ensure you retrain your customization with each SmartAdmin update.
        <link rel="stylesheet" type="text/css" media="screen" href="${pageContext.request.contextPath}/resources/assets/css/your_style.css"> -->

        <!-- #FAVICONS -->
        <link rel="shortcut icon" href="${pageContext.request.contextPath}/resources/assets/img/favicon/favicon.ico" type="image/x-icon">
        <link rel="icon" href="${pageContext.request.contextPath}/resources/assets/img/favicon/favicon.ico" type="image/x-icon">

        <!-- #GOOGLE FONT -->
        <!--<link rel="stylesheet" href="${pageContext.request.contextPath}/resources/assets/http://fonts.googleapis.com/css?family=Open+Sans:400italic,700italic,300,400,700">-->
        <link rel="stylesheet" type="text/css" media="screen" href="${pageContext.request.contextPath}/resources/assets/css/font.css"> 
        <!--		 #APP SCREEN / ICONS 
                         Specifying a Webpage Icon for Web Clip 
                                 Ref: https://developer.apple.com/library/ios/documentation/AppleApplications/Reference/SafariWebContent/ConfiguringWebApplications/ConfiguringWebApplications.html 
                        <link rel="apple-touch-icon" href="${pageContext.request.contextPath}/resources/assets/img/splash/sptouch-icon-iphone.png">
                        <link rel="apple-touch-icon" sizes="76x76" href="${pageContext.request.contextPath}/resources/assets/img/splash/touch-icon-ipad.png">
                        <link rel="apple-touch-icon" sizes="120x120" href="${pageContext.request.contextPath}/resources/assets/img/splash/touch-icon-iphone-retina.png">
                        <link rel="apple-touch-icon" sizes="152x152" href="${pageContext.request.contextPath}/resources/assets/img/splash/touch-icon-ipad-retina.png">
                        
                         iOS web-app metas : hides Safari UI Components and Changes Status Bar Appearance 
                        <meta name="apple-mobile-web-app-capable" content="yes">
                        <meta name="apple-mobile-web-app-status-bar-style" content="black">
                        
                         Startup image for web apps 
                        <link rel="apple-touch-startup-image" href="${pageContext.request.contextPath}/resources/assets/img/splash/ipad-landscape.png" media="screen and (min-device-width: 481px) and (max-device-width: 1024px) and (orientation:landscape)">
                        <link rel="apple-touch-startup-image" href="${pageContext.request.contextPath}/resources/assets/img/splash/ipad-portrait.png" media="screen and (min-device-width: 481px) and (max-device-width: 1024px) and (orientation:portrait)">
                        <link rel="apple-touch-startup-image" href="${pageContext.request.contextPath}/resources/assets/img/splash/iphone.png" media="screen and (max-device-width: 320px)">-->


        <!-- #LOADER -->
        <!--<link rel="stylesheet" href="${pageContext.request.contextPath}/resources/assets/http://fonts.googleapis.com/css?family=Open+Sans:400italic,700italic,300,400,700">-->
        <link rel="stylesheet" type="text/css" media="screen" href="${pageContext.request.contextPath}/resources/custom/css/loader.css"> 
        <style>
            /*alert*/
            .SmallBox .foto {
                font-size: 50px;
                position: absolute;
                left: 20px;
                top: 10px;
            }
            .SmallBox {
                position: absolute;
                right: 5px;
                top: 20px;
                width: 420px;
                height: 100px !important;
                color: #fff;
                z-index: 9999;
                overflow: hidden;
                border: 1px solid transparent;
            }
            .smart-form .input input, .smart-form .select select, .smart-form .textarea textarea {
                display: block;
                box-sizing: border-box;
                -moz-box-sizing: border-box;
                width: 100%;
                height: 45px;
                line-height: 32px;
                padding: 15px 15px;
                outline: 0;
                border-width: 1px;
                border-style: solid;
                border-radius: 5px;
                background: rgba(255,255,255,0);
                font: 16px/26px 'Open Sans',Helvetica,Arial,sans-serif;
                color: #404040;
                appearance: normal;
                -moz-appearance: none;
                -webkit-appearance: none;
            }
            .smart-form .icon-append {
                right: 15px;
                padding-left: 10px;
                border-left-width: 1px;
                border-left-style: solid;
            }
            .smart-form .icon-append, .smart-form .icon-prepend {
                position: absolute;
                top: 15px;
                width: 22px;
                height: 22px;
                font-size: 16px;
                line-height: 22px;
                text-align: center;
            }
        </style>
    </head>

    <body class="animated fadeInDown" style="background:rgba(244,246,249,1) !important;">

        <div id="header" style="background:rgba(244,246,249,1) !important; border:none !important; margin-top:20px;">

            <!--*div id="logo-group">
                <span id="" class="hidden-mobile hidden-xs pull-left"><img src="resources/assets/img/logo-o.png" alt="alpha.png" width="50px" style="margin-top:12px;"></span> 
            </div>
        -->
        </div>

        <div id="main" role="main" style="background:rgba(244,246,249,1) !important;">

            <!-- MAIN CONTENT -->
            <div id="content" class="container">
                <div class="row" >
                    <div class="col-lg-12 col-md-12 col-sm-12" >
                        <h1 style="text-align: center; font-weight: 400; margin-left: -50px;">Aplikasi Demo</h1>
                    </div>
                </div><br/><br/>
                <div class="row">
                    
                   
                    <div class="col-xs-12 col-sm-12 col-md-4 col-md-push-4 col-lg-4" style="margin-top: 80px;">
                        <!--<div class="col-xs-12 col-sm-12 col-md-10 col-lg-10" style="margin-top:-90px;"><center><b id="loginInfo" style="color:red;display: none;"></b></center></div>-->
                        <div class="col-lg-10">
                            <div class="no-padding " id="form-olvidado">
                                <form action="index.html" id="login-form" class="smart-form client-form" style="margin-top: -50px">
                                    <fieldset style="background:none;">
<!--                                        <section>
                                            <label class="label">${theme.companycodeTitle}</label>
                                            <label class="input"> <i class="icon-append fa fa-building"></i>
                                                <input placeholder="${theme.companycodeTitle}" required="required" id="company_id" type="text" size="20" name="company_id" value="" style="border-radius:10px;" autocomplete="off">
                                                <b class="tooltip tooltip-top-right"><i class="fa fa-building txt-color-teal"></i>Masukan ${theme.companycodeTitle}</b></label>
                                        </section>-->
                                        <section>
                                            <label class="label">Username</label>
                                            <label class="input"> <i class="icon-append fa fa-user"></i>
                                                <input placeholder="Username" required="required" id="username" type="text" name="username" size="20" value="" autocomplete="off" style="text-transform:uppercase" maxlength="10">
                                                <b class="tooltip tooltip-top-right"><i class="fa fa-user txt-color-teal"></i> Masukan ${theme.usernameTitle}</b></label>
                                        </section>

                                        <section>
                                            <label class="label">Password</label>
                                            <label class="input"> <i class="icon-append fa fa-lock"></i>
                                                <input placeholder="Password" required="required" id="password" type="password" name="password" size="50" style="text-transform:uppercase" value="">
                                                <b class="tooltip tooltip-top-right"><i class="fa fa-lock txt-color-teal"></i> Masukan ${theme.passwordTitle}</b> </label>

                                        </section>
                                        <!--background-color: #f8991e; border-color: #f8991e;-->
                                        <br/>
                                        <button id="btnSubmit" type="button" class="btn btn-primary pull-right" style=" border-radius: 5px; padding:10px 20px; margin-bottom: 10px; width: 100%">
                                            <img style="display: none;" class="mini-loader"  src="${pageContext.request.contextPath}/resources/custom/img/loader/reload.gif"/>
                                            Masuk
                                        </button>
                                    </fieldset>
                                </form>
                            </div>
                        </div>
                    </div>

                </div>
            </div>

        </div>

        <%--<div  id="footer" style="background:rgba(244,246,249,1) !important;">--%>
            <%--<div id="logo-group">--%>
                <%--<span id="" class="hidden-mobile hidden-xs pull-left "><img src="resources/assets/img/LogoPU.png" alt="alpha.png" width="200px" style="margin-top:0px; margin-left:560px; "></span> --%>
            <%--</div>--%>
        <%--</div>--%>

        <!--================================================== -->	

        <!-- Link to Google CDN's jQuery + jQueryUI; fall back to local -->
        <script src="${pageContext.request.contextPath}/resources/assets/js/libs/jquery.min.js"></script>
        <script> if (!window.jQuery) {
                document.write('<script src="${pageContext.request.contextPath}/resources/assets/js/libs/jquery-2.1.1.min.js"><\/script>');}</script>

        <script src="http://ajax.googleapis.com/ajax/libs/jqueryui/1.10.3/jquery-ui.min.js"></script><!--
            <script> if (!window.jQuery.ui) { document.write('<script src="${pageContext.request.contextPath}/resources/js/libs/jquery-ui-1.10.3.min.js"><\/script>');} </script>-->


        <!-- JS TOUCH : include this plugin for mobile drag / drop touch events 		
        <script src="${pageContext.request.contextPath}/resources/assets/js/plugin/jquery-touch/jquery.ui.touch-punch.min.js"></script> -->

        <!-- BOOTSTRAP JS -->		
        <script src="${pageContext.request.contextPath}/resources/assets/js/bootstrap/bootstrap.min.js"></script>


        <!-- CUSTOM APP JS FILE -->
<!--        <script src="${pageContext.request.contextPath}/resources/custom/js/portal/login.js"></script>-->
        <script src="${pageContext.request.contextPath}/resources/custom/js/portal/common.js"></script>
        <!-- CUSTOM NOTIFICATION -->
        <script src="${pageContext.request.contextPath}/resources/assets/js/notification/SmartNotification.min.js"></script>
        <script >
            $(document).ready(function () {
                $('#btnSubmit').click(function () {
                    Login();
                });

                $('input#username').keyup(function (e) {
                    if (e.keyCode === 13)
                    {
                        Login();
                    }
                });

                $('input#password').keyup(function (e) {
                    if (e.keyCode === 13)
                    {

                        Login();
                    }
                });

                $('input#company_id').keyup(function (e) {
                    if (e.keyCode === 13)
                    {

                        Login();
                    }
                });
                $('#olvidado').click(function (e) {
                    e.preventDefault();
                    $('div#form-olvidado1').hide(true);
                    $('div#form-olvidado').toggle('500');
                });

                $('#acceso').click(function (e) {
                    e.preventDefault();
                    $('div#form-olvidado').toggle('500');
                });
            });
        </script>
        <script>

            function Login() {
                var username = $("input#username").val();
                var password = $("input#password").val();

                if (username == "") {
                    $("input#username").focus();
                    return;
                }
                if (password == "") {
                    $("input#password").focus();
                    return;
                }
                var data = {"username": username, "password": password};
                $("loginInfo").hide();
                $('#btnSubmit').attr('disabled', true);
                $(".mini-loader").show();
                $.ajax({
                    type: "POST",
                    url: APP_PATH + '/login',
                    data: JSON.stringify(data),
                    contentType: 'application/json',
                    cache: false,
                    success: function (hasil) {

                        if (hasil.status) {
                            window.location.href = APP_PATH + "/home";
                        } else {
                            $.smallBox({
                                title: "Login Gagal",
                                content: "Mohon Periksa Kembali UserName/Password",
                                color: "rgba(255,87,34,1)",
                                timeout: 6000,
                                icon: "fa fa-times shake animated"
                            });
                            $(".mini-loader").hide();
                            $('#btnSubmit').removeAttr('disabled');
                        }
                    },
                    error: function () {
                        $.smallBox({
                            title: "Login Gagal",
                            content: "<i class='fa fa-clock-o'></i> Mohon Periksa Kembali Npp/Kata Sandi Anda",
                            color: "#C46A69",
                            iconSmall: "fa fa-times fa-2x fadeInRight animated",
                            timeout: 6000
                        });
                        $(".mini-loader").hide();
                        $('#btnSubmit').removeAttr('disabled');
                    },
                    complete: function () {

                    }
                });
            }
        </script>

    </body>

</html>
