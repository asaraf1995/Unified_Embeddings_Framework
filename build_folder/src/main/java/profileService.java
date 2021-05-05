package main.java;
import main.java.sqlConnection.sqlConnect;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;

//This service is responsible for adding new users to the platform and also for removing the existing users from the platform
//if they wish to revoke the rights of using their data.
public class profileService {
    dataService data = new dataService();
    monitoringService monitor = new monitoringService();
    int min = 1000;
    int max = 10000;

    //This function is for adding new users to the platform depending on their permission.
    //This function would generate a realtime alphanumeric Id or a hash for each new user in the actual Framework. For now,
    //I've used random integers between the range of 1000 - 10000 for user_id.
    public String newUser(int no_facebook_users, int no_of_twitter_users) throws SQLException, IOException, InterruptedException {
        ArrayList<userDatatype> users = new ArrayList<userDatatype>();
        String result = "";
        for(int i = 0; i < no_facebook_users; i++){
            int user_id = (int)Math.floor(Math.random()*(max-min+1)+min);
            users.add(new userDatatype(user_id, true, false));
            result = result + " profileService.newUser() called, New facebook_user " + user_id;
        }
        for(int i = 0; i < no_of_twitter_users; i++){
            int user_id = (int)Math.floor(Math.random()*(max-min+1)+min);
            users.add(new userDatatype(user_id, false, true));
            result = result + " profileService.newUser() called, New twitter_user " + user_id;
        }
        data.push(users);
        result = result + monitor.dataAdded(no_facebook_users, no_of_twitter_users);
        return result;
    }

    //this function is responsible for revoking the user's right at their will and also deleting the user
    //if they wish.
    public String existingUser(ArrayList<userDatatype> users) throws SQLException {
        int count = 0;
        sqlConnect sql = new sqlConnect();
        Connection con = null;
        PreparedStatement stmt = null;
        String search_user = "";
        String update_user = "";
        String result = "";
        if(sql.connect()){
            con = sql.getConnection();
        }
        for(int i = 0; i < users.size(); i++){
            if(users.get(i).twitter_user && users.get(i).facebook_user){
                System.out.println(users.get(i).user_id);
                result = result + " profileService.existingUser() called, Deleted a facebook and twitter user ";
                update_user = "Delete from ml_temp where userid = ?";
                stmt = con.prepareStatement(update_user);
                stmt.setInt(1, users.get(i).user_id);
                stmt.execute();

                search_user = "Delete from facebook_dataset where userid = ?";
                stmt = con.prepareStatement(search_user);
                stmt.setInt(1, users.get(i).user_id);
                stmt.execute();

                search_user = "Delete from twitter_dataset where userid = ?";
                stmt = con.prepareStatement(search_user);
                stmt.setInt(1, users.get(i).user_id);
                stmt.execute();

                search_user = "Delete from embeddings_table where User_id = ?";
                stmt = con.prepareStatement(search_user);
                stmt.setInt(1, users.get(i).user_id);
                stmt.execute();
                count++;
            }
            else if(users.get(i).facebook_user){
                result = result + " profileService.existingUser() called, Deleted a facebook user ";
                search_user = "Delete from facebook_dataset where userid = ?";
                update_user = "Update ml_temp set friend_count = 0, likes = 0, likes_received = 0 where userid = ?";
                stmt = con.prepareStatement(search_user);
                stmt.setInt(1, users.get(i).user_id);
                stmt.execute();
                stmt = con.prepareStatement(update_user);
                stmt.setInt(1, users.get(i).user_id);
                stmt.execute();
                search_user = "Delete from embeddings_table where User_id = ?";
                stmt = con.prepareStatement(search_user);
                stmt.setInt(1, users.get(i).user_id);
                stmt.execute();
                count++;
            }
            else if(users.get(i).twitter_user){
                result = result + " profileService.existingUser() called, Deleted a twitter user ";
                search_user = "Delete from twitter_dataset where userid = ?";
                update_user = "Update ml_temp set tweets = 0, follower_count = 0 where userid = ?";
                stmt = con.prepareStatement(search_user);
                stmt.setInt(1, users.get(i).user_id);
                stmt.execute();
                stmt = con.prepareStatement(update_user);
                stmt.setInt(1, users.get(i).user_id);
                stmt.execute();
                search_user = "Delete from embeddings_table where User_id = ?";
                stmt = con.prepareStatement(search_user);
                stmt.setInt(1, users.get(i).user_id);
                stmt.execute();
                count++;
            }
            else{
                System.out.println(" profileService.existingUser() called, User not found!! ");
                count--;
            }
        }
        result = result + monitor.dataDeleted(count);
        return result;
    }



}
