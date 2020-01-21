/**
 *
 * Copyright (c) 2012, Preecha A. All rights reserved.
 *
 * This is proprietary source code of Preecha Artkaew
 * e-mail: Preecha At live.com
 */

package kbtg.kbond.passbookprint.model.print;

import java.awt.image.BufferedImage;
import java.awt.print.PageFormat;

import javax.print.attribute.standard.MediaSize;
import javax.print.attribute.standard.OrientationRequested;

/**
 * Represents paper size in inches or millimeters with built in parsers. Default
 * is 8.5 x 11.0 inches (US)
 */
public class PaperFormat {
    private static final int REVERSE_PORTRAIT = 9238;

    private float width = 8.5f;
    private float height = 11.0f;

    private Float autoWidth;
    private Float autoHeight;

    private boolean autoSize;

    private int orientation = PageFormat.PORTRAIT;

    private int units = MediaSize.INCH;

    public PaperFormat() {
    }

    /*
     * public PaperSize(String width, String height) {
     * setSize(width, height);
     * }
     */
    public PaperFormat(float width, float height) {
        this.width = width;
        this.height = height;
    }

    /*
     * public PaperSize(String width, String height, String units) {
     * setSize(width, height, units);
     * }
     */
    public PaperFormat(float width, float height, int units) {
        this.units = units;
        this.width = width;
        this.height = height;
    }

	/*
     * public Float[] getSize() {
	 * return size;
	 * }
	 */

    public static int parseOrientation(String orientation) {
        if (orientation.equalsIgnoreCase("landscape")) {
            return PageFormat.LANDSCAPE;
        } else if (orientation.equalsIgnoreCase("portrait")) {
            return PageFormat.PORTRAIT;
        } else if (orientation.equalsIgnoreCase("reverse-landscape") || orientation.equalsIgnoreCase("reverse_landscape")
                || orientation.equalsIgnoreCase("reverse landscape")) {
            return PageFormat.REVERSE_LANDSCAPE;
        } else if (orientation.equalsIgnoreCase("reverse-portrait") || orientation.equalsIgnoreCase("reverse_portrait")
                || orientation.equalsIgnoreCase("reverse portrait")) {
            return PaperFormat.REVERSE_PORTRAIT;
        } else {
            System.out.println("Orientation specified \"" + orientation + "\" not recognized.");
            return -1;
        }
    }

    public static String getOrientationDescription(int orientation) {
        switch (orientation) {
            case PaperFormat.REVERSE_PORTRAIT:
                return "reverse-portrait";
            case PageFormat.LANDSCAPE:
                return "landscape";
            case PageFormat.REVERSE_LANDSCAPE:
                return "reverse-landscape";
            case PageFormat.PORTRAIT:
                return "portrait"; // move down
            default:
                return "unknown";
        }
    }

    /**
     * Automatically calculates the best <code>PaperSize</code> based on the supplied
     * image dimensions
     *
     * @param imageWidth
     * @param imageHeight
     * @param p
     * @return
     */
    public static void setAutoSize(int imageWidth, int imageHeight, PaperFormat p) {
        // swap image dimensions
        if (p.isLandscape()) {
            int temp = imageWidth;
            imageWidth = imageHeight;
            imageHeight = temp;
        }

        float imageRatio = (float) imageWidth / (float) imageHeight;
        float paperRatio = p.getWidth() / p.getHeight();
        float wRatio = imageWidth / p.getWidth();
        float hRatio = imageHeight / p.getHeight();

        if (imageRatio >= paperRatio) {
            // use width to recalculate height
            p.setAutoWidth(p.getWidth());
            p.setAutoHeight(imageHeight / wRatio);
        } else {
            // use height to recalculate width
            p.setAutoHeight(p.getHeight());
            p.setAutoWidth(imageWidth / hRatio);
        }
        p.setAutoSize(true);
    }

    /**
     * Parses paper size (such as 8.5in x 11.0 in) and sets it
     *
     * @param width
     * @param height
     */
    public static PaperFormat parseSize(String width, String height) throws NumberFormatException {
        if (width.toLowerCase().endsWith("in") && height.toLowerCase().endsWith("in")) {
            return parseSize(width.split("in")[0], height.split("in")[0], "in");
        } else if (width.toLowerCase().endsWith("mm") && height.toLowerCase().endsWith("mm")) {
            return parseSize(width.split("mm")[0], height.split("mm")[0], "mm");
        } else {
            return parseSize(width, height, null);
        }
    }

    /**
     * Sets paper size and units, example: 8.5, 11.0, mm.
     *
     * @param width
     * @param height
     * @param unit
     */
    public static PaperFormat parseSize(String width, String height, String units) throws NumberFormatException {
        if (width == null || height == null) {
            throw new NumberFormatException("Cannot parse float from null value");
        }
        Float w = Float.parseFloat(width.trim());
        Float h = Float.parseFloat(height.trim());

        int unitz = parseUnits(units);
        if (unitz != -1) {
            return new PaperFormat(w, h, unitz);
        } else {
            return new PaperFormat(w, h);
        }

    }

    public static int parseUnits(String units) {
        if (units == null) {
            return -1;
        } else if (units.equalsIgnoreCase("in") || units.equalsIgnoreCase("inch") || units.equalsIgnoreCase("standard")) {
            return MediaSize.INCH;
        } else if (units.equalsIgnoreCase("mm") || units.equalsIgnoreCase("metric")) {
            return MediaSize.MM;
        } else {
            return -1;
        }
    }

    public void setAutoSize(BufferedImage b) {
        if (b != null) {
            setAutoSize(b.getWidth(), b.getHeight(), this);
            autoSize = true;
        } else {
            System.out.println("Image specified is empty.");
        }
    }

    public String getOrientationDescription() {
        return getOrientationDescription(orientation);
    }

    public void setOrientation(String s) {
        orientation = parseOrientation(s);
    }

    public int getOrientation() {
        if (orientation == PaperFormat.REVERSE_PORTRAIT) {
            return PageFormat.PORTRAIT;
        }
        return orientation;
    }

    public void setOrientation(int orientation) {
        this.orientation = orientation;
    }

    public OrientationRequested getOrientationRequested() {
        switch (orientation) {
            case PageFormat.LANDSCAPE:
                return OrientationRequested.LANDSCAPE;
            case PageFormat.REVERSE_LANDSCAPE:
                return OrientationRequested.REVERSE_LANDSCAPE;
            case PaperFormat.REVERSE_PORTRAIT:
                return OrientationRequested.REVERSE_PORTRAIT;
            case PageFormat.PORTRAIT: // move down
            default:
                return OrientationRequested.PORTRAIT;
        }
    }

    public boolean isPortrait() {
        return getOrientation() == PaperFormat.REVERSE_PORTRAIT || getOrientation() == PageFormat.PORTRAIT;
    }

    public boolean isLandscape() {
        return getOrientation() == PageFormat.LANDSCAPE || getOrientation() == PageFormat.REVERSE_LANDSCAPE;
    }

    public float getWidth() {
        return width;
    }

	/*
	 * public final void setAutoSize(float width, float height) {
	 * this.autoWidth = width;
	 * this.autoHeight = height;
	 * }
	 */

    public void setWidth(float width) {
        this.width = width;
    }

    public float getHeight() {
        return height;
    }

    public void setHeight(float height) {
        this.height = height;
    }

    @Override
    public String toString() {
        return "Size: " + width + getUnitDescription() + " x " + height + getUnitDescription() + ", AutoSize: " + getAutoWidth()
                + getUnitDescription() + " x " + getAutoHeight() + getUnitDescription() + " (orientation is \"" + getOrientationDescription()
                + "\", auto size is " + (autoSize ? "enabled)" : "disabled)");
    }

    public boolean isAutoSize() {
        return autoSize;
    }

    public void setAutoSize(boolean autoSize) {
        this.autoSize = autoSize;
    }

    public final float getAutoWidth() {
        return autoWidth == null || !autoSize ? width : autoWidth;
    }

	/*
	 * public final void setSize(float width, float height) {
	 * size = new Float[]{width, height};
	 * }
	 */

    public final void setAutoWidth(float width) {
        autoWidth = width;
    }

    public final float getAutoHeight() {
        return autoHeight == null || !autoSize ? height : autoHeight;
    }

    public final void setAutoHeight(float height) {
        autoHeight = height;
    }

    public String getUnitDescription() {
        switch (units) {
            case MediaSize.INCH:
                return "in";
            case MediaSize.MM:
                return "mm";
        }
        return null;
    }

    public int getUnits() {
        return units;
    }

    public final void setUnits(int units) {
        this.units = units;
    }
}
