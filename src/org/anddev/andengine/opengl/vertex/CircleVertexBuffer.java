package org.anddev.andengine.opengl.vertex;

import java.nio.FloatBuffer;

import android.util.FloatMath;

/**
 * @author Nicolas Gramlich
 * @since 13:07:25 - 13.03.2010
 */
public class CircleVertexBuffer extends VertexBuffer {
	// ===========================================================
	// Constants
	// ===========================================================

	public static final int VERTICES_PER_CIRCLE = 360;
	
	// ===========================================================
	// Fields
	// ===========================================================

	// ===========================================================
	// Constructors
	// ===========================================================

	public CircleVertexBuffer(final int pDrawType) {
		super(2 * VERTICES_PER_CIRCLE, pDrawType);
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

	public synchronized void update(final float pRadius) {

		final float[] bufferData = this.mBufferData;

		// Create 360 vertices
		for(int theta = 0; theta <= VERTICES_PER_CIRCLE; theta += 2)
		{
			// x value of vertex
			bufferData[theta] = pRadius * FloatMath.cos((float)Math.toRadians(theta));
			
			// y value of vertex
			bufferData[theta+1] = pRadius * FloatMath.sin((float)Math.toRadians(theta));
		}

		final FloatBuffer buffer = this.getFloatBuffer();
		buffer.position(0);
		buffer.put(bufferData);
		buffer.position(0);

		super.setHardwareBufferNeedsUpdate();
	}

	// ===========================================================
	// Inner and Anonymous Classes
	// ===========================================================
}
