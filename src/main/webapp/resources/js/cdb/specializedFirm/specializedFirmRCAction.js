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

var specializedFirmRCAction = (function () {
    "use strict";
    var isSubmitted = false;

    function _baseURL() {
        return cdbGlobal.baseURL() + "/admin/specializedFirmRCAction";
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
                getSpecializedFirmInfo();
                getSpecializedFirmOwner();
                for(var i in res){
                    if(res[i].serviceRefNo == '4'){
                        $('#changeOfOwnerId').prop('checked',true).prop('disabled', true);
                    }
                    if(res[i].serviceRefNo == '6'){
                        $('#changeOfLocation').prop('checked',true).prop('disabled', true);
                    }
                    if(res[i].serviceRefNo == '7'){
                        $('#upgradeDowngrade').prop('checked',true).prop('disabled', true);
                       $('.category_details').removeClass('hide');
                        getProposedCategories();
                        getServicesFee(applicationNo);
                }
                    if(res[i].serviceRefNo == '8'){
                        $('#updateHR').prop('checked',true).prop('disabled', true);
                        $('.human_resource_criteria').removeClass('hide');
                        getHrsEQs('H');
                    }
                    if(res[i].serviceRefNo == '9'){
                        $('#updateEq').prop('checked',true).prop('disabled', true);
                        $('.equipment_details').removeClass('hide');
                        getHrsEQs('E');
                    }
                    if(res[i].serviceRefNo == '10'){
                        $('#changeOfFirmName').prop('checked',true).prop('disabled', true);
                    }
                    if(res[i].serviceRefNo == '12'){
                        $('#Incorporation').prop('checked',true).prop('disabled', true);
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
                url: cdbGlobal.baseURL() + "/specializedFirm/getPersonalInfo",
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

                        var employeeDetailsDTO = dto.employeeDetailsDTOs;
                        var govCopDTO = dto.govCopDTOs;
                        var cdbDTO = dto.cdbDTOs;

                        if(employeeDetailsDTO !=''){
                            for(var i in employeeDetailsDTO){
                                var workId = employeeDetailsDTO[i].workId;
                                if(workId !='' && workId != null){
                                   // alert(workId);
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
                                    //alert(agency);
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
                    }
                }
            });
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

    function getSpecializedFirmInfo() {
        var applicationNo = $('#appNoVA').val();
        if (applicationNo) {
            $.ajax({
                url: _baseURL() + '/getSpecializedFirmInfo',
                type: 'GET',
                data: {appNo: applicationNo,flag:'V'},
                success: function (res) {
                    if (res.status == '1') {
                        var spFirmDTO = res.dto;
                        debugger;
                        var specializedFirm = spFirmDTO.specializedFirm;
                        $('#ownershipType').text(spFirmDTO.ownershipTypeTxt);
                        $('#country').text(spFirmDTO.countryTxt);
                        $('#tradeLicenseNo').text(specializedFirm.tradeLicenseNo);
                        $('#firmName').text(specializedFirm.firmName);
                        $('#tpn').text(specializedFirm.tpn);
                        $('#pDzongkhag').text(spFirmDTO.pDzongkhagTxt);
                        $('#pGewog').text(spFirmDTO.pGewogTxt);
                        $('#pVillage').text(spFirmDTO.pVillageTxt);
                        $('#estAddress').text(specializedFirm.estAddress);
                        $('#estDzongkhag').text(spFirmDTO.estDzongkhagTxt);
                        $('#regEmail').text(specializedFirm.regEmail);
                        $('#regMobileNo').text(specializedFirm.regMobileNo);
                        $('#regPhoneNo').text(specializedFirm.regPhoneNo);
                        $('#regFaxNo').text(specializedFirm.regFaxNo);
                        $('#ownershipChangeRemarks').text(spFirmDTO.ownershipChangeRemarks);
                        $('#regEmails').val(specializedFirm.regEmail);

                        incorporation(spFirmDTO.incAttachments);
                        getSpFirmFinal(applicationNo);

                        var appHistoryDTOs = spFirmDTO.appHistoryDTOs;
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

                        var specializedFirmHrs = spFirmDTO.spFirmHRs;
                        var partnerHrTr = "";
                        var hrTr = "";
                        var m = 0, n = 0;
                        var owner='';
                      /*  for (var i in specializedFirmHrs) {
                            var verifiedApproved = '';
                            if(specializedFirmHrs[i].Approved == '1'){
                                verifiedApproved = verifiedApproved + "<td>(✔)</td>";
                                verifiedApproved = verifiedApproved + "<td>(✔)</td>";
                            } else if(specializedFirmHrs[i].verified == '1'){
                                verifiedApproved = verifiedApproved + "<td>(✔)</td>";
                                verifiedApproved = verifiedApproved + "<td><input type='checkbox' style='zoom:1.6' class='check' disabled value='1'  required='true'></td>";
                            } else{
                                verifiedApproved = verifiedApproved + "<td><input type='checkbox' style='zoom:1.6' class='check' disabled value='1'  required='true'></td>";
                            }
                            if (specializedFirmHrs[i].isPartnerOrOwner == '1' && specializedFirmHrs[i].deleteRequest != '1') {
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
                            }
                        }
                        $('#partnerDtls').find('tbody').html(partnerHrTr);*/
                        getSpFirmFinal(applicationNo);
                    } else{
                        warningMsg(res.text);
                    }
                }
            });
        }
        getServicesFee(applicationNo);
    }

    function getSpecializedFirmOwner() {
        var applicationNo = $('#appNoVA').val();
        if (applicationNo) {
            $.ajax({
                url: _baseURL() + '/getSpecializedFirmOwner',
                type: 'GET',
                data: {appNo: applicationNo},
                success: function (res) {
                  //  if (res.status == '1') {
                        var existingHrs = res.existing;
                        var editedHrs = res.edited;
                        var newlyAddedHrs = res.newlyAdded;
                        var deletedHRs = res.deleted;

                        addOW("existing-ow",existingHrs);
                        addOW("edited-ow",editedHrs);
                        addOW("newly-added-ow",newlyAddedHrs);
                        addOW("deleted-ow",deletedHRs);
                    /*} else {
                        warningMsg(res.text);
                    }*/
                }
            });
        }
    }

    function addOW(tBodyClass, specializedFirmHrs){

        var partnerHrTr = "";
        var hrTr = "", owner, m=0;

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
                "<td class='countryName'>" + specializedFirmHrs[i].countryName + "</td>" +
                "<td class='cidNo'>" + specializedFirmHrs[i].cidNo + "</td>" +
                "<td class='salutationName'>" + specializedFirmHrs[i].salutationName + "</td>" +
                "<td class='name'>" + specializedFirmHrs[i].name + "</td>" +
                "<td class='sex'>" + specializedFirmHrs[i].sex + "</td>" +
                "<td class='designationName'>" + specializedFirmHrs[i].designationName + "</td>" +
                "<td>" + ((specializedFirmHrs[i].siCertificate == '1')?'(✔)':'') + "</td>" +
                "<td><input type='button' name='humanResource' value='Check for this CID' class='checkCid btn btn-success'></td>" +
                verifiedApproved+"</tr>";
            }
        }
        //$('#partnerDtls').find('tbody').html(partnerHrTr);
        $('#partnerDtls').find('.'+tBodyClass).append(partnerHrTr);
    }

    function getSpFirmFinal(appNo) {
        $.ajax({
            url: _baseURL() + '/getSpFirmFinal',
            type: 'GET',
            data: {appNo: appNo},
            success: function (res) {
                var spFirm = res;
                $('#oldfirmName').html(spFirm.firmName);
                $('#estAddressExist').html(spFirm.regAddress);
                $('#estDzongkhagExist').html(spFirm.oldDzongkhag);
                $('#regEmailExist').html(spFirm.regEmail);
                $('#regMobileNoExist').html(spFirm.regMobileNo);
                $('#regPhoneNoExist').html(spFirm.regPhoneNo);
                $('#regFaxNoExist').html(spFirm.regFaxNo);
            }
        });
    }

    /*function nullif(val){
        if(val == null || val == 'null'){
            val = ''
        }
        return val;
    }*/

 /*   function getHrs() {
        var applicationNo = $('#appNoVA').val();
        if (!applicationNo) {
            return;
        }
        $.ajax({
            url: _baseURL() + '/getHrs',
            type: 'GET',
            data: {appNo: applicationNo},
            success: function (res) {
                var existingHrs = res.existing;
                var editedHrs = res.edited;
                var newlyAddedHrs = res.newlyAdded;
                var deletedHRs = res.deleted;

                addHR("existing-hr",existingHrs);
                addHR("edited-hr",editedHrs);
                addHR("newly-added-hr",newlyAddedHrs);
                addHR("deleted-hr",deletedHRs);

            }
        })
    }*/

    function incorporation(data){
        if(data){
            $('#cIncorporation').removeClass('hide');
            $('#oIncorporation').removeClass('hide');
            var cIncTr = '';
            var categoryTr = '';
            var ownerTr = '';
            for(var i in data){
                if(data[i].attachmentFor == 'InSole' || data[i].attachmentFor == null) {
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

    function addHR(tBodyClass, specializedFirmHrs){

        var partnerHrTr = "";
        var hrTr = "";

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
            var attachments = '';
            for (var j in specializedFirmHrs[i].hrAttachments) {
                attachments = attachments + "<span class='hra'><input type='hidden' class='hraId' value='" + specializedFirmHrs[i].hrAttachments[j].id + "'>" +
                "<a href='" + _baseURL() + "/viewDownload?documentPath=" + specializedFirmHrs[i].hrAttachments[j].documentPath + "' target='_blank'>" + specializedFirmHrs[i].hrAttachments[j].documentName + "</a></span><br>";
            }
            hrTr = hrTr + "<tr class=''>" +
            "<td class='salutationName'><input type='hidden' class='specializedFirmHRid' name='specializedFirmHrs[0].id' value='" + specializedFirmHrs[i].id + "'/>" +
            "<input type='hidden' class='joiningDate' value='" + specializedFirmHrs[i].joiningDate + "'/>" +
            specializedFirmHrs[i].salutationName + "</td>" +
            "<td class='name'>" + specializedFirmHrs[i].name + "</td>" +
            "<td class='cidNo'>" + specializedFirmHrs[i].cidNo + "</td>" +
            "<td class='sex'>" + specializedFirmHrs[i].sex + "</td>" +
            "<td class='countryName'>" + specializedFirmHrs[i].countryName + "</td>" +
            "<td class='designationName'>" + specializedFirmHrs[i].designationName + "</td>" +
            "<td class='qualificationName'>" + specializedFirmHrs[i].qualificationName + "</td>" +
            "<td class='tradeName'>" + specializedFirmHrs[i].tradeName + "</td>" +
            "<td class='serviceTypeName'>" + specializedFirmHrs[i].serviceTypeName + "</td>" +
            "<td>" + nullif(specializedFirmHrs[i].joiningDate) + "</td>" +
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
    function addEQ(tBodyClass, spFirmEQs){

        var eqTr = "";

        for (var i in spFirmEQs) {
            var verifiedApprovedEq = '';
            if(spFirmEQs[i].approved == '1'){
                verifiedApprovedEq = verifiedApprovedEq + "<td>(✔)</td>";
                verifiedApprovedEq = verifiedApprovedEq + "<td>(✔)</td>";
            }
            else if(spFirmEQs[i].verified == '1'){
                verifiedApprovedEq = verifiedApprovedEq + "<td>(✔)</td>";
                verifiedApprovedEq = verifiedApprovedEq + "<td><input type='checkbox' style='zoom:1.6' name='approveEq' value='1' disabled class='check' required=''></td>";
            }else{
                verifiedApprovedEq = verifiedApprovedEq + "<td><input type='checkbox' style='zoom:1.6' name='verifyEq' value='1' disabled class='check' required=''></td>";
            }
            var attachments = '';
            for (var j in spFirmEQs[i].eqAttachments) {
                attachments = attachments + "<span class='hra'><input type='hidden' class='hraId' value='" + spFirmEQs[i].eqAttachments[j].id + "'>" +
                "<a href='" + _baseURL() + "/viewDownload?documentPath=" + spFirmEQs[i].eqAttachments[j].documentPath + "' target='_blank'>" + spFirmEQs[i].eqAttachments[j].documentName + "</a></span><br>";
            }
            eqTr = eqTr + "<tr class=''>" +
            "<td>"+spFirmEQs[i].equipmentName + "</td>" +
            "<td>" + spFirmEQs[i].registrationNo + "</td>" +
            "<td>" + spFirmEQs[i].quantity + "</td>" +
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
                $('#specializedFirmCCTbl').find('tbody').html(tr);
            }
        })
    }

    function checkEquipment(){
        $('body').on('click','.equipmentCheck',function(){
            var regNo = $(this).closest('tr').find('.registrationNo').text();
            $check = $(this).closest('tr').find('.check');
          /*  if(!regNo){
                return;
            }*/
            $.ajax({
                url: cdbGlobal.baseURL() + "/admin/specializedFirm/action/checkEquipment",
                type: 'GET',
                data: {regNo: regNo, serviceName:'specializedFirm'},
                success: function (res) {
                    $('#eqInfo').append("<br/> <b>Owner: </b> " + "Drakpa" + "  ||  <b> Owner CID:  </b> "+ "1111111" +"  ||  <b> Region:</b>"+"Thimphu"+" ||  <b> Vehicle Type:</b>"+"Medium"+"");
                    var vehicleDetailses = res.dto.vehicleDetailses;
                    var cdbDtlsDTO = res.dto.cdbDTOs;
                    if(vehicleDetailses !=''){
                        for(var i in vehicleDetailses){
                            var vRegNo = employeeDetailsDTO[i].registrationNo;
                            if(vRegNo !='' && vRegNo != null){
                                $('#eqInfo').append("<br/> <b>Owner: </b> "+vehicleDetailses[i].ownerName+"  ||  <b> Owner CID:  </b> "+ +"  ||  <b> Region:</b>"+vehicleDetailses[i].registeredRegion+" ||  <b> Vehicle Type:</b>"+vehicleDetailses[i].vehicleType+"");
                            }else{
                                $('#eqInfo').append("<br/> This equipment is not registered.");
                            }
                        }
                    } else {
                        $('#eqInfo').append("<br/> This equipment is not registered.");
                    }
                    if(cdbDtlsDTO !=''){
                        for(var i in cdbDtlsDTO){
                            $('#engStatusInfo').append("<br/> <b>Equipment is owned by: </b> "+cdbDtlsDTO[i].consultantFirmname+" ( CDB No." +cdbDtlsDTO[i].consultantCDBNo+" )");
                        }
                    }else{
                        $('#engStatusInfo').append("<br/>This equipment is not registered in any firm. ");
                    }
                    $("#CheckModalEquipment").modal('show');
                }
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

    function getSpFirmInfoForPayment() {
        var applicationNo = $('#appNoPayment').val();
        if (applicationNo) {
            $.ajax({
                url: _baseURL() + '/getSpecializedFirmInfo',
                type: 'GET',
                data: {appNo: applicationNo,flag:'P'},
                success: function (res) {
                    if (res.status == '1') {
                        var spFirmDTO = res.dto;
                        var specializedFirm = spFirmDTO.specializedFirm;
                        $('#ownershipType').text(spFirmDTO.ownershipTypeTxt);
                        $('#country').text(spFirmDTO.countryTxt);
                        $('#tradeLicenseNo').text(specializedFirm.tradeLicenseNo);
                        $('#firmName').text(specializedFirm.firmName);
                        $('#tpn').text(specializedFirm.tpn);

                        $('#estAddress').text(specializedFirm.estAddress);
                        $('#estDzongkhag').text(spFirmDTO.estDzongkhagTxt);
                        $('#regEmail').text(specializedFirm.regEmail);
                        $('#regMobileNo').text(specializedFirm.regMobileNo);
                        $('#regPhoneNo').text(specializedFirm.regPhoneNo);
                        $('#regFaxNo').text(specializedFirm.regFaxNo);

                    } else {
                        warningMsg(res.text);
                    }
                }
            });
            getServicesFee(applicationNo);
        }
    }

    function getServicesFee(applicationNo){
      //  alert(applicationNo);
        $.ajax({
            url: _baseURL() + '/getAppliedServices',
            type: 'GET',
            data: {appNo: applicationNo},
            success: function (res) {
                var tr = ""; var tApplAmount = 0;
                for(var i in res){
                    tApplAmount += parseFloat(res[i].paymentAmount);
                    tr = tr + "<tr>" +
                    "<td><input type='checkbox' style='zoom:1.6' name='service["+i+"]' value='"+res[i].serviceId+"' checked/></td>"+
                    "<td>"+res[i].serviceName+"</td>"+
                    "<td>"+res[i].paymentAmount+"</td></tr>"
                }

                var tfoot = "<tr style='font-weight: bold'><td colspan='2' align='right'>Total</td><td>"+tApplAmount+"</td></tr> ";

                $('#serviceTbl').find('tbody').empty().append(tr);
                $('#serviceTbl').find('tfoot').html(tfoot);
                $('#paymentAmount').val(tApplAmount);
            }
        });
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

    function sendNotification(){
        $('#sendNotification').on('change',function() {
            var emailId = $('#regEmails').val();
            var cdbNo = $('#cdbNo').val();
            $.ajax({
                url: _baseURL() + '/sendNotification',
                type: 'GET',
                data: {email: emailId,cdbNo:cdbNo},
                success: function (res) {
                    successMsg(res.text);
                }
            });
        });
    }

    function init(){
        approve();
        reject();
        verify();
      //  getSpecializedFirmInfo();
        getSpFirmInfoForPayment();
        paymentUpdate();
        checkHR();
        sendBack();
        getAppliedServices();
        checkEquipment();
        sendNotification();
    }
    return {
        init:init
    };
})();

$(document).ready(function () {
        specializedFirmRCAction.init();
    }
);