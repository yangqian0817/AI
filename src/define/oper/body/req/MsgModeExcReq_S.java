package define.oper.body.req;

import java.nio.ByteBuffer;

import com.mac.smartcontrol.util.FormatTransfer;

public class MsgModeExcReq_S {
	short usIdx;

	private MsgModeExcReq_S(short usIdx) {
		super();
		this.usIdx = usIdx;
	}

	public short getUsIdx() {
		return usIdx;
	}

	public void setUsIdx(short usIdx) {
		this.usIdx = usIdx;
	}
	public byte[] getMsgModeExcReq_S(){
		ByteBuffer bb_Msg=ByteBuffer.allocate(2);
		bb_Msg.asShortBuffer().put(FormatTransfer.reverseShort(usIdx));
		return bb_Msg.array();
	}
}