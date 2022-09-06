<%@page import="entity.Users"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">

<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<meta http-equiv="X-UA-Compatible" content="ie=edge">
<title></title>
<script src="https://kit.fontawesome.com/96ed116afc.js"
	crossorigin="anonymous"></script>
<link rel="stylesheet" href="./resources/assets/css/demo_5/style.css">
<link rel="stylesheet" href="./resources/assets/vendors/core/core.css">
<link rel="stylesheet"
	href="./resources/assets/fonts/feather-font/css/iconfont.css">
<link rel="stylesheet"
	href="./resources/assets/vendors/flag-icon-css/css/flag-icon.min.css">
<link rel="stylesheet" href="./resources/assets/images/favicon.png">

<body>
	<!-- partial:partials/_navbar.html -->
	<div class="horizontal-menu">
		<!-- header -->
		<nav class="navbar top-navbar">
			<div class="">
				<div class="navbar-content">
					<img style="width: 28%;" src="img/UsolLogo.jpg" alt="">
					<ul class="navbar-nav">
						<li class="nav-item dropdown nav-profile"><a
							class="nav-link dropdown-toggle" href="#" id="profileDropdown"
							role="button" data-toggle="dropdown" aria-haspopup="true"
							aria-expanded="false"> <img src="https://via.placeholder.com/30x30" alt="profile">
						</a>
							<div class="dropdown-menu" aria-labelledby="profileDropdown">
								<div
									class="dropdown-header d-flex flex-column align-items-center">
									<div class="figure mb-3">
										<img src="https://via.placeholder.com/80x80" alt="">
									</div>
									<%
									List<Users> userList=(List<Users>)session.getAttribute("userList");
									%>
									<div class="info text-center">
										<p class="name font-weight-bold mb-0"><%=userList.get(0).getFullName()%></p>
										<p class="email text-muted mb-3"><%=userList.get(0).getEmail()%></p>
									</div>
								</div>
								<div class="dropdown-body">
									<ul class="profile-nav p-0 pt-3">
										<li class="nav-item"><a href="Logout.htm"
											class="nav-link"> <i data-feather="log-out"></i> <span>Log
													Out</span>
										</a></li>
									</ul>
								</div>
							</div></li>
					</ul>
					<button
						class="navbar-toggler navbar-toggler-right d-lg-none align-self-center"
						type="button" data-toggle="horizontal-menu-toggle">
						<i data-feather="menu"></i>
					</button>
				</div>
			</div>
		</nav>
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
</body>

</html>