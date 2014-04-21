package io.ckite;

import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;
import java.nio.ByteBuffer;
import java.util.concurrent.atomic.AtomicLong;

import ckite.rpc.Command;
import ckite.rpc.WriteCommand;
import ckite.statemachine.j.StateMachine;

public class IncStateMachine implements StateMachine {

	private AtomicLong counter = new AtomicLong(0);
	
	public static class Inc implements WriteCommand, Externalizable {

		@Override
		public void writeExternal(ObjectOutput out) throws IOException {
		}

		@Override
		public void readExternal(ObjectInput in) throws IOException,
				ClassNotFoundException {
		}
	}
	
	@Override
	public ByteBuffer serialize() {
		ByteBuffer bb =  ByteBuffer.allocate(8);
			    bb.putLong(counter.get());
			    return bb;
	}
	
	@Override
	public void deserialize(ByteBuffer arg0) {
		 counter.set(arg0.getLong());
	}
	
	@Override
	public Object apply(Command command) {
//		if (command instanceof Inc) {
//		}
		return counter.incrementAndGet();
	}
}
