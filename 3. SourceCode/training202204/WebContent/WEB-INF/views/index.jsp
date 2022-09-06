<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
<head>
<link rel="stylesheet"
	href="https://cdn.jsdelivr.net/npm/bootstrap@4.0.0/dist/css/bootstrap.min.css"
	integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm"
	crossorigin="anonymous">
<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css"
	rel="stylesheet"
	integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC"
	crossorigin="anonymous">
<script
	src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.9.2/dist/umd/popper.min.js"
	integrity="sha384-IQsoLXl5PILFhosVNubq5LC7Qb9DXgDA9i+tQ8Zj3iwWAwPtgFTxbJ8NT4GN1R8p"
	crossorigin="anonymous"></script>
<script
	src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.bundle.min.js"
	integrity="sha384-MrcW6ZMFYlzcLA8Nl+NtUVF0sA7MsXsP1UyJoMp4YLEuNSfAP+JcXn/tWtIaxVXM"
	crossorigin="anonymous"></script>
<script
	src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.min.js"
	integrity="sha384-cVKIPhGWiC2Al4u+LWgxfKTRIcfu0JTxR+EQDz/bgldoEyl4H0zUF0QKbrJ0EcQF"
	crossorigin="anonymous"></script>

<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>
<script
	src="https://cdn.jsdelivr.net/npm/sweetalert2@11.4.8/dist/sweetalert2.all.min.js"></script>

</head>

<body>
	<style type="text/css">
button {
	padding: 1em;
	background-color: #1560bd;
	color: #ffffff;
	border-radius: 0.2em;
	border-style: none;
	cursor: pointer;
}
</style>
	<%
		if (request.getAttribute("invalid") != null) {
	%>
	<script>
	 Swal.fire('<%=request.getAttribute("invalid")%>',)
	</script>
	<%
		}
	%>
	<section class="vh-100" style="background-color: #f9fafb;">
		<div class="container py-5 h-100">
			<div
				class="row d-flex justify-content-center align-items-center h-100">
				<div class="col-12 col-md-8 col-lg-6 col-xl-5" style="width:50%">
					<div class="card shadow-2-strong" style="border-radius: 1rem; box-shadow: 2px 0 10px 0 rgb(183 192 206 / 20%);">
						<div class="card-body p-5 text-center">
							<h3 class="mb-5">Welcome to the Event Register !!</h3>
							<form action="Login.htm" method="post">
								<div class="form-outline mb-4 row">
									<h6 class="col-3" style="padding-top:3%">Username</h6>
									<input required type="text" name="username"
										class="form-control form-control-lg col-9" placeholder="Username" style="width:70%"/>
								</div>
								<div class="form-outline mb-4 row">
									<h6 class="col-3" style="padding-top:3%">Password</h6>
									<input required type="password" name="password"
										class="form-control form-control-lg col-9" placeholder="Password" style="width:70%" />
								</div>
								<button class="btn btn-lg btn-block"
									style="background-color: #727cf5; border-color:#727cf5; width:20%" type="submit">Login</button>
							</form>
						</div>
					</div>
				</div>
			</div>
		</div>
	</section>
</body>
</html>