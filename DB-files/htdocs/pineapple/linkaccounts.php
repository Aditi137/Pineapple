<?php
  require "conn.php";
  $user_id = $_POST["supervisee_id"];
  $supervisor_id = $_POST["my_id"];

  $get_user_id = "select id from Accounts where id='$user_id'";
  $user_check = mysqli_query($conn, $get_user_id);
  if(mysqli_num_rows($user_check) > 0){
    $query = "insert into Relations (s_id,u_id,confirmed)
              values ('$user_id','$supervisor_id','NO');";

    if ($result = mysqli_query($conn,$query)){
      echo "Success";
    }
  }
 ?>
