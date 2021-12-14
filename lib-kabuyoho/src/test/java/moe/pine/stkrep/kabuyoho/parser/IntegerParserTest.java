package moe.pine.stkrep.kabuyoho.parser;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class IntegerParserTest {
    @Test
    void parseTest() {
        assertEquals(1115, IntegerParser.parse("1,115円"));
        assertEquals(8562, IntegerParser.parse("8,562億円"));
        assertEquals(102957, IntegerParser.parse("102,957百万円"));
        assertEquals(0, IntegerParser.parse("--円"));
    }
}
