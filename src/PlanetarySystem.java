import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

// Manages the planetary system as a data tree.
public class PlanetarySystem
{
	private Node	root;

	/**
	 * Creates the system with the star at it's root.
	 * @param starName name of the star
	 * @param temperature in Kelvin
	 * @param pressure in Pascals
	 * @param humidity percentage (0 for star)
	 * @param radiation in Sieverts
	 * @throws IllegalArgumentException if humidity is not 0 for the star
	 */
	public PlanetarySystem(String starName, double temperature, double pressure, double humidity, double radiation)
	{
		if (humidity != 0)
			throw new IllegalArgumentException("Star cannot have humidity.");
		SensorData	sensorData = new SensorData(temperature, pressure, humidity, radiation);
		root = new Node(starName, "Star", sensorData);
	}

	/**
	 * Finds a node by its name.
	 * @param name name of the node to find
	 * @return the node or null
	 */
	public Node	findNode(String name)
	{
		return findNodeRecursive(root, name);
	}

	/**
	 * Recursively finds a node by name.
	 * @param current node to check
	 * @param name to find
	 * @return the node or null
	 */
	private Node	findNodeRecursive(Node current, String name)
	{
		if (current.getName().equals(name))
			return current;
		for (Node child : current.getChildren())
		{
			Node	found = findNodeRecursive(child, name);
			if (found != null)
				return found;
		}
		return null;
	}

	/**
	 * Adds a planet as a child to the star or another planet.
	 * @param planetName name of the planet
	 * @param parentName name of the parent (star or planet)
	 * @param temperature in Kelvin
	 * @param pressure in Pascals
	 * @param humidity percentage
	 * @param radiation in Sieverts
	 * @throws IllegalArgumentException for invalid parent or duplicate name
	 */
	public void	addPlanet(String planetName, String parentName, double temperature, double pressure, double humidity, double radiation)
	{
		Node	parent = findNode(parentName);
		if (parent == null)
			throw new IllegalArgumentException("Parent not found.");
		if (parent.getType().equals("Moon"))
			throw new IllegalArgumentException("Moons cannot be parents of planets.");
		if (findNode(planetName) != null)
			throw new IllegalArgumentException("Name already exists.");
		SensorData	sensorData = new SensorData(temperature, pressure, humidity, radiation);
		Node		planet = new Node(planetName, "Planet", sensorData);
		parent.addChild(planet);
	}

	/**
	 * Adds a moon to a planet.
	 * @param satelliteName name of the satellite
	 * @param parentName name of the planet
	 * @param temperature in Kelvin
	 * @param pressure in Pascals
	 * @param humidity percentage
	 * @param radiation in Sieverts
	 * @throws IllegalArgumentException for invalid planet, moon as parent, or duplicate name
	 */
	public void	addSatellite(String satelliteName, String parentName, double temperature, double pressure, double humidity, double radiation)
	{
		Node	parent = findNode(parentName);
		if (parent == null)
			throw new IllegalArgumentException("Parent not found.");
		if (!parent.getType().equals("Planet"))
			throw new IllegalArgumentException("Satellites can only be added to planets.");
		if (findNode(satelliteName) != null)
			throw new IllegalArgumentException("Name already exists.");
		SensorData	sensorData = new SensorData(temperature, pressure, humidity, radiation);
		Node		satellite = new Node(satelliteName, "Moon", sensorData);
		parent.addChild(satellite);
	}

	/**
	 * Finds all nodes with radiation above threshold.
	 * @param threshold radiation level to check
	 * @return list of nodes with high radiation
	 */
	public List<Node>	findRadiationAnomalies(double threshold)
	{
		List<Node>	result = new ArrayList<>();
		findRadiationAnomaliesRecursive(root, threshold, result);
		return result;
	}

	/**
	 * Recursively finds nodes with radiation above threshold.
	 * @param current node to check
	 * @param threshold radiation level
	 * @param result list to store results
	 */
	private void	findRadiationAnomaliesRecursive(Node current, double threshold, List<Node> result)
	{
		if (current.getSensorData().getRadiation() > threshold)
			result.add(current);
		for (Node child : current.getChildren())
			findRadiationAnomaliesRecursive(child, threshold, result);
	}

	/**
	 * Returns the path from root to the node.
	 * @param nodeName name of the target
	 * @return stack representing the path or null
	 */
	public Stack<String>	getPathTo(String nodeName)
	{
		Stack<String>	path = new Stack<>();
		if (findPathRecursive(root, nodeName, path))
			return path;
		return null;
	}

	/**
	 * Recursively finds the path to a node.
	 * @param current node to check
	 * @param targetName name to find
	 * @param path stack to store the path
	 * @return true if found or false
	 */
	private boolean	findPathRecursive(Node current, String targetName, Stack<String> path)
	{
		path.push(current.getName());
		if (current.getName().equals(targetName))
			return true;
		for (Node child : current.getChildren())
			if (findPathRecursive(child, targetName, path))
				return true;
		path.pop();
		return false;
	}

	/**
	 * Prints the entire tree.
	 */
	public void	printMissionReport()
	{
		printNode(root, 0);
	}

	/**
	 * Recursively prints a node and its children.
	 * @param node node to print
	 * @param level indent level
	 */
	private void	printNode(Node node, int level)
	{
		String	indent = "";
		for (int i = 1; i < level; i++)
			indent += "    ";
		if (level > 0)
			indent += "└──";
		SensorData	data = node.getSensorData();
		System.out.printf("%s%s (%s): %.2f Kelvin, %.2f Pascals, %.2f%% humidity, %.2f Sieverts%n",
				indent, node.getName(), node.getType(), data.getTemperature(), data.getPressure(), data.getHumidity(), data.getRadiation());
		for (Node child : node.getChildren())
			printNode(child, level + 1);
	}

	/**
	 * Prints data for a specific node.
	 * @param nodeName name of the node
	 */
	public void	printMissionReport(String nodeName)
	{
		Node	node = findNode(nodeName);
		if (node == null)
			System.out.println("Node not found.");
		else
		{
			SensorData	data = node.getSensorData();
			System.out.printf("%s (%s): %.2f Kelvin, %.2f Pascals, %.2f%% humidity, %.2f Sieverts%n",
					node.getName(), node.getType(), data.getTemperature(), data.getPressure(), data.getHumidity(), data.getRadiation());
		}
	}
}
