package com.suning.sntk.util;

import java.util.ArrayList;
import java.util.List;

import org.testng.annotations.Test;

import com.suning.sntk.dto.vgo.VcategoryRelInfoDto;

/**
 * 功能描述：
 *
 * @author 88396455_白振华
 * @since 2018-9-20
 */
public class VgoAdminUtilsTest {

    @Test
    public void testCutOffPicUrl() {
        List<String> serverItems = new ArrayList<String>();
        serverItems.add("adsd,dsfsf");
        VgoAdminUtils.cutOffPicUrl(serverItems);
    }

    @Test
    public void testObtainCategoryNames() {
        List<String> serverItems = new ArrayList<String>();
        serverItems.add("adsd,dsfsf");
        VgoAdminUtils.obtainServerItemNames(serverItems);
        List<String> serverItems1 = new ArrayList<String>();
        serverItems1.add("adsd");
        VgoAdminUtils.obtainServerItemNames(serverItems1);
    }

    @Test
    public void testObtainServerItemNames() {
        List<VcategoryRelInfoDto> vcategoryRelInfoDtos = new ArrayList<VcategoryRelInfoDto>();
        VcategoryRelInfoDto vcategoryRelInfoDto = new VcategoryRelInfoDto();
        vcategoryRelInfoDto.setCategoryCode("2323");
        vcategoryRelInfoDto.setCategoryName("dfafs");
        vcategoryRelInfoDtos.add(vcategoryRelInfoDto);
        VgoAdminUtils.obtainCategoryNames(vcategoryRelInfoDtos);
    }
}