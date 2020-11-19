<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: user
  Date: 1/3/2020
  Time: 9:30 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<div class="div-actual">
    <div class="card tab2" id="firmCate_">
        <div class="bg-blue card-status card-status-left"></div>
        <div class="card-header">
            <h3 class="card-title">Category Information</h3> >> <i>Please tick the checkbox to select a category</i>
        </div>
        <div class="card-body">
            <div class="col-md-12 col-lg-12 col-sm-12 col-xs-12">
                <table id="spCCTbl" class="table table-bordered table-hover">
                    <thead style="background-color: #F2F2F2">
                    <tr>
                        <th></th>
                        <th>Category</th>
                    </tr>
                    </thead>
                    <tbody>
                    <c:forEach items="${categoryList}" var="category" varStatus="i">
                        <tr>
                            <td><input class="form-control categoryCheck" type="checkbox" name="categories[${i.index}].appliedCategoryId" value="${category.id}" style="width: 17px; height: 17px;"></td>
                            <td>${category.code}${category.name}</td>
                        </tr>
                    </c:forEach>
                    </tbody>
                </table>
            </div>
        </div>
    </div>
</div>
<div class="col-lg-12 form-group">
    <button type="button" onclick="previousTab('general_Information', 'category_details')" class="btn btn-azure col-lg-offset-9">
        <i class="fa fa-arrow-circle-left"></i> &nbsp; Back
    </button>
    <button type="button" id="btnValCCNext" class="btn btn-primary">Next &nbsp;
        <i class="fa fa-arrow-circle-right"></i>
    </button>
</div>

