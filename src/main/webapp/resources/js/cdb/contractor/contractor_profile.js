/**
 * Created by user on 2/14/2020.
 */

function _baseURL() {
    return cdbGlobal.baseURL() + "/public_access/contractorRC";
}
//endregion

var contractorPr = (function () {
    "use strict";


    function getHRsFinal(){
        $.ajax({
            url: _baseURL() + '/getContractorHRsFinal',
            type: 'GET',
            data: {contractorId:$('#contractorIdFinal').val(),ownerOrHR:'H'},
            success: function (res) {
                var contractorHrs = res;
                var hrTr = "";
                //var openModal = "openModal('CheckModal')";
                for (var i in contractorHrs) {

                    var attachments = '';
                    for (var j in contractorHrs[i].hrAttachments){
                        attachments = attachments + "<span class='hra'><input type='hidden' class='hraId' value='"+contractorHrs[i].hrAttachments[j].id+"'>" +
                        "<a href='"+_baseURL() + "/viewDownload?documentPath="+contractorHrs[i].hrAttachments[j].documentPath+"' target='_blank'>"+contractorHrs[i].hrAttachments[j].documentName+"</a></span><br>";
                    }
                    hrTr = hrTr + "<tr class=''><td>"+(parseInt(i)+1)+"</td>"+
                    "<td class='salutationName'><input type='hidden' class='contractorHRid' name='contractorHRs[0].id' value='"+contractorHrs[i].id +"'/>" +
                    "<input type='hidden' class='joiningDate' value='"+contractorHrs[i].joiningDate +"'/>"+
                    contractorHrs[i].salutationName + "</td>" +
                    "<td class='name'>" + contractorHrs[i].name + "</td>" +
                    "<td class='cidNo'>" + contractorHrs[i].cidNo + "</td>" +
                    "<td class='sex'>" + contractorHrs[i].sex + "</td>" +
                    "<td class='countryName'>" + contractorHrs[i].countryName + "</td>" +
                    "<td class='designationName'>" + contractorHrs[i].designationName + "</td>" +
                    "<td class='qualificationName'>" + contractorHrs[i].qualificationName + "</td>" +
                    "<td class='tradeName'>" + contractorHrs[i].tradeName + "</td>" +
                    "<td class='serviceTypeName'>" + contractorHrs[i].serviceTypeName + "</td>" +
                    "<td>" + nullif(contractorHrs[i].joinDate) + "</td>" +
                    "<td class='attachments'>" + attachments + "</td>" +
                    "</tr>";
                }
                $('#hrDtlsTable').find('tbody').html(hrTr);
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
            url: _baseURL() + '/getContractorHRsFinal',
            type: 'GET',
            data: {contractorId:$('#contractorIdFinal').val(),ownerOrHR:'O'},
            success: function (res) {
                var contractorHrs = res;

                var hrTr = '';
                for (var i in contractorHrs) {
                    var showInCert = '';
                    if (contractorHrs[i].siCertificate == '1'){
                        showInCert = '(✔)';
                    } else {
                        showInCert = '( )';
                    }

                    hrTr = hrTr + "<tr class=''><td>"+(parseInt(i)+1)+"</td>"+

                    "<td class='name'>" + contractorHrs[i].name + "</td>" +
                    "<td class='cidNo'>" + contractorHrs[i].cidNo + "</td>" +
                    "<td class='sex'>" + contractorHrs[i].sex + "</td>" +
                    "<td class='countryName'>" + contractorHrs[i].countryName + "</td>" +
                    "<td class='designationName'>" + contractorHrs[i].designationName + "</td>" +
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
            data: {contractorId:$('#contractorIdFinal').val()},
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
                    "<td><input type='hidden' class='contractorEQid' name='contractorEQs[0].id' value='"+equipments[i].id +"'/>"
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
            data: {contractorId:$('#contractorIdFinal').val()},
            success: function (res) {
                var categories = res;
                var tr='';
                for (var i in categories) {
                    var category = '';
                    var className = '';
                    if(categories[i].apClassId == 'e19afe94-c3ea-11e4-af9f-080027dcfac6'){
                        className = 'Large';
                    }else if(categories[i].apClassId == '003f9a02-c3eb-11e4-af9f-080027dcfac6'){
                        className = 'Medium';
                    }else if(categories[i].apClassId == 'ef832830-c3ea-11e4-af9f-080027dcfac6'){
                        className = 'Small';
                    }else if(categories[i].apClassId == '0c14ebea-c3eb-11e4-af9f-080027dcfac6'){
                        className = 'Registered';
                    }
                    if (categories[i].categoryId == "6cd737d4-a2b7-11e4-b4d2-080027dcfac6") {
                        category = 'W1-Roads and Bridges';
                    }
                    if (categories[i].categoryId == "8176bd2d-a2b7-11e4-b4d2-080027dcfac6") {
                        category = 'W2-Traditional Bhutanese Painting/Finishing Works';
                    }
                    if (categories[i].categoryId == "8afc0568-a2b7-11e4-b4d2-080027dcfac6") {
                        category = 'W3-Buildings,Irrigation,Drainage,Flood Control,Water Supply and Sewerage';
                    }
                    if (categories[i].categoryId == "9090a82a-a2b7-11e4-b4d2-080027dcfac6") {
                        category = 'W4-Power and Telecommunication Works';
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

    function getTrackRecord(){
        $.ajax({
            url: _baseURL() + '/getTrackRecord',
            type: 'GET',
            success: function (res) {
                var tr='';
                for (var i in res) {
                    tr = tr +
                    "<tr><td>"+(parseInt(i)+1)+"</td>" +
                    "<td>"+ res[i].pAgency + "</td>" +
                    "<td>"+ isnull(res[i].workId) + "</td>" +
                    "<td>" + res[i].nWorks + "</td>" +
                    "<td>" + res[i].wClassification + "</td>" +
                    "<td>" + res[i].bidAmount + "</td>" +
                    "<td>" + res[i].evalAmount + "</td>" +
                    "<td>" + res[i].dzongkhag + "</td>" +
                    "<td>" + formatAsDate(res[i].startDate) + "</td>" +
                    "<td>" + formatAsDate(res[i].completeDate) + "</td>" +
                    "<td>" + isnull(res[i].remarks) + "</td>" +
                    "<td></td>" +
                    "<td>" + res[i].status + "</td>" +
                    "</tr>";
                }
                $('#trackRecordTbl').find('tbody').html(tr);
            }

        });
    }

    function init(){
        getHRsFinal();
        getOwnerFinal();
        getEQsFinal();
        getClassCategory();
        getTrackRecord();
    }

    return {
        init:init
    };
})();

$(document).ready(function () {
        contractorPr.init();
    }
);