import junit.framework.Assert;
import org.junit.Test;

public class Tests {
    @Test
    public void testSum(){
        Assert.assertEquals(Main.sum(2, 2), 4);
    }
}