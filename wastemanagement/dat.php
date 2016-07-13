<?php
    if(isset($_GET["data"]) && isset($_GET["data2"]))
    {
        $data = $_GET["data"];
        $data2 = $_GET["data2"];
    
$doc = array(
    "cap" =>"akshay",
    "type" => "database",
    "count" => 1,
    "info" => (object)array( "x" => 203, "y" => 102),
    "versions" => array("0.9.7", "0.9.8", "0.9.9")
);

	$connection = new MongoClient();
	$collection = $connection->selectCollection("wastetest", "testcoll");

	$collection->insert( $doc );	

    }
?>
