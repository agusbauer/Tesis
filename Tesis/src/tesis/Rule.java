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
    private String begin;
    private String end;
    private String toBegin;
    private String toEnd;
    private Integer id;
    
    public Rule(String name, String from, String to, Integer id) {
        this.name = name;
        this.begin = from;
        this.end = to;
        this.id = id;
    }

    public Rule(String name, String begin, String end, String toBegin, String toEnd, Integer id) {
        this.name = name;
        this.begin = begin;
        this.end = end;
        this.toBegin = toBegin;
        this.toEnd = toEnd;
        this.id = id;
    }

    public Rule() {
        this.name = "";
        this.begin = "";
        this.end = "";
        this.id = -1;
    }
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getBegin() {
        return begin;
    }

    public void setBegin(String begin) {
        this.begin = begin;
    }

    public String getToBegin() {
        return toBegin;
    }

    public void setToBegin(String toBegin) {
        this.toBegin = toBegin;
    }

    public String getToEnd() {
        return toEnd;
    }

    public void setToEnd(String toEnd) {
        this.toEnd = toEnd;
    }

    public String getEnd() {
        return end;
    }

    public void setEnd(String end) {
        this.end = end;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "Rule{" + "name=" + name + ", begin=" + begin + ", end=" + end + ", toBegin=" + toBegin + ", toEnd=" + toEnd + ", id=" + id + '}';
    }
    
    
}
