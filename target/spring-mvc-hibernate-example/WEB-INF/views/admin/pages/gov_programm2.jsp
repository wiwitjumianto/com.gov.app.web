<%-- 
  agung.abdurohman@bni.co.id
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>

<t:genericpage>
    <jsp:attribute name="pagetitle">
        GOV
    </jsp:attribute>
    <jsp:attribute name="breadcrumb">
        <%--BREAD CRUMB GOES HERE--%>
        <li>Buat program bantuan</li>
        </jsp:attribute>
        <jsp:attribute name="custom_js">
            <%--CUSTOM SCRIPT GOES HERE, WILL BE PRINTED IN PAGE RELATED AREA IN SMARTADMIN TEMPLATE--%>
        <script src="${pageContext.request.contextPath}/resources/custom/js/portal/common.js"></script>
        <script src="${pageContext.request.contextPath}/resources/assets/js/plugin/bootstrapvalidator/bootstrapValidator.min.js"></script>
        <script> if (!window.jQuery) {
                document.write('<script src="${pageContext.request.contextPath}/resources/assets/js/libs/jquery-2.1.1.min.js"><\/script>');}</script>

        <script>

        </script>
        <script type="text/javascript">
            var otable;
            pageSetUp();
            var pageFunction = function () {

                var responsiveHelper_dt_basic = undefined;
                var responsiveHelper_datatable_tabletools = undefined;

                var breakpointDefinition = {
                    tablet: 1024,
                    phone: 480
                };

                otable = $('#datatable_fixed_column').DataTable({
                    "processing": true,
                    "rowId": "govTrxProgrammheaderId",
                    "pagingType": "full_numbers",
                    "serverSide": true,
                    "ajax": {
                        url: APP_PATH + "/programm/header/datasource",
                        type: 'POST',
                        datatype: 'json',
                        contentType: 'application/json',
                        data: function (d) {
                            return JSON.stringify(d);
                        }
                    },
                    columns: [
                        {"data": null},
                        {"data": "govTrxProgrammHeaderName", "searchable": true},
                        {"data": "govTrxProgrammHeaderStartDate", "searchable": true},
                        {"data": "govTrxProgrammHeaderEndDate", "searchable": true},
                        {"data": "govTrxProgrammSumVillage", "searchable": true},
                        {"data": "govTrxProgrammSumUnit", "searchable": true},
                        {"data": "status",
                            "sortable": false,
                            "searchable": false,
                            "render": function (data, type, full, meta) {
                                return getIsActiveHtmlCode(data);
                            }
                        }, {
                            "data": null,
                            "sortable": false,
                            "searchable": false,
                            "render": function (data, type, full, meta) {
                              
                                var editButton = 'editProgrammHeader(' + data.govTrxProgrammheaderId + ')';
                                var deleteButton = 'deleteProgrammHeader(' + data.govTrxProgrammheaderId + ')';
                                return getCRUDButton(editButton, deleteButton);
                            }
                        }
                    ],
                    "lengthMenu": [[10, 20, 30], [10, 20, 30]],
                    "sDom": "<'dt-toolbar'<'col-xs-12 col-sm-6'f><'col-sm-6 col-xs-6 hidden-xs'l>> " +
                            "tr" +
                            "<'dt-toolbar-footer'<'col-sm-6 col-xs-12 hidden-xs datatablecrud'><'col-sm-6 col-xs-12'p>i>",
                    "autoWidth": true,
                    "oLanguage": {"sInfoFiltered": "", "sInfo": "Menampilkan _START_ sampai _END_ dari _TOTAL_ entri",
                        "sEmptyTable": "Belum ada data Akun",
                        "sSearch": '<span class="input-group-addon"><i class="glyphicon glyphicon-search"></i></span>'
                    },
                    language: {
                        searchPlaceholder: "Search records"
                    },
                    "preDrawCallback": function () {
                        if (!responsiveHelper_datatable_tabletools) {
                            responsiveHelper_datatable_tabletools = new ResponsiveDatatablesHelper($('#datatable_fixed_column'), breakpointDefinition);
                        }
                        if (!responsiveHelper_dt_basic) {
                            responsiveHelper_dt_basic = new ResponsiveDatatablesHelper($('#datatable_fixed_column'), breakpointDefinition);
                        }
                    },
                    "rowCallback": function (nRow) {
                        responsiveHelper_datatable_tabletools.createExpandIcon(nRow);
                        responsiveHelper_dt_basic.createExpandIcon(nRow);
                    },
                    "drawCallback": function (oSettings) {
                        responsiveHelper_datatable_tabletools.respond();
                        responsiveHelper_dt_basic.respond();
                        for (var i = 0, iLen = oSettings.aiDisplay.length; i < iLen; i++)
                        {
                            $('td:eq(0)', oSettings.aoData[ oSettings.aiDisplay[i] ].nTr).html(i + 1 + oSettings._iDisplayStart);
                        }
                    },
                    "columnDefs": [{
                            "searchable": false,
                            "orderable": false,
                            "targets": -0
                        }],
                    "order": [[1, 'asc']]
                });

                $("#datatable_fixed_column thead th input[type=text]").on('keyup change', function () {
                    otable
                            .column($(this).parent().index() + ':visible')
                            .search(this.value)
                            .draw();
                });
                $(".datatablecrud").html('<table cellspacing="0" cellpadding="0" border="0" class="ui-pg-table navtable" style="float:left;table-layout:auto;"><tbody><tr>\n\
                                            <td class="ui-pg-button ui-corner-all" id="jqgridJobFunction_iladd" title="" data-original-title="Add new row"><div class="btn btn-sm btn-primary" onclick="addProgrammHeader()"><span class="fa fa-plus"></span>&nbsp;Buat Program Baru</div>\n\
                                            </td>\n\
                                            </tr></tbody></table>');

                $('#datatableDelete').click(function () {
                    var id = otable.row('.selected').id();
                    var ids = otable.rows('.selected').ids();
                    if (ids.length == 0) {
                        showBigWarning("Peringatan", "Tidak ada data yang dipilih", 2000);
                    } else {
                        $.each(ids, function (key, value) {
                            deleteJobGrade(value);
                        });
                    }
                });
                $('#datatableEdit').click(function () {
                    var id = otable.row('.selected').id();
                    if (id == null) {
                        showBigWarning("Peringatan", "Tidak ada data yang dipilih", 2000);
                    } else {
                        editJobGrade(id);
                    }
                });
                $('#datatable_fixed_column').on('draw.dt', function () {
                    $("[rel=tooltip]").tooltip();
                });
            };


            function addProgrammHeader() {
                var json;
                $.ajax({
                    cache: false,
                    type: "POST",
                    url: "/gov/programm/header/modal",
                    data: JSON.stringify(json),
                    contentType: 'application/json',
                    beforeSend: function (xhr) {
                        $(".loading").show();
                    },
                    success: function (data) {
                        $(".loading").hide();
                        $('#modal').html(data);
                        $('#modalGovProgrammHeader').modal('toggle');
                    },
                    error: function () {
                        $(".loading").hide();

                    },
                    complete: function () {
                        $(".loading").hide();
                    }
                });
            }
            function editProgrammHeader(appMstProgrammHeaderId) {
                var data = {
                    "appMstProgrammHeaderId": appMstProgrammHeaderId
                };
                $.ajax({
                    cache: false,
                    type: "POST",
                    url: "/gov/programm/header/modal",
                    data: JSON.stringify(data),
                    contentType: 'application/json',
                    beforeSend: function (xhr) {
                        $(".loading").show();
                    },
                    success: function (data) {
                        $(".loading").hide();
                        $('#modal').html(data);
                        $('#modalGovProgrammHeader').modal('toggle');
                    },
                    error: function () {
                        $(".loading").hide();

                    },
                    complete: function () {
                        $(".loading").hide();
                    }
                });

            }
            function deleteProgrammHeader(appMstProgrammHeaderId) {
                $.SmartMessageBox({
                    title: "Konfirmasi",
                    content: "Apakah anda yakin ingin menghapus data ?",
                    icon: "fa fa-thumbs-o-up",
                    buttons: '[Tidak][Ya]'
                }, function (ButtonPressed) {
                    if (ButtonPressed === "Ya") {
                        var data = {
                            "appMstProgrammHeaderId": appMstProgrammHeaderId
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
        </script>
        <style>
            #addButton{
                margin-top: -150px;
                margin-left: 40px;
            }
        </style>
    </jsp:attribute>
    <jsp:attribute name="bodycontent">
        <%--BODY CONTENT GOES HERE, WILL BE PRINTED BETWEEN DIV ID="CONTENT TAG--%>
        <section id="widget-grid" class="">
            <div class="row">
                <article class="col-xs-12 col-sm-12 col-md-12 col-lg-12">
                    <div class="jarviswidget jarviswidget-color-whitesmoke" id="wid-id-1" data-widget-colorbutton="false" data-widget-togglebutton="false" data-widget-editbutton="false" data-widget-deletebutton="false">
                        <header>
                            <h2>List Program Bantuan</h2>
                        </header>
                        <div>
                            <div class="jarviswidget-editbox">
                            </div>
                            <div class="widget-body no-padding">
                                <table id="datatable_fixed_column" class="table table-striped table-bordered table-hover" width="100%">
                                    <thead>
                                        <tr>
                                            <th style="border-right: none !important;">
                                            </th>
                                            <th class="hasinput">
                                                <input type="text" class="form-control" placeholder="Filter Nama Program Bantuan" />
                                            </th>
                                            <th style="border-right: none !important;">
                                            </th>
                                        <tr>
                                            <th width="2%">No.</th>
                                            <th data-class="expand">Nama Program</th>
                                            <th data-class="expand">Periode Mulai</th>
                                            <th data-class="expand">Periode Barakhir</th>
                                            <th data-class="expand">Jumlah Desa</th>
                                            <th data-class="expand">Total Bantuan Unit</th>
                                            <th width="5%"><center>Status</center></th>
                                            <th width="10%"><center>Aksi</center></th>
                                        </tr>
                                    </thead>
                                </table>
                                <!--<button id ="addButton" class="btn btn-primary" onclick="addProgrammHeader()"> Tambah Akun Baru</button>-->
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


