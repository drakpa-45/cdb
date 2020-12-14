//region functions callable from jsp
function openModal(modalId) {
    $("#" + modalId).modal({backdrop: 'static', keyboard: false});
}

function addRow(tableId) {
    var $tableBody = $('#' + tableId).find("tbody");
    var isValid = true;
    $tableBody.find(':input').each(function () {
        if (isValid == true) {
            isValid = $('#consultantForm').validate().element(this);
        } else {
            $('#consultantForm').validate().element(this);
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
    if($('#' + tableId + ' tbody tr').length > 1) {
        $('#' + tableId + ' tr:last').remove();
    }else{
        warningMsg("You cannot delete last row");
    }
}

function saveAndPreview(presentClass, nextClass) {
    var content = '<h3 class="pt-3 text-center">Fee Structure</h3>' + $("#fees_structure >.div-actual").html() +
        '<h3 class="pt-3 text-center">General Information</h3>' + $("#general_Information >.div-actual").html() +
        '<h3 class="pt-3 text-center">Category Details</h3>' + $("#category_details >.div-actual").html()
        + '<h3 class="pt-3 text-center">Human Resource</h3>' + $("#human_resource_criteria >.div-actual").html() +
        '<h3 class="pt-3 text-center">Consultant Equipment Details</h3>' + $("#equipment_details >.div-actual").html();

    $("." + presentClass + "").addClass('bg-blue-light text-white');
    $('.tab-pane').removeClass("active").addClass("active");
    $('.tab-content').removeClass("active").addClass("active");
    $("." + nextClass).addClass("active");
    $("." + presentClass + ">a").append("<i class='fa fa-check ml-1'></i>");

    $('#btn1').hide();
    $('#btn2').hide();
    $('#btnValGINext').hide();
    $('#btnValCCNext').hide();
    $('#btn3').hide();
    $('#btn4').hide();
    $('#btnValHRNext').hide();
    $('#btn5').hide();
    $('#btnValEqNext').hide();
    $('#btn6').hide();

    window.scroll(0, 0);

   //$("#" + nextClass).prepend(content);
}

var hr_modal = $("#hrModal").html();
var eq_modal = $("#eqModal").html();
var j= 0;
function getModalData(tableId, prefix, totalCol) {
    $('#'+tableId).find('.tbd').remove();
    var td = "";
    var modal = $('#' + prefix + '1').closest('.modal');
    if (modal.find(':input').valid() == false) {
        warningMsg('Please provide your information');
        return false;
    }
    for (var i = 1; i <= totalCol; i++) {
        var $this = $("#" + prefix + i);

        var text = '', value = '', name = '';

        var input_type = $this.prop('type');
        if (~input_type.indexOf("select")) {
            value = $this.val();
            text = $this.find('option:selected').html();
            name = $this.prop('name');
        } else {
            value = $this.val();
            text = value;
            name = $this.prop('name');
        }

        var tdVal = "<input type='hidden' class='"+$this.attr('id')+"' name='" + name + "' value='" + value + "'/>" + text;
        td = td + "<td>" + tdVal + "</td>";
    }
    td = td + "<td ><span class='doc'></span> <div class='hidden hr_attachment'></div></td>";

    var tr = "<tr id='"+j+"'>" + td + "<td class=''><a class='p-2 edit-"+prefix+"'><i class='fa fa-pencil-square-o' aria-hidden='true'></i></a>" +
        "<a class='p-2 del_row'><i class='fa fa-trash text-danger '></i></a></td></tr>";

    $("#" + tableId).append(tr).find(".noRecord").hide();
    if(prefix == 'hr'){
        cloneHrFiles(tableId,modal,j);
    }
    if(prefix == 'eq'){
        cloneEqFiles(tableId,modal,j);
    }
    j= j+1;
}

function cloneHrFiles(tableId,modal,i){
    var uplTbl = $('#hrUploadTbl').find('tbody');
    var docName = '';

    uplTbl.find('.file').each(function(e){
        var index = $(this).closest('tr').index();
        $(this).attr('name', 'consultantHRs[0].consultantHRAs['+index+'].attachment');
    });

    uplTbl.find('.docName').each(function(e){
        var index = $(this).closest('tr').index();
        docName = docName +"<input type='hidden' name='consultantHRs[0].consultantHRAs["+index+"].documentName' value='"+$(this).val()+"'/><b>"+$(this).val() +'</b><br>';
    });
    var curTr = $("#" + tableId).find('#'+i);
    curTr.find('.doc').html(docName);

    uplTbl.find('.file').each(function(e){
        $(this).after($(this).clone()).appendTo(curTr.find('.hr_attachment'));
    });
    modal.modal('hide');
    $("#hrModal").empty().html(hr_modal);
}

function cloneEqFiles(tableId,modal,i){
    var uplTbl = $('#eqUploadTbl').find('tbody');
    var docName = '';

    uplTbl.find('.file').each(function(e){
        var index = $(this).closest('tr').index();
        $(this).attr('name', 'equipments[0].consultantEQAs['+index+'].attachment');
    });

    uplTbl.find('.docName').each(function(e){
        var index = $(this).closest('tr').index();
        docName = docName +"<input type='hidden' name='equipments[0].consultantEQAs["+index+"].documentName' value='"+$(this).val()+"'/><b>"+$(this).val() +'</b><br>';
    });
    var curTr = $("#" + tableId).find('#'+i);
    curTr.find('.doc').html(docName);

    uplTbl.find('.file').each(function(e){
        $(this).after($(this).clone()).appendTo(curTr.find('.hr_attachment'));
    });
    /* modal.find(":input").val('');
     modal.find('#addMore').val('Add More File');*/
    modal.modal('hide');
    $("#eqModal").empty().html(eq_modal);
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

function previousTab(previousClass, presentClass) {
    $("." + presentClass + ">a").addClass('bg-blue text-white');
    $('.tab-pane').removeClass("active");
    $('.tab-content').removeClass("active");
    $("." + previousClass).addClass("active");
    $("#classppended" + previousClass).remove();
}

function showConfirmation(){
    $('#confirmationModel').modal('show');
    //$('#actiontype').val('submit');
    //$('#formId').val('contractorForm');
    $('#targetId').val('acknowledgementmessage');
    $('#messages').html('You are about to submit application. Are you sure to proceed ?');
}
function submitApplication(){
    cdbGlobal.formIndexing($('#certificateTbl').find('tbody'));
    cdbGlobal.formIndexing($('#partnerDtls').find('tbody'));
    cdbGlobal.formIndexing($('#eqdatatable').find('tbody'));
    cdbGlobal.formIndexing($('#hrDtlsTable').find('tbody'));
    $("#addHRModal").find(":input").prop('disabled',true);
    $("#eqModal").find(":input").prop('disabled',true);

    var itemId = [];
    $.each($("input[name='itemId']:checked"), function(){
        itemId = ($(this).val());
        // alert(itemId);
    });

    var consultantForm = $("#consultantForm");
    var url=cdbGlobal.baseURL() + "/consultantNR/save?itemId="+itemId;
    var options = {
        target:'#registrtaionFormCard',
        url:url,
        type:'POST',
        enctype: 'multipart/form-data',
        data: consultantForm.serialize()
    };
    consultantForm.ajaxSubmit(options);
    $('#confirmationModel').modal('hide');
}

function enableSubmit(){
        if($('#submitcheckbox').prop('checked')){
            $('#btnSubmit').prop('disabled',false);
        }
        else{
            $('#btnSubmit').prop('disabled',true);
        }
    }
//endregion
var consultant = (function () {
    "use strict";
    //var formID = "#commissionForm";
    var isSubmitted = false;

    function _baseURL() {
        return cdbGlobal.baseURL() + "/consultantNR";
    }

    function validate() {
        validate_gInfo();
        validate_cc();
        validate_hr();
        validate_eq();
    }

    function validate_gInfo() {
        $('#btnValGINext').on('click', function (e) {
            var isValid = true;
            $('#general_Information').find(':input').each(function () {
                if (isValid == true) {
                    isValid = $('#consultantForm').validate().element(this);
                } else {
                    $('#consultantForm').validate().element(this);
                }
            });
            if (isValid == true) {
                nextTab('general_Information', 'category_details')
            }
        });
    }

    function validate_cc() {
        $('#btnValCCNext').on('click', function (e) {
            var isValid = true;
            $('#consultantCCTbl').find(':input').each(function () {
                if (isValid == true) {
                    isValid = $('#consultantForm').validate().element(this);
                } else {
                    $('#consultantForm').validate().element(this);
                }
            });
            if($('.categoryCheck').is(':checked') == false){
                warningMsg("Please choose at least one category.");
                isValid = false;
            }
            if($('.appliedClassID').is(':checked') == false){
                warningMsg("Please choose at least one category.");
                isValid = false;
            }
            if (isValid == true) {
                    nextTab('category_details', 'human_resource_criteria')
            }
           return isValid;
        });
    }

    function enableClassCategory(){
        $('.categoryCheck').on('click',function(e){
            if($(this).is(':checked')){
                $(this).closest('tr').find('.appliedClassID').prop('disabled',false).prop('required',true);
            }else{
                $(this).closest('tr').find('.appliedClassID').val('').prop('disabled',true).prop('required',false);
            }
        });
    }

    function validate_hr() {
        $('#btnValHRNext').on('click', function (e) {
            var isValid = true;
            var hrAdded = false;
            $('#hrDtlsTable').find(':input').each(function () {
                hrAdded = true;
                if (isValid == true) {
                    isValid = $('#consultantForm').validate().element(this);
                } else {
                    $('#consultantForm').validate().element(this);
                }

            });
            if(hrAdded == false && $('.appliedClassID').val() != 'ef832830-c3ea-11e4-af9f-080027dcfac6'){ // only small category no need of HR
                isValid = false;
                warningMsg("Add at least one HR personal.")
            }
            if (isValid == true) {
                nextTab('human_resource_criteria', 'equipment_details')
            }
        });
    }

    function validate_eq() {
        $('#btnValEqNext').on('click', function (e) {
            var isValid = true;
            $('#eqdatatable').find(':input').each(function () {
                if (isValid == true) {
                    isValid = $('#consultantForm').validate().element(this);
                } else {
                    $('#consultantForm').validate().element(this);
                }

            });
            if (isValid == true) {
                saveAndPreview('equipment_details', 'saveAndPreview')
            }
        });
    }

    function getGewogList() {
        $('#pDzongkhagId').on('change', function (e) {
            $.ajax({
                url: _baseURL() + '/getGewogList',
                type: 'GET',
                async:false,
                data: {dzongkhagId: $(this).val()},
                success: function (res) {
                    if (res.status == '1') {
                        cdbGlobal.loadDropDown($('#pGewogId'), res.dto);
                    }
                }
            });
        });
    }

    function getVillageList() {
        $('#pGewogId').on('change', function (e) {
            $.ajax({
                url: _baseURL() + '/getVillageList',
                type: 'GET',
                async:false,
                data: {gewogId:  $(this).children(":selected").attr("id")},
                success: function (res) {
                    if (res.status == '1') {
                        cdbGlobal.loadDropDown($('#pVillageId'), res.dto);
                    }
                }
            });
        })
    }

    function showFileSize() {
        $('tbody').on('change', '.file', function () {
            //this.files[0].size gets the size of your file.
            var exactSize = calFileSize(this);
            $(this).closest('tr').find('.file-size').text(exactSize);
            //alert(this.files[0].size);
        });
    }

    function calFileSize($this){
        var _size = $this.files[0].size;
        var fSExt = ['Bytes', 'KB', 'MB', 'GB'],
            i = 0;
        while (_size > 900) {
            _size /= 1024;
            i++;
        }
        return (Math.round(_size * 100) / 100) + ' ' + fSExt[i];
    }

    function getTrainingDtl(cidNo){
        if(!cidNo){
            return;
        }
        $.ajax({
            url: _baseURL() + '/getTrainingDtl',
            type: 'GET',
            data: {cidNo: cidNo},
            success: function (res) {
                var trainingTbl = $('#inductionCourseDtl');
                var tr = '';
                for(var i in res){
                    tr = tr + "<tr><td>"+(parseInt(i)+1)+"</td>" +
                    "<td>" + res[i].tType + "</td>" +
                    "<td>" + (formatAsDate(res[i].fromDate) + ' to '+ formatAsDate(res[i].toDate) ) + "</td>" +
                    "<td>"+res[i].tModule+"</td>" +
                    "<td>"+res[i].participant+"</td>"+
                    "<td>"+res[i].cidNo+"</td></tr>";
                }
                trainingTbl.find('tbody').html(tr);
            }
        });
    }

    var cert = "<tr><td></td>" +
        "<td><input type='text' class='form-control' name='cAttachments[0].documentName'/> </td>"+
        "<td><input type='file' name='cAttachments[0].attachment' class='form-control-file file' accept='application/msword,application/pdf,application/vnd.ms-excel,image/gif, image/jpeg, image/jpg,application/vnd.openxmlformats-officedocument.wordprocessingml.document'></td>" +
        "<td class='file-size'></td>" +
        "<td><a class='p-2 del_row'><i class='fa fa-trash text-danger'></i></a></td>" +
        "</tr>";

    function sCertIncorporation() {
        $('#ownershipList').on('change', function (e) {
            var option = $(this).find("option:selected").html();
            var certificateTbl = $('#certificateTbl').find('tbody');
            if (~option.indexOf("Incorporated")) {
                $('#cIncorporation').removeClass('hide');
                certificateTbl.html(cert);
                $('#siCertificate').prop('checked', false);
                $('#addMorebtn').prop('disabled',false);
            }else{
                $('#cIncorporation').addClass('hide');
                certificateTbl.empty();
                $('#siCertificate').prop('checked', true);
                $('#addMorebtn').prop('disabled',true);
            }
        });
    }

    function addMoreCert(){
        $('#addMoreCert').on('click',function(e){
            var certificateTbl = $('#certificateTbl').find('tbody').append(cert);
            /*var row = certificateTbl.find('tr:eq(0)').html();
             certificateTbl.append('<tr>'+row.find(':input').val('')+'</tr>');*/
        });
    }

 /*   function enableRegistrationNo(){
        $('.equipmentId').on('change',function(e){
            alert('inside enable eq');
            var isRegistration = $(this).find("option:selected").hasClass("1");
            if(isRegistration == true){
               $('#eq2').prop('disabled',false).prop('required',true);
                $('#eq3').val(1).prop('disabled',true);
            } else{
                $('#eq2').val('').prop('disabled',true);
                $('#eq3').val('').prop('disabled',false);
            }
        })
    }*/



    function getPersonalInfo(){
        $('#partnerDtls').on('change','.hr-cid', function (e) {
            var $this = $(this);
            var country = $this.closest('tr').find('.country #countryList').val();
            if(country == '8f897032-c6e6-11e4-b574-080027dcfac6') { //if bhutanese fetch from DCRC
                $.ajax({
                    url: _baseURL() + '/getPersonalInfo',
                    type: 'GET',
                    data: {cidNo: $this.val(),type:"fetch"},
                    success: function (res) {
                        if (res.status == '1') {
                            var dto = res.dto;
                          // var index = $this.closest("tr").index();
                            $this.closest('tr').find('.name').val(dto.fullName).prop('readonly', true);
                            $this.closest('tr').find('.sex').val(dto.sex).prop('readonly', true);
                        }
                        else{
                            warningMsg(res.text);
                            $this.closest('tr').find('.name').prop('readonly', false);
                            $this.closest('tr').find('.sex').prop('readonly', false);
                        }
                    }
                });
            }
            getTrainingDtl($this.val());
        })
    }

    function getPersonalInfoHR(){
        $('#addHRModal').on('change','.hr-cid', function (e) {
            var $this = $(this);
            var country = $('#hr5').val();
            if(country == '8f897032-c6e6-11e4-b574-080027dcfac6') { //if bhutanese fetch from DCRC
                $.ajax({
                    url: _baseURL() + '/getPersonalInfo',
                    type: 'GET',
                    data: {cidNo: $this.val(),type:"fetch"},
                    success: function (res) {
                        if (res.status == '1') {
                            var dto = res.dto;
                            // var index = $this.closest("tr").index();
                            $('#hr2').val(dto.fullName).prop('readonly', true);
                            $('#hr4').val(dto.sex).prop('readonly', true);
                            $('#hr11').val(dto.cdbNo).prop('readonly', true);
                        }
                    }
                });
            }
        })
    }

    function isFirmNameUnique(){
        $('#firmName').on('change',function(){
            var $this = $(this);
            $.ajax({
                url: _baseURL() + '/isFirmNameUnique',
                type: 'GET',
                data: {firmName: $this.val()},
                success: function (res) {
                    if(res == true){
                        //$this.val()
                    }else{
                        $this.val('').focus();
                        warningMsg("This firm name has been already taken. Please choose another name.");
                        $this.val('').focus();
                    }
                }
            });
        });
    }

    function isEmailUnique(){
        $('#regEmail').on('change',function(){
            var $this = $('#regEmail');
            $.ajax({
                url: _baseURL() + '/isEmailUnique',
                type: 'GET',
                data: {email: $this.val()},
                success: function (res) {
                    if(res == true){
                       $this.val()
                    }else{
                      $this.val('').focus();
                        warningMsg("This email has already been registered.");
                       $this.val('').focus();
                    }
                }
            });
        });
    }
    function addMoreFile(){
        $('.hrFile').on('click',function(e){
            var uplTbl = $('#hrUploadTbl').find('tbody');
            var tr = "<tr><td><input type='text' class='form-control docName' name='consultantHRs[0].consultantHRAs[0].documentName'/> </td>" +
                "<td><input type='file' class='file' name='consultantHRs[0].consultantHRAs[0].attachment' data-preview-file-type='any'/> </td><td class='file-size'></td>" +
                "<td class='del_row'> <a class='p-2'><i class='fa fa-trash text-danger '></i></a></td> </td></tr>";
            uplTbl.append(tr);
        });
    }

    function delTableRow(){
        $('body').on('click','.del_row',function(){
            if($(this).closest('table').find('tbody tr').length > 1) {
                $(this).closest('tr').remove();
            } else{
                warningMsg("Cannot delete last row. You must have at least one row!");
            }
        });
    }

    function edit_HR(){
        $('body').on('click','.edit-hr',function(e){
            e.preventDefault();
            var row = $(this).closest('tr');
            var hrModal = $('#addHRModal');
            hrModal.find('#hr5').val(row.find('.hr5').val());
            hrModal.find('#hr3').val(row.find('.hr3').val());
            hrModal.find('#hr1').val(row.find('.hr1').val());
            hrModal.find('#hr2').val(row.find('.hr2').val());
            hrModal.find('#hr4').val(row.find('.hr4').val());
            hrModal.find('#hr6').val(row.find('.hr6').val());
            hrModal.find('#hr7').val(row.find('.hr7').val());
            hrModal.find('#hr8').val(row.find('.hr8').val());
            hrModal.find('#hr9').val(row.find('.hr9').val());
            hrModal.find('#hr10').val(row.find('.hr10').val());
            row.addClass('tbd'); //add class to be deleted
            openModal('addHRModal');
        });
    }

    function edit_EQ(){
        $('body').on('click','.edit-eq',function(e){
            e.preventDefault();
            var row = $(this).closest('tr');
            var hrModal = $('#eqModal');
            hrModal.find('#eq1').val(row.find('.eq1').val());
            hrModal.find('#eq2').val(row.find('.eq2').val());
            hrModal.find('#eq3').val(row.find('.eq3').val());
            row.addClass('tbd'); //add class to be deleted
            openModal('eqModal');
        });
    }

    function confirmEmail(){
        $('#confirmEmail').on('blur',function(e){
            if(!$(this)){
                return;
            }
            if($(this).val() != $('#regEmail').val()){
               $(this).focus().val('');
                warningMsg("Confirmation email does not match.");
                $(this).focus().val('');
            }
        })
    }

    function checkDuplicateHR(){
        $('body').on('focusin', '.hr-cid', function(){
            //console.log("Saving value " + $(this).val());
            $(this).data('val', $(this).val());
        }).on('change','.hr-cid',function(){
            var $this = $(this);
            var isHrExist = false;

            var designation = $('#designation').val();

            var country = '';
            var hrOrPartner = $this.closest('table').attr('id');
            if(hrOrPartner == 'partnerDtls'){
                hrOrPartner = 'O';
                country = $this.closest('tr').find('.country #countryList').val();
            }else{
                hrOrPartner = 'H';
                country = $('#hr5').val();
            }

            $('#partnerDtls').find('.ownerCidNo').each(function(e){
                if(hrOrPartner == 'H' && $this.val() == $(this).val() && designation !='030aacf0-24af-11e6-967f-9c2a70cc8e06'){
                    warningMsg("This CID is already added your Owner/Partner list!!!");
                    $this.val('');
                    isHrExist = true;
                    return false;
                }
            });
            $('#hrDtlsTable').find('tbody tr td:nth-child(3)').each(function(){
                if($this.val() == $(this).text()){
                    warningMsg("This CID is already exists in your HR list!!!");
                    $this.val('');
                    isHrExist = true;
                    return false;
                }
            });
            if(!isHrExist){
                getPersonalInfo($this,country,hrOrPartner);
                //getTrainingDtl($this);
            }
        })
    }

    function init() {
        //save();
        getGewogList();
        getVillageList();
        showFileSize();
        validate();
        sCertIncorporation();
        addMoreCert();
        enableRegistrationNo();
        enableClassCategory();
        getPersonalInfo();
        getPersonalInfoHR();
        isEmailUnique();
        isFirmNameUnique();
        addMoreFile();
        delTableRow();
        confirmEmail();
        checkDuplicateHR();
        edit_HR();
        edit_EQ();
    }

    return {
        init: init
    };
})();

$(document).ready(function () {
        consultant.init();
    }
);