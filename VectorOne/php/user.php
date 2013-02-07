<?php

$con = mysql_connect("localhost","seg","seggroup2");
if (!$con)
  {
  die('Could not connect: ' . mysql_error());
  }


/*
    user.php

    Input: 'type' you want the data returned in (xml or json), 'uname' (i.e. the username required)
    Returns user-data from the db about a requested user, 'uname'.

    e.g.: http://netroware.co.uk/test/user.php?type=xml&uname=robby

*/

$un1 = $_GET["uname"];

mysql_select_db("martin_seg2", $con);

$result = mysql_query("SELECT * FROM seg_Users WHERE userName = '".$un1."'");

if($_GET["type"] == xml){
	echo "<xml>";
}


while($row = mysql_fetch_array($result))
  {
  	if($_GET["type"] == json){
  		echo json_encode($row);	
  	} else if ($_GET["type"] == xml) {
  		//echo $row
  		echo( "<user>");
  		echo "<username>".$row['userName']."</username>";
  		echo "<team>".$row['team']."</team>";
  		// assumes totalCaches == total caches visited.
  		echo "<totalcaches>".$row['totalCaches']."</totalcaches>";
  		echo "<totalpoints>".$row['totalPoints']."</totalpoints>";
  		// unsure what avatar is in the db... is this just one
  		// from a preselected number of icons?
  		echo "<avatar>".$row['avatar']."</avatar>";
  		echo "</user>";

  		//echo $row['userName'] . " " . $row['LastName'];
  	}
  	
  //echo $row['email'] . " " . $row['password'];
  //echo "<br />";
  } 


if($_GET["type"] == xml){
	echo "</xml>";
}


// TODO: Create a page that's for getting login data
// 		 vs one that's just for public stuff (i.e. this one?)

mysql_close($con);


// Type of format please:
/*
echo("<xml>");
echo( "<user>");
echo( "<nickname> robby </nickname>");
echo( "<team> BLUE </team>");
echo( "<totalcaches>");
echo(5);
echo( "</totalcaches>" );
echo( "<totalpoints>");
echo(4);
echo( "</totalpoints>" );
echo( "<avatar>" );
echo(100);
echo("</avatar>");
echo( "</user>");
echo("</xml>");
*/

// 





?>
