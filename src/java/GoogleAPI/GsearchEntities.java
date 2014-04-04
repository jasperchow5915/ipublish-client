/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package GoogleAPI;

import java.util.ArrayList;

/**
 *
 * @author lingjunqiu
 */
public class GsearchEntities {
    private String title;
    private String link;
    private String snippet;
    private String eventType;
    private String location;
    private String endDate;
    private ArrayList summarys= null;
    private ArrayList startDates= null;

    public String getEndDate() {
        return endDate;
    }

    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }

    public ArrayList getSummarys() {
        return summarys;
    }

    public void setSummarys(ArrayList summarys) {
        this.summarys = summarys;
    }

    public ArrayList getStartDates() {
        return startDates;
    }

    public void setStartDates(ArrayList startDates) {
        this.startDates = startDates;
    }


  

    public String getEventType() {
        return eventType;
    }

    public void setEventType(String eventType) {
        this.eventType = eventType;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public String getSnippet() {
        return snippet;
    }

    public void setSnippet(String snippet) {
        this.snippet = snippet;
    }
    
}
