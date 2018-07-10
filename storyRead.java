package test;
import javax.swing.JOptionPane;
import net.datastructures.LinkedBinaryTree;
import net.datastructures.Position;
import java.io.PrintWriter;
import java.io.FileReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import net.datastructures.LinkedStack;

public class storyRead{

   private static String inStory = null;
   private static String inLeft = null;
   private static String inRight = null;
   private static String inIndicator = null;
   private static LinkedBinaryTree<StoryNode> leftPop = new LinkedBinaryTree<>();
   private static LinkedBinaryTree<StoryNode> rightPop = new LinkedBinaryTree<>();
   private static LinkedBinaryTree<StoryNode> inTree = new LinkedBinaryTree<>();
   private static LinkedStack<LinkedBinaryTree<StoryNode>> treeStack = new LinkedStack<>();
   private static StoryNode builder;
   private static Position<StoryNode> p;
   private static String infileName;
   
   public static void main(String[] args){
      
      String[] loadChoice = {"Load default game", "Enter custom file"};
      int x = JOptionPane.showOptionDialog(null, "Would you like to load the default game or enter a custom file to be read?","Choose your own adventure!",JOptionPane.YES_NO_CANCEL_OPTION,JOptionPane.QUESTION_MESSAGE,null,loadChoice,loadChoice[1]);
         if(x == 0)
            infileName = "lindsey-william";
         if(x == 1)
            infileName = JOptionPane.showInputDialog("Please enter the name of the file to be read from.");
      if(!infileName.endsWith(".txt"))
         infileName += ".txt";
      File inputFile = new File(infileName);
      Scanner inMan = null;
      try{
         inMan = new Scanner(inputFile);
      }
      catch(FileNotFoundException f){
         System.err.println("Error, couldn't find file");
         System.exit(1);
      }
      while(inMan.hasNext()){
         String inputLine = inMan.nextLine();
         //System.out.println(inputLine);                   Prints off story as it is read in for debugging
         if(!inputLine.equals("Empty")){
            inStory = String.format(inputLine);
            inLeft = inMan.nextLine();
            inRight = inMan.nextLine();
            inIndicator = inMan.nextLine();
         }
         else{
            inStory = "Empty";
            inLeft = null;
            inRight = null;
            inIndicator = "end";
         }
         builder = new StoryNode(inStory, inLeft, inRight);
         inTree.addRoot(builder);
         
         if(inIndicator.equals("end")){
            treeStack.push(inTree);
            if(!inIndicator.equals("end")){
               if(treeStack.size() < 2){
                  System.out.println("Something is wrong here");
                  System.exit(1);
               }
            }
         }    
         else{
            rightPop = treeStack.pop();
            leftPop = treeStack.pop();
            if( leftPop == null || leftPop.size() < 1 ){
               LinkedBinaryTree<StoryNode> emptyMan = new LinkedBinaryTree<>();     // if leftPop is null or less than size one, create a new storyNode that resembles a empty storyNode
               StoryNode fake = new StoryNode("Empty", null, null);
               emptyMan.addRoot(fake);
               leftPop = emptyMan;
            }
            if( rightPop == null || rightPop.size() < 1 ){
               LinkedBinaryTree<StoryNode> emptyMan = new LinkedBinaryTree<>();     // if rightPop is null or less than size one, create a new storyNode that resembles a empty storyNode
               StoryNode fake = new StoryNode("Empty", null, null);
               emptyMan.addRoot(fake);
               rightPop = emptyMan;
            }
            if( leftPop.root().getElement().getStory().equals("Empty") ){
               LinkedBinaryTree<StoryNode> emptyMan = new LinkedBinaryTree<>();
               leftPop = emptyMan;
            }
            if( rightPop.root().getElement().getStory().equals("Empty") ){
               LinkedBinaryTree<StoryNode> emptyMan = new LinkedBinaryTree<>();
               rightPop = emptyMan;
            }
            inTree.attach(inTree.root(), leftPop, rightPop);
            treeStack.push(inTree);
         }
         inTree = new LinkedBinaryTree<>();
      }
      inTree = treeStack.pop();
      
      /*
            At this point, the tree has been constructed after being read in from the 
            file the user has given. The following code is the game player.
      
      */
       
      boolean replay = true;
      p = inTree.root();
      while( replay ){
         while(inTree.numChildren(p) >= 1){
            if(inTree.numChildren(p) == 1){
               String[] oneChoice = {p.getElement().getLeftChoice()};
               int n = JOptionPane.showOptionDialog(null,
               p.getElement().getStory(),"Choose your own adventure!",JOptionPane.YES_NO_CANCEL_OPTION,JOptionPane.QUESTION_MESSAGE,null,oneChoice,oneChoice[0]);
               if(n == 0){
                  if(inTree.left(p) == null){
                     p = inTree.right(p);
                  }
                  else{
                     p = inTree.left(p); 
                  }
               }
            }
            if(inTree.numChildren(p) == 2){
               String[] leftrightChoice = {p.getElement().getLeftChoice(), p.getElement().getRightChoice()};
               int n = JOptionPane.showOptionDialog(null,
               p.getElement().getStory(),"Choose your own adventure!",JOptionPane.YES_NO_CANCEL_OPTION,JOptionPane.QUESTION_MESSAGE,null,leftrightChoice,leftrightChoice[1]);
               if(n == 0){
                  if(inTree.left(p) == null){
                     p = inTree.right(p);
                  }
                  else{
                     p = inTree.left(p);
                  }
               }
               if(n==1){
                  if(inTree.right(p) == null){
                     p = inTree.left(p);
                  }
                  else{
                     p = inTree.right(p);
                  }
               } 
            }
         }        
         if(inTree.numChildren(p) <= 1){
            String[] oneChoice = {p.getElement().getLeftChoice()};
            int n = JOptionPane.showOptionDialog(null,
            p.getElement().getStory(),"Choose your own adventure!",JOptionPane.YES_NO_CANCEL_OPTION,JOptionPane.QUESTION_MESSAGE,null,oneChoice,oneChoice[0]);
            if(n == 0){
               if(inTree.left(p) == null){
                  p = inTree.right(p);
               }
               else{
                  p = inTree.left(p);
               }
            } 
            String[] replayChoice = {"Yes", "No"};
            int b = JOptionPane.showOptionDialog(null,"Would you like to replay?","Choose your own adventure!",JOptionPane.YES_NO_CANCEL_OPTION,JOptionPane.QUESTION_MESSAGE,null,replayChoice,replayChoice[1]);
            if(b == 0)
               p = inTree.root();
            if(b == 1 )
               replay = false;
         }
         p = inTree.root();
      }    
  }
}
