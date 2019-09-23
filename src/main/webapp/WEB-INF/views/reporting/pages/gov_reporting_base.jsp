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
        ;<li><span class="fa fa-bar-chart"></span> &nbsp;Reporting </li>
        </jsp:attribute>
        <jsp:attribute name="custom_js">
            <%--CUSTOM SCRIPT GOES HERE, WILL BE PRINTED IN PAGE RELATED AREA IN SMARTADMIN TEMPLATE--%>
        <script src="${pageContext.request.contextPath}/resources/custom/js/portal/common.js"></script>
        <script src="${pageContext.request.contextPath}/resources/assets/js/plugin/bootstrapvalidator/bootstrapValidator.min.js"></script>
        <script> if (!window.jQuery) {
                document.write('<script src="${pageContext.request.contextPath}/resources/assets/js/libs/jquery-2.1.1.min.js"><\/script>');}</script>
        <style>
            .dataTables_info {
                float: right;
                margin-right: -620px;
                margin-top: 30px;
            }
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
                    "lengthMenu": [[10, 20, 30], [10, 20, 30]],
                    "sDom": "<'dt-toolbar'<'col-xs-12 col-sm-6'f><'col-sm-6 col-xs-12 hidden-xs'>r>" +
                            "t" +
                            "<'dt-toolbar-footer'<'col-sm-6 col-xs-12 hidden-xs'i><'col-xs-12 col-sm-6'p>>",
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
            });

            function inputRealisasi() {
                var json;
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
                    url: "/gov/realization/viewdetail/" + villageId,
                    contentType: 'application/json',
                    success: function (data) {
                        window.location = "/gov/realization/viewdetail/village/" + data;
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
            <div class="row" id="reportingFilter">
                <article class="col-xs-12 col-sm-12 col-md-12 col-lg-12">
                    <div class="jarviswidget jarviswidget-color-whitesmoke" id="wid-id-1" data-widget-colorbutton="false" data-widget-togglebutton="false" data-widget-editbutton="false" data-widget-deletebutton="false" data-widget-sortable="false">
                        <header role="heading" class="border-rounded-5-top">
                            <!--                            <h2 class="page-title txt-color-blueDark">
                                                            Reporting 
                                                        </h2>-->
                            <span class="jarviswidget-loader"><i class="fa fa-refresh fa-spin"></i></span>
                        </header>
                        <div role="content" class="border-rounded-5-bottom">
                            <div class="widget-body">
                                <form>
                                    <section class="col-lg-4">
                                        <label style="font-size:16px; font-weight:400;" class="select text-blue-material">Pilih Jenis Report</label>
                                        <select id="reportType" name="reportType" class="form-control" style="display: inline-block;"
                                                onchange="changeFormType(this.value)">
                                            <option value=""> - Pilih Report -</option>
                                            <option value="1">Laporan Program</option>
                                            <option value="2">Laoran Penerima Realisasi</option>
                                            <option value="3">Laporan Jumlah Realisasi Program</option>
                                        </select>
                                    </section>
                                </form>
                                <br/>
                                <br/>

                                <div id="partial_page_header" style="margin-bottom:10px"></div>
                                <div id="partial_page_data_location_level" style="margin-bottom:10px"></div>
                                <div id="partial_page_data_location" style="margin-bottom:10px"></div>
                            </div>
                            <footer id="processButton" style="padding:10px" class="pull-right" row>
                                <button type="button" id="bottonprocess" disabled="true" onclick="processReport()" data-placement='bottom' class="danger btn btn-primary" style="border-radius:5px; float: left; padding:7px 20px; margin-bottom: 0px; width: 100%;">
                                    Tampilkan
                                </button>
                            </footer>
                        </div>
                    </div>
                </article>
            </div>
            <div id="tableForm">
            </div>
        </section>
    </jsp:attribute>
</t:genericpage>
<script>
    function processReport() {
        var reportType = $('#reportType').val();
        var programmHeader = $('#govTrxProgrammHeaderId').val();
        if (reportType == 1) {
            $('.loading').show();
            $.ajax({
                cache: false,
                type: "GET",
                url: "/gov/reporting/gettablerealisasi/" + programmHeader,
                contentType: 'application/json',
                success: function (data) {
                    $('.loading').hide();
                    $('#tableForm').html("");
                    $('#tableForm').html(data);
                }
            });
        } else if (reportType == 2) {
            $('.loading').show();
            $.ajax({
                cache: false,
                type: "GET",
                url: "/gov/reporting/gettablereceiver/" + programmHeader,
                contentType: 'application/json',
                success: function (data) {
                    $('.loading').hide();
                    $('#tableForm').html("");
                    $('#tableForm').html(data);
                }
            });

        } else if (reportType == 3) {
            $('.loading').show();
            $.ajax({
                cache: false,
                type: "GET",
                url: "/gov/reporting/getsumprovince/" + programmHeader,
                contentType: 'application/json',
                success: function (data) {
                    $('.loading').hide();
                    $('#tableForm').html("");
                    $('#tableForm').html(data);
                }
            });
        }
    }

    function processXLSDetailProgram(type) {
        var programmHeader = $('#govTrxProgrammHeaderId').val();
        window.location.href = "/gov/reporting/download/programdetail/" + programmHeader;
    }

    function processXLSReceiver(headerId) {
        window.location.href = "/gov/reporting/download/receiver/" + headerId;
    }
    
    function processXLSSumPerProvince(headerId){
        window.location.href = "/gov/reporting/download/sumprov/" + headerId;
    }
    
    function changeFormType(reportType) {
        $('#tableForm').html("");
        $('.loading').show();
        $.ajax({
            cache: false,
            type: "GET",
            url: "/gov/reporting/getpartialpage/" + reportType,
            contentType: 'application/json',
            success: function (data) {
                $('.loading').hide();
//                if (reportType == 1) {
//                $('#partial_page_data_location').html("");
                $('#partial_page_header').html(data);
//                }
//                if (reportType == 2) {
//                    $('#partial_page_header').html(data);
//
//                }

            }
        });
        $('.loading').hide();
    }
    function activateButton() {
        $("#bottonprocess").prop("disabled", false);
        $('#tableForm').html("");
    }
</script>
<script src="${pageContext.request.contextPath}/resources/custom/js/portal/common.js"></script>


