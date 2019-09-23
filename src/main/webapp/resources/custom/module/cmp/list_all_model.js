/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


var otable;
var ptable;
$(document).ready(function () {
    pageSetUp();
    $('#collapsePosition').collapse('show');
    
    var typeModel = $("#typeModel").val();
    if (typeModel === 'jobTitleModel') {
        $("#jTBreadCrumb").show();
        $("#jobTitleDiv").show();
    } else if (typeModel === 'positionModel') {
        $("#posBreadCrumb").show();
        $("#positionDiv").show();
    }

    /* COLUMN FILTER  */
    var jobTitleColumnDefinition = [
            {"data": null},
            {"data": "codeJabatan", "searchable": true},
            {"data": "namaJabatanEn", "searchable": true},
            {"data": "namaJabatanId", "searchable": true},
            {"data": "jumlahCompetensi", "searchable": false, "sortable": false},
            {
                "data": null,
                "searchable": false,
                "sortable": false,
                "render": function (data, type, full, meta) {
                    var viewDetail = 'viewDetail(' + data.idJabatan + ', ' + "'/cmp/model/view_detail/jobTitleModel/'" + ')';
                    return getCRUDButtonCustom(null, null, null, null, viewDetail);
                }
            }
        ];
    otable = createTableInstance("datatable_fixed_column", "idJabatan", APP_PATH + "/cmp/model/data_by_json/jobTitle", jobTitleColumnDefinition);
    
    var positionColumnDefinition = [
            {"data": null},
            {"data": "codePosisi", "searchable": true},
            {"data": "namaPosisiEn", "searchable": true},
            {"data": "namaPosisiId", "searchable": true},
            {"data": "jumlahCompetensi", "searchable": false, "sortable": false},
            {
                "data": null,
                "searchable": false,
                "sortable": false,
                "render": function (data, type, full, meta) {
                    var viewDetail = 'viewDetail(' + data.idPosisi + ', ' + "'/cmp/model/view_detail/positionModel/'" + ')';
                    return getCRUDButtonCustom(null, null, null, null, viewDetail);
                }
            }
        ];
    ptable = createTableInstance("datatable_fixed_position", "idPosisi", APP_PATH + "/cmp/model/data_by_json/position", positionColumnDefinition);
    
    // Apply the filter
    $("#datatable_fixed_column thead th input[type=text]").on('keyup change', function () {
        delay(redrawTable(this, otable), 2000 );
    });
    
    $("#datatable_fixed_position thead th input[type=text]").on('keyup change', function () {
        delay(redrawTable(this, ptable), 2000 );
    });
    
    function redrawTable(comp, tableComp) {
        tableComp.column($(comp).parent().index() + ':visible')
                .search(comp.value)
                .draw();
    }
    
    $('#datatable_fixed_column').on('draw.dt', function () {
        $("[rel=tooltip]").tooltip();
    });
    
    $(".datatablecrud").html('<table cellspacing="0" cellpadding="0" border="0" class="ui-pg-table navtable" style="float:left;table-layout:auto;"><tbody><tr>\n\
                                    <div class="btn btn-sm btn-primary" onclick="openUploadModal()"><span class="fa fa-upload"></span>&nbsp;Import Kompetensi Model</div>\n\
                                    </td></tr></tbody>\n\
                                  </table>');
    
    $("#btnUpload").on("click", function () {
        var filePath = $.trim($('#file').val());
        if (!filePath) {
            showSmallWarning("Information", "Harap pilih file terlebih dahulu", 5000);
            return;
        }
        
        $(".loading").show();
        $.ajax({
            cache: false,
            url: APP_PATH + "/cmp/model/uploadCompetencyModel",
            type: "POST",
            data: new FormData(document.getElementById("fileForm")),
            enctype: 'multipart/form-data',
            processData: false,
            contentType: false,
            success: function (dataResult) {
                if (dataResult.status) {
                   $('#modalUploadCompetency').modal('hide');
                   showSmallInfo("Informasi", "Data Berhasil Disimpan", 5000);	
                   otable.clear().draw();
                   ptable.clear().draw();
                } else {
                   showSmallError("Information", "Upload Data Gagal <br/>" + dataResult.message, 5000);
                }
            },
            error: function (dataResult) {
                $(".loading").hide();
                showSmallError("Information", "Upload Data Gagal <br/>" + dataResult.message, 5000);

            },
            complete: function (dataResult) {
                $(".loading").hide();
            }
        });
    });
    
});


function openUploadModal() {
    $('#modalUploadCompetency').modal('toggle');
}
function viewDetail(rowId, url) {
    window.location.href = APP_PATH + url + rowId;
}

function createTableInstance(tableId, rowId, dataUrl, columnDefinition) {
    /* BASIC ;*/
    var responsiveHelper_dt_basic = undefined;
    var responsiveHelper_datatable_tabletools = undefined;

    var breakpointDefinition = {
        tablet: 1024,
        phone: 480
    };
    
    return $('#' + tableId).DataTable({
        "processing": true,
        "select": true,
        "rowId": rowId,
        "pagingType": "full_numbers",
        "serverSide": true,
        "ajax": {
            url: dataUrl,
            type: 'POST',
            datatype: 'json',
            contentType: 'application/json',
            data: function (d) {
                return JSON.stringify(d);
            }
        },
        columns: columnDefinition,
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

        },
        "columnDefs": [{
                "searchable": false,
                "orderable": false,
                "targets": -0
            }],
        "order": [[1, 'asc']]
    });
}

var delay = (function(){
  var timer = 0;
  return function(callback, ms){
    clearTimeout (timer);
    timer = setTimeout(callback, ms);
  };
})();