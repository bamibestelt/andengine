package org.anddev.andengine.opengl.vertex;

import java.nio.FloatBuffer;

import org.anddev.andengine.opengl.font.Font;
import org.anddev.andengine.opengl.font.Letter;
import org.anddev.andengine.util.HorizontalAlign;

import com.badlogic.gdx.utils.BufferUtils;

/**
 * @author Nicolas Gramlich
 * @since 18:05:08 - 07.04.2010
 */
public class TextVertexBuffer extends VertexBuffer {
	// ===========================================================
	// Constants
	// ===========================================================

	public static final int VERTICES_PER_CHARACTER = 6;

	// ===========================================================
	// Fields
	// ===========================================================

	private final HorizontalAlign mHorizontalAlign;

	// ===========================================================
	// Constructors
	// ===========================================================

	public TextVertexBuffer(final int pCharacterCount, final HorizontalAlign pHorizontalAlign, final int pDrawType) {
		super(2 * VERTICES_PER_CHARACTER * pCharacterCount, pDrawType);

		this.mHorizontalAlign = pHorizontalAlign;
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
		//final FloatBuffer vertexFloatBuffer = this.getFloatBuffer();
		//BufferUtils.copy(this.mBufferData, vertexFloatBuffer, vertexFloatBuffer.capacity(), 0);

		super.setHardwareBufferNeedsUpdate();
	}
	
	/*
	public synchronized void update(final Font font, final int pMaximumLineWidth, final int[] pWidths, final String[] pLines) {
		final float[] bufferData = this.mBufferData;
		int i = 0;

		final int lineHeight = font.getLineHeight();

		final int lineCount = pLines.length;
		for (int lineIndex = 0; lineIndex < lineCount; lineIndex++) {
			final String line = pLines[lineIndex];

			int lineX;
			switch(this.mHorizontalAlign) {
				case RIGHT:
					lineX = pMaximumLineWidth - pWidths[lineIndex];
					break;
				case CENTER:
					lineX = (pMaximumLineWidth - pWidths[lineIndex]) >> 1;
					break;
				case LEFT:
				default:
					lineX = 0;
			}

			final int lineY = lineIndex * (font.getLineHeight() + font.getLineGap());
			final int lineYBits = lineY;

			final int lineLength = line.length();
			for (int letterIndex = 0; letterIndex < lineLength; letterIndex++) {				
				final Letter letter = font.getLetter(line.charAt(letterIndex));

				final float lineY2 = lineY + lineHeight;
				final float lineX2 = lineX + letter.mWidth;
				
				final float lineXBits = lineX;
				final float lineX2Bits = lineX2;
				final float lineY2Bits = lineY2;

				bufferData[i++] = lineXBits;
				bufferData[i++] = lineYBits;

				bufferData[i++] = lineXBits;
				bufferData[i++] = lineY2Bits;

				bufferData[i++] = lineX2Bits;
				bufferData[i++] = lineY2Bits;

				bufferData[i++] = lineX2Bits;
				bufferData[i++] = lineY2Bits;

				bufferData[i++] = lineX2Bits;
				bufferData[i++] = lineYBits;

				bufferData[i++] = lineXBits;
				bufferData[i++] = lineYBits;

				lineX += letter.mAdvance;
			}
		}

		this.updateHardware();
	}
	*/

	// ===========================================================
	// Inner and Anonymous Classes
	// ===========================================================
}
