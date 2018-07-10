package test;
public class StoryNode{
   private String storyText;
   private String choice1;
   private String choice2;
   
   public StoryNode(String text, String c1, String c2){
      storyText = text;
      choice1 = c1;
      choice2 = c2;
   }
   
   public String getStory(){
      return storyText;
   }
   public String getLeftChoice(){
      return choice1;
   }
   
   public String getRightChoice(){
      return choice2;
   }     
}