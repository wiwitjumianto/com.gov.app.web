<%-- 
    Created on : 10 agustus 2017, 1:21:28 PM
    Author     : Agung Abdurohman
--%>

<%@page import="java.util.ArrayList"%>
<%@page import="java.util.List"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%> 
<!-- MODAL ADD -->
<script src="http://ajax.googleapis.com/ajax/libs/jqueryui/1.10.3/jquery-ui.min.js"></script>
<div data-backdrop="static" class="modal fade in" id="modalAppDistrict" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true" style="display: none;">
    <div class="modal-dialog modal-lg-50">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-hidden="true">
                    ×
                </button>
                <h1 style="font-weight: 500;color: #000;" class="modal-title" id="myModalLabel">
                    <c:if test="${listDistrict.appMstDistrictId == null}">Tambah </c:if> 
                    <c:if test="${listDistrict.appMstDistrictId != null}">Ubah </c:if>    
                        Kecamatan
                    <c:if test="${listDistrict.appMstDistrictId == null}"> Baru</c:if> 
                    </h1>
                </div>
                <div class="modal-body">
                    <form id="formDistrict">
                        <input type="hidden" id="appMstDistrictId" name="appMstDistrictId" value="${listDistrict.appMstDistrictId}">
                    <div class="row">
                        <div class="col-md-6">
                            <div class="form-group">
                                <label><b><i>Provinsi</i></b></label>
                                <select class="form-control sel2" onchange="getCity(this.value)" style="width:100%;" id="appMstProvinceId" name="appMstProvinceId">
                                    <option value=""> -- Pilih Provinsi -- </option>
                                    <c:forEach items="${listProvince}" var="data">
                                        <option  <c:if test="${data.appMstProvinceId == listDistrict.appMstProvinceId}">selected</c:if> value="${data.appMstProvinceId}">${data.appMstProvinceName}</option>
                                    </c:forEach>
                                </select>
                            </div>
                        </div>
                    </div>
                    <div class="row">
                        <div class="col-md-6">
                            <div class="form-group">
                                <label><b><i>Kota/Kabupaten</i></b></label>
                                <select class="form-control sel2" style="width:100%;" id="appMstCityId" name="appMstCityId">
                                    <option value=""> -- Pilih Kota/Kab -- </option>
                                    <c:forEach items="${listCity}" var="datac">
                                        <option  <c:if test="${datac.appMstCityId == listDistrict.appMstCityId}">selected</c:if> value="${datac.appMstCityId}">${datac.appMstCityName}</option>
                                    </c:forEach>
                                </select>
                            </div>
                        </div>
                    </div>
                    <div class="row">
                        <div class="col-md-12">
                            <div class="form-group">
                                <label><b>Nama Kecamatan</b></label>
                                <input name="appMstDistrictName" id="appMstDistrictName" type="text"  class=" form-control" placeholder="" value="${listDistrict.appMstDistrictName}">
                            </div>
                        </div>
                    </div>

                    <div class="row">
                        <div class="col-md-6">
                            <div class="form-group">
                                <label for="status"><b>Status</b></label>
                                <select name="status" id="status" class="form-control">
                                    <option value="1" 
                                            <c:if test="${listDistrict.status == 1}">selected</c:if>>Aktif</option>
                                            <option value="0" 
                                            <c:if test="${listDistrict.status == 0}">selected</c:if>>Tidak Aktif</option>
                                    </select>
                                </div>
                            </div>
                        </div>
                    </form>
                </div>
                <div class="modal-footer">
                    <button type="button" class="btn btn-border-blue draft" data-dismiss="modal">
                        Batal
                    </button>
                    <button onclick="validate()" type="button" class="btn btn-primary">
                        Submit
                    </button>
                </div>
            </div> <!--/.modal-content -->
        </div><!-- /.modal-dialog -->
    </div>
    <!-- END MODAL ADD -->
    <script>
        var modalFunction = function () {
            $('#modalAppDistrict')
                    .bootstrapValidator({
                        feedbackIcons: {
                            valid: 'glyphicon glyphicon-ok',
                            invalid: 'glyphicon glyphicon-remove',
                            validating: 'glyphicon glyphicon-refresh'
                        },
                        fields: {
                            appMstDistrictName: {
                                validators: {
                                    notEmpty: {
                                    }
                                }
                            },
                            appMstCityId: {
                                validators: {
                                    notEmpty: {
                                    }
                                }
                            },
                            appMstProvinceId: {
                                validators: {
                                    notEmpty: {
                                    }
                                }
                            },
                            status: {
                                validators: {
                                    notEmpty: {
                                    }
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
            $('#modalAppDistrict').bootstrapValidator('validate');
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
                        document.getElementById("appMstCityId").innerHTML += "<option value='"+obj.appMstCityId+"'>"+obj.appMstCityName+"</opton>";
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
            var data = $("#formDistrict").serializeJSON();
            var dataString = JSON.stringify(data);
            var idData = $("#appMstDistrictId").val();
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
                'url': '/gov/app/district/saveOrUpdate/' + is,
                'data': dataString,
                'dataType': 'json',
                success: function (hasil) {
                    if (hasil.status) {
                        $(".loading").hide();
                        $('#modalAppDistrict').modal('toggle');
                        otable.ajax.reload(null, false);
                        showSmallInfo("Informasi", "Data berhasil disimpan", 5000);
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


</script>
<script src="${pageContext.request.contextPath}/resources/custom/js/portal/common.js"></script>