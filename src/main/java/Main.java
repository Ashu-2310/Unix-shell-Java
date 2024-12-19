import java.util.Scanner;

public class Main {
	public static void main(String[] args) throws Exception {
		// Uncomment this block to pass the first stage
		while(true) {
			System.out.print("$ ");

			Scanner scanner = new Scanner(System.in);
			String input = scanner.nextLine();

			if(input.equals("exit 0")) {
				break;
			}
			String comm = "";
			for(int i=0; i<input.length(); i++) {
				if(input.charAt(i) != ' ') {
					comm += input.charAt(i);
				}
				else {
					if(comm.equals("echo")) {
						System.out.println(input.substring(i+1));
						break;
					}
				}
			}

			//System.out.println(input + ": command not found");
		}
	}
}