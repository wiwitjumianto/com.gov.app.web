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
            "sDom": "<'dt-toolbar'<'col-xs-12 col-sm-6'><'col-sm-6 col-xs-12 hidden-xs'>r>" +
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



    loadScript('${pageContext.request.contextPath}/resources/assets/js/plugin/datatables/jquery.dataTables.min.js',
            loadScript("${pageContext.request.contextPath}/resources/assets/js/plugin/datatables/dataTables.bootstrap.min.js", pageFunction));

</script>
<%--BODY CONTENT GOES HERE, WILL BE PRINTED BETWEEN DIV ID="CONTENT TAG--%>
<section id="widget-grid" class="">
    <div class="row">
        <article class="col-xs-12 col-sm-12 col-md-12 col-lg-12">
            <article class="col-xs-12 col-sm-12 col-md-12 col-lg-12">
                <div class="jarviswidget jarviswidget-color-whitesmoke" id="wid-id-1" data-widget-colorbutton="false" data-widget-togglebutton="false" data-widget-editbutton="false" data-widget-deletebutton="false">
                    <header>
                        <h2>Laporan Detail Penerima Bantuan ${dataHeader.govTrxProgrammHeaderName}</h2>
                        <ul id="divExport" class="nav nav-tabs pull-right in report-export">
                            <li class="dropdown pull-right">
                                <a href="javascript:void(0);" class="dropdown-toggle" data-toggle="dropdown" aria-expanded="false">Export <b class="fa fa-share"></b></a>
                                <ul class="dropdown-menu">
                                    <!--                                    <li>
                                                                            <a onclick="processPDFOrg('ou')" href="#s3" data-toggle="tab"><i class="fa fa-file-pdf-o"></i> Save as PDF</a>
                                                                        </li>-->
                                    <li>
                                        <a onclick="processXLSReceiver('${dataHeader.govTrxProgrammHeaderId}')" href="#s3" data-toggle="tab"><i class="fa fa-file-pdf-o"></i> Save as Excel</a>
                                    </li>
                                </ul>
                            </li>
                        </ul>
                    </header>
                    <div>
                        <div class="jarviswidget-editbox">

                        </div>
                        <div class="widget-body no-padding">                             
                            <table id="tableRealisasiDetail" class="table table-striped table-bordered table-hover" width="100%">
                                <thead>
                                    <tr>
                                        <th width="2%">NO</th>
                                        <th data-class="expand">Provinsi</th>
                                        <th data-class="expand">Kabupaten/Kota</th>
                                        <th data-class="expand">Kecamatan</th>
                                        <th data-class="expand">Desa/Kelurahan</th>
                                        <th data-class="expand">Nama Penerima</th>
                                        <th width="12%">NIK</th>
                                        <th width="5%">Jenis Kelamin</th>
                                        <th width="5%">PK/PB</th>
                                        <th data-class="expand">Alamat</th>
                                        <th width="10%">Nilai Bantuan</th>
                                    </tr>
                                </thead>
                                <tbody>
                                    <c:forEach items="${listVillage}" var="dataVil">
                                        <c:forEach items="${listReceiver}" var="dataDet" varStatus="loop">
                                            <c:if test="${dataVil.appMstVillageId == dataDet.govTrxRealizationVillageId}">
                                                <tr>
                                                    <td>${loop.index+1}</td>
                                                    <td>${dataVil.appMstProvinceName}</td>
                                                    <td>${dataVil.appMstCityName}</td>
                                                    <td>${dataVil.appMstDistrictName}</td>
                                                    <td>${dataVil.appMstVillageName}</td>
                                                    <td>${dataDet.govTrxRealizationReceiverName}</td>
                                                    <td>${dataDet.govTrxRealizationReceiverNik}</td>
                                                    <td>
                                                        <c:if test="${dataDet.govTrxRealizationReceiverGender==1}">Laki-Laki</c:if>
                                                        <c:if test="${dataDet.govTrxRealizationReceiverGender==0}">Perempuan</c:if>
                                                        </td>
                                                        <td><c:if test="${dataDet.govTrxRealizationPkPb==1}">PK</c:if>
                                                        <c:if test="${dataDet.govTrxRealizationPkPb==0}">PB</c:if>
                                                        </td>
                                                        <td>${dataDet.govTrxRealizationReceiverAddress}</td>
                                                    <td>${dataDet.govTrxRealizationValue}</td>
                                                </tr>
                                            </c:if>
                                        </c:forEach>
                                    </c:forEach>
                                    <c:if test="${empty listReceiver}">
                                        <tr>
                                            <td colspan="11" style="text-align: center">Data Tidak Ditemukan</td>
                                        </tr>
                                    </c:if>
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


