package OutSourced;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.view.View;

public class RadarBackgroudOverlay extends View{

	public RadarBackgroudOverlay(Context context) {
		super(context);
		// TODO Auto-generated constructor stub
	}
	@Override 
    protected void onDraw(Canvas canvas) { 
            // TODO Auto-generated method stub 
			float midx = 160;
			float midy = 240;
            Paint paint = new Paint(); 
            paint.setStyle(Paint.Style.STROKE); 
            paint.setColor(Color.GRAY);
            canvas.drawCircle(midx, midy, 50, paint);
            canvas.drawCircle(midx, midy, 100, paint);
            canvas.drawCircle(midx, midy, 150, paint);
            canvas.drawCircle(midx, midy, 200, paint);
            super.onDraw(canvas); 
    } 
}
