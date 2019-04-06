package io.github.abl.impl;

import io.github.abl.IAppendableBufferList;
import io.github.abl.IBufferNode;
import io.github.abl.impl.node.ByteBufferNode;
import io.github.abl.impl.node.ByteBufferOffsetNode;
import io.github.abl.impl.node.StringBufferOffsetNode;
import io.github.abl.impl.node.StringBufferNode;

import java.util.LinkedList;

public class AppendableBufferLinkedList extends LinkedList<IBufferNode> implements IAppendableBufferList {

    private int byteLength;

    private AppendableBufferLinkedList() {
    }

    public static IAppendableBufferList create() {
        return new AppendableBufferLinkedList();
    }

    @Override
    public boolean add(IBufferNode node) {
        this.byteLength += node.byteLength();
        return super.add(node);
    }

    @Override
    public boolean add(byte[] node) {
        return this.add(new ByteBufferNode(node, node.length));
    }

    @Override
    public boolean add(byte[] node, int length) {
        return this.add(new ByteBufferNode(node, length));
    }

    @Override
    public boolean add(byte[] node, int offset, int length) {
        return this.add(new ByteBufferOffsetNode(node, offset, length));
    }

    @Override
    public boolean add(String node) {
        return this.add(new StringBufferNode(node, node.length()));
    }

    @Override
    public boolean add(String node, int length) {
        return this.add(new StringBufferNode(node, length));
    }

    @Override
    public boolean add(String node, int offset, int length) {
        return this.add(new StringBufferOffsetNode(node, offset, length));
    }

    @Override
    public byte[] join() {
        byte[] buffer = new byte[byteLength];
        int cursor = 0;
        for (IBufferNode node : this) {
            node.copyOnto(buffer, cursor);
            cursor += node.byteLength();
        }
        return buffer;
    }

    @Override
    public String joinAsString() {
        return new String(this.join());
    }
}
