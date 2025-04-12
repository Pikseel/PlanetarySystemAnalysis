import java.util.ArrayList;
import java.util.List;

// Represents an object in the planetary system.
public class Node
{
	private String		name;
	private String		type;
	private SensorData	sensorData;
	private List<Node>	children;

	/**
	 * Constructs a Node.
	 * @param name of the celestial body
	 * @param type "Star", "Planet", or "Moon"
	 * @param sensorData sensor data for the body
	 */
	public Node(String name, String type, SensorData sensorData)
	{
		this.name = name;
		this.type = type;
		this.sensorData = sensorData;
		this.children = new ArrayList<>();
	}

	/**
	 * Gets name.
	 * @return name of the node
	 */
	public String	getName()
	{
		return name;
	}

	/**
	 * Gets type.
	 * @return type of the node
	 */
	public String	getType()
	{
		return type;
	}

	/**
	 * Gets sensor data.
	 * @return sensor data
	 */
	public SensorData	getSensorData()
	{
		return sensorData;
	}

	/**
	 * Gets children node.
	 * @return list of children
	 */
	public List<Node>	getChildren()
	{
		return children;
	}

	/**
	 * Adds children node.
	 * @param child node to add
	 */
	public void	addChild(Node child)
	{
		children.add(child);
	}
}
