<?php
  require "conn.php";
  $user_id = $_POST["username"];
  $supervisor_id = $_POST["supervisor_username"];

  $query = "update Relations set confirmed='YES' WHERE u_id='$user_id' and s_id='$supervisor_id';";
  $result = mysqli_query($conn, $query);

  ?>
