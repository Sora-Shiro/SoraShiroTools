package com.sorashiro.sorashirotools;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.drawable.ShapeDrawable;
import android.graphics.drawable.shapes.Shape;

/**
 * @author Sora
 * @date 2017.5.1
 * <p>
 * Shape util class, can generate some Shape, ShapeDrawable, and ShapeDrawable-array.
 * Shape工具类，能生成Shape, ShapeDrawable, and ShapeDrawable-array。
 */

public class ShapeUtil {

    public static ShapeDrawable getRectangleDrawable(int color) {
        ShapeDrawable d = new ShapeDrawable(getRectangleShape());
        d.getPaint().setColor(color);
        return d;
    }

    public static Shape getRectangleShape() {
        Shape shape = new Shape() {
            float w;
            float h;
            Path path = new Path();

            @Override
            protected void onResize(float width, float height) {
                this.w = width;
                this.h = height;
            }

            @Override
            public void draw(Canvas canvas, Paint paint) {
                canvas.translate(w / 2, h / 2);
                //Right Orientation
                path.reset();
                path.moveTo(-w / 2, -h / 2);
                path.lineTo(w / 2, -h / 2);
                path.lineTo(w / 2, h / 2);
                path.lineTo(-w / 2, h / 2);
                path.close();
                canvas.drawPath(path, paint);
            }
        };
        return shape;
    }

    public static ShapeDrawable[] getRegTris(int color) {
        ShapeDrawable triBlackUp = ShapeUtil.getRegularTriangleDrawable("up", color);
        ShapeDrawable triBlackDown = ShapeUtil.getRegularTriangleDrawable("down", color);
        ShapeDrawable triBlackLeft = ShapeUtil.getRegularTriangleDrawable("left", color);
        ShapeDrawable triBlackRight = ShapeUtil.getRegularTriangleDrawable("right", color);
        ShapeDrawable[] shapeDrawables = new ShapeDrawable[4];
        shapeDrawables[0] = triBlackUp;
        shapeDrawables[1] = triBlackDown;
        shapeDrawables[2] = triBlackLeft;
        shapeDrawables[3] = triBlackRight;
        return shapeDrawables;
    }

    public static ShapeDrawable getRegularTriangleDrawable(String orientation, int color) {
        ShapeDrawable d = new ShapeDrawable(getRegularTriangleShape(orientation));
        d.getPaint().setColor(color);
        return d;
    }

    public static Shape getRegularTriangleShape(final String orientation) {
        Shape shape = new Shape() {
            float w;
            float h;
            Path path = new Path();

            @Override
            protected void onResize(float width, float height) {
                this.w = width;
                this.h = height;
            }

            @Override
            public void draw(Canvas canvas, Paint paint) {
                canvas.translate(w / 2, h / 2);
                switch (orientation) {
                    case "down":
                        canvas.rotate(90);
                        break;
                    case "left":
                        canvas.rotate(180);
                        break;
                    case "up":
                        canvas.rotate(270);
                        break;
                    case "right":
                        break;
                }
                //Right Orientation
                path.reset();
                path.moveTo(-w / 2, -h / 2);
                path.lineTo(w / 2, 0);
                path.lineTo(-w / 2, h / 2);
                path.lineTo(-w / 2, -h / 2);
                path.close();
                canvas.drawPath(path, paint);
            }
        };
        return shape;
    }

}
