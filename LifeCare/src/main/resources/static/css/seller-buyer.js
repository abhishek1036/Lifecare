function togglePopup() {
	const popup = document.getElementById('popupForm');
	popup.style.display = (popup.style.display === 'none' || popup.style.display === '') ? 'block'
		: 'none';
}
/*
function handleSosEdit(element) {
	let medicineId = element.closest('tr').querySelector('input[type="hidden"]').value;
	let originalValue = element.getAttribute('data-original-value');
	let newValue = element.checked;
	if (newValue !== originalValue) {
		$.ajax({
			type: 'POST',
			url: '/seller/updatesosApplicable',
			data: {
				medicineId: medicineId,
				sosApplicable: newValue
			},
			success: function(response) {
				let customAlert = document.getElementById('notificationContainer');
				customAlert.style.display = 'block';
				customAlert.innerHTML = 'SOS updated successfully';
				setTimeout(() => {
					customAlert.style.display = 'none';
				}, 2000);
			},
			error: function(error) {
				console.error(error);
			}
		});
	}
}*/
$(document).ready(function() {
	$('#Addlistofmedicine button').hide();
	$('input[name="selectedMedicinesIds"]').change(function() {
		let anyCheckboxChecked = $('input[name="selectedMedicinesIds"]:checked').length > 0;
		$('#Addlistofmedicine button').toggle(anyCheckboxChecked);
	});
});

function handleCountEdit(element) {
	let medicineId = element.closest('tr').querySelector('input[type="hidden"]').value;
	let originalValue = element.getAttribute('data-original-value');
	let newValue = element.innerText.trim();

	if (/^\d{1,4}$/.test(newValue)) {
		if (newValue !== originalValue) {
			$.ajax({
				type: 'POST',
				url: '/seller/updateCount',
				data: {
					medicineId: medicineId,
					count: newValue
				},
				success: function(response) {
					let customAlert = document.getElementById('notificationContainer');
					customAlert.style.display = 'block';
					customAlert.innerHTML = 'Count updated successfully';
					setTimeout(() => {
						customAlert.style.display = 'none';
					}, 2000);
				},
				error: function(error) {
					console.error(error);
				}
			});
		}
	} else {
		let customAlert = document.getElementById('notificationContainer');
		customAlert.style.display = 'block';
		customAlert.innerHTML = 'Please enter numeric values';
		setTimeout(() => {
			customAlert.style.display = 'none';
		}, 2000);
		element.innerText = '';
	}
	
	document.addEventListener('keydown',function(event){
		if(event.key==='Enter'){
			event.preventDefault();
		}
	});
}
$(document).ready(function() {
	$('#Addlistofmedicine button').hide();

	$('input[name="selectedMedicinesIds"]').change(function() {
		let anyCheckboxChecked = $('input[name="selectedMedicinesIds"]:checked').length > 0;
		$('#Addlistofmedicine button').toggle(anyCheckboxChecked);
	});
});

$(document).ready(function() {
	let messageElement = $('#message');
	let messageText = messageElement.find('p').text().trim();
	if (messageText.length > 0) {
		messageElement.show();
		setTimeout(function() {
			messageElement.hide();
		}, 2000);
	} else {
		messageElement.hide();
	}
});


function checkExistingMedicine() {
	const enteredMedicineName = document.getElementById('medicineName').value.trim();
	const errorElement = document.getElementById('medicineNameError');
 
	$.ajax({
		type: 'POST',
		url: '/seller/checkMedicineName',
		data: { medicineName: enteredMedicineName },
		success: function (isAvailable) {
			if (isAvailable) {
				errorElement.innerText = '';
			} else {
				errorElement.innerText = 'Medicine already exists';
			}
		},
		error: function (error) {
			console.error('Error checking existing medicine:', error);
		}
	});
}



document.addEventListener('DOMContentLoaded', function () {
  document.getElementById('confirmationOverlay').addEventListener('click', function (event) {
    event.preventDefault();
    let deleteLink = document.querySelector('.delete.confirming');
    if (deleteLink) {
      let isConfirmed = event.target.id === 'confirmDelete';
      confirmDelete(isConfirmed, deleteLink);
    }
    document.getElementById('confirmationOverlay').style.display = 'none';
  });

  function showConfirmation(event) {
    event.preventDefault();
    
    let deleteLink = event.currentTarget;
    deleteLink.classList.add('confirming');
    document.getElementById('confirmationOverlay').style.display = 'flex';
  }

  function confirmDelete(isConfirmed, deleteLink) {
    if (isConfirmed) {
      let medicineId = deleteLink.getAttribute('href').split('=')[1];
      window.location.href = deleteLink.getAttribute('href');

      let customAlert = document.getElementById('notificationContainer');
      customAlert.style.display = 'block';
      customAlert.innerHTML = 'Medicine Deleted successfully';
      setTimeout(() => {
        customAlert.style.display = 'none';
      }, 4000);
    }
  }

  let deleteLinks = document.querySelectorAll('.delete');
  deleteLinks.forEach(function (deleteLink) {
    deleteLink.addEventListener('click', showConfirmation);
  });
});

function addMedicinesToCart() {
	const checkboxes = document.querySelectorAll('input[name="selectedMedicinesIds"]:checked');
	const selectedMedicineIds = Array.from(checkboxes).map(checkbox => checkbox.value);
	document.getElementById('selectedMedicinesIds').value = selectedMedicineIds.join(',');
	document.getElementById('addToCartForm').submit();
}



        function toggleDropdown() {
            var dropdown = document.getElementById("profileDropdown");
            dropdown.style.display = (dropdown.style.display === "block") ? "none" : "block";
        }
 
       /* function toggleSOSDetails() {
            var sosDetails = document.getElementById("sosDetails");
            sosDetails.style.display = (sosDetails.style.display === "block") ? "none" : "block";
        }*/
 
        document.addEventListener('click', function (event) {
            var dropdown = document.getElementById("profileDropdown");
            if (event.target.closest('.profile-container') === null && dropdown.style.display === "block") {
                dropdown.style.display = "none";
            }
        });
    

