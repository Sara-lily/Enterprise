function addToBox(boxName) {
    var selectBox = document.getElementById(boxName);
    var selectedValue = selectBox.options[selectBox.selectedIndex].value;
    //Check to make sure the value is not already in the box.  If it is, show an error message.
    var instrument = selectedValue.substr(0, selectedValue.indexOf(':')); 
    var variable = selectedValue.substr(selectedValue.indexOf(':')+1); 
    if (notInBox(instrument, selectedValue)) {
    	var error = document.getElementById(instrument + "Error");
    	$(error).hide();
    	if (boxName == "selectBox") {
    		var instType = "target";
    	}
    	else {
    		var instType = "reference";
    	}
    	var divBegin = "<div id=" + selectedValue + ">";
    	var input = "<input type='hidden' name='" + instType + "DataVariableRefs' value=" + selectedValue + " path='" + instType + "DataVariableRefs' itemLabel='" + variable + "' itemValue='" + selectedValue + "' />";
    	var button = "<button class='transparentButton' type='button' onclick='removeVar(" + '"' + selectedValue  + '"' + ");'>&#10006</button><div class='variable'>" + variable + "</div><br></div>";
    	$('#' + instrument).append(divBegin + input + button);
    }
    else {
    	var error = document.getElementById(instrument + "Error");
    	$(error).show();
    }
}

function removeVar(varToRemove) {
	var selected = document.getElementById(varToRemove);
	$(selected).remove();
}

function notInBox(instrument, selectedValue) {
	var count = 0;
	$('#' + instrument + '> div').each(function () { 
		if ($(this).attr('id') == selectedValue) {
			count = 1;
			return false;
		}
	});
	if (count == 0) {
		return true;
	}
}