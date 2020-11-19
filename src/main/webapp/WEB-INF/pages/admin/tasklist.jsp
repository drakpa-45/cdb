<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: user
  Date: 2/7/2020
  Time: 11:08 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title></title>
</head>
<body>

<div class="row">
    <div class="col-12">
        <div class="mb-0 page-header">
            <h1 class="page-title">
                Construction Development Board
            </h1>
        </div>

        <div class="card">
            <div class="card-header">
                <h3 class="card-title font-weight-bold">Task List</h3>
            </div>
            <div id="filterTasklistId">
                <div class="row">
                    <div class="col-12">
                        <div class="col-lg-4">
                            <div class="card">
                                <div class="card-header">
                                    <h3 class="card-title">Services</h3>
                                </div>
                                <div class="card-body">
                                    <div class="form-group">
                                        <li class="nav-item" style="color:black">
                                            <a href="javascript:void(0)" class="nav-link" data-toggle="dropdown" aria-expanded="false"><i class="fa fa-edit"></i> Contractor  <i class="fa fa-chevron-down"></i></a>
                                            <div class="dropdown-menu dropdown-menu-arrow" x-placement="bottom-start" style="position: absolute; transform: translate3d(12px, 54px, 0px); top: 0px; left: 0px; will-change: transform;">
                                                <a href="#" onclick="showtasklist()" class="dropdown-item ">New Contractor Application</a>
                                                <a href="#" onclick="showtasklist()" class="dropdown-item ">Renewal of Contractor </a>
                                                <a href="#" onclick="showtasklist()" class="dropdown-item ">Other Services</a>
                                                <a href="#" onclick="showtasklist()" class="dropdown-item ">Cancellation of contractor certificate</a>
                                            </div>
                                        </li>
                                        <br />
                                        <li class="nav-item" style="color:black">
                                            <a href="javascript:void(0)" class="nav-link" data-toggle="dropdown" aria-expanded="false"><i class="fa fa-edit"></i> Consultant  <i class="fa fa-chevron-down"></i></a>
                                            <div class="dropdown-menu dropdown-menu-arrow" x-placement="bottom-start" style="position: absolute; transform: translate3d(12px, 54px, 0px); top: 0px; left: 0px; will-change: transform;">
                                                <a href="#" onclick="showtasklist()" class="dropdown-item ">New Consultant Application</a>
                                                <a href="#" onclick="showtasklist()" class="dropdown-item ">Renewal of Consultant </a>
                                                <a href="#" onclick="showtasklist()" class="dropdown-item ">Other Services</a>
                                                <a href="#" onclick="showtasklist()" class="dropdown-item ">Cancellation of Consultant certificate</a>
                                            </div>
                                        </li>
                                        <br />
                                        <li class="nav-item" style="color:black">
                                            <a href="javascript:void(0)" class="nav-link" data-toggle="dropdown" aria-expanded="false"><i class="fa fa-edit"></i> Architect/Engineer  <i class="fa fa-chevron-down"></i></a>
                                            <div class="dropdown-menu dropdown-menu-arrow" x-placement="bottom-start" style="position: absolute; transform: translate3d(12px, 54px, 0px); top: 0px; left: 0px; will-change: transform;">
                                                <a href="<c:url value="/processEngineer"/>" onclick="showtasklist()" class="dropdown-item ">New Architect/Engineer Application</a>
                                                <a href="#" onclick="showtasklist()" class="dropdown-item ">Renewal of Architect/Engineer </a>
                                                <a href="#" onclick="showtasklist()" class="dropdown-item ">Cancellation of Architect/Engineer certificate</a>
                                            </div>
                                        </li>
                                        <br />
                                        <li class="nav-item" style="color:black">
                                            <a href="javascript:void(0)" class="nav-link" data-toggle="dropdown" aria-expanded="false"><i class="fa fa-edit"></i> Specialized Trade  <i class="fa fa-chevron-down"></i></a>
                                            <div class="dropdown-menu dropdown-menu-arrow" x-placement="bottom-start" style="position: absolute; transform: translate3d(12px, 54px, 0px); top: 0px; left: 0px; will-change: transform;">
                                                <a href="#" onclick="showtasklist()" class="dropdown-item ">New Specialiaed Trade Application</a>
                                                <a href="#" onclick="showtasklist()" class="dropdown-item ">Renewal of Specialiaed Trade </a>
                                                <a href="#" onclick="showtasklist()" class="dropdown-item ">Cancellation of Consultant certificate</a>
                                            </div>
                                        </li>
                                    </div>
                                </div>
                            </div>
                        </div>
                        <div class="col-lg-8">
                            <div class="row">
                                <div class="col-6">
                                    <div class="card">
                                        <div class="card-body text-center">
                                            <div class="h5">Contractor Application</div>
                                            <div class="display-4 font-weight-bold mb-4">30</div>
                                            <div class="progress progress-sm">
                                                <div class="progress-bar bg-blue" style="width: 28%"></div>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                                <div class="col-6">
                                    <div class="card">
                                        <div class="card-body text-center">
                                            <div class="h5">Consultant Application</div>
                                            <div class="display-4 font-weight-bold mb-4">80</div>
                                            <div class="progress progress-sm">
                                                <div class="progress-bar bg-red" style="width: 80%"></div>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                            </div>
                            <div class="row">
                                <div class="col-6">
                                    <div class="card">
                                        <div class="card-body text-center">
                                            <div class="h5">Architect/Engineer Application</div>
                                            <div class="display-4 font-weight-bold mb-4">50</div>
                                            <div class="progress progress-sm">
                                                <div class="progress-bar bg-green" style="width: 50%"></div>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                                <div class="col-6">
                                    <div class="card">
                                        <div class="card-body text-center">
                                            <div class="h5">Specialized Application</div>
                                            <div class="display-4 font-weight-bold mb-4">10</div>
                                            <div class="progress progress-sm">
                                                <div class="progress-bar bg-yellow" style="width: 20%"></div>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>

                    </div>
                </div>
            </div>
            <script type="text/javascript">
                function showtasklist(){
                    $('#tasklistId').show();
                    $('#filterTasklistId').hide();

                }
            </script>
            <div id="tasklistId" style="display: none">
                <div class="card-body">
                    <div class="card">
                        <div class="bg-azure card-header" style="background: #898da0;">
                            <h3 class="card-title text-white">Group Task</h3>
                        </div>
                        <div class="table-responsive">
                            <table class="card-table table table-bordered table-vcenter">
                                <thead>
                                <tr>
                                    <th>Sl No.</th>
                                    <th>Application Number</th>
                                    <th>Service Name</th>
                                    <th>Firm Name</th>
                                    <th>Contact Number</th>
                                    <th>Status</th>
                                    <th>Action Date</th>
                                </tr>
                                </thead>
                                <tbody class="text-center">
                                <tr>
                                    <td class="text-right"><span>1</span></td>
                                    <td>281_0000001</td>
                                    <td>Registration of Contractors </td>
                                    <td>D-nova Builder  </td>
                                    <td>17880455</td>
                                    <td><span class="status-icon bg-success"></span> Sumbitted </td>
                                    <td>2019-03-30</td>
                                </tr>
                                <tr>
                                    <td class="text-right"><span>1</span></td>
                                    <td>281_0000001</td>
                                    <td>Registration of Contractors </td>
                                    <td>D-nova Builder  </td>
                                    <td>17880455</td>
                                    <td><span class="status-icon bg-success"></span> Verified </td>
                                    <td>2019-03-30</td>
                                </tr>

                                </tbody>
                            </table>
                        </div>
                    </div>
                    <div class="card">
                        <div class="bg-azure-dark card-header" style="background: #5369d4;">
                            <h3 class="card-title text-white">My Task</h3>
                        </div>
                        <div class="table-responsive" >
                            <table class="card-table table table-bordered table-vcenter text-nowrap">
                                <thead>
                                <tr>
                                    <th>Sl No.</th>
                                    <th>Application Number</th>
                                    <th>Service Name</th>
                                    <th>Firm Name</th>
                                    <th>Contact Number</th>
                                    <th>Status</th>
                                    <th>Action Date</th>
                                </tr>
                                </thead>
                                <tbody class="text-center">
                                <c:forEach items="${tasklist}" var="task">
                                <tr>
                                    <td class="cssSerial"></td>
                                    <td><a href="<c:url value="/task_action?appNo=${task.applicationNo}"/>" class="font-weight-bold">${task.applicationNo}</a></td>
                                    <td>${task.serviceName}</td>
                                    <td>${task.firmName} </td>
                                    <td>${task.contactNo}</td>
                                    <td><span class="status-icon bg-success"></span> ${task.appStatus} </td>
                                    <td>${task.applicationDate}</td>
                                </tr>
                                </c:forEach>

                                </tbody>
                            </table>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>

<script type="text/javascript">
    $('#personalDetials').prop('class', 'active');
    $('#personalDetials').not('.active').addClass('disabled');
    $('#personal_tab').prop('class', 'tab-pane active');
    $("#personalh").css("background-color","#95b4de");

    $('#locationhead').not('.active').addClass('disabled');
    $('#locationhead').not('.active').find('a').removeAttr("data-toggle");

    $('#attachmenthead').not('.active').addClass('disabled');
    $('#attachmenthead').not('.active').find('a').removeAttr("data-toggle");

    function fetchpersonalDetails(cid){
        if(validatecid(cid)){
            $('#ciderr').html('');
            $('#name').val("Tshewang Tenzin");
            $('#gendermale').prop("checked",true);
            $('#dzongkhang').val("Trashi Yangtse");
            $('#gewog').val("Tongzhang");
            $('#village').val("wogkhar");
            $('#sccNo').val("123456789102");
        }
    }
    function validatecid(cid){
        //age verification
        //security validation
        var retval=true;
        if(cid.substring(0,1)=="3"){
            $('#ciderr').html('Cid starting with 3 is not allow');
            retval=false;
        }
        else if(cid.length!=11){
            $('#ciderr').html('Bhutanese CID should have 11 digit long');
            retval=false;
        }
        return retval;
    }
    $('#cidNo').click(function(){
        $('#ciderr').html('');
    });
    $('#mobile').click(function(){
        $('#mobileerr').html('');
    });
    function next(val){
        if(validatePersonal() && val=="personal"){
            $('#locationcontent').prop('class', 'tab-pane active');
            $('#personalDetials').removeClass("active");
            $('#personal_tab').removeClass("active");
            $('#personalcheck').html('<i class="fa fa-check"></i>');
            $("#personalh").css("background-color","rgb(42, 50, 62)");
            $("#locationh").css("background-color","#95b4de");
        }
        else if(validatelocation() && val=="location"){
            $('#attachmentcontent').prop('class', 'tab-pane active');
            $('#locationhead').removeClass("active");
            $('#locationcontent').removeClass("active");
            $('#locationcheck').html('<i class="fa fa-check"></i>');
            $("#personalh").css("background-color","rgb(42, 50, 62)");
            $("#locationh").css("background-color","rgb(42, 50, 62)");
            $("#attachmenthead").css("background-color","#95b4de");
        }
    }
    function validatelocation(){
        var retval=true;
        if($('#estbname').val()==""){
            $('#estbnameerr').html('Please provide business establishment name');
            retval=false;
        }
        if($('#location').val()==""){
            $('#locationerr').html('Please mention your exact location of business setup');
            retval=false;
        }
        if($('#dzongkhag').val()==""){
            $('#dzongkhagerr').html('Please select Dzongkhag of your business setup');
            retval=false;
        }
        if($('#gewogsection').val()==""){
            $('#gewogsectionerr').html('Please select Gewog of your business setup');
            retval=false;
        }
        if($('#vilagesection').val()==""){
            $('#vilagesectionerr').html('Please select Village of your business setup');
            retval=false;
        }
        if($('#postaladdry').prop('checked')==false && $('#postaladdrn').prop('checked')==false){
            $('#postalselecterr').html('Please choose how you wish to collect your certificate');
            retval=false;
        }
        if($('#postaladdry').prop('checked')==true && $('#postalAddr').val()==""){
            $('#postalAddrerr').html('Please mention your postal address from where you wish to collect certificate');
            retval=false;
        }
        return retval;
    }
    $('#estbname').click(function(){
        $('#estbnameerr').html('');
    });
    $('#location').click(function(){
        $('#locationerr').html('');
    });
    $('#dzongkhag').click(function(){
        $('#dzongkhagerr').html('');
    });
    $('#gewogsection').click(function(){
        $('#gewogsectionerr').html('');
    });
    $('#vilagesection').click(function(){
        $('#vilagesectionerr').html('');
    });
    $('#postaladdry').click(function(){
        $('#postalselecterr').html('');
    });
    $('#postaladdrn').click(function(){
        $('#postalselecterr').html('');
    });
    $('#postalAddr').click(function(){
        $('#postalAddrerr').html('');
    });
    function validatePersonal(){
        var retval=true;
        if($('#cidNo').val()==""){
            $('#ciderr').html('CID is required');
            retval=false;
        }
        if($('#mobile').val()==""){
            $('#mobileerr').html('Mobile Number is required');
            retval=false;
        }
        else if($('#mobile').val().length!="8"){
            $('#mobileerr').html('Mobile Number should have 8 digit');
            retval=false;
        }
        return retval;
    }
    function showpostaladdr(val){
        if(val=="Y"){
            $('#postaladdid').show();
        }
        else{
            $('#postaladdid').hide();
        }
    }
    function pretab(val){
        if(val=="location"){
            $('#personal_tab').prop('class', 'tab-pane active');
            $('#locationhead').removeClass("active");
            $('#locationcontent').removeClass("active");
            $("#personalh").css("background-color","#95b4de");
            $("#locationh").css("background-color","rgb(209, 211, 216)");
        }
        else if("attachment"==val){
            $('#locationcontent').prop('class', 'tab-pane active');
            $('#attachmenthead').removeClass("active");
            $('#attachmentcontent').removeClass("active");
            $("#locationh").css("background-color","#95b4de");
            $("#attachmenthead").css("background-color","rgb(209, 211, 216)");
        }
    }
    function getgewog(){
        $('#gewogsection').append("<option value='1'>Chokhor</option><option value='1'>Chumey</option><option value='1'>Chokhor</option><option value='1'>Tang</option><option value='1'>Ura</option><option value='1'>Sampheling/Bhalujora</option>");
    }
    function getvilage(){
        $('#vilagesection').append("<option value='1'>Chokhor</option><option value='1'>Chumey</option><option value='1'>Chokhor</option><option value='1'>Tang</option><option value='1'>Ura</option><option value='1'>Sampheling/Bhalujora</option>");
    }
    var inicount=0;
    function addmoreattachemnts(){
        inicount++;
        $('#fileadd').append('<div class="form-group" id="addedfile'+inicount+'"><div class="col-sm-6"><input type="file"  name="file1"></div><div class="col-sm-6"><button class="btn btn-danger" type="button" onclick="deleteate('+inicount+')"><i class="fa fa-times">Delete</i></button></div></div>');
    }
    function deleteate(id){
        $('#addedfile'+id).remove();
    }
    function submitform(){

    }
</script>

</body>
</html>
