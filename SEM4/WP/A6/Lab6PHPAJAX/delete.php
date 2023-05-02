<!DOCTYPE html>
<html>
<head>
	<title>Delete Document</title>
</head>
<body>
	<h2>Delete Document</h2>
    <p style = "font-size: 20px">Are you sure you want to delete this document?</p>
    <table border = 1>
      <tr>
        <td>ID</td>
        <td>Title</td>
        <td>Author</td>
        <td>NumberOfPages</td>
        <td>Type</td>
        <td>Format</td>
      </tr>
      <?php
      require 'config.php';
      $id = $_GET['id'];
      $rows = mysqli_query($conn, "SELECT * FROM document where id = $id");
      $i = 1;
      ?>
      <?php foreach($rows as $row) : ?>
      <tr idd = <?php echo $row["id"]; ?>>
      <td><?php echo $row["id"]; ?></td>
        <td><?php echo $row["title"]; ?></td>
        <td><?php echo $row["author"]; ?></td>
        <td><?php echo $row["numberPages"]; ?></td>
        <td><?php echo $row["type"]; ?></td>
        <td><?php echo $row["format"]; ?></td>
      </tr>
      <?php endforeach; ?>
    </table>
    <button type="button" onclick = "submitData(<?php echo $id; ?>);">Yes</button>
    <button type="button" onclick="location.href = 'index.php';">No</button>
    <br>
    <a href="index.php">Go To Documents</a>
    <script>
</script>
    <?php require 'script.php'; ?>
</body>
</html>
