package rx.internal.util.unsafe;


public interface MessagePassingQueue<M> {
    M poll();
}
