package com.PerleDevelopment.AndEngine.tutorial.objects;

import org.andengine.opengl.texture.region.ITiledTextureRegion;
import org.andengine.opengl.vbo.VertexBufferObjectManager;

public class Platform extends GameObject {
  public Platform(float pX, float pY, ITiledTextureRegion pTiledTextureRegion,
    VertexBufferObjectManager pVertexBufferObjectManager) {
    super(pX, pY, pTiledTextureRegion, pVertexBufferObjectManager);
  }

  @Override
  public void move() {
  }
}
