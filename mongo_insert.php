<?php
   // connect to mongodb
echo "yo";   
$m = new MongoClient();
   echo "Connection to database successfully";
	
   // select a database
   $db = $m->test;
   echo "Database mydb selected";
   $collection = $db->mycoll;
   echo "Collection selected succsessfully";
	
   $document = array( 
      "title" => "MongoDB", 
      "description" => "database", 
      "likes" => 100,
      "url" => "http://www.tutorialspoint.com/mongodb/",
      "by", "tutorials point"
   );
	
   $collection->insert($document);
   echo "Document inserted successfully";
?>
