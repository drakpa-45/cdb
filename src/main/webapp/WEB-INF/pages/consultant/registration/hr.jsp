<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: user
  Date: 1/3/2020
  Time: 12:25 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%--<form contractor="" method="post" class="">--%>
<div class="div-actual">
    <div class="row">
        <div class="col-md-12 col-lg-12">
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
                                            <td align="center">-</td>
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
                                            <td align="center">1(Elect)</td>
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
                                            <td align="center">1*</td>
                                            <td align="center">1*</td>
                                            <td align="center">1*</td>
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
                                            <td align="center" colspan="6">*Geo-Tech Engineer, **Social Science/Environmental</td>
                                        </tr>
                                        </tbody>
                                    </table>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>

            <!-- Box Close -->
            <!-- Box Open -->
            <div class="card tab4">
                <div class="bg-blue card-status card-status-left"></div>
                <div class="card-header">
                    <h3 class="card-title">Human Resource Form</h3>
                </div>
                <div class="card-body">
                    <div style="overflow-x: auto;white-space: nowrap;">
                        <table class="table table-bordered" id="hrDtlsTable">
                            <thead>
                            <tr>
                                <th style="width: 5%">Title</th>
                                <th style="width: 10%">Name</th>
                                <th style="width: 10%">ID/Work Permit No.</th>
                                <th style="width: 5%">Gender</th>
                                <th style="width: 10%">Country</th>
                                <th style="width: 10%">Designation</th>
                                <th style="width: 10%">Qualification</th>
                                <th style="width: 7%">Trade / Fields</th>
                                <th style="width: 7%">Service Type</th>
                                <th style="width: 10%">Joining Date</th>
                                <th style="width: 21%">Attachments (CV/UT/AT)</th>
                                <th style="width: 5%">Action</th>
                            </tr>
                            </thead>
                            <tbody>

                            </tbody>
                        </table>

                        <div class="col-lg-12 text-right">
                            <button type="button" class="btn btn-info btn-sm" data-toggle="modal" id="addMoreHrBtn" data-target="#addHRModal"><i class="fa fa-plus"></i> Add More HR</button>
                        </div>
                    </div>
                </div>
            </div>
            <!-- Box Close -->
        </div>
    </div>
</div>
<!-- HR modal open -->
<div aria-hidden="true" aria-labelledby="myModalLabel" role="dialog" tabindex="-1" class="modal fade in" id="addHRModal">
    <div class="modal-dialog modal-lg" id="hrModal">
        <div class="modal-content">
            <div class="modal-header">
                <h4 id="myModalLabel" class="modal-title">Add Human Resource</h4>
                <button aria-hidden="true" data-dismiss="modal" class="close" type="button"></button>
            </div>
            <div class="modal-body form-horizontal">
                <div class="">
                    <div class="form-group">
                        <label class="col-lg-2">Nationality
                            <span class="text-danger">*</span>:</label>
                        <div class="col-lg-4">
                            <select name="consultantHRs[0].countryId" id="hr5" required="true" class="form-control custom-select text-left select-beast country">
                                <%--   <option value="">Select Country</option>--%>
                                <c:forEach var="item" items="${countryList}">
                                    <option value="${item.value}"><c:out value="${item.text}"/></option>
                                </c:forEach>
                            </select>
                        </div>
                        <label class="col-md-2 col-lg-2">CID/Work Permit No <span class="text-danger">*</span>:</label>
                        <div class="col-lg-4">
                            <div class="input-icon">
                                <span class="input-icon-addon">
                                    <i class="fa fa-address-card-o"></i>
                                </span>
                                <input type="number" name="consultantHRs[0].cidNo" class="form-control hr-cid number" id="hr3" required="true" placeholder="CID/Work Permit No">
                            </div>
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-lg-2">Title
                            <span class="text-danger">*</span>:</label>
                        <div class="col-lg-4">
                            <select name="consultantHRs[0].salutationId" id="hr1" required="true" class="form-control custom-select text-left select-beast">
                                <option value="">Select Title</option>
                                <c:forEach var="item" items="${salutationList}">
                                    <option value="${item.value}"><c:out value="${item.text}"/></option>
                                </c:forEach>
                            </select>
                        </div>
                        <label class="col-lg-2">Name
                            <span class="text-danger">*</span>:</label>
                        <div class="col-lg-4">
                            <div class="input-icon">
                                <span class="input-icon-addon"><i class="fe fe-user"></i></span>
                                <input type="text" name="consultantHRs[0].name" id="hr2" class="form-control name" required="true" placeholder="Name">
                            </div>
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-lg-2">Gender<span class="text-danger">*</span>:</label>
                        <div class="col-lg-4">
                            <select name="consultantHRs[0].sex" id="hr4" required="true" class="form-control custom-select text-left select-beast sex">
                                <option value="">Select Gender</option>
                                <option value="M">Male</option>
                                <option value="F">Female</option>
                            </select>
                        </div>
                        <label class="col-lg-2">Designation
                            <span class="text-danger">*</span>:</label>
                        <div class="col-lg-4">
                            <select name="consultantHRs[0].designationId" id="hr6" required="true" class="form-control custom-select text-left select-beast">
                                <option value="">Select Designation</option>
                                <c:forEach var="item" items="${designationList}">
                                    <option value="${item.value}"><c:out value="${item.text}"/></option>
                                </c:forEach>
                            </select>
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-lg-2">Qualification
                            <span class="text-danger">*</span>:</label>
                        <div class="col-lg-4">
                            <select name="consultantHRs[0].qualificationId" id="hr7" required="true" class="form-control custom-select text-left select-beast">
                                <option value="">Select Qualification</option>
                                <c:forEach var="item" items="${qualificationList}">
                                    <option value="${item.value}"><c:out value="${item.text}"/></option>
                                </c:forEach>
                            </select>
                        </div>
                        <label class="col-lg-2">Trade
                            <span class="text-danger">*</span>:</label>
                        <div class="col-lg-4">
                            <select name="consultantHRs[0].tradeId" id="hr8" required="true" class="form-control custom-select text-left select-beast">
                                <option value="">Select Trade</option>
                                <c:forEach var="item" items="${tradeList}">
                                    <option value="${item.value}"><c:out value="${item.text}"/></option>
                                </c:forEach>
                            </select>
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-lg-2">Service Type<span class="text-danger">*</span>:</label>
                        <div class="col-lg-4">
                            <select name="consultantHRs[0].serviceTypeId" id="hr9" required="true" class="form-control custom-select text-left select-beast">
                                <option value="">Select Type</option>
                                <c:forEach var="item" items="${serviceTypeList}">
                                    <option value="${item.value}"><c:out value="${item.text}"/></option>
                                </c:forEach>
                            </select>
                        </div>
                        <label class="col-lg-2"> Joining Date<span class="text-danger">*</span>:</label>
                        <div class="col-lg-4">
                            <div class="input-group margin-bottom-sm">
                                <span class="input-group-addon pr-5"><i class="fa fa-calendar"></i></span>
                                <input type="date" name="consultantHRs[0].joiningDate" value="" required="true" id="hr10" class="form-control datepicker">
                            </div>
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-lg-2">CDB Reg No:</label>
                        <div class="col-lg-4">
                            <div class="input-icon">
                                <span class="input-icon-addon"><i class="fe fe-user"></i></span>
                                <input type="text" id="hr11" class="form-control name" placeholder="cdbNo">
                            </div>
                        </div>
                    </div>
                    <div class="row">
                        <div class="col-md-12 col-lg-12">
                            <input type="button" id="addMore" value="Add More File" class="btn btn-primary hrFile">
                            <div class="table-responsive">
                                <table class="table table-bordered table-hover" id="hrUploadTbl">
                                    <thead>
                                    <tr>
                                        <th>Document Name</th>
                                        <th>Document Attached</th>
                                        <th>File Size</th>
                                        <th>Delete</th>
                                    </tr>
                                    </thead>
                                    <tbody id="fileListhrsection" class="files">
                                    <tr>
                                        <td><input type='text' required="true" value="Certificate" class='form-control docName' name='consultantHRs[0].consultantHRAs[0].documentName'/></td>
                                        <td><input type='file' required="true" class='file' name='consultantHRs[0].consultantHRAs[0].attachment'
                                                   accept='application/msword,application/pdf,application/vnd.ms-excel,image/gif, image/jpeg, image/jpg,application/vnd.openxmlformats-officedocument.wordprocessingml.document'/>
                                        </td>
                                        <td class='file-size'></td>
                                        <td class='del_row'><a class='p-2'><i class='fa fa-trash text-danger '></i></a></td>
                                    </tr>
                                    <tr>
                                        <td><input type='text' required="true" value="Undertaking" class='form-control docName' name='consultantHRs[0].consultantHRAs[0].documentName'/></td>
                                        <td><input type='file' required="true" class='file' name='consultantHRs[0].consultantHRAs[0].attachment'
                                                   accept='application/msword,application/pdf,application/vnd.ms-excel,image/gif, image/jpeg, image/jpg,application/vnd.openxmlformats-officedocument.wordprocessingml.document'/>
                                        </td>
                                        <td class='file-size'></td>
                                        <td class='del_row'><a class='p-2'><i class='fa fa-trash text-danger '></i></a></td>
                                    </tr>
                                    <tr>
                                        <td><input type='text' required="true" value="Others" class='form-control docName' name='consultantHRs[0].consultantHRAs[0].documentName'/></td>
                                        <td><input type='file' required="true" class='file' name='consultantHRs[0].consultantHRAs[0].attachment'
                                                   accept='application/msword,application/pdf,application/vnd.ms-excel,image/gif, image/jpeg, image/jpg,application/vnd.openxmlformats-officedocument.wordprocessingml.document'/>
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
                <button class="btn btn-primary" onclick="getModalData('hrDtlsTable','hr',10)" type="button">OK</button>
                <button data-dismiss="modal" class="btn btn-warning" target="#addHRModal" type="button">Close</button>
            </div>
        </div>
    </div>
</div>
<!-- HR modal close -->
<div class="col-lg-12 form-group">
    <button type="button" id="btn4" onclick="backTab('humanResourceCriteria','category_details')" class="btn btn-azure col-lg-offset-9">
        <i class="fa fa-arrow-circle-left"></i> &nbsp;Back
    </button>
    <button type="button" id="btnValHRNext"
    <%--onclick="nextTab('humanResourceCriteria','consultantEquipmentDtls')"--%> class="btn btn-primary">Next &nbsp;
        <i class="fa fa-arrow-circle-right"></i>
    </button>
</div>
<%--</form>--%>
