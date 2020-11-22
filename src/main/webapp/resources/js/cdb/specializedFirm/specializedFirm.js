//region functions callable from jsp
function openModal(modalId) {
    $("#" + modalId).modal({backdrop: 'static', keyboard: false});
}
function addRow(tableId) {
    var $tableBody = $('#' + tableId).find("tbody");
    var isValid = true;
    $tableBody.find(':input').each(function () {
        if (isValid == true) {
            isValid = $('#specializedFirmForm').validate().element(this);
        } else {
            $('#specializedFirmForm').validate().element(this);
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
        warningMsg("Cannot delete last row. You must have atleast one row for information!");
    }
}

function saveAndPreview(presentClass, nextClass) {
    var content = '<h3 class="pt-3 text-center">Fee Structure</h3>' + $("#fees_structure >.div-actual").html() +
        '<h3 class="pt-3 text-center">General Information</h3>' + $("#general_Information >.div-actual").html() +
        '<h3 class="pt-3 text-center">Category Details</h3>' + $("#category_details >.div-actual").html()
        + '<h3 class="pt-3 text-center">Human Resource</h3>' + $("#human_resource_criteria >.div-actual").html() +
        '<h3 class="pt-3 text-center">Consultant Equipment Details</h3>' + $("#equipment_details >.div-actual").html();

    $("." + presentClass + ">a").addClass('bg-blue-light text-white');
    $('.tab-pane').removeClass("active").addClass("active");
    $('.tab-content').removeClass("active").addClass("active");
    $("." + nextClass).addClass("active");
    $("." + presentClass + ">a").append("<i class='fa fa-check ml-1'></i>");

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
   /* var joiningDate = $('#joiningDate');
    var jd = "<input type='hidden' name='"+joiningDate.prop('name')+"' value='"+joiningDate.val()+"'>";*/

    td = td + "<td ><span class='doc'></span> <div class='hidden hr_attachment'></div></td>";

    td = td + "<td >"+jd+"</td>";

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
        $(this).attr('name', 'spFirmHRs[0].spFirmHRAs['+index+'].attachment');
    });

    uplTbl.find('.docName').each(function(e){
        var index = $(this).closest('tr').index();
        docName = docName +"<input type='hidden' name='spFirmHRs[0].spFirmHRAs["+index+"].documentName' value='"+$(this).val()+"'/><b>"+$(this).val() +'</b><br>';
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
        $(this).attr('name', 'equipments[0].spFirmEQAs['+index+'].attachment');
    });

    uplTbl.find('.docName').each(function(e){
        var index = $(this).closest('tr').index();
        docName = docName +"<input type='hidden' name='equipments[0].spFirmEQAs["+index+"].documentName' value='"+$(this).val()+"'/><b>"+$(this).val() +'</b><br>';
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

    var specializedFirmForm = $("#specializedFirmForm");
    var url=cdbGlobal.baseURL() + "/specializedFirm/save";
    var options = {
        target:'#registrtaionFormCard',
        url:url,
        type:'POST',
        enctype: 'multipart/form-data',
        data: specializedFirmForm.serialize()
    };
    specializedFirmForm.ajaxSubmit(options);
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

var specializedFirm = (function () {
    "use strict";
    //var formID = "#commissionForm";
    var isSubmitted = false;

    function _baseURL() {
        return cdbGlobal.baseURL() + "/specializedFirm";
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
                    isValid = $('#specializedFirmForm').validate().element(this);
                } else {
                    $('#specializedFirmForm').validate().element(this);
                }
            });
            //var isValid = $('#contractorForm').validate().element('#gInfo :input');
            if (isValid == true) {
                nextTab('general_Information', 'category_details')
            }
        });
    }

    function validate_cc() {
        $('#btnValCCNext').on('click', function (e) {
            var isValid = true;
            $('#specializedFirmCCTbl').find(':input').each(function () {
                if (isValid == true) {
                    isValid = $('#specializedFirmForm').validate().element(this);
                } else {
                    $('#specializedFirmForm').validate().element(this);
                }

            });
            if($('.categoryCheck').is(':checked') == false){
                isValid = false;
                warningMsg("Please choose at least one category and class.");
            }
            //var isValid = $('#contractorForm').validate().element('#gInfo :input');
            if (isValid == true) {
                nextTab('category_details', 'human_resource_criteria')
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
                    isValid = $('#specializedFirmForm').validate().element(this);
                } else {
                    $('#specializedFirmForm').validate().element(this);
                }
            });
            if(hrAdded == false ){
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

            var eqAdded = false;
            var classIds = '';
            var classML = false;
            $('#specializedFirmCCTbl').find('.appliedClassID').each(function(){
                if($(this).val() == 'e19afe94-c3ea-11e4-af9f-080027dcfac6') { //large
                    classML = true;
                    return false;
                }
                if($(this).val() == '003f9a02-c3eb-11e4-af9f-080027dcfac6' ) {//medium
                    classML = true;
                    return false;
                }
            });
            $('#eqdatatable').find(':input').each(function () {
                eqAdded = true;
                if (isValid == true) {
                    isValid = $('#specializedFirmForm').validate().element(this);
                } else {
                    $('#specializedFirmForm').validate().element(this);
                }
            });
            if(eqAdded == false && classML){ // only small category no need of EQ
                isValid = false;
                warningMsg("Add at least one equipment details.")
            }
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
               //$('.afterNameMsg').html("For incorporation, please include 'Pvt Ltd' after the proposed firm name");
                $('#cIncorporation').removeClass('hide');
                certificateTbl.html(cert);
                $('#siCertificate').prop('checked', false);
            }else{
              //  $('.afterNameMsg').html("For Sole Proprietorship, please include 'Construction' or 'Builder' after the proposed firm name");
                $('#cIncorporation').addClass('hide');
                certificateTbl.empty();
                $('#siCertificate').prop('checked', true);
            }
            if(!$(this).val()){
                $('.afterNameMsg').empty();
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

    function enableRegistrationNo(){
        $('.equipmentId').on('change',function(e){
            var isRegistration = $(this).find("option:selected").hasClass("1");
            if(isRegistration === true){
                $(this).closest('.form-group').find('.registrationNo').prop('disabled',false).prop('required',true);;
            }else{
                $(this).closest('.form-group').find('.registrationNo').prop('disabled',true).prop('required',false).removeClass('error');
            }
        })
    }

    function enableClassCategory(){
        $('.categoryCheck').on('click',function(e){
            if($(this).is(':checked')){
                $(this).closest('tr').find('.appliedClassID').prop('disabled',false).prop('required',true);
            }else{
                $(this).closest('tr').find('.appliedClassID').val('')
                    .prop('disabled',true).prop('required',false).removeClass('error');
            }
        })
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

    function getPersonalInfo(){
        $('#partnerDtls').on('blur','.hr-cid', function () {
            var $this = $(this);
            var country = $this.closest('tr').find('.country #countryList').val();
            if(country == '8f897032-c6e6-11e4-b574-080027dcfac6') { //if bhutanese fetch from DCRC
                $.ajax({
                    url: _baseURL() + '/getPersonalInfo',
                    type: 'GET',
                    async:false,
                    data: {cidNo: $this.val()},
                    success: function (res) {
                        if (res.status == '1') {
                            var dto = res.dto;
                            // var index = $this.closest("tr").index();
                            $this.closest('tr').find('.name').val(dto.fullName).prop('readonly', true);
                            $this.closest('tr').find('.sex').val(dto.sex).prop('readonly', true);
                            if (parseInt(index) == 0) {
                                /* $('#pDzongkhagId').val(dto.dzongkhagId).change();
                                 $('#pGewogId').val(dto.gowegId).change();
                                 $('#pVillageId').val(dto.villageId);*/
                                $('#pDzongkhagId').val(dto.dzongkhagId);
                                $('#pGewogId').html("<option value='"+dto.goweg+"'>"+dto.goweg+"</option>");
                                $('#pVillageId').val("<option value='"+dto.village+"'>"+dto.village+"</option>");
                            }
                        }
                    }
                });
            }
            getTrainingDtl($this.val());
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
                        //$this.val()
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
        $('#addMoreHr').on('click',function(e){
            var uplTbl = $('#hrUploadTbl').find('tbody');
            var tr = "<tr><td><input type='text' required class='form-control docName' name='spFirmHRs[0].spFirmHRAs[0].documentName'/> </td>" +
                "<td><input type='file' required class='file' name='spFirmHRs[0].spFirmHRAs[0].attachment' accept='application/msword,application/pdf,application/vnd.ms-excel,image/gif, image/jpeg, image/jpg,application/vnd.openxmlformats-officedocument.wordprocessingml.document'/> </td><td class='file-size'></td>" +
                "<td class='del_row'> <a class='p-2'><i class='fa fa-trash text-danger '></i></a></td></tr>";
            uplTbl.append(tr);
        });
    }

    function addMoreEqFile(){
        $('#addMoreEq').on('click',function(e){
            var uplTbl = $('#eqUploadTbl').find('tbody');
            var tr = "<tr><td><input type='text' required class='form-control docName' name='equipments[0].spFirmEQAs[0].documentName'/> </td>" +
                "<td><input type='file' required class='file' name='equipments[0].spFirmEQAs[0].attachment' accept='application/msword,application/pdf,application/vnd.ms-excel,image/gif, image/jpeg, image/jpg,application/vnd.openxmlformats-officedocument.wordprocessingml.document'/> </td><td class='file-size'></td>" +
                "<td class='del_row'> <a class='p-2'><i class='fa fa-trash text-danger '></i></a></td></tr>";
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

    function getPersonalInfoHR(){
        $('#addHRModal').on('change','.hr-cid', function (e) {
            var $this = $(this);
            var country = $('#hr5').val();
            if(country == '8f897032-c6e6-11e4-b574-080027dcfac6') { //if bhutanese fetch from DCRC
                $.ajax({
                    url: _baseURL() + '/getPersonalInfo',
                    type: 'GET',
                    data: {cidNo: $this.val()},
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

    function confirmEmail(){
        $('#confirmEmail').on('blur',function(e){
            if(!$(this)){
                return;
            }
            if($(this).val() != $('#regEmail').val()){
              //  $(this).focus();
                warningMsg("Confirmation email does not match.");
              //  $(this).focus();
                return;
            }
        });
    }

    function validate_joiningDate(){
       /* var today = new Date(),
            day = today.getDate(),
            month = today.getMonth()+1,
            year = today.getFullYear();
        if((day >=1 && day <=9) && (month >=1 && month <=9)){
            today = year+'-'+'0'+month+'-'+'0'+day;
        }else if((day >=1 && day <=9)){
            today = year+'-'+month+'-'+'0'+day;
        }else if((month >=1 && month <=9)){
            today = year+'-'+'0'+month+'-'+day;
        }else{
            today = year+'-'+month+'-'+day;
        }
        $('#joiningDate').attr('min',today);*/

        $('#joiningDate').datepicker({
                maxDate: 0
        });
    }

    function checkDuplicateHR(){
        $('body').on('change','.hr-cid',function(){
            var $this = $(this);
            var isHrExist = false;

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
                if(hrOrPartner == 'H' && $this.val() == $(this).val()){
                    warningMsg("This CID is already added in your Owner/Partner list!!!");
                    $this.val('');
                    isHrExist = true;
                    return false;
                }
            });
            $('#hrDtlsTable').find('tbody tr td:nth-child(3)').each(function(){
                if($this.val() == $(this).text()){
                    warningMsg("This CID is already added in your HR list!!!");
                    $this.val('');
                    isHrExist = true;
                    return false;
                }
            });
            if(!isHrExist){
                getPersonalInfo($this,country,hrOrPartner);
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
        addMoreEqFile();
        delTableRow();
        confirmEmail();
        validate_joiningDate();
        checkDuplicateHR();
        edit_HR();
    }
    return {
        init: init
    };
})();


$(document).ready(function () {
        specializedFirm.init();
    }
);