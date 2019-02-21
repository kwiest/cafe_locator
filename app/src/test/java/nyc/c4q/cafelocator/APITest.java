package nyc.c4q.cafelocator;

import org.junit.Test;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.nio.charset.Charset;

/**
 * Created by jervon.arnoldd on 2/20/19.
 */

public class APITest {

    @Test
    public void testAvailability() throws Exception {
        URLConnection connection = new URL("https://bluebottlecoffee.com/api/cafe_search/fetch.json?cafe_type=all&coordinates=true&latitude=40.6453531&longitude=-74.0150373").openConnection();
        InputStream inputStream = connection.getInputStream();

        StringBuffer buffer = new StringBuffer();
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream, Charset.defaultCharset()))) {
            for (String line; (line = reader.readLine()) != null; ) {
                buffer.append(line);
            }
        }
        assert buffer.length() > 0;
    }
}
