package danger.orespawn;

import cpw.mods.fml.common.network.simpleimpl.IMessage;
import io.netty.buffer.ByteBuf;















public class RiderControlMessage implements IMessage
{
	public int keystate;
	private int previous;
	
	public RiderControlMessage()
	{
		this.keystate = 0;
	}

	public void fromBytes(ByteBuf buf)
	{
		this.fromInteger(buf.readUnsignedByte());
	}

	public void toBytes(ByteBuf buf)
	{
		buf.writeByte(this.toInteger());
	}

	public void fromInteger(int value) {
		this.keystate = value;
	}

	public int toInteger() {
		return this.keystate;
	}

	public boolean hasChanged() {
		int current = this.keystate;
		boolean changed = this.previous != current;
		this.previous = current;
		return changed;
	}
}
