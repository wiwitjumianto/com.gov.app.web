<%-- 
  agung.abdurohman@bni.co.id
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

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
            "sDom": "<'dt-toolbar'<'col-xs-12 col-sm-6'f><'col-sm-6 col-xs-12 hidden-xs'>>" +
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
    });



    loadScript('${pageContext.request.contextPath}/resources/assets/js/plugin/datatables/jquery.dataTables.min.js',
            loadScript("${pageContext.request.contextPath}/resources/assets/js/plugin/datatables/dataTables.bootstrap.min.js", pageFunction));

</script>
<%--BODY CONTENT GOES HERE, WILL BE PRINTED BETWEEN DIV ID="CONTENT TAG--%>
<section id="widget-grid" class="">
    <div class="row">
        <article class="col-xs-12 col-sm-12 col-md-12 col-lg-12">
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
                        </div>
                    </div>
                </div>
            </article>
            <article class="col-xs-12 col-sm-12 col-md-12 col-lg-12">
                <div class="jarviswidget jarviswidget-color-whitesmoke" id="wid-id-1" data-widget-colorbutton="false" data-widget-togglebutton="false" data-widget-editbutton="false" data-widget-deletebutton="false">
                    <header>
                        <h2>Laporan Realisasi Bantuan</h2>
                        <ul id="divExport" class="nav nav-tabs pull-right in report-export">
                            <li class="dropdown pull-right">
                                <a href="javascript:void(0);" class="dropdown-toggle" data-toggle="dropdown" aria-expanded="false">Export <b class="fa fa-share"></b></a>
                                <ul class="dropdown-menu">
                                    <!--                                    <li>
                                                                            <a onclick="processPDFOrg('ou')" href="#s3" data-toggle="tab"><i class="fa fa-file-pdf-o"></i> Save as PDF</a>
                                                                        </li>-->
                                    <li>
                                        <a onclick="processXLSDetailProgram()" href="#s3" data-toggle="tab"><i class="fa fa-file-pdf-o"></i> Save as Excel</a>
                                    </li>
                                </ul>
                            </li>
                        </ul>
                    </header>
                    <div>
                        <div class="jarviswidget-editbox">

                        </div>
                        <div class="widget-body no-padding">                             
                            <table id="tableRealisasi" class="table table-striped table-bordered table-hover" width="100%">
                                <thead>
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
        </article>
    </div>
</section>
<script src="${pageContext.request.contextPath}/resources/custom/js/portal/common.js"></script>


