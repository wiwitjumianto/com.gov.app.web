function TreeView(parentDiv, url) {
    this.parentDiv = parentDiv;
    this.url = url;
    this.data = {};
}

TreeView.prototype.setData = function(data) {
    this.data = data;
};

TreeView.prototype.draw = function() {
    if (this.url) {
        var self = this;
        $.ajax({
            cache: false,
            type: "POST",
            url: this.url,
            data: null,
            contentType: 'application/json',
            async: false,
            success: function (_dataResult) {
                self.data = _dataResult.data;
            }
        });
    }

    if (Object.keys(this.data).length > 0) {
        this.clear();
        this.appendElement(this.parentDiv, this.data, true);
        $('.tree > ul').attr('role', 'tree').find('ul').attr('role', 'group');
        $('.tree').find('li:has(ul)').addClass('parent_li').attr('role', 'treeitem').find(' > span').attr('title', 'Collapse this branch').on('click', function(e) {
                var children = $(this).parent('li.parent_li').find(' > ul > li');
                if (children.is(':visible')) {
                        children.hide('fast');
                        $(this).attr('title', 'Expand this branch').find(' > i').removeClass().addClass('fa fa-lg fa-plus-circle');
                } else {
                        children.show('fast');
                        $(this).attr('title', 'Collapse this branch').find(' > i').removeClass().addClass('fa fa-lg fa-minus-circle');
                }
                e.stopPropagation();
        });
    }
};

TreeView.prototype.clear = function() {
    $(this.parentDiv).empty();
}

TreeView.prototype.isNode = function(data) {
    if (data && data.constructor === Array) {
        if (data.length > 0) {
            return true;
        }
    } 
    return false;
}

TreeView.prototype.appendElement = function (parentElement, data, isFirst) {
    var self = this;
    var ulComp = document.createElement("ul");
    var arr = new Array();
    if (data.constructor === Array) {
        arr = data;
    } else {
        arr.push(data);
    }
    
    $.each(arr, function(index, value) {
        var liComp = document.createElement("li");
        if (!isFirst) {
            liComp.setAttribute("style", "display:none");
        }
        if (self.isNode(value.child)) {
            $(liComp).append("<span><i class=\"fa fa-lg fa-plus-circle\"></i> " + value.name + "</span>");
            self.appendElement(liComp, value.child, false);
        } else {
            $(liComp).append("<span><i class=\"icon-leaf\"></i> " + value.name + "</span>");
        }
        $(ulComp).append(liComp); 
    });
    $(parentElement).append(ulComp);
};
