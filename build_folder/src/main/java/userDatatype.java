package main.java;

//Depending on the number of service this user datatype will change. For now it is only dependent upon
//facebook and twitter. I needed this kind of a special datatype because this way it is easier to manage the user's
//information and remember their choices. In the real Framework this userDatatype would look like
//userdatatype(String user_id, HashMap<String, boolean> permission) because if there are 1000 of services then it
//would be better to have key:value pairs like "Facebook" : true/false etc.
public class userDatatype {
    int user_id;
    boolean facebook_user;
    boolean twitter_user;
    public userDatatype(int user_id, boolean facebook_user, boolean twitter_user){
        this.user_id = user_id;
        this.facebook_user = facebook_user;
        this.twitter_user = twitter_user;
    }
}
