public class Salary implements IMessage<Integer>{
    private String from;
    private String to;
    private Integer content;

    public Salary(String from, String to, Integer content){
        this.from = from;
        this.to = to;
        this.content = content;
    }
    public final String getFrom() {
        return from;
    }
    public final String getTo() {
        return to;
    }
    public final Integer getContent(){
        return content;
    }
}