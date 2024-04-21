<?php
error_reporting(E_ERROR | E_PARSE);
include("koneksi.php");
error_reporting(E_ERROR|E_PARSE);
$conn->set_charset("UTF8");

$idUsers = (int) $_POST['idUsers'];

    $c->set_charset("UTF8");
    $sql = "SELECT * from user where id = 1;";
    $result = $conn->query($sql);
    if ($result->num_rows > 0) {
        while ($obj = $result->fetch_object()) {
            $array[] = $obj;
        }
        echo json_encode(array('result' => 'OK', 'data' => $array));
    } else {
        echo json_encode(array('result' => 'ERROR', 'message' => 'No data found'));
        die();
    }
$c->close();
