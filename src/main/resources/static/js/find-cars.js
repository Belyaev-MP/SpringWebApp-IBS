'use strict';

carsFilter.addEventListener('keyup', () => {
	let term = carsFilter.value;
	
	let xhr = new XMLHttpRequest();
	xhr.open('post', '/car/ajax-filter', true);
	
	xhr.addEventListener('readystatechange', () =>{
		
		if(xhr.readyState !== 4) {
			return;
		}
		
		if (xhr.status === 200) {
			try {
				let cars = JSON.parse(xhr.responseText);
				console.log(cars);
			} catch(e){}
		} else {
			alert('AJAX error');
		}
	});

	xhr.setRequestHeader('Content-Type', 'application/x-www-form-urlencoded');
	xhr.send('q=' + term);
});