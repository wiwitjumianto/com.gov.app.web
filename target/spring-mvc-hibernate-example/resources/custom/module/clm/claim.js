

var getPointTypeCode = {
    1: '<center>Injeksi Poin Pulsa</center>',
	2: '<center>Injeksi Poin Flex Benefit</center>',
	3: '<center>Injeksi Poin Mengajar</center>',
        4: '<center>Injeksi Poin Car Ownership Program</center>',

};
function getPointType(param) {
    return getPointTypeCode[param];
}


var getIsValidCode = {
    0: '<center><i style="color:red;" class="  fa-lg fa fa-thumbs-o-down"></i> Data Tidak Valid</center>',
	1: '<center><i style="color:green;" class=" fa-lg fa fa-thumbs-o-up"></i> Data Valid</center>',

};
function getIsValid(param) {
    return getIsValidCode[param];
}


var getClaimStatusCode = {
    1: '<center><span class="label grey">Draft</span></center>',
    2: '<center><span class="label bg-color-orange">On Progress</span></center>',
    3: '<center><span class="label bg-color-green">Approved</span></center>',
    99: '<center><span class="label bg-color-red">Rejected</span></center>',
    90: '<center><span class="label bg-color-red">Cancelled</span></center>'
};
function getClaimStatus(param) {
    return getClaimStatusCode[param];
}

var getPoinTypeCode = {
    1: 'Injeki Poin Pulsa',
    2: 'Injeki Poin Flex Benefit',
    3: 'Injeki Poin Mengajar',
    4: 'Injeki Poin Car Ownership Program'
};
function getPoinType(param) {
    return getPoinTypeCode[param];
}