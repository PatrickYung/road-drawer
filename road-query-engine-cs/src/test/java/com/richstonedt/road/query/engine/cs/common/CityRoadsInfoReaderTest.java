/*
 * 广州丰石科技公司有限公司拥有本软件版权2017并保留所有权利。
 *  Copyright 2017, Guangzhou Rich Stone Data Technologies Company Limited,
 * All rights reserved.
 *
 */

package com.richstonedt.road.query.engine.cs.common;

import com.richstonedt.road.query.engine.model.excel.ExcelFile;
import com.richstonedt.road.query.engine.model.excel.ExcelSheet;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.util.CollectionUtils;

import java.util.List;

/**
 * <b><code>CityRoadsInfoReaderTest</code></b>
 * <p>
 * class_comment
 * </p>
 * <b>Create Time:</b> 2017/1/24 11:20
 *
 * @author Hetian Zhu
 * @version 0.1.0
 * @since road-query-engine-cs 0.1.0
 */
@RunWith(SpringJUnit4ClassRunner.class)
public class CityRoadsInfoReaderTest {
    /**
     * test getRoadExcelInfo
     *
     * @since road-query-engine-cs 0.1.0
     */
    @Test
    public void getRoadExcelInfo() throws Exception {
        List<ExcelFile> result =  CityRoadsInfoReader.getRoadExcelInfo();
        Assert.assertNotNull(result);
        Assert.assertFalse(CollectionUtils.isEmpty(result));
        Assert.assertEquals(1,result.size());
        ExcelFile file = result.get(0);
        Assert.assertEquals("foshan_nanhai_road.xls",file.getFilePath());
        Assert.assertEquals("0757",file.getCityCode());
        Assert.assertEquals(1,file.getSheets().size());
        ExcelSheet sheet = file.getSheets().get(0);
        Assert.assertEquals("foshan_nanhai_area",sheet.getName());
        Assert.assertEquals(1,sheet.getStartRow());
        Assert.assertEquals(1468,sheet.getEndRow());
        Assert.assertEquals(0,sheet.getStartCol());
        Assert.assertEquals(1,sheet.getEndCol());
    }

}