package com.lmml.graph.common.util;

import com.lmml.graph.common.exception.ApplicationException;

import java.io.PrintWriter;
import java.io.Serializable;
import java.io.StringWriter;
import java.util.HashMap;
import java.util.Map;

public class ResponseWrapper<T> implements Serializable {
    private static final long serialVersionUID = -2057997441963343533L;
    private static final String DEF_SUCC_MSG = "操作成功";
    private static final String DEF_FAIL_MSG = "操作失败";
    private boolean succ;
    private String msg;
    private T content;
    private final Map<String, Object> options;

    public static <T> ResponseWrapper<T> success(String msg, T object) {
        return new ResponseWrapper(true, msg, object);
    }

    public static <T> ResponseWrapper<T> success(T object) {
        return new ResponseWrapper(true, "操作成功", object);
    }

    public static <T> ResponseWrapper<T> success(String msg) {
        return new ResponseWrapper(true, msg, (Object)null);
    }

    public static <T> ResponseWrapper<T> success() {
        return success("操作成功");
    }

    public static <T> ResponseWrapper<T> fail(String msg) {
        return new ResponseWrapper(false, msg, (Object)null);
    }

    public static <T> ResponseWrapper<T> fail() {
        return fail("操作失败");
    }

    public static <T> ResponseWrapper<T> fail(String msg, Exception e) {
        return !(e instanceof ApplicationException) && !(e instanceof IllegalArgumentException)?fail(parseException2Str(e)):new ResponseWrapper(false, e.getMessage(), (Object)null);
    }

    public static <T> ResponseWrapper<T> fail(Exception e) {
        return fail("操作失败", e);
    }

    public ResponseWrapper(boolean succ) {
        this();
        this.succ = succ;
    }

    public ResponseWrapper(boolean succ, String msg) {
        this(succ);
        this.msg = msg;
    }

    public ResponseWrapper(boolean succ, String msg, T content) {
        this(succ, msg);
        this.content = content;
    }

    public ResponseWrapper addOption(String key, Object value) {
        this.options.put(key, value);
        return this;
    }

    public <T> T getOptionValue(String key) {
        return (T) this.options.get(key);
    }

    public boolean hasValueInOption(String key) {
        return this.options.containsKey(key);
    }

    private static String parseException2Str(Exception e) {
        StringWriter sw = new StringWriter();
        PrintWriter pw = new PrintWriter(sw);
        e.printStackTrace(pw);
        return sw.toString();
    }

    public boolean isSucc() {
        return this.succ;
    }

    public String getMsg() {
        return this.msg;
    }

    public T getContent() {
        return this.content;
    }

    public Map<String, Object> getOptions() {
        return this.options;
    }

    public void setSucc(boolean succ) {
        this.succ = succ;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public void setContent(T content) {
        this.content = content;
    }

    public boolean equals(Object o) {
        if(o == this) {
            return true;
        } else if(!(o instanceof ResponseWrapper)) {
            return false;
        } else {
            ResponseWrapper other = (ResponseWrapper)o;
            if(!other.canEqual(this)) {
                return false;
            } else if(this.isSucc() != other.isSucc()) {
                return false;
            } else {
                label49: {
                    String this$msg = this.getMsg();
                    String other$msg = other.getMsg();
                    if(this$msg == null) {
                        if(other$msg == null) {
                            break label49;
                        }
                    } else if(this$msg.equals(other$msg)) {
                        break label49;
                    }

                    return false;
                }

                Object this$content = this.getContent();
                Object other$content = other.getContent();
                if(this$content == null) {
                    if(other$content != null) {
                        return false;
                    }
                } else if(!this$content.equals(other$content)) {
                    return false;
                }

                Map this$options = this.getOptions();
                Map other$options = other.getOptions();
                if(this$options == null) {
                    if(other$options != null) {
                        return false;
                    }
                } else if(!this$options.equals(other$options)) {
                    return false;
                }

                return true;
            }
        }
    }

    protected boolean canEqual(Object other) {
        return other instanceof ResponseWrapper;
    }

    public int hashCode() {
        boolean PRIME = true;
        byte result = 1;
        int result1 = result * 59 + (this.isSucc()?79:97);
        String $msg = this.getMsg();
        result1 = result1 * 59 + ($msg == null?43:$msg.hashCode());
        Object $content = this.getContent();
        result1 = result1 * 59 + ($content == null?43:$content.hashCode());
        Map $options = this.getOptions();
        result1 = result1 * 59 + ($options == null?43:$options.hashCode());
        return result1;
    }

    public String toString() {
        return "ResponseWrapper(succ=" + this.isSucc() + ", msg=" + this.getMsg() + ", content=" + this.getContent() + ", options=" + this.getOptions() + ")";
    }

    public ResponseWrapper() {
        this.succ = true;
        this.options = new HashMap();
    }
}
