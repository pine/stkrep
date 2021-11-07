package moe.pine.stkrep.report.color;


import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class ColorDelegateTest {
    @Test
    void ofTest() {
        final ColorDelegate delegate = ColorDelegate.of("#ff6600");
        assertEquals(1.0f, delegate.getRed(), 0.0f);
        assertEquals(0.4f, delegate.getGreen(), 0.0f);
        assertEquals(0.0f, delegate.getBlue(), 0.0f);
    }
}
