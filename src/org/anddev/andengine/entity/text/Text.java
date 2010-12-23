package org.anddev.andengine.entity.text;

import javax.microedition.khronos.opengles.GL10;
import javax.microedition.khronos.opengles.GL11;

import org.anddev.andengine.engine.camera.Camera;
import org.anddev.andengine.entity.shape.RectangularShape;
import org.anddev.andengine.opengl.buffer.BufferObjectManager;
import org.anddev.andengine.opengl.font.Font;
import org.anddev.andengine.opengl.font.Letter;
import org.anddev.andengine.opengl.texture.buffer.TextTextureBuffer;
import org.anddev.andengine.opengl.util.GLHelper;
import org.anddev.andengine.opengl.vertex.TextVertexBuffer;
import org.anddev.andengine.util.HorizontalAlign;
import org.anddev.andengine.util.StringUtils;

/**
 * @author Nicolas Gramlich
 * @since 10:54:59 - 03.04.2010
 */
public class Text extends RectangularShape {
	// ===========================================================
	// Constants
	// ===========================================================

	// ===========================================================
	// Fields
	// ===========================================================

	private final TextTextureBuffer mTextTextureBuffer;

	private String mText;
	private String[] mLines;
	private int[] mWidths;

	private final Font mFont;

	private int mMaximumLineWidth;

	protected final int mCharactersMaximum;
	protected final int mVertexCount;
	
	private final HorizontalAlign mHorizontalAlign;

	// ===========================================================
	// Constructors
	// ===========================================================

	public Text(final float pX, final float pY, final Font pFont, final String pText) {
		this(pX, pY, pFont, pText, HorizontalAlign.LEFT);
	}

	public Text(final float pX, final float pY, final Font pFont, final String pText, final HorizontalAlign pHorizontalAlign) {
		this(pX, pY, pFont, pText, pHorizontalAlign, pText.length() - StringUtils.countOccurrences(pText, '\n'));
	}

	protected Text(final float pX, final float pY, final Font pFont, final String pText, final HorizontalAlign pHorizontalAlign, final int pCharactersMaximum) {
		super(pX, pY, 0, 0, new TextVertexBuffer(pCharactersMaximum, pHorizontalAlign, GL11.GL_STATIC_DRAW));

		this.mHorizontalAlign = pHorizontalAlign;
		
		this.mCharactersMaximum = pCharactersMaximum;
		this.mVertexCount = TextVertexBuffer.VERTICES_PER_CHARACTER * this.mCharactersMaximum;

		this.mTextTextureBuffer = new TextTextureBuffer(2 * this.mVertexCount, GL11.GL_STATIC_DRAW);
		BufferObjectManager.getActiveInstance().loadBufferObject(this.mTextTextureBuffer);
		this.mFont = pFont;

		this.updateText(pText);

		this.initBlendFunction();
	}
	
	public void unloadBufferObjects()
	{
		BufferObjectManager.getActiveInstance().unloadBufferObject(this.mTextTextureBuffer);
		BufferObjectManager.getActiveInstance().unloadBufferObject(this.getVertexBuffer());
	}

	protected void updateText(final String pText) {
		this.mText = pText;
		final Font font = this.mFont;

		this.mLines = StringUtils.split(this.mText, '\n', this.mLines);
		final String[] lines = this.mLines;

		final int lineCount = lines.length;
		final boolean widthsReusable = this.mWidths != null && this.mWidths.length == lineCount;
		if(widthsReusable == false) {
			this.mWidths = new int[lineCount];
		}
		final int[] widths = this.mWidths;

		int maximumLineWidth = 0;

		for (int i = lineCount - 1; i >= 0; i--) {
			widths[i] = font.getStringWidth(lines[i]);
			maximumLineWidth = Math.max(maximumLineWidth, widths[i]);
		}
		this.mMaximumLineWidth = maximumLineWidth;

		super.mWidth = this.mMaximumLineWidth;
		final float width = super.mWidth;
		super.mBaseWidth = width;

		super.mHeight = lineCount * font.getLineHeight() + (lineCount - 1) * font.getLineGap();
		final float height = super.mHeight;
		super.mBaseHeight = height;

		this.mRotationCenterX = width * 0.5f;
		this.mRotationCenterY = height * 0.5f;

		this.mScaleCenterX = this.mRotationCenterX;
		this.mScaleCenterY = this.mRotationCenterY;

		//this.mTextTextureBuffer.update(font, lines);
		//this.updateVertexBuffer();
		
		this.updateBuffers(false);
	}

	// ===========================================================
	// Getter & Setter
	// ===========================================================

	public int getCharacterCount() {
		return this.mCharactersMaximum;
	}

	@Override
	public TextVertexBuffer getVertexBuffer() {
		return (TextVertexBuffer)super.getVertexBuffer();
	}

	// ===========================================================
	// Methods for/from SuperClass/Interfaces
	// ===========================================================

	@Override
	protected void onInitDraw(final GL10 pGL) {
		super.onInitDraw(pGL);
		GLHelper.enableTextures(pGL);
		GLHelper.enableTexCoordArray(pGL);
	}

	@Override
	protected void drawVertices(final GL10 pGL, final Camera pCamera) {
		pGL.glDrawArrays(GL10.GL_TRIANGLES, 0, this.mVertexCount);
	}

	@Override
	protected void onUpdateVertexBuffer() {
		final Font font = this.mFont;
		if(font != null) {
			this.updateBuffers(true);
			//this.getVertexBuffer().update(font, this.mMaximumLineWidth, this.mWidths, this.mLines);
		}
	}

	@Override
	protected void onApplyTransformations(final GL10 pGL) {
		super.onApplyTransformations(pGL);
		this.applyTexture(pGL);
	}

	// ===========================================================
	// Methods
	// ===========================================================

	private void initBlendFunction() {
		if(this.mFont.getTexture().getTextureOptions().mPreMultipyAlpha) {
			this.setBlendFunction(BLENDFUNCTION_SOURCE_PREMULTIPLYALPHA_DEFAULT, BLENDFUNCTION_DESTINATION_PREMULTIPLYALPHA_DEFAULT);
		}
	}

	private void applyTexture(final GL10 pGL) {
		if(GLHelper.EXTENSIONS_VERTEXBUFFEROBJECTS) {
			final GL11 gl11 = (GL11)pGL;

			this.mTextTextureBuffer.selectOnHardware(gl11);

			GLHelper.bindTexture(pGL, this.mFont.getTexture().getHardwareTextureID());
			GLHelper.texCoordZeroPointer(gl11);
		} else {
			GLHelper.bindTexture(pGL, this.mFont.getTexture().getHardwareTextureID());
			GLHelper.texCoordPointer(pGL, this.mTextTextureBuffer.getFloatBuffer());
		}
	}
	
	
	/**
	 * Update the TextTextureBuffer and TextVertexBuffer Objects
	 */
	private void updateBuffers(final boolean mVertexOnly)
	{
		int vertexIndex = 0;
		int textureIndex = 0;

		final int lineHeight = this.mFont.getLineHeight();
		
		final float vertexBufferData[] = this.getVertexBuffer().getBufferData();
		final float textureBufferData[] = this.mTextTextureBuffer.getBufferData();

		final int lineCount = this.mLines.length;
		for (int lineIndex = 0; lineIndex < lineCount; lineIndex++) {
			final String line = this.mLines[lineIndex];

			int lineX;
			switch(this.mHorizontalAlign) {
				case RIGHT:
					lineX = this.mMaximumLineWidth - this.mWidths[lineIndex];
					break;
				case CENTER:
					lineX = (this.mMaximumLineWidth - this.mWidths[lineIndex]) >> 1;
					break;
				case LEFT:
				default:
					lineX = 0;
			}

			final int lineY = lineIndex * (this.mFont.getLineHeight() + this.mFont.getLineGap());
			final int lineYBits = lineY;

			final int lineLength = line.length();
			for (int letterIndex = 0; letterIndex < lineLength; letterIndex++) {				
				final Letter letter = this.mFont.getLetter(line.charAt(letterIndex));

				final float lineY2 = lineY + lineHeight;
				final float lineX2 = lineX + letter.mWidth;
				
				final float lineXBits = lineX;
				final float lineX2Bits = lineX2;
				final float lineY2Bits = lineY2;
				
				final float letterTextureX = letter.mTextureX;
				final float letterTextureY = letter.mTextureY;
				final float letterTextureX2 = letterTextureX + letter.mTextureWidth;
				final float letterTextureY2 = letterTextureY + letter.mTextureHeight;

				vertexBufferData[vertexIndex++] = lineXBits;
				vertexBufferData[vertexIndex++] = lineYBits;

				vertexBufferData[vertexIndex++] = lineXBits;
				vertexBufferData[vertexIndex++] = lineY2Bits;

				vertexBufferData[vertexIndex++] = lineX2Bits;
				vertexBufferData[vertexIndex++] = lineY2Bits;

				vertexBufferData[vertexIndex++] = lineX2Bits;
				vertexBufferData[vertexIndex++] = lineY2Bits;

				vertexBufferData[vertexIndex++] = lineX2Bits;
				vertexBufferData[vertexIndex++] = lineYBits;

				vertexBufferData[vertexIndex++] = lineXBits;
				vertexBufferData[vertexIndex++] = lineYBits;
				
				
				if(mVertexOnly == false)
				{
					textureBufferData[textureIndex++] = letterTextureX;
					textureBufferData[textureIndex++] = letterTextureY;
					
					textureBufferData[textureIndex++] = letterTextureX;
					textureBufferData[textureIndex++] = letterTextureY2;
					
					textureBufferData[textureIndex++] = letterTextureX2;
					textureBufferData[textureIndex++] = letterTextureY2;
					
					textureBufferData[textureIndex++] = letterTextureX2;
					textureBufferData[textureIndex++] = letterTextureY2;
					
					textureBufferData[textureIndex++] = letterTextureX2;
					textureBufferData[textureIndex++] = letterTextureY;
					
					textureBufferData[textureIndex++] = letterTextureX;
					textureBufferData[textureIndex++] = letterTextureY;
				}

				lineX += letter.mAdvance;
			}
		}
		
		this.getVertexBuffer().updateHardware();
		
		if(mVertexOnly == false)
			this.mTextTextureBuffer.updateHardware();
	}
	

	// ===========================================================
	// Inner and Anonymous Classes
	// ===========================================================
}