

import main.java.dataService;
import main.java.sqlConnection.sqlConnect;
import main.java.userDatatype;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class dataServiceTest {
    dataService d = new dataService();
    @Test
    void pull() throws SQLException {
        int [] users = new int[]{1090279, 1093992};
        String res = d.pull(users);
        sqlConnect sql = new sqlConnect();
        boolean rs1 = false;
        boolean rs2 = false;
        if(sql.connect()) {
            Connection con = sql.getConnection();
            Statement stmt = con.createStatement();
            rs1 = stmt.execute("Select * from embeddings_table where user_id = 1090279");
            rs2 = stmt.execute("Select * from embeddings_table where user_id = 1093992");
        }
        System.out.println(res);
        assertEquals(true, rs1);
        assertEquals(true, rs2);
//        int [] users = new int[]{33397};
//        String res = d.pull(users);
//        System.out.println(res);
    }

    @Test
    void push() throws SQLException, IOException, InterruptedException {
        ArrayList<userDatatype> user = new ArrayList<userDatatype>();
        int min = 40000;
        int max = 300000;
        int user_id = (int)Math.floor(Math.random()*(max-min+1)+min);
        System.out.println(user_id);
        user.add(new userDatatype(user_id, true, true));
        String res = d.push(user);
        boolean rs1 = false;
        sqlConnect sql = new sqlConnect();
        if(sql.connect()) {
            Connection con = sql.getConnection();
            PreparedStatement stmt = null;
            String update_user = "Select * from embeddings_table where user_id = ?";
            stmt = con.prepareStatement(update_user);
            stmt.setInt(1, user_id);
            rs1 = stmt.execute();
        }
        assertEquals(true, rs1);
        System.out.println(res);
        System.out.println("dataService.push() called, to push data successfully run encoder-decoder.py");
    }
}