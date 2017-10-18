
package entities;

import java.sql.Date;

public class Claim {
    private int id;
    private Date date;
    private String text;
    private String status ;
    private boolean isTreated;

    public Claim(int id, Date date, String text, String status, boolean isTreated) {
        this.id = id;
        this.date = date;
        this.text = text;
        this.status = status;
        this.isTreated = isTreated;
    }
    
    public Claim() {
        
    }

    public Claim(int id, Date date, String text, String status) {
        this.id = id;
        this.date = date;
        this.text = text;
        this.status = status;
    }

    public Claim(Date date, String text) {
        this.date = date;
        this.text = text;
    }

    public Claim(int id, Date date, String text) {
        this.id = id;
        this.date = date;
        this.text = text;
    }
    
    public int getId() {
        return id;
    }

    public Date getDate() {
        return date;
    }

    public String getText() {
        return text;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public void setText(String text) {
        this.text = text;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 41 * hash + this.id;
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
        final Claim other = (Claim) obj;
        if (this.id != other.id) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Claim{" + "id=" + id + ", date=" + date + ", text=" + text + ", status=" + status + '}';
    }

    public boolean isIsTreated() {
        return isTreated;
    }

    public void setIsTreated(boolean isTreated) {
        this.isTreated = isTreated;
    }

    
    
}
