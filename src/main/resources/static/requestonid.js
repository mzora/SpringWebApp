$(document).ready(
    function (){
        $("#multiForm").submit(function(event){
            event.preventDefault();
            if($('#inlineFormCustomSelect').val() === "3"){
                ajaxDelete();
            }else if($('#inlineFormCustomSelect').val() === "2"){
                ajaxUpdate();
            }else if($('#inlineFormCustomSelect').val() === "1"){
                ajaxFinder();
            }
        });

        function ajaxDelete(){
            let formData = {
                id : $("#personId").val()
            }
            $.ajax({
                type : 'DELETE',
                contentType : "application/json",
                url : "/api/"+formData.id,
                data : JSON.stringify(formData),
                dataType : 'json',
                success : function(result){
                    alert("Deleted from PostgresDB!");
                    console.log(result)
                },
                error : function (e){
                    alert("Error");
                    console.log("error",e);
                }
            });
        }

        function ajaxFinder(){
            let formData = {
                id : $("#personId").val()
            }
            $.ajax({
                type : 'GET',
                contentType : "application/json",
                url : "/api/"+formData.id,
                success : function(result){
                    let utente = "ID: "+result.data.id+" Nome:"+result.data.name;
                    alert(utente + " Finded");
                    console.log(result)
                },
                error : function (e){
                    alert("Error");
                    console.log("error",e);
                }
            });
        }

        function ajaxUpdate(){
            var formData = {
                id : $("#personId").val(),
                name : $("#personNameToUpdate").val()
            }

            $.ajax({
                type : "PUT",
                contentType : "application/json",
                url : "/api/"+formData.id+"/"+formData.name,
                success : function(result){
                    alert("Updated on PostgresDB!");
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