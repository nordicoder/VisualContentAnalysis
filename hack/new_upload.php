<?php
$ip = $_SERVER['REMOTE_ADDR'];
//$details = json_decode(file_get_contents("https://ipinfo.io/{$ip}/json"));
//echo "---".$details->city;
 
//$location = file_get_contents('http://freegeoip.net/json/'.$_SERVER['REMOTE_ADDR']);
 //print_r($location);
 //$urlTemplate = 'http://api.ip2location.com/?' . 'ip=%s&key=demo' .  '&package=WS10&format=json';
    $ip = $_SERVER['REMOTE_ADDR'];
$details = json_decode(file_get_contents("http://ipinfo.io/{$ip}/json"));
//echo $details->city;
//echo $details->country;
 $ipAddress = $ip;
 //$urlToCall = sprintf( $urlTemplate, $ipAddress);
 //$rawJson = file_get_contents( $urlToCall );
 //$geoLocation = json_decode( $rawJson, true );
 // $geoLocation['city_name'];
$file="child_predict.json";
$json = json_decode(file_get_contents($file),TRUE);
$a = array("34","sunny",$ip,"male", $details->city,$details->country);
$obj = new stdClass();
$obj->row = $a;
print json_encode($obj);
 array_push( $json['values'], $obj );
//$j=array("row" => json_encode($a));
//array_push($json->values,$j);
file_put_contents($file, json_encode($json,TRUE));
$ch = curl_init();
$url="https://api.havenondemand.com/1/api/sync/predict/v1?json=&url=http%3A%2F%2F54.200.209.157%2Fhack%2Fchild_predict.json&service_name=child_trial&apikey=0d247ded-018b-4733-98e1-6ae2e2079a7a";
curl_setopt($ch, CURLOPT_URL, $url);
// Set so curl_exec returns the result instead of outputting it.
curl_setopt($ch, CURLOPT_RETURNTRANSFER, true);
// Get the response and close the channel.
$response = curl_exec($ch);
//print $response;
$results = explode(',', $response);
$guess =  $results[count($results)-2];
echo "<br><br>";
echo "Probable: violator";
echo "<br><br>";
curl_close($ch);
 move_uploaded_file($_FILES["photo"]["tmp_name"], "uploads/" . $_FILES["photo"]["name"]);
                echo "Your file was uploaded successfully.";
$file_name=$_FILES["photo"]["name"];
echo $file_name;
$pieces = explode(".", $file_name);
exec('mkdir '.$pieces[0]);
exec('sudo chmod 777 *');
$query='ffmpeg -i uploads/'.$file_name.' -r 2 '.$pieces[0].'/out%01d.jpg';
exec('sudo chmod 777 *');
echo exec($query);
//$time=exec('ffmpeg -i uploads/'. $file_name .' 2>&1 | grep "Duration"| cut -d \' \' -f 4 | sed s/,// | sed \'s@\..*@@g\' | awk \'{ split($1, A, ":"); split(A[3], B, "."); print 3600*A[1] + 60*A[2] + B[1] }');
//$time = exec('ffmpeg -i uploads/'    .$file_name.    '2>&1 | grep "Duration"| cut -d \' \' -f 4 | sed s/,// | sed \'s@\..*@@g\' | awk \'{ split($1, A, ":"); split(A[3], B, "."); print 3600*A[1] + 60*A[2] + B[1] }\'',$time_arr);
//exec('javac CheckContent.java');
//exec('javac CheckForAgeRunnable.java');

/***file metadata ***/





exec('java CheckContent '.$pieces[0].' 100 2>&1',$lines);





echo "<br>";
$cnt = 0;
$len = count($lines);
for($x=0; $x<$len ; $x++) {
if(strpos($lines[$x], 'true') !== FALSE){
$cnt = $cnt + 1; 
$arr_url = explode(" ",$lines[$x]);
echo '<img src="'.$arr_url[1].'" />';

$handle = fopen('uploads/'.$file_name, "r");
require_once('getid3/getid3.php');
$getID3 = new getID3;
$file = $getID3->analyze('uploads/'.$file_name);
$file_meta="file_meta.json";
$json = json_decode(file_get_contents($file_meta),TRUE);
$a = array($ip,$file_name,$file['playtime_string'],$file['filesize'],$arr_url[1]);
$obj = new stdClass();
$obj->row = $a;
print json_encode($obj);
 array_push( $json['values'], $obj );
//$j=array("row" => json_encode($a));
//array_push($json->values,$j);
file_put_contents($file_meta, json_encode($json,TRUE));



} 
}
$percent = $cnt/$len;
if($guess == "violator")
{
$percent = $percent*1.2;
}
$percent_friendly = number_format( $percent * 100, 2 ) . '%';
echo '<form action="haven_train1.php" method="post" enctype="multipart/form-data">
      <div class="myDiv" >
	  <h2 class="main-tittle">Upload Video to verify</h2>
      <label class="for="fileSelect">Filename:</label>
      <input type="submit" name = "Decision" value="violator"/> <br>
     <input type="submit" name = "Decision" value="nonviolator"/> <br>
	  </div>
	  

		</form>';

//header("Location:filecreate.php?name=omkar");
  //exit();

//<form action = 'check_for_age.php' method = 'POST'>
//<input type = 'submit' value = 'check'>
//</form>
?>
