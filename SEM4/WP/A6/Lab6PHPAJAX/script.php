<script src = "https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js">
</script>

<script type="text/javascript">

var filter = "None";
var opt = "None";

  function submitData(action){
    $(document).ready(function(){
      var data = {
        action: action,
        id: $("#id").val(),
        title: $("#title").val(),
        author: $("#author").val(),
        numberPages: $("#numberPages").val(),
        type: $("#type").val(),
        format: $("#format").val(),
      };

      $.ajax({
        url: 'function.php',
        type: 'post',
        data: data,
        success:function(response){
          alert(response);
          if(response == "Deleted Successfully"){
            $("#"+action).css("display", "none");
            location.reload();
          }
          if(response == "Updated Successfully"){
            window.location.href = "index.php";
          }
          if(response == "Inserted Successfully"){
            window.location.href = "index.php";
          }
        }
      });
    });

  }

  function getDocuments(type) {
  $.ajax({
    url: "get_documents.php?type=" + type,
    dataType: "json",
    success: function(data) {
      // Update the document list on the web page with the data returned from the server
    },
    error: function(jqXHR, textStatus, errorThrown) {
      console.log("AJAX error: " + textStatus + " - " + errorThrown);
    }
  });
}

function jsonParse(text) {
    let json;
    try {
        json = JSON.parse(text);
    } catch (e) {
        return false;
    }
    return json;
}

function get_filtered_by_type(){
    // We create a request
    var ajax = new XMLHttpRequest();
    console.log('here');
    // We define a function to be executed then the ready state is changed, so
    // in our case when the request is finished and the response is ready
    ajax.onreadystatechange = function(){
        if(this.readyState === 4 && this.status === 200){
            // We take our table, in which we will place the data
            let table = document.getElementsByTagName("table")[0];
            let oldTableBody = document.getElementsByTagName("tbody")[0];

            // We create a new body for the table
            let newTableBody = document.createElement('tbody');
            
            // We take the response and convert it into an array of objects
            let json = jsonParse(this.responseText);
            for(let i = 0; i < json.length; i++){
                // We take the object, insert a new row into the table body
                let document1 = json[i];
                let row = newTableBody.insertRow();
                // Then we parse the objects keys and on each of them, we apply a function,
                // which inserts in the previously added row, a new cell, and appends
                // the text from that attribute of the object to the cell
                Object.keys(document1).forEach(function (k){
                    let text;
                    let cell = row.insertCell();
                    text = document1[k];
                    cell.appendChild(document.createTextNode(text));
                });

                // Add edit/delete buttons
                let editDeleteCell = row.insertCell();
                let editButton = document.createElement('a');
                editButton.setAttribute('href', 'edit.php?id=' + document1['id']);
                editButton.innerHTML = 'Edit';
                editDeleteCell.appendChild(editButton);
                
                let deleteButton = document.createElement('button');
                deleteButton.setAttribute('type', 'button');
                deleteButton.setAttribute('onclick', "if(confirm('Are you sure you want to delete this document?')) { submitData(" + document1['id'] + "); }");
                deleteButton.innerHTML = 'Delete';
                editDeleteCell.appendChild(deleteButton);
            }
            // At the end, we replace the old body table with the new one
            table.appendChild(newTableBody);
            oldTableBody.parentNode.removeChild(oldTableBody);
        }
    }

    // We initialize the request
    ajax.open('POST', 'getAllFilesByType.php', true);
    // We take the chosen type for filter
    let type = document.getElementsByTagName("select")[0].value;
    // We convert the value type to a JSON string
    let json = JSON.stringify({'type': type});
    // We send the request to the server
    ajax.send(json);

    setPrevious("Type", type);
}


function get_filtered_by_format(){
    // We create a request
    var ajax = new XMLHttpRequest();
    console.log('here');
    // We define a function to be executed then the ready state is changed, so
    // in our case when the request is finished and the response is ready
    ajax.onreadystatechange = function(){
        if(this.readyState === 4 && this.status === 200){
            // We take our table, in which we will place the data
            let table = document.getElementsByTagName("table")[0];
            let oldTableBody = document.getElementsByTagName("tbody")[0];

            // We create a new body for the table
            let newTableBody = document.createElement('tbody');
            
            // We take the response and convert it into an array of objects
            let json = jsonParse(this.responseText);
            for(let i = 0; i < json.length; i++){
                // We take the object, insert a new row into the table body
                let document1 = json[i];
                let row = newTableBody.insertRow();
                // Then we parse the objects keys and on each of them, we apply a function,
                // which inserts in the previously added row, a new cell, and appends
                // the text from that attribute of the object to the cell
                Object.keys(document1).forEach(function (k){
                    let text;
                    let cell = row.insertCell();
                    text = document1[k];
                    cell.appendChild(document.createTextNode(text));
                });

                // Add edit/delete buttons
                let editDeleteCell = row.insertCell();
                let editButton = document.createElement('a');
                editButton.setAttribute('href', 'edit.php?id=' + document1['id']);
                editButton.innerHTML = 'Edit';
                editDeleteCell.appendChild(editButton);
                
                let deleteButton = document.createElement('button');
                deleteButton.setAttribute('format', 'button');
                deleteButton.setAttribute('onclick', "if(confirm('Are you sure you want to delete this document?')) { submitData(" + document1['id'] + "); }");
                deleteButton.innerHTML = 'Delete';
                editDeleteCell.appendChild(deleteButton);
            }
            // At the end, we replace the old body table with the new one
            table.appendChild(newTableBody);
            oldTableBody.parentNode.removeChild(oldTableBody);
        }
    }

    // We initialize the request
    ajax.open('POST', 'getAllFilesByFormat.php', true);
    // We take the chosen type for filter
    let format = document.getElementsByTagName("select")[1].value;
    // We convert the value type to a JSON string
    let json = JSON.stringify({'format': format});
    // We send the request to the server
    ajax.send(json);

    setPrevious("Format", format);
}

function setPrevious(filt, option){
    document.getElementById("previous-filter").innerHTML = "Filter : " + filter + "  with " + opt;
    filter=filt;
    opt = option;

}
</script>
