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

function saveAndPreview(presentClass, nextClass) {
    var content = '<h3 class="pt-3 text-center">Fee Structure</h3>' + $("#fees_structure >.div-actual").html() +
        '<h3 class="pt-3 text-center">General Information</h3>' + $("#general_Information >.div-actual").html() +
        '<h3 class="pt-3 text-center">Category Details</h3>' + $("#category_details >.div-actual").html()
        + '<h3 class="pt-3 text-center">Human Resource</h3>' + $("#human_resource_criteria >.div-actual").html() +
        '<h3 class="pt-3 text-center">Contractor Equipment Details</h3>' + $("#equipment_details >.div-actual").html();

    $("." + presentClass + ">a").addClass('bg-blue-light text-white');
    $('.tab-pane').removeClass("active").addClass("active");
    $('.tab-content').removeClass("active").addClass("active");
    $("." + nextClass).addClass("active");
    $("." + presentClass + ">a").append("<i class='fa fa-check ml-1'></i>");
    window.moveTo(0, 0);
    $('#nextGIBtn').hide();
    $('#nextHRBtn').hide();
    $('#btnValEqNext').hide();
    $('#btn1').hide();
    $('#btn2').hide();
    $('#btn3').hide();
    $('#btn4').hide();
    $('#btn5').hide();
}

var contractorNRAction = (function () {
    "use strict";
    var isSubmitted = false;

    function _baseURL() {
        return cdbGlobal.baseURL() + "/admin/contractorNRAction";
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
                        $('#cidchecked').text(cidNo);
                        var imagelink='https://www.citizenservices.gov.bt/BtImgWS/ImageServlet?type=PH&cidNo='+cidNo;
                        $('#photoM').html("<img src='"+imagelink+"'  width='200px'  height='200px' class='pull-right'/>");
                        $("#hrModal").modal('show');
                        $("#closeModal").modal('show');

                        var employeeDetailsDTO = dto.employeeDetailsDTOs;
                        var govCopDTO = dto.govCopDTOs;
                        var cdbDTO = dto.cdbDTOs;

                        if(employeeDetailsDTO !=''){
                            for(var i in employeeDetailsDTO){
                                var workId = employeeDetailsDTO[i].workId;
                                if(workId !='' && workId != null){
                                    alert(workId);
                                    $('#dcbinfo').append("<br/> <b>CDB No: </b> "+employeeDetailsDTO[i].cdbNo+"  ||  <b> Procuring Agency:  </b> "+employeeDetailsDTO[i].procuringAgency+"  ||  <b> Work ID:</b>"+employeeDetailsDTO[i].workId+"");
                                   // $('#dcbinfo').append("<br/> This person is engaged with cdb number <b>"+employeeDetailsDTO[i].cdbNo+"</b> in <b>"+employeeDetailsDTO[i].procuringAgency+"</b> with work Id:<b>"+employeeDetailsDTO[i].workId+"</b>");
                                    $('#cidNumber').text(dto.cidNo); $('#hrName').text((dto.fullName));
                                }else{
                                    $('#dcbinfonotEngaged').append("<br/> This person is not engaged in any work or project.");
                                }
                            }
                        }else{
                           // $('#dcbinfo').hide();
                            $('#dcbinfonotEngaged').append("<br/> This person is not engaged in any work or project.");
                        }

                        if(govCopDTO !=''){
                            for(var i in govCopDTO){
                                var agency = govCopDTO[i].agency;
                                if(agency !='' && agency !=null){
                                    alert(agency);
                                    $('#dcbinfo').append("<br/> <b>Position Title: </b> "+govCopDTO[i].positionTitle+"  ||  <b> Agency:  </b> "+govCopDTO[i].agency+"");
                                } else{
                                    $('#dcbinfonotEngaged').append("<br/> This person is not a government/coperate employee.");
                                }
                            }
                        }else{
                           // $('#dcbinfo').hide();
                            $('#dcbinfonotEngaged').append("<br/> This person is not a government/coperate employee.");
                        }
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
                        var govCopDTO = dto.govCopDTOs;
                        var cdbDTO = dto.cdbDTOs;

                        if(employeeDetailsDTO !=''){
                            for(var i in employeeDetailsDTO){
                                var workId = employeeDetailsDTO[i].workId;
                                if(workId !='' && workId != null){
                                    alert(workId);
                                    $('#dcbinfo').append("<br/> <b>CDB No: </b> "+employeeDetailsDTO[i].cdbNo+"  ||  <b> Procuring Agency:  </b> "+employeeDetailsDTO[i].procuringAgency+"  ||  <b> Work ID:</b>"+employeeDetailsDTO[i].workId+"");
                                    // $('#dcbinfo').append("<br/> This person is engaged with cdb number <b>"+employeeDetailsDTO[i].cdbNo+"</b> in <b>"+employeeDetailsDTO[i].procuringAgency+"</b> with work Id:<b>"+employeeDetailsDTO[i].workId+"</b>");
                                    $('#cidNumber').text(dto.cidNo); $('#hrName').text((dto.fullName));
                                }else{
                                    $('#dcbinfonotEngaged').append("<br/> This person is not engaged in any work or project.");
                                }
                            }
                        }else{
                            // $('#dcbinfo').hide();
                            $('#dcbinfonotEngaged').append("<br/> This person is not engaged in any work or project.");
                        }

                        if(govCopDTO !=''){
                            for(var i in govCopDTO){
                                var agency = govCopDTO[i].agency;
                                if(agency !='' && agency !=null){
                                    alert(agency);
                                    $('#dcbinfo').append("<br/> <b>Position Title: </b> "+govCopDTO[i].positionTitle+"  ||  <b> Agency:  </b> "+govCopDTO[i].agency+"");
                                } else{
                                    $('#dcbinfonotEngaged').append("<br/> This person is not a government/coperate employee.");
                                }
                            }
                        }else{
                            // $('#dcbinfo').hide();
                            $('#dcbinfonotEngaged').append("<br/> This person is not a government/coperate employee.");
                        }

                       /* var employeeDetailsDTO = dto.employeeDetailsDTOs;
                        var empDtls ="",empDtls1="",empDtls2="";
                        //alert(employeeDetailsDTO != null);
                        if(employeeDetailsDTO !=null) {
                            $('#engagedId').show();
                            for (var i in employeeDetailsDTO) {
                                empDtls = empDtls +
                                "<tr><td>" + (parseInt(i) + 1) + "</td>" +
                                "<td>" + +"</td>" +
                                "<td>" + +"</td>" +
                                "<td>" + employeeDetailsDTO[i].contractorCDBNo + "</td>" +
                                "<td>" + employeeDetailsDTO[i].contractorFirmname + "</td>" + "</tr>";
                            }
                            $('#employeeDTLS').find('tbody').html(empDtls);
                            for (var i in employeeDetailsDTO) {
                                empDtls1 = empDtls1 +
                                "<tr><td>" + (parseInt(i) + 1) + "</td>" +
                                "<td>" + +"</td>" +
                                "<td>" + +"</td>" +
                                "<td>" + employeeDetailsDTO[i].consultantCDBNo + "</td>" +
                                "<td>" + employeeDetailsDTO[i].consultantFirmname + "</td>" + "</tr>";
                            }
                            $('#employeeDTLS1').find('tbody').html(empDtls1);
                            for (var i in employeeDetailsDTO) {
                                empDtls2 = empDtls2 +
                                "<tr><td>" + (parseInt(i) + 1) + "</td>" +
                                "<td>" + +"</td>" +
                                "<td>" + +"</td>" +
                                "<td>" + employeeDetailsDTO[i].spCDBNo + "</td>" +
                                "<td>" + employeeDetailsDTO[i].spFirmname + "</td>" + "</tr>";
                            }
                            $('#employeeDTLS2').find('tbody').html(empDtls2);
                            $('#cidNumber').text(dto.cidNo);
                            $('#hrName').text((dto.fullName));
                        } else{
                            $('#notEngagedId').show();
                        }*/
                    }
                }
            });
        });
    }

    function checkEquipment(){
        $('body').on('click','.equipmentCheck',function(){
            //var modal = $(this).closest('.modal').attr('id');
            $("#CheckModalEquipment").modal('show');
            $check = $(this).closest('tr').find('.check');
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
                    if (res.status == '1') {
                        var contractorDTO = res.dto;
                        var contractor = contractorDTO.contractor;
                        $('#ownershipType').text(contractorDTO.ownershipTypeTxt);
                        $('#country').text(contractorDTO.countryTxt);
                        $('#tradeLicenseNo').text(contractor.tradeLicenseNo);
                        $('#firmName').text(contractor.firmName);
                        $('#tpn').text(contractor.tpn);
                        $('#pDzongkhag').text(contractorDTO.pDzongkhagTxt);
                        $('#pGewog').text(contractor.pGewog);
                        $('#pVillage').text(contractor.pVillage);
                        $('#estAddress').text(contractor.estAddress);
                        $('#estDzongkhag').text(contractorDTO.estDzongkhagTxt);
                        $('#regEmail').text(contractor.regEmail);
                        $('#regMobileNo').text(contractor.regMobileNo);
                        $('#regPhoneNo').text(contractor.regPhoneNo);
                        $('#regFaxNo').text(contractor.regFaxNo);

                        incorporation(contractorDTO.incAttachments);

                        var contractorHrs = contractorDTO.contractorHRs;
                        var partnerHrTr = "";
                        var hrTr = "";
                        var m = 0, n = 0;
                        var owner='';
                        var ownerName ='', ownerCid='';
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
                            if (contractorHrs[i].isPartnerOrOwner == '1') {
                                owner = contractorHrs[i].name;
                                ownerName = contractorHrs[i].name;
                                ownerCid = contractorHrs[i].cidNo;
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
                            } else {
                                n++;
                                var attachments = '';
                                for (var j in contractorHrs[i].hrAttachments){
                                    attachments = attachments + "<a href='"+_baseURL() + "/viewDownload?documentPath="+contractorHrs[i].hrAttachments[j].documentPath+"' target='_blank'>"+contractorHrs[i].hrAttachments[j].documentName+"</a><br>";
                                }
                                //var href = _baseURL() + "/viewDownload?tableName=crpcontractorhumanresourceattachment&filterCol=CrpContractorHumanResourceId&filterVal="+contractorHrs[i].id;
                                hrTr = hrTr + "<tr>" +
                                "<td>" + n + "<input type='hidden' class='contractorHRid' value='"+contractorHrs[i].id +"' </td>" +
                                "<td>" + contractorHrs[i].countryName + "</td>" +
                                "<td class='cidNo'>" + contractorHrs[i].cidNo + "</td>" +
                                "<td>" + contractorHrs[i].salutationName + "</td>" +
                                "<td>" + contractorHrs[i].name + "</td>" +
                                "<td>" + contractorHrs[i].sex + "</td>" +
                                "<td>" + contractorHrs[i].designationName + "</td>" +
                                "<td>" + contractorHrs[i].qualificationName + "</td>" +
                                "<td>" + contractorHrs[i].tradeName + "</td>" +
                                "<td>" + nullif(contractorHrs[i].joiningDate) + "</td>" +
                                "<td>" + contractorHrs[i].serviceTypeName + "</td>" +
                                    //"<td><a href='javascript:void(0);' class='vAttachment'>View/Download</a> </td>" +
                                "<td>"+attachments+"</td>" +
                                "<td><input type='button'  value='Check for this CID' class='checkCidHr btn btn-success'></td>" +
                                verifiedApproved+"</tr>";
                            }
                        }
                        $('#partnerDtls').find('tbody').html(partnerHrTr);
                        $('#hrTbl').find('tbody').html(hrTr);

                        var categoryClassDTOs = contractorDTO.categories;
                        var ccTr = "";
                        var tFeeAmount = 0;
                        for (var i in categoryClassDTOs) {
                            tFeeAmount += parseFloat(categoryClassDTOs[i].aAmount);
                            ccTr = ccTr + "<tr><td><input class='form-control' type='checkbox' checked='checked' disabled style='width: 17px; height: 17px;'></td>" +
                            "<td>" + categoryClassDTOs[i].categoryName + "</td>" +
                            "<td><select disabled class='form-control'><option>" + categoryClassDTOs[i].aClassName + "</option></select></td>" +
                            "<td>"+categoryClassDTOs[i].aAmount+"</td></tr>";
                        }
                        var tfoot = "<tr><td colspan='3' align='right'>Total</td><td>"+tFeeAmount+"</td> ";
                        $('#contractorCCTbl').find('tbody').html(ccTr);
                        $('#contractorCCTbl').find('tfoot').html(tfoot);

                        var equipments = contractorDTO.equipments;
                        var eqTr = "";
                        for (var i in equipments) {
                            var verifiedApprovedEq = '';
                            if(equipments[i].approved == '1'){
                                verifiedApprovedEq = verifiedApprovedEq + "<td>(✔)</td>";
                                verifiedApprovedEq = verifiedApprovedEq + "<td>(✔)</td>";
                            }
                            else if(equipments[i].verified == '1'){
                                verifiedApprovedEq = verifiedApprovedEq + "<td>(✔)</td>";
                                verifiedApprovedEq = verifiedApprovedEq + "<td><input type='checkbox' style='zoom:1.6' name='approveEq' value='1' disabled class='check' required=''></td>";
                            }else{
                                verifiedApprovedEq = verifiedApprovedEq + "<td><input type='checkbox' style='zoom:1.6' name='verifyEq' value='1' disabled class='check' required=''></td>";
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

                        var appHistoryDTOs = contractorDTO.appHistoryDTOs;
                        var appHistoryTr = "";

                        for (var i in appHistoryDTOs) {
                            var actionTakenBy = appHistoryDTOs[i].userName;
                            actionTakenBy = (actionTakenBy==null)?ownerCid  +'('+ ownerName+ ')':actionTakenBy;
                            appHistoryTr = appHistoryTr +
                            "<tr><td>" + appHistoryDTOs[i].appStatus + "</td>" +
                            "<td>" + actionTakenBy + "</td>" +
                            "<td>" + formatAsDate(appHistoryDTOs[i].actionDate) + "</td>" +
                            "<td>"+ appHistoryDTOs[i].remarks +"</td></tr>";
                        }
                        $('#appStatusTbl').find('tbody').html(appHistoryTr);

                        if(contractor.ownershipTypeId != '1e243ef0-c652-11e4-b574-080027dcfac6'){

                        }
                    } else {
                        warningMsg(res.text);
                    }
                }
            });
        }
    }

    function nullif(val){
        if(val == null || val == 'null'){
            val = ''
        }
        return val;
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
        })
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
                        $('#pDzongkhag').text(contractorDTO.pDzongkhagTxt);
                        $('#pGewog').text(contractor.pGewog);
                        $('#pVillage').text(contractor.pVillage);
                        $('#estAddress').text(contractor.estAddress);
                        $('#estDzongkhag').text(contractorDTO.estDzongkhagTxt);
                        $('#regEmail').text(contractor.regEmail);
                        $('#regMobileNo').text(contractor.regMobileNo);
                        $('#regPhoneNo').text(contractor.regPhoneNo);
                        $('#regFaxNo').text(contractor.regFaxNo);

                        var contractorHrs = contractorDTO.contractorHRs;
                        var partnerHrTr = "";
                        var m = 0;
                        for (var i in contractorHrs) {
                            if (contractorHrs[i].isPartnerOrOwner == '1') {
                                m++;
                                partnerHrTr = partnerHrTr + "<tr><td>" + m + "</td>" +
                                "<td>" + contractorHrs[i].countryName + "</td>" +
                                "<td class='cidNo'>" + contractorHrs[i].cidNo + "</td>" +
                                "<td>" + contractorHrs[i].salutationName + "</td>" +
                                "<td>" + contractorHrs[i].name + "</td>" +
                                "<td>" + contractorHrs[i].sex + "</td>" +
                                "<td>" + contractorHrs[i].designationName + "</td>" +
                                "<td>" + ((contractorHrs[i].siCertificate == '1')?'(✔)':'') + "</td>" +
                                "</tr>";
                            }
                        }
                        $('#partnerDtls').find('tbody').html(partnerHrTr);

                        var categoryClassDTOs = contractorDTO.categories;
                        var ccTr = "";
                        var tApplAmount = 0,tVerAmount= 0,tApprAmount=0;
                        for (var i in categoryClassDTOs) {
                            tApplAmount += parseFloat(categoryClassDTOs[i].aAmount);
                            tVerAmount += parseFloat(categoryClassDTOs[i].vAmount);
                            tApprAmount += parseFloat(categoryClassDTOs[i].apAmount);
                            ccTr = ccTr + "<tr><td><input class='form-control' type='checkbox' checked='checked' disabled style='width: 17px; height: 17px;'></td>" +
                            "<td>" + categoryClassDTOs[i].categoryName + "</td>" +
                            "<td>" + categoryClassDTOs[i].aClassName + "</td>" +
                            "<td class='fee'>"+categoryClassDTOs[i].aAmount+"</td>" +
                            "<td>" + categoryClassDTOs[i].vClassName + "</td>" +
                            "<td class='fee'>"+categoryClassDTOs[i].vAmount+"</td>" +
                            "<td>" + categoryClassDTOs[i].apClassName + "</td>" +
                            "<td class='fee'>"+categoryClassDTOs[i].apAmount+"</td>" +
                            "</tr>";
                        }
                        var tfoot = "<tr><td colspan='2' align='right'>Total</td><td colspan='2'>"+tApplAmount+"</td>" +
                            "<td colspan='2'>"+tVerAmount+"</td><td colspan='2'>"+tApprAmount+"</td> ";

                        $('#contractorCCTbl').find('tbody').html(ccTr);
                        $('#contractorCCTbl').find('tfoot').html(tfoot);
                        $('#paymentAmount').val(tApprAmount);
                        $('#cdbNo').val(contractorDTO.cdbNo);

                        var appHistoryDTOs = contractorDTO.appHistoryDTOs;
                        var appHistoryTr = "";

                        for (var i in appHistoryDTOs) {
                            var actionTakenBy = appHistoryDTOs[i].userName;
                            //actionTakenBy = (actionTakenBy==null)?'By Citizen':actionTakenBy
                            actionTakenBy = (actionTakenBy==null)? contractorHrs[i].cidNo +'('+contractorHrs[i].name+')':actionTakenBy
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

    function incorporation(data){
        if(data > 0){
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
        getContractorInfoForPayment();
        paymentUpdate();
        checkHR();
        sendBack();
        validateOwner();
        validateHr();
        validateEq();
        checkEquipment();
    }
    return {
        verify: verify,
        getContractorInfo: getContractorInfo,
        init:init
    };
})();

$(document).ready(function () {
        contractorNRAction.verify();
        contractorNRAction.getContractorInfo();
        contractorNRAction.init();
    }
);