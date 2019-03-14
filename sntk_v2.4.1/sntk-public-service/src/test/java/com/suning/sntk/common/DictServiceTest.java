/*
 * Copyright (C), 2002-2018, 苏宁易购电子商务有限公司
 * FileName: DictServiceTest
 * Author:   17061157_王薛
 * Date:     2018/7/6 10:17
 * Description: //模块目的、功能描述
 * History: //修改记录
 * <author>      <time>      <version>    <desc>
 * 修改人姓名             修改时间            版本号                  描述
 */

package com.suning.sntk.common;

import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.suning.sntk.BaseTest;
import com.suning.sntk.dao.DictDao;
import com.suning.sntk.entity.DictEntity;
import com.suning.sntk.service.configuration.DictService;
import com.suning.sntk.service.configuration.impl.DictServiceImpl;
import com.suning.sntk.support.enums.DictEnum;
import com.suning.sntk.vo.DictListVo;
import com.suning.sntk.vo.DictVo;
import com.suning.store.commons.pagination.PageRequest;
import com.suning.store.commons.pagination.Pageable;

/**
 * 功能描述：字典服务单元测试（示例）
 *
 * @author 17061157_王薛
 * @since 2018/7/6
 */
public class DictServiceTest extends BaseTest {

    private static final Logger LOGGER = LoggerFactory.getLogger(DictServiceTest.class);

    @Autowired
    private DictServiceImpl dictService;

    @Autowired
    private DictDao dictDao;

    private DictEntity dictEntity;

    private DictEntity dictEntityInsert;

    @BeforeClass
    public void init(){
        dictEntity = new DictEntity();
        dictEntity.setCode("1");
        dictEntity.setDescription("1");
        dictEntity.setType("1");
        dictEntity.setValue("1");
        dictEntity.setId(90L);
        dictDao.insert(dictEntity);
        dictEntityInsert =  new DictEntity();

    }

    @AfterClass
    public void des(){
        DictVo dictByTypeAndCode = dictDao.findDictByTypeAndCode(dictEntityInsert.getType(),
                dictEntityInsert.getCode());
        this.dictDao.deleteById(dictByTypeAndCode.getDictId());
        this.dictDao.deleteById(dictEntity.getId());
    }

    @Test
    public void addDictTest(){
        DictVo dictVo = new DictVo();
        dictVo.setDictType("");
        dictVo.setDictCode("");
        dictVo.setDescription("");
        dictVo.setCreateUser("");
        dictService.addDict(dictVo);
        dictEntityInsert.setCode(dictVo.getDictCode());
        dictEntityInsert.setType(dictVo.getDictType());

    }

    @Test
    public void testQueryPageDictList() {
        Pageable page = new PageRequest(0, 10);
        DictListVo listVo = dictService.queryPageDictList(page);
        System.out.println(listVo.getTotalRecord());
        System.out.println(listVo.getDictList());
    }

    @Test
    public void deleteDictTest(){
        DictEntity entity = dictDao.findById(dictEntity.getId());
        if(null != entity){
            boolean delete = dictDao.delete(entity);
            LOGGER.info("delete,result[{}]" , delete);
        }
        boolean b = this.dictService.deleteDict(dictEntity.getId(), entity.getDeleteUser());

        LOGGER.info("deleteDict,result[{}]" , b);
    }

    @Test
    public void editDictVoTest(){
        DictVo dictVo = new DictVo();
        dictVo.setDictValue("");
        dictVo.setDescription("");
        dictVo.setDictId(dictEntity.getId());
        dictVo.setDictType(dictEntity.getType());
        dictVo.setDictCode(dictEntity.getCode());
        String user = "";
        DictVo dictVo1 = dictDao.findDictByTypeAndCode(dictVo.getDictType(), dictVo.getDictCode());
        if (null != dictVo1){
            DictEntity entity = new DictEntity();
            entity.setId(dictVo.getDictId());
            entity.setType(dictVo.getDictType());
            entity.setValue(dictVo.getDictValue());
            entity.setCode(dictVo.getDictCode());
            entity.setDescription(dictVo.getDescription());
            entity.setUpdateTime(new Date());
            entity.setUpdateUser(user);
            dictDao.updateSkipNull(entity);
        }
//       this.dictService.editDictVo(dictVo,user);
    }

    @Test
    public void findDictByDictEnumTest(){
        DictVo dictVo = this.dictService.findDictByTypeAndCode(DictEnum.CITY_WHILE_LIST.getType(),
                DictEnum.CITY_WHILE_LIST.getCode());
        LOGGER.info("findDictByTypeAndCode,result[{}]", dictVo);
    }

    @Test
    public void findDictByTypeAndCodeTest(){
        DictVo dictVo = dictDao.findDictByTypeAndCode(dictEntity.getType(), dictEntity.getCode());
        LOGGER.info("dictDao.findDictByTypeAndCode,result[{}]", dictVo);
        dictVo = this.dictService.findDictByTypeAndCode(dictEntity.getType(), dictEntity.getCode());
        LOGGER.info("findDictByTypeAndCode,result[{}]", dictVo);
    }
}
