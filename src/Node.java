import java.util.ArrayList;
import java.util.List;

/**
 * Represents a celestial body in the planetary system.
 */
public class Node {
    private String name;
    private String type; // "Star", "Planet", or "Moon"
    private SensorData sensorData;
    private List<Node> children;

    /**
     * Constructs a Node with given properties.
     * @param name of the celestial body
     * @param type "Star", "Planet", or "Moon"
     * @param sensorData sensor data for the body
     */
    public Node(String name, String type, SensorData sensorData) {
        this.name = name;
        this.type = type;
        this.sensorData = sensorData;
        this.children = new ArrayList<>();
    }

    /**
     * Gets the name.
     * @return name of the node
     */
    public String getName() { return name; }

    /**
     * Gets the type.
     * @return type of the node
     */
    public String getType() { return type; }

    /**
     * Gets sensor data.
     * @return sensor data
     */
    public SensorData getSensorData() { return sensorData; }

    /**
     * Gets children nodes.
     * @return list of children
     */
    public List<Node> getChildren() { return children; }

    /**
     * Adds a child node.
     * @param child node to add
     */
    public void addChild(Node child) { children.add(child); }
}