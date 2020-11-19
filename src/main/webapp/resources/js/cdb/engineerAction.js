function showAcknowledgement() {

    $("#registrtaionFormCard").hide();
    $("#acknowledgementCard").show();
}


function nextTab(presentClass, nextClass) {
    $("." + presentClass + ">a").addClass('bg-blue text-white');
    $('.tab-pane').removeClass("active");
    $('.tab-content').removeClass("active");
    $("." + nextClass).addClass("active");
    $("." + presentClass + ">a").append("<i class='fa fa-check ml-1'></i>");
}


var verifyEngineer = (function () {
    "use strict";
    var isSubmitted = false;
    var applicationNo = null;

    function _baseURL() {
        return cdbGlobal.baseURL() + "/admin/engineer";
    }

    function getEngineerList() {
        applicationNo = $('#appNo').val();
        alert(applicationNo);
        $.ajax({
            url: _baseURL() + '/getEngineerList',
            type: 'GET',
            data: {applicationNo: applicationNo},
            success: function (res) {
                if (res != null) {
                    for (var i in res) {
                        populate(res[i]);
                    }
                    populateDocument(res);
                    populateApplicationStatus(res);

                    $('#gewog').val(1);
                    $('#village').val(1);
                    $('#applicationId').val(applicationNo);
                }
            }
        });
    }

    function populateDocument(res) {
        var tableId = $('#documentAttachTableId');
        var row;

        for (var i in res) {
            var view_href = _baseURL() + "/view?documentPath=" + res[i].documentPath + "+&documentName="+ res[i].documentName;
            row = '<tr>' +
            '<td>' + parseInt(i + 1) + '</td>' +
            '<td id="documentName">' + res[i].documentName + '</td>' +
            '<td id="documentPathId">' + res[i].documentPath + '</td>' +
            '<td><a href=""><button class="btn btn-primary btn-sm"type="button" id="btnView"><i class="fa fa-eye"></i>View</button></a></td>' +
            '<td><button class="btn btn-success btn-sm" type="button" id="btnDownload"><i class="fa fa-download"></i>Download</button></td>' +
            '</tr>';
            tableId.find('tbody').append(row);
        }
    }

    function populateApplicationStatus(res) {
        var tableId = $('#applicationStatusTableId');
        var row;
        for (var i in res) {
            row = '<tr>' +
            '<td>' + res[i].registrationStatus + '</td>' +
            '<td>' + res[i].cid + '</td>' +
            '<td>' + res[i].applicationDate + '</td>' +
            '<td>' + res[i].remark + '</td>' +
            '</tr>';
            tableId.find('tbody').append(row);
        }
    }

    function approveApplication() {
        $('#approveApplication').on('click', function () {
            var remark = $("#remark").val();
            alert(remark + "applicationNo" + applicationNo);
            $.ajax({
                url: _baseURL() + '/approveApplication',
                type: 'POST',
                data: {
                    remark: remark,
                    applicationNo: applicationNo
                }, success: function (res) {
                    if (res.status == 1) {
                        successMsg(res.text);
                    } else {
                        warningMsg(res.text);
                    }
                }
            });
        });
    }

    function sendBackApplication() {
        $('#sendBackApplication').on('click', function () {
            var remark = $("#remark").val();
            alert(remark + "applicationNo" + applicationNo);
            $.ajax({
                url: _baseURL() + '/sendBackApplication',
                type: 'POST',
                data: {
                    remark: remark,
                    applicationNo: applicationNo
                }, success: function (res) {
                    if (res.status == 1) {
                        successMsg(res.text);
                    } else {
                        warningMsg(res.text);
                    }
                }
            });
        });
    }

    function verifyApplication() {
        $('#verifyApplication').on('click', function () {
            var remark = $("#remark").val();
            alert(remark + "applicationNo" + applicationNo);
            $.ajax({
                url: _baseURL() + '/verifyApplication',
                type: 'POST',
                data: {
                    remark: remark,
                    applicationNo: applicationNo
                }, success: function (res) {
                    if (res.status == 1) {
                        successMsg(res.text);
                    } else {
                        warningMsg(res.text);
                    }
                }
            });
        });
    }

    function rejectApplication() {
        $('#rejectApplication').on('click', function () {
            var remark = $("#remark").val();
            alert(remark + "applicationNo" + applicationNo);
            $.ajax({
                url: _baseURL() + '/rejectApplication',
                type: 'POST',
                data: {
                    remark: remark,
                    applicationNo: applicationNo
                }, success: function (res) {
                    if (res.status == 1) {
                        successMsg(res.text);
                    } else {
                        warningMsg(res.text);
                    }
                }
            });
        });
    }

    function viewDocument() {
        $('#documentAttachTableId').find('tbody').on('click', '#btnView', function () {
            var selectedRow = $(this).closest('tr');
            var documentName = selectedRow.find('#documentName').text();
            var documentPath = selectedRow.find('#documentPathId').text();
            //alert(documentPath);

            $.ajax({
                url: _baseURL() + '/view',
                type: 'GET',
                data: {documentPath: documentPath, documentName: documentName}
            });
        });
    }

    function downloadDocument() {
        $('#documentAttachTableId').find('tbody').on('click', '#btnDownload', function () {
            alert("download document.");
            var selectedRow = $(this).closest('tr');
            var documentPath = selectedRow.find('#documentPathId').text();
            alert(documentPath);
            $.ajax({
                url: _baseURL() + '/download',
                type: 'GET',
                data: {
                    documentPath: documentPath
                },
                success: function (res) {
                }
            });
        });
    }

    /**
     * To get gewog based on dzongkhag id.
     */
    function getGewogList(dzongkhagId) {
        $.ajax({
            url: _baseURL() + '/getGewogList',
            type: 'GET',
            data: {dzongkhagId: dzongkhagId},
            success: function (res) {
                if (res.status == '1') {
                    cdbGlobal.loadDropDown($('#gewog'), res.dto);
                }
            }
        });
        //});
    }

    /**
     *To get village list based on gewog Id.
     */
    function getVillageList(gewogId) {
        $.ajax({
            url: _baseURL() + '/getVillageList',
            type: 'GET',
            data: {gewogId: gewogId},
            success: function (res) {
                if (res.status == '1') {
                    cdbGlobal.loadDropDown($('#village'), res.dto);
                }
            }
        });
    }


    return {
        getEngineerList: getEngineerList,
        viewDocument: viewDocument,
        downloadDocument: downloadDocument,
        approveApplication: approveApplication,
        sendBackApplication: sendBackApplication,
        verifyApplication: verifyApplication,
        rejectApplication: rejectApplication
    };
})();

$(document).ready(function () {
        verifyEngineer.getEngineerList();
        verifyEngineer.viewDocument();
        verifyEngineer.downloadDocument();
        verifyEngineer.approveApplication();
        verifyEngineer.sendBackApplication();
        verifyEngineer.verifyApplication();
        verifyEngineer.rejectApplication();
    }
);