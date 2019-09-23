/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
var positionArr = {};
var tableFieldDefinition = {
    "reportCompetency" : {
        "headers" : [
            {
                id : "competencydictionaryName",
                fieldName : "Nama Kompetensi (ID)",
                width : 24
            },
            {
                id : "competencydictionaryNameEng",
                fieldName : "Nama Kompetensi (EN)",
                width : 24
            },
            {
                id : "definition",
                fieldName : "Definisi",
                width : 40
            },
            {
                id : "typeName",
                fieldName : "Tipe Kompetensi",
                width : 10
            },
            {
                id : "childCatname",
                fieldName : "Kategory Kompetensi",
                visible : false
            }
        ],
        "groupBy" : 5,
        "url" : "/cmp/cmpReporting/data_by_json/reportCompetency"
    },
    "reportingModel" : {
        "headers" : [
            {
                id : "competencydictionaryName",
                fieldName : "Nama Kompetensi"
            },
            {
                id : "definition",
                fieldName : "Definisi"
            },
            {
                id : "typeName",
                fieldName : "Tipe Kompetensi"
            },
            {
                id : "level",
                fieldName : "Level/Rating"
            },
            {
                id : "position",
                fieldName : "Posisi",
                visible : false
            }
        ],
        "groupBy" : 5,
        "url" : "/cmp/cmpReporting/data_by_json/reportingModel"
    },
    "reportingIndividual" : {
        "headers" : [
            {
                id : "competencydictionaryName",
                fieldName : "Nama Kompetensi"
            },
            {
                id : "definition",
                fieldName : "Definisi"
            },
            {
                id : "typeName",
                fieldName : "Tipe Kompetensi"
            },
            {
                id : "level",
                fieldName : "Level/Rating"
            }
        ],
        "groupBy" : 3,
        "url" : "/cmp/cmpReporting/data_by_json/reportingIndividual"
    },
    "reportingGap" : {
        "headers" : [
            {
                id : "competencyName",
                fieldName : "Nama Kompetensi"
            },
            {
                id : "requiredLevelName",
                fieldName : "Required Level/Rating"
            },
            {
                id : "individualLevelName",
                fieldName : "Individual Level/Rating"
            },
            {
                id : "gap",
                fieldName : "Gap"
            }
        ],
        "url" : "/cmp/cmpReporting/data_by_json/reportingGap"
    }
} 

var reportingTable;

$(document).ready(function () {
    $("#selectUnit").select2();
    $("#selectPosisi").select2();
    fillPositionBasedOnUnit();
    
    $("#reportTypeSelect").change(function() {
        $("#tableReport").hide();
        var options = $("#reportTypeSelect option");
        options.each(function(){
            var panelIdObj = $("#" + $(this).attr("value"));
            if (panelIdObj.length > 0) {
                panelIdObj.hide();
            }
        });
        
        var panelToShow = $("#" + $(this).val());
        if (panelToShow.length > 0) {
            panelToShow.show();
        }
    });

    $(".submitReport").on('click', function(){
        if (isValidReport()) {
            var fields = tableFieldDefinition[$("#reportTypeSelect").val()]
            if (fields) {
                reportingTable = new TrackingDynamicTableWidget("#tableResult", fields.url, {});
                reportingTable.setHeaders(fields.headers);
                reportingTable.setGroupBy(fields.groupBy);
                reportingTable.setPostData(getSearchCriteria());
                reportingTable.draw();
                $("#tableReport").show();
            }
        }
    });

    
    $("#selectUnit").on("change", function() {
        fillPositionBasedOnUnit();
    });

});

function getSearchCriteria() {
    var reportType = $("#reportTypeSelect").val();
    var postData = {};
    if (reportType === 'reportCompetency') {
        postData["periodId"] = $("#competencyPeriod").val();
    } else if (reportType === 'reportingModel') {
        postData["unitId"] = $("#selectUnit").val();
        postData["positionId"] = $("#selectPosisi").val();
    } else if (reportType === 'reportingIndividual') {
        postData["empNumber"] = $("#individualNpp").val();
    } else if (reportType === 'reportingGap') {
        postData["nppNumber"] = $("#gapNpp").val();
    }
    
    return postData;
}

function getReportingParameter(data, typeParameter) {
   var result = new Array();
   $.ajax({
        cache: false,
        type: "POST",
        url: APP_PATH + "/cmp/cmpReporting/getCompetencyReportingParameter/" + typeParameter,
        data: JSON.stringify(data),
        contentType: 'application/json',
        async: false,
        success: function (_dataResult) {
            result = _dataResult;
        }
    }); 
    return result;
}

function fillPositionBasedOnUnit(){
    var unitId = $("#selectUnit").val();
    var positions;
    if (positionArr.hasOwnProperty(unitId)) {
        positions = positionArr[unitId];
    } else {
        var result = getReportingParameter({unitId : unitId}, "position");
        if (result) {
            var positions = new Array();
            $.each(result, function(index, value) {
                if(index === 0) {
                    positions.push({id : "%", text : "Semua"});
                }
                positions.push({id : value.positionId, text : value.positionName});
            });
            positionArr[unitId] = positions;
        }
    }
    
    if (positions.length === 0) {
        $('#selectPosisi').find('option').remove().end();
        $("#selectPosisi").val(null).trigger("change"); 
    } else {
        $("#selectPosisi").select2({data:positions});
    }
}

function isValidReport() {
    var reportType = $("#reportTypeSelect").val();
    if (reportType === 'reportingModel') {
        var unitId = $("#selectUnit").val();
        if (unitId !== "%") {
            var positionId = $("#selectPosisi").val();
            if (!positionId) {
                showSmallError("Informasi", "Tidak ada posisi di bawah unit ini", 5000);
                return false;
            }
        }
    } else if (reportType === 'reportingIndividual') {
        var individualNpp = $("#individualNpp").val();
        if (!individualNpp) {
            showSmallError("Informasi", "Silahkan isi NPP terlebih dahulu", 5000);
            return false;
        }
    } else if (reportType === 'reportingGap') {
        var individualNpp = $("#gapNpp").val();
        if (!individualNpp) {
            showSmallError("Informasi", "Silahkan isi NPP terlebih dahulu", 5000);
            return false;
        }
    }
    return true;
}

function exportJasperReporting(exportType) {
    var reportSelected = $("#reportTypeSelect").val();
    var urlToCall = $("#" + reportSelected).attr("data-url");
    var url = APP_PATH + parsePathVariable(urlToCall) + "/" + exportType;
    var newWin = window.open(url, "Window", "resizable=1,scrollbars=yes,statusbar=0,height=550,width=700,top=0,left=0");
    newWin.focus();
}

function parsePathVariable(url) {
    var parseUrl = url;
    var regExp = /\(([^)]+)\)/;
    var matches;
    while ((matches = regExp.exec(parseUrl)) !== null) {
        var variable = $("#" + matches[1]).val();
        if (variable) {
            if (variable === '%') {
                variable = 'ALL';
            }
            parseUrl = parseUrl.replace("(" + matches[1] + ")", variable);
        };
    }
    return parseUrl;
}