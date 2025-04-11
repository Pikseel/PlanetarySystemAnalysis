import java.util.List;
import java.util.Scanner;
import java.util.Stack;

/**
 * Provides a terminal UI for interacting with the planetary system.
 */
public class Main {
    private static PlanetarySystem system = null;

    /**
     * Runs the terminal interface.
     * @param args command-line arguments (unused)
     */
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.print("> ");
            String line = scanner.nextLine().trim();
            if (line.equals("exit")) {
                break;
            }
            if (line.isEmpty()) {
                continue;
            }
            String[] parts = line.split(" ");
            String command = parts[0];
            try {
                switch (command) {
                    case "create":
                        if (parts.length != 6) {
                            System.out.println("Invalid command: create requires 5 arguments.");
                            break;
                        }
                        if (system != null) {
                            System.out.println("Error: System already created.");
                            break;
                        }
                        String starName = parts[1];
                        double temp = Double.parseDouble(parts[2]);
                        double press = Double.parseDouble(parts[3]);
                        double hum = Double.parseDouble(parts[4]);
                        double rad = Double.parseDouble(parts[5]);
                        system = new PlanetarySystem(starName, temp, press, hum, rad);
                        System.out.println("System created.");
                        break;

                    case "addPlanet":
                        if (system == null) {
                            System.out.println("Error: System not created.");
                            break;
                        }
                        if (parts.length != 7) {
                            System.out.println("Invalid command: addPlanet requires 6 arguments.");
                            break;
                        }
                        String planetName = parts[1];
                        String parentName = parts[2];
                        double pTemp = Double.parseDouble(parts[3]);
                        double pPress = Double.parseDouble(parts[4]);
                        double pHum = Double.parseDouble(parts[5]);
                        double pRad = Double.parseDouble(parts[6]);
                        system.addPlanet(planetName, parentName, pTemp, pPress, pHum, pRad);
                        System.out.println("Planet added.");
                        break;

                    case "addSatellite":
                        if (system == null) {
                            System.out.println("Error: System not created.");
                            break;
                        }
                        if (parts.length != 7) {
                            System.out.println("Invalid command: addSatellite requires 6 arguments.");
                            break;
                        }
                        String satName = parts[1];
                        String satParent = parts[2];
                        double sTemp = Double.parseDouble(parts[3]);
                        double sPress = Double.parseDouble(parts[4]);
                        double sHum = Double.parseDouble(parts[5]);
                        double sRad = Double.parseDouble(parts[6]);
                        system.addSatellite(satName, satParent, sTemp, sPress, sHum, sRad);
                        System.out.println("Satellite added.");
                        break;

                    case "findRadiationAnomalies":
                        if (system == null) {
                            System.out.println("Error: System not created.");
                            break;
                        }
                        if (parts.length != 2) {
                            System.out.println("Invalid command: findRadiationAnomalies requires 1 argument.");
                            break;
                        }
                        double threshold = Double.parseDouble(parts[1]);
                        List<Node> anomalies = system.findRadiationAnomalies(threshold);
                        System.out.println("Radiation anomalies:");
                        for (Node node : anomalies) {
                            System.out.println(node.getName());
                        }
                        break;

                    case "getPathTo":
                        if (system == null) {
                            System.out.println("Error: System not created.");
                            break;
                        }
                        if (parts.length != 2) {
                            System.out.println("Invalid command: getPathTo requires 1 argument.");
                            break;
                        }
                        String targetName = parts[1];
                        Stack<String> path = system.getPathTo(targetName);
                        if (path == null) {
                            System.out.println("Node not found.");
                        } else {
                            System.out.println("Path to " + targetName + ":");
                            while (!path.isEmpty()) {
                                System.out.print(path.pop() + " ");
                            }
                            System.out.println();
                        }
                        break;

                    case "printMissionReport":
                        if (system == null) {
                            System.out.println("Error: System not created.");
                            break;
                        }
                        if (parts.length == 1) {
                            system.printMissionReport();
                        } else if (parts.length == 2) {
                            system.printMissionReport(parts[1]);
                        } else {
                            System.out.println("Invalid command: printMissionReport takes 0 or 1 argument.");
                        }
                        break;

                    default:
                        System.out.println("Unknown command.");
                }
            } catch (NumberFormatException e) {
                System.out.println("Error: Invalid numeric input.");
            } catch (IllegalArgumentException e) {
                System.out.println("Error: " + e.getMessage());
            } catch (Exception e) {
                System.out.println("Error: An unexpected error occurred.");
            }
        }
        scanner.close();
    }
}