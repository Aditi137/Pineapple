<?php
require "conn.php";
$email = $_POST["email"];
$query = "select password from Accounts where email = '$email';";
$result = mysqli_query($conn, $query);

if(mysqli_num_rows($result) > 0){
  echo "pwreset";
}
else{
  echo "The email you entered is not registered to any account";
}
?>
