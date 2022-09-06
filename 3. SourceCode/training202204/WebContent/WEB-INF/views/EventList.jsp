<%@page import="entity.RegistEvents"%>
<%@page import="entity.Events"%>
<%@page import="java.util.HashMap"%>
<%@page import="entity.MasterEventType"%>
<%@page import="entity.Department"%>
<%@page import="dto.response.events.EventsResponse"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jstl/fmt_rt" prefix="fmt"%>
<!DOCTYPE html>
<html lang="en">
<head>
<link rel="stylesheet" href="./resources/assets/css/demo_5/style.css">
<link rel="stylesheet" href="./resources/assets/vendors/core/core.css">
<link rel="stylesheet" href="./resources/assets/css/demo_5/style.css">
<link rel="stylesheet" href="./resources/assets/vendors/core/core.css">
<link rel="stylesheet"
	href="./resources/assets/fonts/feather-font/css/iconfont.css">
<link rel="stylesheet"
	href="./resources/assets/vendors/flag-icon-css/css/flag-icon.min.css">
<link rel="stylesheet" href="./resources/assets/images/favicon.png">

<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<meta http-equiv="X-UA-Compatible" content="ie=edge">
<title>Event List</title>

<script src="https://kit.fontawesome.com/96ed116afc.js"
	crossorigin="anonymous"></script>

</head>

<body>
	<%@ include file="header.jsp"%>
	<!-- partial -->

	<div class="page-wrapper">
		<div
			class="d-flex justify-content-between align-items-center flex-wrap grid-margin">
			<div>
				<h4 class="mb-3 mb-md-0">Welcome to Usol-V</h4>
			</div>
			<div class="d-flex align-items-center flex-wrap text-nowrap">
				<a href="InsertEvent.htm"><button type="button"
						class="btn btn-primary btn-icon-text mb-2 mb-md-0">
						<i class="btn-icon-prepend" data-feather="download-cloud"></i> Add
						Event
					</button></a>

			</div>
		</div>
		<!-- row -->
		<div class="row">
			<div class="col-md-12 grid-margin stretch-card">
				<div class="card">
					<div class="card-body">
						<label for="chkSelect"> <input type="checkbox"
							id="chkSelect" /> Search condition
						</label>
					</div>
					<div class="card-body" id="content" style="display: none">
						<form action="searchEventInfo.htm" method="post">
							<div class="row">
								<div class="col-sm-4">
									<div class="form-group">
										<label class="control-label">Event name</label> <input
											type="text" name="eventName" class="form-control"
											placeholder="Enter Event name">
									</div>
								</div>
								<!-- Col -->
								<div class="col-sm-4">
									<div class="form-group">
										<label class="control-label">Place</label> <input type="text"
											name="place" class="form-control" placeholder="Enter place">
									</div>
								</div>
								<div class="col-sm-4">
									<div class="form-group">
										<label class="control-label">Event Type</label> <select
											name="masterEventsType" class="form-select"
											aria-label="Default select example">
											<%
												List<MasterEventType> masterEventTypeList = (List<MasterEventType>) session
														.getAttribute("masterEventTypeList");
												for (MasterEventType masterEventType : masterEventTypeList) {
											%>
											<option value="<%=masterEventType.getId()%>"><%=masterEventType.getEventType()%></option>
											<%
												}
											%>


										</select>
									</div>
								</div>
								<!-- Col -->
							</div>
							<!-- Row -->
							<div class="row">
								<div class="col-sm-4">
									<div class="row">
										<div class="col-sm-6">
											<div class="form-group">
												<label class="control-label">Start hours</label> <input
													name="startHour" type="time" class="form-control"
													placeholder="Start hours">
											</div>
										</div>
										<div class="col-sm-6">
											<div class="form-group">
												<label class="control-label">End hours</label> <input
													name="endHour" type="time" class="form-control"
													placeholder="End hours">
											</div>
										</div>
									</div>
								</div>
								<!-- Col -->
								<div class="col-sm-4">
									<div class="form-group">
										<label class="control-label">Department</label> <select
											name="department" class="form-select"
											aria-label="Default select example">
											<%
												List<Department> departmentList = (List<Department>) session.getAttribute("departmentList");
												for (Department department : departmentList) {
											%>
											<option value="<%=department.getId()%>"><%=department.getDepartName()%></option>
											<%
												}
											%>


										</select>
									</div>
								</div>
								<!-- Col -->
								<div class="col-sm-4">
									<div class="form-group">
										<label class="control-label">Event Status</label> <select
											name="status" class="form-select"
											aria-label="Default select example">
											<option value="1" selected>Opening</option>
											<option value="2">Closed</option>
										</select>
									</div>
								</div>
								<!-- Col -->
							</div>
							<!-- Row -->
							<div class="row">
								<div class="col-sm-4">
									<div class="row">
										<div class="col-sm-6">
											<div class="form-group">
												<label class="control-label">Start date</label> <input
													name="startDate" type="date" class="form-control"
													placeholder="Start date">
											</div>
										</div>
										<div class="col-sm-6">
											<div class="form-group">
												<label class="control-label">End date</label> <input
													name="endDate" type="date" class="form-control"
													placeholder="End date">
											</div>
										</div>
									</div>
								</div>
								<div class="col-sm-4">
									<div class="form-group">
										<label class="control-label">Creator</label> <input
											name="fullName" type="text" class="form-control"
											placeholder="Enter Creator">
									</div>
								</div>
							</div>
							<!-- Row -->

							<button type="submit" class="btn btn-primary submit">Search</button>
							<button type="button" class="btn btn-primary submit">Reset</button>
						</form>
					</div>
					<div class="card-body">
						<h6 class="card-title">Event list</h6>

						<div>
							<table id="dataTableExample" class="table">
								<thead>
									<tr>
										<th>Event name</th>
										<th>Description</th>
										<th>Time</th>
										<th>Place</th>
										<th>Department</th>
										<th>Creator</th>
										<th>Status</th>
										<th>Joined</th>
										<th>Not join</th>
										<th>Action</th>
									</tr>
								</thead>
								<tbody>
									<%
										List<EventsResponse> events = (List<EventsResponse>) session.getAttribute("events");
										for (EventsResponse event : events) {
									%>
									<tr>
										<td><%=event.getEvents().getEventName()%></td>
										<td><%=event.getEvents().getDescription()%></td>
										<td><fmt:formatDate pattern="dd/MM/yyyy"
												value="<%=event.getEvents().getStartDate()%>" /> <fmt:formatDate
												type="time" timeStyle="short"
												value="<%=event.getEvents().getStartHour()%>" /><br> ~<fmt:formatDate
												pattern="dd/MM/yyyy"
												value="<%=event.getEvents().getEndDate()%>" /> <fmt:formatDate
												type="time" timeStyle="short"
												value="<%=event.getEvents().getEndHour()%>" /></td>
										<td><%=event.getEvents().getPlace()%></td>
										<td><%=event.getEvents().getRegistEvents().getUsers().getDepartment().getDepartName()%></td>
										<td><%=event.getEvents().getUsers().getFullName()%></td>
										<%
											if (event.getEvents().getStatus() == 1) {
										%>
										<td>Opening</td>
										<%
											} else {
										%>
										<td>Closed</td>
										<%
											}
										%>
										<td><%=event.getCountJoined()%></td>
										<td><%=event.getCountNotJoined()%></td>
										<td class="text-center align-middle">
											<div class="btn-group align-top">
												<a
													href="DetailEvent.htm?EventId=<%=event.getEvents().getId()%>"
													class="btn btn-sm btn-outline-secondary badge"> <i
													class="fa-solid fa-circle-info"></i>
												</a>
												<%
													if (event.getEvents().getUsers().getUserName().toLowerCase().equals(session.getAttribute("username"))) {
												%>
												<a
													href="EventsRegister.htm?EventId=<%=event.getEvents().getId()%>"
													class="btn btn-sm btn-outline-secondary badge"> <i
													class="fa-solid fa-pen-to-square"></i>
												</a> <a
													href="DeleteEvents.htm?id=<%=event.getEvents().getId()%>"
													onclick="return confirm('Do you want to keep deleting?');"
													" class="btn btn-sm btn-outline-secondary badge"> <i
													class="fa fa-trash"></i>
												</a>
												<%
													}
												%>

												<a id="voteOptionDisable"
													href="VoteEvents.htm?EventId=<%=event.getEvents().getId()%>"
													class="btn btn-sm btn-outline-secondary badge"> <i
													class="fa-solid fa-check-to-slot"></i>
												</a>
												<script>
													
												</script>

											</div>
										</td>
									</tr>
									<%
										}
									%>
								</tbody>

								</script>
							</table>
						</div>
					</div>
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
	<!-- Checkbox dropdown  -->
	<script type="text/javascript"
		src="https://ajax.googleapis.com/ajax/libs/jquery/1.8.3/jquery.min.js"></script>
	<script type="text/javascript">
		$(function() {
			$("#chkSelect").click(function() {
				if ($(this).is(":checked")) {
					$("#content").show();
				} else {
					$("#content").hide();
				}
			});
		});
		<!--
	//-->
	</script>
</body>

</html>