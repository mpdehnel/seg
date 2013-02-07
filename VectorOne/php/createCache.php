<?php

$con = mysql_connect("localhost","seg","seggroup2");
if (!$con)
{
  	die('Could not connect: ' . mysql_error());
} 
mysql_select_db("martin_seg2", $con);
/*
	createCache.php

	If the user is registered in the database as an admin, it will be automatically approved.
	If not, but the user is registered, it will be submitted, and 'approved' will still be 0.
	If the user is not registered, it won't do anything.

	Pass it a valid (admin or user) 'username', 'userlat'(itude), 'userlon'(gitude) and a 'description'.

	e.g.: http://netroware.co.uk/test/createCache.php?username=robby&userlat=54.1231&userlon=0.01200&description=MyDescription

*/


$username = $_GET["username"]; 
$userLat = $_GET["userlat"];
$userLon = $_GET["userlon"];
$description = $_GET["description"];



// Assume not admin.
$adminFlag = 0;
// Assume not valid username.
$validUser = 0;
// Find out
$result = mysql_query("SELECT * FROM seg_Users WHERE userName = '".$username."'");
$row = mysql_fetch_array($result);

if($row['isAdmin'] == 1){
	$adminFlag = 1;
} else if ($row['userName'] != null){
	$validUser = 1;
} // Else do nothing.


if(($userLat != null) && ($userLon != null) && ($description != null)){
	if($adminFlag == 1){
		mysql_query("INSERT INTO seg_Caches (lat, lon, isApproved, description, addedBy)
		VALUES ('".$userLat."', '".$userLon."','1', '".$description."', '".$username."')");
		echo "Cache successfully added to the database. ";
		echo "As you're an admin, this has automatically been approved.";
	} else if ($validUser == 1) {
		mysql_query("INSERT INTO seg_Caches (lat, lon, description, addedBy)
		VALUES ('".$userLat."', '".$userLon."', '".$description."', '".$username."')");
		echo "Cache successfully added to the database. ";
		echo "The cache will have to be approved by an admin before it will show up.";	
	} else {
		echo "Sorry, you're not a valid user. Please register first!<br />";
		echo $row['userName'];
		echo $username;
	}
	
} else {
	echo "You haven't submitted a 'userlat', 'userlon' and/or 'description'. Please try again.";
}


mysql_close($con);
?>