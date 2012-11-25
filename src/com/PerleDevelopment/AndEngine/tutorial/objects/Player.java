package com.PerleDevelopment.AndEngine.tutorial.objects;

import org.andengine.opengl.texture.region.ITiledTextureRegion;
import org.andengine.opengl.vbo.VertexBufferObjectManager;

import com.PerleDevelopment.AndEngine.tutorial.AndEngineTutorialActivity;
import com.PerleDevelopment.AndEngine.tutorial.helper.AccelerometerHelper;

public class Player extends GameObject {
  final int DEFAULT_VELOCITY = 200;
  boolean jumping = false;

  public Player(
    final float pX, final float pY,
    final ITiledTextureRegion pTiledTextureRegion,
    final VertexBufferObjectManager pVertexBufferObjectManager) {
    super(pX, pY, pTiledTextureRegion, pVertexBufferObjectManager);
  }

  @Override
  public void move() {
    this.mPhysicsHandler.setVelocityX(
      -AccelerometerHelper.TILT * DEFAULT_VELOCITY);
    setRotation(-AccelerometerHelper.TILT * 7);
    OutOfScreenX();

    Jumping();
  }

  private void Jumping() {
    if (jumping) {
      Jump();
    }
    else {
      Fall();
    }
  }

  private void Jump() {
    if (mY <= AndEngineTutorialActivity.CAMERA_HEIGHT / 2) {
      jumping = false;
    }
    else {
      this.mPhysicsHandler.setVelocityY(-DEFAULT_VELOCITY);
    }
  }

  private void Fall() {
    if (mY >= AndEngineTutorialActivity.CAMERA_HEIGHT) {
      jumping = true;
    }
    else {
      this.mPhysicsHandler.setVelocityY(DEFAULT_VELOCITY);
    }
  }

  private void OutOfScreenX() {
    // OutOfScreenX (right)
    if (mX > AndEngineTutorialActivity.CAMERA_WIDTH) {
      mX = 0;
    }
    // OutOfScreenX (left)
    else if (mX < 0) {
      mX = AndEngineTutorialActivity.CAMERA_WIDTH;
    }
  }
}
