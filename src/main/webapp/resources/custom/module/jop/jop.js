/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


var getProgramStatusCode = {
    0: '<center><span class="label bg-color-grays">Cancelled</span></center>',
    1: '<center><span class="label bg-color-orange2">Draft</span></center>',
    2: '<center><span class="label bg-color-greens">In Progress</span></center>',
    3: '<center><span class="label bg-color-reds">Finished</span></center>'
};
function getProgramStatus(param) {
    return getProgramStatusCode[param];
}

var getApprovalStatusCode = {
    1: '<span class="label bg-color-greens"><center style="color: white;">In Progress</center></span>',
    2: '<span class="label bg-color-greens"><center style="color: white;">In Progress</center></span>',
    3: '<span class="label bg-color-reds"><center style="color: white;">Finished</center></span>'
};
function getApprovalStatus(param) {
    return getApprovalStatusCode[param];
}