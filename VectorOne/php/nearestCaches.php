<?php

$con = mysql_connect("localhost","seg","seggroup2");
if (!$con)
  {
  die('Could not connect: ' . mysql_error());
  }
mysql_select_db("martin_seg2", $con);


/*
	nearestCaches.php

	Either: returns the nearest X caches to the user's supplied position,
		Or: returns all caches within a user / app specified distance. Distance is in kilometres.

	Usage:
	1st type:
	e.g.: http://netroware.co.uk/test/nearestCaches.php?type=closestX&number=20&userlat=54.776070&userlon=-1.596955

	2nd type:
	e.g.: http://netroware.co.uk/test/nearestCaches.php?type=withinDistance&distance=20&userlat=54.776070&userlon=-1.596955

*/



$userLat = $_GET["userlat"];
$userLon = $_GET["userlon"];
// 'withinDistance' means all caches within this distance.
// must be paired with $userDistance = $_GET["distance"];
$type = $_GET["type"];

// Allowed distance between points to return.
$userDistance = $_GET["distance"];

// Maximum number of caches to return (when ordered).
$userNumber = $_GET["number"];

if (($userDistance == NULL) && ($type == 'withinDistance')) 
{
	echo "Please set a user defined distance for comparison. Setting to 50km as default.<br />";
	echo "Should be in the form ...&distance=10";
	echo "<br />";
	$userDistance = 50; // Default, for if a distance isn't set by user.
}

//$result = mysql_query("SELECT * FROM seg_Caches WHERE userName = '".$un1."'");

$result = mysql_query("SELECT * FROM seg_Caches");

//$testArray = array('key' => 'value', 'key' => 'value');
$cacheArray = array();
echo "<xml>";
while($row = mysql_fetch_array($result))
{
	//echo json_encode($row);

	$cacheLat = $row['lat'];
	//echo "Cache Latitude = ".$cacheLat.", ";
	$cacheLon = $row['lon'];
	//echo "Cache Longitude = ".$cacheLon.", ";

	if($type == 'withinDistance')
	{
		if(getDistance($userLat,$userLon,$cacheLat,$cacheLon) <= $userDistance )
		{
			// echo "Great success!";
			// echo json_encode($row);
			printCacheInXML($row);
			// echo "<br />";
		}
	} else if($type == 'closestX') {

		// Assigns the distance from user specified point to cache to 'calcDist'
		// Then adds to $cacheArray

		$calculatedDistance = getDistance($userLat,$userLon,$cacheLat,$cacheLon);
		//echo $calculatedDistance;
		$row['calcDist'] = $calculatedDistance;
		//echo " calculated distance test = ".$row['calcDist'];
		//echo "<br />";
		$cacheArray[] = $row;
	}

/*
	// Test: 		54.772289, -1.576860 -- Cathedral
	// Heather's: 	54.780593, -1.586237
	// Random: 		54.776070, -1.596955
	// HH: 			51.429437, -0.355403


	//$distLat = $cacheLat - $userLat;
	//echo "Distance in lat = ".$distLat.", ";

	//$distLon = $cacheLon - $userLon;
	//echo "Distance in lon = ".$distLon.", ";

	//$dist = sqrt(pow($distLon,2)+pow($distLat,2));
	//echo $dist;
	//echo "<br />";
	//echo "Overall distance = ".$dist;
*/
}
#

if($type == 'closestX') 
{	
	// echo "Closest caches to you are:<br />";
	aasort($cacheArray,"calcDist");
	// echo "<xml>";
	// for($i = 0;$i<20;$i++){
	// 	// echo "sweet!";
	// 	echo $cacheArray[$i]['calcdist'];
	// 	echo "<br />";
	// }

	/*
		Return the closest 20 caches.
		If there aren't 20 caches in the database, return them in order, closest first.
	*/

	$i=0;
	while (($i < $userNumber)&&($i<count($cacheArray))){
		foreach ($cacheArray as $key => $val) {
			// echo "$key = ".$val['calcDist'];
			// echo "<br />";
			printCacheInXML($val);
			// What format do they need to be printed out in?
			$i++;
		}
	}

	// echo "</xml>";
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

/*
	Gets the distance on the earth's surface between to points
	Input in decimal degrees
	Output in km.
*/
function getDistance($lat1, $lon1, $lat2, $lon2)
{
	$lat1 = deg2rad($lat1);
	$lat2 = deg2rad($lat2);
	$lon1 = deg2rad($lon1);
	$lon2 = deg2rad($lon2);

	$cosDSig = ((sin($lat1)*sin($lat2)) + ((cos($lat1)*cos($lat2))*cos($lon2 - $lon1)));

	$radDist = acos($cosDSig);

	// Not sure whether to use 6372.8 or 6371.01. 
	// See http://en.wikipedia.org/wiki/Great-circle_distance#Radius_for_spherical_Earth

	$dist1 = $radDist*6372.8;
    //echo "Example function.\n";
    return $dist1;
}

/*
	Sorts a multi-dimensional array by a value not on the surface layer
	http://stackoverflow.com/questions/2699086/sort-multidimensional-array-by-value-2
*/
function aasort (&$array, $key) 
{
    $sorter=array();
    $ret=array();
    reset($array);
    foreach ($array as $ii => $va) {
        $sorter[$ii]=$va[$key];
    }
    asort($sorter);
    foreach ($sorter as $ii => $va) {
        $ret[$ii]=$array[$ii];
    }
    $array=$ret;
}


mysql_close($con);
?>