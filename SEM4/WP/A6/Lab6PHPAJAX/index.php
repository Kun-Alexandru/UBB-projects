<!DOCTYPE html>
<html lang="en" dir="ltr">
  <head>
    <meta charset="utf-8">
    <title id = 'main'>Main</title>
    <link rel="stylesheet" href="mystyle.css">
  </head>
  <body>
    <h2>Main</h2>
    
    <p> Filter by type</p>

    <div>
            <select id="select-type" name="Select Filter" onchange="get_filtered_by_type()">
                <?php
                    require 'config.php';
                    $sql = "SELECT DISTINCT type FROM document";
                    $result_set = $conn->query($sql);
                    
                    echo '<option>' . 'None' . '</option>';

                    if(mysqli_num_rows($result_set) > 0){
                        while($row = mysqli_fetch_array($result_set)){
                            $type = ''. $row['type'] .'';
                            echo '<option>' . $type . '</option>';
                        }
                    }
                ?>
            </select>

    </div>

    <p> Filter by format </p>

    <div>
            <select id="select-type" name="Select Filter" onchange="get_filtered_by_format()">
                <?php
                    require 'config.php';
                    $sql = "SELECT DISTINCT format FROM document";
                    $result_set = $conn->query($sql);
                    
                    echo '<option>' . 'None' . '</option>';

                    if(mysqli_num_rows($result_set) > 0){
                        while($row = mysqli_fetch_array($result_set)){
                            $format = ''. $row['format'] .'';
                            echo '<option>' . $format . '</option>';
                        }
                    }
                ?>
            </select>

    </div>
    <br>
    <table border = 1>
    <thead id>
      <th>ID</th>
      <th>Title</th>
      <th>Author</th>
      <th>NumberPages</th>
      <th>Type</th>
      <th>Format</th>
      </thead>
      <?php
      require 'config.php';
      $rows = mysqli_query($conn, "SELECT * FROM document");
      $i = 1;
      ?>
      <tbody id="browse-tbody">
      <?php
                    require 'config.php';
                    $result_set = mysqli_query($conn, "SELECT * FROM document");
                    
                    while($row = mysqli_fetch_array($result_set)){
                        echo " <tr>";
                        echo  "<td>" . $row['id'] . "</td>";
                        echo  "<td>" . $row['title'] . "</td>";
                        echo  "<td>" . $row['author'] . "</td>";
                        echo  "<td>" . $row['numberPages'] . "</td>";
                        echo  "<td>" . $row['type'] . "</td>";
                        echo  "<td>" . $row['format'] . "</td>";
                        echo '<td>
                          <a href="edit.php?id=' . $row['id'] . '">Edit</a>
                          <button type="button" onclick="if(confirm(\'Are you sure you want to delete this document?\')) { submitData(' . $row['id'] . '); }">Delete</button>
                            </td>';
                        echo   "</tr>";
                    }
                ?>
      </tbody>
    </table>
    <br>
    <a href="add.php">Add Document</a>
    <br>   <br>
    <a href="mainpage.php">Landing page</a>

    <div id="previous-filter">
    <?php require 'script.php'; ?>
    <script>
    document.getElementById("previous-filter").innerHTML = "Filter : - with -";
    function confirmDelete(id) {
        window.location.href = "delete.php?id=" + id;
    }
</script>
  </body>
</html>