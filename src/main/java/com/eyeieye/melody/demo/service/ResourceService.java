package com.eyeieye.melody.demo.service;


import com.eyeieye.melody.demo.domain.Resource;
import com.eyeieye.melody.demo.enums.ResourceType;

import java.util.List;

/**
 * 资源Service接口
 * 
 * @author zhengdd
 * @version $Id: ResourceService.java,v 1.1 2011/06/20 07:43:14 fish Exp $
 */
public interface ResourceService {

    /**
     * 根据资源类型获取资源列表
     * 
     * @param province
     * @return List<Resource>
     */
    public List<Resource> getResourcesByType(ResourceType province);

}
