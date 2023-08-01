package com.opensource.module;

/**
 * @Title: "限流"
 * @Description: "设计思路"
 * 1.限流场景
 *     a)根据请求数量限流
 *     b)根据线程数限流
 *     c)按照线程池设置限流
 *
 * 2.实现思路
 *   a)  根据计数器实现 可以使用aotoInteger配合锁机制
 *       时间窗口限流是比较常用的限流方式（分桶、令牌）
 *   b) 限流方式需要结合具体的中间件，比如dubbo等
 *   c) 这种方式比较少见，可能存在cpu占用过高的风险
 *
 * 3.其他思考
 *    设置限流需要有控制台，可以根据界面调整资源分派，可能需要定义资源。
 *
 *
 * @Author: ZhaoWei
 * @Date: 2023/7/31 15:03
 * @Version V1.0
 */
public interface Limiter {

    /**
     * 基础功能
     * @return
     */
    boolean allow();

}
