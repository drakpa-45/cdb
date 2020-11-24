/**
 * Created by user on 2/14/2020.
 */
var $check = null;
function checkBtn(checkBoxId)
{
    $check.prop('checked',true);
}

function saveAndPreview(presentClass, nextClass) {
    var content = '<h3 class="pt-3 text-center">Fee Structure</h3>' + $("#fees_structure >.div-actual").html() +
        '<h3 class="pt-3 text-center">General Information</h3>' + $("#general_Information >.div-actual").html() +
        '<h3 class="pt-3 text-center">Category Details</h3>' + $("#category_details >.div-actual").html()
        + '<h3 class="pt-3 text-center">Human Resource</h3>' + $("#human_resource_criteria >.div-actual").html() +
        '<h3 class="pt-3 text-center"> Equipment Details</h3>' + $("#equipment_details >.div-actual").html();

    $("." + presentClass + ">a").addClass('bg-blue-light text-white');
    $('.tab-pane').removeClass("active").addClass("active");
    $('.tab-content').removeClass("active").addClass("active");
    $("." + nextClass).addClass("active");
    $("." + presentClass + ">a").append("<i class='fa fa-check ml-1'></i>");

    //$("#" + nextClass).prepend(content);
}

var consultant_action = (function () {
    "use strict";
    var isSubmitted = false;

    function _baseURL() {
        return cdbGlobal.baseURL() + "/admin/consultantNRAction";
    }

    function checkHR(){

        $('body').on('click','.checkCid',function(){
            var cidNo = $(this).closest('tr').find('.cidNo').text();

            $check = $(this).closest('tr').find('.check');
            if(!cidNo){
                return;
            }
            $.ajax({
                url: cdbGlobal.baseURL() + "/consultantNR/getPersonalInfo",
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

    function getConsultantInfo() {
        var applicationNo = $('#appNoVA').val();
        if (applicationNo) {
            $.ajax({
                url: _baseURL() + '/getConsultantInfo',
                type: 'GET',
                data: {appNo: applicationNo,flag:'V'},
                success: function (res) {
                    if (res.status == '1') {
                        var consultantDTO = res.dto;
                        var consultant = consultantDTO.consultant;
                        $('#ownershipType').text(consultantDTO.ownershipTypeTxt);
                        $('#country').text(consultantDTO.countryTxt);
                        $('#tradeLicenseNo').text(consultant.tradeLicenseNo);
                        $('#firmName').text(consultant.firmName);
                        $('#tpn').text(consultant.tpn);
                        $('#pDzongkhag').text(consultantDTO.pDzongkhagTxt);
                        $('#pGewog').text(consultant.pGewogId);
                        $('#pVillage').text(consultant.pVillageId);
                        $('#estAddress').text(consultant.estAddress);
                        $('#estDzongkhag').text(consultantDTO.estDzongkhagTxt);
                        $('#regEmail').text(consultant.regEmail);
                        $('#regMobileNo').text(consultant.regMobileNo);
                        $('#regPhoneNo').text(consultant.regPhoneNo);
                        $('#regFaxNo').text(consultant.regFaxNo);

                        incorporation(consultantDTO.incAttachments);

                        var consultantHrs = consultantDTO.consultantHRs;
                        var partnerHrTr = "";
                        var hrTr = "";
                        var m = 0, n = 0;
                        var owner='';
                        for (var i in consultantHrs) {
                            var verifiedApproved = '';
                            if(consultantHrs[i].Approved == '1'){
                                verifiedApproved = verifiedApproved + "<td>(✔)</td>";
                                verifiedApproved = verifiedApproved + "<td>(✔)</td>";
                            }else if(consultantHrs[i].verified == '1'){
                                verifiedApproved = verifiedApproved + "<td>(✔)</td>";
                                verifiedApproved = verifiedApproved + "<td><input type='checkbox' style='zoom:1.6' class='check' value='1'  required=''></td>";
                            }else{
                                verifiedApproved = verifiedApproved + "<td><input type='checkbox' style='zoom:1.6' class='check' value='1'  required=''></td>";
                            }
                            if (consultantHrs[i].isPartnerOrOwner == '1') {
                                owner = consultantHrs[i].name;
                                m++;
                                partnerHrTr = partnerHrTr + "<tr><td>" + m + "</td>" +
                                "<td>" + consultantHrs[i].countryName + "</td>" +
                                "<td class='cidNo'>" + consultantHrs[i].cidNo + "</td>" +
                                "<td>" + consultantHrs[i].salutationName + "</td>" +
                                "<td>" + consultantHrs[i].name + "</td>" +
                                "<td>" + consultantHrs[i].sex + "</td>" +
                                "<td>" + consultantHrs[i].designationName + "</td>" +
                                "<td>" + ((consultantHrs[i].siCertificate == '1')?'(✔)':'')+ "</td>" +
                                "<td><input type='button' name='humanResource' value='Check for this CID' class='checkCid btn btn-success'></td>" +
                                verifiedApproved+"</tr>";
                            } else {
                                n++;
                                var attachments = '';
                                for (var j in consultantHrs[i].hrAttachments){
                                    attachments = attachments + "<a href='"+_baseURL() + "/viewDownload?documentPath="+consultantHrs[i].hrAttachments[j].documentPath+"' target='_blank'>"+consultantHrs[i].hrAttachments[j].documentName+"</a><br>";
                                }
                                var href = _baseURL() + "/viewDownload?tableName=crpconsultanthumanresourceattachment&filterCol=CrpConsultantHumanResourceId&filterVal="+consultantHrs[i].id;
                                hrTr = hrTr + "<tr>" +
                                "<td>" + n + "<input type='hidden' class='consultantHRid' value='"+consultantHrs[i].id +"' </td>" +
                                "<td>" + consultantHrs[i].countryName + "</td>" +
                                "<td class='cidNo'>" + consultantHrs[i].cidNo + "</td>" +
                                "<td>" + consultantHrs[i].salutationName + "</td>" +
                                "<td>" + consultantHrs[i].name + "</td>" +
                                "<td>" + consultantHrs[i].sex + "</td>" +
                                "<td>" + consultantHrs[i].designationName + "</td>" +
                                "<td>" + consultantHrs[i].qualificationName + "</td>" +
                                "<td>" + consultantHrs[i].tradeName + "</td>" +
                                "<td>" + nullif(consultantHrs[i].joinDate) + "</td>" +
                                "<td>" + consultantHrs[i].serviceTypeName + "</td>" +
                                    //"<td><a href='javascript:void(0);' class='vAttachment'>View/Download</a> </td>" +
                                "<td>"+attachments+"</td>" +
                                "<td><input type='button'  value='Check for this CID' class='checkCid btn btn-success'></td>" +
                                verifiedApproved+"</tr>";
                            }
                        }

                        $('#partnerDtls').find('tbody').html(partnerHrTr);
                        $('#hrTbl').find('tbody').html(hrTr);

                        var categoryClassDTOs = consultantDTO.categories;

                        var ccTr = "";
                        var tFeeAmount = 0;
                       for (var i in categoryClassDTOs) {
                           var valueee = categoryClassDTOs.value;
                           tFeeAmount = tFeeAmount + parseFloat(categoryClassDTOs[i].aAmount);
                         //  var total = valueee.();
                           /* for(var j in valueee){
                                count ++;
                                return count;
                            }*/
                            ccTr = ccTr + "<tr> <td>" + categoryClassDTOs[i].name + "</td>" +
                            "<td>"+
                            " <a href='javascript:void(0)' style='color: #006699' title='${categoryClassDTOs[i].obj1}' data-toggle='tooltip' data-placement='top' class='tooltipCSSSelector'><i class='fa fa-question-circle'></i></a>"+
                            " <input type='checkbox'  checked='checked' disabled id='asone' value='${c.id},${c.text}' class='ticked' name='categories[${i.index}].appliedServiceID'>" + categoryClassDTOs[i].value + "&nbsp; &nbsp; &nbsp;"+
                           "</td> " +
                                "<td>"+categoryClassDTOs[i].aAmount+"</td>"+
                            "</tr>";
                        }
                        var tfoot = "<tr><td colspan='2' align='right'>Total</td><td>"+tFeeAmount+"</td> ";
                        $('#consultantCCTbl').find('tbody').html(ccTr);
                        $('#consultantCCTbl').find('tfoot').html(tfoot);

                        var equipments = consultantDTO.equipments;
                        var openModalEq = "openModalEq('CheckModalEquipment')";
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
                            "<td><input type='button' value='Check for Equipment' class='equipmentCheck btn btn-success'></td>" +
                            verifiedApprovedEq+"</tr>";

                        }
                        $('#equipmentTbl').find('tbody').html(eqTr);

                        var appHistoryDTOs = consultantDTO.appHistoryDTOs;

                        var appHistoryTr = "";
                        for (var i in appHistoryDTOs) {
                            var actionTakenBy = appHistoryDTOs[i].userName;
                            actionTakenBy = (actionTakenBy=='null')?'By citizen':actionTakenBy

                            actionTakenBy = (actionTakenBy==null)? consultantHrs[i].cidNo +'('+ consultantHrs[i].name+ ')':actionTakenBy
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
            var id = $(this).closest('tr').find('.consultantHRid').val();
            $.ajax({
                url: _baseURL() + '/viewDownload',
                type: 'GET',
                data: {tableName:'crpconsultanthumanresourceattachment',filterCol:'CrpConsultantHumanResourceId',filterVal:id}
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

    function getConsultantInfoForPayment() {
        var applicationNo = $('#appNoPayment').val();
        if (applicationNo) {
            $.ajax({
                url: _baseURL() + '/getConsultantInfo',
                type: 'GET',
                data: {appNo: applicationNo,flag:'P'},
                success: function (res) {
                    if (res.status == '1') {
                        var consultantDTO = res.dto;
                        var consultant = consultantDTO.consultant;
                        $('#ownershipType').text(consultantDTO.ownershipTypeTxt);
                        $('#country').text(consultantDTO.countryTxt);
                        $('#tradeLicenseNo').text(consultant.tradeLicenseNo);
                        $('#firmName').text(consultant.firmName);
                        $('#tpn').text(consultant.tpn);
                        $('#pDzongkhag').text(consultantDTO.pDzongkhagTxt);
                        $('#pGewog').text(consultant.pGewogId);
                        $('#pVillage').text(consultant.pVillageId);
                        $('#estAddress').text(consultant.estAddress);
                        $('#estDzongkhag').text(consultantDTO.estDzongkhagTxt);
                        $('#regEmail').text(consultant.regEmail);
                        $('#regMobileNo').text(consultant.regMobileNo);
                        $('#regPhoneNo').text(consultant.regPhoneNo);
                        $('#regFaxNo').text(consultant.regFaxNo);

                        var consultantHrs = consultantDTO.consultantHRs;
                        var partnerHrTr = "";
                        var m = 0;
                        for (var i in consultantHrs) {
                            if (consultantHrs[i].isPartnerOrOwner == '1') {
                                m++;
                                partnerHrTr = partnerHrTr + "<tr><td>" + m + "</td>" +
                                "<td>" + consultantHrs[i].countryName + "</td>" +
                                "<td class='cidNo'>" + consultantHrs[i].cidNo + "</td>" +
                                "<td>" + consultantHrs[i].salutationName + "</td>" +
                                "<td>" + consultantHrs[i].name + "</td>" +
                                "<td>" + consultantHrs[i].sex + "</td>" +
                                "<td>" + consultantHrs[i].designationName + "</td>" +
                                "<td>" + ((consultantHrs[i].siCertificate == '1')?'(✔)':'') + "</td>" +
                                "</tr>";
                            }
                        }
                        $('#partnerDtls').find('tbody').html(partnerHrTr);

                        var categoryClassDTOs = consultantDTO.categories;
                        var ccTr = "";
                        var tFeeAmount = 0;
                        var tApplAmount = 0,tVerAmount= 0,tApprAmount=0;
                        for (var i in categoryClassDTOs) {

                            tApplAmount += parseFloat(categoryClassDTOs[i].aAmount);
                            tVerAmount += parseFloat(categoryClassDTOs[i].aAmount);
                            tApprAmount += parseFloat(categoryClassDTOs[i].aAmount);
                            ccTr = ccTr + "<tr> <td>" + categoryClassDTOs[i].name + "</td>" +
                            "<td>" + categoryClassDTOs[i].name + "</td>" +
                            "<td>"+
                            " <a href='javascript:void(0)' style='color: #006699' title='${categoryClassDTOs[i].obj1}' data-toggle='tooltip' data-placement='top' class='tooltipCSSSelector'><i class='fa fa-question-circle'></i></a>"+
                            " <input type='checkbox'  checked='checked' disabled id='asone' value='${c.id},${c.text}' class='ticked' name='categories[${i.index}].appliedServiceID'>" + categoryClassDTOs[i].value + "&nbsp; &nbsp; &nbsp;"+
                            "</td> " +
                            "<td> "+categoryClassDTOs[i].aAmount+"</td>"+
                            "<td>"+
                            " <a href='javascript:void(0)' style='color: #006699' title='${categoryClassDTOs[i].obj1}' data-toggle='tooltip' data-placement='top' class='tooltipCSSSelector'><i class='fa fa-question-circle'></i></a>"+
                            " <input type='checkbox'  checked='checked' disabled id='asone' value='${c.id},${c.text}' class='ticked' name='categories[${i.index}].appliedServiceID'>" + categoryClassDTOs[i].value + "&nbsp; &nbsp; &nbsp;"+
                            "</td> " +
                            "<td> "+categoryClassDTOs[i].aAmount+"</td>"+
                            "<td>"+
                            " <a href='javascript:void(0)' style='color: #006699' title='${categoryClassDTOs[i].obj1}' data-toggle='tooltip' data-placement='top' class='tooltipCSSSelector'><i class='fa fa-question-circle'></i></a>"+
                            " <input type='checkbox'  checked='checked' disabled id='asone' value='${c.id},${c.text}' class='ticked' name='categories[${i.index}].appliedServiceID'>" + categoryClassDTOs[i].value + "&nbsp; &nbsp; &nbsp;"+
                            "</td> " +
                            "<td> "+categoryClassDTOs[i].aAmount+"</td>"+
                            "</tr>";
                        }
                        var tfoot = "<tr><td colspan='2' align='right'>Total</td><td colspan='2'>"+tApplAmount+"</td>" +
                            "<td colspan='2'>"+tVerAmount+"</td><td colspan='2'>"+tApprAmount+"</td> ";

                        $('#consultantCCTbl').find('tbody').html(ccTr);
                        $('#consultantCCTbl').find('tfoot').html(tfoot);
                        $('#paymentAmount').val(tApprAmount);
                        $('#cdbNo').val(consultantDTO.cdbNo);

                        var appHistoryDTOs = consultantDTO.appHistoryDTOs;

                        var appHistoryTr = "";
                        for (var i in appHistoryDTOs) {
                            var actionTakenBy = appHistoryDTOs[i].userName;
                            actionTakenBy = (actionTakenBy=='null')?'By Citizen':actionTakenBy
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
    function showConfirmation(){
        $('#confirmationModel').modal('show');
        $('#targetId').val('acknowledgementmessage');
        $('#messages').html('You are about to verify/approve for payment/approve this application. Are you sure to proceed ?');
    }

    function incorporation(data){
        if(data){
            $('#cIncorporation').removeClass('hide');
            var tr = '';
            for(var i in data){
                tr = tr + "<tr>"+
                "<td></td>" +
                "<td>"+data[i].documentName+"</td>"+
                "<td><a href='"+_baseURL() + "/viewDownload?documentPath="+data[i].documentPath+"' target='_blank'> View </a></td>" +
                "</tr>";
            }
            $('#IncCertificateTbl').find('tbody').html(tr);
        }else{
            $('#cIncorporation').addClass('hide');
        }
    }

    function paymentUpdate() {
        $('#btnSave').on('click', function (e) {
            $('#consultantPaymentForm').validate({
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

    function validateOwner(){
        $('#partnerDtls').on('change','.check',function(){
            var allChecked = false;
            $('#partnerDtls').find('.check').each(function(){
                if($(this).is(':checked') == true){
                    allChecked = true;
                }else{
                    allChecked = false;
                    return false;
                }
            });
            if(allChecked == true){
                $('#nextGIBtn').prop('disabled',false);
            }else{
                $('#nextGIBtn').prop('disabled',true);
            }
        });
    }
    function validateHr(){
        $('#hrTbl').on('change','.check',function(){
            var allChecked = false;
            $('#hrTbl').find('.check').each(function(){
                if($(this).is(':checked') == true){
                    allChecked = true;
                }else{
                    allChecked = false;
                    return false;
                }
            });
            if(allChecked == true){
                $('#nextHRBtn').prop('disabled',false);
            }else{
                $('#nextHRBtn').prop('disabled',true);
            }
        });
    }
    function validateEq(){
        $('#equipmentTbl').on('change','.check',function(){
            var allChecked = false;
            $('#equipmentTbl').find('.check').each(function(){
                if($(this).is(':checked') == true){
                    allChecked = true;
                }else{
                    allChecked = false;
                    return false;
                }
            });
            if(allChecked == true){
                $('#btnValEqNext').prop('disabled',false);
            }else{
                $('#btnValEqNext').prop('disabled',true);
            }
        });
    }
    function init(){
        viewDownloadAttachment();
        approve();
        reject();
        getConsultantInfoForPayment();
        paymentUpdate();
        checkHR();
        validateOwner();
        validateHr();
        validateEq();
    }


    return {
        verify: verify,
        getConsultantInfo: getConsultantInfo,
        init:init
    };
})();

$(document).ready(function () {
        consultant_action.verify();
        consultant_action.getConsultantInfo();
        consultant_action.init();
    }
);