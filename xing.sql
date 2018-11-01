/*
Navicat MySQL Data Transfer

Source Server         : root
Source Server Version : 50018
Source Host           : localhost:3306
Source Database       : xing

Target Server Type    : MYSQL
Target Server Version : 50018
File Encoding         : 65001

Date: 2018-09-03 14:25:43
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for td_course
-- ----------------------------
DROP TABLE IF EXISTS `td_course`;
CREATE TABLE `td_course` (
  `id` int(5) NOT NULL auto_increment,
  `coursename` varchar(255) default NULL,
  `coursedesc` varchar(255) default NULL,
  `courseimg` varchar(255) default NULL,
  `secondid` int(5) default NULL,
  `tid` int(11) default NULL,
  `starttime` datetime default NULL,
  `couretime` varchar(255) default NULL,
  `studynum` int(11) default NULL,
  `level` varchar(255) default NULL,
  `coureAuthority` varchar(255) default NULL,
  PRIMARY KEY  (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of td_course
-- ----------------------------
INSERT INTO `td_course` VALUES ('20', '微软MOS认证教学及Office实用技巧', '微软MOS认证教学及Office实用技巧\', \'Microsoft Office Specialist（MOS）中文称之为微软办公软件国际认证”，是微软为全球所认可的Office软件国际性专业认证，全球有168个国家地区认可，至2012年4月初全球已经有将近1200万人次参加考试，可使用英文、日文、德文、法文、阿拉伯文、拉丁文、韩文、泰文、意大利文、芬兰文等等二十四种语言进行考试。', 'img/c11.jpg', '14', '21', '2018-08-29 00:00:00', '4小时/周  16周', null, '基础', '1');
INSERT INTO `td_course` VALUES ('21', 'Java Web技术及其应用', '本课程采用一个真实项目贯穿各章节内容，深入介绍了Java Web技术及其应用，内容涵盖Java Web入门、Servlet基础、Servlet核心接口、会话跟踪、JSP语法、JSP内置对象、JSP与JavaBean、表达式语言、标准标签库、Filter与Listener、Web架构MVC、Ajax技术。本书内容以Servlet 3.0规范为主线，同时穿插Servlet 2.5规范的对比介绍', 'img/c2.jpg', '7', '14', '2018-08-29 00:00:00', '4小时/周  16周', null, '基础', '1');
INSERT INTO `td_course` VALUES ('22', '基础应用和应用开发实践', '基础应用开发实践', 'img/c3.jpg', '7', '14', '2018-08-29 00:00:00', '4小时/周  16周', null, '基础', '1');
INSERT INTO `td_course` VALUES ('23', 'C语言课程实训', '本课程通过项目贯穿案例及项目实战的方式，将C语言课程中所学习的知识点运用到实际项目开发中，使学生掌握所学知识，并能灵活运用所学知识解决实际问题。', 'img/c4.jpg', '5', '16', '2018-08-29 00:00:00', '4小时/周  16周', null, '基础', '1');
INSERT INTO `td_course` VALUES ('24', 'c++', 'c++ 基础和设计', 'img/c12.jpg', '5', '14', '2018-08-31 00:00:00', '4小时/周  12周', null, '基础', '1');
INSERT INTO `td_course` VALUES ('25', 'unity 3D开发', 'Unity3D基础入门\', \'从零开始学习Unity3d游戏引擎和c#开发语言，在本次课程中了解并认识Unity开发中的刚体，Prefab，地形系统，角色控制，碰撞，触发器，粒子系统，GUI，游戏菜单制作，游戏性能优化等这些unity组件和知识点，并为学员下一步深入学习Unity3d课程打下坚实的基础。', 'img/c7.jpg', '13', '19', '2018-08-31 00:00:00', '4小时/周  16周', null, '基础', '1');
INSERT INTO `td_course` VALUES ('26', 'C语言数据结构和算法分析', '本课程主要讲了数据结构与算法分析面试指导要点，是IT从业人员面试必看课程之一', 'img/IV007.jpg', '5', '16', '2018-09-01 00:00:00', '4小时/周  16周', null, '基础', '1');

-- ----------------------------
-- Table structure for td_course_grade1
-- ----------------------------
DROP TABLE IF EXISTS `td_course_grade1`;
CREATE TABLE `td_course_grade1` (
  `id` int(5) NOT NULL,
  `name` varchar(255) default NULL,
  PRIMARY KEY  (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of td_course_grade1
-- ----------------------------
INSERT INTO `td_course_grade1` VALUES ('1', '前端开发');
INSERT INTO `td_course_grade1` VALUES ('2', '后端开发');
INSERT INTO `td_course_grade1` VALUES ('3', '移动开发');
INSERT INTO `td_course_grade1` VALUES ('4', '大数据开发');
INSERT INTO `td_course_grade1` VALUES ('5', '游戏开发');
INSERT INTO `td_course_grade1` VALUES ('6', '其他');

-- ----------------------------
-- Table structure for td_course_grade2
-- ----------------------------
DROP TABLE IF EXISTS `td_course_grade2`;
CREATE TABLE `td_course_grade2` (
  `id` int(5) NOT NULL,
  `name` varchar(255) default NULL,
  `firstid` int(5) NOT NULL,
  PRIMARY KEY  (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of td_course_grade2
-- ----------------------------
INSERT INTO `td_course_grade2` VALUES ('1', 'Node.js', '1');
INSERT INTO `td_course_grade2` VALUES ('2', 'html/css', '1');
INSERT INTO `td_course_grade2` VALUES ('3', 'jquery', '1');
INSERT INTO `td_course_grade2` VALUES ('4', 'js', '1');
INSERT INTO `td_course_grade2` VALUES ('5', 'c++/c', '2');
INSERT INTO `td_course_grade2` VALUES ('6', 'c#', '2');
INSERT INTO `td_course_grade2` VALUES ('7', 'java', '2');
INSERT INTO `td_course_grade2` VALUES ('8', 'IOS', '3');
INSERT INTO `td_course_grade2` VALUES ('9', 'Android', '3');
INSERT INTO `td_course_grade2` VALUES ('10', 'Hadoop', '4');
INSERT INTO `td_course_grade2` VALUES ('11', 'sprak', '4');
INSERT INTO `td_course_grade2` VALUES ('12', 'python', '4');
INSERT INTO `td_course_grade2` VALUES ('13', 'Unity', '5');
INSERT INTO `td_course_grade2` VALUES ('14', '其他', '6');

-- ----------------------------
-- Table structure for td_role
-- ----------------------------
DROP TABLE IF EXISTS `td_role`;
CREATE TABLE `td_role` (
  `rid` int(11) NOT NULL auto_increment,
  `rname` varchar(255) default NULL,
  PRIMARY KEY  (`rid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of td_role
-- ----------------------------
INSERT INTO `td_role` VALUES ('0', '普通用户');
INSERT INTO `td_role` VALUES ('1', '教师');
INSERT INTO `td_role` VALUES ('2', '管理员');

-- ----------------------------
-- Table structure for td_sectionvedio
-- ----------------------------
DROP TABLE IF EXISTS `td_sectionvedio`;
CREATE TABLE `td_sectionvedio` (
  `cid` int(11) NOT NULL,
  `zid` int(11) NOT NULL,
  `sid` int(11) NOT NULL,
  `sectionContent` varchar(255) default NULL,
  `videoaddress` varchar(255) default NULL,
  PRIMARY KEY  (`cid`,`zid`,`sid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of td_sectionvedio
-- ----------------------------
INSERT INTO `td_sectionvedio` VALUES ('21', '1', '1', '第一节Web 应用概述', '88beea65eb8b4423968b7264cfad328');
INSERT INTO `td_sectionvedio` VALUES ('21', '1', '2', '第二节 javaWeb应用开发', '88beea65eb8b4423968b7264cfad328');
INSERT INTO `td_sectionvedio` VALUES ('21', '2', '1', '第一节 Servlet概述', '88beea65eb8b4423968b7264cfad328');
INSERT INTO `td_sectionvedio` VALUES ('21', '2', '2', '第二节 Servlet基础', '88beea65eb8b4423968b7264cfad328');
INSERT INTO `td_sectionvedio` VALUES ('21', '2', '3', '第三节 Servlet 应用', '88beea65eb8b4423968b7264cfad328');
INSERT INTO `td_sectionvedio` VALUES ('21', '3', '1', '第一节 Servlet核心接口', '88beea65eb8b4423968b7264cfad328');
INSERT INTO `td_sectionvedio` VALUES ('21', '3', '2', '第二节 ServletConfig接口', '88beea65eb8b4423968b7264cfad328');
INSERT INTO `td_sectionvedio` VALUES ('21', '3', '3', '第三节 ServletContext接口', '88beea65eb8b4423968b7264cfad328');
INSERT INTO `td_sectionvedio` VALUES ('21', '3', '4', '第四节 HttpServletRequest接口', '88beea65eb8b4423968b7264cfad328');
INSERT INTO `td_sectionvedio` VALUES ('21', '3', '5', '第五节  HttpServletResponse接口', '88beea65eb8b4423968b7264cfad3281');
INSERT INTO `td_sectionvedio` VALUES ('21', '4', '1', '第一节 无状态的Http协议', '88beea65eb8b4423968b7264cfad3281');
INSERT INTO `td_sectionvedio` VALUES ('21', '4', '2', '第二节 会话跟踪技术', '88beea65eb8b4423968b7264cfad3281');
INSERT INTO `td_sectionvedio` VALUES ('21', '5', '1', '第一节 JSP概述', '88beea65eb8b4423968b7264cfad3281');
INSERT INTO `td_sectionvedio` VALUES ('21', '6', '1', '第一节 JSP内置对象简介', '88beea65eb8b4423968b7264cfad3281');
INSERT INTO `td_sectionvedio` VALUES ('22', '1', '1', '第一节 java 入门', '88beea65eb8b4423968b7264cfad3281');
INSERT INTO `td_sectionvedio` VALUES ('22', '1', '2', '第二节 java 编程', '88beea65eb8b4423968b7264cfad3281');
INSERT INTO `td_sectionvedio` VALUES ('24', '1', '1', '第一节  C++概述', '88beea65eb8b4423968b7264cfad3281');
INSERT INTO `td_sectionvedio` VALUES ('24', '2', '1', '第一节 c++ 语法概述', '88beea65eb8b4423968b7264cfad3281');
INSERT INTO `td_sectionvedio` VALUES ('24', '3', '1', '第一节类和对象', '88beea65eb8b4423968b7264cfad3281');
INSERT INTO `td_sectionvedio` VALUES ('24', '3', '2', '第二节 析构函数和构造函数', '88beea65eb8b4423968b7264cfad3281');
INSERT INTO `td_sectionvedio` VALUES ('25', '1', '1', '第一节 Unity概述以及安装', '88beea65eb8b4423968b7264cfad3281');
INSERT INTO `td_sectionvedio` VALUES ('25', '1', '2', '第二节 Unity工程文件创建及打开', '88beea65eb8b4423968b7264cfad3281');
INSERT INTO `td_sectionvedio` VALUES ('25', '2', '1', '第一节  Unity地形创建', '88beea65eb8b4423968b7264cfad3281');
INSERT INTO `td_sectionvedio` VALUES ('25', '2', '2', '第二节 加入填空和水', '88beea65eb8b4423968b7264cfad3281');
INSERT INTO `td_sectionvedio` VALUES ('26', '1', '1', '第一节 数据结构和算法绪论', '88beea65eb8b4423968b7264cfad3281');
INSERT INTO `td_sectionvedio` VALUES ('26', '1', '2', '谈谈算法', '88beea65eb8b4423968b7264cfad3281');
INSERT INTO `td_sectionvedio` VALUES ('26', '1', '3', '时间复杂度和空间复杂度', '88beea65eb8b4423968b7264cfad3281');
INSERT INTO `td_sectionvedio` VALUES ('26', '2', '1', '第一节  链表', '6e1f17473cb445eeb33fc8d46ed99d31');
INSERT INTO `td_sectionvedio` VALUES ('26', '2', '2', '第二节  双向链表', '6e1f17473cb445eeb33fc8d46ed99d31');
INSERT INTO `td_sectionvedio` VALUES ('26', '2', '3', '第三节 队列', '6e1f17473cb445eeb33fc8d46ed99d31');
INSERT INTO `td_sectionvedio` VALUES ('26', '2', '4', '第四节  栈', '6e1f17473cb445eeb33fc8d46ed99d31');

-- ----------------------------
-- Table structure for td_teacher
-- ----------------------------
DROP TABLE IF EXISTS `td_teacher`;
CREATE TABLE `td_teacher` (
  `id` int(11) NOT NULL,
  `teacherDesc` varchar(255) default NULL,
  `tname` varchar(255) default NULL,
  `teacherimg` varchar(255) default NULL,
  `sex` varchar(255) default NULL,
  `educationLevel` varchar(255) default NULL,
  `birthday` varchar(255) default NULL,
  `email` varchar(255) default NULL,
  `organization` varchar(255) default NULL,
  PRIMARY KEY  (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of td_teacher
-- ----------------------------
INSERT INTO `td_teacher` VALUES ('1', '																																																																																																																														我是11111111好好学生  2222 333333   444444444   555555555', '李凡', 'img/head.png', '男', '学士', '1985', '2558057081@qq.com', '吉首大学');
INSERT INTO `td_teacher` VALUES ('2', null, 'admin', null, null, null, null, null, null);
INSERT INTO `td_teacher` VALUES ('14', '六年Java开发经验，三年项目管理经验，两年企业内部培训经验；精通JavaEE，主流Web框架技术；HTML、CSS、Javascript前台页面技术；MySql,Oracle数据库技术，曾主导山东省高院，青岛中院OA项目，青岛啤酒大型门户项目，云南白药SOA平台构建等项目。', '刘刚', 'img/Liu_gang.jpg', '男', '博士', '1985', '16456484@163.com', '青软实训');
INSERT INTO `td_teacher` VALUES ('15', '具有丰富的IT行业新人培训、实训经验，拥有7年的项目开发及管理经验。曾先后参与网络通讯、人才网站及山东省血液联网等多个大中型项目。精通J2EE企业及开发及各种开源框架，熟练应用各种框架技术，对数据库建模及MS SQLSERVER、ORACLE等数据库的实用和管理也有多年丰富的实战经验。', '冯娟娟', 'img/Feng_Juanjuan.png', '女', '博士', '1985', null, null);
INSERT INTO `td_teacher` VALUES ('16', '拥有6年IT教学经验和3年开发经验，迄今为止教授过的学生近4000人；精通C、JAVA语言、SQL SERVER 和ORACLE数据库的开发与设计；熟悉项目的分析、需求分档的编写、设计、开发和实施过程，从事过学员收费系统的开发、办公室OA系统开发和青啤的散点图项目及点评项目等。', '徐美娇', 'img/Xu_Meijiao.jpg', '女', '博士', '1985', null, null);
INSERT INTO `td_teacher` VALUES ('17', '多年软件开发工作经验，熟练应用各种框架技术：extjs、struts、springmvc等。对 Mysql、ORACLE等数据库有多年的实战经验。曾先后参与连锁餐饮店管理、网络舆情监控、集团OA等多个大中型项目的设计与开发，对移动端的原生开发以及混合开发均有所研究。', '任红兵', 'img/Ren_Hongbing.png', '男', '博士', '1985', null, null);
INSERT INTO `td_teacher` VALUES ('18', '毕业于中国海洋大学,荣获微软2008-2015年度 MVP(最有价值专家)称号，精通多种开发技术和语言；15年以上的.NET、Java、PHP开发经验；从传统的Delphi、C++到当前流行的Python、Ruby、AS、GO等都有丰富的项目经验；在信息监控、人工智能和数据挖掘等领域参与建设了多项国家级基金项目。', '杨守斌', 'img/Yang_Shoubin.jpg', '男', '博士', '1985', null, null);
INSERT INTO `td_teacher` VALUES ('19', '									毕业于合肥工业大学，十年项目开发和员工培训经历，在游戏开发、游戏制作、三维动画、影视制作等相关领域有丰富的经验，精通unity3d、html5、Maya等相关引擎和软件，负责游戏开发相关岗位培训大纲的制定，参与课程效果的评定与修订工作，从事培训课堂教学，善于培训从零开始的新人。', '乐天', 'img/Le_tian.jpg', '男', '博士', '1985', '16448874233@qq.com', '');
INSERT INTO `td_teacher` VALUES ('20', '80后美女讲师，毕业于青岛科技大学，现任视觉设计总监，聚合blog创始人之一，参与青岛web达人联盟群的创建以及线上线下的沙龙组织者。带领团队参与《欧冠足球》、《欧冠足球2》及Arpg类游戏《青蛇》《萌将》的ui设计及游戏内的交互设计，有丰富的实战经验。', '郑曼曼', 'img/Zheng_Manman.jpg', '女', '博士', '1985', null, null);
INSERT INTO `td_teacher` VALUES ('21', '大学讲师，青岛伟东云教育发展有限公司特邀讲师。具有多年一线教学经验。主要讲授OFFICE的高级应用及计算机语言等相关课程。多次指导学生参加全国计算机应用水平的大赛并获得全国一二等奖的荣誉。为多家单位举办过办公软件高级应用的培训，广受好评。并被青岛伟东微软公司聘为MOS认证“特邀讲师” 。', '吴海霞', 'img/Wu_Haixia.jpg', '女', '博士', '1985', null, null);
INSERT INTO `td_teacher` VALUES ('22', '博士，副教授；精通Java语言，熟悉Java Web技术、Android技术，熟悉Java EE架构，熟悉Struts、Hibernate、Spring等框架，熟悉MySQL、Oracle数据库。拥有10余年开发和授课经验，出版计算机类编程书籍40余部。', '孙更新', 'img/Sun_Gengxin.jpg', '男', '博士', '1985', null, null);
INSERT INTO `td_teacher` VALUES ('28', null, null, null, null, null, null, null, null);

-- ----------------------------
-- Table structure for td_user
-- ----------------------------
DROP TABLE IF EXISTS `td_user`;
CREATE TABLE `td_user` (
  `id` int(5) NOT NULL auto_increment,
  `userName` varchar(255) default NULL,
  `password` varchar(255) default NULL,
  `role` int(2) default NULL,
  PRIMARY KEY  (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of td_user
-- ----------------------------
INSERT INTO `td_user` VALUES ('1', 'lifan', 'lifan', '0');
INSERT INTO `td_user` VALUES ('2', 'admin', 'admin', '2');
INSERT INTO `td_user` VALUES ('14', 'liugang', 'liugang', '1');
INSERT INTO `td_user` VALUES ('15', 'fenjuanjuan', 'fenjuanjuan', '1');
INSERT INTO `td_user` VALUES ('16', 'xumeijiao', 'xumeijiao', '1');
INSERT INTO `td_user` VALUES ('17', 'renhongbing', 'renhongbing', '1');
INSERT INTO `td_user` VALUES ('18', 'yangshoubing', 'yangshoubing', '1');
INSERT INTO `td_user` VALUES ('19', 'letian', 'letian', '1');
INSERT INTO `td_user` VALUES ('20', 'zhenmanman', 'zhenmanman', '1');
INSERT INTO `td_user` VALUES ('21', 'wuhaixia', 'wuhaixia', '1');
INSERT INTO `td_user` VALUES ('22', 'sungengxin', 'sungengxin', '1');
INSERT INTO `td_user` VALUES ('28', 'www', 'www', '0');

-- ----------------------------
-- Table structure for td_zvedio
-- ----------------------------
DROP TABLE IF EXISTS `td_zvedio`;
CREATE TABLE `td_zvedio` (
  `cid` int(11) NOT NULL,
  `zid` int(11) NOT NULL,
  `zContent` varchar(255) default NULL,
  PRIMARY KEY  (`cid`,`zid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of td_zvedio
-- ----------------------------
INSERT INTO `td_zvedio` VALUES ('21', '1', '第一章 javaWeb 入门基础');
INSERT INTO `td_zvedio` VALUES ('21', '2', '第二章 Servlet基础');
INSERT INTO `td_zvedio` VALUES ('21', '3', '第三章  servlet接口核心');
INSERT INTO `td_zvedio` VALUES ('21', '4', '第四章 会话跟踪');
INSERT INTO `td_zvedio` VALUES ('21', '5', '第五章 JSP语法');
INSERT INTO `td_zvedio` VALUES ('21', '6', '第六章  JSP内置对象');
INSERT INTO `td_zvedio` VALUES ('22', '1', '第一章 java基础应用');
INSERT INTO `td_zvedio` VALUES ('24', '1', '第一章  C++简介');
INSERT INTO `td_zvedio` VALUES ('24', '2', '第二章 c++语法');
INSERT INTO `td_zvedio` VALUES ('24', '3', '第三章 类和对象的创建');
INSERT INTO `td_zvedio` VALUES ('25', '1', '第一章 Unity简介');
INSERT INTO `td_zvedio` VALUES ('25', '2', '第二章  Unity创建');
INSERT INTO `td_zvedio` VALUES ('26', '1', '第一章 数据结构和算法绪论');
INSERT INTO `td_zvedio` VALUES ('26', '2', '第二章  线性表');
