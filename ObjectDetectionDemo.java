
import java.awt.MouseInfo;
   import java.awt.Robot;

   import org.bytedeco.javacpp.Loader;
   import org.bytedeco.javacpp.opencv_core;
   import org.bytedeco.javacpp.opencv_highgui;
   import org.bytedeco.javacpp.opencv_imgproc;
import org.bytedeco.javacpp.opencv_objdetect.CvHaarClassifierCascade;
import org.bytedeco.javacpp.helper.opencv_objdetect;
   import org.bytedeco.javacv.CanvasFrame;

   public class ObjectDetectionDemo
   {
     static Robot rob;
     static int lastx;
     static int lasty;
     static int dellx;
     static int delly;
     static int currx;
     static int curry;
   static int flag = 0;
  static int nowx; static int nowy = 1;
  static int totalFaces = 1;
     
     public static void main(String[] args) throws Exception
     {
     Runnable r = new Runnable()
       {

         public void run() {}
   
   
   
   
   
   
   
   
   
    };
    Thread t = new Thread(r);
     t.start();
       
   
   
     lastx = lasty = 0;
       
     rob = new Robot();
    Loader.load(org.bytedeco.javacpp.opencv_objdetect.class);
       
   
  CvHaarClassifierCascade faceClassifier = loadHaarClassifier(
      "G:/home/work/handsome dependancies/haarcascade_frontalface_default.xml");
  
  CvHaarClassifierCascade faceClassifier1 = loadHaarClassifier(
	      "G:/home/work/handsome dependancies/haarcascade_lefteye_2splits.xml");
  
  CvHaarClassifierCascade faceClassifier2 = loadHaarClassifier(
	      "G:/home/work/handsome dependancies/haarcascade_righteye_2splits.xml");
  
  CvHaarClassifierCascade faceClassifier3 = loadHaarClassifier(
	      "G:/home/work/handsome dependancies/haarcascade_mcs_nose.xml");
       
   
   
   
     opencv_highgui.CvCapture capture = opencv_highgui.cvCreateCameraCapture(0);
       
     opencv_highgui.cvSetCaptureProperty(capture, 
      4, 320.0D);
     opencv_highgui.cvSetCaptureProperty(capture, 
       3, 480.0D);
       
   
     opencv_core.IplImage grabbedImage = opencv_highgui.cvQueryFrame(capture);
     opencv_core.IplImage mirrorImage = grabbedImage.clone();
    opencv_core.IplImage grayImage = opencv_core.IplImage.create(mirrorImage.width(), 
       mirrorImage.height(), 8, 1);
       
     opencv_core.CvMemStorage faceStorage = opencv_core.CvMemStorage.create();
     opencv_core.CvMemStorage handStorage = opencv_core.CvMemStorage.create();
       
   
    CanvasFrame frame = new CanvasFrame("Object Detection Demo", 1.0D);
       
   
   
    while ((frame.isVisible()) && 
      ((grabbedImage = opencv_highgui.cvQueryFrame(capture)) != null))
       {
   
   
       opencv_core.cvClearMemStorage(faceStorage);
       opencv_core.cvClearMemStorage(handStorage);
         
   
     opencv_core.cvFlip(grabbedImage, mirrorImage, 1);
         
      opencv_imgproc.cvCvtColor(mirrorImage, grayImage, 6);
         
      findAndMarkObjects(faceClassifier, handStorage, 
         opencv_core.CvScalar.BLUE, mirrorImage, mirrorImage);
      findAndMarkObjects(faceClassifier1, handStorage, 
    	         opencv_core.CvScalar.RED, mirrorImage, mirrorImage);
      
      findAndMarkObjects(faceClassifier2, handStorage, 
 	         opencv_core.CvScalar.RED, mirrorImage, mirrorImage);
      
      findAndMarkObjects(faceClassifier3, handStorage, 
  	         opencv_core.CvScalar.GREEN, mirrorImage, mirrorImage);
    	         
   
       frame.showImage(mirrorImage);
       }
       
   
     frame.dispose();
    opencv_highgui.cvReleaseCapture(capture);
     }
private static CvHaarClassifierCascade loadHaarClassifier(String classifierName)
{
	
	CvHaarClassifierCascade classifier = new CvHaarClassifierCascade(
	      opencv_core.cvLoad(classifierName));
		    if (classifier.isNull()) {
	      System.err.println("Error loading classifier file \"" + classifier + 
		         "\".");
	     System.exit(1);
			       }
			       
		     return classifier;
	
	// TODO Auto-generated method stub
}
     
   
   
   
   
   
   
     private static void findAndMarkObjects(CvHaarClassifierCascade classifier, opencv_core.CvMemStorage storage, opencv_core.CvScalar colour, opencv_core.IplImage inImage, opencv_core.IplImage outImage)
     {
     opencv_core.CvSeq faces = org.bytedeco.javacpp.helper.opencv_objdetect.cvHaarDetectObjects(inImage, classifier, 
      storage, 1.1D, 1, 1);
       
    totalFaces = faces.total();
       
     if ((totalFaces == 0) && (flag == 0))
       {
      rob.keyPress(32);
     rob.keyRelease(32);
     System.out.println("pause");
     flag = 1;
       }
    else if ((totalFaces > 0) && (flag == 1))
       {
       rob.keyPress(32);
      rob.keyRelease(32);
       System.out.println("unpause");
      flag = 0;
       }
       
   
   
       for (int i = 0; (i < totalFaces) && (totalFaces < 2); i++) {
      opencv_core.CvRect r = new opencv_core.CvRect(opencv_core.cvGetSeqElem(faces, i));
         
      int x = r.x();int y = r.y();int w = r.width();int h = r.height();
       opencv_core.cvRectangle(outImage, opencv_core.cvPoint(x, y), opencv_core.cvPoint(x + w, y + h), 
         colour, 1, 16, 0);
       nowx = (x + w) / 2;
       nowy = (y + h) / 2;
       if ((lastx == 0) || (lasty == 0)) {
         lastx = nowx;
         lasty = nowy;
         }
         else
         {
         dellx = lastx - 
           nowx;
         delly = lasty - nowy;
         currx = (int)MouseInfo.getPointerInfo().getLocation().getX();
         curry = (int)MouseInfo.getPointerInfo().getLocation().getY();
           
         System.out.println("area ist " + (x + w) * (y + h));
         lastx = nowx;
         lasty = nowy;
         }
       }
     }
     
   
   
   
   
   
   
   
   
   
    
   }


/* Location:              C:\Users\OMKAR\Documents\showstopper.jar!\ObjectDetectionDemo.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       0.7.1
 */