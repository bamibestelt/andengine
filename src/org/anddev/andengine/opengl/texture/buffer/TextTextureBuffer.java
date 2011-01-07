package org.anddev.andengine.opengl.texture.buffer;

import java.nio.FloatBuffer;

import org.anddev.andengine.opengl.buffer.BufferObject;

import com.badlogic.gdx.utils.BufferUtils;

/**
 * @author Nicolas Gramlich
 * @since 11:05:56 - 03.04.2010
 */
public class TextTextureBuffer extends BufferObject {
	// ===========================================================
	// Constants
	// ===========================================================

	// ===========================================================
	// Fields
	// ===========================================================

	// ===========================================================
	// Constructors
	// ===========================================================

	public TextTextureBuffer(final int pCapacity, final int pDrawType) {
		super(pCapacity, pDrawType);
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

	public void updateHardware()
	{
		final FloatBuffer textureFloatBuffer = this.getFloatBuffer();
		BufferUtils.copy(this.mBufferData, textureFloatBuffer, textureFloatBuffer.capacity(), 0);
		this.setHardwareBufferNeedsUpdate();
	}
	
	/*
	public synchronized void update(final Font pFont, final String[] pLines) {
		final float[] bufferData = this.mBufferData;

		final Font font = pFont;
		final String[] lines = pLines;
		
		int idx = 0;

		final int lineCount = lines.length;
		for (int i = 0; i < lineCount; i++) {
			final String line = lines[i];

			final int lineLength = line.length();
			for (int j = 0; j < lineLength; j++) {
				final Letter letter = font.getLetter(line.charAt(j));

				final float letterTextureX = letter.mTextureX;
				final float letterTextureY = letter.mTextureY;
				final float letterTextureX2 = letterTextureX + letter.mTextureWidth;
				final float letterTextureY2 = letterTextureY + letter.mTextureHeight;
				
				bufferData[idx++] = letterTextureX;
				bufferData[idx++] = letterTextureY;
				
				bufferData[idx++] = letterTextureX;
				bufferData[idx++] = letterTextureY2;
				
				bufferData[idx++] = letterTextureX2;
				bufferData[idx++] = letterTextureY2;
				
				bufferData[idx++] = letterTextureX2;
				bufferData[idx++] = letterTextureY2;
				
				bufferData[idx++] = letterTextureX2;
				bufferData[idx++] = letterTextureY;
				
				bufferData[idx++] = letterTextureX;
				bufferData[idx++] = letterTextureY;

			}
		}
		
		//Log.v("TextTextureBuffer", "Capacity: " + textureFloatBuffer.capacity() + " Index: " + idx);
		
		this.updateHardware();
	}
	*/

	// ===========================================================
	// Inner and Anonymous Classes
	// ===========================================================
}
