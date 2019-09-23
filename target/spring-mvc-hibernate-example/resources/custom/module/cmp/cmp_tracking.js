/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
var paramDet;
var fieldSelected = {};
var kriteriaJson = [
    {
        "id" : "COMP",
        "name" : "Kompetensi",
        "child" : [
            {
                "id" : "COMPETENCYDICTIONARY_NAME",
                "type" : "textfield",
                "name" : "Nama Kompetensi (ID)",
                "table" : "CMP_MST_DICTIONARY"
            },
            {
                "id" : "COMPETENCYDICTIONARY_NAMEENG",
                "type" : "textfield",
                "name" : "Nama Kompetensi (EN)",
                "table" : "CMP_MST_DICTIONARY"
            },
            {
                "id" : "NAME",
                "type" : "dropdown",
                "name" : "Tipe",
                "url" : "/cmp/getDictionaryTypes",
                "dkey" : "name",
                "dvalue" : "name",
                "table" : "CMP_MST_DICTIONARY_TYPE"
            },
            {
                "id" : "CAT_NAME",
                "type" : "textfield",
                "name" : "Nama Kategori (ID)",
                "table" : "CMP_MST_CATEGORY"
            },
            {
                "id" : "CAT_NAME_ENG",
                "type" : "textfield",
                "name" : "Nama Kategori (EN)",
                "table" : "CMP_MST_CATEGORY"
            }
        ]
    },
    {
        "id" : "PERIOD",
        "name" : "Periode Kompetensi",
        "child" : [
            {
                "id" : "PERIOD_NAME",
                "type" : "dropdown",
                "url" : "/cmp/cmpTracking/getPeriodList/",
                "dkey" : "periodName",
                "dvalue" : "periodName",
                "name" : "Nama Periode",
                "table" : "CMP_TRX_COMP_PERIOD"
            }
        ]
    },
    {
        "id" : "EMPLOYEE",
        "name" : "Pegawai",
        "child" : [
            {
                "id" : "EMP_NUMBER",
                "type" : "textfield",
                "name" : "NPP",
                "table" : "PEO_MST_EMPLOYEE"
            },
            {
                "id" : "FIRST_NAME",
                "type" : "textfield",
                "name" : "Nama Depan",
                "table" : "PEO_MST_DPP_NAME"
            },
            {
                "id" : "MIDDLE_NAME",
                "type" : "textfield",
                "name" : "Nama Tengah",
                "table" : "PEO_MST_DPP_NAME"
            },
            {
                "id" : "LAST_NAME",
                "type" : "textfield",
                "name" : "Nama Belakang",
                "table" : "PEO_MST_DPP_NAME"
            },
            {
                "id" : "POSITION_NAME_IND",
                "type" : "textfield",
                "name" : "Nama Posisi (ID)",
                "table" : "ORG_MST_POSITION"
            },
            {
                "id" : "POSITION_NAME_ENG",
                "type" : "textfield",
                "name" : "Nama Posisi (EN)",
                "table" : "ORG_MST_POSITION"
            },
            {
                "id" : "UNIT_NAME_IND",
                "type" : "textfield",
                "name" : "Nama Unit (ID)",
                "table" : "ORG_MST_ORGANIZATION_UNIT"
            },
            {
                "id" : "UNIT_NAME_ENG",
                "type" : "textfield",
                "name" : "Nama Unit (EN)",
                "table" : "ORG_MST_ORGANIZATION_UNIT"
            }
        ]
    }
]

$(document).ready(function () {
    paramDet = new TrackingParameterWidget("#subMenuDiv", "Pilih Parameter", "#conditionId");
    paramDet.setData(kriteriaJson);
    paramDet.draw();
    paramDet.registerEvents();
    
    generateFieldOption();
});

function generateFieldOption() {
    var selectComp = $("#fieldOption");
    selectComp.find('option').remove().end();
    selectComp.append('<option value="">--Pilih Field--</option>');
    $.each(kriteriaJson,function(index, value) {
         selectComp.append('<option value="' + value.id + '">' + value.name + '</option>');
    });
}

function showField() {
    $("#bottontambah").prop('disabled', true);
    var headerId = $("#fieldOption").val();
    var selectComp = $("#displayField");
    selectComp.find('option').remove().end();
    var isFound = false;
    $.each(kriteriaJson,function(index, value) {
        if (value.id === headerId) {
            $.each(value.child,function(idx, val) {
                selectComp.append('<option value="' + val.id + '"  dataTable="' + val.table + '">' + val.name + '</option>');
                if (!isFound) {
                    isFound = true;
                }
            });
            return false;
        }
    });
    $("#bottontambah").prop('disabled', !isFound);
}

function addField() {
    var headerSelected = $("#fieldOption").val();
    var headerSelectedText = $("#fieldOption option:selected").text();
    var fieldToAdd = $("#displayField").val();
    var fieldToAddText = $("#displayField option:selected").text();
    var fieldToAddTable = $("#displayField option:selected").attr('dataTable');
    
    if(!fieldSelected.hasOwnProperty(headerSelected)) {
        $("#bigbox").append('<div id="brgroup_' + headerSelected + '"><br/><br/><br/></div><span id="box_' + headerSelected + '" type="button" data-placement="bottom" class="danger btn-primary pull-left" style=" width:auto !important;background-color: rgba(0,0,0,.3);padding:10px 20px; margin-bottom: 5px; margin-top:-40px; width: 100%;">'
            + '' + headerSelectedText + '&nbsp;<b onclick="removeAllField(\'' + headerSelected + '\')" style =" color: white; cursor: pointer"> X </b></span>'
            + '<div id="boxbox_' + headerSelected + '"><div  style="border: solid 1px rgba(0,0,0,.1); display:block; margin-top:10px; border-width: 1px; width:100%;"><i style = "color : white;"></i></i>'
            + '<divv id="child_' + headerSelected + '" style="margin-top:-15px;"></divv><div style="margin-left:-100 !important;" class="clear-both"></div></div></div>');
        
        fieldSelected[headerSelected] = [];
    } 
    
    var child = fieldSelected[headerSelected];
    var violated = false;
    $.each(child,function(index, value) {
        if (value.id === fieldToAdd) {
            violated = true;
            return false;
        }
    });
    
    if (violated) {
        showSmallError("Informasi", fieldToAddText + " sudah di tambahkan", 5000);
        return;
    }
    
    $("#child_" + headerSelected).append('<span id="boxChild_' + fieldToAdd + '" type="button" data-placement="bottom" class="selectedField danger badge fieldName btn-primary pull-left" style="width:auto !important;background-color: rgba(0,188,212,1); padding:10px 20px; margin: 5px; width: 100%;">'
            + fieldToAddText + '&nbsp;&nbsp;<b onclick="removeField(\'' + fieldToAdd + '\',\'' + headerSelected + '\')" style =" color: white; cursor: pointer"> X </b></span>');
    fieldSelected[headerSelected].push({id : fieldToAdd, table : fieldToAddTable, fieldName : fieldToAddText});
}

function removeAllField(headerId) {
    $("#brgroup_" + headerId).remove();
    $("#boxbox_" + headerId).remove();
    $("#box_" + headerId).remove();
    delete fieldSelected[headerId];
}

function removeField(childId, headerId) {
    $("#boxChild_" + childId).remove();
    var childArr = fieldSelected[headerId];
    $.each(childArr,function(index, value) {
        if (value.id === childId) {
            childArr.splice(index, 1);
        }
    });
    
    fieldSelected[headerId] = childArr;
    if (childArr.length === 0) {
        removeAllField(headerId);
    }
}

function generateData() {
    var criterias = paramDet.getCriteriaValue();
    if (Object.keys(fieldSelected).length === 0) {
        showSmallError("Informasi", "Silahkan tambahkan field yang akan ditampilkan", 5000);
        return;
    }
    
    if (criterias) {
        var postData = {criteria : criterias, field : getFieldSelected()};
        var dynamicTable = new TrackingDynamicTableWidget("#trackingTable", "/cmp/cmpTracking/data_by_json/", postData);
        dynamicTable.draw();
        console.log(postData);
    }
}

function getFieldSelected() {
    var field = new Array();
    $.each(fieldSelected,function(key, value) {
        field = field.concat(value);
    });
    
    return field;
}