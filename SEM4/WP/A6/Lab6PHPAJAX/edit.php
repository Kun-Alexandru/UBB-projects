<!DOCTYPE html>
<html lang="en" dir="ltr">
  <head>
    <meta charset="utf-8">
    <title>Edit document</title>
  </head>
  <body>
    <h2>Edit document</h2>
    <form autocomplete="off" action="" method="post">
      <?php
      require 'config.php';
      $id = $_GET["id"];
      $rows = mysqli_fetch_assoc(mysqli_query($conn, "SELECT * FROM document WHERE id = $id"));
      ?>
      <input type="hidden" id="id" value="<?php echo $rows['id']; ?>">
      <label for="">Title</label>
      <input type="text" id="title" value="<?php echo $rows['title']; ?>"> <br>
      <label for="">Author</label>
      <input type="text" id="author" value="<?php echo $rows['author']; ?>"> <br>
      <label for="">NumberOfPages</label>
      <input type="text" id="numberPages" value="<?php echo $rows['numberPages']; ?>"> <br>
      <label for="">Type</label>
      <input type="text" id="type" value="<?php echo $rows['type']; ?>"> <br>
      <label for="">Format</label>
      <select class="" id="format">
        <option value="JPG" <?php if($rows["format"] == "JPG") echo "selected"; ?> >JPG</option>
        <option value="TXT" <?php if($rows["format"] == "TXT") echo "selected"; ?> >TXT</option>
        <option value="GIF" <?php if($rows["format"] == "GIF") echo "selected"; ?> >GIF</option>
      </select> <br>
      <button type="button" onclick="submitData('edit');">Edit</button>
    </form>
    <br>
    <a href="index.php">Go To Index</a>
    <?php require 'script.php'; ?>
  </body>
</html>
