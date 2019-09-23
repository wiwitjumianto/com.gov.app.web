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
</script>
<br/>
<br/>
<hr/>
<div id="location">
    <div>
    </div>
    <div class="row">
        <div class="col-md-6">
            <div class="form-group">
                <label><b><i>Provinsi</i></b></label>
                <select class="form-control sel2"  multiple="" onchange="activateButton()" style="width:100%;" id="appMstProvinceId" name="appMstProvinceId">
                    <option value=""> -- Pilih Provinsi -- </option>
                    <c:forEach items="${listProvince}" var="data">
                        <option  value="${data.appMstProvinceId}">${data.appMstProvinceName}</option>
                    </c:forEach>
                </select>
            </div>
        </div>
    </div>
</div>
</div>



