package io.github.abl.impl.storage;

import io.github.ifc.IAppendableBufferStorage;
import io.github.ifc.IBufferNode;

import java.util.LinkedList;

public class AppendableBufferLinkedList extends LinkedList<IBufferNode> implements IAppendableBufferStorage {}
