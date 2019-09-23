  function validDate(){
            var StartMonth, StartYear, EndMonth, EndYear;

            StartMonth = document.getElementById("ddlStartMonth").value;
            StartYear = document.getElementById("ddlStartYear").value;
            EndMonth = document.getElementById("ddlEndMonth").value;
            EndYear = document.getElementById("ddlEndYear").value;


            var ScheduleStartDate = StartYear + "-" + StartMonth + "-" + "1";
            var ScheduleEndDate = EndYear + "-" + EndMonth + "-" + "25";
            
                if (StartMonth == 0 || StartYear == 0 || EndMonth == 0 || EndYear == 0) {
                    alert("Start Period and End Period must be selected");
                    return false;
                }
                else if (StartMonth == 0 || StartYear == 0) {
                    alert("Start Period must be selected");
                    return false;
                }
                else if (EndMonth == 0 || EndYear == 0) {
                    alert("End Period must be selected");
                    return false;
                }
                else if ((StartMonth >= EndMonth && StartYear >= EndYear) ||
                    (StartMonth == EndMonth && StartYear >= EndYear) ||
                    (StartMonth >= EndMonth && StartYear == EndYear)) {
                    alert("End Period cannot be greater than Start Period");
                    return false;
                }
                else {
                    var x = confirm("Are you sure want to do the Re-Run Process?");
                    if (x == 1) {
                        return true;
                    }
                    else {
                        return false;

                    }
                }
    }
    function loginForm() {
        var companyid, email, password;
        
        companyid = document.getElementById("companyID").value;
        email = document.getElementById("email").value;
        password = document.getElementById("password").value;
        
        if (companyid == "" && email == "" && password =="") {
            document.getElementById("error").className += " state-error";
            document.getElementById("erroremail").className += " state-error";
            document.getElementById("errorpassword").className += " state-error";
            return false;
        } if (companyid == "" || companyid == null ) {
            document.getElementById("error").className += " state-error";
            return false;
        }  if (email == "" || email == null ) {
            document.getElementById("error").className += " state-error";
            return false;
        }  if (password == "" || password == null ) {
            document.getElementById("error").className += " state-error";
            return false;
        } 
    }
    
    