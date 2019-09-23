/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
var otable;
var ptable;
var isLocalStorageSupported = (typeof(Storage) !== "undefined");
var competencyLevelMapping = {};
$(document).ready(function () {
    if (isLocalStorageSupported) {
        localStorage.setItem("competencySelected", "{}");
    }
    pageSetUp();
    
    var empId = $("#empId").val();
    var empCompetencyColumnDefinition = [
            {"data": null},
            {"data": "periodName", "searchable": true},
            {"data": "namaKategory", "searchable": true},
            {"data": "namaKompetensi", "searchable": true,
                "render": function (data, type, full, meta) {
                    var definisi = $(appendParagraph(full.definisiKompetensi)).text();
                    return "<span data-toggle=\"tooltip\" data-placement=\"top\" title=\"" + definisi +"\" data-html=\"true\">" + data + "</span>";
                }
            },
            {"data": "dictionaryTypeName", "searchable": true},
            {
                "data": null,
                "searchable": false,
                "sortable": false,
                "render": function (data, type, full, meta) {
                    return (full.levelName ? full.levelName : full.targetRating);
                }
            },
            {
                "data": null,
                "searchable": false,
                "sortable": false,
                "render": function (data, type, full, meta) {
                    return getCRUDButtonCustom("changeCompetencyLevel('" +data.competencyIndividualId + "', event)", "deleteCompetency('" + data.competencyIndividualId + "')" , null, null, null);
                }
            },
            {"data": "targetLevel", "searchable": false, "sortable": false, "visible": false},
            {"data": "dictionaryType", "searchable": false, "sortable": false, "visible": false},
            {"data": "targetRating", "searchable": false, "sortable": false, "visible": false},
            {"data": "idKompetensi", "searchable": false, "sortable": false, "visible": false}
        ];
        
        var competencyColumnDefinition = [
                {"data": null},
                {"data": "namaKategory", "searchable": true},
                {"data": "namaKompetensi", "searchable": true,
                    "render": function (data, type, full, meta) {
                        var definisi = $(appendParagraph(full.definisiKompetensi)).text();
                        return "<span data-toggle=\"tooltip\" data-placement=\"top\" title=\"" + definisi +"\" data-html=\"true\">" + data + "</span>";
                    }
                },
                {"data": "dictionaryTypeName", "searchable": true},
                {
                    "data": null, 
                    "searchable": false, 
                    "sortable": false, 
                    "render": function (data, type, full, meta) {
                        var level = 0;
                        if (isLocalStorageSupported) {
                            var competencySelected = JSON.parse(localStorage.getItem("competencySelected"));
                            var compWasAdded = competencySelected[full.periodDicId]
                            if (compWasAdded) {
                                level = compWasAdded["targetLevel"];
                            }
                        }
                        return generateCompetencyLevelDropDown(full.periodDicId, level, full.dictionaryType);

                    }
                },
                {
                    "data": null,
                    "searchable": false,
                    "sortable": false,
                    "render": function (data, type, full, meta) {
                        var isChecked = false;
                        if (isLocalStorageSupported) {
                            var competencySelected = JSON.parse(localStorage.getItem("competencySelected"));
                            var compWasAdded = competencySelected[data.periodDicId]
                            if (compWasAdded) {
                                isChecked = true;
                            }
                        }
                        return "<input type=\"checkbox\" name=\"cbKompetensi\" value=\"" + data.periodDicId + "\" " + (isChecked ? "checked" : "") + "\ onchange=\"onChangeKompetensiCheckBox(event)\" />"
                    }
                },{"data": "dictionaryType", "searchable": false, "sortable": false, "visible": false},
        ];
        
        otable = createTableInstance("datatable_fixed", "competencyIndividualId", APP_PATH + "/cmp/individual/data_by_json/" + empId, empCompetencyColumnDefinition);
        ptable = createTableInstance("datatable_fixed_competency", "periodDicId", APP_PATH + "/cmp/individual/data_competency_by_json/" + empId, competencyColumnDefinition);
        
        $("#datatable_fixed thead th input[type=text]").on('keyup change', function () {
            delay(redrawTable(this, otable), 2000 );
        });
        
        $("#datatable_fixed_competency thead th input[type=text]").on('keyup change', function () {
            delay(redrawTable(this, ptable), 2000 );
        });
        
        $("#btnBack").on("click", function () {
            window.location.href =  APP_PATH + "/cmp/individual";
        });
        
        $(".datatablecrud", "#datatable_fixed_wrapper").html('<table cellspacing="0" cellpadding="0" border="0" class="ui-pg-table navtable" style="float:left;table-layout:auto;zIndex:999;"><tbody><tr>\n\
                        <td>\n\
                            <div class="btn btn-sm btn-primary" onclick="addCompetency()"><span class="fa fa-plus"></span>&nbsp;Tambah Kompetensi</div>\n\
                        </td>\n\
                        </tr></tbody>\n\
                      </table>'); 
        
        
        $("#btnSimpan").on("click", function () {
            var competencySelected = [];
            var isViolated = false;
            if (isLocalStorageSupported) {
                var localStorageValue = JSON.parse(localStorage.getItem("competencySelected"));
                var competencyName = "";
                $.each(localStorageValue, function(key, value) {
                    if (isNumericAndGreaterThenZero(value["targetLevel"])) {
                        competencySelected.push({"perioddicId" : key, "level" : parseInt(value["targetLevel"]), "dictionaryType" : value["dictionaryType"]});
                    } else {
                        competencyName += value["namaKompetensi"] + "<br/>";
                        isViolated = true;
                    }
                });

                if(isViolated) {
                    showSmallError("Informasi", "Silahkan pilih level/rating untuk kompetensi di bawah ini <br/> " + competencyName, 5000);
                    return false;
                }

            } else {
                showSmallError("Informasi", "Browser anda tidak mensupport html5", 5000);
            }

            if (competencySelected.length == 0) {
                showSmallError("Informasi", "Minimal satu kompetensi harus di pilih", 5000);
                return false;
            } 

            $.SmartMessageBox({
                title: "Konfirmasi",
                content: "Anda Yakin Ingin Menambahkan Kompetensi Ini?",
                icon: "fa fa-thumbs-o-up",
                buttons: '[Ya][Tidak]'
            }, function (ButtonPressed) {
                if (ButtonPressed === "Ya") {
                    postData(competencySelected, APP_PATH + "/cmp/individual/addCompetency/" + $("#empId").val(), function (_dataResult) {
                        if (_dataResult.status) {
                            $('#modalCompetency').modal('hide');
                            showSmallInfo("Informasi", "Data Berhasil Disimpan", 5000);
                            otable.clear().draw();
                            ptable.clear().draw();
                            localStorage.setItem("competencySelected", "{}");
                        } else {
                            showSmallError("Informasi", "Data Gagal Disimpan\n" + _dataResult.message, 5000);
                        }
                    });
                }
            });
        });
        
        $("#btnUbahLevel").on("click", function () {
            var levelVal = $("#competencyLevel").val();
            if (!isNumericAndGreaterThenZero(levelVal)) {
                showSmallError("Informasi", "Silahkan pilih level", 5000);
                return false;
            }

            $.SmartMessageBox({
                title: "Konfirmasi",
                content: "Anda Yakin Ingin Mengubah Level Untuk Kompetensi Ini?",
                icon: "fa fa-thumbs-o-up",
                buttons: '[Ya][Tidak]'
            }, function (ButtonPressed) {
                if (ButtonPressed === "Ya") {
                    var competencyData = {"competencyIndividualId" : $("#competencyIndividualId").val(), "competencyLevel" : levelVal, "dictionaryType" : $("#dictionaryType").val()};
                    postData(competencyData, APP_PATH + "/cmp/individual/changeCompetencyLevel/", function (_dataResult) {
                        if (_dataResult.status) {
                            $('#modalUbahLevel').modal('hide');
                            showSmallInfo("Informasi", "Data Berhasil Disimpan", 5000);
                            otable.clear().draw();
                            localStorage.setItem("competencySelected", "{}");
                        } else {
                            showSmallError("Informasi", "Data Gagal Disimpan\n" + _dataResult.message, 5000);
                        }
                    });
                }
            });
        }); 
});

function createTableInstance(tableId, rowId, dataUrl, columnDefinition) {
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
        "pagingType": "full_numbers",
        "serverSide": true,
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
        "order": [[1, 'asc']],
        "initComplete": function( settings, json ) {
            $('[data-toggle="tooltip"]').tooltip();
        }
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

var delay = (function(){
  var timer = 0;
  return function(callback, ms){
    clearTimeout (timer);
    timer = setTimeout(callback, ms);
  };
})();

function redrawTable(comp, tableComp) {
    tableComp.column($(comp).parent().index() + ':visible')
            .search(comp.value)
            .draw();
}


function generateCompetencyLevelDropDown(perioddicId, level, dictionaryType) {
    var str = "";
    if (!competencyLevelMapping[perioddicId]) {
        getCompetencyToServer(perioddicId, dictionaryType);
    } 
    str = generateSelectStatement(perioddicId, competencyLevelMapping[perioddicId], level);
    
    return str;
}

function getCompetencyToServer(perioddicId, dictionaryType) {
    var competencyData = {"perioddicId" : perioddicId, "dictionaryType" : dictionaryType};
    $.ajax({
        cache: false,
        type: "POST",
        url: APP_PATH + "/cmp/model/getCompetencyLevelByPeriodDicId",
        data: JSON.stringify(competencyData),
        contentType: 'application/json',
        async: false,
        success: function (_dataResult) {
            if (dictionaryType === 1) {
                competencyLevelMapping[perioddicId] = _dataResult.levelList;
            } else {
                competencyLevelMapping[perioddicId] = getLevelMappingForRatingType(_dataResult.levelList);
            }
        }
    });
}

function getLevelMappingForRatingType(ratingInfo) {
    var levelList = new Array();
    if (ratingInfo.length > 0 && ratingInfo[0].minVal && ratingInfo[0].maxVal) {
        for(var i = ratingInfo[0].minVal; i<=ratingInfo[0].maxVal; i++) {
            levelList.push({"levelId" : i , "levelName" : i});
        }
    }
    return levelList;
}


function generateSelectStatement(periodDicId,levelList,level) {
    var selectStr = "<select class=\"input-sm targetLevelInput\" id=\""+ periodDicId +"\" onchange=\"onTargetLevelChange(event)\">";
    selectStr += "<option value=\"0\">Choose Level</option>";
    
    $.each(levelList, function(index, value){
       	selectStr += "<option value=\"" + value.levelId + "\"";
        if (level == value.levelId) {
            selectStr += " selected ";
        }
        selectStr += ">" + value.levelName + "</option> ";
    });
    
    selectStr += "</select>"
    
    return selectStr;
}


function onChangeKompetensiCheckBox(event) {
    var comp = event.target;
    if (comp && isLocalStorageSupported) {
        var competencySelected = JSON.parse(localStorage.getItem("competencySelected"));
        var rowId = parseInt($(comp).val());
        if(comp.checked) {
            var rowComp = $(comp).closest('tr');
            if (rowComp) {
                var targetLevel = rowComp.find(".input-sm.targetLevelInput").val();
                var dataRow = ptable.row(rowComp).data();
                dataRow["targetLevel"] = targetLevel;
                var pushData = {};
                competencySelected[rowId] = dataRow;
            }  
        } else {
            delete competencySelected[rowId];
        }
        localStorage.setItem("competencySelected", JSON.stringify(competencySelected));
    }
}

function onTargetLevelChange(event) {
    var comp = event.target;
    if (comp && isLocalStorageSupported) {
        var rowId = $(comp).attr("id");
        var competencySelected = JSON.parse(localStorage.getItem("competencySelected"));
        var dataRow = competencySelected[rowId];
        if (dataRow) {
            dataRow["targetLevel"] = $(comp).val();
            competencySelected[rowId] = dataRow;
            localStorage.setItem("competencySelected", JSON.stringify(competencySelected));
        }
    }
}

function isNumericAndGreaterThenZero(value) {
    return value && !isNaN(value) && (parseInt(value) > 0); 
}

function changeCompetencyLevel(competencyIndividualId, event) {
    var rowComp = $(event.target).closest('tr');
    if (rowComp) {
        var dataRow = otable.row(rowComp).data();
        if (dataRow) {
            $("#categoryName").text(dataRow["namaKategory"]);
            $("#competencyName").text(dataRow["namaKompetensi"]);
            $("#competencyDesc").html(dataRow["definisiKompetensi"]);
            $("#competencyType").text(dataRow["dictionaryTypeName"]);
            
            var selectComp = $("#competencyLevel");
            var targetLevel = dataRow["targetLevel"];
            if (!targetLevel) {
                targetLevel = dataRow["targetRating"];
            }
            selectComp.find('option').remove().end();
            var periodDicId = dataRow["periodDicId"];
            var dictionaryType = dataRow["dictionaryType"];
            if (!competencyLevelMapping[periodDicId]) {
                getCompetencyToServer(periodDicId, dictionaryType);
            }
            
            var levelList = competencyLevelMapping[periodDicId];
            selectComp.append("<option value=\"0\">--Pilih--</option>");
            $.each(levelList, function(index, value){
                var optionStr = "<option value=\"" + value.levelId + "\"";
                if (targetLevel == value.levelId) {
                    optionStr += " selected ";
                }
                optionStr += ">" + value.levelName + "</option> ";
                selectComp.append(optionStr)
            });
            
            $("#competencyIndividualId").val(competencyIndividualId);
            $("#dictionaryTypeVal").val(dictionaryType);
            $('#modalUbahLevel').modal('toggle');
        }
    } 
}

 function addCompetency() {
    $('#modalCompetency').modal('toggle');
 }

function deleteCompetency(competencyIndividualId) {
    $.SmartMessageBox({
            title: "Konfirmasi",
            content: "Anda Yakin Ingin Menghapus Kompetensi Ini?",
            icon: "fa fa-thumbs-o-up",
            buttons: '[Ya][Tidak]'
        }, function (ButtonPressed) {
            if (ButtonPressed === "Ya") {
                var competencyData = {"competencyIndividualId" : competencyIndividualId};
                postData(competencyData, APP_PATH + "/cmp/individual/deleteIndividualCompetency/", function (_dataResult) {
                    if (_dataResult.status) {
                        showSmallInfo("Informasi", "Data Berhasil Dihapus", 5000);
                        otable.clear().draw();
                        ptable.clear().draw();
                    } else {
                        showSmallError("Informasi", "Data Gagal Dihapus\n" + _dataResult.message, 5000);
                    }
                });
            }
    });
} 

function appendParagraph(str) {
    return "<p>" + str + "</p>";
}