/**
 * Created by user on 2/14/2020.
 */

function _baseURL() {
    return cdbGlobal.baseURL() + "/public_access/consultantRC";
}
//endregion

var consultantCC = (function () {
    "use strict";

    function getHRsFinal(){
        $.ajax({
            url: _baseURL() + '/getConsultantHRsFinal',
            type: 'GET',
            data: {consultantId:$('#consultantIdFinal').val(),ownerOrHR:'H'},
            success: function (res) {
                var consultantHrs = res;
                var hrTr = "";
                //var openModal = "openModal('CheckModal')";
                for (var i in consultantHrs) {

                    var attachments = '';
                    for (var j in consultantHrs[i].hrAttachments){
                        attachments = attachments + "<span class='hra'><input type='hidden' class='hraId' value='"+consultantHrs[i].hrAttachments[j].id+"'>" +
                        "<a href='"+_baseURL() + "/viewDownload?documentPath="+consultantHrs[i].hrAttachments[j].documentPath+"' target='_blank'>"+consultantHrs[i].hrAttachments[j].documentName+"</a></span><br>";
                    }
                    hrTr = hrTr + "<tr class=''><td>"+(parseInt(i)+1)+"</td>"+
                    "<td class='salutationName'><input type='hidden' class='consultantHRid' name='consultantHRs[0].id' value='"+consultantHrs[i].id +"'/>" +
                    "<input type='hidden' class='joiningDate' value='"+consultantHrs[i].joiningDate +"'/>"+
                    consultantHrs[i].salutationName + "</td>" +
                    "<td class='name'>" + consultantHrs[i].name + "</td>" +
                    "<td class='cidNo'>" + consultantHrs[i].cidNo + "</td>" +
                    "<td class='sex'>" + consultantHrs[i].sex + "</td>" +
                    "<td class='countryName'>" + consultantHrs[i].countryName + "</td>" +
                    "<td class='designationName'>" + consultantHrs[i].designationName + "</td>" +
                    "<td class='qualificationName'>" + consultantHrs[i].qualificationName + "</td>" +
                    "<td class='tradeName'>" + consultantHrs[i].tradeName + "</td>" +
                    "<td class='serviceTypeName'>" + consultantHrs[i].serviceTypeName + "</td>" +
                    "<td>" + nullif(consultantHrs[i].joinDate) + "</td>" +
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
            url: _baseURL() + '/getConsultantHRsFinal',
            type: 'GET',
            data: {consultantId:$('#consultantIdFinal').val(),ownerOrHR:'O'},
            success: function (res) {
                var consultantHrs = res;

                var hrTr = '';
                for (var i in consultantHrs) {
                    var showInCert = '';
                    if (consultantHrs[i].siCertificate == '1'){
                        showInCert = '(✔)';
                    } else {
                        showInCert = '( )';
                    }

                    hrTr = hrTr + "<tr class=''><td>"+(parseInt(i)+1)+"</td>"+

                    "<td class='name'>" + consultantHrs[i].name + "</td>" +
                    "<td class='cidNo'>" + consultantHrs[i].cidNo + "</td>" +
                    "<td class='sex'>" + consultantHrs[i].sex + "</td>" +
                    "<td class='countryName'>" + consultantHrs[i].countryName + "</td>" +
                    "<td class='designationName'>" + consultantHrs[i].designationName + "</td>" +
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
            data: {consultantId:$('#consultantIdFinal').val()},
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
                    "<td><input type='hidden' class='consultantEQid' name='consultantEQs[0].id' value='"+equipments[i].id +"'/>"
                    + equipments[i].equipmentName + "</td>" +
                    "<td></td>" +
                    "<td>" + equipments[i].registrationNo + "</td>" +
                    "<td></td>" +
                    "<td>" + equipments[i].quantity + "</td>" +
                    "<td>" + attachment + "</td>" +
                    "</tr>";
                }
                $('#equipmentTbl').find('tbody').html(eqTr);
            }

        });
    }

    function getClassCategory(){
      //  $('#classification').on('click',function() {

        $.ajax({
            url: _baseURL() + '/getCategory',
            type: 'GET',
            data: {consultantId:$('#consultantIdFinal').val()},
            success: function (res) {
               // alert('sadf');
                var categories = res;
                var tr='';
                for (var i in categories) {
                    if (categories[i].apClassId == "2dc059a3-bc17-11e4-81ac-080027dcfac6") {
                        $('#asone').prop('checked', true).prop('hide', false);
                    }
                    if (categories[i].apClassId == "378c8114-bc17-11e4-81ac-080027dcfac6") {
                        $('#astwo').prop('checked', true).prop('hide', false);
                    }
                    if (categories[i].apClassId == "42914a22-bc17-11e4-81ac-080027dcfac6") {
                        $('#asthree').prop('checked', true).prop('hide', false);
                    }
                    if (categories[i].apClassId == "51f58a70-bc17-11e4-81ac-080027dcfac6") {
                        $('#cvsone').prop('checked', true);
                    }
                    if (categories[i].apClassId == "5b147a4d-bc17-11e4-81ac-080027dcfac6") {
                        $('#cvstwo').prop('checked', true);
                    }
                    if (categories[i].apClassId == "6516bfdd-bc17-11e4-81ac-080027dcfac6") {
                        $('#cvsthree').prop('checked', true);
                    }
                    if (categories[i].apClassId == "7b84fd72-bc17-11e4-81ac-080027dcfac6") {
                        $('#cvsfour').prop('checked', true);
                    }
                    if (categories[i].apClassId == "a8ee79e6-bc17-11e4-81ac-080027dcfac6") {
                        $('#cvsfive').prop('checked', true);
                    }
                    if (categories[i].apClassId == "be34bd47-bc17-11e4-81ac-080027dcfac6") {
                        $('#cvssix').prop('checked', true);
                    }
                    if (categories[i].apClassId == "cc3bfc36-bc17-11e4-81ac-080027dcfac6") {
                        $('#cvsseven').find('.categoryCheck').prop('checked', true);
                    }

                    if (categories[i].apClassId == "ded7b309-bc17-11e4-81ac-080027dcfac6") {
                        $('#eesone').prop('checked', true);
                    }
                    if (categories[i].apClassId == "ef1e617f-bc17-11e4-81ac-080027dcfac6") {
                        $('#eestwo').prop('checked', true);
                    }
                    if (categories[i].apClassId == "1a4e9b6f-bc18-11e4-81ac-080027dcfac6") {
                        $('#eesthree').prop('checked', true);
                    }
                    if (categories[i].apClassId == "271c4483-bc18-11e4-81ac-080027dcfac6") {
                        $('#eesfour').prop('checked', true);
                    }
                    if (categories[i].apClassId == "30a3dd3c-bc18-11e4-81ac-080027dcfac6") {
                        $('#eesfive').prop('checked', true);
                    }
                    if (categories[i].apClassId == "3ceb09ba-bc18-11e4-81ac-080027dcfac6") {
                        $('#eessix').prop('checked', true);
                    }
                    if (categories[i].apClassId == "4461b1b0-bc18-11e4-81ac-080027dcfac6") {
                        $('#eesseven').prop('checked', true);
                    }
                    if (categories[i].apClassId == "8a6ea970-be66-11e9-9ac2-0026b988eaa8") {
                        $('#sone').prop('checked', true);
                    }
                    if (categories[i].apClassId == "b20d9185-be66-11e9-9ac2-0026b988eaa8") {
                        $('#stwo').prop('checked', true);
                    }
                    if (categories[i].apClassId == "fb9e92cb-be66-11e9-9ac2-0026b988eaa8") {
                        $('#sthree').prop('checked', true);
                    }
                    if (categories[i].apClassId == "1129c568-be67-11e9-9ac2-0026b988eaa8") {
                        $('#sfour').prop('checked', true);
                    }
                    if (categories[i].apClassId == "3aba7cc5-be67-11e9-9ac2-0026b988eaa8") {
                        $('#sfive').prop('checked', true);
                    }
                    if (categories[i].apClassId == "5fa269a3-be67-11e9-9ac2-0026b988eaa8") {
                        $('#ssix').prop('checked', true);
                    }
                    if (categories[i].apClassId == "4cd73d78-be67-11e9-9ac2-0026b988eaa8") {
                        $('#sseven').prop('checked', true);
                    }
                }
              //  $('#classificationTbl').find('tbody').html(tr);
            }

        });
      //  });
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
        consultantCC.init();
    }
);