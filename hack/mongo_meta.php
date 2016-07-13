<?php
$dbhost = 'localhost';
        $dbname = 'test';

        // Connect to test database
        $m = new Mongo("mongodb://$dbhost");
        $db = $m->$dbname;

        // Get the users collection
        $c_users = $db->meta;

        // Find the user with first_name 'MongoDB' and last_name 'Fan'
        //$user = array(
        //      'first_name' => 'MongoDB',
        //      'last_name' => 'Fan'
//$usertimeline = json_decode($usertimeline);

// Loop array and create seperate documents for each tweet
c_users->drop();

$file="file_meta.json";

$json = json_decode(file_get_contents($file),TRUE);

foreach ($json as $id => $item) {
   $c_users->insert($item);
}

?>

