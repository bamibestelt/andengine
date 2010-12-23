package org.anddev.andengine.opengl.vertex;

import java.nio.FloatBuffer;

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
		final float[] bufferData = this.mBufferData;
		
		bufferData[0] = 0;
		bufferData[1] = 0;

		bufferData[2] = 0;
		bufferData[3] = pHeight;

		bufferData[4] = pWidth;
		bufferData[5] = 0;

		bufferData[6] = pWidth;
		bufferData[7] = pHeight;

		final FloatBuffer buffer = this.getFloatBuffer();
		BufferUtils.copy(bufferData, buffer, 8, 0);

		super.setHardwareBufferNeedsUpdate();
	}

	// ===========================================================
	// Inner and Anonymous Classes
	// ===========================================================
}
