<?php
  require "conn.php";
  $user_id = $_POST["username"];

  $query = "select s_id from Relations where u_id = '$user_id' and confirmed = 'NO';";

  $result = mysqli_query($conn, $query);
  if(mysqli_num_rows($result) > 0){
    $supervisor_id = mysqli_fetch_array($result);
    echo $supervisor_id[0];
  }

 ?>
