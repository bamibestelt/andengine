package org.anddev.andengine.ui.activity;

import org.anddev.andengine.opengl.view.RenderSurfaceView;

/**
 * @author Nicolas Gramlich
 * @since 10:18:50 - 06.10.2010
 */
public abstract class LayoutGameActivity extends BaseGameActivity {
	// ===========================================================
	// Constants
	// ===========================================================

	// ===========================================================
	// Fields
	// ===========================================================

	// ===========================================================
	// Constructors
	// ===========================================================

	// ===========================================================
	// Getter & Setter
	// ===========================================================

	// ===========================================================
	// Methods for/from SuperClass/Interfaces
	// ===========================================================

	protected abstract int getLayoutID();
	protected abstract int getRenderSurfaceViewID();

	@Override
	protected void onSetContentView() {
		super.setContentView(this.getLayoutID());

		this.mRenderSurfaceView = (RenderSurfaceView) this.findViewById(this.getRenderSurfaceViewID());
		this.mRenderSurfaceView.setEGLConfigChooser(false);
		//this.mRenderSurfaceView.setDebugFlags(RenderSurfaceView.DEBUG_CHECK_GL_ERROR | RenderSurfaceView.DEBUG_LOG_GL_CALLS);
		//this.mRenderSurfaceView.setEGLConfigChooser(8,8,8,0,0,0);
		//this.mRenderSurfaceView.getHolder().setFormat(PixelFormat.RGBX_8888);
		this.mRenderSurfaceView.setRenderer(this.mEngine);
	}

	// ===========================================================
	// Methods
	// ===========================================================

	// ===========================================================
	// Inner and Anonymous Classes
	// ===========================================================
}
