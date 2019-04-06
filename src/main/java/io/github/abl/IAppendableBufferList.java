package io.github.abl;

public interface IAppendableBufferList {

    boolean add(IBufferNode node);

    boolean add(byte[] node);
    boolean add(byte[] node, int length);
    boolean add(byte[] node, int offset, int length);

    boolean add(String node);
    boolean add(String node, int length);
    boolean add(String node, int offset, int length);

    byte[] join();
    String joinAsString();

}
