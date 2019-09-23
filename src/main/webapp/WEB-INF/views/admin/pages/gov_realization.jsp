<%-- 
  agung.abdurohman@bni.co.id
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<t:genericpage>
    <jsp:attribute name="pagetitle">
        GOV
    </jsp:attribute>
    <jsp:attribute name="breadcrumb">
        <%--BREAD CRUMB GOES HERE--%>
        <li>Input Realisasi ${dataHeader.govTrxProgrammHeaderName}</li>
        </jsp:attribute>
        <jsp:attribute name="custom_js">
            <%--CUSTOM SCRIPT GOES HERE, WILL BE PRINTED IN PAGE RELATED AREA IN SMARTADMIN TEMPLATE--%>
        <script src="${pageContext.request.contextPath}/resources/custom/js/portal/common.js"></script>
        <script src="${pageContext.request.contextPath}/resources/assets/js/plugin/bootstrapvalidator/bootstrapValidator.min.js"></script>
        <script> if (!window.jQuery) {
                document.write('<script src="${pageContext.request.contextPath}/resources/assets/js/libs/jquery-2.1.1.min.js"><\/script>');}</script>
        <style>
/*            .dataTables_info {
                float: right;
                margin-right: -620px;
                margin-top: 30px;
            }*/
            #addButton {
                float: right;
                margin: 5px

            }
        </style>
        <script type="text/javascript">
            var otable;
            $(document).ready(function () {

                $('.datepicker')
                        .datepicker({
                            autoclose: true,
                            format: 'dd/mm/yyyy'
                        })
                        .on('changeDate', function (e) {
                            // Revalidate the date field
                            $('#bootstrap-vertcial-wizard').bootstrapValidator('revalidateField', 'kpiTrxPeriodEndDate');
                            $('#bootstrap-vertcial-wizard').bootstrapValidator('revalidateField', 'kpiTrxPeriodStartDate');
                        });
                var breakpointDefinition = {
                    tablet: 1024,
                    phone: 480
                };
                pageSetUp();
                var responsiveHelper_dt_basic = undefined;
                var responsiveHelper_datatable_fixed_column = undefined;
                var responsiveHelper_datatable_col_reorder = undefined;
                var responsiveHelper_datatable_tabletools = undefined;

                otable = $('#tableRealisasi').DataTable({
                    "pagingType": "full_numbers",
                    "bPaginate": true,
                    "lengthMenu": [[10, 20, 30, 50, 100], [10, 20, 30, 50, 100]],
                    "sDom": "<'dt-toolbar'<'col-xs-12 col-sm-6'f><'col-sm-6 col-xs-12 hidden-xs'l>>" +
                            "t" +
                            "<'dt-toolbar-footer'<'col-sm-6 col-xs-12 hidden-xs'><'col-xs-12 col-sm-6'p>i>",
                    "autoWidth": true,
                    "oLanguage": {
                        "sInfo": "Menampilkan _START_ sampai _END_ dari _TOTAL_ entri",
                        "sEmptyTable": "Belum ada data",
                        "sSearch": '<span class="input-group-addon"><i class="glyphicon glyphicon-search"></i></span>'
                    },
                    language: {
                        searchPlaceholder: "Search records"
                    },
                    "preDrawCallback": function () {
                        // Initialize the responsive datatables helper once.
                        if (!responsiveHelper_dt_basic) {
                            responsiveHelper_dt_basic = new ResponsiveDatatablesHelper($('#tableRealisasi'), breakpointDefinition);
                        }
                    },
                    "rowCallback": function (nRow) {
                        responsiveHelper_dt_basic.createExpandIcon(nRow);
                    },
                    "drawCallback": function (oSettings) {
                        responsiveHelper_dt_basic.respond();
                    }

                });
                $("#tableRealisasi thead th input[type=text]").on('keyup change', function () {
                    otable
                            .column($(this).parent().index() + ':visible')
                            .search(this.value)
                            .draw();
                });
            });

            function inputRealisasi(headerId) {
                var json = {
                    "headerId": headerId
                };
                $.ajax({
                    cache: false,
                    type: "POST",
                    url: "/gov/realization/modal",
                    data: JSON.stringify(json),
                    contentType: 'application/json',
                    beforeSend: function (xhr) {
                        $(".loading").show();
                    },
                    success: function (data) {
                        $(".loading").hide();
                        $('#modal').html(data);
                        $('#modalGovRealization').modal('toggle');
                    },
                    error: function () {
                        $(".loading").hide();

                    },
                    complete: function () {
                        $(".loading").hide();
                    }
                });
            }
            function viewDetail(villageId) {
                var data = {
                    "villageId": villageId
                };
                $.ajax({
                    cache: false,
                    type: "GET",
                    url: "/gov/realization/viewdetail/" + villageId + "/" +${dataHeader.govTrxProgrammHeaderId},
                    contentType: 'application/json',
                    success: function (data) {
                        window.location = "/gov/realization/viewdetail/village/" + data + "/" +${dataHeader.govTrxProgrammHeaderId};
                    }
                });

            }
            function deleteProgrammHeader(govTrxProgrammHeaderId) {
                $.SmartMessageBox({
                    title: "Konfirmasi",
                    content: "Apakah anda yakin ingin menghapus data ?",
                    icon: "fa fa-thumbs-o-up",
                    buttons: '[Tidak][Ya]'
                }, function (ButtonPressed) {
                    if (ButtonPressed === "Ya") {
                        var data = {
                            "govTrxProgrammHeaderId": govTrxProgrammHeaderId
                        };
                        $.ajax({
                            headers: {
                                'Accept': 'application/json',
                                'Content-Type': 'application/json'
                            },
                            'type': 'POST',
                            'url': '/gov/programm/header/saveOrUpdate/' + 3,
                            'data': JSON.stringify(data),
                            'dataType': 'json',
                            success: function (hasil) {
                                if (hasil.status) {
                                    $(".loading").hide();
                                    otable.ajax.reload(null, false);
                                    showSmallInfo("Informasi", "Data berhasil dihapus", 5000);
                                } else {
                                    $(".loading").hide();
                                    showSmallError("Kesalahan", "Data gagal dihapus", 5000);
                                }
                            }
                        });

                    }
                });
            }
            loadScript('${pageContext.request.contextPath}/resources/assets/js/plugin/datatables/jquery.dataTables.min.js',
                    loadScript("${pageContext.request.contextPath}/resources/assets/js/plugin/datatables/dataTables.bootstrap.min.js", pageFunction));
            function cekVillage(x) {
                var data = new Array();
            <c:forEach items="${listDetail}" var="dataDet" varStatus="loop">
                data.push('<c:out value="${dataDet.govTrxProgrammVillageId}"/>');
            </c:forEach>
                var status = 0;
                for (var i = 0; i < data.length; i++) {
                    if (data[i] == x) {
                        status = 1;
                        break;
                    }
                }
                if (status == 0) {
                    $("#govRealizationReceiverName").prop("disabled", true);
                    $("#govRealizationReceiverNik").prop("disabled", true);
                    $("#govRealizationReceiverGender").prop("disabled", true);
                    $("#govRealizationPkPb").prop("disabled", true);
                    $("#govRealizationReceiverAddress").prop("disabled", true);
                    $("#govRealizationValue").prop("disabled", true);
                    showSmallError("Kesalahan", "Desa Tidak Ada Dalam Daftar Program. Silahkan Pilih Desa Yang Berada Dalam Program", 5000);
                } else {
                    $("#govRealizationReceiverName").prop("disabled", false);
                    $("#govRealizationReceiverNik").prop("disabled", false);
                    $("#govRealizationReceiverGender").prop("disabled", false);
                    $("#govRealizationPkPb").prop("disabled", false);
                    $("#govRealizationReceiverAddress").prop("disabled", false);
                    $("#govRealizationValue").prop("disabled", false);
                }
            }
        </script>
    </jsp:attribute>
    <jsp:attribute name="bodycontent">
        <%--BODY CONTENT GOES HERE, WILL BE PRINTED BETWEEN DIV ID="CONTENT TAG--%>
        <section id="widget-grid" class="">
            <div class="row">
                <article class="col-xs-12 col-sm-12 col-md-12 col-lg-12">
                    <div class="col-lg-3 col-md-3 col-sm-3">
                        <div class="well-custom well-light no-padding">
                            <div class="body-card blue-darken-2 lighten-1 text-white text-center border-rounded-0-top" style="height:120px; ">
                                <h6  style ="font-size:17px;">
                                    Jumlah Bantuan Desa
                                </h6>
                                <h5  style="padding-top: 3px;font-size:45px;">
                                    <c:set var="totaldd" value="${0}"/>
                                    <c:forEach items="${listDetail}" var="dataDet" varStatus="loop">
                                        <c:forEach items="${listVillage}" var="dataVil">
                                            <c:if test="${dataDet.govTrxProgrammVillageId == dataVil.appMstVillageId}">
                                                <c:set var="totaldd" value="${totaldd+1}" />
                                            </c:if>
                                        </c:forEach>
                                    </c:forEach>
                                    <c:out value="${totaldd}"/>
                                </h5>
                            </div>
                        </div>
                    </div>
                    <div class="col-lg-3 col-md-3 col-sm-3">
                        <div class="well-custom well-light no-padding">
                            <div class="body-card blue-darken-2 lighten-1 text-white text-center border-rounded-0-top" style="height:120px;">
                                <h6  style ="font-size:17px;">
                                    Jumlah Realisasi Desa
                                </h6>
                                <h5  style="padding-top: 3px; font-size:45px;">
                                    <c:set var="totald" value="${0}"/>
                                    <c:forEach var="article" items="${listDetail}">
                                        <c:if test="${article.govTrxProgrammUnitRealization !=0}">
                                            <c:set var="totald" value="${totald+1}" />
                                        </c:if>
                                    </c:forEach>
                                    <c:out value="${totald}"/>
                                </h5>
                            </div>
                        </div>
                    </div>
                    <div class="col-lg-3 col-md-3 col-sm-3">
                        <div class="well-custom well-light no-padding">
                            <div class="body-card blue-darken-2 lighten-1 text-white text-center border-rounded-0-top" style="height:120px;">
                                <h6  style ="font-size:17px;">
                                    Jumlah Bantuan Unit
                                </h6>
                                <h5  style="padding-top: 3px; font-size:45px;">
                                    <c:set var="total" value="${0}"/>
                                    <c:forEach var="article" items="${listDetail}">
                                        <c:set var="total" value="${total + article.govTrxProgrammUnit}" />
                                    </c:forEach>
                                    <c:out value="${total}"/>
                                </h5>
                            </div>
                        </div>
                    </div>
                    <div class="col-lg-3 col-md-3 col-sm-3">
                        <div class="well-custom well-light no-padding">
                            <div class="body-card blue-darken-2 lighten-1 text-white text-center border-rounded-0-top" style="height:120px;">
                                <h6  style ="font-size:17px;">
                                    Jumlah Realisasi Units
                                </h6>
                                <h5  style="padding-top: 3px; font-size:45px;">
                                    <c:set var="totala" value="${0}"/>
                                    <c:forEach var="article" items="${listDetail}">
                                        <c:set var="totala" value="${totala + article.govTrxProgrammUnitRealization}" />
                                    </c:forEach>
                                    <c:out value="${totala}"/>
                                </h5>
                            </div
                        </div>
                    </div>
                </article>
                <article class="col-xs-12 col-sm-12 col-md-12 col-lg-12">
                    <button class="btn btn-primary" id="addButton" onclick="inputRealisasi('${dataHeader.govTrxProgrammHeaderId}')" <c:if test="${empty listDetail}">disabled=""</c:if>>
                            <span class="fa fa-plus"></span>&nbsp;Input Realisasi
                        </button>
                    </article>
                    <article class="col-xs-12 col-sm-12 col-md-12 col-lg-12">
                        <div class="jarviswidget jarviswidget-color-whitesmoke" id="wid-id-1" data-widget-colorbutton="false" data-widget-togglebutton="false" data-widget-editbutton="false" data-widget-deletebutton="false">
                            <header>
                                <h2>Informasi Realisasi ${dataHeader.govTrxProgrammHeaderName}</h2>
                        </header>
                        <div>
                            <div class="jarviswidget-editbox">

                            </div>
                            <div class="widget-body no-padding">                             
                                <table id="tableRealisasi" class="table table-striped table-bordered table-hover" width="100%">
                                    <thead>
                                        <tr>
                                            <th style="border-right: none !important;">
                                            </th>
                                            <th class="hasinput">
                                                <input type="text" class="form-control" placeholder="Filter " />
                                            </th>
                                            <th class="hasinput">
                                                <input type="text" class="form-control" placeholder="Filter " />
                                            </th>
                                            <th class="hasinput">
                                                <input type="text" class="form-control" placeholder="Filter " />
                                            </th>
                                            <th class="hasinput">
                                                <input type="text" class="form-control" placeholder="Filter " />
                                            </th>
                                            <th style="border-right: none !important;">
                                            </th>
                                            <th style="border-right: none !important;">
                                            </th>
                                            <th style="border-right: none !important;">
                                            </th>
                                            <th style="border-right: none !important;">
                                            </th>
                                            <th style="border-right: none !important;">
                                            </th>
                                            <th style="border-right: none !important;">
                                            </th>
                                            <th style="border-right: none !important;">
                                            </th>
                                        </tr>
                                        <tr>
                                            <th width="2%">${datbelakang}</th>
                                            <th data-class="expand">Provinsi</th>
                                            <th data-class="expand">Kabupaten/Kota</th>
                                            <th data-class="expand">Kecamatan</th>
                                            <th data-class="expand">Desa/kelurahan</th>
                                            <th width="10%">Jumlah Usulan Unit</th>
                                            <th width="10%">Jumlah Usulan Unit Terealisasi</th>
                                            <th width="10%">Jumlah Usulan Unit Belum Terealisasi</th>
                                            <th width="10%">Jumlah PK</th>
                                            <th width="10%">Jumlah PB</th>
                                            <th data-class="expand">Instansi Pengusul</th>
                                            <th data-class="expand">Nama Pengusul</th>
                                            <th data-class="expand">Tanggal Mengusulkan</th>
                                            <th data-class="expand">Nomor Surat</th>
                                            <th width="7%"><center>Aksi</center></th>
                                    </tr>
                                    </thead>
                                    <tbody>
                                        <c:forEach items="${listDetail}" var="dataDet" varStatus="loop">
                                            <c:forEach items="${listVillage}" var="dataVil">
                                                <c:if test="${dataDet.govTrxProgrammVillageId == dataVil.appMstVillageId}">
                                                    <tr>
                                                        <td>${loop.index+1}</td>
                                                        <td>${dataVil.appMstProvinceName}</td>
                                                        <td>${dataVil.appMstCityName}</td>
                                                        <td>${dataVil.appMstDistrictName}</td>
                                                        <td>${dataVil.appMstVillageName}</td>
                                                        <td>${dataDet.govTrxProgrammUnit}</td>
                                                        <td>${dataDet.govTrxProgrammUnitRealization}</td>
                                                        <td>${dataDet.govTrxProgrammUnit - dataDet.govTrxProgrammUnitRealization}</td>
                                                        <td>${dataDet.govTrxSumPk}</td>
                                                        <td>${dataDet.govTrxSumPb}</td>
                                                        <td>${dataDet.proposerName}</td>
                                                        <td>${dataDet.govTrxProgrammDetailIssuer}</td>
                                                        <td>${dataDet.datePropos}</td>
                                                        <td>${dataDet.letterNumber}</td>
                                                        <td><a class="btn btn-prev" href="/gov/realization/viewdetail/${dataVil.appMstVillageId}/${dataHeader.govTrxProgrammHeaderId}"><span class='fa fa-search'></span> LihatDetail</a></td>
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
        </section>
        <div id="modal"></div>
    </jsp:attribute>
</t:genericpage>
<script src="${pageContext.request.contextPath}/resources/custom/js/portal/common.js"></script>


