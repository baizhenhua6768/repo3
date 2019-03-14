package com.suning.sntk.enums;

import org.testng.annotations.Test;

/**
 * 功能描述：
 *
 * @author 88396455_白振华
 * @since 2018-9-20
 */
public class FileTypeTest {

    @Test
    public void testValueOf() {
        FileType.valueOf(1);
    }

    @Test
    public void testGetValue() {
        FileType.SHOPPING_GUIDE.getValue();
    }

    @Test
    public void testGetDescription() {
        FileType.SHOPPING_GUIDE.getDescription();
    }
}