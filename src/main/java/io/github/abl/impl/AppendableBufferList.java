package io.github.abl.impl;

import io.github.abl.impl.storage.AppendableBufferArrayDeque;
import io.github.ifc.IAppendableBufferList;
import io.github.ifc.IAppendableBufferStorage;
import io.github.ifc.IBufferNode;
import io.github.abl.impl.node.ByteBufferNode;
import io.github.abl.impl.node.ByteBufferOffsetNode;
import io.github.abl.impl.node.StringBufferOffsetNode;
import io.github.abl.impl.node.StringBufferNode;
import io.github.abl.impl.storage.AppendableBufferArrayList;
import io.github.abl.impl.storage.AppendableBufferLinkedList;

public class AppendableBufferList implements IAppendableBufferList {

    private int byteLength = 0;
    private IAppendableBufferStorage storage;

    private AppendableBufferList(IAppendableBufferStorage storage) {
        this.storage = storage;
    }

    public static IAppendableBufferList createArrayDeque() {
        return new AppendableBufferList(new AppendableBufferArrayDeque());
    }

    public static IAppendableBufferList createArrayList() {
        return new AppendableBufferList(new AppendableBufferArrayList());
    }

    public static IAppendableBufferList create() {
        return new AppendableBufferList(new AppendableBufferLinkedList());
    }

    @Override
    public boolean add(IBufferNode node) {
        this.byteLength += node.byteLength();
        return this.storage.add(node);
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
        for (IBufferNode node : this.storage) {
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
