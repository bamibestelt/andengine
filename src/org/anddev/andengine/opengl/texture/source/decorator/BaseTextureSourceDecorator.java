package org.anddev.andengine.opengl.texture.source.decorator;

import org.anddev.andengine.opengl.texture.source.ITextureSource;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;

/**
 * @author Nicolas Gramlich
 * @since 16:43:29 - 06.08.2010
 */
public abstract class BaseTextureSourceDecorator implements ITextureSource {
	// ===========================================================
	// Constants
	// ===========================================================

	// ===========================================================
	// Fields
	// ===========================================================

	protected final ITextureSource mTextureSource;
	protected final Paint mPaint = new Paint();
	protected final boolean mAntiAliasing;

	// ===========================================================
	// Constructors
	// ===========================================================

	public BaseTextureSourceDecorator(final ITextureSource pTextureSource) {
		this(pTextureSource, false);
	}

	public BaseTextureSourceDecorator(final ITextureSource pTextureSource, final boolean pAntiAliasing) {
		this.mTextureSource = pTextureSource;
		this.mAntiAliasing = pAntiAliasing;
		this.mPaint.setAntiAlias(pAntiAliasing);
	}

	@Override
	public abstract BaseTextureSourceDecorator clone();

	// ===========================================================
	// Getter & Setter
	// ===========================================================

	// ===========================================================
	// Methods for/from SuperClass/Interfaces
	// ===========================================================

	protected abstract void onDecorateBitmap(final Canvas pCanvas);

	@Override
	public int getWidth() {
		return this.mTextureSource.getWidth();
	}

	@Override
	public int getHeight() {
		return this.mTextureSource.getHeight();
	}

	@Override
	public Bitmap onLoadBitmap() {
		final Bitmap bitmap = this.ensureLoadedBitmapIsMutable(this.mTextureSource.onLoadBitmap());

		final Canvas canvas = new Canvas(bitmap);
		this.onDecorateBitmap(canvas);
		return bitmap;
	}

	// ===========================================================
	// Methods
	// ===========================================================

	private Bitmap ensureLoadedBitmapIsMutable(final Bitmap pBitmap) {
		if(pBitmap.isMutable()) {
			return pBitmap;
		} else {
			final Bitmap mutableBitmap = pBitmap.copy(pBitmap.getConfig(), true);
			pBitmap.recycle();
			return mutableBitmap;
		}
	}

	// ===========================================================
	// Inner and Anonymous Classes
	// ===========================================================
}
