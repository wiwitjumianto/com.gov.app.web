<%-- 
    Author     : agung.abdurohman@bni.co.id
--%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%> 
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<script>
    $('.sel2').select2();
</script>
<br/>
<br/>
<hr/>
<div id="headerPartial">
    <div>
        <div>
            <div class="form-group">
                <label style="font-size: 16px;" class="select text-blue-material">Pilih Program</label>
            </div>
        </div>
        <div id="ouhList" class="col-lg-4">
            <select class="select2 sel2" id="govTrxProgrammHeaderId" name="govTrxProgrammHeaderId" 
                    onchange="activateButton()" style="width: 100%">
                <option value="">-- Pilih Program --</option> 
                <c:forEach items="${listHeader}" var="data">
                    <option value="${data.govTrxProgrammHeaderId}">${data.govTrxProgrammHeaderName}</option>    
                </c:forEach>
            </select>
        </div>
    </div>
</div>



