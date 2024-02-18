import java.sql.SQLOutput;

public class App {
    public static void main(String[] args)
    {
        String bio = "Hi I am sad";
        Task1And2 task = new Task1And2();

        System.out.println("Testing Contains Slang....\n");
        if(!task.containsGenZSlang(bio))
        {
            System.out.println("Contains declines correctly");
        }
        else{
            System.out.println("Issue with contains");
        }
        bio = "";
        if(!task.containsGenZSlang(bio))
        {
            System.out.println("Contains declines correctly");
        }
        else{
            System.out.println("Issue with contains");
        }
        bio = "I racecar no cap";
        if(task.containsGenZSlang(bio))
        {
            System.out.println("Contains accepts correctly");
        }
        else{
            System.out.println("Issue with contains");
        }
        System.out.println("Testing Contains slang Complete\n");

        System.out.println("Testing is special length....");
        String char42 = "Lorem ipsum dolor sit amet, consectetur adipiscing";
        if(task.isSpecialLength(char42))
        {
            System.out.println("Accepts correct bio");
        }
        else{
            System.out.println("Declines correct bio");
        }
        if(!task.isSpecialLength(""))
        {
            System.out.println("Declines wrong bio");
        }
        else{
            System.out.println("Accepts wrong bio");
        }
        if(!task.isSpecialLength("Hi my name jef"))
        {
            System.out.println("Declines wrong bio");
        }
        else{
            System.out.println("Accepts wrong bio");
        }
        System.out.println("Testing special length Complete\n");

        System.out.println("Testing isPalindrome...");
        String palindrome = "racecar";
        if(task.isPalindrome(palindrome))
        {
            System.out.println("Correctly identifies palindrome");
        }
        else{
            System.out.println("Does not detect palindrome");
        }
        bio = "I racecar no cap";
        if(task.containsPalindrome(bio))
        {
            System.out.println("Correctly identifies palindrome");
        }
        else{
            System.out.println("Does not detect palindrome");
        }
        if(!task.isPalindrome("was"))
        {
            System.out.println("Declines wrong word");
        }
        else{
            System.out.println("Accepts wrong word");
        }

        if(!task.containsPalindrome("I was hungry"))
        {
            System.out.println("Declines wrong bio");
        }
        else{
            System.out.println("Accepts wrong word");
        }
        if(!task.containsPalindrome(""))
        {
            System.out.println("Declines empty bio");
        }
        else{
            System.out.println("Accepts wrong word");
        }
        if(!task.isPalindrome(""))
        {
            System.out.println("Declines empty word");
        }
        else{
            System.out.println("Accepts wrong word");
        }
        System.out.println("Testing palindrome Complete\n");
    }
}
