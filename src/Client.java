import java.util.List;

public interface Client {
    GimulatorObject get(Key key) throws GimulatorException;

    List<GimulatorObject> find(Key key) throws GimulatorException;

    void set(Key key) throws GimulatorException;

    void delete(Key key) throws GimulatorException;

    void watch(Key key) throws GimulatorException;

}
