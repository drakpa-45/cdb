/**
 * Created by user on 2/9/2020.
 */


var tasklist = (function (){
    "use strict";
    var isSubmitted = false;

    function _baseURL(){
        return cdbGlobal.baseURL()+"/admin";
    }



    function getContractorInfo(){
        $('#btnSubmit').on('click',function(e) {
                    $.ajax({
                        url: _baseURL() + '/save',
                        type: 'POST',
                        data: $(form).serializeArray(),
                        success: function(res){
                            if(res.status == '1'){
                                successMsg(res.text,_baseURL());
                            } else{
                                warningMsg(res.text);
                            }
                        }
                    });
        });
    }
    function saveCC(){
        $('#btnContractorCC').on('click',function(e) {
            $('form[id="contractorCCForm"]').validate({
                submitHandler: function (form){
                    alert(JSON.stringify($(form).serializeArray()));
                    $.ajax({
                        url: _baseURL() + '/saveCC',
                        type: 'POST',
                        data: $(form).serializeArray(),
                        success: function(res){
                            if(res.status == '1'){
                                successMsg(res.text,_baseURL());
                            } else{
                                warningMsg(res.text);
                            }
                        }
                    });
                }
            });
        })
    }


    return{
        save: save
    };
})();

$(document).ready(function () {
        tasklist.save();
        tasklist.saveCC();
    }
);