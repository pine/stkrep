package moe.pine.stkrep.report.color;


import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class ColorDelegateTest {
    @Test
    void decodeTest() {
        final ColorDelegate delegate = ColorDelegate.decode("#ff6600");
        assertEquals(1.0f, delegate.getRed(), 0.0f);
        assertEquals(0.4f, delegate.getGreen(), 0.0f);
        assertEquals(0.0f, delegate.getBlue(), 0.0f);
        assertEquals(1.0f, delegate.getAlpha(), 0.0f);
    }
}
