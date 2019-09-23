/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

function TrackingParameterWidget(parentDiv, btnLabel, conditionDiv) {
    this.parentDiv = parentDiv;
    this.btnLabel = btnLabel;
    this.conditionDiv = conditionDiv;
    this.searchComponents = {};
    this.data = {};
}

TrackingParameterWidget.prototype.setData = function(data) {
    this.data = data;
};

TrackingParameterWidget.prototype.draw = function() {
    if (this.data) {
        var self = this;
        
        var popOverWrapper = document.createElement("div");
        popOverWrapper.setAttribute("id", "popover_content_wrapper");
        popOverWrapper.setAttribute("style", "display: none");
        
        var headPopOver = document.createElement("div");
        headPopOver.setAttribute("class","popover");
        headPopOver.setAttribute("role","tooltip");
        headPopOver.setAttribute("style" , "display: block; top:0px; left: 0px; max-width:440px !important; margin-top:0;");
        
        var popOverContentDiv = document.createElement("div");
        popOverContentDiv.setAttribute("class", "popover-content");
        
        var popOverDiv = document.createElement("div");
        popOverDiv.setAttribute("name", "popOverDiv");
        popOverDiv.setAttribute("class", "tabvertical");
        
        var mainMenuGroupDiv = document.createElement("div");
        mainMenuGroupDiv.setAttribute("name", "groupName");
        mainMenuGroupDiv.setAttribute("class", "lfttab");
        
        var contentDiv = document.createElement("div");
        contentDiv.setAttribute("id", "contentDiv");
        contentDiv.setAttribute("class", "tab-content  PT10 PB10");
        
        var mainMenuUlComp = document.createElement("ul");
        mainMenuUlComp.setAttribute("class", "nav nav-pills nav-stacked PT10 PB10 ulRecipien");
        $.each(this.data, function(index, value) {
            var menuText = document.createTextNode(value.name);
            var hrefComp = document.createElement("a");
            hrefComp.appendChild(menuText);
            hrefComp.setAttribute("href", "#" + value.id);
            hrefComp.setAttribute("aria-controls", value.id);
            hrefComp.setAttribute("role", "tab");
            hrefComp.setAttribute("data-toggle", "tab");

            var liComp = document.createElement("li");
            liComp.setAttribute("role", "presentation");
            liComp.appendChild(hrefComp);

            mainMenuUlComp.appendChild(liComp);
            
            contentDiv.appendChild(self.drawSubMenu(value.id, value.child));
        });
        
        mainMenuGroupDiv.appendChild(mainMenuUlComp);
        
        var subMenuDiv = document.createElement("div");
        subMenuDiv.setAttribute("class", "rhtlstsrl");
        subMenuDiv.appendChild(contentDiv);
        
        popOverDiv.appendChild(mainMenuGroupDiv);
        popOverDiv.appendChild(subMenuDiv);
        popOverContentDiv.appendChild(popOverDiv);
    
        headPopOver.appendChild(popOverContentDiv);
        popOverWrapper.appendChild(headPopOver);
        
        var btnComp = '<button type="button" data-toggle="popover" data-placement="bottom" class="btn btn-primary pull-left">' + this.btnLabel + ' &nbsp;<i class="fa fa-caret-down"></i></button>';
        $(this.parentDiv).append($(btnComp));
        $(this.parentDiv).append(popOverWrapper);
    }
    
};

TrackingParameterWidget.prototype.drawSubMenu = function(compId, childArr) {
    var panelDiv = document.createElement("div");
    panelDiv.setAttribute("class", "tab-pane");
    panelDiv.setAttribute("id", compId);
    panelDiv.setAttribute("role", "tabpanel");
    
    var groupDiv = document.createElement("div");
    groupDiv.setAttribute("class","col-md-12");
    
    var ulComp = document.createElement("ul");
    ulComp.setAttribute("name", "list-group");
    ulComp.setAttribute("class", "nav nav-pills nav-stacked PT10 PB10 ulRecipien");
    
    $.each(childArr, function(index, value) {
        var menuText = document.createTextNode(value.name);
        var hrefComp = document.createElement("a");
        hrefComp.appendChild(menuText);
        hrefComp.setAttribute("id", value.id);
        hrefComp.setAttribute("type", value.type);
        hrefComp.setAttribute("table", value.table);
        hrefComp.setAttribute("class", "add_button");
        hrefComp.setAttribute("fieldName", value.name);
        if (value.dkey && value.dvalue) {
            hrefComp.setAttribute("dropdown-key", value.dkey);
            hrefComp.setAttribute("dropdown-value", value.dvalue);
        }
        hrefComp.setAttribute("style", "cursor:pointer; color:#000;");
        
        if (value.type === 'dropdown' && value.url) {
            hrefComp.setAttribute("url", value.url);
        }
        
        var liComp = document.createElement("li");
        liComp.setAttribute("name", "list-group-item");
        liComp.setAttribute("style", "display: list-item; margin-left:5px;");
        liComp.appendChild(hrefComp);
        
        ulComp.appendChild(liComp);
    });
    
    groupDiv.appendChild(ulComp);
    panelDiv.appendChild(groupDiv);
    
    return panelDiv;
};

TrackingParameterWidget.prototype.clear = function() {
    $(this.parentDiv).empty();
    $(this.conditionDiv).empty();
    this.searchComponents = {};
};

TrackingParameterWidget.prototype.addComponentSearch = function(compObj) {
    var id = compObj.attr("id");
    var type = compObj.attr("type");
    var table = compObj.attr("table");
    var url = compObj.attr("url");
    var fieldName = compObj.attr("fieldName");
    var searchComponentHtml = "";
    
    if (type === 'textfield') {
        searchComponentHtml =   '<li style="list-style:none; margin-top:10px;" id="textfield_' + id + ' " class="row list">' +
                                '<label style="float:left;" ><input type="checkbox" class="checkbox style-0 cbCriteria" dataId="' + id + '" dataTable="' + table + '" dataFieldName="' + fieldName + '" checked="checked" style="margin-top:-20px;"><span></span></label>' +
                                '<select name="sidecondition_' + id + '" class="form-control before-list" id="selectandor_' + id + '" style="float:left; width:10%; margin-right:5px;"><option value = "AND">And</option><option value="OR">Or</option></select>' + //checkbox
                                '<button type="button" style="float:left; margin-right:5px;"  class="btn btn-default dropdown-toggle downArrow" tabindex="-1"><span class=" fa fa-angle-down "></span></button>' + //up/down
                                '<button type="button" style="float:left; margin-right:5px;"  class="btn btn-default dropdown-toggle upArrow" tabindex="-1"><span class=" fa fa-angle-up " ></span></button>' + //up/down
                                '<input class="form-control after-list" style="float:left; width:35%; margin-right:5px;"  id="select-1" value="' + fieldName + ' " readonly>' + //category
                                '<select name="condition_' + id + '"  class="form-control" style="float:left; width:10%; margin-right:5px;"  id="condition_' + id + '"><option  value ="=">IS</option><option  value ="!=">IS NOT</option></select>' + //condition
                                '<input class="form-control" name="input_' + id + '" style="float:left; width:20%; margin-right:5px;" id="input_' + id + '"/>' + //value
                                '<button class="btn remove_button" style="float:left;"  type="button" parentComp="' + id + '"><span class="glyphicon glyphicon-remove"></span></button></li>'; //button cancel;
    } else if (type === 'dropdown'){
        searchComponentHtml =   '<li style="list-style:none; margin-top:10px;" id="dropdown_' + id + ' " class="row list">' +
                                '<label style="float:left;" ><input type="checkbox" class="checkbox style-0 cbCriteria" checked="checked cbCriteria" dataId="' + id + '" dataTable="' + table + '" style="margin-top:-20px;"><span></span></label>' +
                                '<select name="sidecondition_' + id + '" class="form-control before-list" id="selectandor_' + id + '" style="float:left; width:10%; margin-right:5px;"><option value = "AND">And</option><option value="OR">Or</option></select>' + //checkbox
                                '<button type="button" style="float:left; margin-right:5px;"  class="btn btn-default dropdown-toggle downArrow" tabindex="-1"><span class=" fa fa-angle-down "></span></button>' + //up/down
                                '<button type="button" style="float:left; margin-right:5px;"  class="btn btn-default dropdown-toggle upArrow" tabindex="-1"><span class=" fa fa-angle-up " ></span></button>' + //up/down
                                '<input class="form-control after-list" style="float:left; width:35%; margin-right:5px;"  id="select-1" value="' + fieldName + ' " readonly>' + //category
                                '<select class="form-control"  name="condition_' + id + '"  style="float:left; width:10%; margin-right:5px;"  id="condition_' + id + '"><option  value ="=">IS</option><option  value ="!=">IS NOT</option></select>' + //condition
                                '<select name="input_' + id + '" class="form-control select2"  style="float:left; width:20%; margin-right:5px;" id="input_' + id + '">' + this.constructDropdownOption(url, compObj.attr("dropdown-key"), compObj.attr("dropdown-value")) + 
                                '</select>' + //value
                                '<button class="btn remove_button" style="float:left;"  type="button" parentComp="' + id + '"><span class="glyphicon glyphicon-remove"></span></button></li>'; //button cancel;;
    }

    $(this.conditionDiv).append(searchComponentHtml);
    this.searchComponents[id] = {"type" : type, "table" : table, "url" : url, "fieldName" : fieldName};
};

TrackingParameterWidget.prototype.registerEvents = function() {
    var self = this;
    $('[data-toggle=popover]').popover({
        content: $('#popover_content_wrapper').html(),
        html: true
    });
    
    $(document).on("click", ".add_button", function() {
        var menuComp = $(this);
        var compId = menuComp.attr("id");
        var fieldName = menuComp.attr("fieldName")
        if (self.searchComponents.hasOwnProperty(compId)) {
            showSmallError("Informasi", fieldName + " sudah di tambahkan", 5000);
            return false;
        }
        
        self.addComponentSearch(menuComp);
    });
    
    $(document).on("click", ".remove_button", function() {
        $(this).parents('li').remove();
        var idComp = $(this).attr("parentComp");
        delete self.searchComponents[idComp];
    });
    
    $(document).on("click", ".upArrow", function() {
        $(this).parent().insertBefore($(this).parent().prev());
    });
    
    $(document).on("click", ".downArrow", function() {
        $(this).parent().insertAfter($(this).parent().next());
    });
};

TrackingParameterWidget.prototype.constructDropdownOption = function(url, dkey, dvalue) {
    var dataFromServer = this.getDataToServer(url);
    var optionHtml = "";
    if (dataFromServer) {
        $.each(dataFromServer, function (index, value) {
            optionHtml += '<option value="' + value[dkey] + '">' + value[dvalue] + '</option>';
        });
    }
    return optionHtml;
};

TrackingParameterWidget.prototype.getDataToServer = function(url) {
    var result = [];
    if (url) {
        $.ajax({
            cache: false,
            type: "POST",
            url: APP_PATH + url,
            data: new Array(),
            contentType: 'application/json',
            async: false,
            success: function (_dataResult) {
                result = _dataResult;
            }
        });
    }
    return result;
};

TrackingParameterWidget.prototype.getCriteriaValue = function() {
    var criteriaArr = new Array();
    var cbCriterias = $('.cbCriteria:checkbox:checked');
    var isViolated = false;
    $(cbCriterias).each(function () {
       var id = $(this).attr("dataId");
       var table = $(this).attr("dataTable");
       var fieldName = $(this).attr("dataFieldName");
       var operator = $("#selectandor_" + id).val();
       var condition = $("#condition_" + id).val();
       var value = $("#input_" + id).val();
       
       if (value) {
           criteriaArr.push({id : id, operator : operator, condition : condition, value : value, table : table});
       } else {
           isViolated = true;
           showSmallError("Informasi", "Harap isi value kriteria pada " + fieldName , 5000);
           return false;
       }
    });
    
    if (isViolated) {
        return null;
    }
    
    return criteriaArr;
};