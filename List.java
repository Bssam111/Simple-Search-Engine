

public interface List<T> {
boolean empty();
boolean full();
boolean last();
T retrieve();
void remove();
void findFirst();
void findNext();
void insert(T data);
void update(T data);
}
