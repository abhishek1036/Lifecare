function confirmLogout(event) {
	event.preventDefault();

	const dialogContainer = document.createElement('div');
	dialogContainer.classList.add('custom-dialog-container');

	const dialog = document.createElement('div');
	dialog.classList.add('custom-dialog');

	const message = document.createElement('p');
	message.textContent = 'Are you sure want to logout?';

	const confirmBtn = document.createElement('button1');
	confirmBtn.textContent = 'Yes';
	confirmBtn.addEventListener('click', function() {
		window.location.href = "/logout";
		dialogContainer.remove();
	});

	const cancelBtn = document.createElement('button2');
	cancelBtn.textContent = 'No';
	cancelBtn.addEventListener('click', function() {
		dialogContainer.remove();
	});

	dialog.appendChild(message);
	dialog.appendChild(confirmBtn);
	dialog.appendChild(cancelBtn);

	dialogContainer.appendChild(dialog);

	document.body.appendChild(dialogContainer);
}


const urlParams = new URLSearchParams(window.location.search);
const logoutStatus = urlParams.get('logout');
const displayLogoutMessage = sessionStorage
	.getItem('displayLogoutMessage');

if (logoutStatus === 'success' && displayLogoutMessage !== 'shown') {
	const messageContainer = document.createElement('div');
	messageContainer.classList.add('logout-message');
	messageContainer.textContent = 'Logout successful';

	document.body.appendChild(messageContainer);
	sessionStorage.setItem('displayLogoutMessage', 'shown');
	messageContainer.classList.add('show');
	setTimeout(function() {
		messageContainer.remove();
		sessionStorage.removeItem('displayLogoutMessage');
	}, 3000);

}