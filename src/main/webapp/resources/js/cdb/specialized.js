/**
 * Created by USER on 3/22/2020.
 */

function _baseURL() {
    return cdbGlobal.baseURL() + "/trade";
}
function nextTab(presentClass, nextClass,type){
        if(presentClass=="feesStructure" && validateFees()){
            var cid = $('#app_çid').val();
            var url= _baseURL() +'/getPersonalInfo';
            $.ajax({
                url:url,
                type: 'GET',
                data: {cid: cid,type: "fetch"},
                success: function (res) {
                    if (res.status == '1') {
                        var dto = res.dto;
                        $('#name').val(dto.fullName).prop('readonly', true);
                        $('#cidNo').val(cid);
                        $('#dzongkhagId').val(dto.dzongkhagId);
                        $('#dzongkhag').val(dto.dzongkhagNmae);
                        $('#gewog').val(dto.gowegName);
                        $('#village').val(dto.villageName);
                        var imagelink='https://www.citizenservices.gov.bt/BtImgWS/ImageServlet?type=PH&cidNo='+cid;
                        $('#imageId').html("<img src='"+imagelink+"'  width='200px'  height='200px' class='pull-right'/>");
                        changeNextTab(presentClass, nextClass)
                    }else{
                        var dto = res.dto;
                        $('#name').val(dto.fullName).prop('readonly', false);
                        $('#cidNo').val(cid).prop('readonly', false);
                        $('#dzongkhagId').val(dto.dzongkhagId).prop('readonly', false);
                        $('#dzongkhag').val(dto.dzongkhagNmae).prop('readonly', false);
                        $('#gewog').val(dto.gowegName).prop('readonly', false);
                        $('#village').val(dto.villageName).prop('readonly', false);
                        $('#app_çid_err').val(dto.remarks).prop('readonly', false);
                        var imagelink='https://www.citizenservices.gov.bt/BtImgWS/ImageServlet?type=PH&cidNo='+cid;
                        $('#imageId').html("<img src='"+imagelink+"'  width='200px'  height='200px' class='pull-right'/>");
                        changeNextTab(presentClass, nextClass)
                    }
                }
            });
        }if (presentClass == "personalInformation" && validatepersonalSection()) {
            changeNextTab(presentClass, nextClass)
        } else  if(presentClass=="categoryDtls" && validate_cc()){
        if(validate_cc() == true)
        {
            changeNextTab(presentClass, nextClass)
        }
        }else if(presentClass=="feesStructurerenewal"){
            changeNextTab(presentClass, nextClass)
        }else if(presentClass=="individualcate"){
        changeNextTab(presentClass, nextClass)
    }
}
function validateFees(){
    var retutype=true;
    if($('#app_çid').val()==""){
        warningMsg("Please provide your CID Number.");
        retutype=false;
    }
   /* if($('#app_çid').val()!="" && $('#app_çid').val().length!=11){
        $('#app_çid_err').html('Your CID Number Should be 11 digit');
        retutype=false;
    }*/
    return retutype;
}

function validatepersonalSection(){
    var retutype=true;
    if($('#mobileNo').val()==""){
        $('#mobileNo').focus();
        warningMsg("Please provide your mobile number.");
        $('#mobileNo').focus();
        retutype=false;
    }
    if($('#mobileNo').val().length > 8){
        $('#mobileNo').focus();
        warningMsg("Max length of mobile number is 8.");
        $('#mobileNo').focus();
        retutype=false;
    }
    if($('#regEmail').val()==""){
        $('#regEmail').focus();
        warningMsg("Please provide your email.");
        $('#regEmail').focus();
        retutype=false;
    }
    if($('#salutation').val()==""){
        $('#salutation').focus();
        warningMsg("Please select your salutation.");
        $('#salutation').focus();
        retutype=false;
    }
    return retutype;
}


function validate_cc() {
        var isValid = true;
        if($('.categoryCheck').is(':checked') == false){
            isValid = false;
            warningMsg("Please choose one category.");
        }
        return isValid;
}


function preventDot(e) {
    var key = e.charCode ? e.charCode : e.keyCode;
    if (key == 46)
    { return false; }
}


function changeNextTab(presentClass, nextClass){
    $("." + presentClass + ">a").addClass('bg-blue text-white');
    $('.tab-pane').removeClass("active");
    $('.tab-content').removeClass("active");
    $("." + nextClass).addClass("active");
    $("." + presentClass + ">a").append("<span id='classppended"+presentClass+"'><i class='fa fa-check ml-1' ></i></span>");
}
function previousTab(previousClass, presentClass){
    $("." + presentClass + ">a").addClass('bg-blue text-white');
    $('.tab-pane').removeClass("active");
    $('.tab-content').removeClass("active");
    $("." + previousClass).addClass("active");
    $("#classppended"+previousClass).remove();
}

function validateAttachment(vl,id,checkId){
    if(vl!=""){
        $('#'+id).prop('class', 'alert badge-info');
    }
    else{
        $('#'+id).prop('class', 'alert badge-danger');
    }
}

var inicount=1;
function addmoreattachemnts(){
    inicount++;
    var fnshow='validateAttachment(this.value,"file'+inicount+'","file_added'+inicount+'")';
    $('#fileadd').append('<div class="form-group row" id="addedfile'+inicount+'"><div class="col-sm-6"><input type="file" class="alert badge-danger"  accept="application/msword,application/pdf,application/vnd.ms-excel,image/gif, image/jpeg, image/jpg,application/vnd.openxmlformats-officedocument.wordprocessingml.document" onchange=\''+fnshow+'\' name="files" id="file'+inicount+'"><i id="file_added'+inicount+'"></i></div><div class="col-sm-6"><button class="btn btn-danger fa fa-pull-right mt-4" type="button" onclick="deleteate('+inicount+')"><i class="fa fa-times pr-4"></i>Delete this</button></div></div>');
}
function deleteate(id){
    $('#addedfile'+id).remove();
}
function submitRegistrationForm(){
    var returntpe=true;
    if($('#file1').val()==""){
        $('#file1').focus();
        warningMsg('Please attach your document');
        $('#file1').focus();
        returntpe=false;
    }else {
        $('#concirmationModel').modal('show');
        $('#actiontype').val('submit');
        $('#formId').val('specializedTradeForm');
        $('#targetId').val('acknowledgementmessage');
        $('#url').val(_baseURL() + '/saveSpecializedTrade');
        $('#messages').html('You are about to submit your application. Do you want to proceed?');
        returntpe;
    }
}

$('#submitbtn').prop('disabled',true);
function enablesubmit(){
    if($('#submitcheckbox').prop('checked')){
        $('#submitbtn').prop('disabled',false);
    }
    else{
        $('#submitbtn').prop('disabled',true);
    }
}

function SubmitApplicationDetials(){
 //  alert(type);
    var itemId = [];
    $.each($("input[name='itemId']:checked"), function(){
        itemId = ($(this).val());
    });
    var url='/cdb/public_access/emptylayout/saveSpecializedTrade?itemId='+itemId;
    var options = {
        target:'#registrtaionFormCard',
        url:url,
        type:'POST',
        enctype: 'multipart/form-data',
        data: $('#specializedTradeForm').serialize()
    };
    $("#specializedTradeForm").ajaxSubmit(options);
    $('#concirmationModel').modal('hide');
}

function closemodel(modelId){
    $('#'+modelId).modal('hide');
}

function SubmitApproveVerifyApplicationDetials(firmId,targetId){
    var url=_baseURL() +'/saveArchitect';
    var options = {target:'acknowledgementmessage',url:url,type:'POST',enctype: 'multipart/form-data', data: $('#architectForm').serialize()};
    $("#architectForm").ajaxSubmit(options);
    $('#registrtaionFormCard').hide();
    $('#acknowledgementCard').show();
    $('#concirmationModel').modal('hide');
}

function updateReject(){
    if(validateReject()){
        var url= '/cdb/admin_specializedTrade/emptylayout/updatereject';
        var options = {target:'#content_main_div',url:url,type:'GET', data: $('#tradeverificationForm').serialize()};
        $("#tradeverificationForm").ajaxSubmit(options);
    }
}
function validateReject(){
    var returntpe=true;
    if($('#remarks').val()==""){
        $('#remarks_err').html('Please mention remarks');
        $('#remarks').focus();
        returntpe=false;
    }
    return returntpe;
}

function verifyApplication(){
    var url= '/cdb/admin_specializedTrade/emptylayout/verifySpTradeRegistration';
    var options = {target:'#content_main_div',url:url,type:'GET', data: $('#tradeverificationForm').serialize()};
    $("#tradeverificationForm").ajaxSubmit(options);
}
function approveApplication(type){
    url= '/cdb/admin_specializedTrade/emptylayout/approveSpTradeRegistration?servicefor='+type;
    var options = {target:'#content_main_div',url:url,type:'GET', data: $('#tradeverificationForm').serialize()};
    $("#tradeverificationForm").ajaxSubmit(options);
}

$('.datepicker').datepicker({
    changeMonth:true,
    changeYear: true,
    autoclose: true,
    todayHighlight: true,
    dateFormat:'yy-mm-dd',
    minDate: +1
}).on('changeDate',function(){
    $('.datepicker').datepicker('hide');
});

function approveAndGenerateCertificate(type,ownershipType){
    var paymentType =$('#paymentmode').val();
    //alert(paymentType);
    if(paymentType == "10") {
        var url= '/cdb/admin_specializedTrade/emptylayout/approveAndGenerateCertificate?servicefor='+type+ '&ownershipType='+ownershipType;
        var options = {target:'#content_main_div',url:url,type:'POST', data: $('#tradeverificationForm').serialize()};
        $("#tradeverificationForm").ajaxSubmit(options);
    }
    else {
       if(validateFeesDetails()== true) {
           var url = '/cdb/admin_specializedTrade/emptylayout/approveAndGenerateCertificate?servicefor=' + type + '&ownershipType=' + ownershipType;
           var options = {
               target: '#content_main_div',
               url: url,
               type: 'POST',
               data: $('#tradeverificationForm').serialize()
           };
           $("#tradeverificationForm").ajaxSubmit(options);
       }
    }
}
function validateFeesDetails(){
    var return_type=true;
    if($('#paymentReceiptDate').val()==""){
        $('#paymentReceiptDate').focus();
        warningMsg('Please mention payment receipt date');
        $('#paymentReceiptDate').focus();
        return_type=false;
    }
    if($('#paymentReceiptNo').val()==""){
        $('#paymentReceiptNo').focus();
        warningMsg('Please mention payment receipt number');
        $('#paymentReceiptNo').focus();
        return_type=false;
    }
    if($('#paymentmode').val()==""){
        $('#paymentmode').focus();
        warningMsg('Please select mode of payment');
        $('#paymentmode').focus();
        return_type=false;
    }
    return return_type;
}
$('#architect_table').DataTable({
    responsive: true
});

function printCertificate(cdbNo){
    window.open('/cdb/print/printCertificate?cdbNo=' + cdbNo);
}

function printInfo(cdbNo){
    var url= '/cdb/public_access/profile/printInformation?cdbNo='+cdbNo;
    $('#content_main_div_public_user').load(url);
}

//function submitForm(){
//    $('#concirmationRenewalModel').modal('show');
//    $('#messages').html('You are about to submit application. Do you want to proceed?');
//}

function submitForm(){
    var returntpe=true;
    if($('#file1').val()==""){
        $('#file1').focus();
        warningMsg('Please attach your document');
        $('#file1').focus();
        returntpe=false;
    }else {
        $('#concirmationRenewalModel').modal('show');
        $('#actiontype').val('submit');
        $('#formId').val('specializedTradeForm');
        $('#targetId').val('acknowledgementmessage');
        $('#url').val(_baseURL() + '/saveSpecializedTrade');
        $('#messages').html('You are about to submit your application. Do you want to proceed?');
        returntpe;
    }
}

function SubmitRenewalApplicationDetials(){
    var itemId = [];
    $.each($("input[name='itemId']:checked"), function(){
        itemId = ($(this).val());
    });
    var url= '/cdb/public_access/emptylayout/submitRenwalApplicationSpTrade?itemId='+itemId;
    var options = {target:'#content_main_div_public_user',url:url,type:'POST', data: $('#specializedTraderenewalForm').serialize()};
    $("#specializedTraderenewalForm").ajaxSubmit(options);
    $('#concirmationRenewalModel').modal('hide');
}

function viewAttachment(uuid,type,docType){
    //var url= '${pageContext.request.contextPath}/FileDownloadServlet?uuid='+uuid+'&type='+type;
    var url= '/cdb/public_access_trade/emptylayout/donwloadFiles?uuid='+uuid+'&type='+type+ '&docType='+docType;
    window.open(url,'_blank');
}

function submitcancellation(){
    var url= '/cdb/public_access/emptylayout/submitspcancellation';
    var options = {target:'#content_main_div_public_user',url:url,type:'POST', data: $('#specializedTraderenewalForm').serialize()};
    $("#specializedTraderenewalForm").ajaxSubmit(options);
}

function SubmitRenewalApplicationDetialsInco(){
    var url='/cdb/public_access/emptylayout/saveRenewalSpecializedtradeInco';
    var options = {target:'#registrtaionFormCard', url:url, type:'POST', enctype: 'multipart/form-data', data: $('#specializedTradeFormRenewInco').serialize()};
    $("#specializedTradeFormRenewInco").ajaxSubmit(options);
    $('#concirmationModel').modal('hide');
}

function PrintDiv() {
    var divToPrint = document.getElementById('print');
    var popupWin = window.open('', '_blank', 'width=1000,height=950');
    popupWin.document.open();
    popupWin.document.write('<html><body onload="window.print()">' + divToPrint.innerHTML + '</html>');
    popupWin.document.close();
  //  var url = '${pageContext.request.contextPath}/ruralTimber/afterPrintingApp';
    var options = {
        target: '#registrtaionFormCard',
        url: url,
        type: 'POST',
        enctype: 'form-data',
        data: $('#printingForm').serialize()
    };
    $("#printingForm").submit();
}

function PrintInfo() {
    var divToPrint = document.getElementById('printInfo');
    var popupWin = window.open('', '_blank', 'width=1000,height=1000');
    popupWin.document.open();
    popupWin.document.write('<html><body onload="window.print()">' + divToPrint.innerHTML + '</html>');
    popupWin.document.close();
    //  var url = '${pageContext.request.contextPath}/ruralTimber/afterPrintingApp';
    var options = {
        target: '#registrtaionFormCard',
        url: url,
        type: 'POST',
        enctype: 'form-data',
        data: $('#printingForm').serialize()
    };
    $("#printingForm").submit();
}

var cert = "<tr><td></td>" +
    "<td><input type='text' class='form-control' name='documentName'/> </td>"+
    "<td><input type='file' name='files' class='form-control-file file'></td>" +
        /* "<td class='file-size'></td>" +*/
    "<td><a class='p-2'><i class='fa fa-pencil text-green'></i></a><a class='p-2'><i class='fa fa-trash text-danger'></i></a></td>" +
    "</tr>";

$('#addMoreCert').on('click',function(e){
    var certificateTbl = $('#certificateTbl').find('tbody').append(cert);
});

function validatePartnership(){
    var retutype=true;
    if($('#ownershiptype').val()==""){
        //$('#owner_err').html('Please select ownership type');
        $('#ownershiptype').focus();
        retutype=false;
    }
    if($('#countryId').val()==""){
       // $('#country_err').html('Please select your country');
        $('#countryId').focus();
        retutype=false;
    }
    if($('#firmnameid').val()==""){
      //  $('#firmname_err').html('Please provide your firm name');
        $('#firmnameid').focus();
        retutype=false;
    }
    return retutype;
}

function addRow(tableId) {
    var $tableBody = $('#' + tableId).find("tbody");
    var isValid = true;
    $tableBody.find(':input').each(function () {
        if (isValid == true) {
            isValid = $('#specializedTradeForm').validate().element(this);
        } else {
            $('#specializedTradeForm').validate().element(this);
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

function validateEstbAddrss() {
    var retutype = true;

    if ($('#regMobileNo').val() == "" && $('#regMobileNo').length>8) {
        $('#regMobileNo').focus();
        warningMsg('Please provide your mobile number');
        $('#regMobileNo').focus();
        retutype = false;
    }

    if ($('#regEmail').val() == "") {
        $('#regEmail').focus();
        warningMsg("Please provide your email.");
        $('#regEmail').focus();
        retutype = false;
    }
    if ($('#regDzongkhagId').val() == "") {
        $('#regDzongkhagId').focus();
        warningMsg('Please select your Dzongkhag');
        $('#regDzongkhagId').focus();
        retutype = false;
    }
    if ($('#estAddress').val() == "") {
        $('#estAddress').focus();
        warningMsg('Please provide your Establishment Address ');
        $('#estAddress').focus();
        retutype = false;
    }
    if ($('#dzongkhagId').val() == "") {
        $('#dzongkhagId').focus();
        retutype = false;
    }

    if ($('#pVillageId').val() == "") {
        $('#pVillageId').focus();
        retutype = false;
    }
    if ($('#pGewogId').val() == "") {
        $('#pGewogId').focus();
        retutype = false;
    }
    return retutype;
}
    function getModalData(tableId, prefix, totalCol) {
        var td = "";
        var modal = $('#'+prefix+'1').closest('.modal');
        if(modal.find(':input').valid() == false){
            return false;
        }
        for (var i = 1; i <= totalCol; i++) {
            var $this = $("#" + prefix + i);

            var text = '',value = '',name = '';

            var input_type = $this.prop('type');
            if(~input_type.indexOf("select")){
                value =$this.val();
                text = $this.find('option:selected').html();
                name = $this.prop('name');
            }else{
                value =$this.val();
                text = value;
                name = $this.prop('name');
            }

            var tdVal = "<input type='hidden' name='"+name+"' value='"+value+"'/>"+text;
            td = td + "<td>" + tdVal + "</td>";
        }
        td = td + "<td><input type='file' name='fileHR' class='form-control-file' required=''></td>";

        var tr = "<tr>" + td + "<td><a class='p-2'><i class='fa fa-pencil text-green' title='Payment' data-toggle='modal' data-target='#addHRModal'></i></a><a class='p-2'><i class='fa fa-trash text-danger'></i></a></td></tr>";
        $("#" + tableId).find(".noRecord").hide();
        $('#' + tableId).append(tr);

        modal.find(":input").val('');
        modal.modal('hide');
    }
function addMoreFile(){
   // alert();
    $('.hrFile').on('change',function(e){
        var uplTbl = $('#hrUploadTbl').find('tbody');
        var $this = $(this), $clone = $this.clone();
        var tr = "<tr><td><input type='text' class='form-control' name='specializedtradeHRAttachments[0].documentName'/> </td>" +
            "<td class='file'></td><td></td><td></td></tr>";
        uplTbl.append(tr);
        $this.after($clone).appendTo(uplTbl.find('.file'));
    });
}


function checkForApplicable(value){
        if(value==10){
            $('#paymentForm').hide();
        }else{
            $('#paymentForm').show();
        }
}


function isMailUnique(mailId){
    var url= _baseURL() +'/isMailUnique';
    var $this = $('#regEmail');
    if(validate() == true) {
    $.ajax({
        url:url,
        type: 'GET',
        data: {mailId: mailId},
        success: function (res) {
            if (res.status == '1') {
                //  $('#mail_err').html('This Email is already registered. Please provide unique Email Address');
                $this.val('').focus();
                warningMsg("This email has already been registered.");
                $this.val('').focus();
            }else{
                $('#mail_err').html('');
            }
        }
    });
    }else{
        $this.val('').focus();
    }
}

function checkStatus(cidNo){
    var url= _baseURL() +'/isCIDUnique';
    var $this = $('#app_çid');
    $.ajax({
        url:url,
        type: 'GET',
        data: {cidNo: cidNo},
        success: function (res) {
            if (res.status == '1') {
                $this.val('').focus();
                warningMsg(res.text);
                $this.val('').focus();
            }else{

            }
        }
    });
}

function validate() {
    const email =$('#regEmail').val();
    if (validateEmail(email)) {
        return true;
    } else {
        errorMsg('oops!! please check your email format');
        return false;
    }
}

function validateEmail() {
    var email = $('#regEmail').val();
    const re = /^(([^<>()[\]\\.,;:\s@\"]+(\.[^<>()[\]\\.,;:\s@\"]+)*)|(\".+\"))@((\[[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\])|(([a-zA-Z\-0-9]+\.)+[a-zA-Z]{2,}))$/;
    return re.test(email);
}


function checkForEngagement(cidNo){
    $('body').on('click','.checkCid',function(){
        //var modal = $(this).closest('.modal').attr('id');
        var cidNo = $('#cidNo').val();
        if(!cidNo){
            return;
        }
        $.ajax({
            url: cdbGlobal.baseURL() + "/trade/getPersonalInfo",
            type: 'GET',
            data: {cid: cidNo,type:"check"},
            success: function (res) {
                //   alert('asdf');
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

                    var employeeDetailsDTO = dto.employeeDetailsDTOs;
                    var empDtls ="",empDtls1="",empDtls2="";
                    if(employeeDetailsDTO != ""){
                        for(var i in employeeDetailsDTO){
                            var workId = employeeDetailsDTO[i].workId;
                            alert(workId);
                            if(workId !=''|| workId !='null'){
                                $('#engagedId').show();
                                $('#dcbinfo').append("<br/> This person is engaged with cdb number <b>"+employeeDetailsDTO[i].cdbNo+"</b> in <b>"+employeeDetailsDTO[i].procuringAgency+"</b> with work Id:<b>"+employeeDetailsDTO[i].workId+"</b>");
                                $('#cidNumber').text(dto.cidNo); $('#hrName').text((dto.fullName));
                            } else{
                                $('#dcbinfo').hide();
                                $('#engagedId').show();
                                $('#dcbinfonotEngaged').append("<br/> This person is not engaged in any work or project");
                            }
                        }
                    }else{
                        $('#dcbinfo').hide();
                        $('#engagedId').show();
                        $('#dcbinfonotEngaged').append("<br/> This person is not engaged in any work or project");
                    }
                }
            }
        });
    });
}

checkForEngagement();
