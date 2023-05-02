<?php
require 'config.php';

if(isset($_POST["action"])){
  if($_POST["action"] == "insert"){
    insert();
  }
  else if($_POST["action"] == "edit"){
    edit();
  }
  else{
    delete();
  }
}

function insert(){
  global $conn;

if ($_SERVER["REQUEST_METHOD"] == "POST") {

  // Check if title is empty
  if (empty($_POST["title"])) {
    echo "Title is required";
    exit;
  } else {
    $title = $_POST["title"];
  }

  // Check if author is empty
  if (empty($_POST["author"])) {
    echo "Author is required";
    exit;
  } else {
    $author = $_POST["author"];
  }

  // Check if numberPages is empty
  if (empty($_POST["numberPages"])) {
    echo "Number of pages is required";
    exit;
  } else {
    $numberPages = $_POST["numberPages"];
  }

  if (!is_numeric($numberPages)) {
    echo "Error: Number of pages should be a number.";
    exit();
}

  // Check if type is empty
  if (empty($_POST["type"])) {
    echo "Type is required";
    exit;
  } else {
    $type = $_POST["type"];
  }

  // Check if format is empty
  if (empty($_POST["format"])) {
    echo "Format is required";
    exit;
  } else {
    $format = $_POST["format"];
  }

  // Get the next available ID
  $query = "SELECT MAX(id) FROM document";
  $result = mysqli_query($conn, $query);
  $row = mysqli_fetch_array($result);
  $next_id = $row[0] + 1;

  // Insert the document
  $query = "INSERT INTO document VALUES('$next_id', '$title', '$author', '$numberPages', '$type', '$format')";
  mysqli_query($conn, $query);
  echo "Inserted Successfully";
}
}

function edit(){
  global $conn;

  $id = $_POST["id"];
  $title = $_POST["title"];
  $author = $_POST["author"];
  $numberPages = $_POST["numberPages"];
  $type = $_POST["type"];
  $format = $_POST["format"];

  if (!is_numeric($numberPages)) {
    echo "Error: Number of pages should be a number.";
    exit();
  }

  if (empty($title)) {
    echo "Title is required";
    exit;
  } else {
    $title = $_POST["title"];
  }

  // Check if author is empty
  if (empty($author)) {
    echo "Author is required";
    exit;
  } else {
    $author = $_POST["author"];
  }

  // Check if numberPages is empty
  if (empty($numberPages)) {
    echo "Number of pages is required";
    exit;
  } else {
    $numberPages = $_POST["numberPages"];
  }

  if (!is_numeric($numberPages)) {
    echo "Error: Number of pages should be a number.";
    exit();
}

  // Check if type is empty
  if (empty($type)) {
    echo "Type is required";
    exit;
  } else {
    $type = $_POST["type"];
  }

  // Check if format is empty
  if (empty($format)) {
    echo "Format is required";
    exit;
  } else {
    $format = $_POST["format"];
  }

  $query = "UPDATE document SET title = '$title', author = '$author', numberPages = '$numberPages', type = '$type', format = '$format' WHERE id = $id";
  mysqli_query($conn, $query);
  echo "Updated Successfully";
}

function delete(){
  global $conn;

  $id = $_POST["action"];

  $query = "DELETE FROM document WHERE id = $id";
  mysqli_query($conn, $query);
  echo "Deleted Successfully";

}