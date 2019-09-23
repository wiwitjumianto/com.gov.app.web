<%-- 
    Created on : 10 agustus 2017, 1:21:28 PM
    Author     : Agung Abdurohman
--%>

<%@page import="java.util.ArrayList"%>
<%@page import="java.util.List"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%> 
<!-- MODAL ADD -->
<script>
    $('.sel2').select2();
</script>
<script type="text/javascript" src="${pageContext.request.contextPath}/resources/custom/js/my.js"></script>
<div data-backdrop="static" class="modal fade in" id="modalGovRealization" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true" style="display: none;">
    <div class="modal-dialog modal-lg-50">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-hidden="true">
                    ×
                </button>
                <h1 style="font-weight: 500;color: #000;" class="modal-title" id="myModalLabel">
                    <c:if test="${listVillage.appMstVillageId == null}">Tambah </c:if> 
                    <c:if test="${listVillage.appMstVillageId != null}">Ubah </c:if>    
                        Realisasi
                    <c:if test="${listVillage.appMstVillageId == null}"> Baru</c:if> 
                    </h1>
                </div>
                <div class="modal-body">
                    <form id="formRealization">
                        <input type="hidden" id="appMstVillageId" name="appMstVillageId" value="${listVillage.appMstVillageId}"/>
                    <input type="hidden" id="govTrxProgrammHeaderId" name="govTrxProgrammHeaderId" value="${headerId}"/>
                    <div class="row">
                        <div class="col-md-6">
                            <div class="form-group">
                                <label><b><i>Provinsi</i></b></label>
                                <select class="form-control sel2" onchange="getCity(this.value)" style="width:100%;" id="appMstProvinceId" name="appMstProvinceId">
                                    <option value=""> -- Pilih Provinsi -- </option>
                                    <c:forEach items="${listProvince}" var="data">
                                        <option  value="${data.appMstProvinceId}">${data.appMstProvinceName}</option>
                                    </c:forEach>
                                </select>
                            </div>
                        </div>
                        <div class="col-md-6">
                            <div class="form-group">
                                <label><b><i>Kota/Kabupaten</i></b></label>
                                <select class="form-control sel2" onchange="getDistrict(this.value)" style="width:100%;" id="appMstCityId" name="appMstCityId">
                                    <option value=""> -- Pilih Kota/Kab -- </option>
                                    <c:forEach items="${listCity}" var="datac">
                                        <option value="${datac.appMstCityId}">${datac.appMstCityName}</option>
                                    </c:forEach>
                                </select>
                            </div>
                        </div>
                    </div>
                    <div class="row">
                        <div class="col-md-6">
                            <div class="form-group">
                                <label><b><i>Kecamatan</i></b></label>
                                <select class="form-control sel2" style="width:100%;" onchange="getVillage(this.value)" id="appMstDistrictId" name="appMstDistrictId">
                                    <option value=""> -- Pilih Kecamatan -- </option>
                                    <c:forEach items="${listDistrict}" var="datac">
                                        <option  value="${datac.appMstDistrictId}">${datac.appMstDistrictName}</option>
                                    </c:forEach>
                                </select>
                            </div>
                        </div>
                        <div class="col-md-6">
                            <div class="form-group">
                                <label><b><i>Desa/Kelurahan</i></b></label>
                                <select onchange="cekVillage(this.value)" class="form-control sel2" style="width:100%;" id="govTrxProgrammVillageId" name="govTrxProgrammVillageId">
                                    <option value=""> -- Pilih Desa/Kelurahan -- </option>
                                    <c:forEach items="${listVillageByDistrict}" var="datac">
                                        <option  value="${datac.appMstVillageId}">${datac.appMstVillageName}</option>
                                    </c:forEach>
                                </select>
                            </div>
                        </div>
                    </div>
                    <hr>
                    <div class="row">
                        <div class="col-md-12">
                            <div class="form-group">
                                <label><b>Nama Penerima Bantuan</b></label>
                                <input name="govRealizationReceiverName" id="govRealizationReceiverName" type="text"  class=" form-control" placeholder="">
                            </div>
                        </div>
                    </div>
                    <div class="row">
                        <div class="col-md-6">
                            <div class="form-group">
                                <label><b>Nomor Induk Kependudukan</b></label>
                                <input maxlength="16" name="govRealizationReceiverNik" id="govRealizationReceiverNik" type="number"  class=" form-control" placeholder="">
                            </div>
                        </div>
                    </div>
                    <div class="row">
                        <div class="col-md-6">
                            <div class="form-group">
                                <label for="status"><b>Jenis Kelamin</b></label><br/>
                                <select style="width:100%;" name="govRealizationReceiverGender" id="govRealizationReceiverGender" class="form-control sel2">
                                    <option value="1">Laki-Laki</option>
                                    <option value="0">Perempuan</option>
                                </select>
                            </div>
                        </div>
                    </div>
                    <div class="row">
                        <div class="col-md-6">
                            <div class="form-group">
                                <label for="status"><b>PK/PB</b></label><br/>
                                <select style="width:100%;" name="govRealizationPkPb" id="govRealizationPkPb" class="form-control sel2">
                                    <option value="1">PK</option>
                                    <option value="0">PB</option>
                                </select>
                            </div>
                        </div>
                    </div>
                    <div class="row">
                        <div class="col-md-12">
                            <div class="form-group">
                                <label><b>Alamat</b></label>
                                <textarea name="govRealizationReceiverAddress" id="govRealizationReceiverAddress" class=" form-control" placeholder=""></textarea>
                            </div>
                        </div>
                    </div>
                    <div class="row">
                        <div class="col-md-6">
                            <div class="form-group">
                                <label><b>Nilai Bantuan </b></label>
                                <input name="govRealizationValue" id="govRealizationValue" type="number"  class=" form-control" 
                                       placeholder=""/>*Dalam RP

                            </div>
                        </div>
                    </div>
                    <div class="row">
                        <div class="col-md-6">
                            <div class="form-group">
                                <label for="status"><b>Status</b></label>
                                <select name="status" id="status" class="form-control">
                                    <option value="1" 
                                            <c:if test="${listVillage.status == 1}">selected</c:if>>Aktif</option>
                                            <option value="0" 
                                            <c:if test="${listVillage.status == 0}">selected</c:if>>Tidak Aktif</option>
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
            $('#modalGovRealization')
                    .bootstrapValidator({
                        excluded: [':disabled'],
                        feedbackIcons: {
                            valid: 'glyphicon glyphicon-ok',
                            invalid: 'glyphicon glyphicon-remove',
                            validating: 'glyphicon glyphicon-refresh'
                        },
                        fields: {
                            appMstProvinceId: {
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
                            appMstDistrictId: {
                                validators: {
                                    notEmpty: {
                                    }
                                }
                            },
                            govTrxProgrammVillageId: {
                                validators: {
                                    notEmpty: {
                                    }
                                }
                            },
                            govRealizationReceiverName: {
                                validators: {
                                    notEmpty: {
                                    }
                                }
                            },
                            govRealizationReceiverNik: {
                                validators: {
                                    notEmpty: {
                                    }
                                }
                            },
                            govRealizationReceiverGender: {
                                validators: {
                                    notEmpty: {
                                    }
                                }
                            },
                            govRealizationReceiverAddress: {
                                validators: {
                                    notEmpty: {
                                    }
                                }
                            },
                            govRealizationValue: {
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
            $('#modalGovRealization').bootstrapValidator('validate');
        }
        function getVillage(districtId) {
            var data = {
                "appMstDistrictId": districtId
            };
            $.ajax({
                cache: false,
                type: "POST",
                url: "/gov/app/village/getvillagebydistrict",
                data: JSON.stringify(data),
                contentType: 'application/json',
                beforeSend: function (xhr) {

                },
                success: function (data) {
                    document.getElementById("govTrxProgrammVillageId").innerHTML = "<option value=''> -- Pilih Desa/kelurahan -- </option>";
                    var arr = data.listVillageByDistrict;
                    for (var i = 0; i < arr.length; i++) {
                        var obj = arr[i];
                        document.getElementById("govTrxProgrammVillageId").innerHTML += "<option value='" + obj.appMstVillageId + "'>" + obj.appMstVillageName + "</opton>";
                    }
                },
                error: function () {
                    alert("error");
                },
                complete: function () {
                }
            });
        }
        function getDistrict(cityId) {
            var data = {
                "appMstCityId": cityId
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
            var data = $("#formRealization").serializeJSON();
            var dataString = JSON.stringify(data);
            $.ajax({
                headers: {
                    'Accept': 'application/json',
                    'Content-Type': 'application/json'
                },
                'type': 'POST',
                'url': '/gov/realization/saveOrUpdate/1',
                'data': dataString,
                'dataType': 'json',
                success: function (hasil) {
                    console.log(JSON.stringify(hasil));
                    $(".loading").hide();
                    if (hasil.status) {
                        if (hasil.message == 0) {
                            showSmallWarning("Peringatan", "NIK Telah Digunakan Dalam Program Bantuan", 5000);
                        } else if (hasil.message == 1) {
                            showSmallWarning("Peringatan", "Unit Telah Terealisasi Seluruhnya", 5000);
                        } else {

                            $('#modalGovRealization').modal('toggle');
                            window.location.href = "/gov/realization/list";
                            showSmallInfo("Informasi", "Data berhasil disimpan", 5000);
                        }

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