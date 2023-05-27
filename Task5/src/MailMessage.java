public class MailMessage implements IMessage<String>{
    private String from;
    private String to;
    private String content;

    public MailMessage(String from, String to, String content){
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
    public final String getContent(){
        return content;
    }
}