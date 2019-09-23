$(document).ready(function () {
    
      $('input#username').keyup(function(e){
        if(e.keyCode === 13)
        {
            Login();
        }
    });

   $('input#password').keyup(function(e){
        if(e.keyCode === 13)
        {

            Login();
        }
    });
    
   $('input#company_id').keyup(function(e){
        if(e.keyCode === 13)
        {

            Login();
        }
    });
    

    $('#btnSubmit').click(function () {
        Login();
    });
});

function Login(){
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
        $('#btnSubmit').attr('disabled',true);
        $(".mini-loader").show();
        $.ajax({

            type: "POST",
            url: APP_PATH + '/login',
            data: JSON.stringify(data),
            contentType: 'application/json',
            cache: false,
            success: function (hasil) {

                if (hasil.status) {

                    window.location.href = APP_PATH + "/app/homeDashboard";
//                    window.location.href = APP_PATH + "/app/homeDashboard";

                } else {
        
      
//                        $("#loginInfo").html("Login Failed, System Unavailabe at This Moment");
//                        $("#loginInfo").show();
//                        $(".mini-loader").hide();
//                        showSmallError("Information", "Delete Failed", 5000);
                        $.smallBox({
                            title: "Login Gagal",
                            content: "Mohon Periksa Kembali Npp/Kata Sandi Anda Aguuunngg",
                            color: "rgba(255,87,34,1)",
                            timeout: 6000,
                            icon: "fa fa-times shake animated"
                        });
//                        $("#loginInfo").html("");
//                        $("#loginInfo").show();
                        $(".mini-loader").hide();
                        $('#btnSubmit').removeAttr('disabled');
                    

                }
            },
            error: function () {
                $.smallBox({
                            title: "Login Gagal",
                            content: "<i class='fa fa-clock-o'></i> Mohon Periksa Kembali Npp/Kata Sandi Anda allalal",
                            color: "#C46A69",
                            iconSmall: "fa fa-times fa-2x fadeInRight animated",
                            timeout: 6000
                        });
//                $("#loginInfo").html("Login Gagal, System Belum Dapat Diakses");
//                $("#loginInfo").show();
                $(".mini-loader").hide();
                $('#btnSubmit').removeAttr('disabled');
            },
            complete: function () {

            }
        });
}