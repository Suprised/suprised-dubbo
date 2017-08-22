package com.suprised.dubbo.event;

/**
 * 在调用之前，调用之后，出现异常时，会触发oninvoke, onreturn, onthrow三个事件，可以配置当事件发生时，通知哪个类的哪个方法。 
 */
public interface ServiceEvent {

    /**
     * 调用之前执行 
     */
    public void oninvoke(String param);
    
    /**
     * 返回之后执行 
     */
    public void onreturn(String result, String param);
    
    /**
     * 出现异常时执行 
     */
    public void onthrow(Throwable e, String param);
    
}
