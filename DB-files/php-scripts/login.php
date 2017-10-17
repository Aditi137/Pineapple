<?php
require "conn.php";

$id = $_POST["username"];
$password = $_POST["password"];

$mysql_query= "select account_type from Accounts where id = '$id' AND password = '$password';";
$result = mysqli_query($conn, $mysql_query);
if(mysqli_num_rows($result) > 0){
  $account_type = mysqli_fetch_array($result);
  echo $account_type[0];
}
else{
  echo "Username or password was incorrect";
}
?>
