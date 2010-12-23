package org.anddev.andengine.opengl.vertex;

import java.nio.FloatBuffer;

/**
 * @author Nicolas Gramlich
 * @since 13:07:25 - 13.03.2010
 */
public class LineVertexBuffer extends VertexBuffer {
	// ===========================================================
	// Constants
	// ===========================================================

	public static final int VERTICES_PER_LINE = 2;

	// ===========================================================
	// Fields
	// ===========================================================

	// ===========================================================
	// Constructors
	// ===========================================================

	public LineVertexBuffer(final int pDrawType) {
		super(2 * VERTICES_PER_LINE, pDrawType);
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

	public synchronized void update(final float pX1, final float pY1, final float pX2, final float pY2) {
		final float[] bufferData = this.mBufferData;

		bufferData[0]  = pX1;
		bufferData[1]  = pY1;

		bufferData[2]  = pX2;
		bufferData[3]  = pY2;

		super.setHardwareBufferNeedsUpdate();
	}

	// ===========================================================
	// Inner and Anonymous Classes
	// ===========================================================
}
