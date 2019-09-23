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
<div data-backdrop="static" class="modal fade in" id="modalDetRealization" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true" style="display: none;">
    <div class="modal-dialog modal-lg-50">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-hidden="true">
                    ×
                </button>
                <h1 style="font-weight: 500;color: #000;" class="modal-title" id="myModalLabel">
                    Ubah Detail Realisasi Bantuan
                </h1>
            </div>
            <div class="modal-body">
                <form id="formRealization">
                    <input name="govTrxRealizationId" id="govTrxRealizationId" type="text" style="display: none"class=" form-control" 
                           placeholder=""  value="${data.govTrxRealiszationId}"/>
                    <div class="row">
                        <div class="col-md-12">
                            <div class="form-group">
                                <label><b>Nama Penerima Bantuan</b></label>
                                <input name="govRealizationReceiverName" id="govRealizationReceiverName" type="text" 
                                       class=" form-control" placeholder="" value="${data.govTrxRealizationReceiverName}"/>
                            </div>
                        </div>
                    </div>
                    <div class="row">
                        <div class="col-md-6">
                            <div class="form-group">
                                <label><b>Nomor Induk Kependudukan</b></label>
                                <input maxlength="16" minlength="16" name="govRealizationReceiverNik" id="govRealizationReceiverNik" type="number"
                                       class=" form-control" placeholder="" value="${data.govTrxRealizationReceiverNik}"/>
                            </div>
                        </div>
                    </div>
                    <div class="row">
                        <div class="col-md-6">
                            <div class="form-group">
                                <label for="status"><b>Jenis Kelamin</b></label><br/>
                                <select style="width:100%;" name="govRealizationReceiverGender" id="govRealizationReceiverGender" class="form-control sel2">
                                    <option value="1" <c:if test="${data.govTrxRealizationReceiverGender == 1}">selected</c:if>>Laki-Laki</option>
                                    <option value="0" <c:if test="${data.govTrxRealizationReceiverGender == 0}">selected</c:if>>Perempuan</option>
                                    </select>
                                </div>
                            </div>
                        </div>
                        <div class="row">
                            <div class="col-md-6">
                                <div class="form-group">
                                    <label for="status"><b>PK/PB</b></label><br/>
                                    <select style="width:100%;" name="govRealizationPkPb" id="govRealizationPkPb" class="form-control sel2">
                                        <option value="1"  <c:if test="${data.govTrxRealizationPkPb == 1}">selected</c:if>>PK</option>
                                    <option value="0" <c:if test="${data.govTrxRealizationPkPb == 0}">selected</c:if>>PB</option>
                                    </select>
                                </div>
                            </div>
                        </div>
                        <div class="row">
                            <div class="col-md-12">
                                <div class="form-group">
                                    <label><b>Alamat</b></label>
                                    <textarea name="govRealizationReceiverAddress" id="govRealizationReceiverAddress" class=" form-control" 
                                              placeholder="" value="">${data.govTrxRealizationReceiverAddress}</textarea>
                            </div>
                        </div>
                    </div>
                    <div class="row">
                        <div class="col-md-6">
                            <div class="form-group">
                                <label><b>Nilai Bantuan </b></label>
                                <input name="govRealizationValue" id="govRealizationValue" type="number"  class=" form-control" 
                                       placeholder="" value="${data.govTrxRealizationValue}"
                                       />*Dalam RP

                            </div>
                        </div>
                    </div>
                    <div class="row">
                        <div class="col-md-6">
                            <div class="form-group">
                                <label for="status"><b>Status</b></label>
                                <select name="status" id="status" class="form-control">
                                    <option value="1" 
                                            <c:if test="${data.status == 1}">selected</c:if>>Aktif</option>
                                            <option value="0" 
                                            <c:if test="${data.status == 0}">selected</c:if>>Tidak Aktif</option>
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
            $('#modalDetRealization')
                    .bootstrapValidator({
                        excluded: [':disabled'],
                        feedbackIcons: {
                            valid: 'glyphicon glyphicon-ok',
                            invalid: 'glyphicon glyphicon-remove',
                            validating: 'glyphicon glyphicon-refresh'
                        },
                        fields: {
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
            $('#modalDetRealization').bootstrapValidator('validate');
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
                'url': '/gov/realization/saveOrUpdate/2',
                'data': dataString,
                'dataType': 'json',
                success: function (hasil) {
                    if (hasil.status) {
                        $(".loading").hide();
                        if (hasil.message == 0) {
                            showSmallWarning("Peringatan", "NIK Telah Digunakan Dalam Program Bantuan", 5000);
                        } else {

                            $('#modalDetRealization').modal('toggle');
                            window.location.href = "/gov/realization/viewdetail/" +${villageId} + "/" +${headerId};
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