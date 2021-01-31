/**
 * Created by user on 2/14/2020.
 */
//region functions callable from jsp

function addRow(tableId) {
    var $tableBody = $('#' + tableId).find("tbody");
    var isValid = true;
    $tableBody.find(':input').each(function () {
        if (isValid == true) {
            isValid = $('#consultantOSForm').validate().element(this);
        } else {
            $('#consultantOSForm').validate().element(this);
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
function showAcknowledgement() {
    $("#registrtaionFormCard").hide();
    $("#acknowledgementCard").show();
}

function saveAndPreview(presentClass, nextClass) {
    var content = '<h3 class="pt-3 text-center">Fee Structure</h3>' + $(".feesStructure > .div-actual").html() +
        '<h3 class="pt-3 text-center">General Information</h3>' + $(".generalInformation > .div-actual").html() +

        $("." + presentClass + ">a").addClass('bg-blue text-white');
        $('.tab-pane').removeClass("active").addClass("active");
        $('.tab-content').removeClass("active").addClass("active");
        $("." + nextClass).addClass("active");
        $("." + presentClass + ">a").append("<i class='fa fa-check ml-1'></i>");

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

        $('#btn1').hide();
        $('#btn2').hide();
        $('#btn3').hide();
        $('#btn4').hide();
        $('#btnValCCNext').hide();
        $('#btnValHRNext').hide();
        $('#btn5').hide();
        $('#btnValEqNext').hide();
        $('#btn6').hide();
        $('#btn7').hide();
        $('#btn8').hide();
        window.scroll(0, 0);
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

function openModal(modalId) {
    $("#" + modalId).modal({backdrop: 'static', keyboard: false});
   // $("#" + modalId).modal('show');
}

//region model
var hr_modal = $("#hrModal").html();
var eq_modal = $("#eqModal").html();
var ow_modal = $("#ownerModal").html();
var j= 0;
var bodyId = $('#hrDtlsTableId > tr').length;

function getModalData(tableId, prefix, totalCol) {
    $('#'+tableId).find('.tbd').remove();
    var td = "";
    $('#modalForm').validate();
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

    td = td + "<td ><input type='checkbox' name='contractorHRs[0].deleteRequest' value='1'></td>";

    var tr = "<tr id='"+j+"'>" + td + "<td class=' '><a class='p-2 edit-"+prefix+"'><i class='fa fa-pencil-square-o' aria-hidden='true'></i></a></td></tr>";

    $("#" + tableId).append(tr).find(".noRecord").hide();
    if(prefix == 'hr'){
        cloneHrFiles(tableId,modal,j);
    }
    if(prefix == 'eq'){
        cloneEqFiles(tableId,modal,j);
    }
    j= j+1;
    bodyId ++;
}

function getOwnerModalData(tableId, prefix, totalCol) {
    $('#'+tableId).find('.tbd').remove();
    var td = "";
    $('#modalForm').validate();
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
    td = td + "<td ><input type='checkbox' name='consultantHRs[0].siCertificate' value='1'></td>";

    td = td + "<td ><input type='checkbox' name='consultantHRs[0].deleteRequest' value='1'></td>";

    var tr = "<tr id='"+j+"'>" + td + "<td class=' '><a class='p-2 edit-"+prefix+"'><i class='fa fa-pencil-square-o' aria-hidden='true'></i></a></td></tr>";

    $("#" + tableId).append(tr).find(".noRecord").hide();

    j= j+1;
    bodyId ++;

    modal.modal('hide');
    $("#ownerModal").empty().html(ow_modal);
}

function cloneHrFiles(tableId,modal,i){
    var hrId = "<input type='hidden' name='"+$('#addHRModal').find('#hrId').attr('name')+"' value='"+$('#addHRModal').find('#hrId').val()+"'>";
    var uplTbl = $('#hrUploadTbl').find('tbody');
    var docName = '';
    uplTbl.find('.file').each(function(e){
        var index = $(this).closest('tr').index();
        $(this).attr('name', 'consultantHRs[0].consultantHRAs['+index+'].attachment');
    });

    uplTbl.find('.docName').each(function(e){
        var index = $(this).closest('tr').index();
        docName = docName +"<input type='hidden' name='consultantHRs[0].consultantHRAs["+index+"].id' value='"+$(this).closest('tr').find('.hraId').val()+"'/>";
        docName = docName +"<input type='hidden' name='consultantHRs[0].consultantHRAs["+index+"].documentName' value='"+$(this).val()+"'/><b>"+$(this).val() +'</b><br>';
    });

    var curTr = $("#" + tableId).find('#'+i);
    curTr.find('.doc').html(docName+''+hrId);

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

    modal.modal('hide');
    $("#eqModal").empty().html(eq_modal);
}
//endregion

function enableSubmit(){
    if($('#agreeCheck').prop('checked')){
        $('#btnSubmit').prop('disabled',false);
    }
    else{
        $('#btnSubmit').prop('disabled',true);
    }
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
    cdbGlobal.formIndexing($('#equipmentTbl').find('tbody'));
    cdbGlobal.formIndexing($('#hrDtlsTable').find('tbody'));
    cdbGlobal.formIndexing($('#certificateTblOwner').find('tbody'));
    cdbGlobal.formIndexing($('#certificateTblCategory').find('tbody'));
    $("#addHRModal").find(":input").prop('disabled',true);

    var consultantForm = $("#consultantOSForm");

    var appliedClassID = [];
    $.each($("input[name='appliedClassID']:checked"), function(){
        appliedClassID = ($(this).val());
    });
    var serviceCateID = [];
    $.each($("input[name='serviceCateID']:checked"), function(){
        serviceCateID = ($(this).val());
    });

    var url=_baseURL() + '/save';
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
function _baseURL() {
    return cdbGlobal.baseURL() + "/public_access/consultantOS";
}
//endregion
var consultantOS = (function () {
    "use strict";

    var cert = "<tr><td></td>" +
        "<td><input type='hidden' class='form-control aFor' name='cAttachments[0].attachmentFor' value='InSole'/>" +
        "<input type='text' class='form-control' name='cAttachments[0].documentName'/> </td>"+
        "<td><input type='file' name='cAttachments[0].attachment' class='form-control-file file' accept='application/msword,application/pdf,application/vnd.ms-excel,image/gif, image/jpeg, image/jpg,application/vnd.openxmlformats-officedocument.wordprocessingml.document'></td>" +
        "<td class='file-size'></td>" +
        "<td><a class='p-2 del_row'><i class='fa fa-trash text-danger'></i></a></td>" +
        "</tr>";

    function sCertIncorporation() {
        $('#ownershipList').on('change', function (e) {
            var option = $(this).find("option:selected").html();
            var certificateTbl = $('#certificateTbl').find('tbody');
            if (~option.indexOf("Incorporated") || ~option.indexOf("Sole Proprietorship")) {
                $('#cIncorporation').removeClass('hide');
                certificateTbl.html(cert);
            }else{
                $('#cIncorporation').addClass('hide');
                certificateTbl.empty();
            }
            if($('#ownershipList').val() != '1e243ef0-c652-11e4-b574-080027dcfac6'){
                getIncAttachmentFinal();
            }
        });
    }

    function sCertOwner() {
        var certificateTbl = $('#certificateTblOwner').find('tbody');
        $('#cOwnershipId').removeClass('hide');
        certificateTbl.append(cert);
    }

    function getHRsFinal(){
        $('#updateHR').on('click',function(){
            if($(this).is(':checked')){
                $('.human_resource_criteria').removeClass('hide');
                $.ajax({
                    url: _baseURL() + '/getConsultantHRsFinal',
                    type: 'GET',
                    data: {consultantId:$('#consultantIdFinal').val(),ownerOrHR:'H'},
                    success: function (res) {
                        var consultantHrs = res;
                        var hrTr = "";
                        //var openModal = "openModal('CheckModal')";
                        for (var i in consultantHrs) {
                            var attachments = '';
                            for (var j in consultantHrs[i].hrAttachments){
                                attachments = attachments + "<span class='hra'><input type='hidden' class='hraId' value='"+consultantHrs[i].hrAttachments[j].id+"'>" +
                                "<a href='"+_baseURL() + "/viewDownload?documentPath="+consultantHrs[i].hrAttachments[j].documentPath+"' target='_blank'>"+consultantHrs[i].hrAttachments[j].documentName+"</a></span><br>";
                            }
                            hrTr = hrTr + "<tr class=''>" +
                            "<td class='salutationName'><input type='hidden' class='consultantHRid' name='consultantHRs[0].id' value='"+consultantHrs[i].id +"'/>" +
                            "<input type='hidden' class='joiningDate' value='"+consultantHrs[i].joiningDate +"'/>"+
                            consultantHrs[i].salutationName + "</td>" +
                            "<td class='name'>" + consultantHrs[i].name + "</td>" +
                            "<td class='cidNo'>" + consultantHrs[i].cidNo + "</td>" +
                            "<td class='sex'>" + consultantHrs[i].sex + "</td>" +
                            "<td class='countryName'>" + consultantHrs[i].countryName + "</td>" +
                            "<td class='designationName'>" + consultantHrs[i].designationName + "</td>" +
                            "<td class='qualificationName'>" + consultantHrs[i].qualificationName + "</td>" +
                            "<td class='tradeName'>" + consultantHrs[i].tradeName + "</td>" +
                            "<td class='serviceTypeName'>" + consultantHrs[i].serviceTypeName + "</td>" +
                            "<td class='joiningDate'>" + consultantHrs[i].joinDate + "</td>" +
                            "<td class='attachments'>" + attachments + "</td>" +
                            "<td><input type='checkbox' name='consultantHRs[0].deleteRequest' value='1'></td>" +
                            "<td class='action'><button class='btn-sm btn-info btn-block edit-row'>Edit</button></td>" +
                            "</tr>";
                        }
                        $('#hrDtlsTable').find('tbody').append(hrTr);
                    }
                });
            }else{
                $('.human_resource_criteria').addClass('hide');
            }
        })
    }

    function delTableRow(){
        $('body').on('click','.del_row',function(e){
            e.preventDefault();
            if($(this).last() == true){
                warningMsg("Cannot delete last row. You must have atleast one document!");
            }else{
                $(this).closest('tr').remove();
            }
        });
    }

    function deleteRequest(id){
      /*  $('#hrDtlsTable').on('click',$(this),function(){
            alert('send delete request');
            var $this = $(this).find('.deleteRequest').prop('id');
            alert($this);
            $this.prop('checked',true);
            if($this.is(':checked')){
                alert('val 1');
                $('#deleteRequest').val(1);
            } else{
                $('#deleteRequest').val(0);
            }
        });*/
        alert(id);
        if('#'+id.is(':checked')){
            alert('val 1');
            $('#'+id).val(1);
        } else{
            $('#'+id).val(0);
        }
    }

    function addMoreCert(){
        $('#addMoreCert').on('click',function(e){
            var certificateTbl = $('#certificateTbl').find('tbody').append(cert);
            /*var row = certificateTbl.find('tr:eq(0)').html();
             certificateTbl.append('<tr>'+row.find(':input').val('')+'</tr>');*/
        });
    }

    function addMoreFile(){
        $('.hrFile').on('click',function(e){
            var uplTbl = $('#hrUploadTbl').find('tbody');
            var tr = "<tr><td><input type='text' class='form-control docName' name='consultantHRs[0].consultantHRAs[0].documentName'/> </td>" +
                "<td><input type='file' class='file' name='consultantHRs[0].consultantHRAs[0].attachment' data-preview-file-type='any'/> </td>" +
                "<td class='del_row'> <a class='p-2'><i class='fa fa-trash text-danger '></i></a></td> </td></tr>";
            uplTbl.append(tr);
        });
    }

    var certCategory ="<tr><td></td>" +
        "<td><input type='hidden' class='form-control aFor' name='categoryAttachments[0].attachmentFor' value='AL'/>" +
        "<input type='text' class='form-control' name='categoryAttachments[0].documentName'/> </td>"+
        "<td><input type='file' name='categoryAttachments[2].attachment' class='form-control-file file' accept='application/msword,application/pdf,application/vnd.ms-excel,image/gif, image/jpeg, image/jpg,application/vnd.openxmlformats-officedocument.wordprocessingml.document'></td>" +
        "<td class='file-size'></td>" +
        "<td><a class='p-2 del_row'><i class='fa fa-trash text-danger'></i></a></td>" +
        "</tr>";

    var certOSC = "<tr><td></td>" +
        "<td><input type='hidden' class='form-control aFor' name='ownerAttachments[0].attachmentFor' value='OC'/>" +
        "<input type='text' class='form-control' name='ownerAttachments[0].documentName'/> </td>"+
        "<td><input type='file' name='ownerAttachments[1].attachment' class='form-control-file file' accept='application/msword,application/pdf,application/vnd.ms-excel,image/gif, image/jpeg, image/jpg,application/vnd.openxmlformats-officedocument.wordprocessingml.document'></td>" +
        "<td class='file-size'></td>" +
        "<td><a class='p-2 del_row'><i class='fa fa-trash text-danger'></i></a></td>" +
        "</tr>";

    function addMoreCertCategory(){
        $('#addMoreCertCategory').on('click',function(e){
            var certificateTbl = $('#certificateTblCategory').find('tbody').append(certCategory);
            /*var row = certificateTbl.find('tr:eq(0)').html();
             certificateTbl.append('<tr>'+row.find(':input').val('')+'</tr>');*/
        });
    }

    function addMoreCertOwner(){
        $('#addMoreCertOwner').on('click',function(e){
            var certificateTbl = $('#certificateTblOwner').find('tbody').append(certOSC);
            /*var row = certificateTbl.find('tr:eq(0)').html();
             certificateTbl.append('<tr>'+row.find(':input').val('')+'</tr>');*/
        });
    }

    function service_check(){
        $('body').on('click','.service_check',function(){
            var id = $(this).prop('id');
            var $this = $(this);
            if(id == 'Incorporation' ){
                if($this.is(':checked')){
                    $('#ownershipList').prop('disabled',false);
                    $('#firmName').prop('disabled', false);
                    $('#changeOfFirmName').prop('disabled', true);
                    $('#ownerPartner').removeClass('hide');
                    $('#changeOfOwnerId').prop('disabled', true);
                    $('#ownerShipchangeId').removeClass('hide');
                    $('#deleteRequest').prop('disabled',false);
                    getOwnerFinal();
                }else{
                    $('#ownershipList').prop('disabled',true);
                    $('#firmName').prop('disabled', true);
                    $('#changeOfFirmName').prop('disabled', false);
                    $('#changeOfOwnerId').prop('disabled', false);
                    $('#ownerPartner').addClass('hide');
                    $('#ownerShipchangeId').addClass('hide');
                    $('#deleteRequest').prop('disabled',true);
                }
            }else if(id == 'changeOfFirmName' ){
                if($this.is(':checked')) {
                    $('#firmName').prop('disabled', false);
                }else{
                    $('#firmName').prop('disabled', true);
                }
            }else if(id == 'changeOfLocation' ){
                if($this.is(':checked')) {
                    $('#estAddress').prop('disabled', false);
                    $('#regDzongkhagId').prop('disabled', false);
                }else{
                    $('#estAddress').prop('disabled', false);
                    $('#regDzongkhagId').prop('disabled', false);
                }
            }else if(id == 'changeOfOwnerId' ){
                if($this.is(':checked')) {
                    $('#ownerPartner').removeClass('hide');
                    $('#ownerShipchangeId').removeClass('hide');
                    $('#deleteRequest').prop('disabled',false);
                    getOwnerFinal();
                   // sCertOwner();
                }else{
                    $('#ownerPartner').addClass('hide');
                    $('#cOwnershipId').addClass('hide');
                    $('#ownerShipchangeId').addClass('hide');
                    $('#deleteRequest').prop('disabled',true);
                }
            }
        });
    }

    function getConsultant() {
        if($('#consultantOSForm').length < 1){
            return;
        }
        $.ajax({
            url: _baseURL() + '/getConsultant',
            type: 'GET',
            success: function (res) {
                var consultant = res;
                $('#ownershipList').val(consultant.ownershipTypeId).prop('disabled',true);
                $('#pCountryId').val(consultant.pCountryId).prop('disabled',true);
                $('#tradeLicenseNo').val(consultant.tradeLicenseNo).prop('disabled',true);
                $('#firmName').val(consultant.firmName).prop('disabled',true);
                $('#tpn').val(consultant.tpn).prop('disabled',true);
                $('#pDzongkhagId').val(consultant.regDzongkhagName).prop('disabled',true);
                $('#pGewogId').val(consultant.pGewogId).prop('disabled',true);
                $('#pVillageId').val(consultant.pVillageId).prop('disabled',true);
                $('#estAddress').val(consultant.estAddress).prop('disabled',true);
                $('#regDzongkhagId').val(consultant.regDzongkhagId).prop('disabled',true);
                $('#regEmail').val(consultant.regEmail).prop('disabled',true);
                $('#regMobileNo').val(consultant.regMobileNo).prop('disabled',true);
                $('#regPhoneNo').val(consultant.regPhoneNo).prop('disabled',true);
                $('#regFaxNo').val(consultant.regFaxNo).prop('disabled',true);
                $('#consultantIdFinal').val(consultant.id);

                if(consultant.ownershipTypeId != '1e243ef0-c652-11e4-b574-080027dcfac6'){
                    getIncAttachmentFinal();
                }
            }
        });
    }
    function getIncAttachmentFinal(){
        $.ajax({
            url: _baseURL() + '/getIncAttachmentFinal',
            type: 'GET',
            data: {consultantIdFinal:$('#consultantIdFinal').val(),ownerOrHR:'O'},
            success: function (data) {
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
                            "<td>" +
                            "<input type='hidden' class='form-control aFor' name='cAttachments[0].attachmentFor' value='InSole'/>" +
                            "<input type='text' class='form-control docName' name='cAttachments[0].documentName' value='"+data[i].documentName+"'/ disabled></td>" +
                            "<td class='attachment'><a href='" + _baseURL() + "/viewDownload?documentPath=" + data[i].documentPath + "' target='_blank'> View </a></td>" +
                            "<td></td>" +
                            "<td class='action'><button class='btn-sm btn-info btn-block edit_row' >Edit</button>" +
                            "<button class='btn-sm btn-info btn-block del_row'>Delete</button></td>" +
                            "</tr>";
                        }
                        if(data[i].attachmentFor == 'AL'){
                            categoryTr = categoryTr + "<tr>" +
                            "<td></td>" +
                            "<td>" + data[i].documentName + "</td>" +
                            "<td><a href='" + _baseURL() + "/viewDownload?documentPath=" + data[i].documentPath + "' target='_blank'> View </a></td>" +
                            "<td></td>" +
                            "<td class='action'><button class='btn-sm btn-info btn-block edit_row' >Edit</button>" +
                            "<button class='btn-sm btn-info btn-block del_row'>Delete</button></td>" +
                            "</tr>";
                        }
                        if(data[i].attachmentFor == 'OC'){
                            ownerTr = ownerTr + "<tr>" +
                            "<td></td>" +
                            "<td>" + data[i].documentName + "</td>" +
                            "<td><a href='" + _baseURL() + "/viewDownload?documentPath=" + data[i].documentPath + "' target='_blank'> View </a></td>" +
                            "<td></td>" +
                            "<td class='action'><button class='btn-sm btn-info btn-block edit_row' >Edit</button>" +
                            "<button class='btn-sm btn-info btn-block del_row'>Delete</button></td>" +
                            "</tr>";
                        }
                    }
                    $('#certificateTbl').find('tbody').html(cIncTr);
                    $('#certificateTblOwner').find('tbody').html(ownerTr);
                    $('#certificateTblCategory').find('tbody').html(categoryTr);

                }else{
                    $('#cIncorporation').addClass('hide');
                }
                /*if(data){
                    $('#cIncorporation').removeClass('hide');
                    var tr = '';
                    for(var i in data){
                        tr = tr + "<tr>"+
                        "<td><input type='hidden' class='form-control aFor' name='cAttachments[0].attachmentFor' value='InSole'/></td>" +
                        "<td>"+data[i].documentName+"</td>"+
                        "<td><a href='"+_baseURL() + "/viewDownload?documentPath="+data[i].documentPath+"' target='_blank'> View </a></td>" +
                        "<td class='action'><button class='btn-sm btn-info btn-block edit_row'>Edit</button>" +
                        "<button class='btn-sm btn-info btn-block del_row'>Delete</button></td>" +
                        "</tr>";
                    }
                    $('#IncCertificateTbl').find('tbody').html(tr);
                }else{
                    $('#cIncorporation').addClass('hide');
                }*/
            }

        });
    }
    function editIncAttachment(){
        $('#certificateTbl').on('click','.edit_row',function(){
            $(this).closest('tr').find('.docName').prop('disabled',false);
            var attachment = $(this).closest('tr').find('.attachment');
            attachment.html("<input type='file' name='cAttachments[0].attachment' class='form-control-file file' accept='application/msword,application/pdf,application/vnd.ms-excel,image/gif, image/jpeg, image/jpg,application/vnd.openxmlformats-officedocument.wordprocessingml.document'>");

        })
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

    function getOwnerFinal(){
        var partnerHrTr = "";
        $('#btn3').on('click',function() {
            $.ajax({
                url: _baseURL() + '/getConsultantHRsFinal',
                type: 'GET',
                data: {consultantId: $('#consultantIdFinal').val(), ownerOrHR: 'O'},
                success: function (res) {
                    var consultantHrs = res;
                    for (var i in consultantHrs) {
                        partnerHrTr = partnerHrTr + "<tr>" +
                        "<td class='countryName'>" + consultantHrs[i].countryName + "</td>" +
                        "<td class='cidNo'>" + consultantHrs[i].cidNo + "</td>" +
                        "<td class='salutationName'>" + consultantHrs[i].salutationName + "</td>" +
                        "<td class='name'>" + consultantHrs[i].name + "</td>" +
                        "<td class='sex'>" + consultantHrs[i].sex + "</td>" +
                        "<td class='designationName'>" + consultantHrs[i].designationName + "</td>" +
                        "<td>" + ((consultantHrs[i].siCertificate == '1')?'(✔)':'') + "</td>"+
                        "<td><input type='checkbox' class='deleteRequest' id='deleteRequest' name='consultantHRs[0].deleteRequest' value='1'></td>"+
                        "<td class='action'><button class='btn-sm btn-info btn-block edit-rowOW'>Edit</button></td>" +
                        "</tr>";
                    }
                    $('#partnerDtls').find('tbody').html(partnerHrTr);
                }
            });
        })
    }

    function getOwnerFinalq(){
        $('#btn3').on('click',function() {
            $.ajax({
                url: _baseURL() + '/getConsultantHRsFinal',
                type: 'GET',
                data: {consultantId: $('#consultantIdFinal').val(), ownerOrHR: 'O'},
                success: function (res) {
                    var consultantHrs = res;
                    for (var i in consultantHrs) {
                        var tblRow = $('#partnerDtls').find('tbody tr:eq(' + (parseInt(i)) + ')');
                        tblRow.find('#countryList').val(tblRow.find('#countryList option:contains("' + consultantHrs[i].countryName + '")').val());
                        tblRow.find('.hr-cid').val(consultantHrs[i].cidNo);
                        tblRow.find('#salutation').val(tblRow.find('#salutation option:contains("' + consultantHrs[i].salutationName + '")').val());
                        tblRow.find('#designation').val(tblRow.find('#designation option:contains("' + consultantHrs[i].designationName + '")').val());
                        tblRow.find('.name').val(consultantHrs[i].name);
                        tblRow.find('#gender').val(consultantHrs[i].sex);
                        if (consultantHrs[i].siCertificate == '1') {
                            tblRow.find('.showCert').prop('checked', true);
                        } else {
                            tblRow.find('.showCert').prop('checked', false);
                        }

                    }
                }
            });
        })
    }

    function getEQsFinal(){
        $('#updateEq').on('click',function(){
            if($(this).is(':checked')){
                $('.equipment_details').removeClass('hide');
                $.ajax({
                    url: _baseURL() + '/getEquipmentFinal',
                    type: 'GET',
                    data: {consultantId:$('#consultantIdFinal').val()},
                    success: function (res) {
                        var equipments = res;
                        var eqTr = "";
                        for (var i in equipments) {
                            var attachment = '';
                            for (var j in equipments[i].eqAttachments){
                                attachment = attachment + "<a href='"+_baseURL() + "/viewDownload?documentPath="+equipments[i].eqAttachments[j].documentPath+"' target='_blank'>"+equipments[i].eqAttachments[j].documentName+"</a><br>";
                            }
                            eqTr = eqTr +
                            "<tr>" +
                            "<td><input type='hidden' class='consultantEQid' name='consultantEQs[0].id' value='"+equipments[i].id +"'/>"
                            + equipments[i].equipmentName + "</td>" +
                            "<td>" + equipments[i].registrationNo + "</td>" +
                            "<td>" + equipments[i].quantity + "</td>" +
                            "<td>" + attachment + "</td>" +
                            "<td><input type='checkbox' name='equipments[0].deleteRequest' value='1'></td>" +
                            "<td class='action'><input type='checkbox' class='editCheck' name='equipments[0].editCheck' value=''><button class='btn-sm btn-info btn-block edit_row_eq'>Edit</button></td>"  +
                            "</tr>";
                        }
                        $('#equipmentTbl').find('tbody').html(eqTr);
                    }
                });
            }else{
                $('.equipment_details').addClass('hide');
            }
            //positionTab('equipment_details');
        })
    }

    function addMoreEqFile(){
        $('#addMoreEq').on('click',function(e){
            var uplTbl = $('#eqUploadTbl').find('tbody');
            var tr = "<tr><td><input type='text' required class='form-control docName' name='equipments[0].consultantEQAs[0].documentName'/> </td>" +
                "<td><input type='file' required class='file' name='equipments[0].consultantEQAs[0].attachment' accept='application/msword,application/pdf,application/vnd.ms-excel,image/gif, image/jpeg, image/jpg,application/vnd.openxmlformats-officedocument.wordprocessingml.document'/> </td><td class='file-size'></td>" +
                "<td class='del_row'> <a class='p-2'><i class='fa fa-trash text-danger '></i></a></td></tr>";
            uplTbl.append(tr);
        });
    }

    function getClassCategory(){
        $('#upgradeDowngrade').on('click',function() {
            if ($(this).is(':checked')) {
                $('.category_details').removeClass('hide');
                $.ajax({
                    url: _baseURL() + '/getCategory',
                    type: 'GET',
                    data: {consultantId:$('#consultantIdFinal').val()},
                    success: function (res) {

                        var categories = res;
                        for (var i in categories) {
                            if (categories[i].categoryId == "e6372584-bc15-11e4-81ac-080027dcfac6") {
                                $('#cateId0').prop('checked', true).prop('disabled',false);
                            }
                            if (categories[i].categoryId == "f39b9245-bc15-11e4-81ac-080027dcfac6") {
                                $('#cateId01').prop('checked', true).prop('disabled',false);
                            }
                            if (categories[i].categoryId == "fb2aa1a7-bc15-11e4-81ac-080027dcfac6") {
                                $('#cateId2').prop('checked', true).prop('disabled',false);
                            }
                            if (categories[i].categoryId == "2adfae00-be66-11e9-9ac2-0026b988eaa8") {
                                $('#cateId3').prop('checked', true).prop('disabled',false);
                            }


                            if (categories[i].apClassId == "2dc059a3-bc17-11e4-81ac-080027dcfac6") {
                                $('#asone').prop('checked', true).prop('disabled',false);
                            }
                            if (categories[i].apClassId == "378c8114-bc17-11e4-81ac-080027dcfac6") {
                                $('#astwo').prop('checked', true).prop('disabled',false);
                            }
                            if (categories[i].apClassId == "42914a22-bc17-11e4-81ac-080027dcfac6") {
                                $('#asthree').prop('checked', true).prop('disabled',false);
                            }

                            if (categories[i].apClassId == "51f58a70-bc17-11e4-81ac-080027dcfac6") {
                                $('#cvsone').prop('checked', true).prop('disabled',false);
                            }
                            if (categories[i].apClassId == "5b147a4d-bc17-11e4-81ac-080027dcfac6") {
                                $('#cvstwo').prop('checked', true).prop('disabled',false);
                            }
                            if (categories[i].apClassId == "6516bfdd-bc17-11e4-81ac-080027dcfac6") {
                                $('#cvsthree').prop('checked', true).prop('disabled',false);
                            }
                            if (categories[i].apClassId == "7b84fd72-bc17-11e4-81ac-080027dcfac6") {
                                $('#cvsfour').prop('checked', true).prop('disabled',false);
                            }
                            if (categories[i].apClassId == "a8ee79e6-bc17-11e4-81ac-080027dcfac6") {
                                $('#cvsfive').prop('checked', true).prop('disabled',false);
                            }
                            if (categories[i].apClassId == "be34bd47-bc17-11e4-81ac-080027dcfac6") {
                                $('#cvssix').prop('checked', true).prop('disabled',false);
                            }
                            if (categories[i].apClassId == "cc3bfc36-bc17-11e4-81ac-080027dcfac6") {
                                $('#cvsseven').prop('checked', true).prop('disabled',false);
                            }

                            if (categories[i].apClassId == "ded7b309-bc17-11e4-81ac-080027dcfac6") {
                                $('#eesone').prop('checked', true).prop('disabled',false);
                            }
                            if (categories[i].apClassId == "ef1e617f-bc17-11e4-81ac-080027dcfac6") {
                                $('#eestwo').prop('checked', true).prop('disabled',false);
                            }
                            if (categories[i].apClassId == "1a4e9b6f-bc18-11e4-81ac-080027dcfac6") {
                                $('#eesthree').prop('checked', true).prop('disabled',false);
                            }
                            if (categories[i].apClassId == "271c4483-bc18-11e4-81ac-080027dcfac6") {
                                $('#eesfour').prop('checked', true).prop('disabled',false);
                            }
                            if (categories[i].apClassId == "30a3dd3c-bc18-11e4-81ac-080027dcfac6") {
                                $('#eesfive').prop('checked', true).prop('disabled',false);
                            }
                            if (categories[i].apClassId == "3ceb09ba-bc18-11e4-81ac-080027dcfac6") {
                                $('#eessix').prop('checked', true).prop('disabled',false);
                            }
                            if (categories[i].apClassId == "4461b1b0-bc18-11e4-81ac-080027dcfac6") {
                                $('#eesseven').prop('checked', true).prop('disabled',false);
                            }

                            if (categories[i].apClassId == "8a6ea970-be66-11e9-9ac2-0026b988eaa8") {
                                $('#sone').prop('checked', true).prop('disabled',false);
                            }
                            if (categories[i].apClassId == "b20d9185-be66-11e9-9ac2-0026b988eaa8") {
                                $('#stwo').prop('checked', true).prop('disabled',false);
                            }
                            if (categories[i].apClassId == "fb9e92cb-be66-11e9-9ac2-0026b988eaa8") {
                                $('#sthree').prop('checked', true).prop('disabled',false);
                            }
                            if (categories[i].apClassId == "1129c568-be67-11e9-9ac2-0026b988eaa8") {
                                $('#sfour').prop('checked', true).prop('disabled',false);
                            }
                            if (categories[i].apClassId == "3aba7cc5-be67-11e9-9ac2-0026b988eaa8") {
                                $('#sfive').prop('checked', true).prop('disabled',false);
                            }
                            if (categories[i].apClassId == "5fa269a3-be67-11e9-9ac2-0026b988eaa8") {
                                $('#ssix').prop('checked', true).prop('disabled',false);
                            }
                            if (categories[i].apClassId == "4cd73d78-be67-11e9-9ac2-0026b988eaa8") {
                                $('#sseven').prop('checked', true).prop('disabled',false);
                            }
                        }
                    }
                });
            } else{
                $('.category_details').addClass('hide');
            }
            //positionTab('category_details');
        });
    }

    function enableClassCategory(){
        $('.categoryCheck').on('click',function(e){
            if($(this).is(':checked')){
                $(this).closest('tr').find('.appliedClassID').prop('disabled',false).prop('required',true);
            }else{
                $(this).closest('tr').find('.appliedClassID').prop('disabled',true).prop('required',false);
            }
        });
    }

    function editInModalOwner(){
        $('body').on('click','.edit-rowOW',function(e){
            e.preventDefault();
            var row = $(this).closest('tr');
            var hrModal = $('#addOwModal');
            hrModal.find('#hrId').val(row.find('.consultantHRid').val())//for Edit
            hrModal.find('#ow1').val(hrModal.find('#ow1 option:contains("'+row.find('.countryName').text()+'")').val());
            hrModal.find('#ow2').val(row.find('.cidNo').text());
            hrModal.find('#ow3').val(hrModal.find('#ow3 option:contains("'+row.find('.salutationName').text()+'")').val());
            hrModal.find('#ow4').val(row.find('.name').text());
            hrModal.find('#ow5').val(row.find('.sex').text());
            hrModal.find('#ow6').val(hrModal.find('#ow6 option:contains("'+row.find('.designationName').text()+'")').val());
            var hraTr = "";
            //row.remove();
            row.addClass('tbd'); //add class to be deleted
            openModal('addOwModal');
        });

        $('body').on('click','.edit-ow',function(e){
            e.preventDefault();
            var row = $(this).closest('tr');
            var hrModal = $('#addOwModal');
            hrModal.find('#ow1').val(row.find('.ow1').val());
            hrModal.find('#ow2').val(row.find('.ow2').val());
            hrModal.find('#ow3').val(row.find('.ow3').val());
            hrModal.find('#ow4').val(row.find('.ow4').val());
            hrModal.find('#ow5').val(row.find('.ow5').val());
            hrModal.find('#ow6').val(row.find('.ow6').val());
            row.addClass('tbd'); //add class to be deleted
            openModal('addOwModal');
        });
    }

    function editInModal(){
        $('body').on('click','.edit-row',function(e){
            e.preventDefault();
            var row = $(this).closest('tr');
            var hrModal = $('#addHRModal');
            hrModal.find('#hrId').val(row.find('.consultantHRid').val())//for Edit
            hrModal.find('#hr5').val(hrModal.find('#hr5 option:contains("'+row.find('.countryName').text()+'")').val());
            hrModal.find('#hr3').val(row.find('.cidNo').text());
            hrModal.find('#hr1').val(hrModal.find('#hr1 option:contains("'+row.find('.salutationName').text()+'")').val());
            hrModal.find('#hr2').val(row.find('.name').text());
            hrModal.find('#hr4').val(row.find('.sex').text());
            hrModal.find('#hr6').val(hrModal.find('#hr6 option:contains("'+row.find('.designationName').text()+'")').val());
            hrModal.find('#hr7').val(hrModal.find('#hr7 option:contains("'+row.find('.qualificationName').text()+'")').val());
            hrModal.find('#hr8').val(hrModal.find('#hr8 option:contains("'+row.find('.tradeName').text()+'")').val());
            hrModal.find('#hr9').val(hrModal.find('#hr9 option:contains("'+row.find('.serviceTypeName').text()+'")').val());
            hrModal.find('#hr10').val(row.find('.hr10').val());
            var hraTr = "";
            row.find('.hra').each(function(){
                var name = $(this).find('a').text();
                var hra = $(this).find('a').parent().html();
                hraTr = hraTr+"<tr><td><input type='hidden' class='hraId' value='"+$(this).find('.hraId').val()+"'>" +
                "<input type='text' required class='form-control docName' name='consultantHRs[0].consultantHRAs[0].documentName' value='"+name.substring(0,name.lastIndexOf('.'))+"' disabled></td>" +
                "<td><span class='aName'> "+hra+"</span><span class='aFile'></span> </td>" +
                    /*"<td></td>" +*/
                "<td><button class='change'>Change</button><button class='del_row'>Delete</button></td></tr>";
            });
            hrModal.find('#hrUploadTbl tbody').empty().html(hraTr);
           //row.remove();
            row.addClass('tbd'); //add class to be deleted
            openModal('addHRModal');
        });

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

    function editInModalEQ(){
        $('body').on('click','.edit_row_eq',function(e){
            var editcheck = $(this).closest('tr').find('.editCheck').prop('checked',true).val(1);
            e.preventDefault();
            var row = $(this).closest('tr');
            var modal = $('#eqModal');
            modal.find('.id4Edit').val(row.find('.consultantEQid').val());
            modal.find('#eq1').val(modal.find('#eq1 option:contains("'+row.find('td:nth-child(1)').text()+'")').val());
            modal.find('#eq2').val(row.find('td:nth-child(2)').text());
            modal.find('#eq3').val(row.find('td:nth-child(3)').text());
            var hraTr = "";
            row.find('.attachment').each(function(){
                var name = $(this).find('a').text();
                var hra = $(this).find('a').parent().html();
                hraTr = hraTr+"<tr><td><input type='hidden' class='eqId' value='"+$(this).find('.hraId').val()+"'>" +
                "<input type='text' required class='form-control docName' name='equipments[0].consultantEQAs[0].documentName' value='"+name.substring(0,name.lastIndexOf('.'))+"' disabled></td>" +
                "<td><span class='aName'> "+hra+"</span><span class='aFile'></span> </td>" +
                    /*"<td></td>" +*/
                "<td><button class='change'>Change</button><button class='del_row'>Delete</button></td></tr>";
            });
            modal.find('#eqUploadTbl tbody').empty().html(hraTr);
           // row.remove();
            row.addClass('tbd');
            openModal('eqModal');
        });

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

    function changeFile(){
        $('#hrUploadTbl').on('click','.change',function(e){
            //e.preventDefault();
            var $this = $(this);
            var row = $(this).closest('tr');
            var file= "<input type='file' required class='file' name='consultantHRs[0].consultantHRAs[0].attachment'"+
                "accept='application/msword,application/pdf,application/vnd.ms-excel,image/gif, image/jpeg, image/jpg,application/vnd.openxmlformats-officedocument.wordprocessingml.document'/>";
            if($this.text() == 'Change'){
                row.find('.docName').prop('disabled',false);
                row.find('.aName').addClass('hide');
                row.find('.aFile').html(file);
                $this.text('Cancel');
            }else{
                row.find('.docName').prop('disabled',true);
                row.find('.aName').removeClass('hide');
                row.find('.aFile').empty();
                $this.text('Change');
            }

        });
    }

    function changeFileEq(){
        $('#eqUploadTbl').on('click','.change',function(e){
            //e.preventDefault();
            var $this = $(this);
            var row = $(this).closest('tr');
            var file= "<input type='file' required class='file' name='equipments[0].consultantEQAs[0].attachment'"+
                "accept='application/msword,application/pdf,application/vnd.ms-excel,image/gif, image/jpeg, image/jpg,application/vnd.openxmlformats-officedocument.wordprocessingml.document'/>";
            if($this.text() == 'Change'){
                row.find('.docName').prop('disabled',false);
                row.find('.aName').addClass('hide');
                row.find('.aFile').html(file);
                $this.text('Cancel');
            }else{
                row.find('.docName').prop('disabled',true);
                row.find('.aName').removeClass('hide');
                row.find('.aFile').empty();
                $this.text('Change');
            }

        });
    }

    function allowEditHrEqExpired(){
        if($('#isExpired').val() == 'true'){
            $('#Incorporation').prop('disabled',true);
            $('#changeOfFirmName').prop('disabled',true);
            $('#changeOfLocation').prop('disabled',true);
            $('#changeOfOwnerId').prop('disabled',true);
            $('#upgradeDowngrade').prop('disabled',true);
            $('#err-expired-audit-memo').html('Your application is expired. You can only avail HR update and Equipment Update.');
        }
        var auditMemo = $('#auditMemo').val();
        if(auditMemo != ''){
            $('#Incorporation').prop('disabled',true);
            $('#changeOfFirmName').prop('disabled',true);
            $('#changeOfLocation').prop('disabled',true);
            $('#changeOfOwnerId').prop('disabled',true);
            $('#upgradeDowngrade').prop('disabled',true);
            $('#err-expired-audit-memo').html(auditMemo);
        }
    }

    function enableRegistrationNo(){
        $('.equipmentId ').on('change',function(e){
            var isRegistration = $(this).find("option:selected").hasClass("1");
           // alert(isRegistration);
            if(isRegistration === true){
                $('#eq2').prop('disabled',false);
                $('#eq3').val(1).prop('disabled',true);
            }else{
                $('#eq2').prop('disabled',true);
            }
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

    function getPersonalInfo(){
        $('#partnerDtls').on('change','.hr-cid', function (e) {
            var $this = $(this);
            var country = $this.closest('tr').find('.country #countryList').val();
            if(country == '8f897032-c6e6-11e4-b574-080027dcfac6') { //if bhutanese fetch from DCRC
                $.ajax({
                    url: cdbGlobal.baseURL() + '/consultantNR/getPersonalInfo',
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
                    url:cdbGlobal.baseURL() + '/consultantNR/getPersonalInfo',
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
                url:cdbGlobal.baseURL() + '/consultantNR/isFirmNameUnique',
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
    function init(){
        viewDownloadAttachment();
        getConsultant();
        sCertIncorporation();
        service_check();
        getHRsFinal();
        getEQsFinal();
        getClassCategory();
        enableClassCategory();
        delTableRow();
        editInModal();
        changeFile();
        changeFileEq();
        addMoreCert();
        addMoreCertOwner();
        addMoreCertCategory();
        allowEditHrEqExpired();
        editInModalEQ();
        checkDuplicateHR();
        addMoreEqFile();
        addMoreFile();
        getPersonalInfo();
        getPersonalInfoHR();
       // deleteRequest();
        showFileSize();
        enableRegistrationNo();
        editIncAttachment();
        editInModalOwner();
        isFirmNameUnique();
        getOwnerFinal();
    }
    return {
        init:init
    };
})();

$(document).ready(function () {
        consultantOS.init();
    }
);