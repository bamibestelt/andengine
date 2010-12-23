package org.anddev.andengine.entity.scene.transition;

import javax.microedition.khronos.opengles.GL10;

import org.anddev.andengine.engine.camera.Camera;
import org.anddev.andengine.engine.handler.timer.ITimerCallback;
import org.anddev.andengine.engine.handler.timer.TimerHandler;
import org.anddev.andengine.entity.primitive.Rectangle;
import org.anddev.andengine.entity.scene.Scene;
import org.anddev.andengine.entity.shape.modifier.AlphaModifier;
import org.anddev.andengine.entity.shape.modifier.SequenceShapeModifier;

/**
 * @author Nicolas Gramlich
 * @since 15:20:42 - 06.11.2010
 */
public class FadeTransitionScene extends BaseTransitionScene {
	// ===========================================================
	// Constants
	// ===========================================================

	// ===========================================================
	// Fields
	// ===========================================================

	private final Rectangle mFadeRectangle;

	// ===========================================================
	// Constructors
	// ===========================================================

	public FadeTransitionScene(final float pDuration, final Scene pOldScene, final Scene pNewScene, final float pRed, final float pGreen, final float pBlue) {
		super(1, pDuration, pOldScene, pNewScene);
		
		this.setBackgroundEnabled(false);

		final float durationHalf = pDuration * 0.5f;

		this.mFadeRectangle = new Rectangle(0, 0, 100, 100);
		this.mFadeRectangle.setColor(pRed, pGreen, pBlue);
		this.mFadeRectangle.addShapeModifier(new SequenceShapeModifier(new AlphaModifier(durationHalf, 0, 1), new AlphaModifier(durationHalf, 1, 0)));
		this.getBottomLayer().addEntity(this.mFadeRectangle);

		this.mNewScene.setVisible(false);

		this.registerUpdateHandler(new TimerHandler(durationHalf, new ITimerCallback() {
			@Override
			public void onTimePassed(final TimerHandler pTimerHandler) {
				FadeTransitionScene.this.mOldScene.setVisible(false);
				FadeTransitionScene.this.mNewScene.setVisible(true);
			}
		}));
	}

	// ===========================================================
	// Getter & Setter
	// ===========================================================

	// ===========================================================
	// Methods for/from SuperClass/Interfaces
	// ===========================================================

	@Override
	protected void onManagedDraw(final GL10 pGL, final Camera pCamera) {
		this.mOldScene.onDraw(pGL, pCamera);
		this.mNewScene.onDraw(pGL, pCamera);
		super.onManagedDraw(pGL, pCamera);
	}

	// ===========================================================
	// Methods
	// ===========================================================

	// ===========================================================
	// Inner and Anonymous Classes
	// ===========================================================
}
