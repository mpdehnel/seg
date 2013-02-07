<?php

$con = mysql_connect("localhost","seg","seggroup2");
if (!$con)
{
  	die('Could not connect: ' . mysql_error());
} 
mysql_select_db("martin_seg2", $con);

/*
	Allows an admin to approve a cache.
	For use paired with 'getUnapprovedCaches.php'.

	Pass it an admin's 'username' and the 'cacheID' to approve.

	e.g.: http://netroware.co.uk/test/approveCache.php?username=you&cacheID=15
*/


$username = $_GET["username"]; 
$cacheID = $_GET["cacheID"];

// Assume not admin.
$adminFlag = 0;
// Find out.
$result = mysql_query("SELECT * FROM seg_Users WHERE userName = '".$username."'");
$row = mysql_fetch_array($result);

if($row['isAdmin'] == 1){
	$adminFlag = 1;
} // Else do nothing.

if ($adminFlag == 1){
	mysql_query("UPDATE seg_Caches SET isApproved=1
	WHERE id='".$cacheID."'");
	echo "Cache #".$cacheID." (probably) approved successfully.";
} else {
	echo "You're not an admin, sorry.";
}

mysql_close($con);
?>