
public class Station {
    private String name;
    private Station connection;

    public Station(String name) {
        this.name = name;
        this.connection = null;
    }

    public Station(String name, Station connection) {
        this.name = name;
        this.connection = connection;
    }
    
    public String getName() {
        return name;
    }

    public Station getConnection() {
        return connection;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setConnection(Station connection) {
        this.connection = connection;
    }
    
}
