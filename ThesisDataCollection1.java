/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package thesisdatacollection;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
//import java.io.Writer;
//import java.io.OutputStreamWriter;
//import java.io.FileOutputStream;
import java.io.IOException;

import java.text.ParseException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;

import java.util.Date;
import java.util.List;

//twitter4j - unofficial library for Java API 
import twitter4j.Paging;               //Controls Pagination - Instantiate Pagination class per thread
import twitter4j.Status;               //Data interface representing one single status(tweet) of a user
import twitter4j.Twitter;              //Interface inheriting multiple interfaces from twitter4j.api
import twitter4j.TwitterException;     //Exception class thrown when calls to Twitter API fails(ex: Twitter server error code)
import twitter4j.TwitterFactory;       //Factory class for twitter with root configuration
import twitter4j.auth.AccessToken;     //Represents authorized tokens to access protected resources


/**
 *
 * @author kumud
 */

public class ThesisDataCollection1 {

    /**
     * @param args the command line arguments
     */
    
    static int c; // # of request for the current key/per user token/per session (=3200)
    static long userID = 1; // the Twitter ID/user id read from the file obtained after calling changeSession() method. 
    //However that part of code is taken care by Lina. So just read userIds from file she gives to you.
    static int s; // current session number(starts from 0)
    static int maxSession = 14;  // the maximum number of sessions 
    
    static Twitter twitter = new TwitterFactory().getInstance(); //Returns a instance associated with the root configuration
    static AccessToken oauthToken;
    
    static BufferedWriter out;
    static BufferedWriter uIderror; //user ids with error description
    static BufferedWriter uIds; // user ids only
    static int fileNo;
    
    public static void main(String[] args) throws TwitterException, IOException, ParseException {
        // TODO code application logic here
        
        //Initializing variables
        c = 0;
        s = 0;
        fileNo = 0;
        
        changeSession(); //Here you're getting data (userIds) from Twitter groups by changing sessions(access/consumer tokens)
        
       // Reading user IDs from a user file
       // BufferedReader br = new BufferedReader(new FileReader(new File("MarketWatchMergefile0.txt")));
        BufferedReader br = new BufferedReader(new FileReader(new File("users2.txt"))); //Initially this file contains only userIds
      //BufferedReader br = new BufferedReader(new FileReader(new File("StockTwitsfile0.txt")));
        String line;
        
        // Creating files with userIds and userIds with error description
        //uIderror = new BufferedWriter(new FileWriter("MarketWatchMerge_errorLog2.txt", true));
          uIderror = new BufferedWriter(new FileWriter("users2_errorLog2.txt", true)); //file contains userIds with error description
      //uIderror = new BufferedWriter(new FileWriter("StockTwits_errorLog2.txt", true));
      
      //uIds = new BufferedWriter(new FileWriter("MarketWatchMerge_eLog2.txt", true));
        uIds = new BufferedWriter(new FileWriter("users2_eLog2.txt", true));
      //uIds = new BufferedWriter(new FileWriter("StockTwits_eLog2.txt", true));
        
        line=br.readLine();
        
        while ((line != null)) {
            userID = Long.parseLong(line.trim());
            writeData(); //getting specific user timeline tweets and writing to a file
            //Again per token 3200 requests. Then make use of other session/token
            line = br.readLine();
        } //while
        
        out.close();
        uIderror.close();
        uIds.close();
     
    } //main()

    public static void changeSession() throws TwitterException, IOException {
        c = 0;
        //int p = 1;
       // out = new BufferedWriter(new FileWriter("MarketWatchMergefile"+fileNo+".txt"));
        out = new BufferedWriter(new FileWriter("Data2_file" + fileNo++ + ".txt")); //opening existing file in write mode
      //out = new BufferedWriter(new FileWriter("StockTwitsfile"+fileNo+".txt"));
        
        switch(s){
            case 0:
                twitter = new TwitterFactory().getInstance();
                
                //Authorizing an application to access Twitter data
                twitter.setOAuthConsumer("fjXnBSHLnqrCs2HaosvUCL9nA", "Qg89IwFL8xV2sj45SGM2CUIndA2bHR5sAzxPqRyH6Av9nL0gRw");
                oauthToken = new AccessToken("818520444617768971-rluScyHtKUgtMrRywT7PMUTMFN2zWPU", "nTDcHbyKEE0BxhFvt5dwJg9Cw8YYYAhbA9K0t2N7nDJsE");
                twitter.setOAuthAccessToken(oauthToken);
                
            
                        
               break;
                
            case 1:
                twitter = new TwitterFactory().getInstance();
                twitter.setOAuthConsumer("JZzmBe65KnwRR9EYPAbxe1AEj", "6IMvUy3aMp6rEVNfyNcO8dnhj7s2rUQPKXc6PkNriwfhg1srSL");
                oauthToken = new AccessToken("818520444617768971-z5LrYv94eQppUiv3g4SgJEpniUHuZym", "rXNFl5f5JXaL5MEzERHzg7j55sVtwA4FNVYkWqdrpF3B3");
                twitter.setOAuthAccessToken(oauthToken);
                
             
                break;
                
            case 2:
                twitter = new TwitterFactory().getInstance();
                twitter.setOAuthConsumer("kH7v5MqWYwwxApJ6bxNSa2Mog", "T2M1PoEmz0A6jcQXolpzKR9Or99ZF0DAs2s8ShWww1ksIWU4hb");
                oauthToken = new AccessToken("818520444617768971-ytDamLBqCs1GMNvMARIsKbzhATNRLVJ", "g8Ej5DIzTQVQNNV64QXrsrtyyjfidKxlzKgxiWbxOnIfy");
                twitter.setOAuthAccessToken(oauthToken);
                
                
                break;
                
            case 3:
                twitter = new TwitterFactory().getInstance();
                twitter.setOAuthConsumer("kQkegphBVHVslHObJrRTqrIpy", "2BArWm68SeBPUfhAo49O9Q0AzvfyVgACtZ2woweEkwD4vRXp4t");
                oauthToken = new AccessToken("1632991700-mYDErYb3gqxRersxmTro2uvvhKRql6OMVrFf0OL", "yZg0CEPUpJyDS75kMUOdkHEORTA5RRGKfYtyK5EsXU7YL");
                twitter.setOAuthAccessToken(oauthToken);
                
             
                break;
                
            case 4:
                twitter = new TwitterFactory().getInstance();
                twitter.setOAuthConsumer("B4TBtjVgJD6CnVwKWO5U4WdGo", "3MekrYhcHh7vfTPmnginwCo9NdzCFp8kGDa9n6I4uVwLL9Dtbp");
                oauthToken = new AccessToken("1632991700-9p2Z2NBli4bw3Joyw3EzFBgkS10qjb8AWUgo8oI", "aDs4bQ4BvVMlLt6ML67SAYWX2nx2hafktrjHLD1GiDuwe");
                twitter.setOAuthAccessToken(oauthToken);
                
               
                break;
                
            case 5:
                twitter = new TwitterFactory().getInstance();
                twitter.setOAuthConsumer("2v7KwsFkErO5FNKhrgdT9Yj7n", "pke1NE7G6o0Ee3XqUj64ZHxop5bMd8LpD4U60qLzX0hK9JjQdA");
                oauthToken = new AccessToken("1632991700-wq4kRc3j8BJTaUcd728OQaqeG6Do07rKPsExSUs", "g9zAB7bHUDk6KnFI4MX89ZvWmEOQdiWAzjV4CqLQN3nKr");
                twitter.setOAuthAccessToken(oauthToken);
                
             
                       
                break;
                
            case 6:
                twitter = new TwitterFactory().getInstance();
                twitter.setOAuthConsumer("SzSPXCOaJs7kON3DnBWlMnjDF", "djocBH9qzTrUjpA16DVRWFid0Vw6aY2GdQZLUmgvYMEqj9WLLY");
                oauthToken = new AccessToken("847189723747639297-jcX89sJvxvHJppdBrvcZRjuKfTBHaKx", "BsYd7qi7fTuDWtjdTvy7PVmCPss9FMnqCCSFeF164DhPZ");
                twitter.setOAuthAccessToken(oauthToken);
                
             
                       
                break;
                
                
            case 7:
                twitter = new TwitterFactory().getInstance();
                twitter.setOAuthConsumer("uhRVONKj18MUKRbdiZJ6bH5wI", "JMstVHzUs9iRIu787zEpevB5Gkaupv5svvWd2vucngXcYOKITd");
                oauthToken = new AccessToken("847189723747639297-Ac6pGIeIKmpFAtmdM6V2Mii8kzeKt5u", "FCjjgmkZiHUBLFU3Eq21FJL4yFLvy1YHtZLSOgdmG95eI");
                twitter.setOAuthAccessToken(oauthToken);
                
             
                       
                break;
                
            case 8:
                twitter = new TwitterFactory().getInstance();
                twitter.setOAuthConsumer("bsmhyT1NouVTAljgN4KxHsZYD", "G59dsl9fZIMZD09ulJmRRaBjd5Opqtcp64vWKJRcJUsrkBQ2vQ");
                oauthToken = new AccessToken("847189723747639297-S5029gOZ1s79uBcH4WG4IM2XKb2FyRm", "xsmz5yKZq7nwSr62SDKgYu9TDLCnVviph0GqMvivGHHdB");
                twitter.setOAuthAccessToken(oauthToken);
                
             
                       
                break;
                
            case 9:
                twitter = new TwitterFactory().getInstance();
                twitter.setOAuthConsumer("MBuA6KPC6sSGWctOUwOUyNnSR", "POcFMG3QwzwlPmn6zisBGSGH0c8I6YcveAudVAmEBYCHTiQOtS");
                oauthToken = new AccessToken("847274528229081088-U1oTbZrPgy8DPko7d1sIyYHMPx4LzmJ", "94Ty8Kt1GT2adjiLf5I25MCGkeB38TZL9sxmd6FX33B6Z");
                twitter.setOAuthAccessToken(oauthToken);
                
            
                       
                break;
                
            case 10:
                twitter = new TwitterFactory().getInstance();
                twitter.setOAuthConsumer("xqu6bleBykTke3Ou2GNkfSExC", "eWIIo9w5MWjTyoUBTTOm5dvtvOn7RJAIyn0Ak4iNn9KajNMNZd");
                oauthToken = new AccessToken("847274528229081088-ja4r2NX1XFzL5ZfgyUfWCYUvoBlUUmi", "ofEwOgxp721XhhhzGknMgaFUTk1MN7PibO04tT5CbfRer");
                twitter.setOAuthAccessToken(oauthToken);
                
            
                       
                break;
                
            case 11:
                twitter = new TwitterFactory().getInstance();
                twitter.setOAuthConsumer("lAz9PCWe0fdcT98PcjZuvjyXj", "9q2qpgkOpzxAH2ot58LGKObzrY19bVk6WiCjpthPwIMr2Su2b0");
                oauthToken = new AccessToken("3327916969-ZUUEUFXiytIAiUk6ZzeNSA6OBuB5SCekBAMCFhe", "QaLFoIihhrQxVGsG80RzmpHGeYFKbZmXTLh4X8q3sJrt4");
                twitter.setOAuthAccessToken(oauthToken);
                
            
                break;
                
            case 12:
                twitter = new TwitterFactory().getInstance();
                twitter.setOAuthConsumer("AwmveN3x6rvexCLiRZEOieHMW", "0LnA7VBLyxdySq4eIdscpImghtXyg22LdtS7eHQh1jWOmfcbOQ");
                oauthToken = new AccessToken("3327916969-43IQOM3EsDh4bdkACsRGIhfIg0eMRFJHAnaUGoK", "JUW3rFXqfpHu1tuh7gn5vCrWyOFKVVi0h6tQUg2NevAmg");
                twitter.setOAuthAccessToken(oauthToken);
                
            
                break;
                
            case 13:
                twitter = new TwitterFactory().getInstance();
                twitter.setOAuthConsumer("cqGnlGocsoQEqAqzwJspqPiyy", "2PUpkmLCDElNyOTt06fXZwVrODQZZBreP6s6ZM3wLMMl1w2aK9");
                oauthToken = new AccessToken("3327916969-2U9JrEOgtWwVpRy2NyO8pcYdSL4r6JLH3FNv209", "qYgQqchwCEcTL13hL4TZgbq8Hqa22BTkVDMpXoGF2j3LQ");
                twitter.setOAuthAccessToken(oauthToken);
                
             
                       
                break;
                
            case 14:
                twitter = new TwitterFactory().getInstance();
                twitter.setOAuthConsumer("XQzPHZuWrineBkJRekbUFpDGV", "z4r92hUttm7sRhObK4fedAYGfNDiWBCMm4142JXXVN3i5Nhzq5");
                oauthToken = new AccessToken("847274528229081088-UsR15hciSUObkT7Fpo4AhaVSbQNcO0D", "v2BhUY0KaDU6nfJ84I52gYDaso8DojAGL9sQ4rOSgoSpV");
                twitter.setOAuthAccessToken(oauthToken);
                
             
                       
                break;
                
            
                
            
                
        } //switch
        
        
    } //changeSession

    public static void writeData() throws TwitterException, IOException, ParseException {
        try{
            List<Status> tweets;
            String lang = "";
            int page_num = 1;
            boolean morePages = true;
            Date d;
            
       while(morePages == true){
                // Paging(page_num, num_per_page [capped around 200]) : To get all posts from a user (acc to twitter api only most recent 20 tweets are returned), requesting over multiple pages
                Paging paging = new Paging(page_num,200);
                tweets = twitter.getUserTimeline(userID,paging); //getting list of tweets from users timeline (not his/her home timeline)
                page_num++;
                
                if(tweets.isEmpty()){
                    morePages = false;
                }
                
                for (int i = 0; i < tweets.size(); i++){
                   if (c >= 3200) {
                        s = (s + 1) % maxSession; //going to next session
                        out.close();
                        changeSession();
                        System.out.println("session number=" + s);
                    }
                    
                   // checking date  and langauge 
                  //  d = statuses.get(i).getCreatedAt();
                  lang = tweets.get(i).getLang(); //check for only english tweets
                  
                  if (lang.equals("en")) {
                        String s = tweets.get(i).getText().replace("\n", " ");

                        out.write(tweets.get(i).getId() + "\t" + userID + "\t" + "@" + tweets.get(i).getUser().getScreenName());
                        out.write("\t" + tweets.get(i).getUser().getFollowersCount() + "\t");
                        out.write(s + "\t" + tweets.get(i).getCreatedAt());
                        out.write("\n");
                    } //if lang
                  c++; //increment request per key
                } //for
           } //while
            
            
        } //try 
        
        catch(TwitterException te){
            uIderror.write(userID + "\t" + te.getMessage().replace("\n", " "));
            uIderror.newLine();
            
            uIds.write(userID + "\t");
            uIds.newLine();
        } // catch
        
    
    } //writeData
    
} //class
    

