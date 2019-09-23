			function viewListApproval(dtlTrxId,levelId) {

					var data = {
						"dtlTrxId": dtlTrxId,
						"levelId": levelId
					};
					postData(data, APP_PATH + "/avm/getModalApprovalHistory", function (dataResult) {
						$('#modalApproval').html(dataResult);
						$('#modalApprovalHistory').modal('toggle');
					});
			}