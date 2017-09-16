import org.junit.Assert;
import org.junit.Test;

public class AppendableBufferLinkedListTest {

    @Test
    public void testBasicConcatenation() throws Exception {
        IAppendableBufferList appendableByteLinkedList = AppendableBufferLinkedList.create();
        appendableByteLinkedList.add(new char[]{'a'}, 1);
        appendableByteLinkedList.add(new char[]{'b'}, 1);
        Assert.assertEquals("ab", new String(appendableByteLinkedList.join()));
    }

    @Test
    public void testPartialBufferConcatenation() throws Exception {
        IAppendableBufferList appendableByteLinkedList = AppendableBufferLinkedList.create();

        appendableByteLinkedList.add(new char[]{'a', 'b'}, 1);
        appendableByteLinkedList.add(new char[]{'c', 'd'}, 2);
        Assert.assertEquals("acd", new String(appendableByteLinkedList.join()));

        appendableByteLinkedList.add(new char[]{'e', 'f', 'g'}, 3);
        Assert.assertEquals("acdefg", new String(appendableByteLinkedList.join()));

        appendableByteLinkedList.add(new char[]{'h', 'i', 'j', 'k'}, 3);
        appendableByteLinkedList.add(new char[]{'l', 'm', 'n', 'o', 'p'}, 5);
        Assert.assertEquals("acdefghijlmnop", new String(appendableByteLinkedList.join()));

    }
}