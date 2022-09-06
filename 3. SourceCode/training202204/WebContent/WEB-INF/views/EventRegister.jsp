<%@page import="java.util.HashMap"%>
<%@page import="entity.RegistEvents"%>
<%@page import="entity.VoteOption"%>
<%@page import="entity.MasterEventType"%>
<%@page import="entity.Department"%>
<%@page import="entity.Events"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jstl/fmt_rt" prefix="fmt"%>
<!DOCTYPE html>
<html lang="en">

<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<meta http-equiv="X-UA-Compatible" content="ie=edge">
<title>Event Register</title>
<script src="https://kit.fontawesome.com/96ed116afc.js"
	crossorigin="anonymous"></script>
<!-- core:css -->
<link rel="stylesheet" href="./resources/assets/vendors/core/core.css">
<!-- endinject -->
<!-- plugin css for this page -->
<link rel="stylesheet"
	href="./resources/assets/vendors/bootstrap-datepicker/bootstrap-datepicker.min.css">
<!-- end plugin css for this page -->
<!-- inject:css -->
<link rel="stylesheet"
	href="./resources/assets/fonts/feather-font/css/iconfont.css">
<link rel="stylesheet"
	href="./resources/assets/vendors/flag-icon-css/css/flag-icon.min.css">
<!-- endinject -->
<!-- Layout styles -->
<link rel="stylesheet" href="./resources/assets/css/demo_5/style.css">
<!-- End layout styles -->
<link rel="shortcut icon" href="./resources/assets/images/favicon.png" />
<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css"
	rel="stylesheet"
	integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC"
	crossorigin="anonymous">
<script
	src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.bundle.min.js"
	integrity="sha384-MrcW6ZMFYlzcLA8Nl+NtUVF0sA7MsXsP1UyJoMp4YLEuNSfAP+JcXn/tWtIaxVXM"
	crossorigin="anonymous"></script>
</head>

<body>
	<%@ include file="header.jsp"%>
	<div class="main-wrapper">
		<!-- partial -->
		<div class="page-wrapper">
			<div class="card">
				<div class="">
					<div
						class="d-flex justify-content-between align-items-center flex-wrap grid-margin">
						<div>
							<h4 class="mb-3 mb-md-0">Welcome to Usol-V</h4>
						</div>
					</div>
					<ul class="nav nav-pills mb-3" id="pills-tab" role="tablist">
						<li class="nav-item" role="presentation">
							<button class="nav-link active" id="pills-home-tab"
								data-bs-toggle="pill" data-bs-target="#pills-home" type="button"
								role="tab" aria-controls="pills-home" aria-selected="true">Event
								Info</button>
						</li>
						<li class="nav-item" role="presentation">
							<button class="nav-link" id="pills-profile-tab"
								data-bs-toggle="pill" data-bs-target="#pills-profile"
								type="button" role="tab" aria-controls="pills-profile"
								aria-selected="false">Vote Info</button>
						</li>
					</ul>
					<div class="tab-content" id="pills-tabContent">
						<div class="tab-pane fade show active" id="pills-home"
							role="tabpanel" aria-labelledby="pills-home-tab">

							<div class="row">
								<div class="row">
									<div class="col-md-6 grid-margin stretch-card">
										<%List<Events> events = (List<Events>) request.getAttribute("eventDetails"); 
										for(Events e : events){
										%>
										<div class="card">
											<div class="card-body">
												<h6 class="card-title">1. Event information</h6>
												<form class="forms-sample">
													<div class="form-group row">
														<label class="col-sm-2 col-form-label"
															for="exampleInputUsername1">Event name</label>
														<div class="col-sm-10">
															<input name="eventName" type="text" class="form-control"
																id="exampleInputUsername1" autocomplete="off"
																placeholder="Event name" value="<%=e.getEventName()%>">
														</div>
													</div>
													<div class="form-group row">
														<label class="col-sm-2 col-form-label" for="" >Description</label>
														<div class="col-sm-10">
															<textarea class="form-control" id="" rows="3"><%=e.getDescription() %></textarea>
														</div>
													</div>
													<div class="row">
														<div class="col-sm-2"></div>
														<div class="col-5">
															<select id="departmentSelect" class="form-select"
																aria-label="Default select example">
																<option value="default">Bộ phận</option>
																<%
																	List<Department> departmentList = (List<Department>) request.getAttribute("departmentList");
																	for (Department department : departmentList) {
																%>
																<option value="<%=department.getId()%>"><%=department.getDepartName()%></option>
																<%
																	}
																%>
															</select> &nbsp;
															<div>
																<input id="btnAddDepartment" type="button"
																	class="btn btn-primary float-right" value="Add">
															</div>
														</div>
														<div class="float-right col-5">
															<select id="departmentMember" class="form-select"
																aria-label="Default select example">
																<option>Member</option>

															</select> &nbsp;
															<div>
																<input type="button" id="btnAddMember"
																	class="btn btn-primary float-right" value="Add">
															</div>
														</div>
													</div>
													<div class="form-group row"
														style="height: 200px; overflow: auto;">
														<label class="col-sm-2 col-form-label" for="">Members</label>
														<div class="table-responsive col-sm-10">
															<table id="" class="table">
																<tbody id="listMembersAdd">
																</tbody>
															</table>
														</div>
													</div>
													<div class="form-group row">
														<label class="col-sm-2 col-form-label"
															for="exampleInputUsername1">Place</label>
														<div class="col-sm-10">
															<textarea class="form-control" id="" rows="3"><%=e.getPlace() %></textarea>
														</div>
													</div>
													<div class="form-group row">
														<div class="col-sm-2">
															<label class="col-form-label" for="">Event Type</label>
														</div>
														<div class="col-sm-5">
															<select name="masterEventType" class="form-select"
																aria-label="Default select example">
																<%
																	List<MasterEventType> masterEventTypes = (List<MasterEventType>) request
																			.getAttribute("masterEventTypeList");
																	for (MasterEventType masterEventType : masterEventTypes) {
																%>
																<option value="<%=masterEventType.getId()%>"><%=masterEventType.getEventType()%></option>
																<%
																	}
																%>
															</select>
														</div>
													</div>
													<div class="form-group row">
														<div class="col-sm-2">
															<label class="col-form-label" for="">Option Type</label>
														</div>
														<div class="col-sm-5">
															<select name="optionType" id="selectVoteOption"
																onchange="displayVoteOption(this)" class="form-select"
																aria-label="Default select example">
																<option selected value="1" id="optionType1">Chọn
																	1</option>
																<option value="2" id="optionType2">Chọn 2 hoặc
																	nhiều</option>
															</select>
														</div>
													</div>
													<div class="row">
														<div class="col-sm-2">
															<label class="col-form-label" for="">Start Time</label>
														</div>
														<div class="col-sm-5">
															<div class="form-group">
																<input name="startHour" type="time" class="form-control"
																	placeholder="Start time" value="<%=e.getStartHour() %>">
															</div>
														</div>
														<div class="col-sm-5">
															<div class="form-group">
																<input name="startDate" type="date" class="form-control"
																	placeholder="Start date" value="<%=e.getStartDate()%>">
															</div>
														</div>
													</div>
													<div class="row">
														<div class="col-sm-2">
															<label class="col-form-label" for="">End Time</label>
														</div>
														<div class="col-sm-5">
															<div class="form-group">
																<input name="endHour" type="time" class="form-control"
																	placeholder="End time" value="<%=e.getEndHour()%>">
															</div>
														</div>
														<div class="col-sm-5">
															<div class="form-group">
																<input name="endDate" type="date" class="form-control"
																	placeholder="End date" value="<%=e.getEndDate() %>">
															</div>
														</div>
													</div>
												</form>
											</div>
										</div>
										<%} %>
									</div>
									<div id="voteOption" style="pointer-events: none;"
										class="col-md-6 grid-margin stretch-card">
										<div class="card">
											<div class="card-body">
												<h6 class="card-title">2. Vote option</h6>
												<form action="#" class="forms-sample">
													<div class="row">
														<div class="col-sm-6">
															<div class="form-group">
																<button type="button" class="btn btn-primary"
																	id="btnAdd_timeoption">Add time option</button>
															</div>
															<div class="form-group row" id="addItem_timeoption"
																style="height: 400px; overflow: auto;"></div>
														</div>
														<div class="col-sm-6">
															<div class="form-group">
																<input type="button" value="Add place option"
																	id="btnAddPlaceOption" class="btn btn-primary"
																	placeholder="End time">
															</div>
															<div id="addItem_placeoption"
																style="height: 400px; overflow: auto;"></div>
														</div>
													</div>
												</form>
											</div>
										</div>
									</div>
									<div></div>
								</div>
							</div>
							<div class="d-flex justify-content-center">
								<table>
									<tr>
										<td>
											<button  class="btn btn-primary">Update Event</button>
										</td>
										<td><a
											href="DeleteEvents.htm?id=<%=request.getParameter("EventId")%>"
											onclick="return confirm('Do you want to keep deleting?')">
												<button class="btn btn-primary">Delete Event</button>
										</a></td>
										<td><a
											href="UpdateEventsStatus.htm?id=<%=request.getParameter("EventId")%>">
												<button class="btn btn-primary">Close Event</button>
										</a></td>
										<td>
										<a href="Login.htm">
											<button class="btn btn-primary">Back Event List</button>
										</a>
										</td>
									</tr>
								</table>
							</div>
						</div>
						<div class="tab-pane fade" id="pills-profile" role="tabpanel"
							aria-labelledby="pills-profile-tab">
							<div class="col-md-12" style="padding-right: 0; padding-left: 0;">
								<div class="card">
									<%
										List<VoteOption> voteOptionList = (List<VoteOption>) request.getAttribute("voteOptionList");
										int size = voteOptionList.size();
										List<RegistEvents> registEvents = (List<RegistEvents>) request.getAttribute("registEventDetail");
										HashMap<Long, Users> usersList = new HashMap<Long, Users>();
										HashMap<Long, VoteOption> voteOptions = new HashMap<Long, VoteOption>();
										HashMap<Long, RegistEvents> registList = new HashMap<Long, RegistEvents>();
										HashMap<Long, RegistEvents> registListByUsersId = new HashMap<Long, RegistEvents>();
										for (RegistEvents registEvent : registEvents) {
											usersList.put(registEvent.getUsers().getId(), registEvent.getUsers());
											voteOptions.put(registEvent.getId(), registEvent.getVoteOption());
											registList.put(registEvent.getId(), registEvent);
											registListByUsersId.put(registEvent.getUsers().getId(), registEvent);
										}
										int indexJoined = 0;
										int attachChilden = 0;
										int attachAdult = 0;
										for (long a : registListByUsersId.keySet()) {
											attachChilden += registListByUsersId.get(a).getAttachedPersonChild();
											attachAdult += registListByUsersId.get(a).getAttachedPersonAdult();
											if (registListByUsersId.get(a).getIsJoined()) {
												indexJoined++;

											}
										}
									%>
									<div class="table-responsive pt-3" style="padding: 1rem;">
										<table class="table table-bordered" style="border-top: none">
											<thead>
												<tr class="border-0">
													<td colspan="3" style="border: none"></td>
													<td class="bg-primary; background-color:none"
														style="border: 1px solid #e8ebf1; background-color: #b6bbfa">
														Thong Ke Nhanh</td>
													<td style="border: 1px solid #e8ebf1;"><%=indexJoined%></td>
													<%
														int index = 0;
														for (VoteOption vote : voteOptionList) {
													%>
													<td id="statistics<%=index++%>"
														style="border: 1px solid #e8ebf1;"></td>
													<%
														}
													%>
													<td style="border: 1px solid #e8ebf1;"><%=attachAdult%></td>
													<td style="border: 1px solid #e8ebf1;"><%=attachChilden%></td>
												</tr>
												<tr style="border-top: none; border-bottom: none">
													<td colspan="5" style="border: 0"></td>
													<%
														for (VoteOption voteOption : voteOptionList) {
															if (voteOption.getStartDate() != null) {
													%>
													<td style="border: 0"></td>
													<%
														}
															if (voteOption.getPlace() != null) {
													%>
													<td style="border: 0; height: 10vh;"><img
														src="<%=request.getServletContext().getRealPath("/images/")+voteOption.getOptionImage()%>"
														alt=""
														style="width: 100%; height: 100%; border-radius: 0%"
														width="100%" height="100%"></td>
													<%
													}
													}
													%>
												</tr>
												<tr style="border: 1px solid #e8ebf1">
													<th>No</th>
													<th>Member ID</th>
													<th>Full name</th>
													<th>Email</th>
													<th>Join</th>
													<%
														for (VoteOption voteOption : voteOptionList) {
															if (voteOption.getStartDate() != null) {
													%>
													<th><fmt:formatDate type="date"
															value="<%=voteOption.getStartDate()%>"></fmt:formatDate></th>
													<%
														}
															if (voteOption.getPlace() != null) {
													%>
													<th><%=voteOption.getPlace()%></th>
													<%
														}
														}
													%>
													<th>Attack Adult</th>
													<th>Attack Children</th>
													<th>Note</th>
													<th>Action</th>
												</tr>
											</thead>
											<%
												int count = 0;
											%>
											<tbody>
												<%
													int x = 0;
													int indexVote = 0;
													for (Long i : usersList.keySet()) {
														count++;
												%>
												<tr
													<%
													Users users = (Users) session.getAttribute("users");
													if(!users.getId().equals(usersList.get(i).getId())){
													%>
													style="pointer-events: none;"
													<%	
													}
												%> class="table">
													<input style="display: none;" id="usersId<%=indexVote %>"
														value="<%=usersList.get(i).getId()%>">
													<td><%=count%></td>
													<td><%=usersList.get(i).getId()%></td>
													<td><%=usersList.get(i).getFullName()%></td>
													<td><%=usersList.get(i).getEmail()%></td>
													<%
														for (Long j : registListByUsersId.keySet()) {
																if (registListByUsersId.get(j).getUsers().getId().equals(usersList.get(i).getId())) {
													%>
													<td><select id="isJoined_<%=usersList.get(i).getId() %>"
														style="width: 70px;" name="joined">
															<%
																String joined = registListByUsersId.get(j).getIsJoined() ? "O" : "X";
															%>
															<option
																value="<%=registListByUsersId.get(j).getIsJoined()%>"><%=joined%></option>
															<%
															if(registListByUsersId.get(j).getIsJoined())
															{
																%>
															<option value="false">X</option>
															<%
															}
															else{
																%>
															<option value="true">O</option>
															<%
															}
															
															%>
													</select></td>
													<%
														}

															}
													%>

													<%
														if (size != 0) {
																int y = 0;
																for (Long m : registList.keySet()) {
																	if (registList.get(m).getUsers().getId().equals(i)) {
													%>
													<td><select class="listVote_<%=usersList.get(i).getId()%>" id="isVoted_<%=usersList.get(i).getId()%>-registEvents_<%=registList.get(m).getId() %>" name="isVoted"
														style="width: 70px;">
															<%
																String isVoted = registList.get(m).getVoted() ? "O" : "X";
															%>
															<option id="optionVoted<%=x%><%=y%>"
																value="<%=registList.get(m).getVoted()%>"><%=isVoted%></option>
															<%
															if(registList.get(m).getVoted())
															{
																%>
															<option value="false">X</option>
															<%
															}
															else{
																%>
															<option value="true">O</option>
															<%
															}
															
															%>
													</select></td>
													<%
														y++;
																	}

																}
															}
													%>


													<%
														for (Long k : registListByUsersId.keySet()) {
																if (registListByUsersId.get(k).getUsers().getId().equals(usersList.get(i).getId())) {
													%>
													<td><input id="attachedPersonAdult_<%=usersList.get(i).getId() %>" style="width: 90px;" class="form-control"
														type="number"
														value="<%=registListByUsersId.get(k).getAttachedPersonAdult()%>">
													</td>
													<td><input id="attachedPersonChild_<%=usersList.get(i).getId() %>" style="width: 100px;" class="form-control"
														type="number"
														value="<%=registListByUsersId.get(k).getAttachedPersonChild()%>">
													</td>
													<td><textarea id="note_<%=usersList.get(i).getId() %>" class="" rows="3" cols="20"></textarea>
													</td>
													<td><a href=""
														onclick="return confirm('Do you want to keep deleting?');"
														" class="btn btn-sm btn-outline-secondary badge border-0">
															<i class="fa fa-trash"></i>
													</a></td>
													<%
														}

															}
													%>
												</tr>
												<%
													x++;
													indexVote++;

													}
												%>
											</tbody>
										</table>
									</div>
								</div>
							</div>

							<div>
								<div class="d-flex justify-content-center">
									<table>
										<tr>
											<td>
												<button id="btnUpdateEvent" class="btn btn-primary">Update Event</button>
											</td>
											<%
												List<Events> eventsDetail = (List<Events>) request.getAttribute("eventsDetail");

												if (eventsDetail.get(0).getUsers().getUserName().equals(session.getAttribute("username"))) {
											%>
											<td><a
												href="DeleteEvents.htm?id=<%=request.getParameter("EventId")%>"
												onclick="return confirm('Do you want to keep deleting?')">
													<button class="btn btn-primary">Delete Event</button>
											</a></td>
											<td><a
												href="UpdateEventsStatus.htm?id=<%=request.getParameter("EventId")%>">
													<button class="btn btn-primary">Close Event</button>
											</a></td>
											<%
												}
											%>
											<td><a href="Login.htm">
													<button class="btn btn-primary">Back Event List</button>
											</a></td>
										</tr>
									</table>
								</div>
								<div></div>
							</div>
						</div>
					</div>
					<!-- row -->

				</div>
			</div>
		</div>
		<%@ include file="footer.jsp"%>

	</div>
	</div>
	<!-- core:js -->
	<script src="./resources/assets/vendors/core/core.js"></script>
	<!-- endinject -->
	<!-- plugin js for this page -->
	<script src="./resources/assets/vendors/chartjs/Chart.min.js"></script>
	<script src="./resources/assets/vendors/jquery.flot/jquery.flot.js"></script>
	<script
		src="./resources/assets/vendors/jquery.flot/jquery.flot.resize.js"></script>
	<script
		src="./resources/assets/vendors/bootstrap-datepicker/bootstrap-datepicker.min.js"></script>
	<script src="./resources/assets/vendors/apexcharts/apexcharts.min.js"></script>
	<script
		src="./resources/assets/vendors/progressbar.js/progressbar.min.js"></script>
	<!-- end plugin js for this page -->
	<!-- inject:js -->
	<script src="./resources/assets/vendors/feather-icons/feather.min.js"></script>
	<script src="./resources/assets/js/template.js"></script>
	<!-- endinject -->
	<!-- custom js for this page -->
	<script src="./resources/assets/js/dashboard.js"></script>
	<script src="./resources/assets/js/datepicker.js"></script>
	<!-- end custom js for this page -->
	<script>
	/* onclick get member with department */
		document.getElementById("departmentSelect").onchange = function() {
			if(document.getElementById("departmentSelect").value=="default"){
				document.getElementById("departmentMember").innerHTML = "<option value='default' selected>Member</option>";
			}
			else{
				$.ajax({
					url : "./department.htm?id="+document.getElementById("departmentSelect").value,
					type : "GET",
					contentType : "application/json;charset=utf8",
					dataType : "json",
					success : function(result) {
						let html = "";
						result.forEach(r=>{
							html+= '<option value="department_'+r['department']['id']+'-member_'+r['id']+'">'+r['fullName']+'</option>';
						})
						document.getElementById("departmentMember").innerHTML = html;
					},
					error : function(error) {

					}
				})
			}
		}
	
		/* onclick add department to div */
		var listDepartmentHtml = "";
		var listMemberHtml = "";
		let departmentSet = new Set();
		var countItemDepartment=0;
		document.getElementById("btnAddDepartment").onclick= function () {
			var listDepartmentId = 'listDepartment'+countItemDepartment;
			var deleteDepartmentId = 'deleteDepartment'+countItemDepartment;
			var indexMember = document.getElementById("departmentSelect").selectedIndex;
			var departmentName = document.getElementById("departmentSelect")[indexMember].innerText;
			var valueMember = "department_"+document.getElementById("departmentSelect")[indexMember].value;
			if(!departmentSet.has(valueMember) && document.getElementById("departmentSelect")[indexMember].value!="default"){
				listMemberHtml+= '<tr id="'+listDepartmentId+'"><td>'+departmentName+'<input style="display:none;" value="'+valueMember+'" type="text"></td><td class="text-right align-middle"><a id="'+deleteDepartmentId+'" href="#" class="btn btn-sm btn-outline-secondary border-0 badge"><i class="fa fa-trash"></i></a></td></tr>'
				document.getElementById("listMembersAdd").innerHTML=listMemberHtml;
				departmentSet.add(valueMember);
				countItemDepartment++;
				eventDeleteDepartment();
				deleteMemberAfterAddDepartment(document.getElementById("departmentSelect").value);
			}
		}
		
		/* onclick add member to div*/
		
		document.getElementById("btnAddMember").onclick= function () {
			var listDepartmentId = 'listDepartment'+countItemDepartment;
			var deleteDepartmentId = 'deleteDepartment'+countItemDepartment;
			var indexMember = document.getElementById("departmentMember").selectedIndex;
			var innerTextMember = document.getElementById("departmentMember")[indexMember].innerText;
			var valueMember = document.getElementById("departmentMember")[indexMember].value;
			
			var indexDepartment = document.getElementById("departmentSelect").selectedIndex;
			var departmentName = document.getElementById("departmentSelect")[indexDepartment].innerText;
			
			if(!departmentSet.has(valueMember) && valueMember!="default" && !checkExistsDepartment(valueMember)){
				listMemberHtml+= '<tr id="'+listDepartmentId+'"><td>('+departmentName+') '+innerTextMember+'<input style="display:none;" value="'+valueMember+'" type="text"></td><td class="text-right align-middle"><a id="'+deleteDepartmentId+'" href="#" class="btn btn-sm btn-outline-secondary border-0 badge"><i class="fa fa-trash"></i></a></td></tr>'
				document.getElementById("listMembersAdd").innerHTML=listMemberHtml;
				departmentSet.add(valueMember);
				countItemDepartment++;
				eventDeleteDepartment();
			}
		}
	
		
		/*onclick select vote option*/
		document.getElementById("selectVoteOption").onchange = function(){
			var valueOption = document.getElementById("selectVoteOption").value;
			document.getElementById("voteOption").style.pointerEvents =  valueOption == "1" ? "none" : "auto";
			document.getElementById("addItem_timeoption").style.display = valueOption == "1" ? "none" : "";
			document.getElementById("addItem_placeoption").style.display = valueOption == "1" ? "none" : "";
		}
		
	
		
		/*onclick add time option*/
		var countTimeOption = 0;
		
		document.getElementById("btnAdd_timeoption").onclick = function () {
			let html = `<div class="form-group row" id="timeOption`+countTimeOption+`"><div class="col-sm-3">
						<label id="labelTimeOption`+countTimeOption+`" class="col-form-label" for="">Option `+(countTimeOption+1)+`</label>
						</div>
						<div class="col-sm-6">
							<input id="timeValue`+countTimeOption+`" class="input-group date" type="date"
								data-date-format="dd-mm-yyyy">
						</div>
						<div class="col-sm-2">
							<button class="btn btn-sm btn-outline-secondary border-0"
								id="btn_removeItemTime`+countTimeOption+`">
								<i class="fa fa-trash"></i>
							</button>
						</div>
						<hr></div>`;
			countTimeOption++;
			document.getElementById("addItem_timeoption").innerHTML+=html;
			eventDeleteTimeOption();
			setOrdinalTimeOption();
		}
		
		/*onclick add place option*/
		var countPlaceOption = 0;
		
		document.getElementById("btnAddPlaceOption").onclick = function () {
			let html = `<div class="form-group row" id="placeOption`+countPlaceOption+`"><div class="form-group row">
				<div class="col-sm-3">
				<label id="labelPlaceOption`+countPlaceOption+`" class="col-form-label" for="">Option `+(countPlaceOption+1)+`</label>
					</div>
					<div class="col-sm-6">
						<input id="placeValue`+countPlaceOption+`" type="text" class="form-control" />
					</div>
					<div class="col-sm-2">
						<button class="btn btn-sm btn-outline-secondary border-0" id="btn_removeItemPlace`+countPlaceOption+`">
							<i class="fa fa-trash"></i>
						</button>
					</div>
				</div>
				<div class="form-group row">
					<div class="col-sm-3">
						<label class="col-form-label" for="">Image</label>
					</div>
					<div class="col-sm-9">
						<img style="display:none;" id="imgPreview`+countPlaceOption+`" src="#">
						<input accept="image/*" name="imagePlace`+countPlaceOption+`" class="input-group file-icon" id="choose_file`+countPlaceOption+`"
							type="file">
					</div>
				</div>
				<hr></div>`;
			countPlaceOption++;
			document.getElementById("addItem_placeoption").innerHTML+=html;
			eventDeletePlaceOption();
			setOrdinalPlaceOption();
			choiceImage();
		}
		
		
		/* onclick delete time option */
		function deleteTimeOptionElement(elm1,elm2){
			try{	
				document.getElementById(elm1).onclick = function(){
					document.getElementById(elm2).remove();
					setOrdinalTimeOption();
				}
			}
			catch{
				
			}
		}
		
		function eventDeleteTimeOption(){
			for(var i=0;i<countTimeOption;i++){
				deleteTimeOptionElement("btn_removeItemTime"+i.toString(),"timeOption"+i.toString());
			}
		}
		
		/* onclick delete place option */
		function deletePlaceOptionElement(elm1,elm2){
			try{
				document.getElementById(elm1).onclick = function(){
					document.getElementById(elm2).remove();
					setOrdinalPlaceOption();
				}
			}
			catch{
				
			}
		}
		function eventDeletePlaceOption(){
			for(var i=0;i<countPlaceOption;i++){
				deletePlaceOptionElement("btn_removeItemPlace"+i.toString(),"placeOption"+i.toString());
			}
		}
		/* set ordinal number time option */
		function setOrdinalTimeOption(){
			var index=0;
			for(var i=0;i<countTimeOption;i++){
				try{
					document.getElementById("labelTimeOption"+i.toString()).innerText = "Option "+(index+1);
					index++;
				}
				catch{
					
				}
			}
		}
		/* set ordinal number time option */
		function setOrdinalPlaceOption(){
			var index=0;
			for(var i=0;i<countPlaceOption;i++){
				try{
					document.getElementById("labelPlaceOption"+i.toString()).innerText = "Option "+(index+1);
					index++;
				}
				catch{
					
				}
			}
		}
		/* event choice image */
		function choiceImage(){
			for(var i = 0;i<countPlaceOption;i++){
				getImgData(i);
			}
		}
		function getImgData(i) {
			try{
				document.getElementById("choose_file"+i.toString()).addEventListener("change", function () {
					const files = document.getElementById("choose_file"+i.toString()).files[0];
					  if (files) {
					    const fileReader = new FileReader();
					    fileReader.readAsDataURL(files);
					    fileReader.addEventListener("load", function () {
					    	document.getElementById("imgPreview"+i.toString()).style.display = "block";
					    	document.getElementById("imgPreview"+i.toString()).src = this.result;
					    });    
					  }
				});
			}
			catch{
			}  
		}
	</script>
	<script>
		function deleteDepartmentElement(elm1,elm2){
			try{
				document.getElementById(elm1).onclick = function(){
					
					try{
						var valueMember = document.getElementById(elm2).querySelectorAll('td')[0].querySelectorAll('input')[0].value;
						departmentSet.delete(valueMember);
					}
					catch{
						
					}
					document.getElementById(elm2).remove();
					listMemberHtml= document.getElementById("listMembersAdd").innerHTML;
				}
			}
			catch{
				
			}
		}
		/* delete element department*/
		function eventDeleteDepartment(){
			for(var i=0;i<countItemDepartment;i++){
				deleteDepartmentElement("deleteDepartment"+i.toString(),"listDepartment"+i.toString());
				
			}
		}
		
		function checkExistsDepartment(value){
			try{
				var valueSplit = value.split("-");
				return departmentSet.has(valueSplit[0]);
			}
			catch{
				return false;
			}
		}
		function deleteMemberAfterAddDepartment(value){
			for(var i=0;i<countItemDepartment;i++){
				try{
					var valueDepartment = document.getElementById("listDepartment"+i.toString()).querySelectorAll('td')[0].querySelectorAll('input')[0].value;
					var valueDepartmentSplit = valueDepartment.split("-")[0];
					var valueMemberSplit = valueDepartment.split("-")[1];
					console.log(valueMemberSplit.split("_")[0]);
					if(valueMemberSplit.split("_")[0] == 'member'){
						if(valueDepartmentSplit.split("_")[1]==value){
							departmentSet.delete(valueDepartment);
							document.getElementById("listDepartment"+i.toString()).remove();
						}
					}
				}catch{
					
				}
			}
		}
		function getListStringDepartmentRegist(){
			return Array.from(departmentSet).toString();
		}
	</script>

	<script>
	/*add events request*/
		function getListTimeOption(){
			var listTimeOption = new Array();
			for(var i =0;i<countTimeOption;i++){
				try{
					listTimeOption.push(document.getElementById("timeValue"+i.toString()).value);
				}
				catch{
					
				}
			}
			return listTimeOption;
		}
		function getListPlaceOption(){
			var listPlaceOption = new Array();
			for(var i =0;i<countPlaceOption;i++){
				try{
					listPlaceOption.push(document.getElementById("placeValue"+i.toString()).value);
				}
				catch{
					
				}
			}
			return listPlaceOption;
		}
		
		/* btn click add event */
		document.getElementById("btnAddEvent").onclick = function(){
			var formData = $("#formEvents").serializeArray();
			formData[formData.length] = {name: 'members', value: getListStringDepartmentRegist()};
			formData[formData.length] = {name: 'times', value: getListTimeOption()};
			formData[formData.length] = {name: 'places', value: getListPlaceOption()};
		    $.post("./EventsInsert.htm", formData,   
		    	function(data, status){
		    		if(data != ""){
		    			var resp = data.replaceAll("[","");
			    		resp = resp.replaceAll("]","");
			    		resp = resp.replaceAll(" ","");
			    		resp = resp.split(",");
			        	var indexData = 0;
			    		for(var i=0;i<countPlaceOption;i++){
			    			try{
			    				var file = document.getElementById('choose_file'+i.toString()).files[0];
			    				uploadFile(resp[indexData++],file);
			    			}
			    			catch{
			    				
			    			}
			    		}
		    		}
		    	
		   		}
		    );
		}
	</script>

	<script>
		$(function() {
			$("#optionType1").click(function() {
				if ($(this).is(":checked")) {
					$("#addVoteOption").hide();
				}
			});
		});
		
		
	</script>

	<!-- <form action="#" enctype="multipart/form-data">  
		Select File: <input id="imageUpload" type="file" name="image"/>  
	</form> 
	<input id="btnUpload" type="submit" value="Upload File"/>  -->

	<script>
	/* upload file */
	
	/*document.getElementById("btnUpload").onclick = function(){
		var file = document.getElementById('imageUpload').files[0];
		var ajax = new XMLHttpRequest;
		
		var formData = new FormData;
		formData.append('image', file);
		ajax.upload.addEventListener("progress", myProgressHandler, false);
		ajax.addEventListener('load', myOnLoadHandler, false);
		ajax.open('POST', 'upload.htm', true);
		ajax.send(formData);
	}
	function myProgressHandler(event) {
		  //your code to track upload progress
		  var p = Math.floor(event.loaded/event.total*100);
		  document.title = p+'%';
		}

		function myOnLoadHandler(event) {
		  // your code on finished upload
		  alert (event.target.responseText);
		}
	*/
	function uploadFile(voteOptionId,file){
		var ajax = new XMLHttpRequest;
		var formData = new FormData;
		formData.append('image', file);
		formData.append('voteOptionId',voteOptionId);
		ajax.open('POST', 'upload.htm', true);
		ajax.send(formData);
	}
	</script>
	<script type="text/javascript">
		for(var i = 0;i<<%=voteOptionList.size()%>;i++){
			var countJoined= 0;
			for(var j = 0; j<<%=count%>;j++){
				if(document.getElementById("optionVoted"+j.toString()+i.toString()).value == "true")
					countJoined++;
			}	
			document.getElementById("statistics"+i.toString()).innerText = countJoined;
		}
		
	</script>
	
	<script type="text/javascript">
	document.getElementById("btnUpdateEvent").onclick = function(){
		console.log("hhhhh")
		 updateVote();
	}
	
		
		
	
		function updateVote(){
			var usersId = <%= ((Users) session.getAttribute("users")).getId() %>;
			
			
			var isJoined = document.getElementById("isJoined_"+usersId.toString()).value;
			
			var listVote = document.getElementsByClassName("listVote_"+usersId.toString());
			
			var attachedPersonAdult = document.getElementById("attachedPersonAdult_"+usersId.toString()).value;
				
			var attachedPersonChild = document.getElementById("attachedPersonChild_"+usersId.toString()).value;
			
			var note = document.getElementById("note_"+usersId.toString()).value;
			
			var listResultVote = new Array();
			for(var i = 0;i<listVote.length;i++){
				var isVotedId = listVote[i].id;
				var valueVote = document.getElementById(isVotedId).value;
				isVotedId = isVotedId.split("-")[1].split("_")[1];
				listResultVote.push(isVotedId+"_"+valueVote);
			}
			
			var ajax = new XMLHttpRequest;
			var formData = new FormData;
			formData.append('isJoined', isJoined);
			formData.append('listResultVote',listResultVote);
			formData.append('attachedPersonAdult',attachedPersonAdult);
			formData.append('attachedPersonChild',attachedPersonChild);
			formData.append('note',note);
			ajax.open('POST', 'updateVote.htm', true);
			ajax.send(formData);
			window.location.reload();
		}
		
		
		
	</script>
</body>

</html>