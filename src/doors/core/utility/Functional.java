package doors.core.utility;

public class Functional {

    public interface IAction0 {
        public void invoke();
    }

    public interface IAction1<T> {
        public void invoke(T arg);
    }

}
