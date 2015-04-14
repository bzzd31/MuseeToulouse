
        $(function() {
            var dateToday = new Date();
            var dates = $("#datepickerDebut, #datepickerFin").datepicker({
                changeMonth: true,
                dateFormat: 'dd/mm/yy',
                numberOfMonths: 1,
                minDate: dateToday,
                onSelect: function(selectedDate) {
                    var option = this.id == "datepickerDebut" ? "minDate" : "maxDate",
                        instance = $(this).data("datepicker"),
                        date = $.datepicker.parseDate(instance.settings.dateFormat || $.datepicker._defaults.dateFormat, selectedDate, instance.settings);
                    dates.not(this).datepicker("option", option, date);
                    option == "minDate" ? $("#datepickerDebut").css("background-color", "transparent") :  $("#datepickerFin").css("background-color", "transparent");
                }
            });
        });

        function test(){
            var test = true;
            if(!testNbPersonne()){
                test = false;
            }
            if(!testDateDebut()){
                test = false;
            }
            if(!testDateFin()){
                test = false;
            }

            return test;
        };

        function testNbPersonne(){
            var test = true;
            if(parseInt($('#nbPersonnes').val()) > 6 || parseInt($('#nbPersonnes').val()) < 0 || isNaN(parseInt($('#nbPersonnes').val()))){
                $('#nbPersonnes').css("background-color", "red");
                test = false;
            }
            else{
                $("#nbPersonnes").css("background-color", "transparent");
            }
            return test;
        };

        function testDateDebut(){
            var test = true;
            if(!dateMalFormat($("#datepickerDebut").val())){
                $("#datepickerDebut").css("background-color", "red");
                test = false;
            }
            else{
                $("#datepickerDebut").css("background-color", "transparent");
            }
            return test;
        };

        function testDateFin(){
            var test = true;
            if(!dateMalFormat($("#datepickerFin").val())){
                $("#datepickerFin").css("background-color", "red");
                test = false;
            }else{
                $("#datepickerFin").css("background-color", "transparent");
            }
            return test;
        };

        function dateMalFormat(dateForm){
            if(dateForm.length == 0){
                return false
            }
            var     userFormat = 'dd/mm/yy', // default format
                delimiter = /[^mdy]/.exec(userFormat)[0],
                theFormat = userFormat.split(delimiter),
                theDate = dateForm.split(delimiter),

                isDate = function (date, format) {
                    var m, d, y
                    for (var i = 0, len = format.length; i < len; i++) {
                        if (/m/.test(format[i])) m = date[i]
                        if (/d/.test(format[i])) d = date[i]
                        if (/y/.test(format[i])) y = date[i]
                    }
                    return (
                    m > 0 && m < 13 &&
                    y && y.length === 4 &&
                    d > 0 && d <= (new Date(y, m, 0)).getDate()
                    )
                }

            return isDate(theDate, theFormat)
        };