<%-- 
    Author     : agung.abdurohman@bni.co.id
--%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%> 
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<script type="text/javascript">
    $(document).ready(function () {
        $("#listFunctional").select2();
    });
    $("#selectAllFunctional").click(function () {
        if ($("#selectAllFunctional").is(':checked')) {
            $("#listFunctional > option").prop("selected", "selected");
            $("#listFunctional").trigger("change");
        } else {
            $("#listFunctional > option").removeAttr("selected");
            $("#listFunctional").trigger("change");
        }
    });
</script>
<script>
    $('.sel2').select2();
</script>
<br/>
<br/>
<hr/>
<div id="levelPartial">
    <div>
        <div>
            <div class="form-group">
                <label style="font-size: 16px;" class="select text-blue-material">Pilih Program</label>
            </div>
        </div>
        <div id="ouhList" class="col-lg-4">
            <select class="select2 sel2" id="govTrxProgrammHeaderId" style="width: 100%">
                <option value="">-- Pilih Program --</option> 
                <c:forEach items="${listHeader}" var="data">
                    <option value="${data.govTrxProgrammHeaderId}">${data.govTrxProgrammHeaderName}</option>    
                </c:forEach>
            </select>
        </div>
    </div>
</div>
<br/>
<br/>
<hr/>
<div id="locationLevel">
    <div>
        <div>
            <div class="form-group">
                <label style="font-size: 16px;" class="select text-blue-material">Pilih Tingkat Wilayah</label>
            </div>
        </div>
        <div id="ouhList" class="col-lg-4">
            <select class="select2 sel2" onchange="loadLocation(this.value)" id="locationLevelId" style="width: 100%">
                <option value="">-- Pilih Tingkat Wilayah --</option> 
                <option value="1">Berdasarkan Provinsi</option> 
                <option value="2">Berdasarkan Kabupaten/Kota</option> 
                <option value="3">Berdasarkan kecamatan</option> 
                <option value="4">Berdasarkan Desa</option>                
            </select>
        </div>
    </div>
</div>

<script>
    function loadLocation(locType) {
        $('.loading').show();
        $.ajax({
            cache: false,
            type: "GET",
            url: "/gov/reporting/getpartialpagelocation/" + locType,
            contentType: 'application/json',
            success: function (data) {
                $('#partial_page_data_location').html(data);
            }
        });
        $('.loading').hide();
    }
</script>



