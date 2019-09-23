/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
(function ($) {
    $.NotifierLongPolling = (function () {
        return {
            onMessage: function (data) {
                setTimeout($.NotifierLongPolling.send, 5000);
            },
            send: function () {
                $.ajax({
                    'url': APP_PATH + "/count_new_notifications",
                    'type': 'POST',
                    'dataType': 'json',
                    success: function (data) {
                        if (data.status) {
                            if (data.sum > 0) {
                                $('.badge-notif').text(data.sum);
                                $('.badge-notif').show();
                            } else {
                                $('.badge-notif').hide();
                            }
                        }
                        //setTimeout($.NotifierLongPolling.send, 3000);
                    }
                });
            }
        }
    }());

    // Document is ready
    $(document).bind('ready.app', function () {
        setTimeout($.NotifierLongPolling.send, 500);
    });

})(jQuery);
$("#Notifikasi").on("click", function () {
    if ($("#Notifikasi").attr("aria-expanded") == 'false') {
        cekSession(function (dataResult) {
            if (dataResult.status == false) {
                alert("Your Session is Expired, You'll Redirect to Login Page");
                $(location).attr("href", APP_PATH + "/login");
            }
        });
        $.ajax({
            'url': APP_PATH + "/fetch_notifications/1",
            'type': 'GET',
            beforeSend: function (xhr) {
            },
            success: function (data) {
                if ($("#Notifikasi").attr("aria-expanded") == 'true') {
                    setTimeout($.NotifierLongPolling.send, 500);
//                    $('#notification-list').html('<div class="next"><a id="next-notifications" href="' + APP_PATH + '/fetch_notifications/1">Load more</a></div>');
//                    $("#notification-list").jscroll({
//                        loadingHtml: '<center><img src="' + APP_PATH + '/resources/custom/img/loader/default.gif" alt="Loading" /></center>',
//                        autoTrigger: false,
//                        nextSelector: "#next-notifications",
//                        refresh: true
//                    });
////                    $("#next-notifications").click();
                    $('#notification-list').html(data);
                    $("#notification-list").jscroll({
                        loadingHtml: '<center><img src="' + APP_PATH + '/resources/custom/img/loader/default.gif" alt="Loading" /></center>',
                        autoTrigger: true,
                        nextSelector: "#next-notifications",
                        refresh: true
                    });

                }
            },
            complete: function () {
                if ($("#Notifikasi").attr("aria-expanded") == 'true') {
                    $.ajax({
                        'url': APP_PATH + "/update_read_new_notifications",
                        'type': 'POST',
                        'dataType': 'json'
                    });
                }
            }
        });
    }
});

$("#clear-all-notifications").on("click", function () {
//    $("#Notifikasi").attr("aria-expanded").val('true');
    cekSession(function (dataResult) {
        if (dataResult.status == false) {
            alert("Your Session is Expired, You'll Redirect to Login Page");
            $(location).attr("href", APP_PATH + "/login");
        }
    });
    $.ajax({
        'url': APP_PATH + "/clear_all_notifications",
        'type': 'POST',
        'dataType': 'json'
    });

});