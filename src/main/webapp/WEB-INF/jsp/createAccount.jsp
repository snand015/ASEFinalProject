w
 <!DOCTYPE html>
  <%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<html lang="en">
<head>
  <meta charset="UTF-8">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <title>Bootstrap Form</title>
  <!-- Bootstrap CSS -->
  <link rel="stylesheet" href="/css/style.css" type="text/css"/>
    <link href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css" rel="stylesheet">
  
</head>
<body>
<div class="container">
  <img src="/images/icon.jpeg" alt="Icon" class="icon">
 <div class="container mt-5">
<div class="container my-5 py-5">
    <div class="justify-content-center">
      <div class=" col-md-30 col-lg-30 col-xl-30 ">
      
            <div class=" align-items-center">
    <h2>Registration Form</h2>
   <form:form action="addUser" method="post" modelAttribute="user">
      <div class="form-group">
        <label for="firstName">First Name *</label>
        <form:input type="text" class="form-control-local" path="firstName" placeholder="Enter first name" /><form:errors path="firstName" cssClass="error"/><br/>
      </div>
      <div class="form-group">
        <label for="lastName">Last Name</label>
        <form:input type="text" class="form-control-local" path="lastName" placeholder="Enter last name"/><form:errors path="lastName" cssClass="error"/><br/>
      </div>
      <div class="form-group">
        <label for="email">Email address *</label>
        <form:input type="email" class="form-control-local" path="email" placeholder="Enter email" />
        <form:errors path="email" cssClass="error"/></br>
      </div>
            <div class="form-group">
        <label for="firstName">userId *</label>
      <form:input path="userName" class="form-control-local" type="text" /><form:errors path="userName" cssClass="error"/><br/>
      </div>
       <div class="form-group">
        <label for="firstName">password *</label>
      <form:input path="password" id="password-input" type="password" class="form-control-local" onchange="updateMeter(this.value)"/><form:errors path="password" cssClass="error"/><br/>
      </div>
      <div class="password-meter">
                            <div class="meter-section rounded me-2"></div>
                            <div class="meter-section rounded me-2"></div>
                            <div class="meter-section rounded me-2"></div>
                            <div class="meter-section rounded"></div>
                        </div>
      <div class="form-group">
        <label for="address">Address</label>
        <form:textarea class="form-control textarea" path="address" rows="3" placeholder="Enter address"></form:textarea>
      </div>
      <form:button type="submit" class="btn btn-primary">addUser</form:button>
      <button type="button" class="btn btn-primary round-corner" onclick="window.location.href='/skillapp'">Back</button>
    </form:form>
  </div>
  </div></div></div></div></div>
<script>
const passwordInput = document.getElementById('password-input');
const meterSections = document.querySelectorAll('.meter-section');

passwordInput.addEventListener('input', updateMeter);

function updateMeter(password) {
    
    let strength = calculatePasswordStrength(password);

    // Remove all strength classes
    meterSections.forEach((section) => {
        section.classList.remove('weak', 'medium', 'strong', 'very-strong');
    });

    // Add the appropriate strength class based on the strength value
    if (strength >= 1) {
        meterSections[0].classList.add('weak');
    }
    if (strength >= 2) {
        meterSections[1].classList.add('medium');
    }
    if (strength >= 3) {
        meterSections[2].classList.add('strong');
    }
    if (strength >= 4) {
        meterSections[3].classList.add('very-strong');
    }
}

function calculatePasswordStrength(password) {
	
    const lengthWeight = 0.2;
    const uppercaseWeight = 0.5;
    const lowercaseWeight = 0.5;
    const numberWeight = 0.7;
    const symbolWeight = 1;

    let strength = 0;

    // Calculate the strength based on the password length
    strength += password.length * lengthWeight;

    // Calculate the strength based on uppercase letters
    if (/[A-Z]/.test(password)) {
        strength += uppercaseWeight;
    }

    // Calculate the strength based on lowercase letters
    if (/[a-z]/.test(password)) {
        strength += lowercaseWeight;
    }

    // Calculate the strength based on numbers
    if (/\d/.test(password)) {
        strength += numberWeight;
    }

    // Calculate the strength based on symbols
    if (/[^A-Za-z0-9]/.test(password)) {
        strength += symbolWeight;
    }

    return strength;
}
</script>
  <!-- Bootstrap JS -->
  <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
  <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.16.0/umd/popper.min.js"></script>
  <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
</body>
</html>