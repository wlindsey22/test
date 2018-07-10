package test;
import java.io.IOException;
import net.datastructures.ArrayList;
import javax.swing.JOptionPane;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.jsoup.HttpStatusException;
import net.datastructures.LinkedBinaryTree;
import java.io.PrintWriter;
import java.io.FileReader;
import java.io.File;
import java.io.FileNotFoundException;
import net.datastructures.LinkedStack;

 public class musicApplication {
	 private static PrintWriter writer;

	public static void main(String[] args) {
		runStart();
		
		  String fileName = "music-songs.txt";
	      File songList = new File( fileName );
	      File parent = songList.getParentFile();
	      if( parent != null ){
	         // file path may not exist, may not have permission to create files there
	         boolean failed = false;
	         try{
	            failed = !parent.mkdirs();
	         }
	         catch( SecurityException i ){
	            System.out.println("Do not have permission to create folder structure.");
	            System.exit(1);
	         }           
	      }
	      writer = null;
	      try{
	         writer = new PrintWriter( songList );
	      }
	      catch( FileNotFoundException i ){
	            System.out.println("Couldn't create file at that location.");
	            System.exit(1);
	      }
	      // catch SecurityException again; you may need to do this. We could have had write permission to parent folders, but not this one.
	      // catch UnsupportedEncoding exception as well
	      try {
		      Document doc = Jsoup.connect("http://www.billboard.com/charts/r-b-hip-hop-songs").userAgent("Mozilla/17.0").get();
		      Elements temp=doc.select("h2.chart-row__song");
		      int j = 0;
		      ArrayList<String> musicList = new ArrayList<>();
		      for(Element returnList:temp){
		         String c = returnList.getElementsByTag("h2").first().text();
		      	musicList.add( j , returnList.getElementsByTag("h2").first().text());
		      	j++;
		      }
		      writer.println(musicList);
		     }
		     catch (java.net.UnknownHostException e) {System.out.println("You are not connected to the internet.");}
		     catch (IOException e) {e.printStackTrace();}
	     
	      writer.close();
		
		
		
		
	}
	public static void runStart() {
			
			String[] genreChoice = {"Country", "Hip Hop", "Rock"};
            int n = JOptionPane.showOptionDialog(null, "Please select a genre","Music Application",JOptionPane.YES_NO_CANCEL_OPTION,JOptionPane.QUESTION_MESSAGE,null,genreChoice,genreChoice[2]);
         if(n == 0) runCountry();
         if(n == 1) runHipHop();
         //if(n == 2) runRock();
	}
	public static void billBoardCom(){
		     
	}
	public static void hotNewHipHop(){
		     try {
		      Document doc = Jsoup.connect("http://www.capitalxtra.com/playlists/best-2017/hip-hop-songs/").userAgent("Mozilla/17.0").get();
		      Elements temp=doc.select("h2");
		      int i = 0;
		      ArrayList<String> songList = new ArrayList<>();
		      	for(Element returnList:temp){
		         String c = returnList.getElementsByTag("h2").first().text();
		         songList.add( i , returnList.getElementsByTag("h2").first().text());
		         i++;
		         }
		      	 songList.remove(0);
		         songList.remove(songList.size() - 1);
		      	JOptionPane.showMessageDialog(null,  toStringPrint(songList));
		     }
		   catch (java.net.UnknownHostException e) {System.out.println("You are not connected to the internet.");}
		   catch (IOException e) {e.printStackTrace();}
	}
	public static void newHipHop2() {  
				   try {
					  String custom = JOptionPane.showInputDialog("Please enter a url you wish to get a list from.");
				      Document doc = Jsoup.connect("https://www.thetoptens.com/greatest-80s-rock-songs/").userAgent("Mozilla/17.0").get();
				      Elements temp=doc.select("i hasimage hassample image100");
				      int i = 0;
				      int u = 0;
				      ArrayList<String> songList = new ArrayList<>();
				      for(Element returnList:temp){
				         String d = returnList.getElementsByTag("b").first().text();
				         System.out.println(i+ " "+ returnList.getElementsByTag("i hasimage hassample image100").first().text());
				         songList.add( u , returnList.getElementsByTag("").first().text());
				         u++;
				         }
				      JOptionPane.showMessageDialog(null,  toStringPrint(songList));
				   }
			       catch (HttpStatusException e) {System.out.println("Invalid website");}
				   catch (java.lang.IllegalArgumentException e) {e.printStackTrace();}
				   catch (java.net.UnknownHostException e) {System.out.println("You are not connected to the internet.");}
				   catch (IOException e) {e.printStackTrace();}
	}
	public static void runHipHop() {
				  String [] hiphopChoice = {"Billboard", "Capitalxtra", "Custom"};
				  int n = JOptionPane.showOptionDialog(null, "Please select a website","Music Application",
						  JOptionPane.YES_NO_CANCEL_OPTION,JOptionPane.QUESTION_MESSAGE,null,hiphopChoice,hiphopChoice[2]);
				  if(n == 0) billBoardCom();
				  if(n == 1) hotNewHipHop();
				  if(n == 2) newHipHop2();
				  	
				  	
	}
	public static void newCountry() {
				  try {
				      Document doc = Jsoup.connect("http://www.wideopencountry.com/new-country-songs-for-october-2017/").userAgent("Mozilla/17.0").get();
				      Elements temp=doc.select("h2");
				      int i = 0;
				      ArrayList<String> songList = new ArrayList<>();
				      	for(Element returnList:temp)
				      	{
				      	int j = 0;
				         i++;
				         System.out.println(i+ " "+ returnList.getElementsByTag("h2").first().text());
				         String c = returnList.getElementsByTag("h2").first().text();
				         if(c.contains("—")){songList.add( j , returnList.getElementsByTag("h2").first().text());}
				         j++;
				        }
				      JOptionPane.showMessageDialog(null,  toStringPrint(songList));
				   }
				    catch (java.net.UnknownHostException e) {System.out.println("You are not connected to the internet.");}
				    catch (IOException e) {e.printStackTrace();}
	}
	public static void runCountry() {
			   String [] hiphopChoice = {"Wide Open Country"};
				  int n = JOptionPane.showOptionDialog(null, "Please select a website","Music Application",
						  JOptionPane.YES_NO_CANCEL_OPTION,JOptionPane.QUESTION_MESSAGE,null,hiphopChoice,hiphopChoice[0]);
				  if(n == 0) newCountry();
		   }
		 
		 public static String toStringPrint(ArrayList<String> songss) {
			    StringBuilder sb = new StringBuilder("");
			    for (int y = songss.size()-1; y >= 0; y--) {
			      if (y > 0) sb.append(" \n ");
			      sb.append(songss.get(y));
			    }
			    sb.append("\n");
			    return sb.toString();
			  }
}