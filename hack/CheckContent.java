import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Scanner;


public class CheckContent {

	 static String file_name;
	 static int duration;
		 
	public CheckContent(){
	}
	
	public static void main(String[] args) {
		
		CheckContent checkContent = new CheckContent();
		
		if(args.length < 2)
			return;
		
		 file_name = args[0];

		 duration = Integer.parseInt(args[1]);
//System.out.println(duration+"");
			

		checkContent.execute();
		
		
		
	}

	private void execute() {

				

		for(int i=1; i<=duration; i++){
			
			Thread thread = new Thread(new CheckForAgeRunnable(generateURL(i),file_name +"/out"+i+".jpg"));
			thread.start();
			
		}
		
//		executorService.execute(new CheckForAgeRunnable(Constants.generateURL(file_name), this));
	}

	/*public void onChildCheckComplete(boolean isChild) {
			System.out.println("DONE --> "+isChild);		
	}*/
	
	public class Constants {

		public static final String BASE_URL = "https://api.havenondemand.com/1/api/sync/detectfaces/v1";
		
		public static final String API_KEY = "988379e4-a819-432d-be72-f8e7cac795b6";//"3edd4ca8-b577-4fc1-bc21-ba7c7b015fe4";

		
		
	}
	public  String generateURL(int filepath){
		
		String fileurl = "http://54.200.209.157/"+ file_name +"/out"+filepath+".jpg";
		
		String url = Constants.BASE_URL + "?apikey="+Constants.API_KEY+"&url="+fileurl+"&additional=true";
		
		//System.out.println(url);
		
		return url;
		
	}
	
	
	
	public class CheckForAgeRunnable implements Runnable {

		String URL_STRING;
		String fn;

		public CheckForAgeRunnable(String url, String fn) {
			this.fn = fn;
			this.URL_STRING = url;

		}

		public void run() {

			try {
				
			
				
				//String fname = "out"+getNum();
				/** STEP 1 -- URL */
				URL url = new URL(URL_STRING);
				/** STEP 2 -- Open Connection */
				HttpURLConnection connection = (HttpURLConnection) url
						.openConnection();

				InputStream in = connection.getInputStream();
				
				String data = convertStreamToString(in);
				//System.out.println(data);
				if(data!=null && !data.equals("")){
					if(data.contains("error")|| data.contains("failed") ){
							
					}else{

					if(data.contains("child") )//|| data.contains("youth"))
					//if(isChild(data))
						System.out.println("true "+fn);
					else {
						System.out.println("false");
					}
					}
				}
				
			} catch (Exception e) {
//				e.printStackTrace();
				//System.out.println("false");
			}
		}

		public String convertStreamToString(java.io.InputStream is) {
		    String str = "";
			Scanner s = new Scanner(is);
		    s.useDelimiter("\\A");
		    if(s.hasNext())
		    	str = s.next();
		   	s.close();
		    
		    return str;
		}
		/*
		{"face":
			[
			 {   "additional_information":
			        {"age":"youth"},
				 "top":155,
				 "left":184,
				 "width":348,
				 "height":348
			  }
			 ]
		}*/
		/*public boolean isChild(String resp){
			boolean child = false;
			try{
				JSONObject jsonObject = new JSONObject(resp);				
				if(!jsonObject.isNull("face"))
				{
				JSONArray jsonArray = jsonObject.getJSONArray("face");
				for(int i=0;i<jsonArray.length();i++){
					
					JSONObject jO = jsonArray.getJSONObject(i);
					
						JSONObject additional_info = null;
						if(!jO.isNull("additional_information")){
							additional_info = jO.getJSONObject("additional_information");
							if(!additional_info.isNull("age")){
								String age = additional_info.getString("age");
								
								if(age.equals("child") || age.equals("youth")){
									child = true;
								}
							}
						}
				}
				}
			}catch(Exception e){
				e.printStackTrace();
			}
			return child;
		}	*/
	}


	
}
