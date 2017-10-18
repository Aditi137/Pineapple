<?php
  require "conn.php";

  $s_id = $_GET["my_username"];

  $query = $query = "select a.id as id, a.name as name, a.email as email from Relations r, Accounts a where (r.s_id = '$s_id') and (r.confirmed ='YES') and (r.u_id=a.id);";;
  $result = mysqli_query($conn,$query);

  while($user=mysqli_fetch_assoc($result)){
        $output[]=$user;
    }

  print(json_encode(array('users'=>$output)));


 ?>
