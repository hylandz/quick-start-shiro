# quick-start-shiro
A demo  which  its help  u systematic learning for Shiro

## 多模块方式
+ shiro-admin
  + 入口
  + 引入shiro-framework
+ shiro-common
  + 公共模块: 如共用的工具类
+ shiro-framework
  + 核心模块: 如shiro,redis,mybatis-plus
  + 引入shiro-system
+ shiro-generator
  + 代码生成模块: 
+ shiro-system
  + 系统模块: 用户,角色,权限管理
  + 引入shiro-common