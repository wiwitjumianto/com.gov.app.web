/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
var otable;
var rtable;
$(document).ready(function () {
    var empId = $("#empId").val();
    
    var levelGapColumnDefinition = [
            {"data": null},
            {"data": "periodName", "searchable": true},
            {"data": "competencyName", "searchable": true},
            {"data": null, "searchable": false,
                "render": function (data, type, full, meta) {
                    var ind = full.individualLevel / 5 * 100;
                    var req = full.requiredLevel / 5 * 100;
                    var gap = full.individualLevel - full.requiredLevel;
                    var background = gap < 0 ? 'progress-red-bg' : 'progress-green-bg';
                    return '<div class="progress progress-xs " data-isi ="'+ full.individualLevel +'" data-progressbar-value="'+ind+'" style="width: 85%;"><div class="progress-bar '+background+' " ></div></div>\n\
                            <div class="progress progress-xs" data-isi="'+ full.requiredLevel +'" data-progressbar-value="'+req+'" style="width: 85%;"><div class="progress-bar progress-green-bg " ></div></div>';
                }
            },
            {"data": null, "searchable": false,
                "render": function (data, type, full, meta) {
                    var gap = full.individualLevel - full.requiredLevel;
                    var background = "green";
                    if (gap < 0) {
                        background = "red";
                    }
                    return "<font color=\"" + background +"\">" + gap + "</font>";
                }
            },
            {"data": "categoryName", "searchable": false, "visible": false},
            {"data": "requiredLevel", "searchable": false, "visible": false},
            {"data": "individualLevel", "searchable": false, "visible": false}
    ];
    var firstOrder = [[2, 'asc']];
    
    otable = createTableInstance("datatable_fixed", "perioddicId", APP_PATH + "/cmp/gap/data_by_json/" + empId, levelGapColumnDefinition, firstOrder);
    
    if ($("#datatable_fixed_rating")) {
        
        var ratingGapColumnDefinition = [
                {"data": null},
                {"data": "periodName", "searchable": true},
                {"data": "competencyName", "searchable": true},
                {"data": "requiredRating", "searchable": true},
                {"data": "individualRating", "searchable": false},
                {"data": null, "searchable": false,
                    "render": function (data, type, full, meta) {
                        var gap = full.individualRating - full.requiredRating;
                        var background = "green";
                        if (gap < 0) {
                            background = "red";
                        }
                        return "<font color=\"" + background +"\">" + gap + "</font>";
                    }
                },
                {"data": "categoryName", "searchable": false, "visible": false}
        ];
        
        rtable = createTableInstance("datatable_fixed_rating", "perioddicId", APP_PATH + "/cmp/gap/data_by_json_rating/" + empId, ratingGapColumnDefinition, firstOrder);
        
        $("#datatable_fixed_rating thead th input[type=text]").on('keyup change', function () {
            delay(redrawTable(this, rtable), 2000 );
        }); 
    }
    
    $("#btnBack").on("click", function () {
        window.location.href =  APP_PATH + "/cmp/gap";
    });
    
    $("#cciList").change(function() {
        $("#cciGraph").html($(this).val())
        $("#cciDescription").html($('option:selected', this).attr('cci_description'));
    });
    
    $("#recommendationList").change(function() {
        $("#recoomendationValue").html($(this).val());
        var modalIdVal = $('option:selected', this).attr('ratingModal');
        if (modalIdVal) {
            $("#recommendationDesc").show();
            $("#modalId").val(modalIdVal);
        } else {
            $("#recommendationDesc").hide();
        }
        
    });
    
    $("#datatable_fixed thead th input[type=text]").on('keyup change', function () {
        delay(redrawTable(this, otable), 2000 );
    });
});

function openRecomendationDesc() {
    var idModal = $("#modalId").val();
    if (idModal) {
        $('#' + idModal).modal('toggle');
    }
}

function createTableInstance(tableId, rowId, dataUrl, columnDefinition, firstOrder) {
    /* BASIC ;*/
    var responsiveHelper_dt_basic = undefined;
    var responsiveHelper_datatable_tabletools = undefined;

    var breakpointDefinition = {
        tablet: 1024,
        phone: 480
    };
    
    var defaultTableDataConfiguration = {
        "processing": true,
        "select": true,
        "rowId": rowId,
        "paging": false,
        "serverSide": true,
        "columns": columnDefinition,
        "lengthMenu": [[10, 20, 30], [10, 20, 30]],
        "sDom": "<'dt-toolbar'<'col-xs-12 col-sm-6'f><'col-sm-6 col-xs-6 hidden-xs'l>>" +
                "tr" +
                "<'dt-toolbar-footer'<'col-sm-6 col-xs-12 hidden-xs datatablecrud'><'col-sm-6 col-xs-12'p>>",
        "autoWidth": true,
        "oLanguage": {
            "sSearch": '<span class="input-group-addon"><i class="glyphicon glyphicon-search"></i></span>'
        },
        "preDrawCallback": function () {
            // Initialize the responsive datatables helper once.
            if (!responsiveHelper_datatable_tabletools) {
                responsiveHelper_datatable_tabletools = new ResponsiveDatatablesHelper($('#' + tableId), breakpointDefinition);
            }
            // Initialize the responsive datatables helper once.
            if (!responsiveHelper_dt_basic) {
                responsiveHelper_dt_basic = new ResponsiveDatatablesHelper($('#' + tableId), breakpointDefinition);
            }
        },
        "rowCallback": function (nRow) {
            responsiveHelper_datatable_tabletools.createExpandIcon(nRow);
            responsiveHelper_dt_basic.createExpandIcon(nRow);
        },
        "drawCallback": function (oSettings) {
            responsiveHelper_datatable_tabletools.respond();
            responsiveHelper_dt_basic.respond();
            /* Need to redo the counters if filtered or sorted 
             * ADD BY REZA
             * CALLBACK FOR *COLUMN INDEX*
             */
            for (var i = 0, iLen = oSettings.aiDisplay.length; i < iLen; i++)
            {
                $('td:eq(0)', oSettings.aoData[ oSettings.aiDisplay[i] ].nTr).html(i + 1 + oSettings._iDisplayStart);
            }

            var api = this.api();
            var rows = api.rows( {page:'current'} ).nodes();
            var last=null;
 
            api.column(5, {page:'current'}).data().each( function ( group, i ) {
                if (last !== group ) {
                    $(rows).eq(i).before(
                        '<tr class="group"><td colspan="6" style="background-color:#adc2eb">'+group+'</td></tr>'
                    );
                    last = group;
                }
            } );
        },
        "columnDefs": [{
                "searchable": false,
                "orderable": false,
                "targets": -0
            }],
        "order": firstOrder
    };
    
    if (dataUrl) {
        defaultTableDataConfiguration["ajax"] = {
            url: dataUrl,
            type: 'POST',
            datatype: 'json',
            contentType: 'application/json',
            data: function (d) {
                return JSON.stringify(d);
            }
        };
    }
    
    return $('#' + tableId).DataTable(defaultTableDataConfiguration);
}

function redrawTable(comp, tableComp) {
    tableComp.column($(comp).parent().index() + ':visible')
            .search(comp.value)
            .draw();
}
function getKompetensiByCatId(catId){
    $.get(APP_PATH + "/self/dictionaryByCatId/1/" + catId , function(data, status){
//    $.get("http://172.17.35.69:8081/com.ihcs.api.cmp/self/dictionaryByCatId/1/" + catId , function(data, status){
         alert("Data: " + data + "\nStatus: " + status);
    });
}
var delay = (function(){
  var timer = 0;
  return function(callback, ms){
    clearTimeout (timer);
    timer = setTimeout(callback, ms);
  };
})();