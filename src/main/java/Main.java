import java.util.*;

public class Main {
    public static void echoCommand(String input)
    {
        System.out.println(input);
    }
    
    public static void checkCommandType(String input){
        ArrayList<String> listOfCommands = new ArrayList<>(Arrays.asList("echo","ls","cd","type","pwd","rm","mkdir","exit"));
        if(listOfCommands.contains(input)){
            System.out.println(input + " is a shell builtin");
        }
        else{
            System.out.println(input + ": not found");
        }
    }
    
	public static void main(String[] args) throws Exception {
		// Uncomment this block to pass the first stage
		while(true) {
			System.out.print("$ ");

			Scanner scanner = new Scanner(System.in);
			String input = scanner.nextLine();

			if(input.equals("exit 0")) {
				break;
			}
			if(input.startsWith("echo"))
			{
			    echoCommand(input.substring(5));
			}
			else if(input.startsWith("type")){
			    checkCommandType(input.substring(5));
			}
			else{
			    System.out.println(input + ": command not found");
			}
		}

	}
}