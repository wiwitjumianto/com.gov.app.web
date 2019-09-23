<%-- 
  agung.abdurohman@bni.co.id
--%>

<%@page contentType="text/html" pageEncoding="UTF-8" %>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@page import="java.util.ArrayList" %>
<%@page import="java.util.List" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<t:genericpage>
    <jsp:attribute name="pagetitle">
        GOV
    </jsp:attribute>
    <jsp:attribute name="breadcrumb">
        <%--BREAD CRUMB GOES HERE--%>
        <li>Buat Program Bantuan</li>
        </jsp:attribute>
    <jsp:attribute name="custom_js">
            <%--CUSTOM SCRIPT GOES HERE, WILL BE PRINTED IN PAGE RELATED AREA IN SMARTADMIN TEMPLATE--%>
        <style type="text/css">
            .datepicker {
                margin-top: 65px !important;

            }
        </style>
<script src="${pageContext.request.contextPath}/resources/assets/js/plugin/summernote/summernote.min.js"></script>
<script src="${pageContext.request.contextPath}/resources/assets/js/plugin/markdown/markdown.min.js"></script>
<script src="${pageContext.request.contextPath}/resources/assets/js/plugin/markdown/to-markdown.min.js"></script>
<script src="${pageContext.request.contextPath}/resources/assets/js/plugin/markdown/bootstrap-markdown.min.js"></script>
<script src="${pageContext.request.contextPath}/resources/assets/js/plugin/morris/raphael.min.js"></script>
<script src="${pageContext.request.contextPath}/resources/assets/js/plugin/morris/morris.min.js"></script>

<script src="${pageContext.request.contextPath}/resources/assets/js/plugin/bootstrap-wizard/jquery.bootstrap.wizard.min.js"></script>
<script src="${pageContext.request.contextPath}/resources/assets/js/plugin/bootstrapvalidator/bootstrapValidator.min.js"></script>
<script src="${pageContext.request.contextPath}/resources/assets/js/plugin/serializejson/jquery.serializejson.min.js"></script>

<script src="${pageContext.request.contextPath}/resources/assets/js/plugin/datatables/jquery.dataTables.min.js"></script>
<script src="${pageContext.request.contextPath}/resources/assets/js/plugin/datatables/dataTables.colVis.min.js"></script>
<script src="${pageContext.request.contextPath}/resources/assets/js/plugin/datatables/dataTables.tableTools.min.js"></script>
<script src="${pageContext.request.contextPath}/resources/assets/js/plugin/datatables/dataTables.bootstrap.min.js"></script>
<script src="${pageContext.request.contextPath}/resources/assets/js/plugin/datatable-responsive/datatables.responsive.min.js"></script>
<script src="${pageContext.request.contextPath}/resources/assets/js/plugin/datatables/dataTables.select.min.js"></script>
<script src="${pageContext.request.contextPath}/resources/assets/js/plugin/bootstrap-timepicker/bootstrap-timepicker.min.js"></script>
<script src="${pageContext.request.contextPath}/resources/assets/js/plugin/bootstrapvalidator/bootstrapValidator.min.js"></script>


<script src="${pageContext.request.contextPath}/resources/assets/js/plugin/summernote/summernote.min.js"></script>
<script src="${pageContext.request.contextPath}/resources/assets/js/plugin/markdown/markdown.min.js"></script>
<script src="${pageContext.request.contextPath}/resources/assets/js/plugin/markdown/to-markdown.min.js"></script>
<script src="${pageContext.request.contextPath}/resources/assets/js/plugin/markdown/bootstrap-markdown.min.js"></script>
<script src="${pageContext.request.contextPath}/resources/assets/js/plugin/morris/raphael.min.js"></script>
<script src="${pageContext.request.contextPath}/resources/assets/js/plugin/morris/morris.min.js"></script>
<script src="${pageContext.request.contextPath}/resources/assets/js/plugin/chained/jquery.chained.min.js"></script>
<script type="text/javascript"
        src="${pageContext.request.contextPath}/resources/custom/js/bootstrap-datepicker.min.js"></script>
        <script> if (!window.jQuery) {
            document.write('<script src="${pageContext.request.contextPath}/resources/assets/js/libs/jquery-2.1.1.min.js"><\/script>');
        }</script>
<script>
    $('.sel2').select2();</script>

    </jsp:attribute>
    <jsp:attribute name="bodycontent">
        <%--BODY CONTENT GOES HERE, WILL BE PRINTED BETWEEN DIV ID="CONTENT TAG--%>
        <section id="widget-grid" class="">
            <div class="row">
                <article class="col-xs-12 col-sm-12 col-md-12 col-lg-12">
                    <div class="jarviswidget jarviswidget-color-whitesmoke" id="wid-id-1"
                         data-widget-colorbutton="false" data-widget-togglebutton="false" data-widget-editbutton="false"
                         data-widget-deletebutton="false">
                        <header>
                            <h2>
                                <c:if test="${programmHeader.govTrxProgrammHeaderId == null}">Tambah </c:if>
                                <c:if test="${programmHeader.govTrxProgrammHeaderId != null}">Ubah </c:if>
                                Program Bantuan
                                <c:if test="${programmHeader.govTrxProgrammHeaderId == null}"> Baru</c:if>
                            </h2>
                        </header>
                        <div id="modalGovProgrammHeader">
                            <div class="jarviswidget-editbox">
                            </div>
                            <div class="modal-body">
                                <form id="formProgramm">
                                    <input type="hidden" id="govTrxProgrammHeaderId" name="govTrxProgrammHeaderId"
                                           value="${programmHeader.govTrxProgrammHeaderId}">
                                    <!--                    <div class="row">
                                            <div class="col-md-12">
                                                <div class="form-group">
                                                    <div class="radio">
                                                        <label>

                                                            <input id="isNew" name="isNew" value="0" type="radio" class="radiobox style-0" checked="checked" name="style-0">
                                                            <span>Buat Baru</span>
                                                        </label>
                                                    </div>
                                                    <div class="radio">
                                                        <label>
                                                            <input id="isNew" name="isNew" value="1" type="radio" class="radiobox style-0" name="style-0">
                                                            <span>Melanjutkan Program Sebelunya</span>
                                                        </label>
                                                        <div class="radio">
                                                            <article name="headerList" class="col-xs-6 col-sm-6 col-md-6 col-lg-6" style="display: none;">
                                                                <span id="table-position2-description-error" class="help-block" style="color: #b94a48 !important;"></span>
                                                                <select class="form-control select2" id="govTrxProgrammHeaderId" onchange="takeData()">
                                                                    <option value="0">-- Pilih Program -- </option>
                    <c:forEach items="${listHeader}" var="listItem">
                        <option value="${listItem.govTrxProgrammHeaderId}">${listItem.govTrxProgrammHeaderName} </option>
                    </c:forEach>
                </select>
            </article>
        </div>
    </div>
</div>
</div>
</div>-->
                                    <br/>
                                    <div class="row">
                                        <div class="col-md-12">
                                            <div class="form-group">
                                                <label><b>Nama Program</b></label>
                                                <input name="govTrxProgrammHeaderName" id="govTrxProgrammHeaderName"
                                                       type="text"
                                                       class=" form-control" placeholder=""
                                                       value="${programmHeader.govTrxProgrammHeaderName}">
                                            </div>
                                        </div>
                                    </div>
                                    <div class="row">
                                        <div class="col-md-6">
                                            <div class="form-group">
                                                <label><b>Tanggal Mulai Program</b></label>
                                                <div class="input-group">
                                                    <input type="text" name="govTrxProgrammHeaderStartDate"
                                                           id="govTrxProgrammHeaderStartDate"
                                                           value='${programmHeader.govTrxProgrammHeaderStartDate}'
                                                           placeholder="yyyy-mm-dd" class="form-control datepicker"
                                                           style="margin-top: 0px !important"
                                                           data-dateformat="yyyy-mm-dd">
                                                    <span class="input-group-addon"><i
                                                            class="fa fa-calendar"></i></span>
                                                </div>
                                            </div>
                                        </div>
                                        <div class="col-md-6">
                                            <div class="form-group">
                                                <label><b>Tanggal Akhir Program</b></label>
                                                <div class="input-group">
                                                    <input type="text" name="govTrxProgrammHeaderEndDate"
                                                           id="govTrxProgrammHeaderEndDate"
                                                           value='${programmHeader.govTrxProgrammHeaderEndDate}'
                                                           placeholder="yyyy-mm-dd" class="form-control datepicker"
                                                           style="margin-top: 0px !important"
                                                           data-dateformat="yyyy-mm-dd">
                                                    <span class="input-group-addon"><i
                                                            class="fa fa-calendar"></i></span>
                                                </div>
                                            </div>
                                        </div>
                                    </div>
                                    <!--                    <div class="row">
                                            <div class="col-md-6">
                                                <div class="form-group">
                                                    <label for="status"><b>Status</b></label>
                                                    <select name="status" id="status" class="form-control">
                                                        <option value="0"
                    <c:if test="${programmHeader.status == 0}">selected</c:if>>Tidak Aktif</option>
            <option value="1"
                    <c:if test="${programmHeader.status == 1}">selected</c:if>>Aktif</option>

            </select>
        </div>
    </div>
</div>-->
                                    <hr/>
                                    <div class="row">
                                        <div class="col-md-6">
                                            <div class="form-group">
                                                <label for="status"><b>Pilih Desa</b></label><br/>
                                                <select style="width: 100%" name="villageId" id="villageId"
                                                        class=" col-md-12 sel2 form-control">
                                                    <option value="-1">-- Pilih Desa --</option>
                                                    <c:forEach items="${listVillage}" var="data">
                                        <option value="${data.appMstVillageId}">
                                            [${data.appMstProvinceName}] - [${data.appMstCityName}] -
                                            [${data.appMstDistrictName}] - ${data.appMstVillageName}
                                        </option>
                                    </c:forEach>
                                                </select>
                                            </div>
                                        </div>
                                        <div class="col-md-6">
                                            <div class="form-group">
                                                <div style="padding-top: 1%">
                                                    <label><b></b></label><br/>
                                                    <span class="btn btn-primary fa fa-plus" onclick="addToTable()"> Tambahkan</span>
                                                </div>
                                            </div>
                                        </div>
                                    </div>
                                    <div class="row">
                                        <article class="col-xs-12 col-sm-12 col-md-12 col-lg-12">
                                            <div class="jarviswidget jarviswidget-color-whitesmoke" id="wid-id-1"
                                                 data-widget-colorbutton="false" data-widget-togglebutton="false"
                                                 data-widget-editbutton="false" data-widget-deletebutton="false">
                                                <header>
                                                    <h2>List Desa/Kelurahan</h2>
                                                </header>
                                                <div>
                                                    <div class="jarviswidget-editbox">
                                                    </div>
                                                    <div class="widget-body no-padding">
                                                        <table id="datatableVillage"
                                                               class="table table-striped table-bordered table-hover"
                                                               width="100%">
                                                            <thead>

                                                            <tr>
                                                                <th width="2%">No.</th>
                                                                <th data-class="expand"> Provinsi</th>
                                                                <th data-class="expand"> Kabupaten/Kota</th>
                                                                <th data-class="expand"> Kecamatan</th>
                                                                <th data-class="expand"> Desa/kelurahan</th>
                                                                <th data-class="expand">Jumlah Usulan Unit</th>
                                                                <th data-class="expand">Instansi Pengusul</th>
                                                                <th data-class="expand">Nama Pengusul</th>
                                                                <th data-class="expand">Tanggal Mengusulkan</th>
                                                                <th data-class="expand">No Surat</th>
                                                                <th data-class="expand"></th>
                                                            </tr>
                                                            </thead>
                                                            <tbody id="bodyVillage">
                                                            <c:forEach items="${programmDetail}" var="data2"
                                                                       varStatus="loop">
                                                <c:forEach items="${listVillage}" var="data">
                                                    <c:if test="${data2.govTrxProgrammVillageId == data.appMstVillageId}">
                                                        <tr id='list${data.appMstVillageId}'>
                                                            <td>${loop.index+1}</td>
                                                            <td>${data.appMstProvinceName}</td>
                                                            <td>${data.appMstCityName}</td>
                                                            <td>${data.appMstDistrictName}</td>
                                                            <td>${data.appMstVillageName}</td>
                                                            <td><input value="${data2.govTrxProgrammUnit}" type='number'
                                                                       class='morethanZero form-control'
                                                                       name='govTrxProgrammUnit[${data.appMstVillageId}]'>
                                                            </td>
                                                            <td>
                                                                <select class='form-control'
                                                                        name='proposerId[${data.appMstVillageId}]'>
                                                                    <option value=''>-- Pilih Instansi Pengusul --
                                                                    </option>
                                                                    <c:forEach items='${listProposer}' var='dataP'>
                                                                        <option value='${dataP.appMstProposerId}'
                                                                                <c:if test='${data2.proposerId == dataP.appMstProposerId}'>selected</c:if> >${dataP.appMstProposerName}</option>
                                                                    </c:forEach>
                                                                </select>
                                                            </td>
                                                            <td><input value="${data2.govTrxProgrammDetailIssuer}"
                                                                       type='text' class=' form-control'
                                                                       name='govTrxProgrammDetailIssuer[${data.appMstVillageId}]'>
                                                            </td>
                                                            <td><input type="text"
                                                                       name="datePropos[${data.appMstVillageId}]"
                                                                       value='${data2.datePropos}'
                                                                       placeholder='yyyy-mm-dd'
                                                                       class='form-control datepicker'
                                                                       style='margin-top: 0px !important'
                                                                       data-dateformat='yyyy-mm-dd'>
                                                                <span class='input-group-addon'><i
                                                                        class='fa fa-calendar'></i></span></td>
                                                            <td><input value="${data2.letterNumber}" type='text'
                                                                       class=' form-control'
                                                                       name='letterNumber[${data.appMstVillageId}]'>
                                                            </td>
                                                            <td>
                                                                <div class='btn btn-sm btn-primary'
                                                                     onclick='removeList(${data.appMstVillageId})'><span
                                                                        class='fa fa-minus'></span></div>
                                                            </td>
                                                        </tr>
                                                    </c:if>
                                                </c:forEach>
                                            </c:forEach>
                                                            </tbody>
                                                        </table>
                                                    </div>
                                                </div>
                                            </div>
                                        </article>
                                    </div>
                                </form>
                            </div>
                            <div class="modal-footer ">
                                <button type="button" class="btn btn-border-blue draft" onclick="kembali()">
                                    Batal
                                </button>
                                <button onclick="validate()" type="button" class="btn btn-primary">
                                    Submit
                                </button>
                            </div>
                        </div>
                    </div>
            </div>
            </article>
            </div>
        </section>
    </jsp:attribute>
</t:genericpage>
<script src="${pageContext.request.contextPath}/resources/custom/js/portal/common.js"></script>
<script>
    var dataId = new Array();
    var i = 0;


    function fillArray(vilageId, x, y) {
        alert(x + "==" + y + "==" + vilageId);
    }

    function kembali() {
        $.SmartMessageBox({
            title: "Konfirmasi",
            content: "Apakah anda yakin ingin meninggalkan halaman ini ?",
            icon: "fa fa-thumbs-o-up",
            buttons: '[Tidak][Ya]'
        }, function (ButtonPressed) {
            if (ButtonPressed === "Ya") {
                window.location.href = "/gov/programm/header/list";
            }
        });

    }

    function addToTable() {
        var status = 0;
        var vilId = $('#villageId').val();
        if (vilId > -1) {
            if (dataId === null) {
                dataId[0] = vilId;
            } else {
                var i;
                for (i = 0; i < dataId.length; i++) {
                    if (dataId[i] == vilId) {
                        status = 1;
                        break;
                    }
                }
                if (status === 0) {
                    dataId[dataId.length] = vilId;
                } else {
                    showSmallWarning("Warning", "Desa telah Dipilih", 5000);
                }
            }
            if (status === 0) {
                var vil = {
                    "appMstVillageId": vilId
                };
                $.ajax({
                    cache: false,
                    type: "POST",
                    url: "/gov/app/village/getbyid",
                    data: JSON.stringify(vil),
                    contentType: 'application/json',
                    beforeSend: function (xhr) {

                    },
                    success: function (data) {
                        var obj = data.villageEntity;

                        $("#bodyVillage").append("<tr id='list" + obj.appMstVillageId + "'>\n\
        <td></td>\n\
    <td>" + obj.appMstProvinceName + "</td>\n\
                <td>" + obj.appMstCityName + "</td>\n\
                    <td>" + obj.appMstDistrictName + "</td>\n\
                <td>" + obj.appMstVillageName + "</td>\n\
                    <td><input type='number' class='morethanZero form-control' name='govTrxProgrammUnit[" + obj.appMstVillageId + "]' ></td>\n\
                    \n\
            <td> <select class='form-control sel2' name='proposerId[" + obj.appMstVillageId + "]'> <option value=''>--Pilih Instansi Pengusul--</option>\n\
    <c:forEach items='${listProposer}' var='dt'> <option value ='${dt.appMstProposerId}'>${dt.appMstProposerName}</option></c:forEach></select></td>\n\
            <td><input type='text' class=' form-control' name='govTrxProgrammDetailIssuer[" + obj.appMstVillageId + "]'></td>\n\
            <td><input type='text' class=' form-control datepicker' id='datePropos" + obj.appMstVillageId + "' name='datePropos[" + obj.appMstVillageId + "]' \n\
             placeholder='yyyy-mm-dd' style='margin-top: 0px !important' data-dateformat='yyyy-mm-dd'> \n\
            <span class='input-group-addon'><i class='fa fa-calendar'></i></span></td>\n\
            <td><input type='text' class=' form-control' name='letterNumber[" + obj.appMstVillageId + "]'></td>\n\
            <td><div class='btn btn-sm btn-primary' onclick='removeList(" + obj.appMstVillageId + ")'><span class='fa fa-minus'></span></div></td>\n\
        </tr>");

                        $('.datepicker')
                            .datepicker({
                                autoclose: true,
                                format: "yyyy-mm-dd"
                            })
                            .on('changeDate', function (e) {
                                // Revalidate the date field
                                $('#bootstrap-vertcial-wizard').bootstrapValidator('revalidateField', 'datePropos[' + vilId + ']');
                            });
                        $('.sel2').select2();

                    }, error: function () {
                        alert("error");
                    },
                    complete: function () {

                    }
                });
            }
        } else {
            showSmallError("Kesalahan", "Pilih Desa Terlebih Dahulu!", 5000);
        }


    }

    function removeList(id) {
        var iid = "#list" + id;
        var remTr = document.getElementById(iid);
        for (var i = 0; i < dataId.length; i++) {
            if (dataId[i] == id) {
                dataId.splice(i, 1);
                break;
            }
        }
        $(iid).remove();
    }

    function renderCheckboxesStatus(rowId, existingValue) {
        if (existingValue.appMstVillageId !== rowId)
            return '<div style="margin-left: 30%;" class="smart-form pull-left"><label class="toggle"><input name="villageChoose[]" type="checkbox" name="checkbox-toggle" value="' + rowId + '" checked="true"><i data-swchon-text="YES" data-swchoff-text="NO"></i></label></div>';
        else
            return '<div style="margin-left: 30%;" class="smart-form pull-left"><label class="toggle"><input name="villageChoose[]" type="checkbox" name="checkbox-toggle" value="' + rowId + '"><i data-swchon-text="YES" data-swchoff-text="NO"></i></label></div>';
    }

    var modalFunction = function () {
        $(document).ready(function () {
            $('.datepicker')
                .datepicker({
                    autoclose: true,
                    format: "yyyy-mm-dd"
                })
                .on('changeDate', function (e) {
                    // Revalidate the date field
                    $('#bootstrap-vertcial-wizard').bootstrapValidator('revalidateField', 'govTrxProgrammheaderStartDate');
                    $('#bootstrap-vertcial-wizard').bootstrapValidator('revalidateField', 'govTrxProgrammheaderEndDate');
                });

        });
        $('#modalGovProgrammHeader')
            .bootstrapValidator({
                feedbackIcons: {
                    valid: 'glyphicon glyphicon-ok',
                    invalid: 'glyphicon glyphicon-remove',
                    validating: 'glyphicon glyphicon-refresh'
                },
                fields: {
                    govTrxProgrammHeaderName: {
                        validators: {
                            notEmpty: {}
                        }
                    },
                    govTrxProgrammHeaderStartDate: {
                        validators: {
                            notEmpty: {}
                        }
                    },
                    govTrxProgrammHeaderEndDate: {
                        validators: {
                            notEmpty: {}
                        }
                    },
                    status: {
                        validators: {
                            notEmpty: {}
                        }
                    }
                }
            })
            .off('success.form.bv')
            .on('success.form.bv', function (e) {
                e.preventDefault();
                $(".loading").show();
                submit();
            });
    };

    function validate() {
        $('#modalGovProgrammHeader').bootstrapValidator('validate');
    }

    function getDistrict(cityId) {
        var data = {
            "appMstCityIsubmd": cityId
        };
        $.ajax({
            cache: false,
            type: "POST",
            url: "/gov/app/district/getdistrictbycity",
            data: JSON.stringify(data),
            contentType: 'application/json',
            beforeSend: function (xhr) {

            },
            success: function (data) {
                document.getElementById("appMstDistrictId").innerHTML = "<option value=''> -- Pilih Kecamatan -- </option>";
                var arr = data.listDistrictByCity;
                for (var i = 0; i < arr.length; i++) {
                    var obj = arr[i];
                    document.getElementById("appMstDistrictId").innerHTML += "<option value='" + obj.appMstDistrictId + "'>" + obj.appMstDistrictName + "</opton>";
                }
            },
            error: function () {
                alert("error");
            },
            complete: function () {
            }
        });
    }

    function getCity(provinceId) {
        var data = {
            "appMstProvinceId": provinceId
        };
        $.ajax({
            cache: false,
            type: "POST",
            url: "/gov/app/city/getcitybyprovince",
            data: JSON.stringify(data),
            contentType: 'application/json',
            beforeSend: function (xhr) {

            },
            success: function (data) {
                document.getElementById("appMstCityId").innerHTML = "<option value=''> -- Pilih Kota/Kab -- </option>";
                var arr = data.listCityByprov;
                for (var i = 0; i < arr.length; i++) {
                    var obj = arr[i];
                    document.getElementById("appMstCityId").innerHTML += "<option value='" + obj.appMstCityId + "'>" + obj.appMstCityName + "</opton>";
                }
            },
            error: function () {
                alert("error");
            },
            complete: function () {
            }
        });
    }

    function submit() {
        var data = $("#formProgramm").serializeJSON();
        console.log(JSON.stringify(data));
        var dataString = JSON.stringify(data);
        var idData = $("#govTrxProgrammHeaderId").val();
        var is = null;
        if (idData === "") {
            is = 1;
        } else {
            is = 2;
        }
        $.ajax({
            headers: {
                'Accept': 'application/json',
                'Content-Type': 'application/json'
            },
            'type': 'POST',
            'url': '/gov/programm/header/saveOrUpdate/' + is,
            'data': dataString,
            'dataType': 'json',
            success: function (hasil) {
                if (hasil.status) {
                    $(".loading").hide();
                    showSmallInfo("Informasi", "Data berhasil disimpan", 5000);
                    window.location.href = "/gov/programm/header/list";
                } else {
                    $(".loading").hide();
                    showSmallError("Kesalahan", "Data gagal disimpan", 5000);
                }
            }
        });
    }

    loadScript('${pageContext.request.contextPath}/resources/assets/js/plugin/serializejson/jquery.serializejson.min.js');
    loadScript('${pageContext.request.contextPath}/resources/assets/js/plugin/bootstrapvalidator/bootstrapValidator.min.js',
        loadScript('${pageContext.request.contextPath}/resources/assets/js/plugin/bootstrapvalidator/language/id_ID.js',
            modalFunction));
    $('input[type="radio"]').on('click change', function (e) {
        var value = $(this).val();
        if (value == 1) {
            $('article[name="headerList"]').show(300);
        } else {
            $('article[name="headerList"]').hide(300);
        }
    });</script>
<script src="${pageContext.request.contextPath}/resources/custom/js/portal/common.js"></script>


