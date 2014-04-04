/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GoogleAPI;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import java.util.ArrayList;
import java.util.Iterator;

/**
 *
 * @author lingjunqiu
 */
public class ParseGoogle {

    private String title;
    private String link;
    private String snippet;
    private String startDate;
    private String location;
    private String endDate;
    private boolean flag = true;
    private ArrayList summarys = new ArrayList();
    private ArrayList startDates = new ArrayList();

    public GsearchEntities parse(String jsonData, String query, String year) {
        GsearchEntities gsearch = null;
        String str = (query + " " + year).toLowerCase();
        JsonParser parser = new JsonParser();

        JsonObject jsonObject = (JsonObject) parser.parse(jsonData);
        JsonObject context = (JsonObject) jsonObject.get("searchInformation");
        int totalResults = context.get("totalResults").getAsInt();
        System.out.println("totalResutl" + totalResults);
        if (totalResults == 0) {
            throw new RuntimeException("no result!");
        } else {
            JsonArray items = (JsonArray) jsonObject.get("items");
            Iterator i = items.iterator();
            while (i.hasNext() && flag) {
                JsonObject item = (JsonObject) i.next();
                title = item.get("title").getAsString().toLowerCase();
                if (title.contains(str)) {
                    gsearch = new GsearchEntities();
                    gsearch.setTitle(title);
                    link = item.get("link").getAsString();
                    gsearch.setLink(link);
                    snippet = item.get("snippet").getAsString();
                    gsearch.setSnippet(snippet);
                    if (item.get("pagemap") != null) {
                        JsonObject pagemap = (JsonObject) item.get("pagemap");
                        JsonArray Event = (JsonArray) pagemap.get("Event");
                        Iterator j = Event.iterator();
                        while (j.hasNext()) {
                            JsonObject EventItem = (JsonObject) j.next();
                            String summary = EventItem.get("summary").getAsString();
                            summarys.add(summary);
                            if (EventItem.get("startDate") != null) {
                                startDate = EventItem.get("startDate").getAsString();
                                startDates.add(startDate);
                                System.out.println("startDate: " + startDate);
                            }
                            if (EventItem.get("endDate") != null) {
                                endDate = EventItem.get("endDate").getAsString();
                                gsearch.setEndDate(endDate);
                            }
                        }
                        gsearch.setStartDates(startDates);
                        gsearch.setSummarys(summarys);
                        if (pagemap.get("event") != null) {
                            JsonArray event = (JsonArray) pagemap.get("event");
                            JsonObject eventObject = (JsonObject) event.get(0);
                            location = eventObject.get("location").getAsString();
                            gsearch.setLocation(location);
                        } else {
                            location = "N/A";
                            gsearch.setLocation(location);
                        }
                    } else {

                    }
                    flag = false;
                } else {
                    throw new RuntimeException("sorry,there is no result match this year!");
                }
            }
        }
        return gsearch;
    }

    public String parseCityImg(String jsonData) {

        JsonParser parser = new JsonParser();
        JsonObject jsonObject = (JsonObject) parser.parse(jsonData);
        JsonArray items = (JsonArray) jsonObject.get("items");
        JsonObject item = (JsonObject) items.get(0);
        String cityImg = item.get("link").getAsString();
        return cityImg;
    }

    public String[] parseCityDes(String jsonData) {
        JsonParser parser = new JsonParser();
        JsonObject jsonObject = (JsonObject) parser.parse(jsonData);
        JsonArray items = (JsonArray) jsonObject.get("items");
        JsonObject item = (JsonObject) items.get(0);
        String citySnippet = item.get("snippet").getAsString();
        String cityDesLink = item.get("link").getAsString();
        String[] cityDes = {citySnippet,cityDesLink};
        return cityDes;          
    }
}
