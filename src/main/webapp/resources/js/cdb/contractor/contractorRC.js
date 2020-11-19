/**
 * Created by user on 2/14/2020.
 */
//region functions callable from jsp

function addRow(tableId) {
    var $tableBody = $('#' + tableId).find("tbody");
    var isValid = true;
    $tableBody.find(':input').each(function () {
        if (isValid == true) {
            isValid = $('#contractorRenewalForm').validate().element(this);
        } else {
            $('#contractorRenewalForm').validate().element(this);
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
}

//region model
var hr_modal = $("#hrModal").html();
var eq_modal = $("#eqModal").html();
var j= 0;
function getModalData(tableId, prefix, totalCol) {
    var td = "";
    var modal = $('#' + prefix + '1').closest('.modal');
    $('#modalForm').validate();
    if(modal.find(':text,:file,:checkbox,select').valid() == false){
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

        var tdVal = "<input type='hidden' name='" + name + "' value='" + value + "'/>" + text;
        td = td + "<td>" + tdVal + "</td>";
    }
    td = td + "<td ><span class='doc'></span> <div class='hidden hr_attachment'></div></td>";

    var tr = "<tr id='"+j+"'>" + td + "<td><button class='btn-sm btn-info btn-block edit_row'>Edit</button>" +
        "<button class='btn-sm btn-info btn-block del_row'>Delete</button></td></tr>";

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
    var hrId = "<input type='hidden' name='"+$('#addHRModal').find('#hrId').attr('name')+"' value='"+$('#addHRModal').find('#hrId').val()+"'>";
    var uplTbl = $('#hrUploadTbl').find('tbody');
    var docName = '';

    uplTbl.find('.file').each(function(e){
        var index = $(this).closest('tr').index();
        $(this).attr('name', 'contractorHRs[0].contractorHRAs['+index+'].attachment');
    });

    uplTbl.find('.docName').each(function(e){
        var index = $(this).closest('tr').index();
        docName = docName +"<input type='hidden' name='contractorHRs[0].contractorHRAs["+index+"].id' value='"+$(this).closest('tr').find('.hraId').val()+"'/>";
        docName = docName +"<input type='hidden' name='contractorHRs[0].contractorHRAs["+index+"].documentName' value='"+$(this).val()+"'/><b>"+$(this).val() +'</b><br>';
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
        $(this).attr('name', 'equipments[0].contractorEQAs['+index+'].attachment');
    });

    uplTbl.find('.docName').each(function(e){
        var index = $(this).closest('tr').index();
        docName = docName +"<input type='hidden' name='equipments[0].contractorEQAs["+index+"].documentName' value='"+$(this).val()+"'/><b>"+$(this).val() +'</b><br>';
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
    $("#addHRModal").find(":input").prop('disabled',true);

    var contractorForm = $("#contractorRenewalForm");
    var url=_baseURL() + "/save";
    var options = {
        target:'#registrtaionFormCard',
        url:url,
        type:'POST',
        enctype: 'multipart/form-data',
        data: contractorForm.serialize()
    };
    contractorForm.ajaxSubmit(options);
    $('#confirmationModel').modal('hide');
}
function _baseURL() {
    return cdbGlobal.baseURL() + "/public_access/contractorRC";
}

//endregion

var contractorRC = (function () {
    "use strict";
    var isSubmitted = false;

    var cert = "<tr><td></td>" +
        "<td><input type='text' class='form-control' name='cAttachments[0].documentName'/> </td>"+
        "<td><input type='file' name='cAttachments[0].attachment' class='form-control-file file' accept='application/msword,application/pdf,application/vnd.ms-excel,image/gif, image/jpeg, image/jpg,application/vnd.openxmlformats-officedocument.wordprocessingml.document'></td>" +
        "<td class='file-size'></td>" +
        "<td><a class='p-2'><i class='fa fa-pencil text-green'></i></a><a class='p-2 del_row'><i class='fa fa-trash text-danger'></i></a></td>" +
        "</tr>";

    function sCertIncorporation() {
        $('#ownershipList').on('change', function (e) {
            var option = $(this).find("option:selected").html();
            var certificateTbl = $('#certificateTbl').find('tbody');
            if (~option.indexOf("Incorporated")) {
                $('#cIncorporation').removeClass('hide');
                certificateTbl.append(cert);
            }else{
                $('#cIncorporation').addClass('hide');
                certificateTbl.empty();
            }
        });
    }

    function delTableRow(){
        $('body').on('click','.del_row',function(e){
            e.preventDefault();
            $(this).closest('tr').remove();
        });
    }

    function addMoreCert(){
        $('#addMoreCert').on('click',function(e){
            var certificateTbl = $('#certificateTbl').find('tbody').append(cert);
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
                }else{
                    $('#ownershipList').prop('disabled',true);
                    $('#firmName').prop('disabled', true);
                    $('#changeOfFirmName').prop('disabled', false);
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
                    getOwnerFinal();
                }else{
                    $('#ownerPartner').addClass('hide');
                }
            }
        });
    }

    function getContractor() {
        if($('#contractorRenewalForm').length < 1){
            return;
        }
        $.ajax({
            url: _baseURL() + '/getContractor',
            type: 'GET',
            success: function (res) {
                var contractor = res;
                $('#ownershipList').val(contractor.ownershipTypeId).prop('disabled',true);
                $('#pCountryId').val(contractor.pCountryId).prop('disabled',true);
                $('#tradeLicenseNo').val(contractor.tradeLicenseNo).prop('disabled',true);
                $('#firmName').val(contractor.firmName).prop('disabled',true);
                $('#tpn').val(contractor.tpn).prop('disabled',true);
                $('#pDzongkhagId').val(contractor.pDzongkhagId).prop('disabled',true);
                $('#pGewogId').val(contractor.pGewog).prop('disabled',true);
                $('#pVillageId').val(contractor.pVillage).prop('disabled',true);
                $('#estAddress').val(contractor.estAddress).prop('disabled',true);
                $('#regDzongkhagId').val(contractor.regDzongkhagId).prop('disabled',true);
                $('#regEmail').val(contractor.regEmail).prop('disabled',true);
                $('#regMobileNo').val(contractor.regMobileNo).prop('disabled',true);
                $('#regPhoneNo').val(contractor.regPhoneNo).prop('disabled',true);
                $('#regFaxNo').val(contractor.regFaxNo).prop('disabled',true);
                $('#contractorIdFinal').val(contractor.id);
            }
        });

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

    function getHRsFinal(){
        $('#updateHR').on('click',function(){
            if($(this).is(':checked')){
                $('.human_resource_criteria').removeClass('hide');
                $.ajax({
                    url: _baseURL() + '/getContractorHRsFinal',
                    type: 'GET',
                    data: {contractorId:$('#contractorIdFinal').val(),ownerOrHR:'H'},
                    success: function (res) {
                        var contractorHrs = res;
                        var hrTr = "";
                        //var openModal = "openModal('CheckModal')";
                        for (var i in contractorHrs) {

                            var attachments = '';
                            for (var j in contractorHrs[i].hrAttachments){
                                attachments = attachments + "<span class='hra'><input type='hidden' class='hraId' value='"+contractorHrs[i].hrAttachments[j].id+"'>" +
                                "<a href='"+_baseURL() + "/viewDownload?documentPath="+contractorHrs[i].hrAttachments[j].documentPath+"' target='_blank'>"+contractorHrs[i].hrAttachments[j].documentName+"</a></span><br>";
                            }
                            hrTr = hrTr + "<tr class=''>" +
                            "<td class='salutationName'><input type='hidden' class='contractorHRid' name='contractorHRs[0].id' value='"+contractorHrs[i].id +"'/>" +
                            "<input type='hidden' class='joiningDate' value='"+contractorHrs[i].joiningDate +"'/>"+
                            contractorHrs[i].salutationName + "</td>" +
                            "<td class='name'>" + contractorHrs[i].name + "</td>" +
                            "<td class='cidNo'>" + contractorHrs[i].cidNo + "</td>" +
                            "<td class='sex'>" + contractorHrs[i].sex + "</td>" +
                            "<td class='countryName'>" + contractorHrs[i].countryName + "</td>" +
                            "<td class='designationName'>" + contractorHrs[i].designationName + "</td>" +
                            "<td class='qualificationName'>" + contractorHrs[i].qualificationName + "</td>" +
                            "<td class='tradeName'>" + contractorHrs[i].tradeName + "</td>" +
                            "<td class='serviceTypeName'>" + contractorHrs[i].serviceTypeName + "</td>" +
                            "<td class='attachments'>" + attachments + "</td>" +
                            "<td class='action'><button class='btn-sm btn-info btn-block edit_row'>Edit</button>" +
                            "<button class='btn-sm btn-info btn-block del_row'>Delete</button></td>" +
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

    function getOwnerFinal(){
        $.ajax({
            url: _baseURL() + '/getContractorHRsFinal',
            type: 'GET',
            data: {contractorId:$('#contractorIdFinal').val(),ownerOrHR:'O'},
            success: function (res) {
                var contractorHrs = res;

                for (var i in contractorHrs) {
                    var tblRow = $('#partnerDtls').find('tbody tr:eq(' + (parseInt(i)) + ')');
                    tblRow.find('#countryList').val(tblRow.find('#countryList option:contains("' + contractorHrs[i].countryName + '")').val());
                    tblRow.find('.hr-cid').val(contractorHrs[i].cidNo);
                    tblRow.find('#salutation').val(tblRow.find('#salutation option:contains("' + contractorHrs[i].salutationName + '")').val());
                    tblRow.find('#designation').val(tblRow.find('#designation option:contains("' + contractorHrs[i].designationName + '")').val());
                    tblRow.find('.name').val(contractorHrs[i].name);
                    tblRow.find('#gender').val(contractorHrs[i].sex);
                    if (contractorHrs[i].siCertificate == '1') {
                        tblRow.find('.showCert').prop('checked', true);
                    } else {
                        tblRow.find('.showCert').prop('checked', false);
                    }
                }
            }

        });
    }
    function getEQsFinal(){
        $('#updateEq').on('click',function(){
            if($(this).is(':checked')){
                $('.equipment_details').removeClass('hide');
                $.ajax({
                    url: _baseURL() + '/getEquipmentFinal',
                    type: 'GET',
                    data: {contractorId:$('#contractorIdFinal').val()},
                    success: function (res) {
                        var equipments = res;
                        var eqTr = "";
                        for (var i in equipments) {

                            var attachment = '';
                            for (var j in equipments[i].eqAttachments){
                                attachment = attachment + "<span class='attachment'><input type='hidden' class='eqId' value='"+equipments[i].eqAttachments[j].id+"'>" +
                                "<a href='"+_baseURL() + "/viewDownload?documentPath="+equipments[i].eqAttachments[j].documentPath+"' target='_blank'>"+equipments[i].eqAttachments[j].documentName+"</a></span><br>";
                            }
                            eqTr = eqTr +
                            "<tr>" +
                            "<td><input type='hidden' class='contractorEQid' name='equipments[0].id' value='"+equipments[i].id +"'/>" + equipments[i].equipmentName + "</td>" +
                            "<td>" + equipments[i].registrationNo + "</td>" +
                            "<td>" + equipments[i].quantity + "</td>" +
                            "<td style='text-align: center'>"+attachment+"</td>" +
                            "<td class='action'><button class='btn-sm btn-info btn-block edit_row_eq'>Edit</button>" +
                            "<button class='btn-sm btn-info btn-block del_row'>Delete</button></td>" +
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

    function getClassCategory(){
        $('#upgradeDowngrade').on('click',function() {
            if ($(this).is(':checked')) {
                $('.category_details').removeClass('hide');
                $.ajax({
                    url: _baseURL() + '/getCategory',
                    type: 'GET',
                    data: {contractorId:$('#contractorIdFinal').val()},
                    success: function (res) {
                        var categories = res;
                        for (var i in categories) {
                            if (categories[i].categoryId == "6cd737d4-a2b7-11e4-b4d2-080027dcfac6") {
                                $('#W1').find('.categoryCheck').prop('checked', true);
                                $('#W1').find('.appliedClassID').val(categories[i].aClassId);
                                $('#W1').find('.existingClass').val(categories[i].aClassId);
                            }
                            if (categories[i].categoryId == "8176bd2d-a2b7-11e4-b4d2-080027dcfac6") {
                                $('#W2').find('.categoryCheck').prop('checked', true);
                                $('#W2').find('.appliedClassID').val(categories[i].aClassId);
                                $('#W2').find('.existingClass').val(categories[i].aClassId);
                            }
                            if (categories[i].categoryId == "8afc0568-a2b7-11e4-b4d2-080027dcfac6") {
                                $('#W3').find('.categoryCheck').prop('checked', true);
                                $('#W3').find('.appliedClassID').val(categories[i].aClassId);
                                $('#W3').find('.existingClass').val(categories[i].aClassId);
                            }
                            if (categories[i].categoryId == "9090a82a-a2b7-11e4-b4d2-080027dcfac6") {
                                $('#W4').find('.categoryCheck').prop('checked', true);
                                $('#W4').find('.appliedClassID').val(categories[i].aClassId);
                                $('#W4').find('.existingClass').val(categories[i].aClassId);
                            }
                        }
                    }
                });

            }else{
                $('.category_details').addClass('hide');
            }
            //positionTab('category_details');
        });
    }

    function enableClassCategory(){
        $('.categoryCheck').on('click',function(e){
            if($(this).is(':checked')){
                var tr = $(this).closest('tr');
                tr.find('.appliedClassID').prop('disabled',false).prop('required',true);
                tr.find('.appliedClassID').val(tr.find('.existingClass').val());
            }else{
                $(this).closest('tr').find('.appliedClassID').val('')
                    .prop('disabled',true).prop('required',false).removeClass('error');
            }
        })
    }

    function editInModal(){
        $('body').on('click','.edit_row',function(e){
            e.preventDefault();
            var row = $(this).closest('tr');
            var hrModal = $('#addHRModal');
            hrModal.find('#hrId').val(row.find('.contractorHRid').val())//for Edit
            hrModal.find('#hr5').val(hrModal.find('#hr5 option:contains("'+row.find('.countryName').text()+'")').val());
            hrModal.find('#hr3').val(row.find('.cidNo').text());
            hrModal.find('#hr1').val(hrModal.find('#hr1 option:contains("'+row.find('.salutationName').text()+'")').val());
            hrModal.find('#hr2').val(row.find('.name').text());
            hrModal.find('#hr4').val(row.find('.sex').text());
            hrModal.find('#hr6').val(hrModal.find('#hr6 option:contains("'+row.find('.designationName').text()+'")').val());
            hrModal.find('#hr7').val(hrModal.find('#hr7 option:contains("'+row.find('.qualificationName').text()+'")').val());
            hrModal.find('#hr8').val(hrModal.find('#hr8 option:contains("'+row.find('.tradeName').text()+'")').val());
            hrModal.find('#hr9').val(hrModal.find('#hr9 option:contains("'+row.find('.serviceTypeName').text()+'")').val());
            hrModal.find('#joiningDate').val(row.find('.joiningDate').val());
            var hraTr = "";
            row.find('.hra').each(function(){
                var name = $(this).find('a').text();
                var hra = $(this).find('a').parent().html();
                hraTr = hraTr+"<tr><td><input type='hidden' class='hraId' value='"+$(this).find('.hraId').val()+"'>" +
                "<input type='text' required class='form-control docName' name='contractorHRs[0].contractorHRAs[0].documentName' value='"+name.substring(0,name.lastIndexOf('.'))+"' disabled></td>" +
                "<td><span class='aName'> "+hra+"</span><span class='aFile'></span> </td>" +
                /*"<td></td>" +*/
                "<td><button class='change'>Change</button><button class='del_row'>Delete</button></td></tr>";
            });
            hrModal.find('#hrUploadTbl tbody').empty().html(hraTr);
            row.remove();
            openModal('addHRModal');
        });
    }

    function editInModalEQ(){
        $('body').on('click','.edit_row_eq',function(e){
            e.preventDefault();
            var row = $(this).closest('tr');
            alert(row.find('.contractorEQid').val());
            var modal = $('#eqModal');
            modal.find('#eq1').val(row.find('.contractorEQid').val());
            modal.find('#eq2').val(row.find('td:eq(2)').text());
            modal.find('#eq3').val(row.find('td:eq(2)').text());
            var hraTr = "";
            row.find('.attachment').each(function(){
                var name = $(this).find('a').text();
                var hra = $(this).find('a').parent().html();
                hraTr = hraTr+"<tr><td><input type='hidden' class='eqId' value='"+$(this).find('.hraId').val()+"'>" +
                "<input type='text' required class='form-control docName' name='equipments[0].contractorEQAs[0].documentName' value='"+name.substring(0,name.lastIndexOf('.'))+"' disabled></td>" +
                "<td><span class='aName'> "+hra+"</span><span class='aFile'></span> </td>" +
                    /*"<td></td>" +*/
                "<td><button class='change'>Change</button><button class='del_row'>Delete</button></td></tr>";
            });
            modal.find('#eqUploadTbl tbody').empty().html(hraTr);
            row.remove();
            openModal('eqModal');
        });
    }

    function changeFile(){
        $('#hrUploadTbl').on('click','.change',function(e){
            //e.preventDefault();
            var $this = $(this);
            var row = $(this).closest('tr');
            var file= "<input type='file' required class='file' name='contractorHRs[0].contractorHRAs[0].attachment'"+
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

    function getPersonalInfo($this,country,hrOrPartner){

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
                        if(hrOrPartner == 'H') {
                            $('#hr2').val(dto.fullName).prop('readonly', true);
                            $('#hr4').val(dto.sex).prop('readonly', true);
                        }else{
                            var index = $this.closest("tr").index();
                            $this.closest('tr').find('.name').val(dto.fullName).prop('readonly', true);
                            $this.closest('tr').find('.sex').val(dto.sex).prop('readonly', true);
                            //$('#pDzongkhagId').val(dto.dzongkhagId);
                            if (parseInt(index) == 0) {
                                $('#pDzongkhagId').val(dto.dzongkhagId);
                                $('#pGewogId').html("<option value='"+dto.gowegName+"'>"+dto.gowegName+"</option>");
                                $('#pVillageId').html("<option value='"+dto.villageName+"'>"+dto.villageName+"</option>");
                            }
                        }
                    }
                }
            });
        }
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
                    warningMsg("This is CID is already added your Owner/Partner list!!!");
                    $this.val('');
                    isHrExist = true;
                    return false;
                }
            });
            $('#hrDtlsTable').find('tbody tr td:nth-child(3)').each(function(){
                if($this.val() == $(this).text()){
                    warningMsg("This is CID is already added your HR list!!!");
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

    function init(){
        viewDownloadAttachment();
        getContractor();
        sCertIncorporation();
        service_check();
        getHRsFinal();
        getEQsFinal();
        getClassCategory();
        enableClassCategory();
        delTableRow();
        editInModal();
        changeFile();
        addMoreCert();
        checkDuplicateHR();
        editInModalEQ();
    }

    return {
        init:init
    };
})();

$(document).ready(function () {
        contractorRC.init();
    }
);