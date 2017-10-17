<?php
  require "conn.php";
  $name = $_POST["name"];
  $email = $_POST["email"];
  $id = $_POST["username"];
  $password = $_POST["password"];
  $account_type = $_POST["account_type"];

  $query = "insert into Accounts (name,email,id,password,account_type)
            values ('$name','$email','$id','$password','$account_type');";

  if ($result = mysqli_query($conn,$query)){
    echo "Success";
  }else{
    echo "Failure";
  }



 ?>
