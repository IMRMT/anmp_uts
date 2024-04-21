<?php
header("Access-Control-Allow-Origin: *");
header("Access-Control-Allow-Headers: *");

include("koneksi.php");
error_reporting(E_ERROR|E_PARSE);

$name = $_POST['name'];
$password = $_POST['password'];

$stmt = $conn->prepare(
    "INSERT INTO user (username, password) VALUES (?,?)"
);
$stmt -> bind_param("ss", $username, $password);
if ($stmt->execute()){
    $arr = ["Result" => "Success"];
} else{
    $arr = ["Result" => "Error", "Message" => "Gagal Register"];
}
echo json_encode($arr);
$stmt->close();
$conn->close();

?>