package common;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.suning.sntk.admin.service.common.impl.ExportFileServiceImpl;
import com.suning.sntk.admin.service.common.impl.PageableExportProvider;
import com.suning.sntk.enums.FileType;
import com.suning.sntk.support.common.VgoConstants;
import com.suning.store.commons.pagination.Page;
import com.suning.store.commons.pagination.PageImpl;
import com.suning.store.commons.pagination.Pageable;

/**
 * 功能描述：导出测试
 *
 * @author 88396455_白振华
 * @since 2018-9-19
 */
public class ExportFileServiceImplTest {

    private static final Logger logger = LoggerFactory.getLogger(ExportFileServiceImplTest.class);

    @InjectMocks
    private ExportFileServiceImpl exportFileService;

    @BeforeMethod
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @AfterMethod
    public void tearDown() {
    }

    @Test
    public void testExportFile() {
        try {
            exportFileService.exportFile("12321232", new PageableExportProvider<Object>() {
                @Override
                public long count() {
                    return 10;
                }

                @Override
                public FileType getFileType() {
                    FileType unknown = FileType.UNKNOWN;
                    return null == unknown ? FileType.UNKNOWN : FileType.SHOPPING_GUIDE;
                }

                @Override
                public String[] getColumnHeaders() {
                    return VgoConstants.VGO_EXPORT_HEARDER;
                }

                @Override
                public Object getCellValue(Object data, int row, int column) {
                    switch (column) {
                        case 0:
                            return "hello";
                        case 1:
                            return "hello";
                        case 2:
                            return "hello";
                        case 3:
                            return "hello";
                        case 4:
                            return "hello";
                        case 5:
                            return "hello";
                        case 6:
                            return "hello";
                        case 7:
                            return "hello";
                        case 8:
                            return "hello";
                        case 9:
                            return "hello";
                        default:
                            return StringUtils.EMPTY;
                    }
                }

                @Override
                protected Page<Object> getNextPage(Pageable pageable) {
                    List<Object> list = new ArrayList<Object>();
                    String hello = "hello";
                    list.add(hello);
                    list.add("aadd");
                    Page<Object> result = new PageImpl<Object>(list);
                    return result;
                }
            });
        } catch (Exception e) {
            logger.error("testExportFile,error", e);
        }

    }

}