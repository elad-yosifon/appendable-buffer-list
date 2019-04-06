package io.github.abl;

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
    public boolean add(byte[] node, int length) {
        return this.add(new BufferNode(node, length));
    }

    @Override
    public boolean add(byte[] node, int offset, int length) {
        return this.add(new OffsetBufferNode(node, offset, length));
    }

    @Override
    public boolean add(String node, int byteLength) {
        return this.add(new BufferNode(node.getBytes(), byteLength));
    }

    @Override
    public boolean add(String node, int byteOffset, int byteLength) {
        return this.add(new OffsetBufferNode(node.getBytes(), byteOffset, byteLength));
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
