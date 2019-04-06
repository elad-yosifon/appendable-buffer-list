package io.github.abl.impl.storage;

import io.github.ifc.IAppendableBufferStorage;
import io.github.ifc.IBufferNode;

import java.util.ArrayDeque;

public class AppendableBufferArrayDeque extends ArrayDeque<IBufferNode> implements IAppendableBufferStorage {}
