<?php
include("koneksi.php");
error_reporting(E_ERROR|E_PARSE);
$sql = "select * from berita";
$result = $conn->query($sql);
$array = array();
if ($result->num_rows > 0) {
    while ($obj = $result->fetch_object()) {
        $array[] = $obj;
    }
    echo json_encode(array('result' => 'OK', 'data' => $array), JSON_PRETTY_PRINT);
} else {
    echo json_encode(array('result' => 'ERROR', 'message' => 'No data found'), JSON_PRETTY_PRINT);
    die();
}
?>
