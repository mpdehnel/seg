<?php
$con = mysql_connect("localhost","seg","seggroup2");
if (!$con)
  {
  die('Could not connect: ' . mysql_error());
  }

// some code

mysql_select_db("martin_seg2", $con);

$result = mysql_query("SELECT * FROM seg_Users");

while($row = mysql_fetch_array($result))
  {
  	echo json_encode($row);
  //echo $row['email'] . " " . $row['password'];
  echo "<br />";
  }


mysql_close($con);
?>
