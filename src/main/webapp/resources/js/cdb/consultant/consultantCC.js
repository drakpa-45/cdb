/**
 * Created by user on 2/14/2020.
 */
//region functions callable from jsp

function addRow(tableId) {
    var $tableBody = $('#' + tableId).find("tbody");
    var isValid = true;
    $tableBody.find(':input').each(function () {
        if (isValid == true) {
            isValid = $('#consultantRenewalForm').validate().element(this);
        } else {
            $('#consultantRenewalForm').validate().element(this);
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
//endregion

function enableSubmit(){
    if($('#agreeCheck').prop('checked')){
        $('#btnSubmit').prop('disabled',false);
    }
    else{
        $('#btnSubmit').prop('disabled',true);
    }

}
function _baseURL() {
    return cdbGlobal.baseURL() + "/public_access/consultantCC";
}
//endregion
var consultantCC = (function () {
    "use strict";
    var formID = "#consultantCCForm";
    function saveCC(){ //save cancellation of certificate
        $(formID).on('click', '#btnSubmit', function (e) {
            $(formID).validate({
                submitHandler: function (form) {
                    $.ajax({
                        url: _baseURL() + '/save',
                        type: 'POST',
                        data: $(form).serializeArray(),
                        success: function (res) {
                            if (res.status == 1) {
                                successMsg(res.text, _baseURL());
                            } else {
                                errorMsg(res.text);
                            }
                        }
                    })
                }
            });
        });
    }

    var cert = "<tr><td></td>" +
        "<td><input type='text' class='form-control' name='cAttachments[0].documentName'/> </td>"+
        "<td><input type='file' name='cAttachments[0].attachment' class='form-control-file file' accept='application/msword,application/pdf,application/vnd.ms-excel,image/gif, image/jpeg, image/jpg,application/vnd.openxmlformats-officedocument.wordprocessingml.document'></td>" +
        "<td class='file-size'></td>" +
        "<td><a class='p-2'><i class='fa fa-pencil text-green'></i></a><a class='p-2 del_row'><i class='fa fa-trash text-danger'></i></a></td>" +
        "</tr>";

    function init(){
        saveCC();
    }

    return {
        init:init
    };
})();

$(document).ready(function () {
        consultantCC.init();
    }
);