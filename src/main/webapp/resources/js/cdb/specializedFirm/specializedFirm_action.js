/**
 * Created by user on 2/14/2020.
 */
var $check = null;
function checkBtn(checkBoxId)
{
    $check.prop('checked',true);
}

var specializedFirm_action = (function () {
    "use strict";
    var isSubmitted = false;

    function _baseURL() {
        return cdbGlobal.baseURL() + "/admin/specializedFirm/action";
    }
    function checkHR(){
        $('body').on('click','.checkCid',function(){
            //var modal = $(this).closest('.modal').attr('id');
            var cidNo = $(this).closest('tr').find('.cidNo').text();
            $check = $(this).closest('tr').find('.check');
            if(!cidNo){
                return;
            }
            $.ajax({
                url: cdbGlobal.baseURL() + "/specializedFirm/getPersonalInfo",
                type: 'GET',
                data: {cidNo: cidNo},
                success: function (res) {
                    if (res.status == '1') {
                        var dto = res.dto;
                        $('#nameM').text(dto.fullName);
                        $('#sexM').text(dto.sex);
                        $('#dzongkhagM').text(dto.dzongkhagNmae);
                        $('#gewogM').text(dto.gowegName);
                        $('#villageM').text(dto.villageName);
                        $('#dobM').text(dto.dob);
                        var imagelink='https://www.citizenservices.gov.bt/BtImgWS/ImageServlet?type=PH&cidNo='+cidNo;
                        $('#photoM').html("<img src='"+imagelink+"'  width='200px'  height='200px' class='pull-right'/>");
                        $("#hrModal").modal('show');
                    }
                }
            });
        });
    }

    function getSpecializedFirmInfo() {
        var applicationNo = $('#appNoVA').val();
        if (applicationNo) {
            $.ajax({
                url: _baseURL() + '/getSpecializedFirmInfo',
                type: 'GET',
                data: {appNo: applicationNo,flag:'V'},
                success: function (res) {
                    if (res.status == '1') {
                        var specializedFirmDTO = res.dto;
                        var specializedFirm = specializedFirmDTO.specializedFirm;
                        $('#ownershipType').text(specializedFirmDTO.ownershipTypeTxt);
                        $('#country').text(specializedFirmDTO.countryTxt);
                        $('#tradeLicenseNo').text(specializedFirm.tradeLicenseNo);
                        $('#firmName').text(specializedFirm.firmName);
                        $('#tpn').text(specializedFirm.tpn);
                        $('#pDzongkhag').text(specializedFirmDTO.pDzongkhagTxt);
                        $('#pGewog').text(specializedFirm.pGewogId);
                        $('#pVillage').text(specializedFirm.pVillageId);
                        $('#estAddress').text(specializedFirm.estAddress);
                        $('#estDzongkhag').text(specializedFirmDTO.estDzongkhagTxt);
                        $('#regEmail').text(specializedFirm.regEmail);
                        $('#regMobileNo').text(specializedFirm.regMobileNo);
                        $('#regPhoneNo').text(specializedFirm.regPhoneNo);
                        $('#regFaxNo').text(specializedFirm.regFaxNo);

                        var specializedFirmHrs = specializedFirmDTO.spFirmHRs;
                        var partnerHrTr = "";
                        var hrTr = "";
                        var m = 0, n = 0;
                        var owner='';
                        for (var i in specializedFirmHrs) {
                            var verifiedApproved = '';
                            if(specializedFirmHrs[i].Approved == '1'){
                                verifiedApproved = verifiedApproved + "<td>(✔)</td>";
                                verifiedApproved = verifiedApproved + "<td>(✔)</td>";
                            }else if(specializedFirmHrs[i].verified == '1'){
                                verifiedApproved = verifiedApproved + "<td>(✔)</td>";
                                verifiedApproved = verifiedApproved + "<td><input type='checkbox' style='zoom:1.6' class='check'  value='1'  required='true'></td>";
                            }else{
                                verifiedApproved = verifiedApproved + "<td><input type='checkbox' style='zoom:1.6' class='check' value='1'  required='true'></td>";
                            }
                            if (specializedFirmHrs[i].isPartnerOrOwner == '1') {
                                owner = specializedFirmHrs[i].name;
                                m++;
                                partnerHrTr = partnerHrTr + "<tr><td>" + m + "</td>" +
                                "<td>" + specializedFirmHrs[i].countryName + "</td>" +
                                "<td class='cidNo'>" + specializedFirmHrs[i].cidNo + "</td>" +
                                "<td>" + specializedFirmHrs[i].salutationName + "</td>" +
                                "<td>" + specializedFirmHrs[i].name + "</td>" +
                                "<td>" + specializedFirmHrs[i].sex + "</td>" +
                                "<td>" + specializedFirmHrs[i].designationName + "</td>" +
                                "<td>" + ((specializedFirmHrs[i].siCertificate == '1')?'(✔)':'') + "</td>" +
                                "<td><input type='button' name='humanResource' value='Check for this CID' class='checkCid btn btn-success'></td>" +
                                verifiedApproved+"</tr>";
                            } else {
                                n++;
                                var attachments = '';
                                for (var j in specializedFirmHrs[i].hrAttachments){
                                    attachments = attachments + "<a href='"+_baseURL() + "/viewDownload?documentPath="+specializedFirmHrs[i].hrAttachments[j].documentPath+"' target='_blank'>"+specializedFirmHrs[i].hrAttachments[j].documentName+"</a><br>";
                                }
                                var href = _baseURL() + "/viewDownload?tableName=crpspecializedtradehumanresourceattachment&filterCol=CrpSpecializedtradeHumanResourceId&filterVal="+specializedFirmHrs[i].id;
                                hrTr = hrTr + "<tr>" +
                                "<td>" + n + "<input type='hidden' class='specializedtradeHRid' value='"+specializedFirmHrs[i].id +"' </td>" +
                                "<td>" + specializedFirmHrs[i].countryName + "</td>" +
                                "<td class='cidNo'>" + specializedFirmHrs[i].cidNo + "</td>" +
                                "<td>" + specializedFirmHrs[i].salutationName + "</td>" +
                                "<td>" + specializedFirmHrs[i].name + "</td>" +
                                "<td>" + specializedFirmHrs[i].sex + "</td>" +
                                "<td>" + specializedFirmHrs[i].designationName + "</td>" +
                                "<td>" + specializedFirmHrs[i].qualificationName + "</td>" +
                                "<td>" + specializedFirmHrs[i].tradeName + "</td>" +
                                "<td>" + nullif(specializedFirmHrs[i].joinDate) + "</td>" +
                                "<td>" + specializedFirmHrs[i].serviceTypeName + "</td>" +
                                    //"<td><a href='javascript:void(0);' class='vAttachment'>View/Download</a> </td>" +
                                "<td>"+attachments+"</td>" +
                                "<td><input type='button'  value='Check for this CID' class='checkCid btn btn-success'></td>" +
                                verifiedApproved+"</tr>";
                            }
                        }

                        $('#partnerDtls').find('tbody').html(partnerHrTr);
                        $('#hrTbl').find('tbody').html(hrTr);

                        var specializedFirmIncoAttachments = "";

                        var docInco = "", d=0;
                        for (var k in specializedFirmDTO.incAttachments){
                            specializedFirmIncoAttachments = specializedFirmIncoAttachments + "<a href='"+_baseURL() + "/viewDownload?documentPath="+specializedFirmDTO.incAttachments[k].documentPath+"' target='_blank'>"+specializedFirmDTO.incAttachments[k].documentName+"</a><br>";
                        }
                        docInco = docInco + "<tr><td>" + docInco + "</td>" +
                        "<td>"+specializedFirmIncoAttachments+"</td>"+"</tr>";

                        $('#certificateTbl').find('tbody').html(docInco);

                        var categoryClassDTOs = specializedFirmDTO.categories;
                        var ccTr = "";
                        var tFeeAmount = 0;
                        for (var i in categoryClassDTOs) {
                            tFeeAmount += parseFloat(categoryClassDTOs[i].aAmount);
                            ccTr = ccTr + "<tr><td><input class='form-control' type='checkbox' checked='checked' disabled style='width: 17px; height: 17px;'></td>" +
                            "<td>" + categoryClassDTOs[i].categoryName + "</td>" +
                            "<td>"+categoryClassDTOs[i].aAmount+"</td></tr>";
                        }
                        var tfoot = "<tr><td colspan='2' align='right'>Total</td><td>"+tFeeAmount+"</td> ";
                        $('#specalizedFirmCCTbl').find('tbody').html(ccTr);
                        $('#specalizedFirmCCTbl').find('tfoot').html(tfoot);

                        var equipments = specializedFirmDTO.equipments;
                        var eqTr = "";
                        for (var i in equipments) {
                            var verifiedApprovedEq = '';
                            if(equipments[i].approved == '1'){
                                verifiedApprovedEq = verifiedApprovedEq + "<td>(✔)</td>";
                                verifiedApprovedEq = verifiedApprovedEq + "<td>(✔)</td>";
                            }
                            else if(equipments[i].verified == '1'){
                                verifiedApprovedEq = verifiedApprovedEq + "<td>(✔)</td>";
                                verifiedApprovedEq = verifiedApprovedEq + "<td><input type='checkbox' style='zoom:1.6' name='approveEq' value='1'  class='check' required=''></td>";
                            }else{
                                verifiedApprovedEq = verifiedApprovedEq + "<td><input type='checkbox' style='zoom:1.6' name='verifyEq' value='1'  class='check' required=''></td>";
                            }
                            var attachment = '';
                            for (var j in equipments[i].eqAttachments){
                                attachment = attachment + "<a href='"+_baseURL() + "/viewDownload?documentPath="+equipments[i].eqAttachments[j].documentPath+"' target='_blank'>"+equipments[i].eqAttachments[j].documentName+"</a><br>";
                            }
                            eqTr = eqTr +
                            "<tr><td>" + (parseInt(i) + 1) + "</td>" +
                            "<td>" + equipments[i].equipmentName + "</td>" +
                            "<td></td>" +
                            "<td>" + equipments[i].registrationNo + "</td>" +
                            "<td>"+owner+"</td>" +
                            "<td>" + equipments[i].quantity + "</td>" +
                            "<td style='text-align: center'>"+attachment+"</td>" +
                            "<td><input type='button' name='humanResource' value='Check for Equipment' class='equipmentCheck btn btn-success'></td>" +
                            verifiedApprovedEq+"</tr>";
                        }
                        $('#equipmentTbl').find('tbody').html(eqTr);

                        var appHistoryDTOs = specializedFirmDTO.appHistoryDTOs;

                        var appHistoryTr = "";

                        for (var i in appHistoryDTOs) {
                            var actionTakenBy = appHistoryDTOs[i].userName;
                            //actionTakenBy = (actionTakenBy==null)?'By Citizen':actionTakenBy
                            actionTakenBy = (actionTakenBy==null)? specializedFirmHrs[i].cidNo +'(Applicants CID/work permit number)':actionTakenBy

                            appHistoryTr = appHistoryTr +
                            "<tr><td>" + appHistoryDTOs[i].appStatus + "</td>" +
                            "<td>" + actionTakenBy + "</td>" +
                            "<td>" + formatAsDate(appHistoryDTOs[i].actionDate) + "</td>" +
                            "<td>"+ appHistoryDTOs[i].remarks +"</td></tr>";

                        }
                        $('#appStatusTbl').find('tbody').html(appHistoryTr);

                    } else {
                        warningMsg(res.text);
                    }
                }
            });
        }
        function nullif(val){
            if(val == null || val == 'null'){
                val = ''
            }
            return val;
        }
    }

    function viewDownloadAttachment(){
        $('body').on('click','.vAttachment',function(){
            var id = $(this).closest('tr').find('.specializedtradeHRid').val();
            $.ajax({
                url: _baseURL() + '/viewDownload',
                type: 'GET',
                data: {tableName:'crpspecializedtradehumanresourceattachment',filterCol:'CrpSpecializedtradeHumanResourceId',filterVal:id}

            });
        });
    }
    function verify() {
        $('#btnVerify').on('click', function (e) {
            $.ajax({
                url: _baseURL() + '/verify',
                type: 'POST',
                data: {appNo:$('#appNoVA').val(),vRemarks:$('#vRemarks').val()},
                success: function (res) {
                    if (res.status == '1') {
                        successMsg(res.text, _baseURL());
                    } else {
                        warningMsg(res.text);
                    }
                }
            });
        })
    }

    function approve() {
        $('#btnApprove').on('click', function (e) {
            $.ajax({
                url: _baseURL() + '/approve',
                type: 'POST',
                data: {appNo:$('#appNoVA').val(),remarks:$('#vRemarks').val()},
                success: function (res) {
                    if (res.status == '1') {
                        successMsg(res.text, _baseURL());
                    } else {
                        warningMsg(res.text);
                    }
                }
            });
        })
    }

    function reject() {
        $('#btnReject').on('click', function (e) {
            $.ajax({
                url: _baseURL() + '/reject',
                type: 'POST',
                data: {appNo:$('#appNoVA').val(),remarks:$('#vRemarks').val()},
                success: function (res) {
                    if (res.status == '1') {
                        successMsg(res.text, _baseURL());
                    } else {
                        warningMsg(res.text);
                    }
                }
            });
        })
    }

    function sendBack() {
        $('#btnSendBack').on('click', function (e) {
            $.ajax({
                url: _baseURL() + '/sendBack',
                type: 'POST',
                data: {appNo:$('#appNoVA').val(),remarks:$('#vRemarks').val()},
                success: function (res) {
                    if (res.status == '1') {
                        successMsg(res.text, _baseURL());
                    } else {
                        warningMsg(res.text);
                    }
                }
            });
        })
    }


    function getSpecializedFirmInfoForPayment() {
        var applicationNo = $('#appNoPayment').val();
        if (applicationNo) {
            $.ajax({
                url: _baseURL() + '/getSpecializedFirmInfo',
                type: 'GET',
                data: {appNo: applicationNo,flag:'P'},
                success: function (res) {
                    if (res.status == '1') {
                        var specializedFirmDTO = res.dto;
                        var specializedFirm = specializedFirmDTO.specializedFirm;
                        $('#ownershipType').text(specializedFirmDTO.ownershipTypeTxt);
                        $('#country').text(specializedFirmDTO.countryTxt);
                        $('#tradeLicenseNo').text(specializedFirm.tradeLicenseNo);
                        $('#firmName').text(specializedFirm.firmName);
                        $('#tpn').text(specializedFirm.tpn);
                        $('#estAddress').text(specializedFirm.estAddress);
                        $('#estDzongkhag').text(specializedFirmDTO.estDzongkhagTxt);
                        $('#regEmail').text(specializedFirm.regEmail);
                        $('#regMobileNo').text(specializedFirm.regMobileNo);
                        $('#regPhoneNo').text(specializedFirm.regPhoneNo);
                        $('#regFaxNo').text(specializedFirm.regFaxNo);
                        var specializedFirmHrs = specializedFirmDTO.spFirmHRs;
                        var partnerHrTr = "";
                        var m = 0;
                        for (var i in specializedFirmHrs) {
                            if (specializedFirmHrs[i].isPartnerOrOwner == '1') {
                                m++;
                                partnerHrTr = partnerHrTr + "<tr><td>" + m + "</td>" +
                                "<td>" + specializedFirmHrs[i].countryName + "</td>" +
                                "<td class='cidNo'>" + specializedFirmHrs[i].cidNo + "</td>" +
                                "<td>" + specializedFirmHrs[i].salutationName + "</td>" +
                                "<td>" + specializedFirmHrs[i].name + "</td>" +
                                "<td>" + specializedFirmHrs[i].sex + "</td>" +
                                "<td>" + specializedFirmHrs[i].designationName + "</td>" +
                                "<td>" + ((specializedFirmHrs[i].siCertificate == '1')?'(✔)':'') + "</td>" +
                                "</tr>";
                            }
                        }
                        $('#partnerDtls').find('tbody').html(partnerHrTr);

                        var categoryClassDTOs = specializedFirmDTO.categories;
                        var ccTr = "";
                        var tApplAmount = 0,tVerAmount= 0,tApprAmount=0;
                        for (var i in categoryClassDTOs) {
                            tApplAmount += parseFloat(categoryClassDTOs[i].aAmount);
                            tVerAmount += parseFloat(categoryClassDTOs[i].vAmount);
                            tApprAmount += parseFloat(categoryClassDTOs[i].apAmount);
                            ccTr = ccTr + "<tr><td><input class='form-control' type='checkbox' checked='checked' disabled style='width: 17px; height: 17px;'></td>" +
                            "<td>" + categoryClassDTOs[i].categoryName + "</td>" +
                            "<td>" + categoryClassDTOs[i].aClassId + "</td>" +
                            "<td class='fee'>"+categoryClassDTOs[i].aAmount+"</td>" +
                            "<td>" + categoryClassDTOs[i].vClassName + "</td>" +
                            "<td class='fee'>"+categoryClassDTOs[i].vAmount+"</td>" +
                            "<td>" + categoryClassDTOs[i].apClassName + "</td>" +
                            "<td class='fee'>"+categoryClassDTOs[i].apAmount+"</td>" +
                            "</tr>";
                        }
                        var tfoot = "<tr><td colspan='3' align='right'>Total</td><td>"+tApplAmount+"</td>" +
                            "<td></td>" +"<td>"+tVerAmount+"</td><td></td>" +"<td>"+tApprAmount+"</td> ";

                        $('#spFirmCCTbl').find('tbody').html(ccTr);
                        $('#spFirmCCTbl').find('tfoot').html(tfoot);
                        $('#paymentAmount').val(tApprAmount);
                        $('#cdbNo').val(specializedFirmDTO.cdbNo);

                        var appHistoryDTOs = specializedFirmDTO.appHistoryDTOs;

                        var appHistoryTr = "";

                        for (var i in appHistoryDTOs) {
                            var actionTakenBy = appHistoryDTOs[i].userName;
                            //actionTakenBy = (actionTakenBy==null)?'By Citizen':actionTakenBy
                            actionTakenBy = (actionTakenBy==null)? specializedFirmHrs[i].cidNo +'(Applicants CID/work permit number)':actionTakenBy
                            appHistoryTr = appHistoryTr +
                            "<tr><td>" + appHistoryDTOs[i].appStatus + "</td>" +
                            "<td>" + actionTakenBy + "</td>" +
                            "<td>" + formatAsDate(appHistoryDTOs[i].actionDate) + "</td>" +
                            "<td>"+ appHistoryDTOs[i].remarks +"</td></tr>";

                        }
                        $('#appStatusTbl').find('tbody').html(appHistoryTr);
                        } else {
                        warningMsg(res.text);
                    }
                }
            });
        }
    }

    function paymentUpdate() {
        $('#btnSave').on('click', function (e) {
            $('#specializedFirmPaymentForm').validate({
                submitHandler: function (form) {
                    $.ajax({
                        url: _baseURL() + '/paymentUpdate',
                        type: 'POST',
                        data: $(form).serializeArray(),
                        success: function (res) {
                            if (res.status == '1') {
                                successMsg(res.text, _baseURL());
                            } else {
                                warningMsg(res.text);
                            }
                        }
                    });
                }
            })
        })
    }

    function init(){
        viewDownloadAttachment();
        approve();
        reject();
        getSpecializedFirmInfoForPayment();
        paymentUpdate();
        checkHR();
        sendBack();
    }
    return {
        verify: verify,
        getSpecializedFirmInfo: getSpecializedFirmInfo,
        init:init
    };
})();

$(document).ready(function () {
        specializedFirm_action.verify();
        specializedFirm_action.getSpecializedFirmInfo();
        specializedFirm_action.init();
    }
);