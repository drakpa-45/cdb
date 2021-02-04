/**
 * Created by user on 2/14/2020.
 */

function _baseURL() {
    return cdbGlobal.baseURL() + "/public_access/specializedFirmRC";
}
//endregion

var specializedFirmCC = (function () {
    "use strict";


    function getHRsFinal(){
        $.ajax({
            url: _baseURL() + '/getSpFirmHRsFinal',
            type: 'GET',
            data: {specializedFirmId:$('#spFirmHRidIdFinal').val(),ownerOrHR:'H'},
            success: function (res) {
                var specializedFirmHrs = res;
                var hrTr = "";
                //var openModal = "openModal('CheckModal')";
                for (var i in specializedFirmHrs) {

                    var attachments = '';
                    for (var j in specializedFirmHrs[i].hrAttachments){
                        attachments = attachments + "<span class='hra'><input type='hidden' class='hraId' value='"+specializedFirmHrs[i].hrAttachments[j].id+"'>" +
                        "<a href='"+_baseURL() + "/viewDownload?documentPath="+specializedFirmHrs[i].hrAttachments[j].documentPath+"' target='_blank'>"+specializedFirmHrs[i].hrAttachments[j].documentName+"</a></span><br>";
                    }
                    hrTr = hrTr + "<tr class=''><td>"+(parseInt(i)+1)+"</td>"+
                    "<td class='salutationName'><input type='hidden' class='spFirmHRid' name='spFirmHRs[0].id' value='"+specializedFirmHrs[i].id +"'/>" +
                    "<input type='hidden' class='joiningDate' value='"+specializedFirmHrs[i].joiningDate +"'/>"+
                    specializedFirmHrs[i].salutationName + "</td>" +
                    "<td class='name'>" + specializedFirmHrs[i].name + "</td>" +
                    "<td class='cidNo'>" + specializedFirmHrs[i].cidNo + "</td>" +
                    "<td class='sex'>" + specializedFirmHrs[i].sex + "</td>" +
                    "<td class='countryName'>" + specializedFirmHrs[i].countryName + "</td>" +
                    "<td class='designationName'>" + specializedFirmHrs[i].designationName + "</td>" +
                    "<td class='qualificationName'>" + specializedFirmHrs[i].qualificationName + "</td>" +
                    "<td class='tradeName'>" + specializedFirmHrs[i].tradeName + "</td>" +
                    "<td class='serviceTypeName'>" + specializedFirmHrs[i].serviceTypeName + "</td>" +
                    "<td>" + nullif(specializedFirmHrs[i].joinDate) + "</td>" +
                    "<td class='attachments'>" + attachments + "</td>" +
                    "</tr>";
                }
                $('#hrDtlsTable').find('tbody').append(hrTr);
            }

        });
        function nullif(val){
            if(val == null || val == 'null'){
                val = ''
            }
            return val;
        }
    }

    function getOwnerFinal(){
        $.ajax({
            url: _baseURL() + '/getSpFirmHRsFinal',
            type: 'GET',
            data: {specializedFirmId:$('#spFirmHRidIdFinal').val(),ownerOrHR:'O'},
            success: function (res) {
                var specializedFirmHrs = res;

                var hrTr = '';
                for (var i in specializedFirmHrs) {
                    var showInCert = '';
                    if (specializedFirmHrs[i].siCertificate == '1'){
                        showInCert = '(✔)';
                    } else {
                        showInCert = '( )';
                    }

                    hrTr = hrTr + "<tr class=''><td>"+(parseInt(i)+1)+"</td>"+

                    "<td class='name'>" + specializedFirmHrs[i].name + "</td>" +
                    "<td class='cidNo'>" + specializedFirmHrs[i].cidNo + "</td>" +
                    "<td class='sex'>" + specializedFirmHrs[i].sex + "</td>" +
                    "<td class='countryName'>" + specializedFirmHrs[i].countryName + "</td>" +
                    "<td class='designationName'>" + specializedFirmHrs[i].designationName + "</td>" +
                    "<td class='align-content-center'>"+showInCert+"</td>"+
                    "</tr>";
                }
                $('#partnerDtls').find('tbody').html(hrTr);
            }
        });
    }

    function getEQsFinal(){
        $.ajax({
            url: _baseURL() + '/getEquipmentFinal',
            type: 'GET',
            data: {specializedFirmId:$('#spFirmHRidIdFinal').val()},
            success: function (res) {
                var equipments = res;
                var eqTr = "";
                for (var i in equipments) {

                    var attachment = '';
                    for (var j in equipments[i].eqAttachments){
                        attachment = attachment + "<a href='"+_baseURL() + "/viewDownload?documentPath="+equipments[i].eqAttachments[j].documentPath+"' target='_blank'>"+equipments[i].eqAttachments[j].documentName+"</a><br>";
                    }
                    eqTr = eqTr +
                    "<tr><td>"+(parseInt(i)+1)+"</td>" +
                    "<td><input type='hidden' class='specializedFirmEQid' name='spFirmEQs[0].id' value='"+equipments[i].id +"'/>"
                    + equipments[i].equipmentName + "</td>" +
                    "<td>" + equipments[i].equipmentType + "</td>" +
                    "<td>" + equipments[i].registrationNo + "</td>" +
                    "<td>" + equipments[i].quantity + "</td>" +
                    "<td>" + attachment + "</td>" +
                    "</tr>";
                }
                $('#equipmentTbl').find('tbody').html(eqTr);
            }

        });
    }

    function getClassCategory(){
        $.ajax({
            url: _baseURL() + '/getCategory',
            type: 'GET',
            data: {specializedFirmId:$('#spFirmHRidIdFinal').val()},
            success: function (res) {
                var categories = res;
                var tr='';
                for (var i in categories) {
                    var category = '';
                    var className = '';
                    if (categories[i].categoryId == "3h1f937c-c74f-11e4-bf37-080027dcfac6") {
                        className = 'SF1-Masonry';
                    }
                    if (categories[i].categoryId == "3h2f937c-c74f-11e4-bf37-080027dcfac6") {
                        className = 'SF2-Construction Carpentry';
                    }
                    if (categories[i].categoryId == "3h3f937c-c74f-11e4-bf37-080027dcfac6") {
                        className = 'SF3-Plumbing';
                    }
                    if (categories[i].categoryId == "3h4f937c-c74f-11e4-bf37-080027dcfac6") {
                        className = 'SF4-Electrical';
                    }
                    if (categories[i].categoryId == "3h5f937c-c74f-11e4-bf37-080027dcfac6") {
                        className = 'SF5-Weilding & Fabrication';
                    }
                    if (categories[i].categoryId == "3h6f937c-c74f-11e4-bf37-080027dcfac6") {
                        className = 'SF6-Painting';
                    }

                    if (categories[i].categoryId == "3h1f937c-c74f-11e4-bf37-080027dcfac6") {
                        category = 'Masonry';
                    }
                    if (categories[i].categoryId == "3h2f937c-c74f-11e4-bf37-080027dcfac6") {
                        category = 'Construction Carpentry';
                    }
                    if (categories[i].categoryId == "3h3f937c-c74f-11e4-bf37-080027dcfac6") {
                        category = 'Plumbing';
                    }
                    if (categories[i].categoryId == "3h4f937c-c74f-11e4-bf37-080027dcfac6") {
                        category = 'Electrical';
                    }
                    if (categories[i].categoryId == "3h5f937c-c74f-11e4-bf37-080027dcfac6") {
                        category = 'Weilding & Fabrication';
                    }
                    if (categories[i].categoryId == "3h6f937c-c74f-11e4-bf37-080027dcfac6") {
                        category = 'Painting';
                    }

                    tr=tr+'<tr>'+
                    '<td colspan="2">'+category +'</td>'+
                    '<td>'+className +'</td>'+
                    '<td>'+className +'</td>'+
                    '<td>'+className +'</td></tr>'
                }

                $('#classificationTbl').find('tbody').html(tr);
            }

        });
    }


    function init(){
        getHRsFinal();
        getOwnerFinal();
        getEQsFinal();
        getClassCategory();
    }

    return {
        init:init
    };
})();

$(document).ready(function () {
        specializedFirmCC.init();
    }
);