/*
Copyright 2006 Jerry Huxtable

Licensed under the Apache License, Version 2.0 (the "License");
you may not use this file except in compliance with the License.
You may obtain a copy of the License at

   http://www.apache.org/licenses/LICENSE-2.0

Unless required by applicable law or agreed to in writing, software
distributed under the License is distributed on an "AS IS" BASIS,
WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
See the License for the specific language governing permissions and
limitations under the License.
 */

package com.jhlabs.image;

import java.awt.image.BufferedImage;

public class ErodeAlphaFilter extends PointFilter {

  private float lowerThreshold;
  protected float radius = 5;
  private float softness = 0;
  private float threshold;
  private float upperThreshold;

  public ErodeAlphaFilter() {
    this(3, 0.75f, 0);
  }

  public ErodeAlphaFilter(float radius, float threshold, float softness) {
    this.radius = radius;
    this.threshold = threshold;
    this.softness = softness;
  }

  @Override
  public BufferedImage filter(BufferedImage src, BufferedImage dst) {
    dst = new GaussianFilter((int) radius).filter(src, null);
    lowerThreshold = 255 * (threshold - softness * 0.5f);
    upperThreshold = 255 * (threshold + softness * 0.5f);
    return super.filter(dst, dst);
  }

  @Override
  public int filterRGB(int x, int y, int rgb) {
    int a = rgb >> 24 & 0xff;
    if (a == 255)
      return 0xffffffff;
    final float f = ImageMath.smoothStep(lowerThreshold, upperThreshold, a);
    a = (int) (f * 255);
    if (a < 0)
      a = 0;
    else if (a > 255)
      a = 255;
    return a << 24 | 0xffffff;
  }

  public float getRadius() {
    return radius;
  }

  public float getSoftness() {
    return softness;
  }

  public float getThreshold() {
    return threshold;
  }

  public void setRadius(float radius) {
    this.radius = radius;
  }

  public void setSoftness(float softness) {
    this.softness = softness;
  }

  public void setThreshold(float threshold) {
    this.threshold = threshold;
  }

  @Override
  public String toString() {
    return "Alpha/Erode...";
  }
}
