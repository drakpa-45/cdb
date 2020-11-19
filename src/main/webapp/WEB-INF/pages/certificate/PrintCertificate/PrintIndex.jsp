<%@ page language="java" %>
<%@taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<script src="<%=request.getContextPath()%>/Scripts/jquery-1.4.2.min.js" type="text/javascript"></script>
<script src="<%=request.getContextPath()%>/Scripts/JqueryAjaxFormSubmit.js" type="text/javascript"></script>
<script src="<%=request.getContextPath()%>/Scripts/jquery.form.js" type="text/javascript"></script>
<script src="Scripts/itpoverlay.js" type="text/javascript"></script>
<link rel="stylesheet" type="text/css" href="css/styleAjax.css" />
<script language="javascript" type="text/javascript">
	var context = '<%= request.getContextPath() %>';
	function sortByService(headerId){
		var overlay = new ItpOverlay();
        overlay.show("body");
        setTimeout ( 
          function(){
      	   overlay.hide("body");
          }
       , 50 );
		var serviceId = headerId;
		$.ajax
		({
			type : "POST",
			url : "<%=request.getContextPath()%>/getApprovedListPath.do?method=getApprovedApplicationListByServiceAction&serviceId="+serviceId,
			data : $('form').serialize(),
			cache : false,
			dataType : "html",
			success : function(responseText) 
			{
				$("#getApprovedData").html(responseText);
			}
		});
	}
	function getByApplicationID()
	{
		var applicationNo = $('#applicationNo').val(); 
		
		var overlay = new ItpOverlay();
        overlay.show("body");
        setTimeout ( 
          function(){
      	   overlay.hide("body");
          }
       , 8000 );
        
        $.ajax
		({
			type : "POST",
			url : "<%=request.getContextPath()%>/getApprovedListPath.do?method=getApprovedApplicationListByAppIdAction&applicationNo="+applicationNo,
			data : $('form').serialize(),
			cache : false,
			dataType : "html",
			success : function(responseText) 
			{
				$("#getApprovedData").html(responseText);
			}
		});
	}
</script>
<body>
<jsp:include page="/Pages/cdb/common/header.jsp" />
<jsp:include page="/Pages/cdb/common/linkpage.jsp"></jsp:include>
<html:form action="/getApprovedApplicationListPath.do" method="post" styleId="serviceForm" enctype="multipart/form-data"> 
<div  class="content-wrapper" >
     <div class="container">
			<div class="row"  >
			  <div class="col-md-12 col-lg-12">
			  <h4 class="page-head-line">
					Construction Development Board
					<small>
						<i class="ace-icon fa fa-angle-double-right"></i>
						Certificate Prints
					</small>
				</h4>
			  </div>
			</div> 
			<br/>
			<div class="row"  >
				<div class="form-group">
	        		<label class="col-lg-2 control-label" >
	        			Select Service :
	        		</label>
	        	</div>	
			  <div class="col-lg-10">
			      <html:select property="streamIdService" styleClass="form-control" styleId="streamIdService" onchange="sortByService(this.value)">
			    	  <html:option value="0">-Select-</html:option>
			    	  <html:optionsCollection name="serviceForm" property="streamListService" label="headerName" value="headerId" />
			      </html:select> 
			  </div>
			 </div>
			 <br />
			 <div class="row"  >
			   <div class="col-md-12 ">
			      <div id="getApprovedData">
			   </div>
			 </div> 
			</div> 
	</div>
</div>			
 </html:form>
 <jsp:include page="/Pages/cdb/common/footer.jsp"></jsp:include>
</body>