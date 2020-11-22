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
                    <span><b>Registration Criteria for Specialized Firm</b></span>

                    <div class="form-group">
                        <div class="col-lg-12">
                            <p>CID/ Work Permit copy for all Personnel including
                                his/her academic transcripts(For engineers Provisional Certificate will not be accepted
                                if the engineer has graduated more than six months ago)
                                and undertaking letter with legal stamp. </p>
                        </div>
                    </div>
                    <div class="form-group">
                        <div class="col-lg-12">
                            <div id="small">
                                <div><i>Registration Criteria for Specialized Firm</i></div>
                                <div class="table-responsive table-striped">
                                    <table class="table table-bordered table-hover table-striped" style=" font-size: 12px;">
                                        <thead style="background-color:#F2F2F2">
                                        <tr>
                                            <th align="center" rowspan="2">Specialized Category</th>
                                            <th align="center" colspan="2">Human Resource Required
                                           <tr>
                                            <th>Qualification Level</th>
                                            <th>Nos. of certified person(s)</th>
                                        </tr>
                                            </th>
                                        </tr>
                                        </thead>
                                        <tbody>
                                        <tr>
                                            <td align="center" rowspan="2">SF1-MasonrySF1-Masonry</td>
                                            <td align="center">National Certificate 3 – NC3</td>
                                            <td align="center">2</td>
                                        </tr>
                                        <tr>
                                            <td align="center">National Certificate 2 – NC2</td>
                                            <td align="center">1</td>
                                        </tr>
                                        <tr style="background-color:#F2F2F2">
                                            <td align="center" rowspan="2">SF2-Construction carpentry</td>
                                            <td align="center">National Certificate 3 – NC3</td>
                                            <td align="center">1</td>
                                        </tr>
                                        <tr style="background-color:#F2F2F2">
                                            <td align="center">National Certificate 2 – NC2</td>
                                            <td align="center">2</td>
                                        </tr>
                                        <tr>
                                            <td align="center" rowspan="2">SF3-Plumbing</td>
                                            <td align="center">National Certificate 3 – NC3</td>
                                            <td align="center">1</td>
                                        </tr>
                                        <tr>
                                            <td align="center">National Certificate 2 – NC2</td>
                                            <td align="center">1</td>
                                        </tr>
                                        <tr style="background-color:#F2F2F2">
                                            <td align="center" rowspan="2">SF4-Electrical</td>
                                            <td align="center">National Certificate 3 – NC3</td>
                                            <td align="center">1</td>
                                        </tr>
                                        <tr style="background-color:#F2F2F2">
                                            <td align="center">National Certificate 2 – NC2</td>
                                            <td align="center">1</td>
                                        </tr>
                                        <tr>
                                            <td align="center" rowspan="2">SF5-Welding &amp; Fabrication</td>
                                            <td align="center">National Certificate 3 – NC3</td>
                                            <td align="center">1</td>
                                        </tr>
                                        <tr>
                                            <td align="center">National Certificate 2 – NC2</td>
                                            <td align="center">1</td>
                                        </tr>
                                        <tr style="background-color:#F2F2F2">
                                            <td align="center" rowspan="2">SF6- Painting</td>
                                            <td align="center">National Certificate 3 – NC3</td>
                                            <td align="center">1</td>
                                        </tr>
                                        <tr style="background-color:#F2F2F2">
                                            <td align="center">National Certificate 2 – NC2</td>
                                            <td align="center">1</td>
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
                                <th style="width: 5%">Salutation</th>
                                <th style="width: 10%">Name</th>
                                <th style="width: 10%">ID/Work Permit No.</th>
                                <th style="width: 5%">Sex</th>
                                <th style="width: 10%">Country</th>
                                <th style="width: 10%">Designation</th>
                                <th style="width: 10%">Qualification</th>
                                <th style="width: 7%">Trade / Fields</th>
                                <th style="width: 7%">Service Type</th>
                                <th style="width: 21%">Attachments (CV/UT/AT)</th>
                                <th style="width: 5%">Joining Date</th>
                                <th style="width: 5%">Action</th>
                            </tr>
                            </thead>
                            <tbody>

                            </tbody>
                        </table>
                        <div class="col-lg-12 text-right">
                            <button type="button" class="btn btn-info btn-sm" data-toggle="modal" data-target="#addHRModal"><i class="fa fa-plus"></i> Add More HR</button>
                        </div>
                    </div>
                </div>
            </div>
            <!-- Box Close -->
        </div>
    </div>
</div>

<!-- HR modal open -->
<div aria-hidden="true" aria-labelledby="myModalLabel" role="dialog"  class="modal fade in" id="addHRModal">
    <div class="modal-dialog modal-lg" id="hrModal">
        <div class="modal-content">
            <div class="modal-header">
                <h4 id="myModalLabel" class="modal-title">Add Human Resource</h4>
                <button aria-hidden="true" data-dismiss="modal" class="close" type="button"></button>
            </div>
            <div class="modal-body form-horizontal">
                <div class="modal-div">
                    <div class="form-group">
                        <label class="col-lg-2">Nationality
                            <span class="text-danger">*</span>:</label>
                        <div class="col-lg-4">
                            <select name="spFirmHRs[0].countryId" id="hr5" required="" class="form-control custom-select text-left select-beast country">
                               <%-- <option value="">Select Country</option>--%>
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
                                <input type="text" name="spFirmHRs[0].cidNo" class="form-control hr-cid number" id="hr3" required="" placeholder="CID/Work Permit No">
                            </div>
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-lg-2">Salutation
                            <span class="text-danger">*</span>:</label>
                        <div class="col-lg-4">
                            <select name="spFirmHRs[0].salutationId" id="hr1" required="" class="form-control custom-select text-left select-beast">
                                <option value="">Select Salutation</option>
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
                                <input type="text" name="spFirmHRs[0].name" id="hr2" class="form-control name" required="" placeholder="Name">
                            </div>
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-lg-2">Gender<span class="text-danger">*</span>:</label>
                        <div class="col-lg-4">
                            <select name="spFirmHRs[0].sex" id="hr4" required="" class="form-control custom-select text-left select-beast sex">
                                <option value="">Select Gender</option>
                                <option value="M">Male</option>
                                <option value="F">Female</option>
                            </select>
                        </div>
                        <label class="col-lg-2">Designation
                            <span class="text-danger">*</span>:</label>
                        <div class="col-lg-4">
                            <select name="spFirmHRs[0].designationId" id="hr6" required="" class="form-control custom-select text-left select-beast">
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
                            <select name="spFirmHRs[0].qualificationId" id="hr7" required="" class="form-control custom-select text-left select-beast">
                                <option value="">Select Qualification</option>
                                <c:forEach var="item" items="${qualificationList}">
                                    <option value="${item.value}"><c:out value="${item.text}"/></option>
                                </c:forEach>
                            </select>
                        </div>
                        <label class="col-lg-2">Trade
                            <span class="text-danger">*</span>:</label>
                        <div class="col-lg-4">
                            <select name="spFirmHRs[0].tradeId" id="hr8" required="" class="form-control custom-select text-left select-beast">
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
                            <select name="spFirmHRs[0].serviceTypeId" id="hr9" required="true" class="form-control custom-select text-left select-beast">
                                <option value="">Select Type</option>
                                <c:forEach var="item" items="${serviceTypeList}">
                                    <option value="${item.value}"><c:out value="${item.text}"/></option>
                                </c:forEach>
                            </select>
                        </div>
                        <label class="col-lg-2"> Joining Date<span class="text-danger">*</span>:</label>
                        <div class="col-lg-4">
                            <div class="input-group margin-bottom-sm">
                                <span class="input-group-addon pr-5"><i
                                        class="fa fa-calendar"></i></span>
                                <input type="date" name="spFirmHRs[0].joiningDate"
                                       value="" id="hr10"
                                       class="form-control datepicker" required="true">
                            </div>
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-lg-2">CDB Reg No :</label>
                        <div class="col-lg-4">
                            <div class="input-icon">
                                <span class="input-icon-addon"><i class="fe fe-user"></i></span>
                                <input type="text" name="spFirmHRs[0].cdbNo" id="hr11" class="form-control name"  placeholder="cdbNo..">
                            </div>
                        </div>
                    </div>
                    <div class="row">
                        <div class="col-md-12 col-lg-12">
                            <input type="button" id="addMoreHr" value="Add More File" class="btn btn-primary hrFile">
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
                                    <tr><td><input type='text' required="true"  value="CID"class='form-control docName' name='spFirmHRs[0].spFirmHRAs[0].documentName'/> </td>
                                        <td><input type='file' required="true" class='file' name='spFirmHRs[0].spFirmHRAs[0].attachment' accept='application/msword,application/pdf,application/vnd.ms-excel,image/gif, image/jpeg, image/jpg,application/vnd.openxmlformats-officedocument.wordprocessingml.document'/> </td>
                                        <td class='file-size'></td>
                                        <td class='del_row'> <a class='p-2'><i class='fa fa-trash text-danger '></i></a></td>
                                    </tr>
                                    <tr><td><input type='text' required="true" value="Certificate" class='form-control docName' name='spFirmHRs[0].spFirmHRAs[0].documentName'/> </td>
                                        <td><input type='file' required="true" class='file'  name='spFirmHRs[0].spFirmHRAs[0].attachment' accept='application/msword,application/pdf,application/vnd.ms-excel,image/gif, image/jpeg, image/jpg,application/vnd.openxmlformats-officedocument.wordprocessingml.document'/> </td>
                                        <td class='file-size'></td>
                                        <td class='del_row'> <a class='p-2'><i class='fa fa-trash text-danger '></i></a></td>
                                    </tr>
                                    <tr><td><input type='text' required="true" value="Others" class='form-control docName' name='spFirmHRs[0].spFirmHRAs[0].documentName'/> </td>
                                        <td><input type='file' required="true" class='file' name='spFirmHRs[0].spFirmHRAs[0].attachment' accept='application/msword,application/pdf,application/vnd.ms-excel,image/gif, image/jpeg, image/jpg,application/vnd.openxmlformats-officedocument.wordprocessingml.document'/> </td>
                                        <td class='file-size'></td>
                                        <td class='del_row'> <a class='p-2'><i class='fa fa-trash text-danger '></i></a></td>
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
                <button data-dismiss="modal" class="btn btn-warning" type="button">Close</button>
            </div>
        </div>
    </div>
</div>
<!-- HR modal close -->
<div class="col-lg-12 form-group">
    <button type="button" onclick="backTab('humanResourceCriteria','category_details')" class="btn btn-azure col-lg-offset-9">
        <i class="fa fa-arrow-circle-left"></i> &nbsp;Back</button>
    <button type="button" id="btnValHRNext"
    <%--onclick="nextTab('humanResourceCriteria','consultantEquipmentDtls')"--%>
            class="btn btn-primary">Next &nbsp;
        <i class="fa fa-arrow-circle-right"></i>
    </button>
</div>
<%--</form>--%>
