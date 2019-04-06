package io.github.abl;

public interface IAppendableBufferList {

    boolean add(IBufferNode node);

    boolean add(byte[] node, int length);

    boolean add(byte[] node, int offset, int length);

    byte[] join();

    boolean add(String node, int byteLength);

    boolean add(String node, int byteOffset, int byteLength);

    String joinAsString();

}
