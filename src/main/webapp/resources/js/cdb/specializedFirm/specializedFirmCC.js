/**
 * Created by user on 2/14/2020.
 */
//region functions callable from jsp

function addRow(tableId) {
    var $tableBody = $('#' + tableId).find("tbody");
    var isValid = true;
    $tableBody.find(':input').each(function () {
        if (isValid == true) {
            isValid = $('#specializedFirmRenewalForm').validate().element(this);
        } else {
            $('#specializedFirmRenewalForm').validate().element(this);
        }

    });
    if (isValid == true) {
        var $trLast = $tableBody.find("tr:last");
        var $trNew = $trLast.clone();
        $trNew.find('input:text').val('');
        $trLast.after($trNew);
    }
}
function removeRow(tableId) {
    $('#' + tableId + ' tr:last').remove();
}
function showAcknowledgement() {

    $("#registrtaionFormCard").hide();
    $("#acknowledgementCard").show();
}
//endregion

function enableSubmit(){
    if($('#agreeCheck').prop('checked')){
        $('#btnSubmit').prop('disabled',false);
    }
    else{
        $('#btnSubmit').prop('disabled',true);
    }

}
function _baseURL() {
    return cdbGlobal.baseURL() + "/public_access/specializedFirmCC";
}

//endregion

var specializedFirmCC = (function () {
    "use strict";
    var formID = "#specializedFirmCCForm";
    function saveCC(){ //save cancellation of certificate
        $(formID).on('click', '#btnSubmit', function (e) {
            $(formID).validate({
                submitHandler: function (form) {
                    $.ajax({
                        url: _baseURL() + '/save',
                        type: 'POST',
                        data: $(form).serializeArray(),
                        success: function (res) {
                            if (res.status == 1) {
                                successMsg(res.text, _baseURL());
                            } else {
                                errorMsg(res.text);
                            }
                        }
                    })
                }
            });
        });
    }

    var cert = "<tr><td></td>" +
        "<td><input type='text' class='form-control' name='cAttachments[0].documentName'/> </td>"+
        "<td><input type='file' name='cAttachments[0].attachment' class='form-control-file file' accept='application/msword,application/pdf,application/vnd.ms-excel,image/gif, image/jpeg, image/jpg,application/vnd.openxmlformats-officedocument.wordprocessingml.document'></td>" +
        "<td class='file-size'></td>" +
        "<td><a class='p-2'><i class='fa fa-pencil text-green'></i></a><a class='p-2 del_row'><i class='fa fa-trash text-danger'></i></a></td>" +
        "</tr>";

    /*function sCertIncorporation() {
        $('#ownershipList').on('change', function (e) {
            var option = $(this).find("option:selected").html();
            var certificateTbl = $('#certificateTbl').find('tbody');
            if (~option.indexOf("Incorporated")) {
                $('#cIncorporation').removeClass('hide');
                certificateTbl.append(cert);
            }else{
                $('#cIncorporation').addClass('hide');
                certificateTbl.empty();
            }
        });
    }*/

    /*function delTableRow(){
        $('body').on('click','.del_row',function(e){
            e.preventDefault();
            $(this).closest('tr').remove();
        });
    }*/

    /*function addMoreCert(){
        $('#addMoreCert').on('click',function(e){
            var certificateTbl = $('#certificateTbl').find('tbody').append(cert);
        });
    }*/

    /*function getContractor() {
        if($('#contractorCCActionForm').length < 1){
            return;
        }
        $.ajax({
            url: _baseURL() + '/getContractor',
            type: 'GET',
            success: function (res) {
                var contractor = res;
                $('#ownershipList').val(contractor.ownershipTypeId).prop('disabled',true);
                $('#pCountryId').val(contractor.pCountryId).prop('disabled',true);
                $('#tradeLicenseNo').val(contractor.tradeLicenseNo).prop('disabled',true);
                $('#firmName').val(contractor.firmName).prop('disabled',true);
                $('#tpn').val(contractor.tpn).prop('disabled',true);
                $('#pDzongkhagId').val(contractor.pDzongkhagId).prop('disabled',true);
                $('#pGewogId').val(contractor.pGewog).prop('disabled',true);
                $('#pVillageId').val(contractor.pVillage).prop('disabled',true);
                $('#estAddress').val(contractor.estAddress).prop('disabled',true);
                $('#regDzongkhagId').val(contractor.regDzongkhagId).prop('disabled',true);
                $('#regEmail').val(contractor.regEmail).prop('disabled',true);
                $('#regMobileNo').val(contractor.regMobileNo).prop('disabled',true);
                $('#regPhoneNo').val(contractor.regPhoneNo).prop('disabled',true);
                $('#regFaxNo').val(contractor.regFaxNo).prop('disabled',true);
                $('#contractorIdFinal').val(contractor.id);
            }
        });
    }*/

    /*function viewDownloadAttachment(){
        $('body').on('click','.vAttachment',function(){
            var id = $(this).closest('tr').find('.contractorHRid').val();
            $.ajax({
                url: _baseURL() + '/viewDownload',
                type: 'GET',
                data: {tableName:'crpcontractorhumanresourceattachment',filterCol:'CrpContractorHumanResourceId',filterVal:id}

            });
        });
    }*/

    /*function getHRsFinal(){
        $('#updateHR').on('click',function(){
            if($(this).is(':checked')){
                $('.human_resource_criteria').removeClass('hide');
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
                            hrTr = hrTr + "<tr class=''>" +
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
                            "<td class='attachments'>" + attachments + "</td>" +
                            "<td class='action'><button class='btn-sm btn-info btn-block edit_row'>Edit</button>" +
                            "<button class='btn-sm btn-info btn-block del_row'>Delete</button></td>" +
                            "</tr>";
                        }
                        $('#hrDtlsTable').find('tbody').append(hrTr);
                    }

                });

            }else{
                $('.human_resource_criteria').addClass('hide');
            }
        })
    }*/

    /*function getOwnerFinal(){
        $.ajax({
            url: _baseURL() + '/getContractorHRsFinal',
            type: 'GET',
            data: {contractorId:$('#contractorIdFinal').val(),ownerOrHR:'O'},
            success: function (res) {
                var contractorHrs = res;

                for (var i in contractorHrs) {
                    var tblRow = $('#partnerDtls').find('tbody tr:eq(' + (parseInt(i)) + ')');
                    tblRow.find('#countryList').val(tblRow.find('#countryList option:contains("' + contractorHrs[i].countryName + '")').val());
                    tblRow.find('.hr-cid').val(contractorHrs[i].cidNo);
                    tblRow.find('#salutation').val(tblRow.find('#salutation option:contains("' + contractorHrs[i].salutationName + '")').val());
                    tblRow.find('#designation').val(tblRow.find('#designation option:contains("' + contractorHrs[i].designationName + '")').val());
                    tblRow.find('.name').val(contractorHrs[i].name);
                    tblRow.find('#gender').val(contractorHrs[i].sex);
                    if (contractorHrs[i].siCertificate == '1') {
                        tblRow.find('.showCert').prop('checked', true);
                    } else {
                        tblRow.find('.showCert').prop('checked', false);
                    }
                }
            }

        });
    }*/

    /*function getEQsFinal(){
        $('#updateEq').on('click',function(){
            if($(this).is(':checked')){
                $('.equipment_details').removeClass('hide');
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
                            "<tr>" +
                            "<td><input type='hidden' class='contractorEQid' name='contractorEQs[0].id' value='"+equipments[i].id +"'/>"
                            + equipments[i].equipmentName + "</td>" +
                            "<td>" + equipments[i].registrationNo + "</td>" +
                            "<td>" + equipments[i].quantity + "</td>" +
                            "<td>" + attachment + "</td>" +
                            "<td class='action'><button class='btn-sm btn-info btn-block edit_row'>Edit</button>" +
                            "<button class='btn-sm btn-info btn-block del_row'>Delete</button></td>" +
                            "</tr>";
                        }
                        $('#equipmentTbl').find('tbody').html(eqTr);
                    }

                });
            }else{
                $('.equipment_details').addClass('hide');
            }
            //positionTab('equipment_details');
        })
    }*/

    /*function getClassCategory(){
        $('#upgradeDowngrade').on('click',function() {
            if ($(this).is(':checked')) {
                $('.category_details').removeClass('hide');
                $.ajax({
                    url: _baseURL() + '/getCategory',
                    type: 'GET',
                    data: {contractorId:$('#contractorIdFinal').val()},
                    success: function (res) {
                        var categories = res;
                        for (var i in categories) {
                            if (categories[i].categoryId == "6cd737d4-a2b7-11e4-b4d2-080027dcfac6") {
                                $('#W1').find('.categoryCheck').prop('checked', true);
                                $('#W1').find('.appliedClassID').val(categories[i].aClassId);
                            }
                            if (categories[i].categoryId == "8176bd2d-a2b7-11e4-b4d2-080027dcfac6") {
                                $('#W2').find('.categoryCheck').prop('checked', true);
                                $('#W2').find('.appliedClassID').val(categories[i].aClassId);
                            }
                            if (categories[i].categoryId == "8afc0568-a2b7-11e4-b4d2-080027dcfac6") {
                                $('#W3').find('.categoryCheck').prop('checked', true);
                                $('#W3').find('.appliedClassID').val(categories[i].aClassId);
                            }
                            if (categories[i].categoryId == "9090a82a-a2b7-11e4-b4d2-080027dcfac6") {
                                $('#W4').find('.categoryCheck').prop('checked', true);
                                $('#W4').find('.appliedClassID').val(categories[i].aClassId);
                            }
                        }
                    }

                });

            }else{
                $('.category_details').addClass('hide');
            }
        });
    }*/

    /*function enableClassCategory(){
        $('.categoryCheck').on('click',function(e){
            if($(this).is(':checked')){
                $(this).closest('tr').find('.appliedClassID').prop('disabled',false).prop('required',true);
            }else{
                $(this).closest('tr').find('.appliedClassID').val('')
                    .prop('disabled',true).prop('required',false).removeClass('error');
            }
        })
    }*/

    /*function editInModal(){
        $('body').on('click','.edit_row',function(e){
            e.preventDefault();
            var row = $(this).closest('tr');
            var hrModal = $('#addHRModal');
            hrModal.find('#hrId').val(row.find('.contractorHRid').val())//for Edit
            hrModal.find('#hr5').val(hrModal.find('#hr5 option:contains("'+row.find('.countryName').text()+'")').val());
            hrModal.find('#hr3').val(row.find('.cidNo').text());
            hrModal.find('#hr1').val(hrModal.find('#hr1 option:contains("'+row.find('.salutationName').text()+'")').val());
            hrModal.find('#hr2').val(row.find('.name').text());
            hrModal.find('#hr4').val(row.find('.sex').text());
            hrModal.find('#hr6').val(hrModal.find('#hr6 option:contains("'+row.find('.designationName').text()+'")').val());
            hrModal.find('#hr7').val(hrModal.find('#hr7 option:contains("'+row.find('.qualificationName').text()+'")').val());
            hrModal.find('#hr8').val(hrModal.find('#hr8 option:contains("'+row.find('.tradeName').text()+'")').val());
            hrModal.find('#hr9').val(hrModal.find('#hr9 option:contains("'+row.find('.serviceTypeName').text()+'")').val());
            hrModal.find('#joiningDate').val(row.find('.joiningDate').val());
            var hraTr = "";
            row.find('.hra').each(function(){
                var name = $(this).find('a').text();
                var hra = $(this).find('a').parent().html();
                hraTr = hraTr+"<tr><td><input type='hidden' class='hraId' value='"+$(this).find('.hraId').val()+"'>" +
                "<input type='text' required class='form-control docName' name='contractorHRs[0].contractorHRAs[0].documentName' value='"+name.substring(0,name.lastIndexOf('.'))+"' disabled></td>" +
                "<td><span class='aName'> "+hra+"</span><span class='aFile'></span> </td>" +
                "<td><button class='change'>Change</button><button class='del_row'>Delete</button></td></tr>";
            });
            hrModal.find('#hrUploadTbl tbody').empty().html(hraTr);
            row.remove();
            openModal('addHRModal');
        });
    }*/

    /*function changeFile(){
        $('#hrUploadTbl').on('click','.change',function(e){
            var $this = $(this);
            var row = $(this).closest('tr');
            var file= "<input type='file' required class='file' name='contractorHRs[0].contractorHRAs[0].attachment'"+
                "accept='application/msword,application/pdf,application/vnd.ms-excel,image/gif, image/jpeg, image/jpg,application/vnd.openxmlformats-officedocument.wordprocessingml.document'/>";
            if($this.text() == 'Change'){
                row.find('.docName').prop('disabled',false);
                row.find('.aName').addClass('hide');
                row.find('.aFile').html(file);
                $this.text('Cancel');
            }else{
                row.find('.docName').prop('disabled',true);
                row.find('.aName').removeClass('hide');
                row.find('.aFile').empty();
                $this.text('Change');
            }

        });
    }*/

    function init(){
        /*viewDownloadAttachment();
        getContractor();
        sCertIncorporation();
        service_check();
        getHRsFinal();
        getEQsFinal();
        getClassCategory();
        enableClassCategory();
        delTableRow();
        editInModal();
        changeFile();
        addMoreCert();*/
        saveCC();
    }

    return {
        init:init
    };
})();

$(document).ready(function () {
        specializedFirmCC.init();
    }
);