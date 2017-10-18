/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

/**
 *
 * @author grami
 */
public class Comment {
    private int id;
    private String comment;

    public Comment() {
    }

    public Comment(int id, String comment) {
        this.id = id;
        this.comment = comment;
        
    }

    public Comment(String comment) {
        this.comment = comment;
    }
    
    

    public int getId() {
        return id;
    }

    public String getComment() {
        return comment;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    @Override
    public String toString() {
        return "Comment{" + "id=" + id + ", comment=" + comment + ", user=" +  '}';
    }

    @Override
    public int hashCode() {
        int hash = 3;
       
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
        final Comment other = (Comment) obj;
        if (this.id != other.id) {
            return false;
        }
        return true;
    }

    
    
    
}
