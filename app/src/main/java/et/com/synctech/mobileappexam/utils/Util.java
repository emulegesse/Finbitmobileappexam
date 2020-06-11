package et.com.synctech.mobileappexam.utils;

import android.graphics.Color;
import android.util.Log;
import android.widget.ImageView;

import com.makeramen.roundedimageview.RoundedTransformationBuilder;
import com.squareup.picasso.Picasso;
import com.squareup.picasso.Transformation;

import et.com.synctech.mobileappexam.R;

public final class Util {

    /**
     * Used as the global TAG throughout the application to log and filter out
     * the output only with the provided TAG.
     */
    private static final String TAG = "TAG-LIFTEXAM-DEBUG";

    public static void loadRoundedImagePath(String url, ImageView target) {

        if(!url.equalsIgnoreCase("")){

            Transformation transformation = new RoundedTransformationBuilder()
                    .borderColor(Color.BLACK)
                    .borderWidthDp(0)
                    .cornerRadiusDp(50)
                    .oval(false)
                    .build();

            Picasso.get()
                    .load(url)
                    .fit()
                    .centerCrop()
                    .placeholder(R.drawable.ic_person)
                    .transform(transformation)
                    .into(target);
        }

    }

}