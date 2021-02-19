/**
 * Created by USER on 3/22/2020.
 */
function _baseURL() {
    return cdbGlobal.baseURL() + "/architects";
}
function nextTab(presentClass, nextClass){
    if(presentClass=="feesStructure"&& validateFees()){
        var cid = $('#app_çid').val();
        var url= _baseURL() +'/getPersonalInfo';
        $.ajax({
            url:url,
            type: 'GET',
            data: {cid: cid, type: "fetch"},
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
                    $('#dzongkhagId').val(dto.dzongkhagId).prop('readonly', false).prop('hide', true);
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
    }
    else if(presentClass=="personalInformation" && validatepersonalSection()){
        changeNextTab(presentClass, nextClass)
    }
    else if(presentClass=="categoryDtls" && validateeducaion()){
        changeNextTab(presentClass, nextClass)
    }
    else if(presentClass=="feesStructurerenewal"){
        changeNextTab(presentClass, nextClass)
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

function validateFees(){
    var retutype=true;
    if($('#app_çid').val()==""){
        warningMsg("Please provide your CID Number.");
        retutype=false;
    }
  /*  if($('#app_çid').val()!="" && $('#app_çid').val().length!=11){
        $('#app_çid_err').html('Your CID Number Should be 11 digit');
        retutype=false;
    }*/
    return retutype;
}

function validatepersonalSection(){
    var retutype=true;
    if($('#trade').val()==""){
        $('#trade').focus();
        warningMsg("Please select trade.");
        $('#trade').focus();
        retutype=false;
    }
    if($('#serviceSectorType').val()==""){
        $('#serviceSectorType').focus();
        warningMsg("Please select service type.");
        $('#serviceSectorType').focus();
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

function validateeducaion(){
    var retutype=true;
    if($('#universityCountry').val()==""){
        $('#universityCountry').focus();
        warningMsg("Please select country of university.");
        $('#universityCountry').focus();
        retutype=false;
    }

    if($('#universityName').val()==""){
        $('#universityName').focus();
        warningMsg("Please mention name of your university.");
        $('#universityName').focus();
        retutype=false;
    }
    if($('#graduationYear').val()==""){
        $('#graduationYear').focus();
        warningMsg("Please mention year of graduation.");
        $('#graduationYear').focus();
        retutype=false;
    }
    if($('#qualificationId').val()==""){
        $('#qualificationId').focus();
        warningMsg("Please select your qualification.");
        $('#qualificationId').focus();
        retutype=false;
    }
    if($('#mobileNo').val()==""){
        $('#mobileNo').focus();
        warningMsg("Please provide your mobile number.");
        $('#mobileNo').focus();
        retutype=false;
    }

    if($('#regEmail').val()==""){
        //  $('#mail_err').html('Please provide your email');
        $('#regEmail').focus();
        warningMsg("Please provide your email.");
        $('#regEmail').focus();
        retutype=false;
    }
    return retutype;
}

function preventDot(e) {
    var key = e.charCode ? e.charCode : e.keyCode;
    if (key == 46)
    { return false; }
}

function remove_err(errId){
    $('#'+errId).html('');
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
        warningMsg('Please attach your documents');
        $('#file1').focus();
        returntpe=false;
    }else {
        $('#concirmationModel').modal('show');
        $('#actiontype').val('submit');
        $('#formId').val('architectForm');
        $('#targetId').val('acknowledgementmessage');
        $('#url').val(_baseURL() + '/saveArchitect');
        $('#messages').html('You are about to submit application. Are you sure to proceed ?');
        returntpe;
    }
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

    // var data = new FormData($('#architectForm')[0]);
    /*$.ajax({
     url: url,
     type: 'POST',
     data: $('#architectForm').serialize(),
     enctype: 'multipart/form-data',
     //contentType: false,
     //processData: false,
     success: function (res){
     if (res.status == 1) {
     $("#acknowledgementmessage").html(res.text);
     $("#registrtaionFormCard").hide();
     $("#acknowledgementCard").show();
     } else{
     warningMsg(res.text);
     }
     }
     });*/

}

function updateReject(){
    if(validateReject()){
        var url= '/cdb/admin_architect/emptylayout/updatereject';
        var options = {target:'#content_main_div',url:url,type:'GET', data: $('#architectverificationForm').serialize()};
        $("#architectverificationForm").ajaxSubmit(options);
    }
}
function validateReject(){
    var returntpe=true;
    if($('#remarks').val()==""){
        $('#remarks').focus();
        warningMsg('Please mention remarks');
        $('#remarks').focus();
        returntpe=false;
    }
    return returntpe;
}

function verifyApplication(){
    var url= '/cdb/admin_architect/emptylayout/verifyArchitectRegistration';
    var options = {target:'#content_main_div',url:url,type:'GET', data: $('#architectverificationForm').serialize()};
    $("#architectverificationForm").ajaxSubmit(options);
}
function approveApplication(type){
    url= '/cdb/admin_architect/emptylayout/approveArchitectRegistration?servicefor='+type;
    var options = {target:'#content_main_div',url:url,type:'GET', data: $('#architectverificationForm').serialize()};
    $("#architectverificationForm").ajaxSubmit(options);
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

function approveAndGenerateCertificate(type){
    var paymentType =$('#paymentmode').val();
    if(paymentType == "Not Applicable") {
        var url= '/cdb/admin_architect/emptylayout/approveAndGenerateCertificate?servicefor='+type;
        var options = {target:'#content_main_div',url:url,type:'POST', data: $('#architectverificationForm').serialize()};
        $("#architectverificationForm").ajaxSubmit(options);
    }
    else {
        if(validateFeesDetails() == true) {
            var url = '/cdb/admin_architect/emptylayout/approveAndGenerateCertificate?servicefor=' + type;
            var options = {
                target: '#content_main_div',
                url: url,
                type: 'POST',
                data: $('#architectverificationForm').serialize()
            };
            $("#architectverificationForm").ajaxSubmit(options);
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
    var url= '/cdb/admin_architect/emptylayout/printarchitectInfo?cdbNo='+cdbNo;
    $('#content_main_div_public_user').load(url);
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

$('#submitbtn').prop('disabled',true);
function enablesubmit(){
    if($('#submitcheckbox').prop('checked')){
        $('#submitbtn').prop('disabled',false);
    }
    else{
        $('#submitbtn').prop('disabled',true);
    }
}

/*function PrintInfo() {
    b3ece95606d75f5e942ddf1a94f0ce5281187821
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
}*/

function submitForm(){
    var returntpe=true;
    if($('#file1').val()==""){
        $('#file1').focus();
        warningMsg('Please attach your documents');
        $('#file1').focus();
        returntpe=false;
    }else {
        $('#concirmationRenewalModel').modal('show');
        $('#messages').html('You are about to submit application. Are you sure to proceed ?');
        returntpe;
    }
}
function SubmitApplicationDetials(){
    var url='/cdb/public_access/emptylayout/saveArchitect';
    var options = {target:'#registrtaionFormCard',url:url,type:'POST',enctype: 'multipart/form-data', data: $('#architectForm').serialize()};
    $("#architectForm").ajaxSubmit(options);
    $('#concirmationModel').modal('hide');
}
function SubmitRenewalApplicationDetials(){
        var url= '/cdb/public_access/emptylayout/submitRenwalApplication';
        var options = {target:'#content_main_div_public_user',url:url,type:'POST', data: $('#architectrenewalForm').serialize()};
        $("#architectrenewalForm").ajaxSubmit(options);
        $('#concirmationRenewalModel').modal('hide');

}
function viewAttachment(uuid,type,path,name){
    //var url= '${pageContext.request.contextPath}/FileDownloadServlet?uuid='+uuid+'&type='+type;
    var url= '/cdb/public_access/emptylayout/donwloadFiles?uuid='+uuid+'&type='+type;
    window.open(url,'_blank');
}

function submitcancellation() {
    if ($('#cancellationRemarks').val() == '') {
        warningMsg('please Provide reason for cancellation of certificate.');
    } else {
        var url = '/cdb/public_access/emptylayout/submitcancellation';
        var options = {
            target: '#content_main_div_public_user',
            url: url,
            type: 'POST',
            data: $('#architectrenewalForm').serialize()
        };
        $("#architectrenewalForm").ajaxSubmit(options);
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

function checkForApplicable(value){
    if(value=="Not Applicable"){
        $('#paymentForm').hide();
    } else{
        $('#paymentForm').show();
    }
}

function checkForEngagement(cidNo){
    $('body').on('click','.checkCid',function(){
        //var modal = $(this).closest('.modal').attr('id');
        var cidNo = $('#cidNo').val();
       // alert(cidNo);
        if(!cidNo){
            return;
        }
        debugger;
        $.ajax({
            url: cdbGlobal.baseURL() + "/architects/getPersonalInfo",
            type: 'GET',
            data: {cid: cidNo,type:"check"},
            success: function (res) {
               // alert('asdf');
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
                    var govCopDTO = dto.govCopDTOs;
                    var cdbDTO = dto.cdbDTOs;

                    if(employeeDetailsDTO !=''){
                        for(var i in employeeDetailsDTO){
                            var workId = employeeDetailsDTO[i].workId;
                            if(workId !='' && workId != null){
                                $('#dcbinfo').html("<br/> <b>CDB No: </b> "+employeeDetailsDTO[i].cdbNo+"  ||  <b> Procuring Agency:  </b> "+employeeDetailsDTO[i].procuringAgency+"  ||  <b> Work ID:</b>"+employeeDetailsDTO[i].workId+"");
                                // $('#dcbinfo').append("<br/> This person is engaged with cdb number <b>"+employeeDetailsDTO[i].cdbNo+"</b> in <b>"+employeeDetailsDTO[i].procuringAgency+"</b> with work Id:<b>"+employeeDetailsDTO[i].workId+"</b>");
                                $('#cidNumber').text(dto.cidNo); $('#hrName').text((dto.fullName));
                            }else{
                                $('#dcbinfonotEngaged').html("<br/> This person is not engaged in any work or project.");
                            }
                        }
                    }else{
                        // $('#dcbinfo').hide();
                        $('#dcbinfonotEngaged').html("<br/> This person is not engaged in any work or project.");
                    }

                    if(govCopDTO !=''){
                        for(var i in govCopDTO){
                            var agency = govCopDTO[i].agency;
                            if(agency !='' && agency !=null){
                                alert(agency);
                                $('#dcbinfo').html("<br/> <b>Position Title: </b> "+govCopDTO[i].positionTitle+"  ||  <b> Agency:  </b> "+govCopDTO[i].agency+"");
                            } else{
                                $('#dcbinfonotEngaged').html("<br/> This person is not a government/coperate employee.");
                            }
                        }
                    }else{
                        // $('#dcbinfo').hide();
                        $('#dcbinfonotEngaged').html("<br/> This person is not a government/coperate employee.");
                    }
                }
            }
        });
    });
}
checkForEngagement();
