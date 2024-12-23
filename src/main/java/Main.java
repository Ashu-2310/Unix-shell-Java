import java.util.*;
import java.nio.file.*;
import java.nio.file.*;

public class Main {

	private static String cwd = Path.of("").toAbsolutePath().toString();

	private static void echoCommand(String input)
	{
		System.out.println(input);
	}

	private static void checkCommandType(String input) {
		ArrayList<String> listOfCommands = new ArrayList<>(Arrays.asList("echo","cd","type","exit","pwd"));
		if(listOfCommands.contains(input)) {
			System.out.println(input + " is a shell builtin");
		}
		else {
			String path = getPath(input);
			if(path != null) {
				System.out.println(input + " is " + path);
			}
			else {
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

	private static void cdImplement(String directory)
	{
		if(Files.isDirectory(Path.of(directory))) {
			cwd = directory;
		}
		else {
			System.out.printf("cd: %s: No such file or directory%n", directory);
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
			else if(input.startsWith("type")) {
				checkCommandType(input.substring(5));
			}
			else if(input.startsWith("pwd"))
			{
				System.out.println(cwd);
			}
			else if(input.startsWith("cd")) {
				cdImplement(input.substring(3));
			}
			else {
				String command = input.split(" ")[0];
				String path = getPath(command);
				if (path == null) {
					System.out.printf("%s: command not found%n", command);
				} else {
					String fullPath = path + input.substring(command.length());
					Process p = Runtime.getRuntime().exec(fullPath.split(" "));
					p.getInputStream().transferTo(System.out);
				}
			}

		}
	}
}