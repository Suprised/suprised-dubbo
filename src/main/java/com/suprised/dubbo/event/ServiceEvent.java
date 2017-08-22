package com.suprised.dubbo.event;

/**
 * �ڵ���֮ǰ������֮�󣬳����쳣ʱ���ᴥ��oninvoke, onreturn, onthrow�����¼����������õ��¼�����ʱ��֪ͨ�ĸ�����ĸ������� 
 */
public interface ServiceEvent {

    /**
     * ����֮ǰִ�� 
     */
    public void oninvoke(String param);
    
    /**
     * ����֮��ִ�� 
     */
    public void onreturn(String result, String param);
    
    /**
     * �����쳣ʱִ�� 
     */
    public void onthrow(Throwable e, String param);
    
}
