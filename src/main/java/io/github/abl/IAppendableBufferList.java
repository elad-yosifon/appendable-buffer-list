package io.github.abl;

public interface IAppendableBufferList {

    boolean add(BufferNode node);

    boolean add(char[] node, int contentLength);

    char[] join();

}
