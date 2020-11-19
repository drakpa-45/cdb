/**
 * Created by user on 2/14/2020.
 */
//region functions callable from jsp

function addRow(tableId) {
    var $tableBody = $('#' + tableId).find("tbody");
    var isValid = true;
    $tableBody.find(':input').each(function () {
        if (isValid == true) {
            isValid = $('#contractorRenewalForm').validate().element(this);
        } else {
            $('#contractorRenewalForm').validate().element(this);
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

function saveAndPreview(presentClass, nextClass) {
    var content = '<h3 class="pt-3 text-center">Fee Structure</h3>' + $(".feesStructure > .div-actual").html() +
        '<h3 class="pt-3 text-center">General Information</h3>' + $(".generalInformation > .div-actual").html() +

        $("." + presentClass + ">a").addClass('bg-blue text-white');
    $('.tab-pane').removeClass("active").addClass("active");
    $('.tab-content').removeClass("active").addClass("active");
    $("." + nextClass).addClass("active");
    $("." + presentClass + ">a").append("<i class='fa fa-check ml-1'></i>");
    //$("#" + nextClass).html(content);


}

function nextTab(presentClass, nextClass) {
    $("." + presentClass + ">a").addClass('bg-blue text-white');
    $('.tab-pane').removeClass("active");
    $('.tab-content').removeClass("active");
    $("." + nextClass).addClass("active");
    $("." + presentClass + ">a").append("<i class='fa fa-check ml-1'></i>");
}
function backTab(presentClass, prevClass) {
    $("." + presentClass + ">a").removeClass('bg-blue text-white');
    $('.tab-pane').removeClass("active");
    $('.tab-content').removeClass("active");
    $("." + prevClass).addClass("active");
    $("." + presentClass + ">a").find(".fa-check").remove();
    $("." + prevClass + ">a").find(".fa-check").remove();
}

function openModal(modalId) {
    $("#" + modalId).modal('show');
}

function enableSubmit(){
    if($('#agreeCheck').prop('checked')){
        $('#btnSubmit').prop('disabled',false);
    }
    else{
        $('#btnSubmit').prop('disabled',true);
    }

}

function showConfirmation(){
    $('#confirmationModel').modal('show');
    //$('#actiontype').val('submit');
    //$('#formId').val('contractorForm');
    $('#targetId').val('acknowledgementmessage');
    $('#messages').html('You are about to verify application. Are you sure to proceed ?');
}
function submitApplication(){
    cdbGlobal.formIndexing($('#certificateTbl').find('tbody'));
    cdbGlobal.formIndexing($('#partnerDtls').find('tbody'));
    cdbGlobal.formIndexing($('#eqdatatable').find('tbody'));
    cdbGlobal.formIndexing($('#hrDtlsTable').find('tbody'));
    $("#addHRModal").find(":input").prop('disabled',true);

    //ensure at least one services are availed;
    /*var availed  = false;
    $('.service_check').forEach(function(){
        if ($(this).is(':checked') == true){
            availed = true;
        }
    });
    if(availed == false){
        warningMsg("Please check at least one services");
        return;
    }*/

    var contractorForm = $("#contractorRenewalForm");
    var url=_baseURL() + "/save";
    var options = {
        target:'#registrtaionFormCard',
        url:url,
        type:'POST',
        enctype: 'multipart/form-data',
        data: contractorForm.serialize()
    };
    contractorForm.ajaxSubmit(options);
    $('#confirmationModel').modal('hide');
}
function _baseURL() {
    return cdbGlobal.baseURL() + "/public_access/contractor_renewal";
}

//endregion

var contractor_renewal = (function () {
    "use strict";
    var isSubmitted = false;

    var cert = "<tr><td></td>" +
        "<td><input type='text' class='form-control' name='cAttachments[0].documentName'/> </td>"+
        "<td><input type='file' name='cAttachments[0].attachment' class='form-control-file file'></td>" +
        "<td class='file-size'></td>" +
        "<td><a class='p-2'><i class='fa fa-pencil text-green'></i></a><a class='p-2'><i class='fa fa-trash text-danger'></i></a></td>" +
        "</tr>";

    function sCertIncorporation() {
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
    }


    function service_check(){
        $('body').on('click','.service_check',function(){
            var id = $(this).prop('id');
            var $this = $(this);
            if(id == 'Incorporation' ){
                if($this.is(':checked')){
                    $('#ownershipList').prop('disabled',false);
                }else{
                    $('#ownershipList').prop('disabled',true);
                }
            }else if(id == 'changeOfFirmName' ){
                if($this.is(':checked')) {
                    $('#firmName').prop('disabled', false);
                }else{
                    $('#firmName').prop('disabled', true);
                }
            }else if(id == 'changeOfLocation' ){
                if($this.is(':checked')) {
                    $('#estAddress').prop('disabled', false);
                    $('#regDzongkhagId').prop('disabled', false);
                }else{
                    $('#estAddress').prop('disabled', false);
                    $('#regDzongkhagId').prop('disabled', false);
                }
            }else if(id == 'changeOfOwnerId' ){
                if($this.is(':checked')) {
                    $('#ownerPartner').removeClass('hide');
                }else{
                    $('#ownerPartner').addClass('hide');
                }
            }
        });
    }

    function getContractor() {
            $.ajax({
                url: _baseURL() + '/getContractor',
                type: 'GET',
                success: function (res) {
                    if (res.status == '1') {
                        var contractorDTO = res.dto;
                        var contractor = res.dto;
                        $('#ownershipList').val(contractorDTO.ownershipTypeId).prop('disabled',true);
                        $('#pCountryId').val(contractor.pCountryId).prop('disabled',true);
                        $('#tradeLicenseNo').val(contractor.tradeLicenseNo).prop('disabled',true);
                        $('#firmName').val(contractor.firmName).prop('disabled',true);
                        $('#tpn').val(contractor.tpn).prop('disabled',true);
                        $('#pDzongkhagId').val(contractorDTO.pDzongkhagId).prop('disabled',true);
                        $('#pGewogId').val(contractorDTO.pGewogId).prop('disabled',true);
                        $('#pVillageId').val(contractorDTO.pVillageId).prop('disabled',true);
                        $('#estAddress').val(contractor.estAddress).prop('disabled',true);
                        $('#regDzongkhagId').val(contractorDTO.estDzongkhagId).prop('disabled',true);
                        $('#regEmail').val(contractor.regEmail).prop('disabled',true);
                        $('#regMobileNo').val(contractor.regMobileNo).prop('disabled',true);
                        $('#regPhoneNo').val(contractor.regPhoneNo).prop('disabled',true);
                        $('#regFaxNo').val(contractor.regFaxNo).prop('disabled',true);

                        /*var contractorHrs = contractorDTO.contractorHRs;
                        var partnerHrTr = "";
                        var hrTr = "";
                        var m = 0, n = 0;
                        var openModal = "openModal('CheckModal')";
                        for (var i in contractorHrs) {
                            if (contractorHrs[i].isPartnerOrOwner == '1') {
                                m++;
                                partnerHrTr = partnerHrTr + "<tr><td>" + m + "</td>" +
                                "<td>" + contractorHrs[i].countryName + "</td>" +
                                "<td>" + contractorHrs[i].cidNo + "</td>" +
                                "<td>" + contractorHrs[i].salutationName + "</td>" +
                                "<td>" + contractorHrs[i].name + "</td>" +
                                "<td>" + contractorHrs[i].sex + "</td>" +
                                "<td>" + contractorHrs[i].designationName + "</td>" +
                                "<td>" + contractorHrs[i].siCertificate + "</td>" +
                                "<td><input type='button' name='humanResource' value='Check for this CID' id='checkCid' class='btn btn-success' onclick="+openModal+"></td>" +
                                "<td><input type='checkbox' style='zoom:1.6' name='verifyowner0' value='1' id='checkver1'></td></tr>";

                            } else {
                                n++;
                                var href = _baseURL() + "/viewDownload?tableName=crpcontractorhumanresourceattachment&filterCol=CrpContractorHumanResourceId&filterVal="+contractorHrs[i].id;
                                hrTr = hrTr + "<tr>" +
                                "<td>" + n + "<input type='hidden' class='contractorHRid' value='"+contractorHrs[i].id +"' </td>" +
                                "<td>" + contractorHrs[i].countryName + "</td>" +
                                "<td>" + contractorHrs[i].cidNo + "</td>" +
                                "<td>" + contractorHrs[i].salutationName + "</td>" +
                                "<td>" + contractorHrs[i].name + "</td>" +
                                "<td>" + contractorHrs[i].sex + "</td>" +
                                "<td>" + contractorHrs[i].designationName + "</td>" +
                                "<td>" + contractorHrs[i].qualificationName + "</td>" +
                                "<td>" + contractorHrs[i].tradeName + "</td>" +
                                "<td>" + contractorHrs[i].joiningDate + "</td>" +
                                "<td>" + contractorHrs[i].serviceTypeName + "</td>" +
                                //"<td><a href='javascript:void(0);' class='vAttachment'>View/Download</a> </td>" +
                                "<td><a href="+href+" target='_blank'>View/Download</a> </td>" +
                                "<td><input type='button'  value='Check for this CID' id='checkCid' class='btn btn-success' onclick="+openModal+"></td>" +
                                "<td><input type='checkbox' style='zoom:1.6' name='verifyowner0' value='1' id='checkver1'></td></tr>";
                            }
                        }
                        $('#partnerDtls').find('tbody').html(partnerHrTr);
                        $('#hrTbl').find('tbody').html(hrTr);

                        var categoryClassDTOs = contractorDTO.categories;
                        var ccTr = "";
                        for (var i in categoryClassDTOs) {
                            ccTr = ccTr + "<tr><td><input class='form-control' type='checkbox' checked='checked' disabled style='width: 17px; height: 17px;'></td>" +
                            "<td>" + categoryClassDTOs[i].categoryName + "</td>" +
                            "<td><select disabled class='form-control'><option>" + categoryClassDTOs[i].aClassName + "</option></select></td></tr>";
                        }
                        $('#contractorCCTbl').find('tbody').html(ccTr);

                        var equipments = contractorDTO.equipments;
                        var eqTr = "";
                        for (var i in equipments) {
                            var href = _baseURL() + "/viewDownload?tableName=crpcontractorequipmentattachment&filterCol=CrpContractorEquipmentId&filterVal="+equipments[i].id;
                            eqTr = eqTr +
                            "<tr><td>" + (parseInt(i) + 1) + "</td>" +
                            "<td>" + equipments[i].equipmentName + "</td>" +
                            "<td></td>" +
                            "<td>" + equipments[i].registrationNo + "</td>" +
                            "<td></td>" +
                            "<td>" + equipments[i].quantity + "</td>" +
                            "<td><a href="+href+" target='_blank'>View/Download</a> </td>" +
                            "<td><input type='button' name='humanResource' value='Check for this CID' id='checkCid' class='btn btn-success'></td>" +
                            "<td><input type='checkbox' style='zoom:1.6' name='verifyowner0' value='1' id='checkver1'></td></tr>";

                        }
                        $('#equipmentTbl').find('tbody').html(eqTr);

                        var appHistoryDTOs = contractorDTO.appHistoryDTOs;

                        var appHistoryTr = "";
                        for (var i in appHistoryDTOs) {
                            appHistoryTr = appHistoryTr +
                            "<tr><td>" + appHistoryDTOs[i].appStatus + "</td>" +
                            "<td>" + appHistoryDTOs[i].userName + "</td>" +
                            "<td>" + formatAsDate(appHistoryDTOs[i].actionDate) + "</td>" +
                            "<td>"+ appHistoryDTOs[i].remarks +"</td></tr>";

                        }
                        $('#appStatusTbl').find('tbody').html(appHistoryTr);*/

                    } else {
                        warningMsg(res.text);
                    }
                }
            });

    }


    function viewDownloadAttachment(){
        $('body').on('click','.vAttachment',function(){
            var id = $(this).closest('tr').find('.contractorHRid').val();
            $.ajax({
                url: _baseURL() + '/viewDownload',
                type: 'GET',
                data: {tableName:'crpcontractorhumanresourceattachment',filterCol:'CrpContractorHumanResourceId',filterVal:id}

            });
        });
    }




    function init(){
        viewDownloadAttachment();
        getContractor();
        sCertIncorporation();
        service_check();
    }


    return {
        init:init
    };
})();

$(document).ready(function () {
        contractor_renewal.init();
    }
);