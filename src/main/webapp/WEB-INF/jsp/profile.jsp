<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Create Skill Profile</title>
    <!-- Add Bootstrap CSS link here -->

</head>
<body>

<div class="container mt-5">
    <h3>Create Skill Profile</h3>

    <form:form action="/createProfile" method="post" modelAttribute="profile">
        <!-- Dynamic Skill Entries -->
        <div id="skillEntries">
            <div class="form-group skill-entry">
                <label for="skillInput">Skill:</label>
                <form:input type="text" class="form-control" path="skill" />
                <label for="descriptionInput">Description:</label>
                <form:textarea class="form-control" path="description"></form:textarea>
                <button type="button" class="btn btn-danger remove-skill mt-2">Remove Skill</button>
            </div>
        </div>

        <!-- Add Skill Button -->
        <button type="button" class="btn btn-success mt-3" id="addSkill">Add Skill</button>

        <!-- Submit Button -->
        <form:button type="submit" class="btn btn-primary mt-3">Create Profile</form:button>
    </form:form>
</div>


<script>
    $(document).ready(function () {
    	alert("hello");
        // Add Skill Button Click Event
        $("#addSkill").click(function () {
        	alert("add function clicked");
            var newSkillEntry = $("#skillEntries .skill-entry:first").clone();
            newSkillEntry.find("input, textarea").val(""); // Clear values
            newSkillEntry.find(".remove-skill").show(); // Show remove button
            $("#skillEntries").append(newSkillEntry);
        });

        // Remove Skill Button Click Event
        $("#skillEntries").on("click", ".remove-skill", function () {
            $(this).closest(".skill-entry").remove();
        });

        // Initially hide remove button for the first entry
        $("#skillEntries .skill-entry:first .remove-skill").hide();
    });
</script>

</body>
</html>
