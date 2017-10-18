package entities;

/**
 *
 * @author grami
 */
public class Message implements Comparable<Message> {

    private int id;
    private String text;

    public Message(String text) {
        this.text = text;
    }

    public Message(int id, String text) {
        this.id = id;
        this.text = text;
    }

    public Message() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    @Override
    public int hashCode() {
        int hash = 57;

        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Message other = (Message) obj;
        if (this.id != other.id) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Message{" + "id=" + id + ", text=" + text + '}';
    }

    @Override
    public int compareTo(Message o) {
        return this.id - o.id;
    }

}
