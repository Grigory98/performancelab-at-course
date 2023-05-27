import java.util.*;
import java.util.function.Consumer;

public class MailService<T> implements Consumer<IMessage<T>>
{
    private static class MyHashMap<K,V> extends HashMap<K,V> {
        @Override
        public V get(Object key){
            V tmp = super.get(key);
            try {
                if (tmp == null) tmp = (V) Collections.emptyList();
            }
            catch (ClassCastException e) {}
            return tmp;
        }
    }
    private Map<String, List<T>> mailBox;
    public MailService(){
        mailBox = new MyHashMap<>();
    }
    @Override
    public void accept(IMessage<T> t){
        List<T> val;
        if(mailBox.containsKey(t.getTo())) {
            val = mailBox.get(t.getTo());
            val.add(t.getContent());
            mailBox.put(t.getTo(), val);
        } else {
            val = new LinkedList<>();
            val.add(t.getContent());
            mailBox.put(t.getTo(), val);
        }
    }
    public Map<String, List<T>> getMailBox() {
        return mailBox;
    }
}