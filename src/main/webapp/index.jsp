<html>
<head>

<title> Hilo Student</title>
<style>


	h1{
		margin: 0 auto;
	  	width: 300px;
	}
		
	form {
	  /* Center the form on the page */
	  margin: 0 auto;
	  width: 550px;
	  /* Form outline */
	  padding: 1em;
	  border: 1px solid #CCC;
	  border-radius: 1em;
	}
	
</style>

<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.1.0/jquery.min.js"></script>
<script>
$(document).ready(function() {
    var max_fields      = 2;
    var wrapper         = $(".container1");
    var add_button      = $(".add_form_field");
 
    var x = 1;
    $(add_button).click(function(e){
        e.preventDefault();
        if(x < max_fields){
            x++;
            $(wrapper).append('<div>Question: <input type="text" name="myquest[]"/><a href="#" class="delete">Delete</a></div>'); //add input box
        }
  else
  {
  alert('You Reached the limits')
  }
    });
 
    $(wrapper).on("click",".delete", function(e){
        e.preventDefault(); $(this).parent('div').remove(); x--;
    })
});


$(document).ready(function() {
    var max_fields      = 5;
    var wrapper         = $(".container2");
    var add_button      = $(".add_form_field2");
 
    var x = 1;
    $(add_button).click(function(e){
        e.preventDefault();
        if(x < max_fields){
            x++;
            $(wrapper).append('<div>Answer: <input type="text" name="myAns[]"/> Target: <input type="text" name="mytarget[]"/><a href="#" class="delete">Delete</a></div>'); //add input box
        }
  else
  {
  alert('You Reached the limits')
  }
    });
 
    $(wrapper).on("click",".delete", function(e){
        e.preventDefault(); $(this).parent('div').remove(); x--;
    })
});
</script>


</head>
<body>

	<h1> Build Your Bot </h1>


	<form action="show">
		Category: <select name="category">
			<option value="-"></option>
			  <option value="1">Career</option>
			  <option value="2">Demographic</option>
			  <option value="3">Entrepreneurship</option>
			  <option value="4">Financial</option>
			  <option value="5">Mental Health</option>
			  <option value="6">Personal Health</option>
		</select>
		<input type="submit" value="show"> 
	
	</form>



	<form action="add">
		State ID: <input type="text" name="id"><br>
		
		<br><div class="container1">		
		<div> Question: <input type="text" name="myquest[]"> <button class="add_form_field">Add New Question &nbsp; <span style="font-size:16px; font-weight:bold;">+ </span></button></div> 
		</div>
		
		<br><div class="container2">
		<button class="add_form_field2">Add New Answer &nbsp; <span style="font-size:16px; font-weight:bold;">+ </span></button>
		<div>Answer: <input type="text" name="myAns[]"> Target: <input type="text" name="mytarget[]"></div>
		</div> 

		<input type="submit">
	</form>





</body>
</html>	