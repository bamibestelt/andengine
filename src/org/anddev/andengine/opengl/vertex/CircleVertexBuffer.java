package org.anddev.andengine.opengl.vertex;

import android.util.FloatMath;

/**
 * @author Nicolas Gramlich
 * @since 13:07:25 - 13.03.2010
 */
public class CircleVertexBuffer extends VertexBuffer {
	// ===========================================================
	// Constants
	// ===========================================================

	public static final int VERTICES_PER_CIRCLE = 722;
	
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

		bufferData[0] = 0.0f;
		bufferData[1] = 0.0f;
		
		for(int i = 0; i <= 720; i += 2)
		{
			// x value of vertex
			bufferData[i+2] = pRadius * FloatMath.cos((float)Math.toRadians(i/2));
			
			// y value of vertex
			bufferData[i+3] = pRadius * FloatMath.sin((float)Math.toRadians(i/2));
		}
		
		//bufferData[720] = 0.0f;
		//bufferData[721] = 1.0f;

		super.setHardwareBufferNeedsUpdate();
	}

	// ===========================================================
	// Inner and Anonymous Classes
	// ===========================================================
}
