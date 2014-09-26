function OpenSel(op) {
 
if (op == 1) {
document.getElementById('principal').style.display="block";
document.getElementById('divum').style.display="none";
document.getElementById('divdois').style.display="none";
document.getElementById('divtres').style.display="none";
}else if (op == 2) {
document.getElementById('principal').style.display="none";
document.getElementById('divum').style.display="block";
document.getElementById('divdois').style.display="none";
document.getElementById('divtres').style.display="none";
}else if (op == 3) {
document.getElementById('principal').style.display="none";
document.getElementById('divum').style.display="none";
document.getElementById('divdois').style.display="block";
document.getElementById('divtres').style.display="none";
}else if (op == 4) {
document.getElementById('principal').style.display="none";
document.getElementById('divum').style.display="none";
document.getElementById('divdois').style.display="none";
document.getElementById('divtres').style.display="block";
}else {
document.getElementById('principal').style.display="none";
document.getElementById('divum').style.display="none";
document.getElementById('divdois').style.display="block";
}
}