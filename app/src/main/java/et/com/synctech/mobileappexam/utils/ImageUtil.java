package et.com.synctech.mobileappexam.utils;

import android.widget.ImageView;

import com.makeramen.roundedimageview.RoundedTransformationBuilder;
import com.squareup.picasso.Picasso;

import et.com.synctech.mobileappexam.R;

public final class ImageUtil {

    public static void loadRoundedImagePath(String url, ImageView target, Long radius) {

        if (url.equals("")) {
            url = "empty";
        }

        Picasso.get()
                .load(url)
                .error(R.drawable.ic_person)
                .fit()
                .centerCrop()
                .placeholder(R.drawable.ic_person)
                .transform(new RoundedTransformationBuilder()
                        .cornerRadiusDp(radius)
                        .oval(false)
                        .build())
                .into(target);
    }

}