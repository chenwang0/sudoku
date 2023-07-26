package com.iiikn.core.resovler;


/**
 * Autowire注入接口。
 * 从spring中拷贝过来的一个类，实际在spring中的作于解析自动装配的候选对象。
 * 它主要用于在自动装配过程中，确定哪些对象是合适的候选对象，可以被注入到需要依赖的对象中。
 *
 * @author: cw
 * @since:
 * @version: v0.1
 *
 * 修改记录：
 * 时间      修改人员    修改内容
 * ------------------------------
 */
public interface AutowireCandidateResolver extends AnnotationInjectResolver {

}
