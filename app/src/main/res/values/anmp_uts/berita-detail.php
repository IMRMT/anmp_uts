<?php

$jsonString = file_get_contents('../berita/beritas.json');

$data = json_decode($jsonString, true);

if(isset($_GET['id'])){
    $id = $_GET['id'];
}else $id = 1;

$result = array();

foreach($data as $item){
    if($item['id']==$id){
        $result = $item;
    }
}

echo json_encode($result);
?>
