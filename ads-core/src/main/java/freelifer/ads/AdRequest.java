package freelifer.ads;

import android.content.Context;

import freelifer.ads.internal.*;

/**
 * @author kzhu on 2018/2/9.
 */
public class AdRequest {

    private final String adUnitId;

    private AdRequest(Builder builder) {
        this.adUnitId = builder.adUnitId;
    }

    public void initialize(Context context) {
        freelifer.ads.internal.Ads.getInstance().initializeAd(this, context);
    }

    public void load(Context context) {
        freelifer.ads.internal.Ads.getInstance().loadAd(this, context);
    }

    public void destroy() {

        freelifer.ads.internal.Ads.getInstance().destroyAd(this);
    }

    public static class Builder {

        private String adUnitId;

        private Builder() {
        }

        public static Builder createBanner() {
            return new Builder();
        }

        public Builder setAdSize() {
            return this;
        }

        public Builder setAdUnitId(String adUnitId) {
            this.adUnitId = adUnitId;
            return this;
        }
    }
}
