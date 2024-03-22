let y = document.getElementById("btn");
let role = document.getElementById("role").value;
let textBoxDiv = document.getElementById("textBoxDiv");
/*let yesRadio = document.getElementById("sosCardDetails_yes");/
let showgovid = document.getElementById("showgovid");
showgovid.style.display = "none";
*/
function register() {
	y.style.left = "50px";
}
/*
function showTextBox() {
	if (yesRadio.checked) {
		textBoxDiv.style.display = "block";
	} else {
		textBoxDiv.style.display = "none";
	}
}

document.getElementById("role").addEventListener("change", function() {
	let selectedRole = this.value;
	if (selectedRole === "BUYER") {
		showgovid.style.display = "block";
	} else {
		showgovid.style.display = "none";
	}
});
*/
function validatePassword() {
	let password = document.getElementById("password").value;
	let confirmPassword = document.getElementById("confirmPassword").value;

	if (password != confirmPassword) {
		document.getElementById("passwordmessage").innerHTML = "Passwords do not match!";
		return false;
	} else {
		document.getElementById("passwordmessage").innerHTML = "";
		return true;
	}
}

window.onload = function() {
	let confirmPasswordInput = document.getElementById("confirmPassword");
	confirmPasswordInput.addEventListener("blur", function() {
		validatePassword();
	});
};

document.addEventListener('DOMContentLoaded', function() {
	function addToCart() {
		let customAlert = document.getElementById('customAlert');
		customAlert.style.display = 'block';
		customAlert.innerHTML = 'Added to Cart';
		setTimeout(() => {
			customAlert.style.display = 'none';
		}, 4000);
	}
	addToCart();
});

const urlParams = new URLSearchParams(window.location.search);
const message = urlParams.get('message');  
if (message) {                     
	alert(message);
}

