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
        <li>Input Realisasi / Detail Program Desa ${villageName}</li>
        </jsp:attribute>
        <jsp:attribute name="custom_js">
            <%--CUSTOM SCRIPT GOES HERE, WILL BE PRINTED IN PAGE RELATED AREA IN SMARTADMIN TEMPLATE--%>
        <script src="${pageContext.request.contextPath}/resources/custom/js/portal/common.js"></script>
        <script src="${pageContext.request.contextPath}/resources/assets/js/plugin/bootstrapvalidator/bootstrapValidator.min.js"></script>
        <script> if (!window.jQuery) {
                document.write('<script src="${pageContext.request.contextPath}/resources/assets/js/libs/jquery-2.1.1.min.js"><\/script>');}</script>
        <script type="text/javascript">
            var otable;
            $(document).ready(function () {
                var breakpointDefinition = {
                    tablet: 1024,
                    phone: 480
                };
                pageSetUp();
                var responsiveHelper_dt_basic = undefined;
                var responsiveHelper_datatable_fixed_column = undefined;
                var responsiveHelper_datatable_col_reorder = undefined;
                var responsiveHelper_datatable_tabletools = undefined;

                otable = $('#tableRealisasiDetail').DataTable({
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
                            responsiveHelper_dt_basic = new ResponsiveDatatablesHelper($('#tableRealisasiDetail'), breakpointDefinition);
                        }
                    },
                    "rowCallback": function (nRow) {
                        responsiveHelper_dt_basic.createExpandIcon(nRow);
                    },
                    "drawCallback": function (oSettings) {
                        responsiveHelper_dt_basic.respond();
                    }

                });
                $("#tableRealisasiDetail thead th input[type=text]").on('keyup change', function () {
                    otable.column($(this).parent().index() + ':visible')
                            .search(this.value)
                            .draw();
                });
            });

            function editRealisasi(realizationId) {
                var json = {
                    "headerId": ${headerId},
                    "villageId": ${villageId}
                };
                $.ajax({
                    cache: false,
                    type: "POST",
                    url: "/gov/realization/modaledit/" + realizationId,
                    data: JSON.stringify(json),
                    contentType: 'application/json',
                    beforeSend: function (xhr) {
                        $(".loading").show();
                    },
                    success: function (data) {

                        $(".loading").hide();
                        $('#modal').html(data);
                        $('#modalDetRealization').modal('toggle');
                    },
                    error: function () {
                        $(".loading").hide();

                    },
                    complete: function () {
                        $(".loading").hide();
                    }
                });
            }
            loadScript('${pageContext.request.contextPath}/resources/assets/js/plugin/datatables/jquery.dataTables.min.js',
                    loadScript("${pageContext.request.contextPath}/resources/assets/js/plugin/datatables/dataTables.bootstrap.min.js", pageFunction));
        </script>
    </jsp:attribute>
    <jsp:attribute name="bodycontent">
        <%--BODY CONTENT GOES HERE, WILL BE PRINTED BETWEEN DIV ID="CONTENT TAG--%>
        <section id="widget-grid" class="">
            <div class="row">
                <article class="col-xs-12 col-sm-12 col-md-12 col-lg-12">
                    <div class="jarviswidget jarviswidget-color-whitesmoke" id="wid-id-1" data-widget-colorbutton="false" data-widget-togglebutton="false" data-widget-editbutton="false" data-widget-deletebutton="false">
                        <header>
                            <h2>Detail Realisasi Bantuan Untuk Desa/Kelurahan ${villageName} </h2>
                        </header>
                        <div>
                            <div class="widget-body no-padding">
                                <table id="tableRealisasiDetail" class="table table-striped table-bordered table-hover" width="100%">
                                    <thead>
                                        <tr>
                                            <th style="border-right: none !important;">
                                            </th>
                                            <th class="hasinput">
                                                <input type="text" class="form-control" placeholder="Filter Nama Penerima" />
                                            </th>
                                            <th class="hasinput">
                                                <input type="text" class="form-control" placeholder="Filter NIK" />
                                            </th>
                                            <th class="hasinput">
                                                <input type="text" class="form-control" placeholder="Filter Jenis Kelamin" />
                                            </th>
                                            <th class="hasinput">
                                                <input type="text" class="form-control" placeholder="Filter PK/PB" />
                                            </th>
                                            <th class="hasinput">
                                                <input type="text" class="form-control" placeholder="Filter Alamat" />
                                            </th>
                                            <th class="hasinput">
                                                <input type="text" class="form-control" placeholder="Filter Nilai Bantuan" />
                                            </th>
                                        </tr>
                                        <tr>
                                            <th width="2%"></th>
                                            <th data-class="expand">Nama Penerima</th>
                                            <th width="12%">NIK</th>
                                            <th width="5%">Jenis Kelamin</th>
                                            <th width="5%">PK/PB</th>
                                            <th data-class="expand">Alamat</th>
                                            <th width="10%">Nilai Bantuan</th>
                                            <th width="7%">Aksi</th>
                                        </tr>
                                    </thead>
                                    <tbody>
                                        <c:forEach items="${dataList}" var="dataDet" varStatus="loop">
                                            <tr>
                                                <td>${loop.index+1}</td>
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
                                                <td><a class="btn btn-prev" onclick="editRealisasi('${dataDet.govTrxRealiszationId}')"><span class='fa fa-edit'></span> Edit</a></td>
                                            </tr>
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


