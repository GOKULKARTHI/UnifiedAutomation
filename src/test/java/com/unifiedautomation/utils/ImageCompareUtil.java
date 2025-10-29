package com.unifiedautomation.utils;

import ru.yandex.qatools.ashot.AShot;
import ru.yandex.qatools.ashot.Screenshot;
import ru.yandex.qatools.ashot.comparison.ImageDiff;
import ru.yandex.qatools.ashot.comparison.ImageDiffer;
import ru.yandex.qatools.ashot.coordinates.WebDriverCoordsProvider;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class ImageCompareUtil {
    public static boolean compareImages(String baselinePath, String actualPath, String diffPath) throws IOException {
        BufferedImage baseline = ImageIO.read(new File(baselinePath));
        BufferedImage actual = ImageIO.read(new File(actualPath));

        ImageDiffer differ = new ImageDiffer();
        ImageDiff diff = differ.makeDiff(baseline, actual);

        if (diff.hasDiff()) {
            ImageIO.write(diff.getMarkedImage(), "PNG", new File(diffPath));
            return false;
        }
        return true;
    }

    public static void saveBaselineIfAbsent(String baselinePath, BufferedImage image) throws IOException {
        if (!Files.exists(Paths.get(baselinePath))) {
            ImageIO.write(image, "PNG", new File(baselinePath));
            System.out.println("Baseline image saved at: " + baselinePath + ". Please approve and commit.");
        }
    }
}
