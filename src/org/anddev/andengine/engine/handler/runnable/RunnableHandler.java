package org.anddev.andengine.engine.handler.runnable;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.anddev.andengine.engine.handler.IUpdateHandler;

/**
 * @author Nicolas Gramlich
 * @since 10:24:39 - 18.06.2010
 */
public class RunnableHandler implements IUpdateHandler {
	// ===========================================================
	// Constants
	// ===========================================================

	// ===========================================================
	// Fields
	// ===========================================================

	private List<Runnable> mRunnables = Collections.synchronizedList(new ArrayList<Runnable>());

	// ===========================================================
	// Constructors
	// ===========================================================

	// ===========================================================
	// Getter & Setter
	// ===========================================================

	// ===========================================================
	// Methods for/from SuperClass/Interfaces
	// ===========================================================

	@Override
	public void onUpdate(final float pSecondsElapsed) {
		int runnableCount = this.mRunnables.size();
		for(int i = runnableCount - 1; i >= 0; i--) {
			this.mRunnables.remove(i).run();
		}
	}

	@Override
	public void reset() {
		this.mRunnables.clear();
	}

	// ===========================================================
	// Methods
	// ===========================================================

	public void postRunnable(final Runnable pRunnable) {
		this.mRunnables.add(pRunnable);
	}

	// ===========================================================
	// Inner and Anonymous Classes
	// ===========================================================
}
