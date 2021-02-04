$(document).ready(
    function (){
        $("#personForm").submit(function(event){
            event.preventDefault();
            ajaxPost();
        });

        function ajaxPost(){
            var formData = {
                name : $("#personName").val(),
            }

            $.ajax({
                type : "POST",
                contentType : "application/json",
                url : "/savePerson",
                data : JSON.stringify(formData),
                dataType : 'json',
                success : function(result){
                    alert(result.data.name + " recorded on PostgresDB!");
                    console.log(result)
                },
                error : function (e){
                    alert("Error");
                    console.log("error",e);
                }
            });
        }
    }
)