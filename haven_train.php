<?php
 print "lulz";
 
$ch = curl_init();
$url="https://api.havenondemand.com/1/api/async/trainpredictor/v1?json=&url=http%3A%2F%2F54.200.209.157%2Fchild_train1.json&prediction_field=class&service_name=child_trial&apikey=0d247ded-018b-4733-98e1-6ae2e2079a7a";
curl_setopt($ch, CURLOPT_URL, $url);
// Set so curl_exec returns the result instead of outputting it.
curl_setopt($ch, CURLOPT_RETURNTRANSFER, true);
// Get the response and close the channel.
$response = curl_exec($ch);
print $response;
curl_close($ch);
 
 
 ?>