package com.PerleDevelopment.AndEngine.tutorial.objects;

import org.andengine.opengl.texture.region.ITiledTextureRegion;
import org.andengine.opengl.vbo.VertexBufferObjectManager;

import com.PerleDevelopment.AndEngine.tutorial.AndEngineTutorialActivity;

public class Player extends GameObject {
  public Player(final float pX, final float pY,
    final ITiledTextureRegion pTiledTextureRegion,
    final VertexBufferObjectManager pVertexBufferObjectManager) {
    super(pX, pY, pTiledTextureRegion, pVertexBufferObjectManager);
  }

  @Override
  public void move() {
    this.mPhysicsHandler.setVelocityX(100);
    OutOfScreenX();
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
