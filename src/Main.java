import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.Stack;

public class Main
{
	private static PlanetarySystem	pSystem = null;
	
	public static void	main(String[] args)
	{
		Scanner	scanner = new Scanner(System.in);
		while (true)
		{
			String	line = scanner.nextLine().trim();
			if (line.equals("exit"))
			{
				System.out.println("Exiting...");
				break;
			}
			String[]	parts = line.split(" ");
			String		command = parts[0];
			try
			{
				switch (command)
				{
					case "create":
						if (parts.length != 7)
						{
							System.out.println("Usage: create planetSystem \"name\" \"temperature\" \"pressure\" \"humidity\" \"radiation\"");
							break;
						}
						if (!parts[1].equals("planetSystem"))
						{
							System.out.println("Usage: create planetSystem \"name\" \"temperature\" \"pressure\" \"humidity\" \"radiation\"");
							break;
						}
						if (pSystem != null)
						{
							System.out.println("Error: System already created.");
							break;
						}
						pSystem = new PlanetarySystem(parts[2], Double.parseDouble(parts[3]),
							Double.parseDouble(parts[4]), Double.parseDouble(parts[5]), Double.parseDouble(parts[6]));
						System.out.println("System created.");
						break;

					case "addPlanet":
						if (pSystem == null)
						{
							System.out.println("Error: System not created.");
							break;
						}
						if (parts.length != 7)
						{
							System.out.println("Invalid command: addPlanet requires 6 arguments.");
							break;
						}
						pSystem.addPlanet(parts[1], parts[2], Double.parseDouble(parts[3]), 
							Double.parseDouble(parts[4]), Double.parseDouble(parts[5]), Double.parseDouble(parts[6]));
						System.out.println("Planet added.");
						break;

					case "addSatellite":
						if (pSystem == null)
						{
							System.out.println("Error: System not created.");
							break;
						}
						if (parts.length != 7)
						{
							System.out.println("Invalid command: addSatellite requires 6 arguments.");
							break;
						}
						pSystem.addSatellite(parts[1], parts[2], Double.parseDouble(parts[3]), 
							Double.parseDouble(parts[4]), Double.parseDouble(parts[5]), Double.parseDouble(parts[6]));
						System.out.println("Satellite added.");
						break;

					case "findRadiationAnomalies":
						if (pSystem == null)
						{
							System.out.println("Error: System not created.");
							break;
						}
						if (parts.length != 2)
						{
							System.out.println("Invalid command: findRadiationAnomalies requires 1 argument.");
							break;
						}
						double		threshold = Double.parseDouble(parts[1]);
						List<Node>	anomalies = pSystem.findRadiationAnomalies(threshold);
						System.out.println("Radiation anomalies:");
						for (Node node : anomalies)
							System.out.println(node.getName());
						break;

					case "getPathTo":
						if (pSystem == null)
						{
							System.out.println("Error: System not created.");
							break;
						}
						if (parts.length != 2)
						{
							System.out.println("Invalid command: getPathTo requires 1 argument.");
							break;
						}
						String			targetName = parts[1];
						Stack<String>	path = pSystem.getPathTo(targetName);
						if (path == null)
							System.out.println("Node not found.");
						else
						{
							System.out.println("Path to " + targetName + ":");
							List<String>	pathList = new ArrayList<>();
							while (!path.isEmpty())
								pathList.add(path.pop());
							for (int i = pathList.size() - 1; i >= 0; i--)
								System.out.print(pathList.get(i) + " ");
							System.out.println();
						}
						break;

					case "printMissionReport":
						if (pSystem == null)
						{
							System.out.println("Error: System not created.");
							break;
						}
						if (parts.length == 1)
						{
							System.out.println("Mission report:");
							pSystem.printMissionReport();
						}
						else if (parts.length == 2)
							pSystem.printMissionReport(parts[1]);
						else
							System.out.println("Invalid command: printMissionReport takes 0 or 1 argument.");
						break;

					default:
						System.out.println("Unknown command.");
				}
			}
			catch (NumberFormatException e)
			{
				System.out.println("Error: Invalid numeric input.");
			}
			catch (IllegalArgumentException e)
			{
				System.out.println("Error: " + e.getMessage());
			}
			catch (Exception e)
			{
				System.out.println("Error");
			}
		}
		scanner.close();
	}
}
