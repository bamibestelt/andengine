package org.anddev.andengine.entity.scene.transition;

import org.anddev.andengine.entity.scene.Scene;

/**
 * @author Nicolas Gramlich
 * @since 14:27:49 - 06.11.2010
 */
public abstract class BaseTransitionScene extends Scene {
	// ===========================================================
	// Constants
	// ===========================================================

	// ===========================================================
	// Fields
	// ===========================================================

	protected final Scene mNewScene;
	protected final Scene mOldScene;
	protected final float mDuration;

	// ===========================================================
	// Constructors
	// ===========================================================

	protected BaseTransitionScene(final int pLayerCount, final float pDuration, final Scene pOldScene, final Scene pNewScene) {
		super(pLayerCount);
		
        if (pOldScene == pNewScene) {
            throw new IllegalArgumentException("New scene must be different from old scene!");
        }
        
        this.mDuration = pDuration;
        
		this.mNewScene = pOldScene;
		this.mOldScene = pNewScene;
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

	// ===========================================================
	// Inner and Anonymous Classes
	// ===========================================================
}
