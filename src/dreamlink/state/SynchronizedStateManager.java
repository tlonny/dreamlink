package dreamlink.state;

import java.util.ArrayList;
import java.util.List;

public class SynchronizedStateManager {

    public static final SynchronizedStateManager instance = new SynchronizedStateManager();

    private List<ISynchronizedState> state = new ArrayList<ISynchronizedState>();

    public void addState(ISynchronizedState state) {
        this.state.add(state);
    }

    public void advanceState() {
        for (ISynchronizedState state : this.state) {
            state.advanceState();
        }
    }
    
}
