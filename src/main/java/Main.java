import java.util.*;
import java.nio.file.Files;
import java.nio.file.Path;

public class Main {
    private static void echoCommand(String input)
    {
        System.out.println(input);
    }
    
    private static void checkCommandType(String input){
        ArrayList<String> listOfCommands = new ArrayList<>(Arrays.asList("echo","cd","type","exit"));
        if(listOfCommands.contains(input)){
            System.out.println(input + " is a shell builtin");
        }
        else{
            String path = getPath(input);
            if(path != null){
                System.out.println(input + " is " + path);
            }
            else{
                System.out.println(input + ": not found");
            }
        }
    }
    
    private static String getPath(String parameter) {
    for (String path : System.getenv("PATH").split(":")) {
      Path fullPath = Path.of(path, parameter);
      if (Files.isRegularFile(fullPath)) {
        return fullPath.toString();
      }
    }
    return null;
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