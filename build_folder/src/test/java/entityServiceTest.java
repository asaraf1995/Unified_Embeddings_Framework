import main.java.sqlConnection.sqlConnect;
import main.java.entityService;
import main.java.userDatatype;
import org.junit.jupiter.api.Test;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;

import static org.junit.jupiter.api.Assertions.*;

class entityServiceTest {
    entityService e = new entityService();
    @Test
    void fetchData() throws SQLException {
        String result = e.fetchData(4238);
         sqlConnect sql = new sqlConnect();
        boolean rs = false;
         if(sql.connect()){
             Connection con = sql.getConnection();
             Statement stmt = con.createStatement();
             rs = stmt.execute("Select * from embeddings_table where user_id = 4238");
         }
        if (result == " entityService.fetchData() called, User not found "){
            System.out.println(rs);
            assertEquals(false, rs);
        }
        else{
            System.out.println(rs);
            assertEquals(true, rs);
        }
    }

    @Test
    void writeData() throws SQLException {
        int min = 40000;
        int max = 300000;
        int user_id = (int)Math.floor(Math.random()*(max-min+1)+min);
        userDatatype user = new userDatatype(user_id, true, true);
        String result = e.writeData(user);
        sqlConnect sql = new sqlConnect();
        boolean rs = false;
        if(sql.connect()){
            Connection con = sql.getConnection();
            PreparedStatement stmt = null;
            String update_user = "Select * from embeddings_table where user_id = ?";
            stmt = con.prepareStatement(update_user);
            stmt.setInt(1, user_id);
            rs = stmt.execute();
        }
        if (result == " entityService.writeData() called, User Data insert successfully in facebook_dataset twitter_dataset"){
            System.out.println(rs);
            assertEquals(true, rs);
        }
    }
}