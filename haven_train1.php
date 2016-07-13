<?php
$var=$_POST['Decision'];
echo $var;



$ip = $_SERVER['REMOTE_ADDR'];
//$details = json_decode(file_get_contents("https://ipinfo.io/{$ip}/json"));
//echo "---".$details->city;
 

//$location = file_get_contents('http://freegeoip.net/json/'.$_SERVER['REMOTE_ADDR']);
 //print_r($location);
$details = json_decode(file_get_contents("http://ipinfo.io/{$ip}/json"));
echo $details->city;
echo $details->country;
echo json_encode($details);
 
 
 
 //$urlTemplate = 'http://api.ip2location.com/?' . 'ip=%s&key=demo' .  '&package=WS10&format=json';
    
 $ipAddress = $ip;

 //$urlToCall = sprintf( $urlTemplate, $ipAddress);

 //$rawJson = file_get_contents( $urlToCall );

 //$geoLocation = json_decode( $rawJson, true );

 // $geoLocation['city_name'];

$file="child_train.json";

$json = json_decode(file_get_contents($file),TRUE);

$a = array("34","sunny",$ip,"male", $details->city,$details->country,$var);
$obj = new stdClass();
$obj->row = $a;

print json_encode($obj);


 array_push( $json['values'], $obj );
//$j=array("row" => json_encode($a));

//array_push($json->values,$j);

file_put_contents($file, json_encode($json,TRUE));

$ch = curl_init();

$url="https://api.havenondemand.com/1/api/async/trainpredictor/v1?json=&url=http%3A%2F%2F54.200.209.157%2Fchild_train1.json&prediction_field=class&service_name=child_trial&apikey=0d247ded-018b-4733-98e1-6ae2e2079a7a";

//$url="https://api.havenondemand.com/1/api/sync/predict/v1?json=&url=http%3A%2F%2F54.200.209.157%2Fchild_predict.json&service_name=child_trial&apikey=0d247ded-018b-4733-98e1-6ae2e2079a7a";


curl_setopt($ch, CURLOPT_URL, $url);
// Set so curl_exec returns the result instead of outputting it.
curl_setopt($ch, CURLOPT_RETURNTRANSFER, true);
// Get the response and close the channel.
$response = curl_exec($ch);
print $response;


curl_close($ch);


?>