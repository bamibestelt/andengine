package org.anddev.andengine.opengl.vertex;

import java.nio.FloatBuffer;

/**
 * @author Matt Thompson <mthompson@hexwave.com>
 */
public class BatchRectangleVertexBuffer extends VertexBuffer {
	// ===========================================================
	// Constants
	// ===========================================================

	public static final int VERTICES_PER_RECTANGLE = 4;
	
	private static final int FLOAT_TO_RAW_INT_BITS_ZERO = Float.floatToRawIntBits(0);
	
	private final int mNumRectangles;
	
	// ===========================================================
	// Fields
	// ===========================================================

	// ===========================================================
	// Constructors
	// ===========================================================

	public BatchRectangleVertexBuffer(final int pNumRectangles, final int pDrawType) {
		super(2 * VERTICES_PER_RECTANGLE * pNumRectangles, pDrawType);
		mNumRectangles = pNumRectangles;
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

	public void update(final int pIndex, final float pWidth, final float pHeight) {
		final float x = 0;
		final float y = 0;
		final float x2 = pWidth;
		final float y2 = pHeight;

		final float[] bufferData = this.mBufferData;
		bufferData[0*pIndex] = x;
		bufferData[1*pIndex] = y;

		bufferData[2*pIndex] = x;
		bufferData[3*pIndex] = y2;

		bufferData[4*pIndex] = x2;
		bufferData[5*pIndex] = y;

		bufferData[6*pIndex] = x2;
		bufferData[7*pIndex] = y2;

		super.setHardwareBufferNeedsUpdate();
	}

	// ===========================================================
	// Inner and Anonymous Classes
	// ===========================================================
}
