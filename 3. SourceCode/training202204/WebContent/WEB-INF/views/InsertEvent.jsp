<%@page import="entity.Users"%>
<%@page import="entity.MasterEventType"%>
<%@page import="entity.Department"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">

<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<meta http-equiv="X-UA-Compatible" content="ie=edge">
<title>Form Insert</title>
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

	<div class="main-wrapper">
		<%@ include file="header.jsp"%>
		<!-- partial -->
		<div class="page-wrapper">
			<div class="card">
				<div class="card-body">
					<div
						class="d-flex justify-content-between align-items-center flex-wrap grid-margin">
						<div>
							<h4 class="mb-3 mb-md-0">Welcome to Usol-V</h4>
						</div>
					</div>
					<!-- row -->
					<div class="row">
						<div class="col-md-6 grid-margin stretch-card">
							<div class="card">
								<div class="card-body">
									<h6 class="card-title">1. Event information</h6>

									<form id="formEvents" action="#" class="forms-sample">
										<div class="form-group row">
											<label class="col-sm-2" for="exampleInputUsername1">Event
												name</label>
											<div class="col-sm-10">
												<input name="eventName" type="text" class="form-control"
													id="exampleInputUsername1" autocomplete="off"
													placeholder="Event name">
											</div>
										</div>
										<div class="form-group row">
											<label class="col-sm-2 col-form-label" for="">Description</label>
											<div class="col-sm-10">
												<textarea name="description" class="form-control" id=""
													rows="3"></textarea>
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
														System.out.print(departmentList.size());
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
													<option value="default">Member</option>

												</select> &nbsp;	
												<div>
													<input type="button" id="btnAddMember"
														class="btn btn-primary float-right" value="Add">
												</div>
											</div>
										</div>
										<div class="form-group row"
											style="height: 200px; overflow: auto;">
											<label class="col-sm-3 col-form-label" for="">Members</label>
											<div class="table-responsive col-sm-9">
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
												<textarea name="place" class="form-control" id="" rows="3"></textarea>
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
													<option selected value="1" id="optionType1">Chọn 1</option>
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
														placeholder="Start time">
												</div>
											</div>
											<div class="col-sm-5">
												<div class="form-group">
													<input name="startDate" type="date" class="form-control"
														placeholder="Start date">
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
														placeholder="End time">
												</div>
											</div>
											<div class="col-sm-5">
												<div class="form-group">
													<input name="endDate" type="date" class="form-control"
														placeholder="End date">
												</div>
											</div>
										</div>
									</form>
								</div>
							</div>
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
						<div class="page-wrapper">
							<div class="row">
								<div class="col-6">
									<button id="btnAddEvent" class="btn btn-primary float-right">Add
										Event</button>
								</div>
								<div class="col-6">
									<a href="Login.htm"><button class="btn btn-primary">&nbsp;&nbsp;&nbsp;Cancel&nbsp;&nbsp;&nbsp;</button></a>
								</div>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>

		<!-- partial:partials/_footer.html -->
		<%@ include file="footer.jsp"%>
		<!-- partial -->

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
		    		data = JSON.parse(data);
		    		var listVoteOptionId = data['listVoteOptionId'];
		    		var eventsId = data['eventsId'];
		    		if(listVoteOptionId != ""){
		    			var resp = listVoteOptionId.replaceAll("[","");
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
		    		 window.location.href = "./Login.htm";
		    	
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
</body>

</html>