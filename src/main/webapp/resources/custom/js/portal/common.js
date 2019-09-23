Number.prototype.formatMoney = function (decPlaces, thouSeparator, decSeparator) {
    //CARA PAKE FUNGSI DIATAS
//var myMoney=3543.75873;
//var formattedMoney = '$' + myMoney.formatMoney(2,',','.'); // "$3,543.76"
    var n = this,
            decPlaces = isNaN(decPlaces = Math.abs(decPlaces)) ? 2 : decPlaces,
            decSeparator = decSeparator == undefined ? "." : decSeparator,
            thouSeparator = thouSeparator == undefined ? "," : thouSeparator,
            sign = n < 0 ? "-" : "",
            i = parseInt(n = Math.abs(+n || 0).toFixed(decPlaces)) + "",
            j = (j = i.length) > 3 ? j % 3 : 0;
    return sign + (j ? i.substr(0, j) + thouSeparator : "") + i.substr(j).replace(/(\d{3})(?=\d)/g, "$1" + thouSeparator) + (decPlaces ? decSeparator + Math.abs(n - i).toFixed(decPlaces).slice(2) : "");
};

$(document).ready(function () {



    $("body").on("keypress", ".onlyCharandspace", function (event) {

        var inputValue = event.which;

        if ((inputValue >= 94 && inputValue <= 95)) {
            event.preventDefault();
        }


        // allow letters and whitespaces only.
        if (!(inputValue >= 65 && inputValue <= 120) && inputValue !== 8 && (inputValue !== 32 && inputValue !== 0) && !(inputValue >= 97 && inputValue <= 122)) {
            event.preventDefault();
        }
    });


    $("body").on("keypress", ".onlyAlphaNumeric", function (event) {

        var inputValue = event.which;

        if ((inputValue >= 94 && inputValue <= 95)) {
            event.preventDefault();
        }


        // allow letters and whitespaces only.
        if (!(inputValue >= 65 && inputValue <= 90) && inputValue !== 8 && inputValue !== 127 && !(inputValue >= 97 && inputValue <= 122) && !(inputValue >= 48 && inputValue <= 57)) {
            event.preventDefault();
        }
    });



    //ONLY ALLOW NUMBER INPUT
    $("body").on("keydown", ".onlyNumber", function (e) {
//	alert(e.keyCode);



        if (e.keyCode == 188) {

            $(this).val($(this).val() + ".");
            e.preventDefault();

        }
        // Allow: backspace, delete, tab, escape, enter and .
        if ($.inArray(e.keyCode, [46, 8, 9, 27, 13, 110, 189, 190, 173, 109]) !== -1 ||
                // Allow: Ctrl+A, Command+A
                        (e.keyCode === 65 && (e.ctrlKey === true || e.metaKey === true)) ||
                        // Allow: home, end, left, right, down, up
                                (e.keyCode >= 35 && e.keyCode <= 40)) {
                    // let it happen, don't do anything
                    return;
                }
                // Ensure that it is a number and stop the keypress
                if ((e.shiftKey || (e.keyCode < 48 || e.keyCode > 57)) && (e.keyCode < 96 || e.keyCode > 105)) {
                    e.preventDefault();
                }
            });


    //ONLY ALLOW INTEGER INPUT
    $("body").on("keydown", ".onlyInteger", function (e) {

        // Allow: backspace, delete, tab, escape, enter and .
        if ($.inArray(e.keyCode, [46, 8, 9, 27, 13]) !== -1 ||
                // Allow: Ctrl+A, Command+A
                        (e.keyCode === 65 && (e.ctrlKey === true || e.metaKey === true)) ||
                        // Allow: home, end, left, right, down, up
                                (e.keyCode >= 35 && e.keyCode <= 40)) {
                    // let it happen, don't do anything
                    return;
                }
                // Ensure that it is a number and stop the keypress
                if ((e.shiftKey || (e.keyCode < 48 || e.keyCode > 57)) && (e.keyCode < 96 || e.keyCode > 105)) {
                    e.preventDefault();
                }
            });
    
    
     //ONLY ALLOW DECIMAL INPUT
    $("body").on("keydown", ".onlyDecimalNumber", function (e) {
        var charCode = (e.which) ? e.which : e.keyCode;
        if (charCode != 46 && charCode > 31 && (charCode < 48 || charCode > 57)){
           e.preventDefault();
        }
        return;
    });

    //ONLY ALLOW FREE TEXT    
    $(".freeText").keypress(function (event) {
        var inputValue = event.which;
        // allow letters and whitespaces only.

        if (!(inputValue >= 46 && inputValue <= 57) && !(inputValue >= 65 && inputValue <= 120) && (inputValue !== 94 && inputValue !== 32 && inputValue !== 0 && inputValue !== 07 && inputValue !== 08)) {
            event.preventDefault();
        }

    });


});
function ajaxBeforeSend(xhr) {
    cekSession();
}

function postData(json, api, callback) {
    alert(api);
    $.ajax({
        cache: false,
        type: "POST",
        url: api,
        data: JSON.stringify(json),
        contentType: 'application/json',
        beforeSend: function (xhr) {
//            ajaxBeforeSend(xhr);
            $(".loading").show();
        },
        success: function (data) {
            $(".loading").hide();
            callback(data);
        },
        error: function () {
            $(".loading").hide();
            result = {status: false, result: null};
            callback(result);
        },
        complete: function () {
            $(".loading").hide();
        }
    });

}

function customPostData(data) {
    cekSession(function (dataResult) {
        if (dataResult.status == false) {
            alert("Your Session is Expired, You'll Redirect to Login Page");
            $(location).attr("href", APP_PATH + "/login");
        }
    });
    var success = data.successFunction;
    var error = data.errorFunction;
    $.ajax({
        cache: false,
        type: "POST",
        url: data.api,
        data: JSON.stringify(data.data),
        contentType: 'application/json',
        beforeSend: function (xhr) {
            $(".loading").show();
        },
        success: function (data, statusText, xhr) {
            $(".loading").hide();
            eval(success(data, statusText, xhr));
        },
        error: function (data, statusText, xhr) {
            $(".loading").hide();
            eval(error(data, statusText, xhr));
        },
        complete: function () {
            $(".loading").hide();
        }
    });
}

function postDataWithoutLoading(json, api, callback) {

    var result;
    cekSession(function (dataResult) {
        if (dataResult.status == false) {
            alert("Your Session is Expired, You'll Redirect to Login Page");
            $(location).attr("href", APP_PATH + "/login");
        }
    });

    $.ajax({
        cache: false,
        type: "POST",
        url: api,
        data: JSON.stringify(json),
        contentType: 'application/json',
        beforeSend: function (xhr) {
//            ajaxBeforeSend(xhr);
        },
        success: function (data) {
            callback(data);
        },
        error: function () {
            result = {status: false, result: null};
            callback(result);
        },
        complete: function () {
        }
    });
}

function getData(json, api, callback) {
    var result;

    $.ajax({
        cache: false,
        type: "GET",
        url: api,
        data: JSON.stringify(json),
        contentType: 'application/json',
        beforeSend: function (xhr) {
            ajaxBeforeSend(xhr);
        },
        success: function (data) {
            result = {status: true, result: data};
            callback(result);

        },
        error: function () {
            result = {status: false, result: null};
            callback(result);
        }
    });
}

function getDataWithoutSession(json, api, callback) {
    var result;

    $.ajax({
        cache: false,
        type: "GET",
        url: api,
        data: JSON.stringify(json),
        contentType: 'application/json',
        success: function (data) {
            result = {status: true, result: data};
            callback(result);

        },
        error: function () {
            result = {status: false, result: null};
            callback(result);
        }
    });
}

function cekSession(callback) {
    $.ajax({
        async: false,
        cache: false,
        type: "POST",
        url: APP_PATH + "/cekSession",
        data: "usr=" + USR,
        success: function (data) {

            callback(data);

        },
        error: function () {
            $(location).attr("href", APP_PATH + "/login");
        },
        complete: function () {

        }
    });
}


var IsActiveHtmlCode = {
    0: '<center><i style="color:red;" class=" fa-2x fa-lg fa fa-times"></i></center>',
    1: '<center><i style="color:green;" class=" fa-2x fa-lg fa fa-check"></i></center>',
    3: '<center><i style="color:green;" class=" fa-2x fa-lg fa fa-check"></i></center>'
};
function getIsActiveHtmlCode(param) {
    return IsActiveHtmlCode[param];
}

var IsAdmin = {
    0: 'Undifine',
    1: 'Admin',
    2: 'Personalia'
};
function getIsAdmin(param) {
    return IsAdmin[param];
}

var IsUpdateableHtmlCode = {
    0: '<center><input type="checkbox" name="us"/></center>',
    1: '<center><input type="checkbox" checked="true" name="us"/></center>',
};

function getIsUpdateableHtmlCode(param) {
    return IsUpdateableHtmlCode[param];
}

var IsActiveHtmlCodeWithStatus = {
    0: '<center><i style="color:red;" class=" fa-2x fa-lg fa fa-times"></i> Invalid</center>',
    1: '<center><i style="color:green;" class=" fa-2x fa-lg fa fa-check"></i> Valid</center>',
};
function getIsActiveHtmlCodeWithStatus(param) {
    return IsActiveHtmlCodeWithStatus[param];
}


var HtmlBenefitCode = {
    1: 'disclaimer-sama.png',
    3: 'disclaimer-atas.png',
    2: 'disclaimer-bawah.png'
//    1: '<i style="color:orange;" class=" fa-2x fa-lg fa fa-exchange"></i>',
//    3: '<i style="color:green;" class=" fa-2x fa-lg fa fa-level-up"></i>',
//    2: '<i style="color:red;" class=" fa-2x fa-lg fa fa-level-down"></i>'
};
function getHtmlBenefit(param) {
    return HtmlBenefitCode[param];
}


function getIsActiveHtmlCode(param) {
    return IsActiveHtmlCode[param];
}


var IsActiveHtmlCodeSmall = {
    0: '<i style="color:red;" class="fa-lg fa fa-times"></i>',
    1: '<i style="color:green;" class="fa-lg fa fa-check"></i>'
};
function getIsActiveHtmlCodeSmall(param) {
    return IsActiveHtmlCodeSmall[param];
}

/*
 * Smart Notifications
 */
function showBigError(title, message, timeout) {

    $.bigBox({
        title: title,
        content: message,
        color: "#C46A69",
        //timeout: 6000,
        icon: "fa fa-times shake animated",
        number: "1",
        timeout: timeout
    });

    e.preventDefault();

}

function showBigWarning(title, message, timeout) {

    $.bigBox({
        title: title,
        content: message,
        color: "#C79121",
        //timeout: 6000,
        icon: "fa fa-info shake animated",
        number: "1",
        timeout: timeout
    });


}

var jqgrid_container_table = [];

var editOptionsJobFunction = {
    keys: true,
    successfunc: function (response) {
        var r = JSON.parse(response.responseText);
        refreshJQGridTable();
        return r.status;
    },
    oneditfunc: function (rowid) {
    },
    afterrestorefunc: function (rowid, response) {
        refreshJQGridTable();
    },
    aftersavefunc: function (rowid, response) {
        refreshJQGridTable();
    },
    errorfunc: function (response, data) {
        var r = JSON.parse(data.responseText);
        refreshJQGridTable();
        $.bigBox({
            title: "Error",
            content: r.message,
            color: "#C46A69",
            icon: "fa fa-warning shake animated",
            timeout: 2000
        });
    }

};

function refreshJQGridTable() {
    $.each(jqgrid_container_table, function (index, value) {
        jQuery(value).trigger('reloadGrid');
    });
}

function buildStandardJQGrid(data) {
    jqgrid_container_table.push(data.container_table);

    $(document).ready(function () {
        pageSetUp();
        editOptionsJob = editOptionsJobFunction;
        jQuery(data.container_table).jqGrid({
            url: data.url,
            datatype: "json",
            mtype: "get",
            height: 'auto',
            rownumbers: true,
            colNames: data.colNames,
            colModel: data.colModel,
            rowNum: 10,
            rowList: [10, 20, 30],
            pager: data.container_paging,
            sortname: 'id',
            toolbarfilter: true,
            viewrecords: true,
            sortorder: "asc",
            emptyrecords: 'Job Level is empty',
            loadtext: "Loading data...",
            editurl: data.editurl,
            autowidth: true,
            gridComplete: function () {
                var ids = jQuery(data.container_table).jqGrid('getDataIDs');
                for (var i = 0; i < ids.length; i++) {
                    var cl = ids[i];
                    be = "<button class='btn btn-xs btn-default' data-original-title='Edit Row' onclick=\"jQuery('" + data.container_table + "').editRow('" + cl + "', true);\"><i class='fa fa-pencil'></i>Edit</button>&nbsp;";
//                    be = "<button class='btn btn-xs btn-default' data-original-title='Edit Row' onclick=\"$('" + data.container_table + "').jqGrid('editRow','" + cl + "', true, '', '', '', '', reload);\"><i class='fa fa-pencil'></i>Edit</button>&nbsp;";

                    se = "<button class='btn btn-xs btn-default' data-original-title='Save Row' onclick=\"jQuery('" + data.container_table + "').saveRow('" + cl + "', editOptionsJob.successfunc, null, null, editOptionsJob.aftersavefunc, editOptionsJob.errorfunc, editOptionsJob.afterrestorefunc);\"><i class='fa fa-save'></i>Save</button>&nbsp;";
                    ca = "<button class='btn btn-xs btn-default' data-original-title='Cancel' onclick=\"jQuery('" + data.container_table + "').restoreRow('" + cl + "');\"><i class='fa fa-times'></i>Cancel</button>&nbsp;";
                    //ce = "<button class='btn btn-xs btn-default' onclick=\"jQuery('#jqgridJobFunction').restoreRow('"+cl+"');\"><i class='fa fa-times'></i></button>";
                    //jQuery(data.container_table).jqGrid('setRowData',ids[i],{act:be+se+ce});
                    jQuery(data.container_table).jqGrid('setRowData', ids[i], {
                        act: be + se + ca
                    });
                }
            },
            ajaxRowOptions: {
                type: "POST",
                contentType: "application/json; charset=utf-8",
                dataType: "json"
            },
            serializeRowData: function (postdata) {
                return JSON.stringify(postdata);
            }
        });
        jQuery(data.container_table).jqGrid('navGrid', data.container_paging, {
            edit: false,
            add: false,
            search: false,
            del: true
        }, null, null, {
            caption: "Delete",
            msg: "Delete selected field?",
            warning: "dsds",
            ajaxDelOptions:
                    {
                        type: "POST",
                        contentType: "application/json",
                        dataType: "json"
                    },
            serializeDelData: function (postdata) {
                return JSON.stringify(postdata);
            }
        });
        jQuery(data.container_table).jqGrid('inlineNav', data.container_paging,
                {
                    edit: false,
                    add: true,
                    addicon: "ui-icon-plus",
                    save: true,
                    saveicon: "ui-icon-disk",
                    cancel: true,
                    cancelicon: "ui-icon-cancel",
                    addParams: {
                        rowID: "new_row",
                        useFormatter: false,
                        position: "last",
                        addRowParams: editOptionsJobFunction
                    },
                    editParams: {
                        editRowParams: editOptionsJobFunction
                    }
                }
        );
        /* Add tooltips */
        $('.navtable .ui-pg-button').tooltip({
            container: 'body'
        });
        // remove classes
        $(".ui-jqgrid").removeClass("ui-widget ui-widget-content");
        $(".ui-jqgrid-view").children().removeClass("ui-widget-header ui-state-default");
        $(".ui-jqgrid-labels, .ui-search-toolbar").children().removeClass("ui-state-default ui-th-column ui-th-ltr");
        $(".ui-jqgrid-pager").removeClass("ui-state-default");
        $(".ui-jqgrid").removeClass("ui-widget-content");

        // add classes
        $(".ui-jqgrid-htable").addClass("table table-bordered table-hover");
        $(".ui-jqgrid-btable").addClass("table table-bordered table-striped");

        $(".ui-pg-div").removeClass().addClass("btn btn-sm btn-primary");
        $(".ui-icon.ui-icon-plus").removeClass().addClass("fa fa-plus");
        $(".ui-icon.ui-icon-pencil").removeClass().addClass("fa fa-pencil");
        $(".ui-icon.ui-icon-trash").removeClass().addClass("fa fa-trash-o");
        $(".ui-icon.ui-icon-search").removeClass().addClass("fa fa-search");
        $(".ui-icon.ui-icon-refresh").removeClass().addClass("fa fa-refresh");
        $(".ui-icon.ui-icon-disk").removeClass().addClass("fa fa-save").parent(".btn-primary").removeClass("btn-primary").addClass("btn-success");
        $(".ui-icon.ui-icon-cancel").removeClass().addClass("fa fa-times").parent(".btn-primary").removeClass("btn-primary").addClass("btn-danger");

        $(".ui-icon.ui-icon-seek-prev").wrap("<div class='btn btn-sm btn-default'></div>");
        $(".ui-icon.ui-icon-seek-prev").removeClass().addClass("fa fa-backward");

        $(".ui-icon.ui-icon-seek-first").wrap("<div class='btn btn-sm btn-default'></div>");
        $(".ui-icon.ui-icon-seek-first").removeClass().addClass("fa fa-fast-backward");

        $(".ui-icon.ui-icon-seek-next").wrap("<div class='btn btn-sm btn-default'></div>");
        $(".ui-icon.ui-icon-seek-next").removeClass().addClass("fa fa-forward");

        $(".ui-icon.ui-icon-seek-end").wrap("<div class='btn btn-sm btn-default'></div>");
        $(".ui-icon.ui-icon-seek-end").removeClass().addClass("fa fa-fast-forward");

    })
    $(window).on('resize.jqGrid', function () {
        $(data.container_table).jqGrid('setGridWidth', $(data.container_table).width());
    })
}


function showBigInfo(title, message, timeout) {

    $.bigBox({
        title: title,
        content: message,
        color: "rgba(255,152,0,1)",
        timeout: timeout,
        icon: "fa fa-bell shake animated"
    });
}

function showSmallError(title, message, timeout) {

    $.smallBox({
        title: title,
        content: message,
        color: "rgba(255,87,34,1)",
        timeout: timeout,
        icon: "fa fa-times shake animated"
    });

}

function showSmallWarning(title, message, timeout) {

    $.smallBox({
        title: title,
        content: message,
        color: "rgba(0,188,212,1)",
        timeout: timeout,
        icon: "fa fa-info shake animated"
    });
}

function showSmallInfo(title, message, timeout) {

    $.smallBox({
        title: title,
        content: message,
        color: "rgba(76,175,80,1)",
        timeout: timeout,
        icon: "fa fa-thumbs-o-up  shake animated"
    });
}

function getCRUDButton(editFunction, deleteFunction, previewFunction, readFunction, restartFunction, exportToPdfFunction) {
    var htmlPreviewButton = "";
    var htmlReadButton = "";
    var htmlEditButton = "";
    var htmlDeleteButton = "";
    var htmlRestartButton = "";
    var htmlExportToPdfButton = "";

    var template = '<center><div class="btn-group"><button class="btn btn-primary btn-xs dropdown-toggle" data-toggle="dropdown">Aksi <span class="caret"></span></button><ul class="dropdown-menu" style="left:-113px;background-color:white;">';

    if (readFunction != null) {
        template += '<li style=\"cursor:pointer;\"><a onclick="' + readFunction + '"><span class="fa fa-mail-forward" style="color: #4CAF50;"></span> Lihat Detail</a></li>';
        htmlReadButton = '<button rel="tooltip" data-placement="top" data-original-title="View" style="margin-bottom:8px;margin-left:10px;" class="btn btn-default" onclick="' + readFunction + '"><i class="fa fa-mail-forward  fa-lg"></i></button>&nbsp;';
    }
    if (restartFunction != null) {
        template += '<li style=\"cursor:pointer;\"><a onclick="' + restartFunction + '"><span class="fa fa-refresh" style="color: #4CAF50;"></span> Ulang Job</a></li>';
        htmlRestartButton = '<button rel="tooltip" data-placement="top" data-original-title="Restart Job" style="margin-bottom:8px;margin-left:10px;" class="btn bg-color-orange txt-color-white" onclick="' + restartFunction + '"><i class="fa  fa-refresh  fa-lg"></i></button>&nbsp;';
    }

    if (previewFunction != null) {
        template += '<li style=\"cursor:pointer;\"><a onclick="' + previewFunction + '"><span class="fa fa-search-plus" style="color: #4CAF50;"></span> Lihat Detail</a></li>';
        htmlPreviewButton = '<button rel="tooltip" data-placement="top" data-original-title="Lihat Detail" style="margin-left:10px;" class="btn btn-info btn-sm" onclick="' + previewFunction + '"><i class="fa fa-search-plus fa-sm" ></i></button>&nbsp;';
    }
    if (editFunction != null) {
        template += '<li style=\"cursor:pointer;\"><a onclick="' + editFunction + '"><span class="fa fa-edit" style="color: #FFC107;"></span> Ubah</a></li>';
        htmlEditButton = '<button rel="tooltip" data-placement="top" data-original-title="Ubah" style="" class="btn btn-default btn-sm" onclick="' + editFunction + '"><i class="fa fa-edit fa-sm" ></i></button>&nbsp;';
    }
    if (deleteFunction != null) {
        template += '<li style=\"cursor:pointer;\"><a onclick="' + deleteFunction + '"><span class="fa fa-trash" style="color: #F44336;"></span> Hapus</a></li>';
        htmlDeleteButton = '<button rel="tooltip" data-placement="top" data-original-title="Hapus" alt="Delete" style="" class="btn bg-color-red txt-color-white  btn-sm" onclick="' + deleteFunction + '"><i class="fa fa-trash fa-lg" ></i></button>';
    }
    if (exportToPdfFunction != null) {
        template += '<li style=\"cursor:pointer;\"><a onclick="' + exportToPdfFunction + '"><span class="fa fa-file-pdf-o" style="color: #F44336;"></span> Export PDF</a></li>';
        htmlExportToPdfButton = '<button rel="tooltip" data-placement="top" data-original-title="Hapus" alt="Export to PDF" style="" class="btn bg-color-red txt-color-white  btn-sm" onclick="' + exportToPdfFunction + '"><i class="fa fa-file-pdf-o fa-lg" ></i></button>';
    }
    template += '</ul></div><center>';
    var buttons = htmlPreviewButton + htmlEditButton + htmlDeleteButton + htmlReadButton + htmlRestartButton + htmlExportToPdfButton;
    var container = '<div align="center">' + buttons + '</div>';

    return template;
}

function getDateFormat(epoch) {

//    var timestamp = epoch;
//    var date = new Date(timestamp);
//    var iso = date.toISOString().match(/(\d{4}\-\d{2}\-\d{2})T(\d{2}:\d{2}:\d{2})/);
    return "<center>" + moment("/Date(" + epoch + ")/").format('DD MMMM YYYY') + "</center>";

}





Number.prototype.formatMoney = function (decPlaces, thouSeparator, decSeparator) {
    var n = this,
            decPlaces = isNaN(decPlaces = Math.abs(decPlaces)) ? 2 : decPlaces,
            decSeparator = decSeparator == undefined ? "." : decSeparator,
            thouSeparator = thouSeparator == undefined ? "," : thouSeparator,
            sign = n < 0 ? "-" : "",
            i = parseInt(n = Math.abs(+n || 0).toFixed(decPlaces)) + "",
            j = (j = i.length) > 3 ? j % 3 : 0;
    return sign + (j ? i.substr(0, j) + thouSeparator : "") + i.substr(j).replace(/(\d{3})(?=\d)/g, "$1" + thouSeparator) + (decPlaces ? decSeparator + Math.abs(n - i).toFixed(decPlaces).slice(2) : "");
};

var IsActiveHtmlCodeSmall = {
    0: '<i style="color:red;" class=" fa fa-times"></i>',
    1: '<i style="color:green;" class=" fa fa-check"></i>',
};
function getIsActiveHtmlCodeSmall(param) {
    return IsActiveHtmlCodeSmall[param];
}

function getDateFormat(epoch) {
    var timestamp = epoch;
    var date = new Date(timestamp);
    var iso = date.toISOString().match(/(\d{4}\-\d{2}\-\d{2})T(\d{2}:\d{2}:\d{2})/);
    return iso[1] + ' ' + iso[2];
}

function showNewsDetail(newsid) {
    var data = {
        "newsId": newsid
    };
    postData(data, APP_PATH + "/hci/news/detail", function (dataResult) {
        $('#modalNews').html(dataResult);
        $('#modalNewsDetail').modal('toggle');
    });
}

//GLOBAL VARIABLE FOR VALIDATION
var COMMON_REGEXP = /^[a-zA-Z-0-9~+:,/#&_@*'%$!()\[\] ]{1,}$/;
var MIN_STR_LEN = 1;
var MAX_STR_LEN = 255;
var MIN_STR_CODE_LEN = 1;
var MAX_STR_CODE_LEN = 255;

function getREJECTButton(rejectFunction) {
    var htmlRejectButton = '<button rel="tooltip" data-placement="top" data-original-title="Reject" alt="Reject" style="margin-bottom:8px;" class="btn bg-color-red txt-color-white" onclick="' + rejectFunction + '"><i class="fa fa-mail-reply fa-lg"></i></button>';
//    var htmlEditButton = "<button onclick='editMenu(" + editFunction + ")' rel='tooltip' data-placement='top' data-original-title='Edit Menu' style='margin-bottom:8px;margin-left:10px;' class='btn btn-default'><i class='fa fa-edit fa-lg'></i></button>&nbsp;&nbsp;&nbsp;";
    var buttons = htmlRejectButton;
    var container = '<div align="center">' + buttons + '</div>';
    return container;
}

var IsActiveHtmlCodeWithStatus = {
    0: '<center><i style="color:red;" class=" fa-2x fa-lg fa fa-times"></i> Invalid</center>',
    1: '<center><i style="color:green;" class=" fa-2x fa-lg fa fa-check"></i> Valid</center>',
};
function getIsActiveHtmlCodeWithStatus(param) {
    return IsActiveHtmlCodeWithStatus[param];
}

var JobQueueStatus = {
    0: '<center><i style="color:red;" class=" fa-2x fa-lg fa fa-times"></i> Invalid</center>',
    1: '<center><i style="color:blue;" class=" fa-2x fa-lg fa fa-arrow-up"></i> New</center>',
    2: '<center><i style="color:orange;" class=" fa-2x fa-lg fa fa-arrow-right"></i> Progress</center>',
    3: '<center><i style="color:green;" class=" fa-2x fa-lg fa fa-check"></i> Done</center>',
    4: '<center><i style="color:green;" class=" fa-2x fa-lg fa fa-check"></i> Sent</center>',
    5: '<center><i style="color:red;" class=" fa-2x fa-lg fa fa-times"></i> Not Sent</center>',
};
function getJobQueueStatus(param) {
    return JobQueueStatus[param];
}

function getCRUDButtonCustom(editFunction, deleteFunction, readFunction, restartFunction, previewFunction) {
    var htmlPreviewButton = "";
    var htmlReadButton = "";
    var htmlEditButton = "";
    var htmlDeleteButton = "";
    var htmlRestartButton = "";

    var template = '<div class="btn-group"><button class="btn btn-primary btn-xs dropdown-toggle" data-toggle="dropdown">Aksi <span class="caret"></span></button><ul class="dropdown-menu" style="left:-113px;background-color:white;">';

    if (readFunction != null) {
        template += '<li><a onclick="' + readFunction + '"><span class="fa fa-mail-forward" style="color: #4CAF50;"></span> Lihat Detail</a></li>';
        htmlReadButton = '<button rel="tooltip" data-placement="top" data-original-title="View" style="margin-bottom:8px;margin-left:10px;" class="btn btn-default" onclick="' + readFunction + '"><i class="fa fa-mail-forward  fa-lg"></i></button>&nbsp;';
    }
    if (restartFunction != null) {
        template += '<li><a onclick="' + restartFunction + '"><span class="fa fa-refresh" style="color: #4CAF50;"></span> Lihat Detail</a></li>';
        htmlRestartButton = '<button rel="tooltip" data-placement="top" data-original-title="Restart Job" style="margin-bottom:8px;margin-left:10px;" class="btn bg-color-orange txt-color-white" onclick="' + restartFunction + '"><i class="fa  fa-refresh  fa-lg"></i></button>&nbsp;';
    }

    if (previewFunction != null) {
        template += '<li><a onclick="' + previewFunction + '"><span class="fa fa-search-plus" style="color: #4CAF50;"></span> Lihat Detail</a></li>';
        htmlPreviewButton = '<button rel="tooltip" data-placement="top" data-original-title="Lihat Detail" style="margin-left:10px;" class="btn btn-info btn-sm" onclick="' + previewFunction + '"><i class="fa fa-search-plus fa-sm" ></i></button>&nbsp;';
    }
    if (editFunction != null) {
        template += '<li><a onclick="' + editFunction + '"><span class="fa fa-edit" style="color: #FFC107;"></span> Ubah</a></li>';
        htmlEditButton = '<button rel="tooltip" data-placement="top" data-original-title="Ubah" style="" class="btn btn-default btn-sm" onclick="' + editFunction + '"><i class="fa fa-edit fa-sm" ></i></button>&nbsp;';
    }
    if (deleteFunction != null) {
        template += '<li><a onclick="' + deleteFunction + '"><span class="fa fa-trash" style="color: #F44336;"></span> Hapus</a></li>';
        htmlDeleteButton = '<button rel="tooltip" data-placement="top" data-original-title="Hapus" alt="Delete" style="" class="btn bg-color-red txt-color-white  btn-sm" onclick="' + deleteFunction + '"><i class="fa fa-trash fa-lg" ></i></button>';
    }
    template += '</ul></div>';
    var buttons = htmlEditButton + htmlDeleteButton + htmlReadButton + htmlRestartButton + htmlPreviewButton;
    var container = '<div align="left">' + buttons + '</div>';

    return template;
}

var getWigStatusCode = {
    1: '<center><span class="label grey">Belum dimulai</span></center>',
    2: '<center><span class="label bg-color-orange">Sedang dimulai</span></center>',
    3: '<center><span class="label bg-color-green">selesai</span></center>',
    99: '<center><span class="label bg-color-red">Rejected</span></center>',
    90: '<center><span class="label bg-color-red">Cancelled</span></center>'
};
function getWigStatus(param) {
    return getWigStatusCode[param];
}

function formatRupiah(angka, prefix){
    var number_string = angka.replace(/[^.\d]/g, '').toString(),
            split = number_string.split('.'),
            sisa = split[0].length % 3,
            rupiah = split[0].substr(0, sisa),
            ribuan = split[0].substr(sisa).match(/\d{3}/gi);

    if (ribuan) {
        separator = sisa ? ',' : '';
        rupiah += separator + ribuan.join(',');
    }

    rupiah = split[1] != undefined ? rupiah + '.' + split[1] : rupiah;
    return prefix == undefined ? rupiah : (rupiah ? 'Rp. ' + rupiah : '');
}

function sortResults(data, prop, asc) {
    data = data.sort(function(a, b) {
        if (asc) {
            return (a[prop] > b[prop]) ? 1 : ((a[prop] < b[prop]) ? -1 : 0);
        } else {
            return (b[prop] > a[prop]) ? 1 : ((b[prop] < a[prop]) ? -1 : 0);
        }
    });
    return data;
}
