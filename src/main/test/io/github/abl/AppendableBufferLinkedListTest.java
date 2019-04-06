package io.github.abl;

import org.junit.Assert;
import org.junit.Test;

public class AppendableBufferLinkedListTest {

    @Test
    public void testBasicByteConcatenation() throws Exception {
        IAppendableBufferList appendableByteLinkedList = AppendableBufferLinkedList.create();
        appendableByteLinkedList.add("a".getBytes(), 1);
        appendableByteLinkedList.add("b".getBytes(), 1);
        Assert.assertEquals("ab", appendableByteLinkedList.joinAsString());
    }

    @Test
    public void testOffsetByteConcatenation() throws Exception {
        IAppendableBufferList appendableByteLinkedList = AppendableBufferLinkedList.create();
        appendableByteLinkedList.add("abcd".getBytes(), 0,1);
        appendableByteLinkedList.add("abcd".getBytes(), 1,1);
        Assert.assertEquals("ab", appendableByteLinkedList.joinAsString());
    }

    @Test
    public void testBasicStringConcatenation() throws Exception {
        IAppendableBufferList appendableByteLinkedList = AppendableBufferLinkedList.create();
        appendableByteLinkedList.add("a", 1);
        appendableByteLinkedList.add("b", 1);
        Assert.assertEquals("ab", appendableByteLinkedList.joinAsString());
    }

    @Test
    public void testOffsetStringConcatenation() throws Exception {
        IAppendableBufferList appendableByteLinkedList = AppendableBufferLinkedList.create();
        appendableByteLinkedList.add("abcd", 0,1);
        appendableByteLinkedList.add("abcd", 1,1);
        Assert.assertEquals("ab", appendableByteLinkedList.joinAsString());
    }

    @Test
    public void testPartialByteConcatenation() throws Exception {
        IAppendableBufferList appendableByteLinkedList = AppendableBufferLinkedList.create();

        appendableByteLinkedList.add("ab".getBytes(), 1);
        appendableByteLinkedList.add("cd".getBytes(), 2);
        Assert.assertEquals("acd", appendableByteLinkedList.joinAsString());

        appendableByteLinkedList.add("efg".getBytes(), 3);
        Assert.assertEquals("acdefg", appendableByteLinkedList.joinAsString());

        appendableByteLinkedList.add("hijk".getBytes(), 1,3);
        appendableByteLinkedList.add("lmnop".getBytes(), 5);
        Assert.assertEquals("acdefgijklmnop", appendableByteLinkedList.joinAsString());
    }

    @Test
    public void testPartialStringConcatenation() throws Exception {
        IAppendableBufferList appendableByteLinkedList = AppendableBufferLinkedList.create();

        appendableByteLinkedList.add("ab", 1);
        appendableByteLinkedList.add("cd", 2);
        Assert.assertEquals("acd", appendableByteLinkedList.joinAsString());

        appendableByteLinkedList.add("efg", 3);
        Assert.assertEquals("acdefg", appendableByteLinkedList.joinAsString());

        appendableByteLinkedList.add("hijk", 1,3);
        appendableByteLinkedList.add("lmnop", 5);
        Assert.assertEquals("acdefgijklmnop", appendableByteLinkedList.joinAsString());
    }

    @Test
    public void testPartialMixedConcatenation() throws Exception {
        IAppendableBufferList appendableByteLinkedList = AppendableBufferLinkedList.create();

        appendableByteLinkedList.add("ab".getBytes(), 1);
        appendableByteLinkedList.add("cd", 2);
        Assert.assertEquals("acd", appendableByteLinkedList.joinAsString());

        appendableByteLinkedList.add("efg".getBytes(), 3);
        Assert.assertEquals("acdefg", appendableByteLinkedList.joinAsString());

        appendableByteLinkedList.add("hijk", 1,3);
        appendableByteLinkedList.add("lmnop", 5);
        Assert.assertEquals("acdefgijklmnop", appendableByteLinkedList.joinAsString());
    }
}