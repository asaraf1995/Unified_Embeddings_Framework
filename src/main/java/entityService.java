package main.java;
import java.sql.*;
import main.java.sqlConnection.sqlConnect;

//This service is required to read and write a user's data in all the tables depending upon user's permission. For example:
// a user can permit to allow data collection by only facebook and deny the other services.
public class entityService {
    //this function returns a String stating whether the user embedding was found in the embeddings_table or not.
    public String fetchData(int userid) throws SQLException {
        String search_query = String.format("SELECT Embeddings FROM embeddings_table where User_id = %s", userid);
        sqlConnect sql = new sqlConnect();
        if (sql.connect()){
            Connection con = sql.getConnection();
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery(search_query);
            while (rs.next())
            {
                String embeddings = rs.getString("Embeddings");
                return (" entityService.fetchData() called, User Embedding: " + embeddings);
            }
            stmt.close();
        }
        return (" entityService.fetchData() called, User not found ");
    }
    //this function returns a String stating in what all tables was the user data added to depending upon user's permission.
    //for example: either facebook or twitter table or both.
    public String writeData(userDatatype user) throws SQLException {
        sqlConnect sql = new sqlConnect();
        Connection con;
        Statement stmt = null;
        if(sql.connect()){
            con = sql.getConnection();
            stmt = con.createStatement();
        }
        int friend_count = 0;
        int tweets = 0;
        int follower_count = 0;
        int likes = 0;
        int likes_received = 0;
        String write_query_twitter = "";
        String write_query_facebook = "";
        String write_query_ml_temp = "";
        String facebook_insert = "";
        String twitter_insert = "";
        if(user.twitter_user){
            //for now I'm using static values for the user data. In the real Framework this would be
            //received in runtime whenever the user permits.
            friend_count = 120; tweets = 10; follower_count = 100;
            write_query_twitter = String.format("insert into twitter_dataset (userid, age, dob_day, dob_year, dob_month, gender, friend_count, tweets, follower_count)"
                    + " values (%s, 24, 10, 1994, 4, 'M', %s, %s, %s)", user.user_id, friend_count, tweets, follower_count);
            twitter_insert = "twitter_dataset";
            stmt.executeUpdate(write_query_twitter);
        }
        if(user.facebook_user){
            //for now I'm using static values for the user data. In the real Framework this would be
            //received in runtime whenever the user permits.
            friend_count = 100; likes = 140; likes_received = 160;
            write_query_facebook = String.format("insert into facebook_dataset (userid, age, dob_day, dob_year, dob_month, gender, friend_count, likes, likes_received)"
                    + " values (%s, 24, 10, 1994, 4, 'M', %s, %s, %s)", user.user_id, friend_count, likes, likes_received);
            facebook_insert = "facebook_dataset";
            stmt.executeUpdate(write_query_facebook);
        }
        write_query_ml_temp = String.format("insert into ml_temp (userid, age, dob_day, dob_year, dob_month, gender, friend_count, tweets, follower_count, likes, likes_received)"
                + " values (%s, 24, 10, 1994, 4, 'M', %s, %s, %s, %s, %s)", user.user_id, friend_count, tweets, follower_count, likes, likes_received);

            stmt.executeUpdate(write_query_ml_temp);
            stmt.close();

        return(" entityService.writeData() called, User Data insert successfully in " + facebook_insert + " " + twitter_insert);
    }
}

