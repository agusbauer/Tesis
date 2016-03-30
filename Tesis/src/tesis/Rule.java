/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tesis;

/**
 *
 * @author agustin
 */
public class Rule {
    
    private String name;
    private String from;
    private String to;
    private Integer id;

    public Rule(String name, String from, String to, Integer id) {
        this.name = name;
        this.from = from;
        this.to = to;
        this.id = id;
    }

    public Rule() {
        this.name = "";
        this.from = "";
        this.to = "";
        this.id = -1;
    }
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getFrom() {
        return from;
    }

    public void setFrom(String from) {
        this.from = from;
    }

    public String getTo() {
        return to;
    }

    public void setTo(String to) {
        this.to = to;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "Rule{" + "name=" + name + ", from=" + from + ", to=" + to + ", id=" + id + '}';
    }

    
    
    
}
