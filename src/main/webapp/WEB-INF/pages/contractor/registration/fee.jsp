<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: user
  Date: 10/29/2019
  Time: 8:40 PM
  To change this template use File | Settings | File Templates.
--%>
<div class="form-group div-actual">
    <table id="feeTbl" class="table table-striped table-bordered table-hover">
        <thead>
        <tr>
            <th>Classification</th>
            <th>Validity (years)</th>
            <th> Fees per Category (Nu.)</th>
        </tr>
        </thead>
        <tbody>
        <c:forEach items="${feeStructureList}" var="fee">
            <tr>
                <td>${fee.name}</td>
                <td>2</td>
                <td>${fee.registrationFee}</td>
            </tr>
        </c:forEach>
        </tbody>
    </table>
    <b class="text-azure">Note: The license validity for non-Bhutanese contractor is One year</b>
</div>
<div class="form-group">
    <div class="col-md-offset-11 col-lg-offset-10 col-xs-offset-10">
        <button type="button"
                onclick="nextTab('fees_structure','general_Information')"
                class="btn btn-primary">Next &nbsp;
            <i class="fa fa-arrow-circle-right"></i>
        </button>
    </div>
</div>

