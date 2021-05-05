import main.java.apiService;
import org.junit.jupiter.api.Test;
import java.io.IOException;
import java.sql.SQLException;

import static org.junit.jupiter.api.Assertions.*;

class apiServiceTest {
    apiService api = new apiService();
    @Test
    void apiCall() throws SQLException, IOException, InterruptedException {
        String [] results = api.apiCall();
        System.out.println(results);
    }
}