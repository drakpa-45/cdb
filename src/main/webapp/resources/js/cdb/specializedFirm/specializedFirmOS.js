/**
 * Created by user on 2/14/2020.
 */
//region functions callable from jsp

function addRow(tableId) {
    var $tableBody = $('#' + tableId).find("tbody");
    var isValid = true;
    $tableBody.find(':input').each(function () {
        if (isValid == true) {
            isValid = $('#specializedFirmOSForm').validate().element(this);
        } else {
            $('#specializedFirmOSForm').validate().element(this);
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
    $("#" + modalId).modal('show');
}
//region model
var hr_modal = $("#hrModal").html();
var eq_modal = $("#eqModal").html();
var j= 0;
function getModalData(tableId, prefix, totalCol) {
    var td = "";
    $('#modalForm').validate();
    var modal = $('#' + prefix + '1').closest('.modal');
    if (modal.find(':input').valid() == false) {
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
        $(this).attr('name', 'spFirmHRs[0].spFirmHRAs['+index+'].attachment');
    });

    uplTbl.find('.docName').each(function(e){
        var index = $(this).closest('tr').index();
        docName = docName +"<input type='hidden' name='spFirmHRs[0].spFirmHRAs["+index+"].id' value='"+$(this).closest('tr').find('.hraId').val()+"'/>";
        docName = docName +"<input type='hidden' name='spFirmHRs[0].spFirmHRAs["+index+"].documentName' value='"+$(this).val()+"'/><b>"+$(this).val() +'</b><br>';
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
    //$('#formId').val('specializedFirmForm');
    $('#targetId').val('acknowledgementmessage');
    $('#messages').html('You are about to submit application. Are you sure to proceed ?');
}
function submitApplication(){
    cdbGlobal.formIndexing($('#certificateTbl').find('tbody'));
    cdbGlobal.formIndexing($('#partnerDtls').find('tbody'));
    cdbGlobal.formIndexing($('#equipmentTbl').find('tbody'));
    cdbGlobal.formIndexing($('#hrDtlsTable').find('tbody'));
    $("#addHRModal").find(":input").prop('disabled',true);

    var specializedFirmForm = $("#specializedFirmOSForm");
    var url=_baseURL() + "/save";
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
function _baseURL() {
    return cdbGlobal.baseURL() + "/public_access/specializedFirmOS";
}

//endregion

var specializedFirmOS = (function () {
    "use strict";

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
                   // $('#firmName').prop('disabled', true);
                    $('#newFirmName').show();
                    $('#changeOfFirmName').prop('disabled', true);
                }else{
                    $('#ownershipList').prop('disabled',true);
                  //  $('#firmName').prop('disabled', false);
                    $('#newFirmName').hide();
                    $('#changeOfFirmName').prop('disabled', false);
                }
            } else if(id == 'changeOfFirmName' ){
                if($this.is(':checked')) {
                //    $('#firmName').prop('disabled', true);
                    $('#newFirmName').show();
                }else{
                  //  $('#firmName').prop('disabled', false);
                    $('#newFirmName').hide();
                }
            }else if(id == 'changeOfLocation' ){
                if($this.is(':checked')) {
                    $('#editEstbAddress').show();
                    $('#newRegDzoId').show();
                    $('#estAddress').prop('disabled', true);
                    $('#regDzongkhagId').prop('disabled', true);

                }else{
                    $('#editEstbAddress').hide();
                    $('#newRegDzoId').hide();
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

    function getSpecializedFirm() {
        if($('#specializedFirmOSForm').length < 1){
            return;
        }
        $.ajax({
            url: _baseURL() + '/getSpecializedFirm',
            type: 'GET',
            success: function (res) {
                var specializedFirm = res;
                $('#ownershipList').val(specializedFirm.ownershipTypeId).prop('disabled',true);
                $('#pCountryId').val(specializedFirm.pCountryId).prop('disabled',true);
                $('#tradeLicenseNo').val(specializedFirm.tradeLicenseNo).prop('disabled',true);
                $('#firmName').val(specializedFirm.firmName).prop('disabled',true);
                $('#tpn').val(specializedFirm.tpn).prop('disabled',true);
                $('#pDzongkhagId').val(specializedFirm.pDzongkhagId).prop('disabled',true);
                $('#pGewogId').val(specializedFirm.pGewogId).prop('disabled',true);
                $('#pVillageId').val(specializedFirm.pVillageId).prop('disabled',true);
                $('#estAddress').val(specializedFirm.regAddress).prop('disabled',true);
                $('#regDzongkhagId').val(specializedFirm.regDzongkhagId).prop('disabled',true);
                $('#regEmail').val(specializedFirm.regEmail).prop('disabled',true);
                $('#regMobileNo').val(specializedFirm.regMobileNo).prop('disabled',true);
                $('#regPhoneNo').val(specializedFirm.regPhoneNo).prop('disabled',true);
                $('#regFaxNo').val(specializedFirm.regFaxNo).prop('disabled',true);
                $('#specializedFirmIdFinal').val(specializedFirm.id);
            }
        });
    }

    function viewDownloadAttachment(){
        $('body').on('click','.vAttachment',function(){
            var id = $(this).closest('tr').find('.specializedFirmHRid').val();
            $.ajax({
                url: _baseURL() + '/viewDownload',
                type: 'GET',
                data: {tableName:'crpspecializedtradehumanresourceattachment',filterCol:'CrpSpecializedtradeHumanResourceId',filterVal:id}
            });
        });
    }

    function getHRsFinal(){
        $('#updateHR').on('click',function(){
            if($(this).is(':checked')){
                $('.human_resource_criteria').removeClass('hide');
                $.ajax({
                    url: _baseURL() + '/getSpecializedFirmHRsFinal',
                    type: 'GET',
                    data: {specializedFirmId:$('#specializedFirmIdFinal').val(),ownerOrHR:'H'},
                    success: function (res) {
                        var spFirmHrs = res;
                        var hrTr = "";
                        //var openModal = "openModal('CheckModal')";
                        for (var i in spFirmHrs) {

                            var attachments = '';
                            for (var j in spFirmHrs[i].hrAttachments){
                                attachments = attachments + "<span class='hra'><input type='hidden' class='hraId' value='"+spFirmHrs[i].hrAttachments[j].id+"'>" +
                                "<a href='"+_baseURL() + "/viewDownload?documentPath="+spFirmHrs[i].hrAttachments[j].documentPath+"' target='_blank'>"+spFirmHrs[i].hrAttachments[j].documentName+"</a></span><br>";
                            }
                            hrTr = hrTr + "<tr class=''>" +
                            "<td class='salutationName'><input type='hidden' class='specializedFirmHRid' name='spFirmHRs[0].id' value='"+spFirmHrs[i].id +"'/>" +
                            "<input type='hidden' class='joiningDate' value='"+spFirmHrs[i].joiningDate +"'/>"+
                            spFirmHrs[i].salutationName + "</td>" +
                            "<td class='name'>" + spFirmHrs[i].name + "</td>" +
                            "<td class='cidNo'>" + spFirmHrs[i].cidNo + "</td>" +
                            "<td class='sex'>" + spFirmHrs[i].sex + "</td>" +
                            "<td class='countryName'>" + spFirmHrs[i].countryName + "</td>" +
                            "<td class='designationName'>" + spFirmHrs[i].designationName + "</td>" +
                            "<td class='qualificationName'>" + spFirmHrs[i].qualificationName + "</td>" +
                            "<td class='tradeName'>" + spFirmHrs[i].tradeName + "</td>" +
                            "<td class='serviceTypeName'>" + spFirmHrs[i].serviceTypeName + "</td>" +
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
       // var specializedFirmId =$('#specializedFirmIdFinal').val();
      //  alert(specializedFirmId);
        $.ajax({
            url: _baseURL() + '/getSpecializedFirmHRsFinal',
            type: 'GET',
            data: {specializedFirmId:$('#specializedFirmIdFinal').val(),ownerOrHR:'O'},
            success: function (res) {
                var spFirmHrs = res;
                for (var i in spFirmHrs) {
                    var tblRow = $('#partnerDtls').find('tbody tr:eq(' + (parseInt(i)) + ')');
                    tblRow.find('#countryList').val(tblRow.find('#countryList option:contains("' + spFirmHrs[i].countryName + '")').val());
                    tblRow.find('.hr-cid').val(spFirmHrs[i].cidNo);
                    tblRow.find('#salutation').val(tblRow.find('#salutation option:contains("' + spFirmHrs[i].salutationName + '")').val());
                    tblRow.find('#designation').val(tblRow.find('#designation option:contains("' + spFirmHrs[i].designationName + '")').val());
                    tblRow.find('.name').val(spFirmHrs[i].name);
                    tblRow.find('#gender').val(spFirmHrs[i].sex);
                    if (spFirmHrs[i].siCertificate == '1') {
                        tblRow.find('.showCert').prop('checked', true);
                    } else {
                        tblRow.find('.showCert').prop('checked', false);
                    }
                }
            }

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

    function getEQsFinal(){
        $('#updateEq').on('click',function(){
            if($(this).is(':checked')){
                $('.equipment_details').removeClass('hide');
                $.ajax({
                    url: _baseURL() + '/getEquipmentFinal',
                    type: 'GET',
                    data: {specializedFirmId:$('#specializedFirmIdFinal').val()},
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
                            "<td><input type='hidden' class='specializedFirmEQid' name='spFirmEQs[0].id' value='"+equipments[i].id +"'/>"
                            + equipments[i].equipmentName + "</td>" +
                            "<td>" + equipments[i].registrationNo + "</td>" +
                            "<td>" + equipments[i].quantity + "</td>" +
                            "<td>" + attachment + "</td>" +
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

    function addMoreEqFile(){
        $('#addMoreEq').on('click',function(e){
            var uplTbl = $('#eqUploadTbl').find('tbody');
            var tr = "<tr><td><input type='text' required class='form-control docName' name='equipments[0].spFirmEQAs[0].documentName'/> </td>" +
                "<td><input type='file' required class='file' name='equipments[0].spFirmEQAs[0].attachment' accept='application/msword,application/pdf,application/vnd.ms-excel,image/gif, image/jpeg, image/jpg,application/vnd.openxmlformats-officedocument.wordprocessingml.document'/> </td><td class='file-size'></td>" +
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
                    data: {specializedFirmId:$('#specializedFirmIdFinal').val()},
                    success: function (res) {
                        var categories = res;
                        for (var i in categories) {
                            if (categories[i].categoryId == "3h1f937c-c74f-11e4-bf37-080027dcfac6") {
                                $('#W1').find('.categoryCheck').prop('checked', true);
                            }
                            if (categories[i].categoryId == "3h2f937c-c74f-11e4-bf37-080027dcfac6") {
                                $('#W2').find('.categoryCheck').prop('checked', true);
                            }
                            if (categories[i].categoryId == "3h3f937c-c74f-11e4-bf37-080027dcfac6") {
                                $('#W3').find('.categoryCheck').prop('checked', true);
                            }
                            if (categories[i].categoryId == "3h4f937c-c74f-11e4-bf37-080027dcfac6") {
                                $('#W4').find('.categoryCheck').prop('checked', true);
                            }
                            if (categories[i].categoryId == "3h5f937c-c74f-11e4-bf37-080027dcfac6") {
                                $('#W5').find('.categoryCheck').prop('checked', true);
                            }
                            if (categories[i].categoryId == "3h6f937c-c74f-11e4-bf37-080027dcfac6") {
                                $('#W6').find('.categoryCheck').prop('checked', true);
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
                $(this).closest('tr').find('.categoryCheck').prop('disabled',false).prop('required',true);
            }else{
                $(this).closest('tr').find('.categoryCheck').prop('disabled',true);
            }
        })
    }

    function editInModal(){
        $('body').on('click','.edit_row',function(e){
            e.preventDefault();
            var row = $(this).closest('tr');
            var hrModal = $('#addHRModal');
            hrModal.find('#hrId').val(row.find('.specializedFirmHRid').val())//for Edit
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
                "<input type='text' required class='form-control docName' name='spFirmHRs[0].spFirmHRAs[0].documentName' value='"+name.substring(0,name.lastIndexOf('.'))+"' disabled></td>" +
                "<td><span class='aName'> "+hra+"</span><span class='aFile'></span> </td>" +
                    "<td></td>" +
                "<td><button class='change'>Change</button><button class='del_row'>Delete</button></td></tr>";
            });
            hrModal.find('#hrUploadTbl tbody').empty().html(hraTr);
           // row.remove();
            openModal('addHRModal');
        });
    }

    function editInModalEQ(){
        $('body').on('click','.edit_row_eq',function(e){
            e.preventDefault();
            var row = $(this).closest('tr');
          //  alert(row.find('.specializedFirmEQid').val());
            var modal = $('#eqModal');
            modal.find('#eq1').val(row.find('.specializedFirmEQid').val());
            modal.find('#eq2').val(row.find('td:eq(2)').text());
            modal.find('#eq3').val(row.find('td:eq(2)').text());
            var hraTr = "";
            row.find('.attachment').each(function(){
                var name = $(this).find('a').text();
                var hra = $(this).find('a').parent().html();
                hraTr = hraTr+"<tr><td><input type='hidden' class='eqId' value='"+$(this).find('.hraId').val()+"'>" +
                "<input type='text' required class='form-control docName' name='equipments[0].spFirmEQAs[0].documentName' value='"+name.substring(0,name.lastIndexOf('.'))+"' disabled></td>" +
                "<td><span class='aName'> "+hra+"</span><span class='aFile'></span> </td>" +
                    /*"<td></td>" +*/
                "<td><button class='change'>Change</button><button class='del_row'>Delete</button></td></tr>";
            });
            modal.find('#eqUploadTbl tbody').empty().html(hraTr);
           // row.remove();
            openModal('eqModal');
        });
    }

    function changeFile(){
        $('#hrUploadTbl').on('click','.change',function(e){
            //e.preventDefault();
            var $this = $(this);
            var row = $(this).closest('tr');
            var file= "<input type='file' required class='file' name='spFirmHRs[0].spFirmHRAs[0].attachment'"+
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
            $('#err-expired-audit-memo').val('Your application is expired. You can only avail HR update and Equipment Update.');
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
        getSpecializedFirm();
        sCertIncorporation();
        service_check();
        getHRsFinal();
        getEQsFinal();
        getClassCategory();
        enableClassCategory();
        allowEditHrEqExpired();
        delTableRow();
        editInModal();
        changeFile();
        showFileSize();
        addMoreCert();
        checkDuplicateHR();
        editInModalEQ();
        addMoreEqFile();
    }
    return {
        init:init
    };
})();

$(document).ready(function () {
        specializedFirmOS.init();
    }
);