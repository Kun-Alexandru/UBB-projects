<!DOCTYPE html>
<html lang="en" dir="ltr">
  <head>
    <meta charset="utf-8">
    <title>Add Document</title>
  </head>
  <body>
    <h2>Add User</h2>
    <form autocomplete="off" action="" method="post">
      <label for="">Title</label>
      <input type="text" id="title" value=""> <br>
      <label for="">Author</label>
      <input type="text" id="author" value=""> <br>
      <label for="">Number of pages</label>
      <input type="text" id="numberPages" value=""> <br>
      <label for="">Type</label>
      <input type="text" id="type" value=""> <br>
      <label for="">Format</label>
      <select class="" id="format">
        <option value="JPG">JPG</option>
        <option value="TXT">TXT</option>
        <option value="GIF">GIF</option>
      </select> <br>
      <button type="button" onclick="submitData('insert');">Insert</button>
    </form>
    <br>
    <a href="index.php">Go To Index</a>
    <?php require 'script.php'; ?>
  </body>
</html>
