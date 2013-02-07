<?php

$con = mysql_connect("localhost","seg","seggroup2");
if (!$con)
{
  	die('Could not connect: ' . mysql_error());
} 
mysql_select_db("martin_seg2", $con);

/*
	Unsurprisingly, returns (in our standard XML format) caches that haven't
	yet been admin approved. For use paired with 'approveCache.php' by an admin.
*/

$result = mysql_query("SELECT * FROM seg_Caches WHERE isApproved = '0'");

echo "<xml>";
while($row = mysql_fetch_array($result)){
	echo printCacheInXML($row);
}
echo "</xml>";

/*
	Prints out the data from a cache in XML form.
*/
function printCacheInXML($cache)
{
	echo "<cache>";
	echo "<id>";
	echo $cache['id'];
	echo "</id>";
	echo "<lat>";
	echo $cache['lat'];
	echo "</lat>";
	echo "<lon>";
	echo $cache['lon'];
	echo "</lon>";
	echo "<value>";
	echo $cache['value'];
	echo "</value>";
	echo "<isApproved>";
	echo $cache['isApproved'];
	echo "</isApproved>";
	echo "<state>";
	echo $cache['state'];
	echo "</state>";
	echo "<description>";
	echo $cache['description'];
	echo "</description>";
	echo "<currentDistance>";
	echo $cache['calcDist'];
	echo "</currentDistance>";
	echo "</cache>";
}


mysql_close($con);
?>