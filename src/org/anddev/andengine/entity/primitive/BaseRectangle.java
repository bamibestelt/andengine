package org.anddev.andengine.entity.primitive;

import javax.microedition.khronos.opengles.GL10;
import javax.microedition.khronos.opengles.GL11;

import org.anddev.andengine.engine.camera.Camera;
import org.anddev.andengine.entity.shape.BoundingBoxShape;
import org.anddev.andengine.opengl.vertex.RectangleVertexBuffer;

/**
 * @author Nicolas Gramlich
 * @since 19:05:49 - 11.04.2010
 */
public abstract class BaseRectangle extends BoundingBoxShape {
	// ===========================================================
	// Constants
	// ===========================================================

	// ===========================================================
	// Fields
	// ===========================================================

	// ===========================================================
	// Constructors
	// ===========================================================

	public BaseRectangle(final float pX, final float pY, final float pWidth, final float pHeight) {
		super(pX, pY, pWidth, pHeight, new RectangleVertexBuffer(GL11.GL_STATIC_DRAW));
		this.updateVertexBuffer();
	}

	public BaseRectangle(final float pX, final float pY, final float pWidth, final float pHeight, final RectangleVertexBuffer pRectangleVertexBuffer) {
		super(pX, pY, pWidth, pHeight, pRectangleVertexBuffer);
	}

	// ===========================================================
	// Getter & Setter
	// ===========================================================

	// ===========================================================
	// Methods for/from SuperClass/Interfaces
	// ===========================================================

	@Override
	public RectangleVertexBuffer getVertexBuffer() {
		return (RectangleVertexBuffer)super.getVertexBuffer();
	}

	@Override
	protected void onUpdateVertexBuffer(){
		this.getVertexBuffer().update(this.mWidth, this.mHeight);
	}
	
	@Override
	protected void drawVertices(GL10 pGL, Camera pCamera) {
		pGL.glDrawArrays(GL10.GL_TRIANGLE_STRIP, 0, 4);
	}

	// ===========================================================
	// Methods
	// ===========================================================

	// ===========================================================
	// Inner and Anonymous Classes
	// ===========================================================
}
