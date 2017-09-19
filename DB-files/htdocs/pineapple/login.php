<?php
require "conn.php";

$id = $_POST["username"];
$password = $_POST["password"];

$mysql_query= "select * from Accounts where id = '$id' AND password = '$password';";
$result = mysqli_query($conn, $mysql_query);
if(mysqli_num_rows($result) > 0){
  echo "Success";
}
else{
  echo "Fail";
}
?>
