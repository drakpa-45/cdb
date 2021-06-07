/**
 * Created by user on 2/14/2020.
 */
//region functions callable from jsp

function _baseURL() {
    return cdbGlobal.baseURL() + "/public_access/contractorCC";
}

//endregion

var contractorCC = (function () {
    "use strict";
    var formID = "#contractorCCForm";
    function saveCC(){ //save cancellation of certificate
        $(formID).on('click', '#btnSubmit', function (e) {
            debugger;
            $(formID).validate({
                submitHandler: function (form) {
                    $.ajax({
                        url: _baseURL() + '/save',
                        type: 'POST',
                        data: $(form).serializeArray(),
                        success: function (res) {
                                successMsg('Cancellation request for CDB certificate is sent.', _baseURL());
                        }
                    })
                }
            });
        });
    }
    function init(){
        saveCC();
    }
    return {
        init:init
    };
})();

$(document).ready(function () {
        contractorCC.init();
    }
);