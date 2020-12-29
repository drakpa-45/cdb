<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: user
  Date: 1/3/2020
  Time: 9:30 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%--<form name="contractorCCForm" contractor="" method="post" class="globalForm" id="contractorCCForm">--%>
<div class="div-actual">
    <table id="contractorCCTbl" class="table table-bordered table-hover">
        <thead style="background-color: #F2F2F2">
        <tr>
            <td></td>
            <td>Category</td>
            <td>Apply for Class</td>
        </tr>
        </thead>
        <tbody>
        <c:forEach items="${categoryList}" var="category" varStatus="i">
            <tr>
                <td><input class="form-control categoryCheck" type="checkbox" name="categories[${i.index}].projectCateID" value="${category.id}"
                    <%--onclick="EnableDisableTextBox(this)"--%> style="width: 17px; height: 17px;"></td>
                <td>${category.code}-${category.name} </td>
                <td><select name="categories[${i.index}].appliedClassID" class="form-control appliedClassID" disabled>
                    <option value="">-Select-</option>
                    <c:forEach items="${classification}" var="c">
                        <c:choose>
                            <c:when test="${(category.code eq 'W2') && (c.value eq  '0c14ebea-c3eb-11e4-af9f-080027dcfac6')}">
                                <option value="${c.value}">${c.text}</option>
                            </c:when>
                            <c:when test="${(category.code ne 'W2') && (c.value ne '0c14ebea-c3eb-11e4-af9f-080027dcfac6')}">
                                <option value="${c.value}">${c.text}</option>
                            </c:when>
                        </c:choose>
                    </c:forEach>
                </select></td>
            </tr>
        </c:forEach>
        </tbody>
    </table>
</div>
<div class="col-lg-12 form-group">
    <button type="button" onclick="backTab('category_details', 'general_Information')"
            class="btn btn-azure col-lg-offset-9">
        <i class="fa fa-arrow-circle-left"></i> &nbsp; Back
    </button>
    <button type="button" id="btnValCCNext" class="btn btn-primary">Next &nbsp;
        <i class="fa fa-arrow-circle-right"></i>
    </button>
</div>

<%--</form>--%>
