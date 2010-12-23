package org.anddev.andengine.entity;

import org.anddev.andengine.engine.handler.IUpdateHandler;
import org.anddev.andengine.opengl.IDrawable;


/**
 * @author Nicolas Gramlich
 * @since 11:20:25 - 08.03.2010
 */
public interface IEntity extends IDrawable, IUpdateHandler {
	// ===========================================================
	// Final Fields
	// ===========================================================

	// ===========================================================
	// Methods
	// ===========================================================

	public int getZIndex();
	public void setZIndex(final int pZIndex);
}
