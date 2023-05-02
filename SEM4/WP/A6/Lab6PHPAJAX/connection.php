<?php

function OpenConnection(): mysqli
{
    $server = "localhost:3310"; //"localhost";
    $user = "root";
    $password = "";
    $database = "documents_db";

    $con = mysqli_connect($server, $user, $password, $database);

    if(!$con){
        die('Could not connect to DB');
    }
    
    return $con;
}

function CloseConnection(mysqli $con)
{
    $con->close();
}