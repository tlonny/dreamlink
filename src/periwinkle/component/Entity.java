package periwinkle.component;

import java.util.HashSet;
import java.util.Set;

public class Entity {

    private static Set<Entity> TO_SETUP_SET = new HashSet<>();
    private static Set<Entity> TO_TEARDOWN_SET = new HashSet<>();

    public static void transition() {
        for(var entity : TO_SETUP_SET) {
            entity.setup();
        }
        for(var entity : TO_TEARDOWN_SET) {
            entity.tearDown();
        }
        TO_SETUP_SET.clear();
        TO_TEARDOWN_SET.clear();
    }

    public Entity() {
       TO_SETUP_SET.add(this);
    }

    public void destroy() {
       TO_TEARDOWN_SET.add(this);
    }

    public void setup() {
    }

    public void tearDown() {
    }
}
