<?php
  require "conn.php";
  $user_id = $_POST["username"];
  $supervisor_id = $_POST["supervisor_username"];

  $query = "delete from Relations where u_id='$user_id' and s_id='$supervisor_id';";
  $result = mysqli_query($conn, $query);

  ?>
