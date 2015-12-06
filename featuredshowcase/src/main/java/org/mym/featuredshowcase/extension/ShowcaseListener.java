package org.mym.featuredshowcase.extension;

import org.mym.featuredshowcase.FeaturedShowcaseView;

/**
 * This interface provides a mechanism for extra operations on showcase view status changed.
 */
public interface ShowcaseListener {
    /**
     * Called when the showcase view is visible to user.
     */
    void onVisible(FeaturedShowcaseView view);

    /**
     * Called when the showcase is hidden, e.g. user clicked target area or touched anywhere and
     * {@link org.mym.featuredshowcase.ShowConfig#hideOnTouchOutside} flag is set to true.
     */
    void onDismiss(FeaturedShowcaseView view);
}
