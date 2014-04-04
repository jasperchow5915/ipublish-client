/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package GoogleAPI;

import org.netbeans.saas.RestConnection;

/**
 *
 * @author lingjunqiu
 */
public class GoogleSearch {
    private String yquery;
    private  String city;   
    public  GsearchEntities gsearch(String query, String year) throws Exception{
        String API_key = "AIzaSyBlOfsOez6wZMujDLY9Jm5t2vSwy0fpfmM";
        String SEARCH_ID_cx = "000075800476485618465:ufr_xsf4pne";
        String[][] pathParams = new String[][]{};
        String[][] queryParams = new String[][]{};
        GsearchEntities gsearch= new GsearchEntities();
        yquery = query.replaceAll("[ /&]", "%20")+year;
        
        RestConnection conn = new RestConnection("https://www.googleapis.com/customsearch/v1?key=" + API_key + "&cx=" + SEARCH_ID_cx + "&q=" + yquery + "&num=5", pathParams, queryParams);
        String response = conn.get().getDataAsString();
        ParseGoogle parseGoogle = new ParseGoogle();
        gsearch = parseGoogle.parse(response,query,year);
        
        return gsearch;
    }
    
     public String gsearchByImg(String location) throws Exception{
        String API_key = "AIzaSyBlOfsOez6wZMujDLY9Jm5t2vSwy0fpfmM";
        String SEARCH_ID_cx = "000075800476485618465:wage8ktzjto";
        String[][] pathParams = new String[][]{};
        String[][] queryParams = new String[][]{};
        city = location.split(" ")[0];     
        RestConnection conn = new RestConnection("https://www.googleapis.com/customsearch/v1?key=" + API_key + "&cx=" + SEARCH_ID_cx + "&q=" + city +  "&searchType=image&fileType=png&imgSize=medium&imgType=photo", pathParams, queryParams);
        String response = conn.get().getDataAsString();    
        ParseGoogle parseGoogle = new ParseGoogle();
        String cityImg=parseGoogle.parseCityImg(response);  
        
        return cityImg;
    }
     
      public String[] gsearchByCity(String location) throws Exception{
        String API_key = "AIzaSyBlOfsOez6wZMujDLY9Jm5t2vSwy0fpfmM";
        String SEARCH_ID_cx = "000075800476485618465:wage8ktzjto";
        String[][] pathParams = new String[][]{};
        String[][] queryParams = new String[][]{};
        city = location.split(" ")[0];     
        RestConnection conn = new RestConnection("https://www.googleapis.com/customsearch/v1?key=" + API_key + "&cx=" + SEARCH_ID_cx + "&q=" + city + "&num=1", pathParams, queryParams);
        String response = conn.get().getDataAsString();    
        ParseGoogle parseGoogle = new ParseGoogle();
        String[] cityDes=parseGoogle.parseCityDes(response);  
        
        return cityDes;
    }
}
