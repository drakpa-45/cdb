<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: user
  Date: 11/22/2019
  Time: 11:22 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%--<form name="contractorGIForm" contractor="contractor/saveGI" method="POST" class="globalForm"
      id="contractorGIForm" enctype="multipart/form-data" role="form">--%>
<div class="div-actual" id="soledetails">
    <div class="card tab2">
        <div class="bg-blue card-status card-status-left"></div>
        <div class="card-header">
            <h3 class="card-title">Personal Details</h3>
        </div>
        <div class="card-body">
            <div class="col-lg-12 col-lg-md col-sm-12 col-xs-12">
                <div class="col-lg-6 col-md-6 col-sm-6 col-xs-12 form-group">
                    <div class="form-group row">
                        <label class="col-lg-3 col-md-3 col-sm-3 col-xs-12">Salutation: <span class="text-danger">*</span></label>
                        <div class="col-lg-9 col-md-9 col-sm-9 col-xs-12">
                            <form:select id="salutation" path="salutationList" name="salutation" class="form-control">
                                <form:option value="">Select</form:option>
                                <form:options items="${salutationList}" itemValue="value" itemLabel="text"></form:options>
                            </form:select>
                        </div>
                        <span id="salutation_err" class="text-danger"></span>
                    </div>
                    <div class="form-group row">
                        <label class="col-lg-3 col-md-3 col-sm-3 col-xs-12">CID Number:</label>
                        <div class="col-lg-9 col-md-9 col-sm-9 col-xs-12">
                            <input type="text" readonly id="cidNo" name="cidNo" class="form-control number">
                        </div>
                    </div>
                    <div class="form-group row">
                        <label class="col-lg-3 col-md-3 col-sm-3 col-xs-12">Name:</label>
                        <div class="col-lg-9 col-md-9 col-sm-9 col-xs-12">
                            <input type="text" readonly name="fullname" maxlength="100" id="name" class="form-control">
                        </div>
                    </div>
                    <div class="form-group row">
                        <label class="col-lg-3 col-md-3 col-sm-3 col-xs-12">Dzongkhag:</label>
                        <div class="col-lg-9 col-md-9 col-sm-9 col-xs-12">
                            <input type="text" readonly maxlength="100" id="dzongkhag" class="form-control">
                            <input type="hidden" name="dzongkhagId" id="dzongkhagId">
                        </div>
                    </div>
                    <div class="form-group row">
                        <label class="col-lg-3 col-md-3 col-sm-3 col-xs-12">Gewog:</label>
                        <div class="col-lg-9 col-md-9 col-sm-9 col-xs-12">
                            <input type="text" readonly maxlength="100" id="gewog" name="gewog" class="form-control">
                        </div>
                    </div>
                    <div class="form-group row">
                        <label class="col-lg-3 col-md-3 col-sm-3 col-xs-12">Village:</label>
                        <div class="col-lg-9 col-md-9 col-sm-9 col-xs-12">
                            <input type="text" readonly maxlength="100" id="village" name="village" class="form-control">
                            <input type="hidden" name="villageId" id="villageId">
                        </div>
                    </div>
                </div>
                <div class="col-lg-6 col-md-6 col-sm-6 col-xs-12 form-group">
                    <span id="imageId"></span>
                </div>
            </div>
        </div>
    </div>
    <!-- Box Close -->
    <!-- Box Open -->
    <div class="card tab2">
        <div class="bg-blue card-status card-status-left"></div>
        <div class="card-header">
            <h3 class="card-title">Contact Details</h3>
        </div>
        <div class="card-body">
            <div class="col-md-12 col-lg-12 col-sm-12 col-xs-12">
                <div class="col-lg-6 col-md-6 col-sm-6 col-xs-12 form-group">
                    <label>Email: <span class="text-danger">*</span></label>
                    <input id="regEmail" type="email" class="form-control" onchange="isMailUnique(this.value)"  name="email" placeholder="Type valid email">
                    <span id="mail_err" class="text-danger"></span>
                </div>
                <div class="col-lg-6 col-md-6 col-sm-6 col-xs-12 form-group">
                    <label>Mobile Number: <span class="text-danger">*</span></label>
                    <input type="number" class=" form-control number"  id="mobileNo" min="0" name="mobileNo" onkeypress="return preventDot(event);"/>
                    <span id="mobileNo_err" class="text-danger"></span>
                </div>
            </div>
            <div class="col-md-12 col-lg-12 col-sm-12 col-xs-12">
                <div class="col-lg-6 col-md-6 col-sm-6 col-xs-12 form-group">
                    <label>Telephone Number:</label>
                    <input id="TphoneNo" type="number" class="form-control" name="telephoneNo" onkeypress="return preventDot(event);">
                </div>
                <div class="col-lg-6 col-md-6 col-sm-6 col-xs-12 form-group">
                    <label>TPN Number:</label>
                    <input type="text" class="form-control" id="tpn" name="tpn">
                </div>
            </div>
            <div class="col-lg-12">
                <div class="col-lg-6 col-md-6 col-sm-6 col-xs-12 form-group">
                    <label>Office/Employer Name: </label>
                    <input type="text" class="form-control" name="employeeName">
                </div>
                <div class="col-lg-6 col-md-6 col-sm-6 col-xs-12 form-group">
                    <label>Office/Employer Address: </label>
                    <textarea class="form-control" name="employeeAddress"></textarea>
                </div>
            </div>
        </div>
    </div>
</div>

