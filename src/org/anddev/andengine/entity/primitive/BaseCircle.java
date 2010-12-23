package org.anddev.andengine.entity.primitive;

import javax.microedition.khronos.opengles.GL11;

import org.anddev.andengine.entity.shape.CircleShape;
import org.anddev.andengine.opengl.vertex.CircleVertexBuffer;

/**
 * @author Nicolas Gramlich
 * @since 19:05:49 - 11.04.2010
 */
public abstract class BaseCircle extends CircleShape {
	// ===========================================================
	// Constants
	// ===========================================================

	// ===========================================================
	// Fields
	// ===========================================================

	// ===========================================================
	// Constructors
	// ===========================================================

	public BaseCircle(final float pX, final float pY, final float pRadius) {
		super(pX, pY, pRadius, new CircleVertexBuffer(GL11.GL_STATIC_DRAW));
		this.updateVertexBuffer();
	}

	public BaseCircle(final float pX, final float pY, final float pRadius, final CircleVertexBuffer pCircleVertexBuffer) {
		super(pX, pY, pRadius, pCircleVertexBuffer);
	}

	// ===========================================================
	// Getter & Setter
	// ===========================================================

	// ===========================================================
	// Methods for/from SuperClass/Interfaces
	// ===========================================================

	@Override
	public CircleVertexBuffer getVertexBuffer() {
		return (CircleVertexBuffer)super.getVertexBuffer();
	}

	@Override
	protected void onUpdateVertexBuffer(){
		this.getVertexBuffer().update(this.mRadius);
	}

	// ===========================================================
	// Methods
	// ===========================================================

	// ===========================================================
	// Inner and Anonymous Classes
	// ===========================================================
}
