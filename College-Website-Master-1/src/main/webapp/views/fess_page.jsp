<%@ page import="com.model.helper.DatabaseHelper"%>
<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<html>
<head>
<meta charset="UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>Online Pay Fees - GGU</title>
<link rel="shortcut icon"
	href="<%=request.getContextPath()%>/static/image/favicon.ico"
	type="image/svg+xml">
<link rel="stylesheet"
	href="<%=request.getContextPath()%>/static/css/fees_style.CSS">
<link rel="stylesheet"
	href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
<script type="module"
	src="https://unpkg.com/ionicons@5.5.2/dist/ionicons/ionicons.esm.js"></script>
</head>

	<%
	// JSP logic to retrieve data from the database
	String uid = request.getParameter("uid");
	if (uid != null && !uid.isEmpty()) {
		String studentName = DatabaseHelper.getStudentName(uid);
		String pendingFees = DatabaseHelper.getPendingFees(uid);

		// Set retrieved data in request scope
		request.setAttribute("studentName", studentName);
		request.setAttribute("uid", uid);
		System.out.println(studentName);
		request.setAttribute("pendingFees", pendingFees);
	}
	%>
</body>
<body>
	<section class="header">
		<nav>
			<a href="index.html"><img src="<%=request.getContextPath()%>/static/image/logo.png"
				id="logo-img"></a>

			<div class="nav-links" id="navLinks">

				<span class="icon" onclick="hidemenu()">&#10005;</span>
				<ul>
					<li><a href="<%=request.getContextPath()%>/index.html">Home</a></li>
					<li><a href="<%=request.getContextPath()%>/Admission_page.html">Admission</a></li>
					<li><a href="<%=request.getContextPath()%>/#course_call">Course</a></li>
					<li><a href="<%=request.getContextPath()%>/fees_page.html">Fees</a></li>
					<li><a href="<%=request.getContextPath()%>/Contact_page.html">Contact</a></li>
				</ul>
			</div>
			<span class="icon" onclick="showmenu()">&#9776;</span>
		</nav>

		<form method="post" onsubmit="pay()">
			<div class="wrapper">
				<div class="r_form_wrap">

					<div class="title">
						<p id="header_fees">Online Pay Fees</p>
					</div>

					<div class="r_form">
						<div class="input_wrap">
							<label for="yourname">Course</label>
							<!-- Your course input content here -->
							<i class="fa fa-bars" aria-hidden="true" id="icon"></i> <select
								id="course" name="cars" class="input" required>
								<option value="select">Select the Course</option>
								<option value="bca" id="bcabatch">BCA 2021-2022 Batch</option>
							</select> <br> <br> <label for="yourname">Your
								UID/Enrollment no</label>
							<div class="input_item">
								<i class="fa fa-list-ol" aria-hidden="true" id="icon"></i> <input
									type="number" class="input" id="uid" name="uid"
									placeholder="Enter the last two UID digit" value="<%=request.getAttribute("uid")%>">
							</div>
						</div>
						<p id="uid_detail">Ex: FCAB2101101001 to enter the: 01</p>
						<input type="submit" class="button" value="Get"> <br>
						<br>

						<!-- Display Student Name -->
						<div class="input_wrap">
							<label for="yourname">Student name</label>
							<div class="input_item">
								<i class="fa fa-user" id="icon"></i> <input type="text"
									id="demo" class="input"
									value="<%=request.getAttribute("studentName")%>">
							</div>
						</div>

						<!-- Display Pending Fees Detail -->
						<div class="input_wrap">
							<label for="yourname">Pending Fees Detail</label>
							<div class="input_item">
								<i class="fa fa-credit-card" id="icon"></i> <input type="text"
									id="demo1" class="input"
									value="<%=request.getAttribute("pendingFees")%>">
							</div>
						</div>

						<input type="submit" class="button" value="Pay Fees">
					</div>
				</div>
			</div>
		</form>
	</section>

	<div class="none_div">
		<!-- Your content for none_div here -->
	</div>

	<script
		src="<%=request.getContextPath()%>/static/javascript/fees_script.js"></script>
	<script>
		function pay() {
			// Add your pay function logic here
		}

		// Add your other scripts here
	</script>
	<script src=" https://smtpjs.com/v3/smtp.js"></script>

</html>