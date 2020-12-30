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
    <table id="consultantCCTbl" class="table table-bordered table-hover">
        <thead style="background-color: #F2F2F2">
        <tr>
            <td>Category</td>
            <td>Apply for Services</td>
        </tr>
        </thead>
        <tbody>
        <c:forEach items="${categoryList}" var="category" varStatus="i">
            <tr>
                <td>
                    <input type="checkbox" style="zoom:1.6" class=" categoryCheck" id="asone" value="${category.id}" name="categories[${i.index}].serviceCateID"> &nbsp;
                    ${category.code}-${category.name}
                </td>
                <td>
                    <c:forEach items="${classification}" var="c">
                        <c:choose>
                            <c:when test="${(category.code eq 'A') && (c.value eq 'e6372584-bc15-11e4-81ac-080027dcfac6')}">
                                <a href="javascript:void(0)" style="color: #006699" title='${c.obj1}' data-toggle="tooltip" data-placement="top" class="tooltipCSSSelector"><i class="fa fa-question-circle"></i></a>
                                <%--<input type="hidden" id="asone1" value="${c.text}" name="categories[${i.index}].appliedServices">--%>
                                <input type="checkbox" id="asone" value="${c.id}" class="ticked appliedClassID" disabled name="categories[${i.index}].appliedServiceID">${c.text} &nbsp; &nbsp; &nbsp;
                            </c:when>
                            <c:when test="${(category.code eq 'C') && (c.value eq 'f39b9245-bc15-11e4-81ac-080027dcfac6')}">
                                <a href="javascript:void(0)" style="color: #006699" title='${c.obj1}' data-toggle="tooltip" data-placement="top" class="tooltipCSSSelector"><i class="fa fa-question-circle"></i></a>
                                <input type="checkbox" id="asone" value="${c.id}" class="ticked appliedClassID" disabled name="categories[${i.index}].appliedServiceID">${c.text} &nbsp; &nbsp; &nbsp;
                            </c:when>
                            <c:when test="${(category.code eq 'E') && (c.value eq  'fb2aa1a7-bc15-11e4-81ac-080027dcfac6')}">
                                <a href="javascript:void(0)" style="color: #006699" title='${c.obj1}' data-toggle="tooltip" data-placement="top" class="tooltipCSSSelector"><i class="fa fa-question-circle"></i></a>
                                <input type="checkbox" id="asone" value="${c.id}" class="ticked appliedClassID" disabled name="categories[${i.index}].appliedServiceID">${c.text} &nbsp; &nbsp; &nbsp;
                            </c:when>
                            <c:when test="${(category.code eq 'S') && (c.value eq  '2adfae00-be66-11e9-9ac2-0026b988eaa8')}">
                                <a href="javascript:void(0)" style="color: #006699" title='${c.obj1}' data-toggle="tooltip" data-placement="top" class="tooltipCSSSelector"><i class="fa fa-question-circle"></i></a>
                                <input type="checkbox" id="asone" value="${c.id}" class="ticked appliedClassID" disabled name="categories[${i.index}].appliedServiceID">${c.text} &nbsp; &nbsp; &nbsp;
                            </c:when>
                        </c:choose>
                    </c:forEach>
                </td>
            </tr>
        </c:forEach>
        </tbody>
    </table>
</div>
<div class="col-lg-12 form-group">
    <button type="button" id="btn3" onclick="backTab('category_details', 'general_Information')" class="btn btn-azure col-lg-offset-9">
        <i class="fa fa-arrow-circle-left"></i> &nbsp; Back
    </button>
    <button type="button" id="btnValCCNext" class="btn btn-primary">Next &nbsp;
        <i class="fa fa-arrow-circle-right"></i>
    </button>
</div>
