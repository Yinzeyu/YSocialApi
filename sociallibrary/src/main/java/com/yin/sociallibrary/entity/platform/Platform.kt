package com.yin.sociallibrary.entity.platform

import com.yin.sociallibrary.config.OperationType

/**
 * description: 平台信息
 *@date 2019/7/15
 *@author: yzy.
 */
data class Platform (var platConfig: PlatformConfig,                   // 平台配置
                     var availableOperationType: List<OperationType>)  // 平台操作