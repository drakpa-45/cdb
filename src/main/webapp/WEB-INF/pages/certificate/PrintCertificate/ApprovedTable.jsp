<%@ page language="java" %>
<%@taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%String service=(String)request.getAttribute("serviceID"); %>
<script language="javascript" type="text/javascript">
	var context = '<%= request.getContextPath() %>';
	
	function viewApplicationForm(appNo)
	{
		var overlay = new ItpOverlay();
        overlay.show("body");
        setTimeout ( 
          function(){
      	   overlay.hide("body");
          }
       , 6000 );
        
		$.ajax
		({
			type : "POST",
			url : "<%=request.getContextPath()%>/ShowApprovalAction.do?appNo="+appNo,
			data : $('form').serialize(),
			cache : false,
			dataType : "html",
			success : function(responseText) 
			{
				$("#getApprovedData").html(responseText);
			}
		});
	}
	function printCertificates(appNo,servicefor){
		if('<%=service%>'=="Contractors Application"){
			window.open('<%=request.getContextPath()%>/contractorRenewalAction.do?method=printContractorCertificateAction&appNo='+appNo,'_blank');
		}
		if('<%=service%>'=="Architests/Engineers Application"){
			window.open('<%=request.getContextPath()%>/architectsPaths.do?method=printArchitectCertificateAction&appNo='+appNo+'&role='+servicefor,'_blank');
		}
		if('<%=service%>'=="Consultants Application"){
			window.open('<%=request.getContextPath()%>/regConsultants.do?method=printCertificate&applicationId='+appNo,'_blank');	
		}
		if('<%=service%>'=="Specialized Traders Application"){
			window.open('<%=request.getContextPath()%>/specializedTradePaths.do?method=printTradeCertificateAction&applicationId='+appNo,'_blank');
		}
	}
</script>

<html:form action="/getApprovedApplicationListPath.do" method="post" styleId="serviceForm" enctype="multipart/form-data"> 

<div class="row">
	<div class="col-md-12">
		<div class="panel panel-info">
			<div class="panel-heading">
				<%=service%>
			</div>
			<div class="panel-body">
		    <div class="alert alert-info" style="display: none;" id="alertGroupId">
           		<i class="fa fa-info-circle fa-lg"><strong><span id="tableId"></span></strong> </i> 
           	</div>
			<div class="table-responsive">
				<table id="group-task" class="table table-striped table-bordered table-hover">
					<thead>
						 <TR>
						  <th>Sl/No</th>
						  <th>Application Number</th>
						  <th>CDB Number</th>
						  <th>Application Type</th>
						  <th>Approved Date</th>
						  <th>Status</th>
						  <th>Firm Name</th>
				    	  <th>Contact No</th>
						  <th>&nbsp;</th>
						 </TR>
					</thead>
					<tbody>
				 		<logic:iterate name="approvedDTO" id="timeAlloctionId" type="bt.gov.g2c.framework.common.dto.GetApprovedApplicationDTO" indexId="index"> 
					   		<tr>
						    	<td>
						     		<%=index.intValue()+1 %>
						    	</td>
						    	<td>
						     		<bean:write name="timeAlloctionId" property="applicationNo"/>
						    	</td>
						    	<td>
						     		<bean:write name="timeAlloctionId" property="cdbNo"/>
						    	</td>
					   			<td>
						     		<bean:write name="timeAlloctionId" property="serviceName"/>
					    		</td>
						    	<td>
						     		<bean:write name="timeAlloctionId" property="approvalDate"/>
						    	</td>
						    	<td>
						     		<bean:write name="timeAlloctionId" property="status"/>
						    	</td>
						    	<td>
						     		<bean:write name="timeAlloctionId" property="firmName"/>
						    	</td>
						    	<td>
						     		<bean:write name="timeAlloctionId" property="contactNo"/>
						    	</td>
						    	<td>
<!--							     <a href="#" onclick="viewApplicationForm('<bean:write name="timeAlloctionId" property="applicationNo"/>')">-->
<!--								  <strong>View Form</strong>	-->
<!--								 </a>-->
							  		<a href="#" onclick="printCertificates('<bean:write name="timeAlloctionId" property="cdbNo"/>','<bean:write name="timeAlloctionId" property="servicefor"/>')">
							  			<strong>PRINT</strong>	
							 		</a>
						    	</td>
					   		</tr>
					  	</logic:iterate>
					</tbody>
				</table>
			</div>
			</div>
		</div>
	</div>
</div>
<hr />
<div class="form-group">
	<label class="col-lg-6" >
			Please provide cdb number: This section is use only if the information is there with cdb and not applied any application from g2c.
	</label>
	<div class="col-md-2">
		<select class="form-control" name="cdbtype" id="cdbtype" onclick="removeerr('cdbtypeerr')">
			<option value="">select</option>
			<option value="C">Contractor</option>
			<option value="con">Consultant</option>
			<option value="A">Architect</option>
			<option value="sp">Sp Trade</option>
		</select>
		<span class="text-danger" id="cdbtypeerr"></span>
	</div>
	<div class="col-md-2">
		<input type="text" name="cdbno" id="cdbno" class="form-control" onclick="removeerr('cdbnoerr')">
		<span id="cdbnoerr" class="text-danger"></span>
	</div>
	<div class="col-md-2">
		<button type="button" class="btn btn-primary" onclick="validateandprint()">Print</button>
	</div>
</div>
<script type="text/javascript">
$(document).ready(function() 
{
	 $('#group-task').DataTable({
         responsive: true
 });  
 $('select[name=group-task_length]').append($("<option></option>").attr("value","-1").text("All")); 
});
function validateandprint(){
	if(validatethis()){
		if($('#cdbtype').val()=="C"){
			window.open('<%=request.getContextPath()%>/contractorRenewalAction.do?method=printContractorCertificateAction&appNo='+$('#cdbno').val(),'_blank');
		}
		if($('#cdbtype').val()=="A"){
			window.open('<%=request.getContextPath()%>/architectsPaths.do?method=printArchitectCertificateAction&appNo='+$('#cdbno').val()+'&role=ARCHITECT','_blank');
		}
		if($('#cdbtype').val()=="con"){
			window.open('<%=request.getContextPath()%>/regConsultants.do?method=printCertificate&applicationId='+$('#cdbno').val(),'_blank');	
		}
		if($('#cdbtype').val()=="sp"){
			window.open('<%=request.getContextPath()%>/specializedTradePaths.do?method=printTradeCertificateAction&applicationId='+$('#cdbno').val(),'_blank');
		}
	}
}
function validatethis(){
	var rettype=true;
	if($('#cdbtype').val()==""){
		$('#cdbtypeerr').html('Please select type');
		rettype=false;
	}
	if($('#cdbno').val()==""){
		$('#cdbnoerr').html('Please provide cdb number');
		rettype=false;
	}
	return rettype;
}
function removeerr(errId){
	$('#'+errId).html('');
}
</script>
</html:form>
