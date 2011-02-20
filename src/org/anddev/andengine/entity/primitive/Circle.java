package org.anddev.andengine.entity.primitive;

import javax.microedition.khronos.opengles.GL10;

import org.anddev.andengine.opengl.util.GLHelper;
import org.anddev.andengine.opengl.vertex.CircleVertexBuffer;

/**
 * @author Nicolas Gramlich
 * @since 12:18:49 - 13.03.2010
 */
public class Circle extends BaseCircle {
	// ===========================================================
	// Constants
	// ===========================================================

	// ===========================================================
	// Fields
	// ===========================================================

	// ===========================================================
	// Constructors
	// ===========================================================

	public Circle(final float pX, final float pY, final float pRadius) {
		super(pX, pY, pRadius);
	}

	public Circle(final float pX, final float pY, final float pRadius, final CircleVertexBuffer pCircleVertexBuffer) {
		super(pX, pY, pRadius, pCircleVertexBuffer);
	}

	// ===========================================================
	// Getter & Setter
	// ===========================================================

	// ===========================================================
	// Methods for/from SuperClass/Interfaces
	// ===========================================================

	@Override
	protected void onInitDraw(final GL10 pGL) {
		super.onInitDraw(pGL);
		GLHelper.disableTextures(pGL);
		GLHelper.disableTexCoordArray(pGL);
		GLHelper.lineWidth(pGL, 1.0f);
	}

	// ===========================================================
	// Methods
	// ===========================================================

	// ===========================================================
	// Inner and Anonymous Classes
	// ===========================================================
}
