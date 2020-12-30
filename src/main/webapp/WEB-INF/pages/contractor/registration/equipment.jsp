<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: user
  Date: 1/3/2020
  Time: 9:37 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%--<form contractor="" method="post" class="">--%>
<div class="div-actual">
    <i><strong>Contractor Equipment Details</strong></i>
    <div class="panel panel-default">
        <div class="panel-body">
            <span><b>Requirements for different Category and Classification</b></span>
            <div class="form-group">
                <div class="col-lg-1">
                </div>
                <div class="col-lg-11">
                    <p>1.&nbsp;&nbsp;&nbsp;No equipment requirement for small class contractors.</p>
                    <p>2.&nbsp;&nbsp;&nbsp;Submit valid blue book copies supported by valid insurances for all RSTA
                        registered equipments.</p>
                    <p>3.&nbsp;&nbsp;&nbsp;Submit equipments verification reports duly endorsed by a Govt. Engineer (not
                        less than the rank of AE) for those equipment, which are not dealt by RSTA.</p>
                </div>
            </div>
            <div class="form-group">
                <div class="col-lg-6">
                    <div><i>Note for small Class (S)</i></div>
                    <div class="table-responsive">
                        <table id="" class="table table-bordered table-hover" style=" font-size: 12px;">
                            <tbody>
                            <tr>
                                <td>No equipment requirement for small class contractors.</td>
                            </tr>
                            <tr>
                                <td>Submit blue book copies supported by Route Permits and insurances for all RSTA registered equipments.</td>
                            </tr>
                            <tr>
                                <td>Submit equipments verification reports duly endorsed by a Govt. Engineer (not less than the rank of AE) for those equipment, which are not dealt by RSTA.</td>
                            </tr>
                            </tbody>
                        </table>
                    </div>
                    <div><i>Note for Medium Class (M)</i></div>
                    <div class="table-responsive">
                        <table class="table table-bordered table-hover" style=" font-size: 12px;">
                            <thead style="background-color:#F2F2F2">
                            <tr>
                                <th align="center">Equipments</th>
                                <td align="center">W1</td>
                                <td align="center">W3</td>
                                <td align="center">W4</td>
                            </tr>
                            </thead>
                            <tbody>
                            <tr>
                                <td align="center">Truck</td>
                                <td align="center">1*</td>
                                <td align="center">1*</td>
                                <td align="center">1*</td>
                            </tr>
                            <tr>
                                <td align="center">Survey Equipment leveling machine</td>
                                <td align="center">1*</td>
                                <td align="center">1*</td>
                                <td align="center">1*</td>
                            </tr>
                            <tr>
                                <td align="center">concrete Mixer</td>
                                <td align="center">1</td>
                                <td align="center">1</td>
                                <td align="center"></td>
                            </tr>
                            <tr>
                                <td align="center">Vibrator</td>
                                <td align="center">1*</td>
                                <td align="center">1*</td>
                                <td align="center">1*</td>
                            </tr>
                            <tr>
                                <td align="center">Steel Shuttering (sft)</td>
                                <td align="center"></td>
                                <td align="center">2000</td>
                                <td align="center"></td>
                            </tr>
                            <tr>
                                <td align="center">Water Pump/Multi Meter</td>
                                <td align="center"></td>
                                <td align="center">1</td>
                                <td align="center">1</td>
                            </tr>
                            <tr>
                                <td align="center">Meggar</td>
                                <td align="center"></td>
                                <td align="center"></td>
                                <td align="center">1</td>
                            </tr>
                            </tbody>
                        </table>
                    </div>
                </div>
                <div class="col-lg-6">
                    <div><i>Note for Large Class (L)</i></div>
                    <div class="table-responsive">
                        <table class="table table-bordered table-hover" style=" font-size: 12px;">
                            <thead style="background-color:#F2F2F2">
                            <tr>
                                <th align="center">Equipments</th>
                                <td align="center">W1</td>
                                <td align="center">W3</td>
                                <td align="center">W4</td>
                            </tr>
                            </thead>
                            <tbody>
                            <tr>
                                <td align="center">Excavator</td>
                                <td align="center">1</td>
                                <td align="center">1</td>
                                <td align="center"></td>
                            </tr>
                            <tr>
                                <td align="center">Road Roller</td>
                                <td align="center">1</td>
                                <td align="center"></td>
                                <td align="center"></td>
                            </tr>
                            <tr>
                                <td align="center">Truck</td>
                                <td align="center">1</td>
                                <td align="center">1</td>
                                <td align="center">1</td>
                            </tr>
                            <tr>
                                <td align="center">Air Compressor</td>
                                <td align="center">1</td>
                                <td align="center">1</td>
                                <td align="center">1</td>
                            </tr>
                            <tr>
                                <td align="center">Survey Equipment</td>
                                <td align="center">1*</td>
                                <td align="center">1*</td>
                                <td align="center">1*</td>
                            </tr>
                            <tr>
                                <td align="center">Concrete Mixer</td>
                                <td align="center"></td>
                                <td align="center">1</td>
                                <td align="center">1</td>
                            </tr>
                            <tr>
                                <td align="center">Vibrator</td>
                                <td align="center"></td>
                                <td align="center">1*</td>
                                <td align="center">1*</td>
                            </tr>
                            <tr>
                                <td align="center">Crane Truck</td>
                                <td align="center"></td>
                                <td align="center">1</td>
                                <td align="center">1</td>
                            </tr>
                            <tr>
                                <td align="center">Steel Shuttering (sft)</td>
                                <td align="center"></td>
                                <td align="center">5000</td>
                                <td align="center"></td>
                            </tr>
                            <tr>
                                <td align="center">Water Pump/Multi Meter</td>
                                <td align="center"></td>
                                <td align="center"></td>
                                <td align="center">1</td>
                            </tr>
                            <tr>
                                <td align="center">Meggar</td>
                                <td align="center"></td>
                                <td align="center"></td>
                                <td align="center">1</td>
                            </tr>
                            <tr>
                                <td align="center">Max Puller</td>
                                <td align="center"></td>
                                <td align="center"></td>
                                <td align="center">1</td>
                            </tr>
                            </tbody>
                        </table>
                    </div>
                </div>
            </div>
        </div>
    </div>
    <div class="panel panel-default">
        <div class="panel-body">
            <div class="table-responsive">
                <table class="table table-bordered table-striped"
                       id="eqdatatable">
                    <thead>
                    <tr>
                        <th>Equipment Name</th>
                        <th>Registration Number</th>
                        <th>Quantity</th>
                        <th>Attachment</th>
                        <th>Action</th>
                    </tr>
                    </thead>
                    <tbody>
                    <%--<tr>
                        <td><select name="equipments[0].equipmentId" class="form-control custom-select text-left equipmentId" required="">
                            <option value="">Select Equipment</option>
                            <c:forEach var="item" items="${equipmentList}">
                                <option value="${item.value}" class="${item.obj1}"><c:out value="${item.text}"/></option>
                            </c:forEach>
                        </select></td>
                        <td>
                            <input type="text" name="equipments[0].registrationNo" class="form-control registrationNo" required="true" disabled>
                        </td>
                        <td>
                            <input type="text" name="equipments[0].quantity" class="form-control" required="">
                        </td>
                        <td>
                            <input type="file" name="equipments[0].contractorEQA.attachment" id="equipmentFile"
                                   accept='application/msword,application/pdf,application/vnd.ms-excel,image/gif, image/jpeg, image/jpg,application/vnd.openxmlformats-officedocument.wordprocessingml.document'>
                        </td>
                        <td><a class='p-2'><i class='fa fa-pencil text-green'></i></a><a class='p-2'><i class='fa fa-trash text-danger'></i></a></td>
                    </tr>--%>
                    </tbody>
                </table>

                <div class="col-lg-12 text-right">
                    <button type="button" class="btn btn-info btn-sm" data-toggle="modal" data-target="#eqModal"><i class="fa fa-plus"></i> Add More Equipment</button>
                </div>
                <%--<div class="col-lg-12 text-right">
                    <button type="button" class="btn btn-outline-primary btn-sm" onclick="addRow('eqdatatable')">
                        <i class="fe fe-plus mr-2"></i>Add More
                    </button>
                    <button type="button" class="btn btn-outline-danger btn-sm" onclick="removeRow('eqdatatable')">
                        <i class="fe fe-trash mr-2"></i>Remove Last Row
                    </button>
                </div>--%>
            </div>
        </div>
    </div>
</div>
<div aria-hidden="true" aria-labelledby="myModalLabel" role="dialog" class="modal fade in" id="eqModal">
    <div class="modal-dialog modal-lg">
        <div class="modal-content">
            <div class="modal-header">
                <h4 id="myModalLabel" class="modal-title">Add Equipment</h4>
                <button aria-hidden="true" data-dismiss="modal" class="close" type="button"><span></span></button>
            </div>
            <div class="modal-body form-horizontal">
                <div class="">
                    <div class="form-group">
                        <label class="col-lg-2">Equipment <span class="text-danger">*</span>:</label>
                        <div class="col-lg-4">
                            <select name="equipments[0].equipmentId" class="form-control custom-select text-left select-beast equipmentId" required="" onchange="enableRegistrationNo()" id="eq1">
                                <option value="">Select</option>
                                <c:forEach var="item" items="${equipmentList}">
                                    <option value="${item.value}" class="${item.obj1}"><c:out value="${item.text}"/></option>
                                </c:forEach>
                            </select>
                        </div>
                        <label class="col-lg-3">Registration No <span class="text-danger">*</span>:</label>
                        <div class="col-lg-3">
                            <input type="text" placeholder="BP-1-A1234" name="equipments[0].registrationNo" class="form-control registrationNo" onchange="validateNo(this.value)"<%--required="true"--%> disabled id="eq2">
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-lg-2">Quantity <span class="text-danger">*</span>:</label>
                        <div class="col-lg-4">
                            <div class="input-icon">
                                <input type="text" name="equipments[0].quantity" class="form-control" required="" id="eq3">
                            </div>
                        </div>
                    </div>
                    <div class="row">
                        <div class="col-lg-12">
                            <input type="button" id="addMoreEq" value="Add More File" class="btn btn-primary eqFile">
                            <div class="table-responsive">
                                <table class="table table-bordered table-hover" id="eqUploadTbl">
                                    <thead>
                                    <tr>
                                        <th>Document Name</th>
                                        <th>Document Attached</th>
                                        <th>File Size</th>
                                        <th>Delete</th>
                                    </tr>
                                    </thead>
                                    <tbody class="files">
                                    <tr>
                                        <td><input type='text' required="" class='form-control docName' name='equipments[0].contractorEQAs[0].documentName'/></td>
                                        <td><input type='file' required="true" class='file' name='equipments[0].contractorEQAs[0].attachment'
                                                   accept='application/msword,application/pdf,application/vnd.ms-excel,image/gif, image/jpeg, image/jpg,application/vnd.openxmlformats-officedocument.wordprocessingml.document'/>
                                        </td>
                                        <td class='file-size'></td>
                                        <td class='del_row'><a class='p-2'><i class='fa fa-trash text-danger '></i></a></td>
                                    </tr>
                                    <tr>
                                        <td><input type='text' required="" class='form-control docName' name='equipments[0].contractorEQAs[0].documentName'/></td>
                                        <td><input type='file' required="true" class='file' name='equipments[0].contractorEQAs[0].attachment'
                                                   accept='application/msword,application/pdf,application/vnd.ms-excel,image/gif, image/jpeg, image/jpg,application/vnd.openxmlformats-officedocument.wordprocessingml.document'/>
                                        </td>
                                        <td class='file-size'></td>
                                        <td class='del_row'><a class='p-2'><i class='fa fa-trash text-danger '></i></a></td>
                                    </tr>
                                    <%--   <tr><td><input type='text' required="" class='form-control docName' name='equipments[0].contractorEQAs[0].documentName'/> </td>
                                           <td><input type='file' required="" class='file' name='equipments[0].contractorEQAs[0].attachment' accept='application/msword,application/pdf,application/vnd.ms-excel,image/gif, image/jpeg, image/jpg,application/vnd.openxmlformats-officedocument.wordprocessingml.document'/> </td>
                                           <td class='file-size'></td>
                                           <td class='del_row'> <a class='p-2'><i class='fa fa-trash text-danger '></i></a></td>
                                       </tr>--%>
                                    </tbody>
                                </table>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
            <div class="modal-footer">
                <button data-dismiss="modal" class="btn btn-primary" onclick="getModalData('eqdatatable','eq',3)" type="button">OK</button>
                <button data-dismiss="modal" class="btn btn-warning" type="button">Close</button>
            </div>
        </div>
    </div>
</div>
<div class="col-lg-12 form-group">
    <button type="button" onclick="backTab('equipment_details','human_resource_criteria')" class="btn btn-azure col-lg-offset-9">
        <i class="fa fa-arrow-circle-left"></i>&nbsp; Back
    </button>
    <button type="button" class="btn btn-primary" id="btnValEqNext">Save & Preview &nbsp;
        <i class="fa fa-life-saver"></i>
    </button>
</div>
<script>
    function validateNo(vNo) {
        if (validateVehNo(vNo)) {
            return true;
        } else {
            $('#eq2').val('');
            errorMsg('oops!! please check your vehicle Number format');
            return false;
        }
    }
    function validateVehNo() {
        var vNo = $('#eq2').val();
        const re = /\w[BPG]-\d-\w\d{4}$/;
        return re.test(vNo);
    }
    function checkDuplicateEQ() {
        var $this = $(this);
        var isEqExist = false;
        $('#eqdatatable').find('tbody tr td:nth-child(3)').each(function () {
            if ($this.val() == $(this).text()) {
                warningMsg("This Registration Number is already exists in your list!!!");
                $this.val('');
                isEqExist = true;
                return isEqExist;
            }
        });
    }

    function enableRegistrationNo() {
        $('.equipmentId').on('change', function (e) {
            var isRegistration = $(this).find("option:selected").hasClass("1");
            if (isRegistration == true) {
                $('#eq2').prop('disabled', false).prop('required', true);
                $('#eq3').val(1).prop('disabled', true);
            } else {
                $('#eq2').val('').prop('disabled', true);
                $('#eq3').val('').prop('disabled', false);
            }
        })
    }
</script>

