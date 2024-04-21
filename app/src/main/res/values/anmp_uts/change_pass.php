<?php
header("Access-Control-Allow-Origin: *");
header("Access-Control-Allow-Headers: *");

include("koneksi.php");
error_reporting(E_ERROR|E_PARSE);

$id = $_POST['idUsers'];
$newPass = $_POST['new_password'];
$array = array();

$stmt = $conn->prepare("UPDATE user SET password = ? WHERE id = ?");
    $stmt->bind_param("si", $newPass, $id);

    if ($stmt->execute()) {
    $affectedRows = $stmt->affected_rows;

    if ($affectedRows > 0) {
        $arr = array(
            "result" => "OK",
            "message" => "Password Successfully Updated!",
            "affected_rows" => $affectedRows
        );
    } else {
        $arr = array(
            "result" => "OK",
            "message" => "No changes made to the password",
            "affected_rows" => 0
        );
    }
} else {
    $arr = array(
        "result" => "error",
        "message" => "Failed Update Password!",
        "affected_rows" => 0
    );
}

echo json_encode($arr);

$stmt->close();
$c->close();
?>
