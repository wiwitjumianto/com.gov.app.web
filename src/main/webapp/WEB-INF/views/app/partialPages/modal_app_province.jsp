<%-- 
    Created on : 10 agustus 2017, 1:21:28 PM
    Author     : Agung Abdurohman
--%>

<%@page import="java.util.ArrayList"%>
<%@page import="java.util.List"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%> 
<!-- MODAL ADD -->
<script src="http://ajax.googleapis.com/ajax/libs/jqueryui/1.10.3/jquery-ui.min.js"></script>
<div data-backdrop="static" class="modal fade in" id="modalAppProvince" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true" style="display: none;">
    <div class="modal-dialog modal-lg-50">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-hidden="true">
                    ×
                </button>
                <h1 style="font-weight: 500;color: #000;" class="modal-title" id="myModalLabel">
                    <c:if test="${provinceList.appMstProvinceId == null}">Tambah </c:if> 
                    <c:if test="${provinceList.appMstProvinceId != null}">Ubah </c:if>    
                        Provinsi
                    <c:if test="${provinceList.appMstProvinceId == null}"> Baru</c:if> 
                    </h1>
                </div>
                <div class="modal-body">
                    <form id="formProvince">
                        <input type="hidden" id="appMstProvinceId" name="appMstProvinceId" value="${provinceList.appMstProvinceId}">
                    <div class="row">
                        <div class="col-md-12">
                            <div class="form-group">
                                <label><b>Nama Provinsi</b></label>
                                <input name="appMstProvinceName" id="appMstProvinceName" type="text"  class="form-control" placeholder="" value="${provinceList.appMstProvinceName}">
                            </div>
                        </div>
                    </div>
                    <div class="row">
                        <div class="col-md-6">
                            <div class="form-group">
                                <label for="status"><b>Status</b></label>
                                <select name="status" id="status" class="form-control">
                                    <option value="1" 
                                            <c:if test="${provinceList.status == 1}">selected</c:if>>Aktif</option>
                                            <option value="0" 
                                            <c:if test="${provinceList.status == 0}">selected</c:if>>Tidak Aktif</option>
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
            $('#modalAppProvince')
                    .bootstrapValidator({
                        feedbackIcons: {
                            valid: 'glyphicon glyphicon-ok',
                            invalid: 'glyphicon glyphicon-remove',
                            validating: 'glyphicon glyphicon-refresh'
                        },
                        fields: {
                            appMstProvinceName: {
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
            $('#modalAppProvince').bootstrapValidator('validate');
        }
        function submit() {
            var data = $("#formProvince").serializeJSON();
            var dataString = JSON.stringify(data);
            var idData = $("#appMstProvinceId").val();
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
                'url': '/gov/app/province/saveOrUpdate/' + is,
                'data': dataString,
                'dataType': 'json',
                success: function (hasil) {
                    if (hasil.status) {
                        $(".loading").hide();
                        $('#modalAppProvince').modal('toggle');
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