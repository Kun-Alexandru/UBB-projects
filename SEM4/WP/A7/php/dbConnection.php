<?php

function OpenConnection(): mysqli
{
    $server = "localhost:3310";
    $user = "root";
    $password = "";
    $database = "documents_db";

    $con = mysqli_connect($server, $user, $password, $database);

    if(!$con){
        die('Could not connect to database!');
    }
    
    return $con;
}

function CloseConnection(mysqli $con): void
{
    $con->close();
}