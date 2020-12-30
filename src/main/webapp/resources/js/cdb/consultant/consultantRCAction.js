/**
 * Created by user on 2/14/2020.
 */
var $check = null;
function checkBtn(checkBoxId)
{
    $check.prop('checked',true);
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

var consultantRCAction = (function () {
    "use strict";
    var isSubmitted = false;

    function _baseURL() {
        return cdbGlobal.baseURL() + "/admin/consultantRCAction";
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
                getConsultantInfo();
                for(var i in res){
                    if(res[i].serviceRefNo == '4'){
                        $('#changeOfOwnerId').prop('checked',true).prop('disabled',true);
                    }
                    if(res[i].serviceRefNo == '6'){
                        $('#changeOfLocation').prop('checked',true).prop('disabled',true);
                    }
                    if(res[i].serviceRefNo == '7'){
                        $('#upgradeDowngrade').prop('checked',true).prop('disabled',true);
                        $('.category_details').removeClass('hide');
                        getProposedCategories();
                    }
                    if(res[i].serviceRefNo == '8'){
                        $('#updateHR').prop('checked',true).prop('disabled',true);
                        $('.human_resource_criteria').removeClass('hide');
                        getHrsEQs('H');
                    }
                    if(res[i].serviceRefNo == '9'){
                        $('#updateEq').prop('checked',true).prop('disabled',true);
                        $('.equipment_details').removeClass('hide');
                        getHrsEQs('E');
                    }
                    if(res[i].serviceRefNo == '10'){
                        $('#changeOfFirmName').prop('checked',true).prop('disabled',true);
                    }
                    if(res[i].serviceRefNo == '12'){
                        $('#Incorporation').prop('checked',true).prop('disabled',true);
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
                url: cdbGlobal.baseURL() + "/consultantNR/getPersonalInfo",
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
                url: cdbGlobal.baseURL() + "/consultantNR/getPersonalInfo",
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
                        $('#pGewog').text(consultantDTO.pGewogTxt);
                        $('#pVillage').text(consultantDTO.pVillageTxt);
                        $('#estAddress').text(consultant.estAddress);
                        $('#estDzongkhag').text(consultantDTO.estDzongkhagTxt);
                        $('#regEmail').text(consultant.regEmail);
                        $('#regMobileNo').text(consultant.regMobileNo);
                        $('#regPhoneNo').text(consultant.regPhoneNo);
                        $('#regFaxNo').text(consultant.regFaxNo);

                        incorporation(consultantDTO.incAttachments);

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

                        var appHistoryDTOs = consultantDTO.appHistoryDTOs;

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
                                verifiedApproved = verifiedApproved + "<td><input type='checkbox' style='zoom:1.6' class='check' disabled value='1'  required=''></td>";
                            }else{
                                verifiedApproved = verifiedApproved + "<td><input type='checkbox' style='zoom:1.6' class='check' disabled value='1'  required=''></td>";
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
                            }
                        }
                        $('#partnerDtls').find('tbody').html(partnerHrTr);

                        getConsultantFinal(applicationNo);
                    } else {
                        warningMsg(res.text);
                    }
                }
            });
        }
        getServicesFee(applicationNo);
    }

    function getConsultantFinal(appNo){
        $.ajax({
            url: _baseURL() + '/getConsultantFinal',
            type: 'GET',
            data: {appNo: appNo},
            success: function (res) {
                var consultant = res;
                $('#oldfirmName').html(consultant.firmName);
                $('#estAddressExist').html(consultant.estAddress);
                $('#estDzongkhagExist').val(consultant.regDzongkhagId).prop('disabled',true);
                $('#regEmailExist').html(consultant.regEmail);
                $('#regMobileNoExist').html(consultant.regMobileNo);
                $('#regPhoneNoExist').html(consultant.regPhoneNo);
                $('#regFaxNoExist').html(consultant.regFaxNo);

            }
        });
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

    function addHR(tBodyClass, consultantHrs){
        var hrTr = "";

        for (var i in consultantHrs) {
            var verifiedApproved = '';
            if(consultantHrs[i].Approved == '1'){
                verifiedApproved = verifiedApproved + "<td>(✔)</td>";
                verifiedApproved = verifiedApproved + "<td>(✔)</td>";
            }else if(consultantHrs[i].verified == '1'){
                verifiedApproved = verifiedApproved + "<td>(✔)</td>";
                verifiedApproved = verifiedApproved + "<td><input type='checkbox' style='zoom:1.6' class='check' disabled value='1'  required=''></td>";
            }else{
                verifiedApproved = verifiedApproved + "<td><input type='checkbox' style='zoom:1.6' class='check' disabled value='1'  required=''></td>";
            }
            var attachments = '';
            for (var j in consultantHrs[i].hrAttachments) {
                attachments = attachments + "<span class='hra'><input type='hidden' class='hraId' value='" + consultantHrs[i].hrAttachments[j].id + "'>" +
                "<a href='" + _baseURL() + "/viewDownload?documentPath=" + consultantHrs[i].hrAttachments[j].documentPath + "' target='_blank'>" + consultantHrs[i].hrAttachments[j].documentName + "</a></span><br>";
            }
            hrTr = hrTr + "<tr class=''>" +
            "<td class='salutationName'><input type='hidden' class='consultantHRid' name='consultantHRs[0].id' value='" + consultantHrs[i].id + "'/>" +
            "<input type='hidden' class='joiningDate' value='" + consultantHrs[i].joiningDate + "'/>" +
            consultantHrs[i].salutationName + "</td>" +
            "<td class='name'>" + consultantHrs[i].name + "</td>" +
            "<td class='cidNo'>" + consultantHrs[i].cidNo + "</td>" +
            "<td class='sex'>" + consultantHrs[i].sex + "</td>" +
            "<td class='countryName'>" + consultantHrs[i].countryName + "</td>" +
            "<td class='designationName'>" + consultantHrs[i].designationName + "</td>" +
            "<td class='qualificationName'>" + consultantHrs[i].qualificationName + "</td>" +
            "<td class='tradeName'>" + consultantHrs[i].tradeName + "</td>" +
            "<td class='serviceTypeName'>" + consultantHrs[i].serviceTypeName + "</td>" +
            "<td>" + nullif(consultantHrs[i].joiningDate) + "</td>" +
            "<td class='attachments'>" + attachments + "</td>" +
            "<td><input type='button'  value='Check for this CID' class='checkCidHr btn btn-success'></td>" +
            verifiedApproved+ "</tr>";
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
    function addEQ(tBodyClass, consultantEQs){

        var eqTr = "";

        for (var i in consultantEQs) {
            var verifiedApprovedEq = '';
            if(consultantEQs[i].approved == '1'){
                verifiedApprovedEq = verifiedApprovedEq + "<td>(✔)</td>";
                verifiedApprovedEq = verifiedApprovedEq + "<td>(✔)</td>";
            }
            else if(consultantEQs[i].verified == '1'){
                verifiedApprovedEq = verifiedApprovedEq + "<td>(✔)</td>";
                verifiedApprovedEq = verifiedApprovedEq + "<td><input type='checkbox' style='zoom:1.6' name='approveEq' value='1' disabled class='check' required=''></td>";
            }else{
                verifiedApprovedEq = verifiedApprovedEq + "<td><input type='checkbox' style='zoom:1.6' name='verifyEq' value='1' disabled class='check' required=''></td>";
            }
            var attachments = '';
            for (var j in consultantEQs[i].eqAttachments) {
                attachments = attachments + "<span class='hra'><input type='hidden' class='hraId' value='" + consultantEQs[i].eqAttachments[j].id + "'>" +
                "<a href='" + _baseURL() + "/viewDownload?documentPath=" + consultantEQs[i].eqAttachments[j].documentPath + "' target='_blank'>" + consultantEQs[i].eqAttachments[j].documentName + "</a></span><br>";
            }
            eqTr = eqTr + "<tr class=''>" +
            "<td>"+consultantEQs[i].equipmentName + "</td>" +
            "<td>" + consultantEQs[i].registrationNo + "</td>" +
            "<td>" + consultantEQs[i].quantity + "</td>" +
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
                    '<td>'+res[i].exClassName+'</td>'+
                    '<td>'+res[i].aClassName+'</td>'+
                    '<td>'+res[i].aAmount+'</td>'+
                    '</tr>';
                }
                $('#consultantCCTbl').find('tbody').html(tr);
            }
        })
    }

    function incorporation(data){
        if(data){
            $('#cIncorporation').removeClass('hidden');
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

                        $('#estAddress').text(consultant.estAddress);
                        $('#estDzongkhag').text(consultantDTO.estDzongkhagTxt);
                        $('#regEmail').text(consultant.regEmail);
                        $('#regMobileNo').text(consultant.regMobileNo);
                        $('#regPhoneNo').text(consultant.regPhoneNo);
                        $('#regFaxNo').text(consultant.regFaxNo);
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

    function init(){

        approve();
        reject();
        verify();
        //getConsultantInfo();
        getConsultantInfoForPayment();
        paymentUpdate();
        checkHR();
        sendBack();
        getAppliedServices();
        checkEquipment();
    }
    return {
        init:init
    };
})();

$(document).ready(function () {
        consultantRCAction.init();
    }
);