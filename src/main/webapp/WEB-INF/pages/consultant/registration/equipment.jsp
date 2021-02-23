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
    <i><strong>Consultant Equipment Details</strong></i>
    <div class="panel panel-default">
        <div class="panel-body">
            <span><b>Requirements for different Category and Classification</b></span>
            <div class="form-group mt-5">
                <div class="col-lg-6">
                    <div id="Surveyor">
                        <div><i>Note for Surveyor Services</i></div>
                        <div class="table-responsive">
                            <table id="" class="table table-bordered table-hover" style=" font-size: 12px;">
                                <thead style="background-color:#F2F2F2">
                                <tr>
                                    <th align="center">Category</th>
                                    <th align="center">Human Resource <br>Requirement</th>
                                    <th align="center">Equipment Requirement</th>
                                </tr>
                                </thead>
                                <tbody>
                                <tr>
                                    <td align="center">S1</td>
                                    <td align="center">Minimum Diploma field of specialization with Certified Cadastral Surveyor: 2</td>
                                    <td align="center">Total Station (3 sec or better): 1 set Or GNSS RTK: 1 set</td>
                                </tr>
                                <tr>
                                    <td align="center">S2</td>
                                    <td align="center" rowspan="2">Minimum Diploma in field of specialization: 1 Certificate in field of specialization: 1</td>
                                    <td align="center">GNSS/RTK: 1 set Total Station (5 sec or better): 1 set Levelling instrument: 1</td>
                                </tr>
                                <tr>
                                    <td align="center">S3</td>
                                    <td align="center">Bathymetric Survey Instrument: 1</td>
                                </tr>
                                <tr>
                                    <td align="center">S4</td>
                                    <td align="center" rowspan="3">Minimum Diploma in field of specialization: 1 Certificate in field of specialization: 1</td>
                                    <td align="center">Survey Grade GNSS: 4 Precision Levelling instrument: 1 Licensed GNSS Data Processing & adjustment Software: 1</td>
                                </tr>
                                <tr>
                                    <td align="center">S5</td>
                                    <td align="center">DPWS: 1 Licensed Photogrammetric software: 1</td>
                                </tr>
                                <tr>
                                    <td align="center">S6</td>
                                    <td align="center"></td>
                                </tr>
                                <tr>
                                    <td align="center">S7</td>
                                    <td align="center"> Minimum diploma in field of specialization: 1</td>
                                    <td align="center">Established collimator: 1</td>
                                </tr>
                                </tbody>
                            </table>
                        </div>
                    </div>
                </div>
                <div class="col-sm-6">
                    <div id="Electrical">
                        <div><i>Note for Electrical Engineering Services</i></div>
                        <div class="table-responsive">
                            <table id="" class="table table-bordered table-hover" style=" font-size: 12px;">
                                <thead style="background-color:#F2F2F2">
                                <tr>
                                    <th align="center">Employees</th>
                                    <th align="center">E1&E2</th>
                                    <th align="center">E3&E4</th>
                                    <th align="center">E5&E6</th>
                                    <th align="center">E7</th>
                                </tr>
                                </thead>
                                <tbody>
                                <tr>
                                    <td align="center">Manager</td>
                                    <td align="center">1</td>
                                    <td align="center">1</td>
                                    <td align="center">1</td>
                                    <td align="center">1</td>
                                </tr>
                                <tr>
                                    <td align="center">Engineer (Degree)</td>
                                    <td align="center">1(civil),1(Elect),1(Mech),1(Hydrologiest)</td>
                                    <td align="center">1(civil),1(Elect)</td>
                                    <td align="center">1(Elect)</td>
                                    <td align="center">-</td>
                                </tr>
                                <tr>
                                    <td align="center">Engineer (Diploma)</td>
                                    <td align="center">-</td>
                                    <td align="center">1(civil)</td>
                                    <td align="center">1(civil)</td>
                                    <td align="center">1(Elect)</td>
                                </tr>
                                </tbody>
                            </table>
                        </div>
                    </div>
                    <div id="Architectural">
                        <div><i>Note for Architectural Services</i></div>
                        <div class="table-responsive">
                            <table id="" class="table table-bordered table-hover" style=" font-size: 12px;">
                                <thead style="background-color:#F2F2F2">
                                <tr>
                                    <th align="center">Employees</th>
                                    <th align="center">A1</th>
                                    <th align="center">A2</th>
                                    <th align="center">A3</th>
                                </tr>
                                </thead>
                                <tbody>
                                <tr>
                                    <td align="center">Architect(Bhutanese)Master/Degree</td>
                                    <td align="center">1</td>
                                    <td align="center">1</td>
                                    <td align="center">1</td>
                                </tr>
                                </tbody>
                            </table>
                        </div>
                    </div>
                    <div id="Civil">
                        <div><i>Note for Civil Engineering Services</i></div>
                        <div class="table-responsive">
                            <table id="" class="table table-bordered table-hover" style=" font-size: 12px;">
                                <thead style="background-color:#F2F2F2">
                                <tr>
                                    <th align="center">Employees</th>
                                    <th align="center">C1&C4</th>
                                    <th align="center">C2</th>
                                    <th align="center">C3</th>
                                    <th align="center">C5&C6</th>
                                    <th align="center">C7</th>
                                </tr>
                                </thead>
                                <tbody>
                                <tr>
                                    <td align="center">Engineer (Degree)</td>
                                    <td align="center">1</td>
                                    <td align="center">1*</td>
                                    <td align="center">1**</td>
                                    <td align="center">-</td>
                                    <td align="center">1</td>
                                </tr>
                                <tr>
                                    <td align="center">Engineer (Diploma)</td>
                                    <td align="center">-</td>
                                    <td align="center">-</td>
                                    <td align="center">-</td>
                                    <td align="center">1</td>
                                    <td align="center">-</td>
                                </tr>
                                <tr>
                                    <td align="center" colspan="6">*Geo-Tech Engineer, **Social Science/Environmental
                                    </td>
                                </tr>
                                </tbody>
                            </table>
                        </div>
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
                    <%-- <tr>
                         <td><select name="equipments[0].equipmentId"
                                     class="form-control custom-select text-left equipmentId"
                                     required="">
                             <option value="">Select Equipment</option>
                             <c:forEach var="item" items="${equipmentList}">
                                 <option value="${item.value}" class="${item.obj1}"><c:out
                                         value="${item.text}"/></option>
                             </c:forEach>
                         </select></td>
                         <td>
                             <input type="text" name="equipments[0].registrationNo"
                                    class="form-control registrationNo" required="true" disabled>
                         </td>
                         <td>
                             <input type="text" name="equipments[0].quantity"
                                    class="form-control"
                                    required="">
                         </td>
                         <td>
                             <input type="file" name="equipments[0].consultantEQA.attachment" id="equipmentFile">
                         </td>
                         <td><a class='p-2'><i class='fa fa-pencil text-green'></i></a><a class='p-2'><i class='fa fa-trash text-danger'></i></a></td>
                     </tr>--%>
                    </tbody>
                </table>
                <div class="col-lg-12 text-right">
                    <button type="button" class="btn btn-info btn-sm" data-toggle="modal" id="addMoreEqup" data-target="#eqModal"><i
                            class="fa fa-plus"></i> Add More Equipment
                    </button>
                </div>
                <%--<div class="col-lg-12 text-right">
                    <button type="button"
                            class="btn btn-outline-primary btn-sm"
                            onclick="addRow('eqdatatable')">
                        <i class="fe fe-plus mr-2"></i>Add More
                    </button>
                    <button type="button"
                            class="btn btn-outline-danger btn-sm"
                            onclick="removeRow('eqdatatable')">
                        <i class="fe fe-trash mr-2"></i>Remove Last Row
                    </button>
                </div>--%>
                <!-- Equipment modal close -->
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
                        <label class="col-lg-2">Equipment<span class="text-danger">*</span>:</label>
                        <div class="col-lg-4">
                            <select name="equipments[0].equipmentId" class="form-control custom-select text-left equipmentId" required="" id="eq1" onchange="enableRegistrationNo()">
                                <option value="">Select Equipment</option>
                                <c:forEach var="item" items="${equipmentList}">
                                    <option value="${item.value}" class="${item.obj1}"><c:out value="${item.text}"/></option>
                                </c:forEach>
                            </select>
                        </div>
                        <label class="col-lg-3">Registration No <span class="text-danger">*</span>:</label>
                        <div class="col-lg-3">
                            <input type="text" name="equipments[0].registrationNo" class="form-control registrationNo" onchange="validateNo(this.value)" <%--required="true"--%> id="eq2">
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-lg-2">Quantity <span class="text-danger">*</span>:</label>
                        <div class="col-lg-4">
                            <div class="input-icon">
                                <input type="text" name="equipments[0].quantity" class="form-control number" required="" id="eq3">
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
                                        <td><input type='text' required="" value="Bluebook" class='form-control docName' name='equipments[0].consultantEQAs[0].documentName'/></td>
                                        <td><input type='file' required="" class='file' name='equipments[0].consultantEQAs[0].attachment'
                                                   accept='application/msword,application/pdf,application/vnd.ms-excel,image/gif, image/jpeg, image/jpg'/>
                                        </td>
                                        <td class='file-size'></td>
                                        <td class='del_row'><a class='p-2'><i class='fa fa-trash text-danger '></i></a></td>
                                    </tr>
                                    <tr>
                                        <td><input type='text' required="" value="Insurance Certificate" class='form-control docName' name='equipments[0].consultantEQAs[0].documentName'/></td>
                                        <td><input type='file' required="" class='file' name='equipments[0].consultantEQAs[0].attachment'
                                                   accept='application/msword,application/pdf,application/vnd.ms-excel,image/gif, image/jpeg, image/jpg'/>
                                        </td>
                                        <td class='file-size'></td>
                                        <td class='del_row'><a class='p-2'><i class='fa fa-trash text-danger '></i></a></td>
                                    </tr>
                                    <tr>
                                        <td><input type='text' required="" value="Others" class='form-control docName' name='equipments[0].consultantEQAs[0].documentName'/></td>
                                        <td><input type='file' required="" class='file' name='equipments[0].consultantEQAs[0].attachment'
                                                   accept='application/msword,application/pdf,application/vnd.ms-excel,image/gif, image/jpeg, image/jpg'/>
                                        </td>
                                        <td class='file-size'></td>
                                        <td class='del_row'><a class='p-2'><i class='fa fa-trash text-danger '></i></a></td>
                                    </tr>
                                    </tbody>
                                </table>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
            <div class="modal-footer">
                <button class="btn btn-primary" onclick="getModalData('eqdatatable','eq',3)" type="button">OK</button>
                <button data-dismiss="modal" class="btn btn-warning" type="button">Close</button>
            </div>
        </div>
    </div>
</div>
<div class="col-lg-12 form-group">
    <button type="button" id="btn5" onclick="backTab('equipment_details','human_resource_criteria')" class="btn btn-azure col-lg-offset-9">
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
                $('#eq2').val('').prop('disabled', true).prop('required', false);
                $('#eq3').val('').prop('disabled', false).prop('required', true);
            }
        })
    }
</script>
<%--</form>--%>
