/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
function TrackingDynamicTableWidget(parentDiv, url, postData) {
    this.parentDiv = parentDiv;
    this.url = url;
    this.postData = postData;
    this.headers = this.postData.field;
    this.parentDivId = $(this.parentDiv).attr("id");
};

TrackingDynamicTableWidget.prototype.setHeaders = function(headers) {
    this.headers = headers;
};

TrackingDynamicTableWidget.prototype.setGroupBy = function(groupBy) {
    this.groupBy = groupBy;
};

TrackingDynamicTableWidget.prototype.setPostData = function(postData) {
    this.postData = postData;
};

TrackingDynamicTableWidget.prototype.draw = function() {
    this.clear();
    var columnDefinition = new Array();
    
    var tableComp = document.createElement("table");
    tableComp.setAttribute("id", "datatable_" + this.parentDivId);
    tableComp.setAttribute("class", "table table-striped table-bordered table-hover");
    tableComp.setAttribute("width", "100%");
    
    var tableHeadComp = document.createElement("thead");
    var tableHeadRowComp = document.createElement("tr");
    
    var noText = document.createTextNode("No.");
    var tableHeaderNoComp = document.createElement("th");
    tableHeaderNoComp.appendChild(noText);
    tableHeaderNoComp.setAttribute("width", "2%");
    columnDefinition.push({"data": null});
    tableHeadRowComp.appendChild(tableHeaderNoComp);
    
    $.each(this.headers, function(index, value) {
        var text = document.createTextNode(value.fieldName);
        var tableHeader = document.createElement("th");
        tableHeader.setAttribute("data-hide", "phone, tablet");
        tableHeader.appendChild(text);
        
        if (value.width) {
           tableHeader.setAttribute("width", value.width + "%");
        }
        
        var visible = true;
        if (value.hasOwnProperty("visible")) {
            visible = value.visible;
        }
        
        columnDefinition.push({"data": value.id, "searchable": false, "visible" : visible});
        tableHeadRowComp.appendChild(tableHeader);
    });
    
    tableHeadComp.appendChild(tableHeadRowComp);
    tableComp.appendChild(tableHeadComp);
    $(this.parentDiv).append(tableComp);
    this.dataTable(columnDefinition);
};

TrackingDynamicTableWidget.prototype.clear = function() {
    $(this.parentDiv).empty();
};


TrackingDynamicTableWidget.prototype.dataTable = function(columnDefinition) {
    var self = this;
    var tableId = "datatable_" + this.parentDivId;
    var responsiveHelper_dt_basic = undefined;
    var responsiveHelper_datatable_tabletools = undefined;

    var breakpointDefinition = {
        tablet: 1024,
        phone: 480
    };
    
    var defaultTableDataConfiguration = {
        "processing": true,
        "select": true,
        "pagingType": "full_numbers",
        "serverSide": true,
        "searching": false,
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
            
            if (self.groupBy) {
                var api = this.api();
                var rows = api.rows( {page:'current'} ).nodes();
                var last=null;

                api.column(self.groupBy, {page:'current'}).data().each( function ( group, i ) {
                    if (last !== group ) {
                        $(rows).eq(i).before(
                            '<tr class="group"><td colspan="6" style="background-color:#adc2eb">'+group+'</td></tr>'
                        );
                        last = group;
                    }
                } );
            }
        },
        "columnDefs": [{
                "searchable": false,
                "orderable": false,
                "targets": -0
            }]
    };
    
    
    if (this.url) {
        defaultTableDataConfiguration["ajax"] = {
            url: APP_PATH + this.url,
            type: 'POST',
            datatype: 'json',
            contentType: 'application/json',
            data: function (d) {
                var postData = {searchData : self.postData, paging:d};
                return JSON.stringify(postData);
            }
        };
    }
    $("#" + tableId).DataTable(defaultTableDataConfiguration);
};
