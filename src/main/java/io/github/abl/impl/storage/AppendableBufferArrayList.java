package io.github.abl.impl.storage;

import io.github.ifc.IAppendableBufferStorage;
import io.github.ifc.IBufferNode;

import java.util.ArrayList;

public class AppendableBufferArrayList extends ArrayList<IBufferNode> implements IAppendableBufferStorage {}
