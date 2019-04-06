package io.github.abl;

import java.util.LinkedList;

public class AppendableBufferLinkedList extends LinkedList<BufferNode> implements IAppendableBufferList {

    private int contentLength;

    private AppendableBufferLinkedList() {
    }

    public static IAppendableBufferList create() {
        return new AppendableBufferLinkedList();
    }

    @Override
    public boolean add(BufferNode node) {
        this.contentLength += node.getContentLength();
        return super.add(node);
    }

    @Override
    public boolean add(char[] node, int contentLength) {
        BufferNode bufferNode = new BufferNode(node, contentLength);
        return this.add(bufferNode);
    }

    @Override
    public char[] join() {
        char[] buffer = new char[contentLength];
        int    cursor = 0;
        for (BufferNode node : this) {
            System.arraycopy(node.getContent(), 0, buffer, cursor, node.getContentLength());
            cursor += node.getContentLength();
        }
        return buffer;
    }

}
