import com.example.school.services.InMemCache;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@RunWith(JUnit4.class)
public class TestInMem {

    InMemCache inMemCache = new InMemCache(1);

    @Test
    public void testInMem() throws Exception {
        List<Integer> integers = new ArrayList<>();
        Set<String> stringSet = new HashSet<>();
        Set<String> stringSet1 = new HashSet<>();
        integers.add(1);
        integers.add(2);
        stringSet.add("key-1");
        stringSet.add("key-2");
        stringSet1.add("key-3");


        //put the key value
        inMemCache.Set(null, null);

        //get and verify the expected is equal
        Object result = inMemCache.Get(stringSet);

        Assert.assertNull(result);
        Assert.assertNotEquals(result, integers);
    }

}
