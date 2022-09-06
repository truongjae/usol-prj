<%@page import="java.util.HashMap"%>
<%@page import="entity.VoteOption"%>
<%@page import="entity.RegistEvents"%>
<%@page import="entity.Events"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jstl/fmt_rt" prefix="fmt"%>
<!DOCTYPE html>
<html lang="en">

<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<meta http-equiv="X-UA-Compatible" content="ie=edge">
<title>Event Detail</title>
<!-- core:css -->
<link rel="stylesheet" href="./resources/assets/vendors/core/core.css">
<link rel="stylesheet" href="./resources/assets/vendors/core/core.css">
<!-- endinject -->
<!-- plugin css for this page -->
<link rel="stylesheet"
	href="./resources/assets/vendors/bootstrap-datepicker/bootstrap-datepicker.min.css">
<!-- end plugin css for this page -->
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
	href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.1.2/css/all.min.css"
	rel="stylesheet">
</head>

<body>
	<%@ include file="header.jsp"%>
	<%
		List<Events> events = (List<Events>) request.getAttribute("eventDetails");
	%>
	<div class="main-wrapper">
		<section>
			<div class="col-md-12" style="padding-left: 0; padding-right: 0">
				<div class="card">
					<div class="card-header py-3" style="border-bottom: none">
						<h5>1. Event Information</h5>
					</div>
					<div class="row">
						<div class="col-sm-6">
							<form>
								<div class="card" style="box-shadow: none; border: none;">
									<div class="card-body">
										<div class="row mb-3">
											<label for="exampleInputUsername2"
												class="col-sm-3 col-form-label">Event Name</label>
											<div class="col-sm-9">

												<input type="text" class="form-control"
													id="exampleInputUsername2" placeholder=""
													value="<%=events.get(0).getEventName()%>">
											</div>
										</div>
										<div class="row mb-3">
											<label for="exampleInputEmail2"
												class="col-sm-3 col-form-label">Time</label>
											<div class="col-sm-9">
												<input type="date" value="<%=events.get(0).getEndDate()%>"
													class="form-control" id="exampleInputEmail2"
													autocomplete="off" placeholder="">
											</div>
										</div>
										<div class="row mb-3">
											<label for="exampleInputMobile"
												class="col-sm-3 col-form-label">Place</label>
											<div class="col-sm-9">
												<input type="text" class="form-control"
													id="exampleInputMobile" placeholder=""
													value="<%=events.get(0).getPlace()%>">
											</div>
										</div>
										<div class="row mb-3">
											<label for="exampleInputPassword2"
												class="col-sm-3 col-form-label">Creator</label>
											<div class="col-sm-9">
												<input type="text" class="form-control"
													id="exampleInputPassword2" autocomplete="off"
													placeholder=""
													value="<%=events.get(0).getUsers().getFullName()%>">
											</div>
										</div>
									</div>
								</div>
							</form>
						</div>
						<div class="col-sm-4" style="text-align: center">
							<img src="./img/<%=events.get(0).getEventImage()%>" alt=""
								width="80%" height="100%">
						</div>
						<div class="col-sm-2">
							<div>
								<br> <a href="EventRegister.htm"><button type="button"
										class="btn" style="width: 90%; background-color: #b6bbfa;">Update
										Event</button></a> <br> <br>
								<%
									if (events.get(0).getUsers().getUserName().equals(session.getAttribute("username"))) {
								%>
								<a
									href="DeleteEvents.htm?id=<%=request.getParameter("EventId")%>" onclick="return confirm('Do you want to keep deleting?');"><button
										type="button" class="btn"
										style="width: 90%; background-color: #b6bbfa;">Delete
										Event</button></a> <br> <br> <a
									href="UpdateEventsStatus.htm?id=<%=request.getParameter("EventId")%>"><button
										type="button" class="btn"
										style="width: 90%; background-color: #b6bbfa;">Close
										Event</button></a> <br> <br>
								<%
									}
								%>
								<a href="Login.htm"><button type="button" class="btn"
										style="width: 90%; background-color: #b6bbfa;">Back
										Event List</button></a>
							</div>
						</div>
					</div>
					<div class="card-header py-3" style="border-bottom: none">
						<h5>2. Detail Vote</h5>
					</div>
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
						int indexJoined=0;
						int attachChilden=0;
						int attachAdult=0;
						for(long a:registListByUsersId.keySet())
						{
							attachChilden+=registListByUsersId.get(a).getAttachedPersonChild();
							attachAdult+=registListByUsersId.get(a).getAttachedPersonAdult();
							if(registListByUsersId.get(a).getIsJoined())
							{
								indexJoined++;
								
							}
						} 
						
						
					%>
					<div class="table-responsive pt-3" style="padding: 1rem;">
						<table class="table table-bordered" style="border-top: none">
							<thead>
								<tr>
									<td colspan="3" style="border: none"></td>
									<td class="bg-primary; background-color:none"
										style="border: 1px solid #e8ebf1; background-color: #b6bbfa">
										Thong Ke Nhanh</td>
									<td style="border: 1px solid #e8ebf1;"><%=indexJoined%></td>
									<%
										int index=0;
										for (VoteOption vote:voteOptionList) {
											%>
											<td id="statistics<%=index++ %>" style="border: 1px solid #e8ebf1;"></td>
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
										src="img/<%=voteOption.getOptionImage()%>" alt=""
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
								</tr>
							</thead>
							<%
								int count = 0;
							%>
							<tbody>
								<%
									int x=0;
									for (Long i : usersList.keySet()) {
										count++;
										
								%>
								<tr class="table">
									<td><%=count%></td>
									<td><%=usersList.get(i).getId()%></td>
									<td><%=usersList.get(i).getFullName()%></td>
									<td><%=usersList.get(i).getEmail()%></td>
									<%
										for (Long j : registListByUsersId.keySet()) {
												if (registListByUsersId.get(j).getUsers().getId().equals(usersList.get(i).getId())) {
									%>
									<td><select style="width: 70px;" name="joined">
											<%
												String joined = registListByUsersId.get(j).getIsJoined() ? "O" : "X";
											%>
											<option value="<%=registListByUsersId.get(j).getIsJoined()%>"><%=joined%></option>
									</select></td>
									<%
										}

											}
									%>

									<%
										if (size != 0) {
												int y=0;
												for (Long m : registList.keySet()) {
													if (registList.get(m).getUsers().getId().equals(i)) {
									%>
									<td><select name="isVoted"  style="width: 70px;">
											<%
												String isVoted = registList.get(m).getVoted() ? "O" : "X";
											%>
											<option id="optionVoted<%=x%><%=y%>" value="<%=registList.get(m).getVoted()%>"><%=isVoted%></option>
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
									<td><%=registListByUsersId.get(k).getAttachedPersonAdult()%></td>
									<td><%=registListByUsersId.get(k).getAttachedPersonChild()%></td>
									<td></td>
									<%
										}

											}
									%>
								</tr>
								<%
									x++;
								
									}
								%>
							</tbody>


						</table>
					</div>
				</div>
			</div>
		</section>
	</div>
	<%@ include file="footer.jsp"%>

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
</body>

</html>