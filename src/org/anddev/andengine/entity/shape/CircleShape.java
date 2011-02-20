package org.anddev.andengine.entity.shape;

import javax.microedition.khronos.opengles.GL10;

import org.anddev.andengine.engine.camera.Camera;
import org.anddev.andengine.opengl.buffer.BufferObjectManager;
import org.anddev.andengine.opengl.vertex.CircleVertexBuffer;
import org.anddev.andengine.opengl.vertex.VertexBuffer;

import android.util.Log;

public abstract class CircleShape extends Shape {
	private float mCenterX;
	private float mCenterY;
	private float mBaseRadius;
	protected float mRadius;
	private VertexBuffer mVertexBuffer;
	
	public CircleShape(final float pCenterX, final float pCenterY, final float pRadius, final VertexBuffer pVertexBuffer) {
		super(pCenterX, pCenterY);
		
		this.mBaseRadius = pRadius;
		this.mRadius = pRadius;
		
		this.mCenterX = pCenterX;
		this.mCenterY = pCenterY;
		this.mVertexBuffer = pVertexBuffer;
		
		BufferObjectManager.getActiveInstance().loadBufferObject(this.mVertexBuffer);
		
		this.mRotationCenterX = mCenterX;
		this.mRotationCenterY = mCenterY;
		
		this.mScaleCenterX = this.mRotationCenterX;
		this.mScaleCenterY = this.mRotationCenterY;
	}
	
	public void setRadius(final float pRadius)
	{
		this.mRadius = pRadius;
		this.updateVertexBuffer();
	}

	@Override
	public float[] getSceneCenterCoordinates() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public float getWidth() {
		return mRadius * 2.0f;
	}

	@Override
	public float getHeight() {
		return mRadius * 2.0f;
	}

	@Override
	public float getBaseWidth() {
		return mBaseRadius * 2.0f;
	}

	@Override
	public float getBaseHeight() {
		return mBaseRadius * 2.0f;
	}

	@Override
	public boolean collidesWith(IShape pOtherShape) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean contains(float pX, float pY) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public float[] convertSceneToLocalCoordinates(float pX, float pY) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public float[] convertLocalToSceneCoordinates(float pX, float pY) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	protected VertexBuffer getVertexBuffer() {
		return this.mVertexBuffer;
	}

	@Override
	protected boolean isCulled(Camera pCamera) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	protected void drawVertices(GL10 pGL, Camera pCamera) {
		//Log.v("CircleShape", "Drawing arrays");
		pGL.glDrawArrays(GL10.GL_LINE_LOOP, 0, CircleVertexBuffer.VERTICES_PER_CIRCLE);
		//pGL.glDrawArrays(GL10.GL_TRIANGLE_FAN, 0, CircleVertexBuffer.VERTICES_PER_CIRCLE);
	}

}
