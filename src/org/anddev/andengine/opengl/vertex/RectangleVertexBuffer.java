package org.anddev.andengine.opengl.vertex;

import com.badlogic.gdx.utils.BufferUtils;


/**
 * @author Nicolas Gramlich
 * @since 13:07:25 - 13.03.2010
 */
public class RectangleVertexBuffer extends VertexBuffer {
	// ===========================================================
	// Constants
	// ===========================================================

	public static final int VERTICES_PER_RECTANGLE = 4;
	
	//private static final int FLOAT_TO_RAW_INT_BITS_ZERO = Float.floatToRawIntBits(0);

	// ===========================================================
	// Fields
	// ===========================================================

	// ===========================================================
	// Constructors
	// ===========================================================

	public RectangleVertexBuffer(final int pDrawType) {
		super(2 * VERTICES_PER_RECTANGLE, pDrawType);
	}

	// ===========================================================
	// Getter & Setter
	// ===========================================================

	// ===========================================================
	// Methods for/from SuperClass/Interfaces
	// ===========================================================

	// ===========================================================
	// Methods
	// ===========================================================

	public synchronized void update(final float pWidth, final float pHeight) {
		//final float[] bufferData = this.mBufferData;
		
		mBufferData[0] = 0;
		mBufferData[1] = 0;

		mBufferData[2] = 0;
		mBufferData[3] = pHeight;

		mBufferData[4] = pWidth;
		mBufferData[5] = 0;

		mBufferData[6] = pWidth;
		mBufferData[7] = pHeight;

		//this.getFloatBuffer();
		BufferUtils.copy(mBufferData, mFloatBuffer, mFloatBuffer.capacity(), 0);
		
		super.setHardwareBufferNeedsUpdate();
	}

	// ===========================================================
	// Inner and Anonymous Classes
	// ===========================================================
}
