package com.qiluruanjianyuan.crm.settings.domain;

/**
 * 实体类
 * 关于字符串中表现得日期及时间
 * 我们在市场上常用的有两种方式：
 *日期：年月日：yyyy-MM-dd  10为字符串
 *
 * 日期+时间：年月日时分秒：yyyy-MM-dd HH:mm:ss  19为字符串
 * */


/**
 * 关于登录：
 * 验证账号密码:
 * User  user=select * from tbl_user where loginAct=? and loginPwd=?   过dao层，接触到了数据库
 *
 * 如果user对象为null,说明账号密码错误
 * 如果不为null，说明账号密码正确，需要继续向下验证其他字段信息
 *
 * 从user中get到哪些信息？
 * expireTime 验证失效时间（比如离职啊，就登录不进去了）
 * lockState 锁定状态 0表示锁定，1 表示启用(比如账号被锁上了)
 * allowIps 验货浏览器的ip地址是否有效
 *
 *
 *
 * */
public class User {
   private String id ;//编号：主键
   private String loginAct;//登录账号
   private String name ;//用户真实姓名
   private String loginPwd;//登录密码
   private String email;//用户邮箱
   private String expireTime;//失效时间（19位），由日期加时间组成
   private String lockState;//锁定状态，0表示锁定，1 表示启用
   private String deptno;//部门编号
   private String allowIps;//允许访问的ip地址
   private String createTime;//记录的创建时间
   private String createBy;//记录的创建人
   private String editTime;//修改时间（19位）
   private String editBy;//修改人

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getLoginAct() {
        return loginAct;
    }

    public void setLoginAct(String loginAct) {
        this.loginAct = loginAct;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLoginPwd() {
        return loginPwd;
    }

    public void setLoginPwd(String loginPwd) {
        this.loginPwd = loginPwd;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getExpireTime() {
        return expireTime;
    }

    public void setExpireTime(String expireTime) {
        this.expireTime = expireTime;
    }

    public String getLockState() {
        return lockState;
    }

    public void setLockState(String lockState) {
        this.lockState = lockState;
    }

    public String getDeptno() {
        return deptno;
    }

    public void setDeptno(String deptno) {
        this.deptno = deptno;
    }

    public String getAllowIps() {
        return allowIps;
    }

    public void setAllowIps(String allowIps) {
        this.allowIps = allowIps;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    public String getCreateBy() {
        return createBy;
    }

    public void setCreateBy(String createBy) {
        this.createBy = createBy;
    }

    public String getEditTime() {
        return editTime;
    }

    public void setEditTime(String editTime) {
        this.editTime = editTime;
    }

    public String getEditBy() {
        return editBy;
    }

    public void setEditBy(String editBy) {
        this.editBy = editBy;
    }

    public User() {
    }

    @Override
    public String toString() {
        return "User{" +
                "id='" + id + '\'' +
                ", loginAct='" + loginAct + '\'' +
                ", name='" + name + '\'' +
                ", loginPwd='" + loginPwd + '\'' +
                ", email='" + email + '\'' +
                ", expireTime='" + expireTime + '\'' +
                ", lockState='" + lockState + '\'' +
                ", deptno='" + deptno + '\'' +
                ", allowIps='" + allowIps + '\'' +
                ", createTime='" + createTime + '\'' +
                ", createBy='" + createBy + '\'' +
                ", editTime='" + editTime + '\'' +
                ", editBy='" + editBy + '\'' +
                '}';
    }
}
