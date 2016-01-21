
function help(id){
	if (document.getElementById(id).style.display == 'none') {
		document.getElementById(id).style.display = ''
	} else {
		document.getElementById(id).style.display = 'none'
	}	
}

$(document).ready(function(){
    $('[data-toggle="popover"]').popover();   
});