/**
 * Created by user on 2/14/2020.
 */
var $check = null;
function checkBtn(checkBoxId) {
    $check.prop('checked',true);
    if (checkBoxId == "owner") {
        $('#nextGIBtn').prop('disabled', false);
    } else if(checkBoxId == 'equipment'){
        $('#btnValEqNext').prop('disabled', false);
    }else{
        $('#nextHRBtn').prop('disabled', false);
    }
}

function nextTab(presentClass) {
    var $this =  $('#'+presentClass);
    var nextClass = "";
    $this.nextAll().each(function() {
        if($(this).hasClass('hide') == false){
            nextClass =$(this).attr('id'); //since id and class have been kept same
            return false;
        }
    });
    if(nextClass == "saveAndPreview") { //nextAndPreview
        $("." + presentClass + ">a").addClass('bg-blue text-white');
        $('.tab-pane').removeClass("active").addClass("active");
        $('.tab-content').removeClass("active").addClass("active");
        $("." + nextClass).addClass("active");
        $("." + presentClass + ">a").append("<i class='fa fa-check ml-1'></i>");
        $('.nextBackBtn').addClass('hide');
    }else{ //next
        $("." + presentClass + ">a").addClass('bg-blue text-white');
        $('.tab-pane').removeClass("active");
        $('.tab-content').removeClass("active");
        $("." + nextClass).addClass("active");
        $("." + presentClass + ">a").append("<i class='fa fa-check ml-1'></i>");
        $('.nextBackBtn').removeClass('hide');
    }
}
function backTab(presentClass) {
    var $this =  $('#'+presentClass);
    var prevClass = "";
    $this.prevAll().each(function() {
        if($(this).hasClass('hide')==false){
            prevClass = $(this).attr('id'); //since id and class have been kept same
            return false;
        }
    });
    $("." + presentClass + ">a").removeClass('bg-blue text-white');
    $('.tab-pane').removeClass("active");
    $('.tab-content').removeClass("active");
    $("." + prevClass).addClass("active");
    $("." + presentClass + ">a").find(".fa-check").remove();
    $("." + prevClass + ">a").find(".fa-check").remove();
}

var contractorRCAction = (function () {
    "use strict";
    var isSubmitted = false;

    function _baseURL() {
        return cdbGlobal.baseURL() + "/admin/contractorRCAction";
    }

    function getAppliedServices(){
        var applicationNo = $('#appNoVA').val();
        if(!applicationNo){
            return;
        }
        $.ajax({
            url: _baseURL() + '/getAppliedServices',
            type: 'GET',
            data: {appNo: applicationNo},
            success: function (res) {
                getContractorInfo();
                for(var i in res){
                    if(res[i].serviceRefNo == '4'){
                        $('#changeOfOwnerId').prop('checked',true);
                    }
                    if(res[i].serviceRefNo == '6'){
                        $('#changeOfLocation').prop('checked',true);
                    }
                    if(res[i].serviceRefNo == '7'){
                        $('#upgradeDowngrade').prop('checked',true);
                        $('.category_details').removeClass('hide');
                        getProposedCategories();
                    }
                    if(res[i].serviceRefNo == '8'){
                        $('#updateHR').prop('checked',true);
                        $('.human_resource_criteria').removeClass('hide');
                        getHrsEQs('H');
                    }
                    if(res[i].serviceRefNo == '9'){
                        $('#updateEq').prop('checked',true);
                        $('.equipment_details').removeClass('hide');
                        getHrsEQs('E');
                    }
                    if(res[i].serviceRefNo == '10'){
                        $('#changeOfFirmName').prop('checked',true);
                    }
                    if(res[i].serviceRefNo == '12'){
                        $('#Incorporation').prop('checked',true);
                    }
                }
            }
        });
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
                url: cdbGlobal.baseURL() + "/contractorNR/getPersonalInfo",
                type: 'GET',
                data: {cidNo: cidNo,type:"check"},
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
                        $("#closeModal").modal('show');
                    }
                }
            });
        });

        $('body').on('click','.checkCidHr',function(){
            var cidNo = $(this).closest('tr').find('.cidNo').text();
            $check = $(this).closest('tr').find('.check');
            if(!cidNo){
                return;
            }
            $.ajax({
                url: cdbGlobal.baseURL() + "/contractorNR/getPersonalInfo",
                type: 'GET',
                data: {cidNo: cidNo,type:"check"},
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
                        $("#closeModal1").modal('show');

                        var employeeDetailsDTO = dto.employeeDetailsDTOs;
                        var empDtls ="",empDtls1="",empDtls2="";
                        //alert(employeeDetailsDTO != null);
                        if(employeeDetailsDTO !=null){
                            $('#engagedId').show();
                            for(var i in employeeDetailsDTO){
                                empDtls = empDtls +
                                "<tr><td>" + (parseInt(i) + 1) + "</td>" +
                                "<td>" +  + "</td>" +
                                "<td>" + + "</td>" +
                                "<td>" + employeeDetailsDTO[i].contractorCDBNo+ "</td>" +
                                "<td>" + employeeDetailsDTO[i].contractorFirmname + "</td>" +"</tr>";
                            }
                            $('#employeeDTLS').find('tbody').html(empDtls);

                            for(var i in employeeDetailsDTO){
                                empDtls1 = empDtls1 +
                                "<tr><td>" + (parseInt(i) + 1) + "</td>" +
                                "<td>" +  + "</td>" +
                                "<td>" + + "</td>" +
                                "<td>" + employeeDetailsDTO[i].consultantCDBNo+ "</td>" +
                                "<td>" + employeeDetailsDTO[i].consultantFirmname + "</td>" +"</tr>";
                            }
                            $('#employeeDTLS1').find('tbody').html(empDtls1);

                            for(var i in employeeDetailsDTO){
                                empDtls2 = empDtls2 +
                                "<tr><td>" + (parseInt(i) + 1) + "</td>" +
                                "<td>" +  + "</td>" +
                                "<td>" + + "</td>" +
                                "<td>" + employeeDetailsDTO[i].spCDBNo+ "</td>" +
                                "<td>" + employeeDetailsDTO[i].spFirmname + "</td>" +"</tr>";
                            }
                            $('#employeeDTLS2').find('tbody').html(empDtls2);

                            $('#cidNumber').text(dto.cidNo); $('#hrName').text((dto.fullName));
                        } else{
                            $('#notEngagedId').show();
                        }
                    }
                }
            });
        });
    }

    function getContractorInfo() {
        var applicationNo = $('#appNoVA').val();
        if (applicationNo) {
            $.ajax({
                url: _baseURL() + '/getContractorInfo',
                type: 'GET',
                data: {appNo: applicationNo,flag:'V'},
                success: function (res) {
                    debugger;
                    if (res.status == '1') {
                        var contractorDTO = res.dto;
                        var contractor = contractorDTO.contractor;
                        $('#ownershipType').text(contractorDTO.ownershipTypeTxt);
                        $('#country').text(contractorDTO.countryTxt);
                        $('#tradeLicenseNo').text(contractor.tradeLicenseNo);
                        $('#firmName').text(contractor.firmName);
                        $('#tpn').text(contractor.tpn);
                        $('#pDzongkhag').text(contractorDTO.pDzongkhagTxt);
                        $('#pGewog').text(contractorDTO.pGewogTxt);
                        $('#pVillage').text(contractorDTO.pVillageTxt);
                        $('#estAddress').text(contractor.estAddress);
                        $('#estDzongkhag').text(contractorDTO.estDzongkhagTxt);
                        $('#regEmail').text(contractor.regEmail);
                        $('#regMobileNo').text(contractor.regMobileNo);
                        $('#regPhoneNo').text(contractor.regPhoneNo);
                        $('#regFaxNo').text(contractor.regFaxNo);
                        $('#oldfirmName').text(contractorDTO.oldFirmName);
                        $('#oldestAddress').text(contractorDTO.oldEstbAddress);
                        $('#oldestDzongkhag').text(contractorDTO.oldDzongkhag);

                        incorporation(contractorDTO.incAttachments);

                        var appHistoryDTOs = contractorDTO.appHistoryDTOs;

                        var appHistoryTr = "";

                        for (var i in appHistoryDTOs) {
                            var actionTakenBy = appHistoryDTOs[i].userName;
                            actionTakenBy = (actionTakenBy==null)?'By Citizen':actionTakenBy;
                            appHistoryTr = appHistoryTr +
                            "<tr><td>" + appHistoryDTOs[i].appStatus + "</td>" +
                            "<td>" + actionTakenBy + "</td>" +
                            "<td>" + formatAsDate(appHistoryDTOs[i].actionDate) + "</td>" +
                            "<td>"+ appHistoryDTOs[i].remarks +"</td></tr>";
                        }
                        $('#appStatusTbl').find('tbody').html(appHistoryTr);

                        var contractorHrs = contractorDTO.contractorHRs;
                        var partnerHrTr = "";
                        var hrTr = "";
                        var m = 0, n = 0;
                        var owner='';
                        for (var i in contractorHrs) {
                            var verifiedApproved = '';
                            if(contractorHrs[i].Approved == '1'){
                                verifiedApproved = verifiedApproved + "<td>(✔)</td>";
                                verifiedApproved = verifiedApproved + "<td>(✔)</td>";
                            }else if(contractorHrs[i].verified == '1'){
                                verifiedApproved = verifiedApproved + "<td>(✔)</td>";
                                verifiedApproved = verifiedApproved + "<td><input type='checkbox' style='zoom:1.6' disabled class='check' value='1'  required=''></td>";
                            }else{
                                verifiedApproved = verifiedApproved + "<td><input type='checkbox' style='zoom:1.6' disabled class='check' value='1'  required=''></td>";
                            }
                            if (contractorHrs[i].isPartnerOrOwner == '1') {
                                owner = contractorHrs[i].name;
                                m++;
                                partnerHrTr = partnerHrTr + "<tr><td>" + m + "</td>" +
                                "<td>" + contractorHrs[i].countryName + "</td>" +
                                "<td class='cidNo'>" + contractorHrs[i].cidNo + "</td>" +
                                "<td>" + contractorHrs[i].salutationName + "</td>" +
                                "<td>" + contractorHrs[i].name + "</td>" +
                                "<td>" + contractorHrs[i].sex + "</td>" +
                                "<td>" + contractorHrs[i].designationName + "</td>" +
                                "<td>" + ((contractorHrs[i].siCertificate == '1')?'(✔)':'') + "</td>" +
                                "<td><input type='button' name='humanResource' value='Check for this CID' class='checkCid btn btn-success'></td>" +
                                verifiedApproved+"</tr>";
                                getTrainingDtl(contractorHrs[i].cidNo);
                            }
                        }
                        $('#partnerDtls').find('tbody').html(partnerHrTr);

                        getContractorFinal(applicationNo);

                    } else {
                        warningMsg(res.text);
                    }
                }
            });
        }
        getServicesFee(applicationNo);
    }


    function getHrsEQs(hrOrEq) {
        var applicationNo = $('#appNoVA').val();
        if (!applicationNo) {
            return;
        }
        var url;
        if(hrOrEq == 'H'){
            url = "/getHrs";
        }else if(hrOrEq == 'E'){
            url = "/getEQs";
        }else{}
        $.ajax({
            url: _baseURL() + url,
            type: 'GET',
            data: {appNo: applicationNo},
            success: function (res) {
                var existingHrs = res.existing;
                var editedHrs = res.edited;
                var newlyAddedHrs = res.newlyAdded;
                var deletedHRs = res.deleted;

                if(hrOrEq == 'H'){
                    addHR("existing-hr",existingHrs);
                    addHR("edited-hr",editedHrs);
                    addHR("newly-added-hr",newlyAddedHrs);
                    addHR("deleted-hr",deletedHRs);
                }else{
                    addEQ("existing-eq",existingHrs);
                    addEQ("edited-eq",editedHrs);
                    addEQ("newly-added-eq",newlyAddedHrs);
                    addEQ("deleted-eq",deletedHRs);
                }

            }
        })
    }

    function addHR(tBodyClass, contractorHrs){

        var hrTr = "";

        for (var i in contractorHrs) {
            var verifiedApproved = '';
            if(contractorHrs[i].Approved == '1'){
                verifiedApproved = verifiedApproved + "<td>(✔)</td>";
                verifiedApproved = verifiedApproved + "<td>(✔)</td>";
            }else if(contractorHrs[i].verified == '1'){
                verifiedApproved = verifiedApproved + "<td>(✔)</td>";
                verifiedApproved = verifiedApproved + "<td><input type='checkbox' style='zoom:1.6' class='check' value='1'  disabled required=''></td>";
            }else{
                verifiedApproved = verifiedApproved + "<td><input type='checkbox' style='zoom:1.6' class='check' value='1' disabled required=''></td>";
            }
            var attachments = '';
            for (var j in contractorHrs[i].hrAttachments) {
                attachments = attachments + "<span class='hra'><input type='hidden' class='hraId' value='" + contractorHrs[i].hrAttachments[j].id + "'>" +
                "<a href='" + _baseURL() + "/viewDownload?documentPath=" + contractorHrs[i].hrAttachments[j].documentPath + "' target='_blank'>" + contractorHrs[i].hrAttachments[j].documentName + "</a></span><br>";
            }
            hrTr = hrTr + "<tr class=''>" +
            "<td class='salutationName'><input type='hidden' class='contractorHRid' name='contractorHRs[0].id' value='" + contractorHrs[i].id + "'/>" +
            "<input type='hidden' class='joiningDate' value='" + contractorHrs[i].joiningDate + "'/>" +
            contractorHrs[i].salutationName + "</td>" +
            "<td class='name'>" + contractorHrs[i].name + "</td>" +
            "<td class='cidNo'>" + contractorHrs[i].cidNo + "</td>" +
            "<td class='sex'>" + contractorHrs[i].sex + "</td>" +
            "<td class='countryName'>" + contractorHrs[i].countryName + "</td>" +
            "<td class='designationName'>" + contractorHrs[i].designationName + "</td>" +
            "<td class='qualificationName'>" + contractorHrs[i].qualificationName + "</td>" +
            "<td class='tradeName'>" + contractorHrs[i].tradeName + "</td>" +
            "<td class='serviceTypeName'>" + contractorHrs[i].serviceTypeName + "</td>" +
            "<td>" + nullif(contractorHrs[i].joiningDate) + "</td>" +
            "<td class='attachments'>" + attachments + "</td>" +
            "<td><input type='button'  value='Check for this CID' class='checkCidHr btn btn-success'></td>" +
            verifiedApproved+"</tr>";
        }
        //$('#partnerDtls').find('tbody').html(partnerHrTr);
        $('#hrDtlsTable').find('.'+tBodyClass).append(hrTr);
    }

    function nullif(val){
        if(val == null || val == 'null'){
            val = ''
        }
        return val;
    }

    function getContractorFinal(appNo){
        $.ajax({
            url: _baseURL() + '/getContractorFinal',
            type: 'GET',
            data: {appNo: appNo},
            success: function (res) {
                var contractor = res;
                $('#oldfirmName').html(contractor.firmName);
                $('#estAddressExist').html(contractor.estAddress);
                $('#estDzongkhagExist').val(contractor.regDzongkhagId).prop('disabled',true);
                $('#regEmailExist').html(contractor.regEmail);
                $('#regMobileNoExist').html(contractor.regMobileNo);
                $('#regPhoneNoExist').html(contractor.regPhoneNo);
                $('#regFaxNoExist').html(contractor.regFaxNo);

            }
        });
    }

    function addEQ(tBodyClass, contractorEQs){

        var eqTr = "";

        for (var i in contractorEQs) {
            var verifiedApprovedEq = '';
            if(contractorEQs[i].approved == '1'){
                verifiedApprovedEq = verifiedApprovedEq + "<td>(✔)</td>";
                verifiedApprovedEq = verifiedApprovedEq + "<td>(✔)</td>";
            }
            else if(contractorEQs[i].verified == '1'){
                verifiedApprovedEq = verifiedApprovedEq + "<td>(✔)</td>";
                verifiedApprovedEq = verifiedApprovedEq + "<td><input type='checkbox' style='zoom:1.6' name='approveEq' value='1' disabled class='check' required=''></td>";
            }else{
                verifiedApprovedEq = verifiedApprovedEq + "<td><input type='checkbox' style='zoom:1.6' name='verifyEq' value='1' disabled class='check' required=''></td>";
            }
            var attachments = '';
            for (var j in contractorEQs[i].eqAttachments) {
                attachments = attachments + "<span class='hra'><input type='hidden' class='hraId' value='" + contractorEQs[i].eqAttachments[j].id + "'>" +
                "<a href='" + _baseURL() + "/viewDownload?documentPath=" + contractorEQs[i].eqAttachments[j].documentPath + "' target='_blank'>" + contractorEQs[i].eqAttachments[j].documentName + "</a></span><br>";
            }
            eqTr = eqTr + "<tr class=''>" +
            "<td>"+contractorEQs[i].equipmentName + "</td>" +
            "<td>" + contractorEQs[i].registrationNo + "</td>" +
            "<td>" + contractorEQs[i].quantity + "</td>" +
            "<td class='attachments'>" + attachments + "</td>" +
            "<td><input type='button' name='humanResource' value='Check for Equipment' class='equipmentCheck btn btn-success'></td>" +
            verifiedApprovedEq+"</tr>";
        }
        //$('#partnerDtls').find('tbody').html(partnerHrTr);
        $('#equipmentTbl').find('.'+tBodyClass).append(eqTr);
    }

    function getProposedCategories() {
        var applicationNo = $('#appNoVA').val();
        if (!applicationNo) {
            return;
        }
        $.ajax({
            url: _baseURL() + '/getProposedCategories',
            type: 'GET',
            data: {appNo: applicationNo},
            success: function (res) {
                var tr = '';
                for(var i in res){
                    tr = tr + '<tr>' +
                    '<td><input type="checkbox" checked disabled /></td>'+
                    '<td>'+res[i].serviceName+'</td>'+
                    '<td>'+res[i].categoryName+'</td>'+
                    '<td>'+isnull(res[i].exClassName)+'</td>'+
                    '<td>'+isnull(res[i].aClassName)+'</td>'+
                    '<td>'+res[i].aAmount+'</td>'+
                    '</tr>';
                }
                $('#contractorCCTbl').find('tbody').html(tr);
                $('#contractorCCTbl1').find('tbody').html(tr);
            }
        })
    }

    function checkEquipment(){
        $('body').on('click','.equipmentCheck',function(){
            //var modal = $(this).closest('.modal').attr('id');
            $("#CheckModalEquipment").modal('show');
            $check = $(this).closest('tr').find('.check');
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
        });
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
        });
    }

    function sendBack() {
        $('#btnSendBack').on('click', function (e) {
            var appNo = $('#appNoVA').val();
            if(!appNo){
                appNo = $('#appNoPayment').val();
            }
            $.ajax({
                url: _baseURL() + '/sendBack',
                type: 'POST',
                data: {appNo:appNo,remarks:$('#vRemarks').val()},
                success: function (res) {
                    if (res.status == '1') {
                        successMsg(res.text, _baseURL());
                    } else {
                        warningMsg(res.text);
                    }
                }
            });
        });
    }

    function getContractorInfoForPayment() {
        var applicationNo = $('#appNoPayment').val();
        if (applicationNo) {
            $.ajax({
                url: _baseURL() + '/getContractorInfo',
                type: 'GET',
                data: {appNo: applicationNo,flag:'P'},
                success: function (res) {
                    if (res.status == '1') {
                        var contractorDTO = res.dto;
                        var contractor = contractorDTO.contractor;
                        $('#ownershipType').text(contractorDTO.ownershipTypeTxt);
                        $('#country').text(contractorDTO.countryTxt);
                        $('#tradeLicenseNo').text(contractor.tradeLicenseNo);
                        $('#firmName').text(contractor.firmName);
                        $('#tpn').text(contractor.tpn);

                        $('#estAddress').text(contractor.estAddress);
                        $('#estDzongkhag').text(contractorDTO.estDzongkhagTxt);
                        $('#regEmail').text(contractor.regEmail);
                        $('#regMobileNo').text(contractor.regMobileNo);
                        $('#regPhoneNo').text(contractor.regPhoneNo);
                        $('#regFaxNo').text(contractor.regFaxNo);

                        var appHistoryDTOs = contractorDTO.appHistoryDTOs;

                        var appHistoryTr = "";

                        for (var i in appHistoryDTOs) {
                            var actionTakenBy = appHistoryDTOs[i].userName;
                            actionTakenBy = (actionTakenBy==null)?'By Citizen':actionTakenBy;
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
            getServicesFee(applicationNo);

        }
    }

    function getServicesFee(applicationNo){
        $.ajax({
            url: _baseURL() + '/getAppliedServices',
            type: 'GET',
            data: {appNo: applicationNo},
            success: function (res) {
                var tr = "";
                var tApprAmount = 0;
                for(var i in res){
                    tr = tr + "<tr>" +
                    "<td><input type='checkbox' style='zoom:1.6' name='service["+i+"]' value='"+res[i].serviceId+"' checked/></td>"+
                    "<td>"+res[i].serviceName+"</td>"+
                    "<td>"+res[i].paymentAmount+"</td></tr>";
                    tApprAmount =tApprAmount+parseFloat(res[i].paymentAmount);
                }
                var tfoot = "<tr style='font-weight: bold'><td colspan='2' align='right'>Total</td><td>"+tApprAmount+"</td></tr> ";

                $('#serviceTbl').find('tbody').empty().append(tr);
                $('#serviceTbl').find('tfoot').empty().append(tfoot);
                $('#paymentAmount').val(tApprAmount);
            }
        });
    }

    function paymentUpdate() {
        $('#btnSave').on('click', function (e) {
            $('#contractorPaymentForm').validate({
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

    function incorporation(data){
        if(data){
            $('#cIncorporation').removeClass('hide');
            $('#oIncorporation').removeClass('hide');
            var cIncTr = '';
            var categoryTr = '';
            var ownerTr = '';
            for(var i in data){
                if(data[i].attachmentFor == 'InSole') {
                    cIncTr = cIncTr + "<tr>" +
                    "<td></td>" +
                    "<td>" + data[i].documentName + "</td>" +
                    "<td><a href='" + _baseURL() + "/viewDownload?documentPath=" + data[i].documentPath + "' target='_blank'> View </a></td>" +
                    "</tr>";
                }
                if(data[i].attachmentFor == 'AL'){
                    categoryTr = categoryTr + "<tr>" +
                    "<td></td>" +
                    "<td>" + data[i].documentName + "</td>" +
                    "<td><a href='" + _baseURL() + "/viewDownload?documentPath=" + data[i].documentPath + "' target='_blank'> View </a></td>" +
                    "</tr>";
                }
                if(data[i].attachmentFor == 'OC'){
                    ownerTr = ownerTr + "<tr>" +
                    "<td></td>" +
                    "<td>" + data[i].documentName + "</td>" +
                    "<td><a href='" + _baseURL() + "/viewDownload?documentPath=" + data[i].documentPath + "' target='_blank'> View </a></td>" +
                    "</tr>";
                }
            }
            $('#IncCertificateTbl').find('tbody').html(cIncTr);
            $('#OcCertificateTbl').find('tbody').html(ownerTr);
            $('#certificateTblCategory').find('tbody').html(categoryTr);

        }else{
            $('#cIncorporation').addClass('hide');
        }
    }
    function getTrainingDtl(cidNo){
        $.ajax({
            url: cdbGlobal.baseURL() + '/contractorNR/getTrainingDtl',
            type: 'GET',
            data: {cidNo: cidNo},
            success: function (res) {
                var trainingTbl = $('#inductionCourseDtl');
                trainingTbl.find('tbody').find('.noRecord').remove();
                var tr = '';
                for (var i in res) {
                    tr = tr + "<tr><td></td>" +
                    "<td>" + res[i].tType + "</td>" +
                    "<td>" + (formatAsDate(res[i].fromDate) + ' to ' + formatAsDate(res[i].toDate) ) + "</td>" +
                    "<td>" + res[i].tModule + "</td>" +
                    "<td>" + res[i].participant + "</td>" +
                    "<td>" + res[i].cidNo + "</td></tr>";
                }
                trainingTbl.find('tbody').append(tr);
            }
        })
    }

    function init(){
        approve();
        reject();
        verify();
        checkHR();
        getContractorInfoForPayment();
        paymentUpdate();
        sendBack();
        getAppliedServices();
        getProposedCategories();
        checkEquipment()();
    }
    return {
        init:init
    };
})();

$(document).ready(function () {
        contractorRCAction.init();
    }
);