<p>
  <?php
require "conn.php";

$id="1337";
$password="pw123";
$mysql_query= "select * from Accounts where id = '$id' AND password = '$password';";
$result = mysqli_query($conn, $mysql_query);
if(mysqli_num_rows($result) > 0){
  echo "Login successfull";
}
else{
  echo "Login failed";
}
?>
</p>
