$(document).ready(function () {
	getTaskToDo();
        countTaskTodo();
});

function getTaskToDo() {
	cekSession(function (dataResult) {
		if (dataResult.status == false) {
			alert("Your Session is Expired, You'll Redirect to Login Page");
			$(location).attr("href", APP_PATH + "/login");
		}
	});
	$.ajax({
		'url': APP_PATH + "/app/getTaskToDo",
		'type': 'GET',
		beforeSend: function (xhr) {
			$('#taskToDo').html("");
		},
		success: function (data) {
			$('#taskToDo').html(data);
		},
		complete: function () {

		}
	});
}

function countTaskTodo() {
	cekSession(function (dataResult) {
		if (dataResult.status == false) {
			alert("Your Session is Expired, You'll Redirect to Login Page");
			$(location).attr("href", APP_PATH + "/login");
		}
	});
	$.ajax({
		'url': APP_PATH + "/app/countTaskToDo",
		'type': 'POST',
		beforeSend: function (xhr) {
			$('#taskToDo').html("");
		},
		success: function (data) {
			$('span#countTask').html(data);
		},
		complete: function () {

		}
	});
}