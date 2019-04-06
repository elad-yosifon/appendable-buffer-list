package io.github.abl;

import io.github.abl.impl.AppendableBufferLinkedList;
import org.junit.Assert;
import org.junit.Test;

import java.nio.charset.Charset;

public class AppendableBufferLinkedListTest {

    @Test
    public void testBasicByteConcatenation() throws Exception {
        IAppendableBufferList appendableByteLinkedList = AppendableBufferLinkedList.create();
        appendableByteLinkedList.add("a".getBytes());
        appendableByteLinkedList.add("b".getBytes());
        Assert.assertEquals("ab", appendableByteLinkedList.joinAsString());
    }

    @Test
    public void testOffsetByteConcatenation() throws Exception {
        IAppendableBufferList appendableByteLinkedList = AppendableBufferLinkedList.create();
        appendableByteLinkedList.add("abcd".getBytes(),1);
        appendableByteLinkedList.add("abcd".getBytes(), 1,1);
        Assert.assertEquals("ab", appendableByteLinkedList.joinAsString());
    }

    @Test
    public void testBasicStringConcatenation() throws Exception {
        IAppendableBufferList appendableByteLinkedList = AppendableBufferLinkedList.create();
        appendableByteLinkedList.add("a");
        appendableByteLinkedList.add("b");
        Assert.assertEquals("ab", appendableByteLinkedList.joinAsString());
    }

    @Test
    public void testOffsetStringConcatenation() throws Exception {
        IAppendableBufferList appendableByteLinkedList = AppendableBufferLinkedList.create();
        appendableByteLinkedList.add("abcd",1);
        appendableByteLinkedList.add("abcd", 1,1);
        Assert.assertEquals("ab", appendableByteLinkedList.joinAsString());
    }

    @Test
    public void testPartialByteConcatenation() throws Exception {
        IAppendableBufferList appendableByteLinkedList = AppendableBufferLinkedList.create();

        appendableByteLinkedList.add("ab".getBytes(), 1);
        appendableByteLinkedList.add("cd".getBytes());
        Assert.assertEquals("acd", appendableByteLinkedList.joinAsString());

        appendableByteLinkedList.add("efg".getBytes());
        Assert.assertEquals("acdefg", appendableByteLinkedList.joinAsString());

        appendableByteLinkedList.add("hijk".getBytes(), 1,3);
        appendableByteLinkedList.add("lmnop".getBytes());
        Assert.assertEquals("acdefgijklmnop", appendableByteLinkedList.joinAsString());
    }

    @Test
    public void testPartialStringConcatenation() throws Exception {
        IAppendableBufferList appendableByteLinkedList = AppendableBufferLinkedList.create();

        appendableByteLinkedList.add("ab", 1);
        appendableByteLinkedList.add("cd");
        Assert.assertEquals("acd", appendableByteLinkedList.joinAsString());

        appendableByteLinkedList.add("efg");
        Assert.assertEquals("acdefg", appendableByteLinkedList.joinAsString());

        appendableByteLinkedList.add("hijk", 1,3);
        appendableByteLinkedList.add("lmnop");
        Assert.assertEquals("acdefgijklmnop", appendableByteLinkedList.joinAsString());
    }

    @Test
    public void testPartialMixedConcatenation() throws Exception {
        IAppendableBufferList appendableByteLinkedList = AppendableBufferLinkedList.create();

        appendableByteLinkedList.add("ab".getBytes(), 1);
        appendableByteLinkedList.add("cd");
        Assert.assertEquals("acd", appendableByteLinkedList.joinAsString());

        appendableByteLinkedList.add("efg".getBytes());
        Assert.assertEquals("acdefg", appendableByteLinkedList.joinAsString());

        appendableByteLinkedList.add("hijk", 1,3);
        appendableByteLinkedList.add("lmnop");
        Assert.assertEquals("acdefgijklmnop", appendableByteLinkedList.joinAsString());
    }
}